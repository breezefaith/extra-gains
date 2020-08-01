#include <iostream>
using namespace std;

#define STACKSIZE 10
typedef int DataType;
typedef struct
{
    DataType *base;
    DataType *top;
    int stacksize;
} SqStack;

//初始化空顺序栈
int InitStack(SqStack &S);

//判栈空
int StackEmpty(SqStack S);

//入栈
int Push(SqStack &S, DataType item);

//出栈
int Pop(SqStack &S, DataType &item);

void conversion(int N)
{ //对于任意一个非负十进制数，打印输出与其等值的八进制数
    SqStack S;
    InitStack(S); //初始化空栈S
    while (N)
    {                   //当N非零时,循环
        Push(S, N % 8); //把N与8求余得到的八进制数压入栈S
        N = N / 8;      //N更新为N与8的商
    }
    int e;
    cout << "转化的八进制数为：";
    while (!StackEmpty(S)) //当栈S非空时，循环
    {
        Pop(S, e); //弹出栈顶元素e
        cout << e; //输出e
    }
}
int main()
{
    int m, n;
    cout << "请输入十进制数:";
    cin >> m;
    conversion(m);
    return 0;
}

/* 请在这里填写答案 */
//初始化空顺序栈
int InitStack(SqStack &S)
{
    S.base = new DataType[STACKSIZE];
    S.top = NULL;
    S.stacksize = 0;
    return 1;
}
//判断栈空
int StackEmpty(SqStack S)
{
    return S.stacksize == 0 ? 1 : 0;
}
//入栈
int Push(SqStack &S, DataType item)
{
    if (S.stacksize < STACKSIZE)
    {
        S.base[S.stacksize] = item;
        S.top = &(S.base[S.stacksize]);
        S.stacksize++;
        return 1;
    }
    return 0;
}
//出栈
int Pop(SqStack &S, DataType &item)
{
    if (S.stacksize > 0)
    {
        item = *S.top;
        S.stacksize--;
        S.top = &(S.base[S.stacksize - 1]);
        return 1;
    }
    return 0;
}