#include "ConstDoublyLinkedListIterator.h"

template<typename T>
ConstDoublyLinkedListIterator<T>::ConstDoublyLinkedListIterator(const ConstDoublyLinkedListIterator& orig) 
{
	this->curNode = orig.curNode;
}

//are the two iterators equal?
//they are if they are over the same doubly linked list
//and (they are referring to the same element in the list
//or they are out of bounds)
template<typename T>
bool ConstDoublyLinkedListIterator<T>::operator==(const ConstDoublyLinkedListIterator<T>& rhs)const 
{
	return rhs.curNode.next == this->curNode.next && rhs.curNode.prev == this->curNode.prev && rhs.curNode.val == this->curNode.val;
}

//are the two iterators different?
template<typename T>
bool ConstDoublyLinkedListIterator<T>::operator!=(const ConstDoublyLinkedListIterator<T>& rhs) const 
{
	return !this->operator==(rhs);
}

//is the iterator safe to dereference?
template<typename T>
ConstDoublyLinkedListIterator<T>::operator bool() const 
{
	return true;
}

//go to the next element
template<typename T>
ConstDoublyLinkedListIterator<T>& ConstDoublyLinkedListIterator<T>::operator++() //pre
{
	this->curNode = this->curNode.next;
	return *this;
}
template<typename T>
const ConstDoublyLinkedListIterator<T> ConstDoublyLinkedListIterator<T>::operator++(int)//post
{
	ConstDoublyLinkedListIterator<T> temp(*this);
	this->curNode = this->curNode.next;
	return temp;
}

//go to the previous element
template<typename T>
ConstDoublyLinkedListIterator<T>& ConstDoublyLinkedListIterator<T>::operator--() //pre
{
	this->curNode = this->curNode.prev;
	return *this;
}
template<typename T>
const ConstDoublyLinkedListIterator<T> ConstDoublyLinkedListIterator<T>::operator--(int) //post
{
	ConstDoublyLinkedListIterator<T> temp(*this);
	this->curNode = this->curNode.prev;
	return temp;
}

//get a const reference to the value
template<typename T>
const T& ConstDoublyLinkedListIterator<T>::operator*() const
{
	return this->curNode.val;
}

template<typename T>
ConstDoublyLinkedListIterator<T>::ConstDoublyLinkedListIterator(const DoubleLinkedNode<T>& node)
{
	this->curNode = node;
}
