#ifndef LIST_H
#define LIST_H

template <class T>
class List
{
public:
    virtual void clear() = 0;
    virtual bool isEmpty() = 0;
    virtual bool append(const T value) = 0;
    virtual bool insert(const int p, const T value) = 0;
    virtual bool remove(const int p) = 0;
    virtual bool getValue(const int p, T &value) = 0;
    virtual bool setValue(const int p, const T value) = 0;
    virtual bool search(int &p, const T value) = 0;
};

#endif
