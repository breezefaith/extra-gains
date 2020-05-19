#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

#include <stack>

using namespace std;

void solution();
int cal(char sym, int a, int b);

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
    return 0;
}

void solution()
{
    char *exp = new char[200];
    cin >> exp;

    stack<int> numStack;
    stack<char> symStack;

    char *p = exp;

    while (*p != '\0')
    {
        if (*p == '(' || *p == '+' || *p == '-' || *p == '/' || *p == '*')
        {
            symStack.push(*p);
        }

        if (*p >= '0' && *p <= '9')
        {
            numStack.push((*p) - '0');
            cout << numStack.top() << endl;
        }

        if (*p == ')')
        {
            //符号栈出栈，直至遇到左括号(
            char symbol = 0;
            while ((symbol = symStack.top()) != '(')
            {
                //一个符号出栈，两个数字出栈，计算后入数字栈
                symStack.pop();

                int b = numStack.top();
                numStack.pop();

                int a = numStack.top();
                numStack.pop();

                numStack.push(cal(symbol, a, b));
                cout << numStack.top() << endl;
            }

            //左括号出栈
            symStack.pop();
        }

        p++;
    }

    //此时符号栈已去除所有括号
    while (symStack.empty() == false)
    {
        //一个符号出栈，两个数字出栈，计算后入数字栈
        char symbol = symStack.top();
        symStack.pop();

        int b = numStack.top();
        numStack.pop();

        int a = numStack.top();
        numStack.pop();

        numStack.push(cal(symbol, a, b));
        cout << numStack.top() << endl;
    }

    //数字栈中仅剩一个元素
    cout << numStack.top() << endl;
    system("pause");
}

int cal(char sym, int a, int b)
{
    switch (sym)
    {
    case '+':
        return a + b;
    case '-':
        return a - b;
    case '*':
        return a * b;
    case '/':
        return a / b;
    default:
        return 0;
    }
}