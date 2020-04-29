/**
 * This file represents a node in a doubly linked list.
 * 
 * @author CS1027A
 *
 */
public class DoubleNode<T>{
	
	/**
	 * 'next' is a reference to the next DoubleNode in the doubly linked list.
	 */
    private DoubleNode<T> next;
    
	/**
	 * 'previous' is a reference to the previous DoubleNode in the doubly linked list.
	 */
    private DoubleNode<T> previous;
    
	/**
	 * 'element' contains the data of the DoubleNode in the doubly linked list.
	 */
    private T element;
    
	/**
	 * This method creates a new DoubleNode with no element.
	 */
    public DoubleNode() {
    	
        next = null;
        previous = null;
        element = null;
        
    } //end DoubleNode (constructor)
    
	/**
	 * This method creates a new DoubleNode with an element.
	 * @param elem the element in the DoubleNode
	 */
    public DoubleNode (T elem) {
    	
        next = null;
        previous = null;
        element = elem;
        
    } //end DoubleNode (constructor)
    
	/**
	 * This method returns the next DoubleNode in the doubly linked list
	 * @return the next DoubleNode in the doubly linked list
	 */
    public DoubleNode<T> getNext() {
    	
        return next;
        
    } //end getNext
    
	/**
	 * This method returns the previous DoubleNode in the doubly linked list
	 * @return the previous DoubleNode in the doubly linked list
	 */
    public DoubleNode<T> getPrevious() {
    	
        return previous;
        
    } //end getPrevious
    
	/**
	 * This method sets the next DoubleNode in the doubly linked list
	 * @param the next DoubleNode in the doubly linked list
	 */
    public void setNext(DoubleNode<T> node) {
    	
        next = node;
        
    } //end setNext
    
	/**
	 * This method sets the previous DoubleNode in the doubly linked list
	 * @param the previous DoubleNode in the doubly linked list
	 */
    public void setPrevious(DoubleNode<T> node) {
    	
        previous = node;
        
    } //end setPrevious
    
	/**
	 * This method returns the element of the DoubleNode in the doubly linked list
	 * @return the element of the DoubleNode in the doubly linked list
	 */
    public T getElement() {
    	
        return element;
        
    } //end getElement
    
	/**
	 * This method sets the element of the DoubleNode in the doubly linked list
	 * @param the element of the DoubleNode in the doubly linked list
	 */
    public void setElement(T elem) {
    	
        element = elem;
        
    } //end setElement
    
	/**
	 * This method returns the string representation of the element of the DoubleNode in the doubly linked list
	 * @return the string representation of the element of the DoubleNode in the doubly linked list
	 */
    public String toString() {
    	
    	return "'" + element + "'";
    	
    } //end toString
    
} //end DoubleNode (class)
