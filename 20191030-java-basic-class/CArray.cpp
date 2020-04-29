#include <iostream>

using namespace std;

class CArray
{
private:
    int *arr;
    int max;
    int n;

public:
    CArray();
    CArray(const CArray &c);
    bool push_back(int num);
    void operator=(const CArray &c);
    int &operator[](int i);
    int length();
};

CArray::CArray()
{
    max = 5;
    n = 0;
    arr = new int[max];
}

CArray::CArray(const CArray &c)
{
    max = c.max;
    n = c.n;
    arr = new int[n];
    for (int i = 0; i < n; i++)
    {
        arr[i] = c.arr[i];
    }
}

bool CArray::push_back(int num)
{
    if (n == max)
    {
        return false;
    }
    arr[n++] = num;
    return true;
}

int CArray::length()
{
    return n;
}

void CArray::operator=(const CArray &c)
{
    max = c.max;
    n = c.n;
    arr = new int[n];
    for (int i = 0; i < n; i++)
    {
        arr[i] = c.arr[i];
    }
}

int &CArray::operator[](int i)
{
    return arr[i];
}

int main()
{
    CArray a;
    for (int i = 0; i < 5; ++i)
    {
        a.push_back(i);
    }

    CArray a2, a3;

    a2 = a;

    for (int i = 0; i < a.length(); ++i)
    {
        cout << a2[i] << " ";
    }

    a2 = a3;

    for (int i = 0; i < a2.length(); ++i)
    {
        cout << a2[i] << " ";
    }

    cout << endl;

    a[3] = 100;

    CArray a4(a);

    for (int i = 0; i < a4.length(); ++i)
    {
        cout << a4[i] << " ";
    }

    return 0;
}