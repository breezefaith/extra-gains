package phonebook.hashes;

import phonebook.exceptions.UnimplementedMethodException;
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
 *
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
	private int tombStoneCount;
	
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
    public QuadraticProbingHashTable(boolean soft) {
    	this.softFlag  = soft;
        this.count = 0;
        this.tombStoneCount = 0;
        this.primeGenerator = new PrimeGenerator();
        this.table = new KVPair[this.primeGenerator.getCurrPrime()];
    }

    @Override
    public Probes put(String key, String value) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	
    	
    	
    	int probeCount = 1;
    	if(count > 0)
    	if(count>0 && (table.length ) / (count + tombStoneCount) < 2) {
    		int c = resize(this.primeGenerator.getNextPrime());
    		probeCount += c;
    	}
    	int hash = this.hash(key);
    	while(table[hash] != null) {
    		hash = (hash + (probeCount  ) + (probeCount ) * (probeCount )) % table.length;
    		probeCount = probeCount + 1;
    	}

    	table[hash] = new KVPair(key, value);
    	
    	this.count += 1;
        return new Probes(value, probeCount);
    }


    @Override
    public Probes get(String key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	int probeCount = 1;
    	int hash = this.hash(key);
    	
    	while(table[hash] != null && !table[hash].getKey().equals(key) && !table[hash].equals(this.TOMBSTONE)) {
    		hash = (hash + (probeCount  ) + (probeCount ) * (probeCount )) % table.length;
    		probeCount += 1;
    	}
    	
    	if(table[hash] != null) {
    		return new Probes(table[hash].getValue(), probeCount);
    	}
    	return new Probes(null, probeCount);
    }

    @Override
    public Probes remove(String key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    
    	int probeCount = 1;
    	int hash = this.hash(key);

    	while(table[hash] != null && !table[hash].getKey().equals(key)) {
    		hash = (hash + (probeCount  ) + (probeCount ) * (probeCount )) % table.length;
    		probeCount += 1;
    	}
    	KVPair p = new KVPair(null, null);
    	if(!this.softFlag){
	    	if(table[hash] != null) {
	    		p = table[hash];
	    		table[hash] = null;
	    		int c = resize(this.primeGenerator.getCurrPrime());
	    		probeCount = probeCount + c;
	    	}
	    	else {
	    		return new Probes(null, probeCount);
	    	}
	    	
    	}
    	else {
    		if(table[hash] != null) {
    			p = table[hash];
	    		table[hash] = this.TOMBSTONE;
	    		this.tombStoneCount += 1;
	    		
	    	}
	    	else {
	    		return new Probes(null, probeCount);
	    	}
    	}
    	return  new Probes(p.getValue(), probeCount );
    }


    @Override
    public boolean containsKey(String key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	int hash = this.hash(key);
    	int probeCount = 1;
    	while(table[hash] != null && !table[hash].getKey().equals(key)) {
    		hash = (hash + (probeCount  ) + (probeCount ) * (probeCount )) % table.length;
    		probeCount += 1;
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
    public int size(){
    	return this.count;
    }

    @Override
    public int capacity() {
        return this.table.length;        		
    }

}