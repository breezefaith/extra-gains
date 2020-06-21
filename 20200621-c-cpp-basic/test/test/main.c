#include <stdio.h>
#include <stdlib.h>

void set_up(int* cal_type_ptr, int* data_type_ptr, int* N_ptr);
/**
 * @param data_type 1: int, 2: float
 */
void** create_matrix(int N, int data_type);
void input_matrix(int N, int data_type, void** matrix);
void free_matrix(int N, void** matrix);
void* cal(int cal_type, int N, int data_type, void** matrix);
void* cal_dia_sum(int N, int data_type, void** matrix);
void* cal_max(int N, int data_type, void** matrix);
void display(void* res, int data_type);

int main()
{
    int cal_type = 1, data_type = 1;
    int N = 1;
    void** matrix = NULL;
    void* res = NULL;

    set_up(&cal_type, &data_type, &N);
    // matrix = create_matrix(N, data_type);
    // input_matrix(N, data_type, matrix);
    // res = cal(cal_type, N, data_type, matrix);
    // display(res, data_type);

    free(matrix);
    free(res);

    return 0;
}

void set_up(int* cal_type_ptr, int* data_type_ptr, int* N_ptr)
{
    printf("请指定本程序功能:\n");
    printf("1. 计算方块矩阵对角线元素之和\n");
    printf("2. 计算方块矩阵所有元素的最大值\n");
    printf("请选择: ");
    scanf("%d", cal_type_ptr);
    if (*cal_type_ptr < 1 || *cal_type_ptr > 2)
    {
        *cal_type_ptr = 1;
    }

    printf("请输入矩阵大小N(1 <= N <= 5):\n");
    scanf("%d", N_ptr);
    if (*N_ptr < 1 || *N_ptr > 5)
    {
        *N_ptr = 1;
    }

    printf("请指定矩阵的数据类型:\n");
    printf("1. int\n");
    printf("2. float\n");
    scanf("%d", data_type_ptr);
    if (*data_type_ptr < 1 || *data_type_ptr > 2)
    {
        *data_type_ptr = 1;
    }

    return;
}

// void **create_matrix(int N, int data_type)
// {
//     void **m = NULL;
//     int i = 0, j = 0;
//     if (data_type == 1)
//     {
//         m = (int **)malloc(N * sizeof(int));
//         for (i = 0; i < N; i++)
//         {
//             m[i] = (int *)malloc(N * sizeof(int));
//             for (j = 0; j < N; j++)
//             {
//                 m[i][j] = (int *)malloc(sizeof(int));
//                 *(m[i][j]) = 1;
//             }
//         }
//     }
//     else if (data_type == 2)
//     {
//         m = (int **)malloc(N * sizeof(float));
//         for (i = 0; i < N; i++)
//         {
//             m[i] = (int *)malloc(N * sizeof(float));
//             for (j = 0; j < N; j++)
//             {
//                 m[i][j] = (float *)malloc(sizeof(float));
//                 *(m[i][j]) = 1.0f;
//             }
//         }
//     }
//     return m;
// }

// void input_matrix(int N, int data_type, void **matrix)
// {
//     int i = 0, j = 0;
//     if (data_type == 1)
//     {
//         for (i = 0; i < N; i++)
//         {
//             for (i = 0; i < N; i++)
//             {
//                 scanf("%d", matrix[i][j]);
//             }
//         }
//     }
//     else if (data_type == 2)
//     {
//         for (i = 0; i < N; i++)
//         {
//             for (i = 0; i < N; i++)
//             {
//                 scanf("%f", matrix[i][j]);
//             }
//         }
//     }
// }

// void free_matrix(int N, void **matrix)
// {
//     int i = 0, j = 0;
//     if (matrix == NULL)
//     {
//         return;
//     }

//     for (i = 0; i < N; i++)
//     {
//         for (j = 0; j < N; j++)
//         {
//             free(matrix[i][j]);
//         }
//         free(matrix[i]);
//     }

//     return;
// }

// void *cal(int cal_type, int N, int data_type, void **matrix)
// {
//     if (cal_type == 1)
//     {
//         return cal_dia_sum(N, data_type, matrix);
//     }
//     else if (cal_type == 2)
//     {
//         return cal_max(N, data_type, matrix);
//     }
//     return NULL;
// }

// void *cal_dia_sum(int N, int data_type, void **matrix)
// {
//     void *res = NULL;
//     int i = 0, j = 0;
//     if (data_type == 1)
//     {
//         res = (int *)malloc(sizeof(int));
//         for (i = 0; i < N; i++)
//         {
//             *res = *res + *((int *)matrix[i][i]);
//         }
//     }
//     else if (data_type == 2)
//     {
//         res = (float *)malloc(sizeof(float));
//         for (i = 0; i < N; i++)
//         {
//             *res = *res + *((float *)matrix[i][i]);
//         }
//     }
//     return res;
// }

// void *cal_max(int N, int data_type, void **matrix)
// {
//     void *res = NULL;
//     int i = 0, j = 0;
//     if (data_type == 1)
//     {
//         for (i = 0; i < N; i++)
//         {
//             for (i = 0; i < N; i++)
//             {
//                 if (*((int *)res) > *((int *)matrix[i][i]))
//                 {
//                     *res = *((int *)matrix[i][i]);
//                 }
//             }
//         }
//     }
//     else if (data_type == 2)
//     {
//         res = (float *)malloc(sizeof(float));
//         for (i = 0; i < N; i++)
//         {
//             for (i = 0; i < N; i++)
//             {
//                 if (*((float *)res) > *((float *)matrix[i][i]))
//                 {
//                     *res = *((float *)matrix[i][i]);
//                 }
//             }
//         }
//     }
// }

// void display(void *res, int data_type)
// {
//     printf("result = ");
//     if (data_type == 1)
//     {
//         printf("%d\n", *((int *)res));
//     }
//     else if (data_type == 2)
//     {
//         printf("%f\n", *((float *)res));
//     }
// }