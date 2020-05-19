#include "DoublyLinkedListIterator.h"

template<typename T>
DoublyLinkedListIterator<T>::DoublyLinkedListIterator(const DoublyLinkedListIterator& orig)
{
	this->curNode = orig.curNode;
}
//are the two iterators equal?
//they are if they are over the same doubly linked list
//and (they are referring to the same element in the list
//or they are out of bounds)
template<typename T>
bool DoublyLinkedListIterator<T>::operator==(const DoublyLinkedListIterator<T>& rhs) const
{

}

//are the two iterators different?
template<typename T>
bool DoublyLinkedListIterator<T>::operator!=(const DoublyLinkedListIterator<T>& rhs) const
{
	return !this->operator==(rhs);
}

//is the iterator safe to dereference?
template<typename T>
DoublyLinkedListIterator<T>::operator bool() const
{

}

//go to the next element
template<typename T>
DoublyLinkedListIterator<T>& DoublyLinkedListIterator<T>::operator++() //pre
{
	this->curNode = this->curNode.next;
	return *this;
}
template<typename T>
const DoublyLinkedListIterator<T> DoublyLinkedListIterator<T>::operator++(int)//post
{
	ConstDoublyLinkedListIterator<T> temp(*this);
	this->curNode = this->curNode.next;
	return temp;
}

//go to the previous element
template<typename T>
DoublyLinkedListIterator<T>& DoublyLinkedListIterator<T>::operator--() //pre
{
	this->curNode = this->curNode.prev;
	return *this;
}
template<typename T>
const DoublyLinkedListIterator<T> DoublyLinkedListIterator<T>::operator--(int) //post
{
	ConstDoublyLinkedListIterator<T> temp(*this);
	this->curNode = this->curNode.prev;
	return temp;
}

//get a const reference to the value
template<typename T>
const T& DoublyLinkedListIterator<T>::operator*() const
{
	return this->curNode.val;
}

//get a non const iterator
template<typename T>
T& DoublyLinkedListIterator<T>::operator*()
{
	return new DoublyLinkedListIterator<T>(*this);
}