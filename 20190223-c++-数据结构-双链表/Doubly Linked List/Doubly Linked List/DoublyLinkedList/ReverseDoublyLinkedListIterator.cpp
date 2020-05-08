#include "ReverseDoublyLinkedListIterator.h"

//copy constructor
template<typename T>
ReverseDoublyLinkedListIterator<T>::ReverseDoublyLinkedListIterator(const ReverseDoublyLinkedListIterator& orig)
{

}

//are the two iterators equal?
//they are if they are over the same doubly linked list
//and (they are referring to the same element in the list
//or they are out of bounds)
template<typename T>
bool ReverseDoublyLinkedListIterator<T>::operator==(const ReverseDoublyLinkedListIterator<T>& rhs) const
{

}

//are the two iterators different?
template<typename T>
bool ReverseDoublyLinkedListIterator<T>::operator!=(ReverseDoublyLinkedListIterator<T>& rhs) const
{

}

//is the iterator safe to dereference?
template<typename T>
ReverseDoublyLinkedListIterator<T>::operator bool() const 
{

}

//go to the next element
template<typename T>
ReverseDoublyLinkedListIterator<T>& ReverseDoublyLinkedListIterator<T>::operator++() //pre
{

}
template<typename T>
const ReverseDoublyLinkedListIterator<T> ReverseDoublyLinkedListIterator<T>::operator++(int)//post
{

}

//go to the previous element
template<typename T>
ReverseDoublyLinkedListIterator<T>& ReverseDoublyLinkedListIterator<T>::operator--() //pre
{

}

template<typename T>
const ReverseDoublyLinkedListIterator<T> ReverseDoublyLinkedListIterator<T>::operator--(int)//post
{

}

//get a const reference to the value
template<typename T>
const T& ReverseDoublyLinkedListIterator<T>::operator*() const
{

}

//get a non-const reference to the value
template<typename T>
T& ReverseDoublyLinkedListIterator<T>::operator*()
{

}