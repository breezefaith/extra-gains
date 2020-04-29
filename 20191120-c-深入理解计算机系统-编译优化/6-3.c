#include <stdio.h>
#include <stdlib.h>
#include <time.h>

float *createArray(int size)
{
    float *a = (float *)malloc(sizeof(float) * size);
    for (int i = 0; i < size; i++)
    {
        float r = rand() / (float)RAND_MAX;
        a[i] = r < 0.5f ? 0.0f : r + 0.26f;
    }
    return a;
}

float f(float *a, int n)
{
    float prod = 1.0f;
    for (int i = 0; i < n; ++i)
    {
        if (a[i] != 0.0f)
        {
            prod *= a[i];
        }
    }
    return prod;
}

float g(float *a, int n)
{
    float prod = 1.0f;
    for (int i = 0; i < n; ++i)
    {
        prod *= a[i];
    }
    return prod;
}

int main()
{
    int size = 10000;
    float *a = NULL;
    float res = 0.0f;
    clock_t start, finish;

    a = createArray(size);

    start = clock();
    res = f(a, size);
    finish = clock();
    printf("f(a) = %f, time: %d ms\n", res, (int)(finish - start));

    float *b = NULL;

    b = (float *)malloc(sizeof(float) * size);
    for (int i = 0; i < size; i++)
    {
        if (a[i] != 0.0f)
        {
            b[i] = a[i];
        }
        else
        {
            b[i] = 1.0f;
        }
    }

    start = clock();
    res = g(b, size);
    finish = clock();
    printf("g(b) = %f, time: %d ms\n", res, (int)(finish - start));
    // the time token to run g(b) is faster than calling f(a), for which the lines
    // in function g is less than f, and there is no time loss for non-zero
    // detection.

    float *c = NULL;
    int c_size = 0;
    c = (float *)malloc(sizeof(float) * size);
    for (int i = 0; i < size; i++)
    {
        if (a[i] != 0.0f)
        {
            c[c_size++] = a[i];
        }
    }

    start = clock();
    res = g(c, c_size);
    finish = clock();
    printf("g(c) = %f, time: %d ms\n", res, (int)(finish - start));
    // the time token to run g(c) is faster than calling f(a) or g(b), for which
    // the lines in function g is less than f, there is no time loss for non-zero
    // detection, and there are less loop times when calling g(c).

    free(a);
    free(b);
    free(c);

    return 0;
}