//
// Created by Ryan XIA on 2017/2/22.
//

#ifndef LINKEDLIST_LINKEDLIST_H
#define LINKEDLIST_LINKEDLIST_H

#include <iostream>
using namespace std;

class Node {
private:
    int data;
    Node *next;

public:
    Node() : data(0), next(nullptr) {}
    int getData();
    void setData(int newData);
    Node *getNext();
    void setNext(Node *newNext);
};

class LinkedList {
private:
    Node *head;

public:
    LinkedList() {head = nullptr;}
    ~LinkedList();
    void append(int number);
    bool insert(int pos, int num);
    int getSize();
    bool search(int num);
    bool remove(int pos);
    void display();
};


#endif /* LINKEDLIST_H */