//
// Created by mfbut on 2/11/2019.
//

#ifndef LINKEDLIST_DOUBLELINKEDNODE_H
#define LINKEDLIST_DOUBLELINKEDNODE_H
#include <iostream>

//the class that represents a node in the list

template<typename T>
class DoubleLinkedNode {
public:
	T val;
	T* prev;
	T* next;

	DoubleLinkedNode<T>(T& value=NULL) {
		val = value;
		next = NULL;
		prev = NULL;
	}
};


#endif //LINKEDLIST_DOUBLELINKEDNODE_H
