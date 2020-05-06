package phonebook.hashes;

import phonebook.utils.KVPair;
import phonebook.utils.KVPairList;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link SeparateChainingHashTable} is a {@link HashTable} that implements <b>Separate Chaining</b>
 * as its collision resolution strategy, i.e the collision chains are implemented as actual
 * Linked Lists. These Linked Lists are <b>not assumed ordered</b>. It is the easiest and most &quot; natural &quot; way to
 * implement a hash table and is useful for estimating hash function quality. In practice, it would
 * <b>not</b> be the best way to implement a hash table, because of the wasted space for the heads of the lists.
 * Open Addressing methods, like those implemented in {@link LinearProbingHashTable} and {@link QuadraticProbingHashTable}
 * are more desirable in practice, since they use the original space of the table for the collision chains themselves.</p>
 *
 * @author YOUR NAME HERE!
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see LinearProbingHashTable
 * @see OrderedLinearProbingHashTable
 * @see CollisionResolver
 */
public class SeparateChainingHashTable implements HashTable {

    /* ****************************************************************** */
    /* ***** PRIVATE FIELDS / METHODS PROVIDED TO YOU: DO NOT EDIT! ***** */
    /* ****************************************************************** */

    private KVPairList[] table;
    private int count;
    private PrimeGenerator primeGenerator;

    // We mask the top bit of the default hashCode() to filter away negative values.
    // Have to copy over the implementation from OpenAddressingHashTable; no biggie.
    private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    /* **************************************** */
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS:  */
    /* **************************************** */

    /**
     * Default constructor. Initializes the internal storage with a size equal to the default of {@link PrimeGenerator}.
     */
    public SeparateChainingHashTable() {
        count = 0;
        primeGenerator = new PrimeGenerator();
        table = new KVPairList[primeGenerator.getCurrPrime()];
        for (int i = 0; i < table.length; i++) {
            table[i] = new KVPairList();
        }
    }

    @Override
    public Probes put(String key, String value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        KVPairList list = table[hash(key)];
        Probes probes = list.getValue(key);
        if (probes.getValue() == null) {
            list.addBack(key, value);
            if (++count > table.length) {
                enlarge();
            }
            return new Probes(value, 1);
        } else {
            list.updateValue(key, value);
            return new Probes(value, probes.getProbes());
        }
    }

    @Override
    public Probes get(String key) {
        if (key == null) {
            return new Probes(null, 0);
        }
        Probes probes = table[hash(key)].getValue(key);

        return new Probes(probes.getValue(), probes.getProbes());
    }

    @Override
    public Probes remove(String key) {
        if (key == null) {
            return new Probes(null, 0);
        }

        KVPairList list = table[hash(key)];
        Probes probes = list.removeByKey(key);
        count--;
        return probes;
    }

    @Override
    public boolean containsKey(String key) {
        Probes probes = get(key);
        return probes.getValue() != null && probes.getProbes() != 0;
    }

    @Override
    public boolean containsValue(String value) {
        for (int i = 0; i < table.length; i++) {
            if (table[i].containsValue(value) == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        int cnt = 0;
        for (int i = 0; i < table.length; i++) {
            cnt += table[i].size();
        }
        return cnt;
    }

    @Override
    public int capacity() {
        return table.length; // Or the value of the current prime.
    }

    /**
     * Enlarges this hash table. At the very minimum, this method should increase the <b>capacity</b> of the hash table and ensure
     * that the new size is prime. The class {@link PrimeGenerator} implements the enlargement heuristic that
     * we have talked about in class and can be used as a black box if you wish.
     *
     * @see PrimeGenerator#getNextPrime()
     */
    public void enlarge() {
        KVPairList[] oldTable = table;
        KVPairList[] newTable = new KVPairList[primeGenerator.getNextPrime()];
        table = newTable;
        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new KVPairList();
        }
        count = 0;
        for (int i = 0; i < oldTable.length; ++i) {
            for (KVPair item : oldTable[i]) {
                put(item.getKey(), item.getValue());
            }
        }
    }

    /**
     * Shrinks this hash table. At the very minimum, this method should decrease the size of the hash table and ensure
     * that the new size is prime. The class {@link PrimeGenerator} implements the shrinking heuristic that
     * we have talked about in class and can be used as a black box if you wish.
     *
     * @see PrimeGenerator#getPreviousPrime()
     */
    public void shrink() {
        KVPairList[] oldTable = table;
        KVPairList[] newTable = new KVPairList[primeGenerator.getPreviousPrime()];
        table = newTable;
        count = 0;

        for (int i = 0; i < newTable.length; i++) {
            newTable[i] = new KVPairList();
        }
        for (int i = 0; i < oldTable.length; ++i) {
            for (KVPair item : oldTable[i]) {
                put(item.getKey(), item.getValue());
            }
        }
    }
}
