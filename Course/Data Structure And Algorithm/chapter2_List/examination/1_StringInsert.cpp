#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

const int MAX_LEN = 10000;

void solution();

int main()
{
    int T = 0;
    int t = 0;

    T = 1;
    while (t < T)
    {
        solution();
        t++;
    }

    system("pause");
    return 0;
}

void solution()
{
    char str[MAX_LEN], substr[MAX_LEN];
    int maxPos = 0;
    int len1 = 0;
    int len2 = 0;
    cin >> str >> substr;
    len1 = strlen(str);
    len2 = strlen(substr);
    for (int i = 1; i < len1; i++)
    {
        if (str[i] > str[maxPos])
        {
            maxPos = i;
        }
    }

    for (int i = len1; i >= maxPos + 1; i--)
    {
        str[i + len2] = str[i];
    }
    for (int i = 0; i < len2; i++)
    {
        str[i + maxPos + 1] = substr[i];
    }

    cout << str << endl;
}