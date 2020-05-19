#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

/**最大正整数位数 */
const int MAX_BIT_LEN = 20;
/**字符串最大长度 */
const int MAX_STR_LEN = 200;
/**栈最大个数 */
const int MAX_STACK_SIZE = 200;
/**字符串转数字 */
int string2num(const char *s);
/**数字转字符串 */
char *num2string(char *s, int num);
/**删除中缀表达式空格 */
bool removeSpace(char *infix);
/**中缀表达式转后缀表达式 */
bool infix2postfix(char *infix, char *postfix);
/**计算后缀表达式 */
bool calPosfixExpression(char *postfix, int &result);

void solution();

struct Node
{
    char *item;
    struct Node *next;

    Node()
    {
        item = NULL;
        next = NULL;
    }
    Node(char *it)
    {
        item = it;
        next = NULL;
    }
};
typedef struct Node Node;

/**链式栈 */
class LinkedStack
{
private:
    Node *head;
    int maxSize;
    int size;

public:
    LinkedStack()
    {
        head = NULL;
        size = maxSize = 0;
    }
    LinkedStack(int m)
    {
        maxSize = m;
        size = 0;
        head = NULL;
    }

    bool pop(char *s)
    {
        if (size <= 0)
        {
            return false;
        }
        strcpy(s, head->item);
        Node *tmp = head->next;
        delete head;
        head = tmp;
        size--;
        return true;
    }

    bool top(char *s)
    {
        if (size <= 0)
        {
            return false;
        }
        strcpy(s, head->item);
        return s;
    }

    bool push(char *s)
    {
        if (size >= maxSize)
        {
            return false;
        }
        Node *node = new Node(s);
        node->next = head;
        head = node;
        size++;
        return true;
    }

    int getSize()
    {
        return size;
    }
};

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
    // cout << string2num("1234") << endl;
    // char infix[MAX_STR_LEN] = "a*(b-(c+d)                )";
    // char infix[MAX_STR_LEN] = "1*(2-(3+4))";
    // char infix[MAX_STR_LEN] = "1+2-   3+4           -5+6";
    // char infix[MAX_STR_LEN] = "23+(34*45)/(5+6+7)";
    char infix[MAX_STR_LEN] = "\0";
    char postfix[MAX_STR_LEN] = "\0";
    cout << "input infix expression:" << endl;
    cin >> infix;
    cout << "origin infix expression:" << endl;
    cout << infix << endl;
    removeSpace(infix);
    cout << "after remove spaces:" << endl;
    cout << infix << endl;
    if (infix2postfix(infix, postfix))
    {
        cout << "the postfix exporession:" << endl;
        cout << postfix << endl;
        int res = 0;
        if (calPosfixExpression(postfix, res))
        {
            cout << "the result:" << endl;
            cout << res << endl;
        }
    }
}

/**字符串转数字 */
int string2num(const char *s)
{
    int res = 0;
    int index = 1;
    for (int i = strlen(s) - 1; i >= 0; i--)
    {
        if (i == 0 && s[i] == '-')
        {
            res = res * (-1);
            break;
        }
        if (i == 0 && s[i] == '+')
        {
            break;
        }
        //出现非法字符则直接返回0
        if (s[i] < '0' || s[i] > '9')
        {
            return 0;
        }
        res += (s[i] - '0') * index;
        index *= 10;
    }
    return res;
}

/**数字转字符串 */
char *num2string(char *s, int num)
{
    int org_num = num;
    int i = 0;
    if (num == 0)
    {
        s[0] = '0';
        s[1] = '\0';
        return s;
    }
    if (num < 0)
    {
        num = -1 * num;
    }
    //1234 得到 "4321"
    while (num != 0)
    {
        s[i++] = (num % 10) + '0';
        num /= 10;
    }
    //若num<0加上符号
    if (org_num < 0)
    {
        s[i++] = '-';
    }
    s[i] = '\0';
    //字符串逆置
    int len = strlen(s);
    for (int i = 0; i < len / 2; i++)
    {
        char tmp = s[i];
        s[i] = s[len - 1 - i];
        s[len - 1 - i] = tmp;
    }
    return s;
}

/**删除中缀表达式中空格 */
bool removeSpace(char *infix)
{
    int i = 0;
    while (infix[i] != '\0')
    {
        if (infix[i] == ' ')
        {
            int j = i;
            for (; j < strlen(infix) - 1; j++)
            {
                infix[j] = infix[j + 1];
            }
            infix[j] = '\0';
            continue;
        }
        i++;
    }
    return true;
}

