#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void inner(float *u, float *v, int length, float *dest)
{
    int i;
    float sum = 0.0f;
    for (i = 0; i < length; i++)
    {
        sum += u[i] * v[i];
    }

    *dest = sum;
}

void inner2(float *u, float *v, int length, float *dest)
{
    int i;
    float sum = 0.0f;
    for (i = 0; i < length; i = i + 4)
    {
        sum += u[i] * v[i] + u[i + 1] * v[i + 1] + u[i + 2] * v[i + 2] +
               u[i + 3] * v[i + 3];
    }

    for (; i < length; i++)
    {
        sum += u[i] * v[i];
    }

    *dest = sum;
}

int main()
{
    int length = 0;
    int i = 0;
    float dest = 0.0f;
    float *u = NULL, *v = NULL;
    clock_t start, finish;

    printf("Please enter the length of u and v: ");
    while (scanf("%d", &length) != EOF)
    {
        u = (float *)malloc(sizeof(float) * length);
        v = (float *)malloc(sizeof(float) * length);

        srand((unsigned)time(NULL));
        for (i = 0; i < length; i++)
        {
            u[i] = (float)(rand() % 10) / 9;
            v[i] = (float)(rand() % 10) / 9;
        }

        start = clock();
        inner(u, v, length, &dest);
        finish = clock();
        printf("dest = %f, time: %d ms\n", dest, (int)(finish - start));

        start = clock();
        inner2(u, v, length, &dest);
        finish = clock();
        printf("dest = %f, time: %d ms\n", dest, (int)(finish - start));

        free(u);
        free(v);

        printf("Please enter the length of u and v: ");
    }

    return 0;
}
