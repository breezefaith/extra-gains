#include "ConstReverseDoublyLinkedListIterator.h"

//copy constructor
template<typename T>
ConstReverseDoublyLinkedListIterator<T>::ConstReverseDoublyLinkedListIterator(const ConstReverseDoublyLinkedListIterator& orig)
{
	this->curNode = orig.curNode;
}

//are the two iterators equal?
 //they are if they are over the same doubly linked list
 //and (they are referring to the same element in the list
 //or they are out of bounds)
template<typename T>
bool ConstReverseDoublyLinkedListIterator<T>::operator==(const ConstReverseDoublyLinkedListIterator<T>& rhs) const
{
	return rhs.curNode.next == this->curNode.next && rhs.curNode.prev == this->curNode.prev && rhs.curNode.val == this->curNode.val;
}

//are the two iterators different?
template<typename T>
bool ConstReverseDoublyLinkedListIterator<T>::operator!=(ConstReverseDoublyLinkedListIterator<T>& rhs) const
{
	return !this->operator==(rhs);
}

//is the iterator safe to dereference?
template<typename T>
ConstReverseDoublyLinkedListIterator<T>::operator bool() const
{
	return true;
}

//go to the next element
template<typename T>
ConstReverseDoublyLinkedListIterator<T>& ConstReverseDoublyLinkedListIterator<T>::operator++() //pre
{
	this->curNode = this->curNode.prev;
	return *this;
}
template<typename T>
const ConstReverseDoublyLinkedListIterator<T> ConstReverseDoublyLinkedListIterator<T>::operator++(int)//post
{
	ConstReverseDoublyLinkedListIterator<T> temp(*this);
	this->curNode = this->curNode.prev;
	return temp;
}

//go to the previous element
template<typename T>
ConstReverseDoublyLinkedListIterator<T>& ConstReverseDoublyLinkedListIterator<T>::operator--()//pre
{
	this->curNode = this->curNode.next;
	return *this;
}
template<typename T>
const ConstReverseDoublyLinkedListIterator<T> ConstReverseDoublyLinkedListIterator<T>::operator--(int)//post
{
	ConstReverseDoublyLinkedListIterator<T> temp(*this);
	this->curNode = this->curNode.next;
	return temp;
}
//get a const reference to the value
template<typename T>
const T& ConstReverseDoublyLinkedListIterator<T>::operator*() const
{
	return this->curNode.val;
}

template<typename T>
ConstReverseDoublyLinkedListIterator<T>::ConstReverseDoublyLinkedListIterator(const DoubleLinkedNode<T>& node)
{
	this->curNode = node;
}