/**中缀表达式转后缀表达式 */
bool infix2postfix(char *infix, char *postfix)
{
    LinkedStack *symbolStack = new LinkedStack(MAX_STACK_SIZE);
    int i = 0;
    int j = 0;
    char tmp[MAX_BIT_LEN] = "\0";
    while (infix[i] != '\0')
    {
        if (infix[i] == ' ')
        {
            i++;
            continue;
        }
        if (i == 0 && (infix[i] == '+' || infix[i] == '-' || infix[i] == '*' || infix[i] == '/'))
        {
            cout << "expression format error!" << endl;
            strcpy(postfix, "0");
            return false;
        }
        if (infix[i + 1] == '\0' && (infix[i] == '+' || infix[i] == '-' || infix[i] == '*' || infix[i] == '/'))
        {
            cout << "expression format error!" << endl;
            strcpy(postfix, "0");
            return false;
        }
        switch (infix[i])
        {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
            postfix[j++] = infix[i];
            break;
        case '+':
            //
            //如果第一个字符即为该符号记为格式错误,如果当前字符的上一个字符也是+-*/记为格式错误
            if (i == 0 || (i > 0 && (infix[i - 1] == '+' || infix[i - 1] == '-' || infix[i - 1] == '*' || infix[i - 1] == '/')))
            {
                cout << "expression format error!" << endl;
                strcpy(postfix, "0");
                return false;
            }
            while (symbolStack->top(tmp) && (tmp[0] != '('))
            {
                symbolStack->pop(tmp);
                postfix[j++] = ' ';
                postfix[j++] = tmp[0];
            }
            postfix[j++] = ' ';
            symbolStack->push("+");
            break;
        case '-':
            //如果第一个字符即为该符号记为格式错误,如果当前字符的上一个字符也是+-*/记为格式错误
            if (i == 0 || (i > 0 && (infix[i - 1] == '+' || infix[i - 1] == '-' || infix[i - 1] == '*' || infix[i - 1] == '/')))
            {
                cout << "expression format error!" << endl;
                strcpy(postfix, "0");
                return false;
            }
            while (symbolStack->top(tmp) && (tmp[0] != '('))
            {
                symbolStack->pop(tmp);
                postfix[j++] = ' ';
                postfix[j++] = tmp[0];
            }
            postfix[j++] = ' ';
            symbolStack->push("-");
            break;
        case '*':
            //如果第一个字符即为该符号记为格式错误,如果当前字符的上一个字符也是+-*/记为格式错误
            if (i == 0 || (i > 0 && (infix[i - 1] == '+' || infix[i - 1] == '-' || infix[i - 1] == '*' || infix[i - 1] == '/')))
            {
                cout << "expression format error!" << endl;
                strcpy(postfix, "0");
                return false;
            }
            while (symbolStack->top(tmp) && (tmp[0] == '*' || tmp[0] == '/'))
            {
                symbolStack->pop(tmp);
                postfix[j++] = ' ';
                postfix[j++] = tmp[0];
            }
            postfix[j++] = ' ';
            symbolStack->push("*");
            break;
        case '/':
            //如果第一个字符即为该符号记为格式错误,如果当前字符的上一个字符也是+-*/记为格式错误
            if (i == 0 || (i > 0 && (infix[i - 1] == '+' || infix[i - 1] == '-' || infix[i - 1] == '*' || infix[i - 1] == '/')))
            {
                cout << "expression format error!" << endl;
                strcpy(postfix, "0");
                return false;
            }
            while (symbolStack->top(tmp) && (tmp[0] == '*' || tmp[0] == '/'))
            {
                symbolStack->pop(tmp);
                postfix[j++] = ' ';
                postfix[j++] = tmp[0];
            }
            postfix[j++] = ' ';
            symbolStack->push("/");
            break;
        case '(':
            symbolStack->push("(");
            break;
        case ')':
            while (symbolStack->top(tmp) && (tmp[0] != '('))
            {
                symbolStack->pop(tmp);
                postfix[j++] = ' ';
                postfix[j++] = tmp[0];
            }
            if (symbolStack->pop(tmp) == false)
            {
                cout << "expression format error!" << endl;
                strcpy(postfix, "0");
                return false;
            }
            break;
        default:
            if ((infix[i] <= 'Z' && infix[i] >= 'A') || (infix[i] <= 'z' && infix[i] >= 'a'))
            {
                postfix[j++] = infix[i];
            }
            else
            {
                cout << "illegal character" << endl;
                strcpy(postfix, "0");
                return false;
            }

            break;
        }
        i++;
    }
    while (symbolStack->top(tmp))
    {
        symbolStack->pop(tmp);
        postfix[j++] = ' ';
        if (tmp[0] == '(')
        {
            cout << "expression format error!" << endl;
            strcpy(postfix, "0");
            return false;
        }
        postfix[j++] = tmp[0];
    }
    return true;
}

