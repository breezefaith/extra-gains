package phonebook.hashes;

import phonebook.exceptions.UnimplementedMethodException;
import phonebook.utils.KVPair;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**
 * <p>{@link OrderedLinearProbingHashTable} is an Openly Addressed {@link HashTable} implemented with
 * <b>Ordered Linear Probing</b> as its collision resolution strategy: every key collision is resolved by moving
 * one address over, and the keys in the chain is in order. It suffer from the &quot; clustering &quot; problem:
 * collision resolutions tend to cluster collision chains locally, making it hard for new keys to be
 * inserted without collisions. {@link QuadraticProbingHashTable} is a {@link HashTable} that
 * tries to avoid this problem, albeit sacrificing cache locality.</p>
 *
 * @author YOUR NAME HERE!
 *
 * @see HashTable
 * @see SeparateChainingHashTable
 * @see LinearProbingHashTable
 * @see QuadraticProbingHashTable
 * @see CollisionResolver
 */
public class OrderedLinearProbingHashTable extends OpenAddressingHashTable {

    /* ********************************************************************/
    /* ** INSERT ANY PRIVATE METHODS OR FIELDS YOU WANT TO USE HERE: ******/
    /* ********************************************************************/
	
	public int tombstones() {
    	int c = 0;
    	for(int i=0; i < table.length; i++) {
			if(table[i]!= null &&  table[i].equals(this.TOMBSTONE)) {
				c += 1;
			}
		}
    	return c;
    }
	
	private int resize(int size) {
		this.count = 0;
		int c = 0;
		KVPair[] temp = this.table;
		this.table = new KVPair[this.primeGenerator.getCurrPrime()];
		int i=0;
		for(i=0; i < temp.length; i++) {
			c= c + 1;
			if(temp[i] != null && ! temp[i].equals(this.TOMBSTONE)) {
				c = c + this.put(temp[i].getKey(), temp[i].getValue()).getProbes();
			}
		}
		return c;
	}
    /* ******************************************/
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS: */
    /* **************************************** */

    /**
     * Constructor with soft deletion option. Initializes the internal storage with a size equal to the starting value of  {@link PrimeGenerator}.
     * @param soft A boolean indicator of whether we want to use soft deletion or not. {@code true} if and only if
     *               we want soft deletion, {@code false} otherwise.
     */
    public OrderedLinearProbingHashTable(boolean soft){
    	this.softFlag  = soft;
        this.count = 0;
        this.primeGenerator = new PrimeGenerator();
        this.table = new KVPair[this.primeGenerator.getCurrPrime()];
    }



    /**
     * Inserts the pair &lt;key, value&gt; into this. The container should <b>not</b> allow for {@code null}
     * keys and values, and we <b>will</b> test if you are throwing a {@link IllegalArgumentException} from your code
     * if this method is given {@code null} arguments! It is important that we establish that no {@code null} entries
     * can exist in our database because the semantics of {@link #get(String)} and {@link #remove(String)} are that they
     * return {@code null} if, and only if, their key parameter is {@code null}. This method is expected to run in <em>amortized
     * constant time</em>.
     *
     * Different from {@link LinearProbingHashTable}, the keys in the chain are <b>in order</b>. As a result, we might increase
     * the cost of insertion and reduce the cost on search miss. One thing to notice is that, in soft deletion, we ignore
     * the tombstone during the reordering of the keys in the chain. We will have some example in the writeup.
     *
     * Instances of {@link OrderedLinearProbingHashTable} will follow the writeup's guidelines about how to internally resize
     * the hash table when the capacity exceeds 50&#37;
     * @param key The record's key.
     * @param value The record's value.
     * @throws IllegalArgumentException if either argument is {@code null}.
     * @return The {@link phonebook.utils.Probes} with the value added and the number of probes it makes.
     */
    @Override
    public Probes put(String key, String value) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	
    	int probeCount = 1;

    	if(count>0 && (((float)count/table.length)*100) >= 50.0) {
    		int c = resize(this.primeGenerator.getNextPrime());
    		probeCount += c;
    	}
    	
    	int hash = this.hash(key);
  
    	while(table[hash] != null) {
    		int compare = table[hash].getKey().compareTo(key);
    		if (compare > 0) {
    			KVPair k = table[hash];
    			table[hash] = new KVPair(key, value);
    	    	Probes p1 = put(k.getKey(), k.getValue());
    	    	probeCount = p1.getProbes();
    	    	this.count -= 1;
    	    	break;
    		}
    		else {
	    		hash = (hash + 1) % table.length;
	    		probeCount = probeCount + 1;
    		}
    	}
    	
    	table[hash] = new KVPair(key, value);
    	this.count += 1; 

        return new Probes(value, probeCount);
    }

    @Override
    public Probes get(String key) {
    	if(key == null) {
    		return new Probes(null, 0);
    	}
    	int probeCount = 1;
    	int hash = this.hash(key);
    	int oldHash = hash;
    	while(table[hash] != null && !table[hash].getKey().equals(key) && !table[hash].equals(this.TOMBSTONE) ) {
    		int compare = table[hash].getKey().compareTo(key);
    		if (compare > 0) {
    			return new Probes(null, probeCount);    			
    		}
    		else {
	    		hash = (hash + 1) % table.length;
	    		probeCount += 1;
    		}
    	}
  
    	if(table[hash] != null) {
    		return new Probes(table[hash].getValue(), probeCount);
    	}
    	
    	return new Probes(null, probeCount);
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
    	if(key == null) {
    		return new Probes(null, 0);
    	}
    	Probes p = null;
    	int probeCount = 1;
    	int hash = this.hash(key);
    	int oldHash = hash;
    	while(table[hash] != null && !table[hash].getKey().equals(key) ) {
    		int compare = table[hash].getKey().compareTo(key);
    		if (compare > 0 ) {
    			return new Probes(null, probeCount);    			
    		}
    		else {
	    		hash = (hash + 1) % table.length;
	    		probeCount += 1;
    		}
    	}
    	KVPair kv = new KVPair(null, null);
    	int index = table.length;
    	if(this.softFlag){
	    	if(table[hash] != null) {
	    		kv = table[hash];
	    		table[hash] = this.TOMBSTONE;
	    	}
	    	else {
	    		return new Probes(null, probeCount);
	    	}
	    	
	    	p = new Probes(kv.getValue(), probeCount);
    	}
    	else {
    		if(table[hash] != null) {
    			kv =  table[hash];
    			this.count -= 1;
    			while(table[hash] != null ) {
    	    		table[hash] = table[( hash + 1) % this.table.length];
    	    		hash = (hash + 1) % this.table.length;
    	    	}
    			
    			p = new Probes(kv.getValue(), probeCount);
	    	}
    		else {
	    		return new Probes(null, probeCount);
	    	}
    	}
    	return p;
    }

    @Override
    public boolean containsKey(String key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	int hash = this.hash(key);
    	
    	while(table[hash] != null && !table[hash].getKey().equals(key)) {
    		hash = (hash + 1) % table.length;
    	}
    	
    	if(table[hash] != null) {
    		return true;
    	}
    	
    	return false;
    }

    @Override
    public boolean containsValue(String value) {
    	if(value == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	for(int i=0; i < table.length; i++) {
    		if(table[i] != null && table[i].getValue().equals(value)) {
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
        return this.table.length;
    }
}
