#include "LinkedList.h"

//Implement your linkedlist here.
int Node::getData()
{
    return data;
}
void Node::setData(int newData)
{
    data = newData;
}
Node *Node::getNext()
{
    return next;
}
void Node::setNext(Node *newNext)
{
    next = newNext;
}

LinkedList::~LinkedList()
{
    Node *p = head;
    while (p != nullptr)
    {
        Node *q = p;
        p = p->getNext();
        delete q;
    }
}

void LinkedList::append(int number)
{
    if (head == nullptr)
    {
        head = new Node();
        head->setData(number);
    }
    else
    {
        Node *n = new Node();
        n->setData(number);

        Node *p = head;
        while (p->getNext() != nullptr)
        {
            p = p->getNext();
        }
        p->setNext(n);
    }
}

bool LinkedList::insert(int pos, int num)
{
    if (pos < 0 || pos > getSize() - 1 || head == nullptr)
    {
        return false;
    }

    Node *n = new Node();
    n->setData(num);

    if (pos == 0)
    {
        n->setNext(head);
        head = n;
    }
    else
    {
        Node *p = head;
        int i = 0;

        while (p != nullptr && i < pos - 1)
        {
            i++;
            p = p->getNext();
        }

        n->setNext(p->getNext());
        p->setNext(n);
    }
    return true;
}

int LinkedList::getSize()
{
    int size = 0;
    Node *p = head;
    while (p != nullptr)
    {
        size++;
        p = p->getNext();
    }
    return size;
}

bool LinkedList::search(int num)
{
    Node *p = head;
    while (p != nullptr)
    {
        if (p->getData() == num)
        {
            return true;
        }
        p = p->getNext();
    }
    return false;
}

bool LinkedList::remove(int pos)
{
    if (pos < 0 || pos > getSize() - 1 || head == nullptr)
    {
        return false;
    }

    if (pos == 0)
    {
        Node *p = head;
        head = head->getNext();
        delete p;
    }
    else
    {
        Node *p = head, *q;
        int i = 0;
        while (p->getNext() != nullptr && i < pos - 1)
        {
            i++;
            p = p->getNext();
        }

        q = p->getNext();
        p->setNext(q->getNext());

        delete q;
    }

    return true;
}

void LinkedList::display()
{
    Node *p = head;
    while (p != nullptr)
    {
        cout << p->getData() << " -> ";
        p = p->getNext();
    }
    cout << "null" << endl;
}