/**
 * 计算后缀表达式
 * 例： 23 34 45 * 5 6 + 7 + / +
 * 结果：result = 108
 * 返回值: ture
 */
bool calPosfixExpression(char *postfix, int &result)
{
    LinkedStack *numberStack = new LinkedStack(MAX_STACK_SIZE);
    int i = 0;
    int j = 0;
    int res = 0;
    char nstr[MAX_BIT_LEN] = "\0";
    int tmp = 0;
    int num1 = 0, num2 = 0;
    char *s = NULL;

    //逐字符读取后缀表达式
    while (postfix[i] != '\0')
    {
        //若非空格则持续读取
        if (postfix[i] != ' ')
        {
            if (postfix[i] != '+' && postfix[i] != '-' && postfix[i] != '*' && postfix[i] != '/' && (postfix[i] < '0' || postfix[i] > '9'))
            {
                cout << "illegal character" << endl;
                result = 0;
                return false;
            }
            nstr[j++] = postfix[i];
            //最后一个字符后没有空格，因此增加特例
            if (postfix[i + 1] != '\0')
            {
                i++;
                continue;
            }
        }

        nstr[j] = '\0';
        j = 0;

        if (strcmp(nstr, "+") == 0)
        {
            if (numberStack->getSize() < 2)
            {
                cout << "expression format error!" << endl;
                return false;
            }
            numberStack->pop(nstr);
            num2 = string2num(nstr);
            numberStack->pop(nstr);
            num1 = string2num(nstr);
            num2string(nstr, num1 + num2);
            s = (char *)malloc(sizeof(char) * strlen(nstr) + 1);
            strcpy(s, nstr);
            numberStack->push(s);
        }
        else if (strcmp(nstr, "-") == 0)
        {
            //除法应注意先出栈的是减数，后出栈的是被减数
            if (numberStack->getSize() < 2)
            {
                cout << "expression format error!" << endl;
                return false;
            }
            numberStack->pop(nstr);
            num2 = string2num(nstr);
            numberStack->pop(nstr);
            num1 = string2num(nstr);
            num2string(nstr, num1 - num2);
            s = (char *)malloc(sizeof(char) * strlen(nstr) + 1);
            strcpy(s, nstr);
            numberStack->push(s);
        }
        else if (strcmp(nstr, "*") == 0)
        {
            if (numberStack->getSize() < 2)
            {
                cout << "expression format error!" << endl;
                result = 0;
                return false;
            }
            numberStack->pop(nstr);
            num2 = string2num(nstr);
            numberStack->pop(nstr);
            num1 = string2num(nstr);
            num2string(nstr, num1 * num2);
            s = (char *)malloc(sizeof(char) * strlen(nstr) + 1);
            strcpy(s, nstr);
            numberStack->push(s);
        }
        else if (strcmp(nstr, "/") == 0)
        {
            //除法应注意先出栈的是除数，后出栈的是被除数，且除数不能为0
            if (numberStack->getSize() < 2)
            {
                cout << "expression format error!" << endl;
                return false;
            }
            numberStack->pop(nstr);
            num2 = string2num(nstr);
            numberStack->pop(nstr);
            num1 = string2num(nstr);
            if (num2 == 0)
            {
                cout << "divid zero!" << endl;
                result = 0;
                return false;
            }
            num2string(nstr, num1 / num2);
            s = (char *)malloc(sizeof(char) * strlen(nstr) + 1);
            strcpy(s, nstr);
            numberStack->push(s);
        }
        else
        {
            s = (char *)malloc(sizeof(char) * strlen(nstr) + 1);
            strcpy(s, nstr);
            numberStack->push(s);
            strcpy(nstr, "\0");
        }

        i++;
    }
    //后缀表达式字符串读取完成后，栈中应有且仅有1个元素
    if (numberStack->getSize() == 1 && numberStack->pop(nstr))
    {
        result = string2num(nstr);
        return true;
    }
    else
    {
        cout << "expression format error!" << endl;
        result = 0;
        return false;
    }
}