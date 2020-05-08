#include "DoublyLinkedList.h"


//create a Doubly Linked List that has the same values
  //as in the vector and in the same order
template<typename T>
DoublyLinkedList<T>::DoublyLinkedList(const std::vector<T>& values) {
	if (values == NULL || values.size() == 0) {
		this->DoublyLinkedList();
		return;
	}
	head = new DoubleLinkedNode();
	head->val = values.at(0);
	head->prev = NULL;
	head->next = NULL;
	tail = head;
	for (int i = 1; i < values.size(); i++) {
		push_back(values.at(i));
	}
	len = values.size();
}

//create an empty DoublyLinkedList
template<typename T>
DoublyLinkedList<T>::DoublyLinkedList() {
	len = 0;
	head = NULL;
	tai = NULL;
}


template<typename T>
DoublyLinkedList<T>::~DoublyLinkedList() {

}

//remove all of the elements from your list
template<typename T>
void DoublyLinkedList<T>::clear() {

}

//get a reference to the front element in the list
template<typename T>
const T& DoublyLinkedList<T>::front() const {
	return head;
}

template<typename T>
T& DoublyLinkedList<T>::front() {
	return head;
}

//get a reference to the last element in the list
template<typename T>
const T& DoublyLinkedList<T>::back() const {
	return tail;
}

template<typename T>
T& DoublyLinkedList<T>::back() {
	return tail;
}

//add a value to the front of the list
template<typename T>
void DoublyLinkedList<T>::push_front(const T& value) {
	DoubleLinkedNode<T> node = new DoubleLinkedNode();
	node.val = value;
	node.prev = NULL;
	node.next = head;
	head->prev = node;
	head = node;
	len++;
}

//add a value to the back of the list
template<typename T>
void DoublyLinkedList<T>::push_back(const T& value) {
	DoubleLinkedNode<T> node = new DoubleLinkedNode();
	node.val = value;
	node.next = NULL;
	node.prev = tail;
	tail->next = node;
	tail = node;
	len++;
}

//is the list empty?
template<typename T>
bool DoublyLinkedList<T>::empty() const {
	return len==0?;
}

//return the number of elements in the list
template<typename T>
int DoublyLinkedList<T>::size() const {
	return len;
}

//return a constant bidirectional iterator to the front of the list
template<typename T>
DoublyLinkedList<T>::const_iterator DoublyLinkedList<T>::begin() const {
	return new ConstDoublyLinkedListIterator<T>(*head);
}

template<typename T>
DoublyLinkedList<T>::const_iterator DoublyLinkedList<T>::end() const {
	return new ConstDoublyLinkedListIterator<T>(*tail);
}

//return a nonconstant bidirectional iterator to the front of the list
template<typename T>
DoublyLinkedList<T>::iterator DoublyLinkedList<T>::begin() {
	return new DoublyLinkedListIterator<T>(*head);
}

template<typename T>
DoublyLinkedList<T>::iterator DoublyLinkedList<T>::end() {
	return new DoublyLinkedListIterator<T>(*tail);
}

template<typename T>
DoublyLinkedList<T>::const_reverse_iterator DoublyLinkedList<T>::crbegin() const {
	return new ConstReverseDoublyLinkedListIterator(*tail);
}

template<typename T>
DoublyLinkedList<T>::const_reverse_iterator DoublyLinkedList<T>::crend() const {
	return new ConstReverseDoublyLinkedListIterator(*head);
}

template<typename T>
DoublyLinkedList<T>::reverse_iterator DoublyLinkedList<T>::rbegin() {
	return new ReverseDoublyLinkedListIterator<T>(*tail);
}

template<typename T>
DoublyLinkedList<T>::reverse_iterator DoublyLinkedList<T>::rend() {
	return new ReverseDoublyLinkedListIterator<T>(*tail);
}

//insert the value at the position in the list
//I promise not to use the iterator again after the insertion is done
//An example if we had the list 1 <-> 9 <-> 17
//And the iterator was pointing to the 9 and we wanted to
//insert -22 the result would be
//1 <-> 22 <-> 9 <-> 17
template<typename T>
void DoublyLinkedList<T>::insert(iterator & position, const T & value)
{
	if (len == 0) 
	{
		this->push_back(value);
		return;
	}

	DoubleLinkedNode<T>& oldNode = position.getCurNode();
	DoubleLinkedNode<T>& prevNode = oldNode.prev;
	DoubleLinkedNode<T> newNode = new DoubleLinkedNode<T>(value);

	if (prevNode == NULL) {
		oldNode.prev = newNode;
		newNode.next = oldNode;
		head = &newNode;
	}
	else 
	{
		oldNode.prev = newNode;
		newNode.next = oldNode;
		prevNode.next = newNode;
		newNode.prev = prevNode;
	}
	len++;
}

//remove the element at the position pointed to
//by the iterator.
//I promise not to use the iterator again after the erase is done
//An example if we had the list 1 <-> 9 <-> 17
//And when the wanted to erase the iterator was at the 9
//1 <-> 17
template<typename T>
void DoublyLinkedList<T>::erase(iterator & position)
{
	DoubleLinkedNode<T>& oldNode = position.getCurNode();
	DoubleLinkedNode<T>& prevNode = oldNode.prev;
	DoubleLinkedNode<T>& nextNode = oldNode.next;

	if (prevNode == NULL) 
	{
		head = &nextNode;
	}
	else if (nextNode == NULL)
	{
		tail = &prevNode;
	}
	else 
	{
		prevNode.next = &nextNode;
		nextNode.prev = &prevNode;
	}
	delete oldNode;
	len--;
}

//write to the stream each element in the list in order
//with a space in between them
template<typename T>
std::ostream& operator<<(std::ostream& out, const DoublyLinkedList<T>& doublyLinkedList) 
{
	for (ConstDoublyLinkedListIterator<T> i = doublyLinkedList.begin(); i != NULL; ++i) 
	{
		out << *i << " <-> ";
	}
	out << endl;
	return out;
}

//read elements from the stream as long as it is good
// or until a newline (\n) is encountered
//if a newline is encontered it should be consumed
template<typename T>
std::istream& operator>>(std::istream& in, DoublyLinkedList<T>& doublyLinkedList) {

}