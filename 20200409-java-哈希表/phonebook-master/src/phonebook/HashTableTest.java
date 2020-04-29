package phonebook;

import org.junit.Before;

import org.junit.Test;
import phonebook.hashes.*;

public class HashTableTest {
    private HashTable hashtable;

    @Before
    public void setUp() {
//        hashtable = new LinearProbingHashTable(true);
//        hashtable = new LinearProbingHashTable(false);
//        hashtable = new OrderedLinearProbingHashTable(true);
//        hashtable = new OrderedLinearProbingHashTable(false);
//        hashtable = new QuadraticProbingHashTable(true);
//        hashtable = new QuadraticProbingHashTable(false);
        hashtable = new SeparateChainingHashTable();
    }

    @Test
    public void test1(){
        System.out.println();
    }
}