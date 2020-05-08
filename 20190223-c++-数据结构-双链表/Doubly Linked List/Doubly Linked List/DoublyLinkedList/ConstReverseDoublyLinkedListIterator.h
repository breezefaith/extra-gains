//
// Created by mfbut on 2/16/2019.
//

#ifndef TESTDOUBLELINKEDLIST_CONSTREVERSEDOUBLYLINKEDLISTITERATOR_H
#define TESTDOUBLELINKEDLIST_CONSTREVERSEDOUBLYLINKEDLISTITERATOR_H

#include "DoubleLinkedNode.h"

template<typename T>
class ConstReverseDoublyLinkedListIterator {
//you must implement at least the methods below
  //you are free to add (and will likely need to add)
  //more members and methods

  //copy constructor
  ConstReverseDoublyLinkedListIterator(const ConstReverseDoublyLinkedListIterator& orig);

  //are the two iterators equal?
  //they are if they are over the same doubly linked list
  //and (they are referring to the same element in the list
  //or they are out of bounds)
  bool operator==(const ConstReverseDoublyLinkedListIterator<T>& rhs) const;

  //are the two iterators different?
  bool operator!=(ConstReverseDoublyLinkedListIterator<T>& rhs) const;

  //is the iterator safe to dereference?
  operator bool() const;

  //go to the next element
  ConstReverseDoublyLinkedListIterator<T>& operator++(); //pre
  const ConstReverseDoublyLinkedListIterator<T> operator++(int);//post

  //go to the previous element
  ConstReverseDoublyLinkedListIterator<T>& operator--(); //pre
  const ConstReverseDoublyLinkedListIterator<T> operator--(int);//post

  //get a const reference to the value
  const T& operator*() const;


  ConstReverseDoublyLinkedListIterator(const DoubleLinkedNode<T>& node);

private:
	DoubleLinkedNode<T>& curNode;

};

#endif //TESTDOUBLELINKEDLIST_CONSTREVERSEDOUBLYLINKEDLISTITERATOR_H
