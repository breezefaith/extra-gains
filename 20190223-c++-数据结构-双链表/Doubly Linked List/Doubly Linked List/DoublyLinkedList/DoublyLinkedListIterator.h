//
// Created by mfbut on 2/11/2019.
//

#ifndef LINKEDLIST_GENERICDOUBLYLINKEDLISTITERATOR_H
#define LINKEDLIST_GENERICDOUBLYLINKEDLISTITERATOR_H

#include "DoubleLinkedNode.h"


template<typename T>
class DoublyLinkedListIterator {
  //you must implement at least the methods below
  //you are free to add (and will likely need to add)
  //more members and methods

 public:
  DoublyLinkedListIterator(const DoublyLinkedListIterator& orig);
  //are the two iterators equal?
  //they are if they are over the same doubly linked list
  //and (they are referring to the same element in the list
  //or they are out of bounds)
  bool operator==(const DoublyLinkedListIterator<T>& rhs) const;

  //are the two iterators different?
  bool operator!=(const DoublyLinkedListIterator<T>& rhs) const;

  //is the iterator safe to dereference?
  operator bool() const;

  //go to the next element
  DoublyLinkedListIterator<T>& operator++(); //pre
  const DoublyLinkedListIterator<T> operator++(int);//post

  //go to the previous element
  DoublyLinkedListIterator<T>& operator--(); //pre
  const DoublyLinkedListIterator<T> operator--(int); //post

  //get a const reference to the value
  const T& operator*() const;

  //get a non const iterator
  T& operator*();


  DoubleLinkedNode<T>& getCurNode() { return this->curNode; }

private:
	DoubleLinkedNode<T>& curNode;

};

#endif //LINKEDLIST_GENERICDOUBLYLINKEDLISTITERATOR_H
