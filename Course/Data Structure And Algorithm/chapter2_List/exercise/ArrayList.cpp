#include "ArrayList.h"

template <class T>
int ArrayList<T>::length()
{
    return len;
}

template <class T>
void ArrayList<T>::clear()
{
    delete[] arr;
    arr = new T[maxLen];
    len = 0;
    curPos = 0;
}

template <class T>
bool ArrayList<T>::append(const T value)
{
    if (len >= maxLen)
    {
        std::cout << "List is full." << std::endl;
        return false;
    }

    arr[len++] = value;
    return true;
}

template <class T>
bool ArrayList<T>::insert(const int p, const T value)
{
    if (len >= maxLen)
    {
        std::cout << "List is full." << std::endl;
        return false;
    }
    if (p < 0 || p > len)
    {
        std::cout << "P is overflow." << std::endl;
        return false;
    }
    for (int i = len - 1; i > p; i--)
    {
        arr[i] = arr[i - 1];
    }
    arr[p] = value;
    len++;
    return true;
}

template <class T>
bool ArrayList<T>::remove(const int p)
{
    if (len <= 0)
    {
        std::cout << "List is empty." << std::endl;
        return false;
    }
    if (p < 0 || p >= len)
    {
        std::cout << "P is overflow." << std::endl;
        return false;
    }
    for (int i = p; i < len - 1; i++)
    {
        arr[i] = arr[i + 1];
    }
    len--;
    return true;
}

template <class T>
bool setValue(const int p, const T value)
{
    if (p < 0 || p >= len)
    {
        std::cout << "P is overflow." << std::endl;
        return false;
    }
    arr[p] = value;
    return true;
}

template <class T>
bool getValue(const int p, T &value)
{
    if (p < 0 || p > len)
    {
        std::cout << "P is overflow." << std::endl;
        return false;
    }
    value = arr[p];
    return true;
}

template <class T>
bool ArrayList<T>::search(int &p, const T value)
{
    p = -1;
    if (len <= 0)
    {
        std::cout << "List is empty." << std::endl;
        return false;
    }
    for (int i = 0; i < len; i++)
    {
        if (arr[i] == value)
        {
            p = i;
            return true;
        }
    }
    return false;
}