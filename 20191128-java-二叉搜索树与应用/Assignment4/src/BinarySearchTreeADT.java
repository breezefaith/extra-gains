/**
 * BinarySearchTreeADT defines the interface to a binary tree data structure.
 *
 * @author CS1027A
 */
import java.util.Iterator;

public interface BinarySearchTreeADT<T> {
	
   /** 
    * Returns a reference to the root element 
    *
    * @return a reference to the root
    */
   public BinaryTreeNode<T> getRoot();

   /** 
    * Returns true if the binary tree is empty and false otherwise.
    *
    * @return true if the binary tree is empty
    */
   public boolean isEmpty();

   /** 
    * Returns the number of elements in the binary tree.
    *
    * @return the integer number of elements in the tree
   */
   public int size();

   /** 
    * Returns true if the binary tree contains an element that matches
    * the specified element and false otherwise. 
    *
    * @param element the element being sought in the tree
    * @return true if the tree contains the target element
    */
   public boolean contains(T element);

   /** 
    * Returns a reference to the binary tree node holding the
    * specified element if it is found in the binary tree. 
    * Throws an exception if the specified element is not found.
    *
    * @param node the current node of the binary tree
    * @param element the element being sought in the tree
    * @return a reference to the binary tree node holding the specified element
    */
   public BinaryTreeNode<T> find(BinaryTreeNode<T> node, Comparable<T> element) throws NonExistentKeyException;
   
   /**
    * Adds the specified element to the binary tree. Throws an exception
    * if the element is already in the tree.
    * 
    * @param node the current node of the binary tree
    * @param element the element to add to the binary tree
    */
   public void add(BinaryTreeNode<T> node, T element) throws DuplicatedKeyException;

   /**  
    * Returns the string representation of the binary tree.
    *
    * @return a string representation of the binary tree
    */
   public String toString();

   /**  
    * Returns a BinaryTreeIterator for the binary tree.
    *
    * @return a BinaryTreeIterator for the elements of the binary tree
    */
   public Iterator<T> iterator();
   
} //end BinarySearchTreeADT (interface)