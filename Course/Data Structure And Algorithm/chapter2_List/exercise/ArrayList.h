#ifndef ARRAY_LIST_H
#define ARRAY_LIST_H

#include "List.h"

const int DEFAULT_MAX_LEN = 10;

template <class T>
class ArrayList : public List<T>
{
private:
    T *arr;
    int maxLen;
    int len;
    int curPos;
public:
    ArrayList();
    ArrayList(const int size);
    ~ArrayList();

    int length();
    void clear();
    bool isEmpty();
    bool append(const T value);
    bool insert(const int p, const T value);
    bool remove(const int p);
    bool getValue(const int p, T &value);
    bool setValue(const int p, const T value);
    bool search(int& p, const T value);
};

template <class T>
ArrayList<T>::ArrayList()
{
    maxLen = DEFAULT_MAX_LEN;
    arr = new T[maxLen];
    len = 0;
    curPos = 0;
}

template <class T>
ArrayList<T>::ArrayList(const int size)
{
    maxLen = size;
    arr = new T[maxLen];
    len = 0;
    curPos = 0;
}

template <class T>
ArrayList<T>::~ArrayList()
{
    delete[] arr;
}
#endif