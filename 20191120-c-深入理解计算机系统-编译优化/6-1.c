#include <stdio.h>

int f(int a, int b, int c, int d, int n)
{
    int result = 0;
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
        {
            result += a * b + i * c * d + j;
        }
    }
    return result;
}

int f2(int a, int b, int c, int d, int n)
{
    int result = 0;
    for (int i = 0; i < n; i++)
    {
        result += n * (a * b + i * c * d) + n * (n - 1) / 2;
    }

    return result;
}

int main()
{
    printf("%d %d\n", f(1, 2, 3, 4, 5), f2(1, 2, 3, 4, 5));
    printf("%d %d\n", f(2, 3, 4, 5, 6), f2(2, 3, 4, 5, 6));
    printf("%d %d\n", f(6, 5, 4, 3, 2), f2(6, 5, 4, 3, 2));
    printf("%d %d\n", f(5, 4, 3, 2, 1), f2(5, 4, 3, 2, 1));
    return 0;
}