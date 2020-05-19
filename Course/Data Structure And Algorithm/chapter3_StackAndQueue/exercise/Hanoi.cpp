#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

void solution();

void hanoi(int n, char src, char tar, char ass);
void move(char src, char tar);

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
    int n = 10;
    char src = 'A';
    char tar = 'B';
    char ass = 'C';
    hanoi(n, src, ass, tar);
}

void hanoi(int n, char src, char tar, char ass)
{
    if (n == 0)
    {
        return;
    }
    hanoi(n - 1, src, ass, tar);
    move(src, tar);
    hanoi(n - 1, ass, tar, src);
}

void move(char src, char tar)
{
    cout << src << " -> " << tar << endl;
}