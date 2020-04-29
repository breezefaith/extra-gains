package phonebook.hashes;

import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link LinearProbingHashTable} is an Openly Addressed {@link HashTable} implemented with <b>Linear Probing</b> as its
 * collision resolution strategy: every key collision is resolved by moving one address over. It is
 * the most famous collision resolution strategy, praised for its simplicity, theoretical properties
 * and cache locality. It <b>does</b>, however, suffer from the &quot; clustering &quot; problem:
 * collision resolutions tend to cluster collision chains locally, making it hard for new keys to be
 * inserted without collisions. {@link QuadraticProbingHashTable} is a {@link HashTable} that
 * tries to avoid this problem, albeit sacrificing cache locality.</p>
 *
 * @author YOUR NAME HERE!
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see OrderedLinearProbingHashTable
 * @see QuadraticProbingHashTable
 * @see CollisionResolver
 */
public class LinearProbingHashTable extends OpenAddressingHashTable {

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
    public LinearProbingHashTable(boolean soft) {
        super();
        this.primeGenerator = new PrimeGenerator();
        this.count = 0;
        this.softFlag = soft;
        this.table = new KVPair[this.primeGenerator.getCurrPrime()];
    }

    /**
     * Inserts the pair &lt;key, value&gt; into this. The container should <b>not</b> allow for {@code null}
     * keys and values, and we <b>will</b> test if you are throwing a {@link IllegalArgumentException} from your code
     * if this method is given {@code null} arguments! It is important that we establish that no {@code null} entries
     * can exist in our database because the semantics of {@link #get(String)} and {@link #remove(String)} are that they
     * return {@code null} if, and only if, their key parameter is {@code null}. This method is expected to run in <em>amortized
     * constant time</em>.
     * <p>
     * Instances of {@link LinearProbingHashTable} will follow the writeup's guidelines about how to internally resize
     * the hash table when the capacity exceeds 50&#37;
     *
     * @param key   The record's key.
     * @param value The record's value.
     * @return The {@link phonebook.utils.Probes} with the value added and the number of probes it makes.
     * @throws IllegalArgumentException if either argument is {@code null}.
     */
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
            index = (index + 1) % this.table.length;
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
            index = (index + 1) % table.length;
            probes += 1;
        }
        return new Probes(table[index] != null ? table[index].getValue() : null, probes);
    }


    /**
     * <b>Return</b> and <b>remove</b> the value associated with key in the {@link HashTable}. If key does not exist in the database
     * or if key = {@code null}, this method returns {@code null}. This method is expected to run in <em>amortized constant time</em>.
     *
     * @param key The key to search for.
     * @return The {@link phonebook.utils.Probes} with associated value and the number of probe used. If the key is {@code null}, return value {@code null}
     * and 0 as number of probes; if the key dones't exists in the database, return {@code null} and the number of probes used.
     */
    @Override
    public Probes remove(String key) {
        if (key == null) {
            return new Probes(null, 0);
        }
        int index = hash(key);
        int probes = 1;

        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + 1) % table.length;
            probes++;
        }

        if (table[index] != null) {
            KVPair old = table[index];
            if (softFlag) {
                table[index] = this.TOMBSTONE;
                tombstoneCount++;
            } else {
                table[index] = null;
                int i = (index + 1) % this.table.length;

                while (table[i] != null) {
                    table[i - 1 + this.table.length] = table[i];
                    i = (i + 1) % table.length;
                    probes++;
                }
                count--;
            }
            return new Probes(old.getValue(), probes + 1);
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
        return this.count;
    }

    @Override
    public int capacity() {
        return (table != null) ? table.length : primeGenerator.getCurrPrime();
    }
}
