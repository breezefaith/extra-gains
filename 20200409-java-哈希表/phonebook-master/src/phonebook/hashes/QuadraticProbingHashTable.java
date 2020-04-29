package phonebook.hashes;

import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link QuadraticProbingHashTable} is an Openly Addressed {@link HashTable} which uses <b>Quadratic
 * Probing</b> as its collision resolution strategy. Quadratic Probing differs from <b>Linear</b> Probing
 * in that collisions are resolved by taking &quot; jumps &quot; on the hash table, the length of which
 * determined by an increasing polynomial factor. For example, during a key insertion which generates
 * several collisions, the first collision will be resolved by moving 1^2 + 1 = 2 positions over from
 * the originally hashed address (like Linear Probing), the second one will be resolved by moving
 * 2^2 + 2= 6 positions over from our hashed address, the third one by moving 3^2 + 3 = 12 positions over, etc.
 * </p>
 *
 * <p>By using this collision resolution technique, {@link QuadraticProbingHashTable} aims to get rid of the
 * &quot;key clustering &quot; problem that {@link LinearProbingHashTable} suffers from. Leaving more
 * space in between memory probes allows other keys to be inserted without many collisions. The tradeoff
 * is that, in doing so, {@link QuadraticProbingHashTable} sacrifices <em>cache locality</em>.</p>
 *
 * @author YOUR NAME HERE!
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see OrderedLinearProbingHashTable
 * @see LinearProbingHashTable
 * @see CollisionResolver
 */
public class QuadraticProbingHashTable extends OpenAddressingHashTable {

    /* ********************************************************************/
    /* ** INSERT ANY PRIVATE METHODS OR FIELDS YOU WANT TO USE HERE: ******/
    /* ********************************************************************/

    /* ******************************************/
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS: */
    /* **************************************** */

    /**
     * Constructor with soft deletion option. Initializes the internal storage with a size equal to the starting value of  {@link PrimeGenerator}.
     *
     * @param soft A boolean indicator of whether we want to use soft deletion or not. {@code true} if and only if
     *             we want soft deletion, {@code false} otherwise.
     */
    public QuadraticProbingHashTable(boolean soft) {
        super();
        this.primeGenerator = new PrimeGenerator();
        this.count = 0;
        this.softFlag = soft;
        this.table = new KVPair[this.primeGenerator.getCurrPrime()];
    }

    @Override
    public Probes put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }
        int probes = 1;
        if (count > 0 && (double) count / capacity() >= loadFactor) {
            probes += resize();
        }

        int index = hash(key);
        while (table[index] != null) {
            index = (index + probes + probes * probes) % table.length;
            probes = probes + 1;
        }

        table[index] = new KVPair(key, value);
        count++;
        return new Probes(value, probes);
    }


    @Override
    public Probes get(String key) {
        if (key == null) {
            return new Probes(null, 0);
        }
        int index = hash(key);
        int probes = 1;

        while (table[index] != null && !table[index].getKey().equals(key) && !table[index].equals(this.TOMBSTONE)) {
            index = (index + probes + probes * probes) % table.length;
            probes++;
        }

        return new Probes(table[index] != null ? table[index].getValue() : null, probes);
    }

    @Override
    public Probes remove(String key) {
        if (key == null) {
            return new Probes(null, 0);
        }
        int hash = hash(key);
        int index = hash;
        int probes = 1;

        while (table[index] != null && table[index].getKey().equals(key) == false) {
            index = (index + probes + probes * probes) % table.length;
            probes++;
        }

        if (table[index] != null) {
            KVPair old = table[index];

            if (softFlag) {
                table[index] = this.TOMBSTONE;
                tombstoneCount++;
            } else {
                table[index] = null;
                probes += resize(primeGenerator.getCurrPrime());
            }
            return new Probes(old.getValue(), probes);
        } else {
            return new Probes(null, probes);
        }
    }

    @Override
    public boolean containsKey(String key) {
        Probes probes = get(key);
        return probes.getValue() != null && probes.getProbes() != 0;
    }

    @Override
    public boolean containsValue(String value) {
        if (table == null) {
            return false;
        }
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && value.equals(table[i].getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public int capacity() {
        return table == null ? primeGenerator.getCurrPrime() : table.length;
    }

}