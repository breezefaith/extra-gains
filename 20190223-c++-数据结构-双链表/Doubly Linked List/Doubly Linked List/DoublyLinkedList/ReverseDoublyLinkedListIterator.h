//
// Created by mfbut on 2/13/2019.
//

#ifndef LINKEDLIST_GENERICREVERSEDOUBLYLINKEDLISTITERATOR_H
#define LINKEDLIST_GENERICREVERSEDOUBLYLINKEDLISTITERATOR_H

#include "DoubleLinkedNode.h"

template<typename T>
class ReverseDoublyLinkedListIterator {
 public:
  //you must implement at least the methods below
  //you are free to add (and will likely need to add)
  //more members and methods

  //copy constructor
  ReverseDoublyLinkedListIterator(const ReverseDoublyLinkedListIterator& orig);

  //are the two iterators equal?
  //they are if they are over the same doubly linked list
  //and (they are referring to the same element in the list
  //or they are out of bounds)
  bool operator==(const ReverseDoublyLinkedListIterator<T>& rhs) const;

  //are the two iterators different?
  bool operator!=(ReverseDoublyLinkedListIterator<T>& rhs) const;

  //is the iterator safe to dereference?
  operator bool() const;

  //go to the next element
  ReverseDoublyLinkedListIterator<T>& operator++(); //pre
  const ReverseDoublyLinkedListIterator<T> operator++(int);//post

  //go to the previous element
  ReverseDoublyLinkedListIterator<T>& operator--(); //pre
  const ReverseDoublyLinkedListIterator<T> operator--(int);//post

  //get a const reference to the value
  const T& operator*() const;

  //get a non-const reference to the value
  T& operator*();

  DoubleLinkedNode<T>& getCurNode() { return this->curNode; }

private:
	DoubleLinkedNode<T>& curNode;

};

#endif //LINKEDLIST_GENERICREVERSEDOUBLYLINKEDLISTITERATOR_H
