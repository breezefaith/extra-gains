#include <stdio.h>
#include <stdlib.h>

/**
 * 将整个功能需求分解为一个个小的模块，通过声明和实现不同的函数来实现不同的模块。
 * 还使用了void指针，动态内存申请与释放等C语言特性。
 * @param cal_type 1: 计算对角元素之和, 2: 求最大值, 3: 求最小值
 * @param data_type 1: int, 2: float
 */
void set_up(int *cal_type_ptr, int *data_type_ptr, int *N_ptr);
void **create_matrix(int N, int data_type);
void input_matrix(int N, int data_type, void **m);
void free_matrix(int N, int data_type, void **m);
void *cal(int cal_type, int N, int data_type, void **m);
void *cal_dia_sum(int N, int data_type, void **m);
void *cal_max(int N, int data_type, void **m);
void display(void *res, int data_type);

//新增功能
void *cal_all_sum(int N, int data_type, void **m);
void *cal_min(int N, int data_type, void **m);

int main()
{
    int cal_type = 1, data_type = 1;
    int N = 1;
    void **m = NULL;
    void *res = NULL;

    set_up(&cal_type, &data_type, &N);
    m = create_matrix(N, data_type);
    input_matrix(N, data_type, m);
    res = cal(cal_type, N, data_type, m);
    display(res, data_type);

    free_matrix(N, data_type, m);
    free(res);

    return 0;
}

void set_up(int *cal_type_ptr, int *data_type_ptr, int *N_ptr)
{
    printf("请指定本程序功能:\n");
    printf("1. 计算方块矩阵对角线元素之和\n");
    printf("2. 计算方块矩阵所有元素的最大值\n");
    printf("3. 计算方块矩阵所有元素之和\n");
    printf("4. 计算方块矩阵所有元素的最小值\n");
    printf("请选择: ");
    scanf("%d", cal_type_ptr);

    printf("请输入矩阵大小N(1 <= N <= 5): ");
    scanf("%d", N_ptr);

    printf("请指定矩阵的数据类型:\n");
    printf("1. int\n");
    printf("2. float\n");
    printf("请选择: ");
    scanf("%d", data_type_ptr);

    return;
}

void **create_matrix(int N, int data_type)
{
    void **m = NULL;
    int **im = NULL;
    float **fm = NULL;
    int i = 0, j = 0;
    if (data_type == 1)
    {
        im = (int **)malloc(N * sizeof(int));
        for (i = 0; i < N; i++)
        {
            im[i] = (int *)malloc(N * sizeof(int));
            for (j = 0; j < N; j++)
            {
                im[i][j] = 1;
            }
        }
        m = (void **)im;
    }
    else if (data_type == 2)
    {
        fm = (float **)malloc(N * sizeof(float));
        for (i = 0; i < N; i++)
        {
            fm[i] = (float *)malloc(N * sizeof(float));
            for (j = 0; j < N; j++)
            {
                fm[i][j] = 1.0f;
            }
        }
        m = (void **)fm;
    }
    return m;
}

void input_matrix(int N, int data_type, void **m)
{
    int i = 0, j = 0;
    int **im = NULL;
    float **fm = NULL;
    printf("请输入各元素:\n");
    if (data_type == 1)
    {
        im = (int **)m;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                scanf("%d", &im[i][j]);
            }
        }
    }
    else if (data_type == 2)
    {
        fm = (float **)m;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                scanf("%f", &(((float *)m[i])[j]));
            }
        }
    }
}

void display_matrix(int N, int data_type, void **m)
{
    int i = 0, j = 0;
    int **im = NULL;
    float **fm = NULL;
    if (data_type == 1)
    {
        im = (int **)m;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                printf("%d ", im[i][j]);
            }
            printf("\n");
        }
    }
    else if (data_type == 2)
    {
        fm = (float **)m;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                printf("%f ", fm[i][j]);
            }
            printf("\n");
        }
    }
}

void free_matrix(int N, int data_type, void **m)
{
    int i = 0, j = 0;
    int **im = NULL;
    float **fm = NULL;
    if (m == NULL)
    {
        return;
    }

    for (i = 0; i < N; i++)
    {
        free((void *)m[i]);
    }
    free((void *)m);
}

