#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 20

typedef int KeyType;

typedef struct
{
    KeyType key;
} RedType;

typedef struct
{
    RedType r[MAXSIZE + 1];
    int length;
} SqList;

void CreateList(SqList &L); /*待排序列的创建，由裁判实现，细节不表 */

int BinarySearch(SqList L, KeyType key, int &n);
/* 对有序顺序表 L 进行二分查找关键值 key，函数值返回查找成功时元素的位置，0表示查找失败；
       参数 n 返回查找过程中比较次数。 */

int main()
{
    SqList L;
    KeyType x;
    int p, n = 0;

    CreateList(L);
    scanf("%d", &x);
    p = BinarySearch(L, x, n);
    printf("%d %d\n", p, n);

    return 0;
}
/* 请在这里填写答案 */
void CreateList(SqList &L)
{
    scanf("%d", &L.length);
    for (int i = 0; i < L.length; i++)
    {
        scanf("%d", &L.r[i]);
    }
}

int BinarySearch(SqList L, KeyType key, int &n)
{
    int left = 0, right = L.length - 1;
    int mid = (right + left) / 2;
    while (right >= left)
    {
        n++;
        if (key == L.r[mid].key)
        {
            return mid + 1;
        }
        else if (key > L.r[mid].key)
        {
            left = mid + 1;
        }
        else
        {
            right = mid - 1;
        }
        mid = (right + left) / 2;
    }
    return 0;
}
/* 对有序顺序表 L 进行二分查找关键值 key，函数值返回查找成功时元素key所在位置，0 表示查找失败；
       参数 n 返回查找过程中比较次数。 */
