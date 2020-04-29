package phonebook.hashes;

import java.util.Iterator;

import phonebook.exceptions.UnimplementedMethodException;
import phonebook.utils.KVPair;
import phonebook.utils.KVPairList;
import phonebook.utils.PrimeGenerator;
import phonebook.utils.Probes;

/**<p>{@link SeparateChainingHashTable} is a {@link HashTable} that implements <b>Separate Chaining</b>
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
public class SeparateChainingHashTable implements HashTable{

    /* ****************************************************************** */
    /* ***** PRIVATE FIELDS / METHODS PROVIDED TO YOU: DO NOT EDIT! ***** */
    /* ****************************************************************** */

    private KVPairList[] table;
    private int count;
    private PrimeGenerator primeGenerator;
    

    // We mask the top bit of the default hashCode() to filter away negative values.
    // Have to copy over the implementation from OpenAddressingHashTable; no biggie.
    private int hash(String key){
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    /* **************************************** */
    /*  IMPLEMENT THE FOLLOWING PUBLIC METHODS:  */
    /* **************************************** */
    /**
     *  Default constructor. Initializes the internal storage with a size equal to the default of {@link PrimeGenerator}.
     */
    public SeparateChainingHashTable(){
    	 this.count = 0;
         this.primeGenerator = new PrimeGenerator();
         this.table = new KVPairList[this.primeGenerator.getCurrPrime()];
    }

    @Override
    public Probes put(String key, String value) {
        
    	if(key == null || value == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	
    	int probeCount = 1;
    	
    	if(count>0 && (((float)count/table.length)*100) >= 70.0) {
    		this.enlarge();
    	}
    	int hash = this.hash(key);
    	
    	if(table[hash]!=null) {
    		table[hash].addBack(key, value);
    	}
    	else {
    		table[hash]=new KVPairList(key, value);
    	}
    	
    	this.count+=1;
    	return new Probes(value, probeCount);
    	
    }

    @Override
    public Probes get(String key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	int hash = this.hash(key);
    	int probeCount = 1;
    	
    	if(table[hash]!=null ) {
			Iterator it=table[hash].iterator();
			while(it.hasNext()) {
				
				KVPair kv=(KVPair) it.next();
				if(kv.getKey().equals(key)) {
					return new Probes(kv.getValue(), probeCount);
				}
				probeCount += 1;
			}
			
    	}
    	
    	return new Probes(null,probeCount);
    }

    @Override
    public Probes remove(String key) {
        
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	int hash=this.hash(key);
    	int probeCount=1;
    	Probes p1 = new Probes(null, 0);
    	if(table[hash]!=null) {
    		Iterator it=table[hash].iterator();
			while(it.hasNext()) {
				
				KVPair kv=(KVPair) it.next();
				if(kv.getKey().equals(key)) {
					p1 = table[hash].removeByKey(key);
					probeCount += p1.getProbes();
					this.count -= 1;
					return new Probes(p1.getValue(), p1.getProbes());
				}
				probeCount += 1;
			}
			
			
    	}

    	return new Probes(null,  probeCount);
    }

    @Override
    public boolean containsKey(String key) {
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	int hash = this.hash(key);
    	
    	if(table[hash]!=null) {
    		if(table[hash].containsKey(key)) {
    			return true;
    		}
    	}
    	
    	return false;
    	
    }

    @Override
    public boolean containsValue(String value) {
    	if(value == null) {
    		throw new IllegalArgumentException();
    	}
    	for(int i=0; i < this.table.length; i++) {
			if(this.table[i] != null) {
				Iterator it=this.table[i].iterator();
				while(it.hasNext()) {
					KVPair kv=(KVPair) it.next();
					if(kv.getValue().equals(value)) {
						return true;
					}
				}
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
        return table.length; // Or the value of the current prime.
    }

    /**
     * Enlarges this hash table. At the very minimum, this method should increase the <b>capacity</b> of the hash table and ensure
     * that the new size is prime. The class {@link PrimeGenerator} implements the enlargement heuristic that
     * we have talked about in class and can be used as a black box if you wish.
     * @see PrimeGenerator#getNextPrime()
     */
    public void enlarge() {
    	this.count = 0;
		KVPairList[] temp = new KVPairList[table.length];
		for(int i=0;i<table.length;i++) {
			temp[i]=this.table[i];
		}
		this.table = new KVPairList[this.primeGenerator.getNextPrime()];
		int i=0;
		for(i=0; i < temp.length; i++) {
			if(temp[i] != null) {
				Iterator it=temp[i].iterator();
				while(it.hasNext()) {
					KVPair kv=(KVPair) it.next();
					this.put(kv.getKey(),kv.getValue());
				}
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
    public void shrink(){
    	this.count = 0;
		KVPairList[] temp = new KVPairList[table.length];
		for(int i=0;i<table.length;i++) {
			temp[i]=this.table[i];
		}
		this.table = new KVPairList[this.primeGenerator.getPreviousPrime()];
		int i=0;
		for(i=0; i < temp.length; i++) {
			if(temp[i] != null) {
				Iterator it=temp[i].iterator();
				while(it.hasNext()) {
					KVPair kv=(KVPair) it.next();
					this.put(kv.getKey(),kv.getValue());
				}
			}
		}
    }
}