void *cal(int cal_type, int N, int data_type, void **m)
{
    if (cal_type == 1)
    {
        return cal_dia_sum(N, data_type, m);
    }
    else if (cal_type == 2)
    {
        return cal_max(N, data_type, m);
    }
    else if (cal_type == 3)
    {
        return cal_all_sum(N, data_type, m);
    }
    else if (cal_type == 4)
    {
        return cal_min(N, data_type, m);
    }
    return NULL;
}

void *cal_dia_sum(int N, int data_type, void **m)
{
    void *res = NULL;
    int *ires = NULL;
    float *fres = NULL;

    int **im = NULL;
    float **fm = NULL;
    int i = 0, j = 0;
    if (data_type == 1)
    {
        im = (int **)m;
        ires = (int *)malloc(sizeof(int));
        *ires = 0;
        for (i = 0; i < N; i++)
        {
            *ires = *ires + im[i][i];
        }
        res = (void *)ires;
    }
    else if (data_type == 2)
    {
        fm = (float **)m;
        fres = (float *)malloc(sizeof(float));
        *fres = 0;
        for (i = 0; i < N; i++)
        {
            *fres = *fres + fm[i][i];
        }
        res = (void *)fres;
    }
    return res;
}

void *cal_max(int N, int data_type, void **m)
{
    void *res = NULL;
    int *ires = NULL;
    float *fres = NULL;

    int **im = NULL;
    float **fm = NULL;
    int i = 0, j = 0;
    if (data_type == 1)
    {
        im = (int **)m;
        ires = (int *)malloc(sizeof(int));
        *ires = im[0][0];
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                if (*ires < im[i][j])
                {
                    *ires = im[i][j];
                }
            }
        }
        res = (void *)ires;
    }
    else if (data_type == 2)
    {
        fm = (float **)m;
        fres = (float *)malloc(sizeof(float));
        *fres = fm[0][0];
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                if (*fres < fm[i][j])
                {
                    *fres = fm[i][j];
                }
            }
        }
        res = (void *)fres;
    }
    return res;
}

void display(void *res, int data_type)
{
    int *ires = NULL;
    float *fres = NULL;
    printf("result = ");
    if (data_type == 1)
    {
        ires = (int *)res;
        printf("%d\n", *ires);
    }
    else if (data_type == 2)
    {
        fres = (float *)res;
        printf("%f\n", *fres);
    }
}

//新增功能
void *cal_all_sum(int N, int data_type, void **m)
{
    void *res = NULL;
    int *ires = NULL;
    float *fres = NULL;

    int **im = NULL;
    float **fm = NULL;
    int i = 0, j = 0;
    if (data_type == 1)
    {
        im = (int **)m;
        ires = (int *)malloc(sizeof(int));
        *ires = 0;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                *ires = *ires + im[i][j];
            }
        }
        res = (void *)ires;
    }
    else if (data_type == 2)
    {
        fm = (float **)m;
        fres = (float *)malloc(sizeof(float));
        *fres = 0;
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                *fres = *fres + fm[i][j];
            }
        }
        res = (void *)fres;
    }
    return res;
}

void *cal_min(int N, int data_type, void **m)
{
    void *res = NULL;
    int *ires = NULL;
    float *fres = NULL;

    int **im = NULL;
    float **fm = NULL;
    int i = 0, j = 0;
    if (data_type == 1)
    {
        im = (int **)m;
        ires = (int *)malloc(sizeof(int));
        *ires = im[0][0];
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                if (*ires > im[i][j])
                {
                    *ires = im[i][j];
                }
            }
        }
        res = (void *)ires;
    }
    else if (data_type == 2)
    {
        fm = (float **)m;
        fres = (float *)malloc(sizeof(float));
        *fres = fm[0][0];
        for (i = 0; i < N; i++)
        {
            for (j = 0; j < N; j++)
            {
                if (*fres > fm[i][j])
                {
                    *fres = fm[i][j];
                }
            }
        }
        res = (void *)fres;
    }
    return res;
}