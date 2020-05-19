#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

const int MAX_STR_LEN = 200;
const int MAX_STACK_SIZE = 200;

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
    LinkedStack *stack = new LinkedStack(MAX_STACK_SIZE);
    char str[MAX_STR_LEN];
    int i = 0;
    int j = 0;
    int first = -1, last = -1;
    cin >> str;

    while (str[i] != '\0')
    {
        if (str[i] == '<')
        {
            first = i;
            //如果当前字符是'<'，则去寻找最近的'>'
            j = first;
            while (str[j] != '\0')
            {
                if (str[j] == '>')
                {
                    last = j;
                    break;
                }
                j++;
            }
            //如果一直找到最后一个字符都没找到'>'则不合法
            if (str[j] == '\0')
            {
                cout << "False" << endl;
                return;
            }
            //如果找到的str[first..last]以"</"开头，则判断其与栈顶标签是否相同
            if (strncmp(&str[first], "</", last - first + 1) == 0)
            {
                char temp[MAX_STR_LEN] = "\0";
                if (stack->pop(temp) == true)
                {
                    //如果栈顶元素与闭合标签匹配则继续，否则不合法
                    if (strncmp(temp, &str[first + 2], last - first) == 0)
                    {
                        i = last;
                    }
                    else
                    {
                        cout << "False" << endl;
                        return;
                    }
                }
                else
                {
                    //如果栈中没有元素则不合法
                    cout << "False" << endl;
                    return;
                }
            }
            //如果找到的str[first..last]以"<"而非"</"开头，则将标签入栈
            else
            {
                char *s = (char *)malloc(sizeof(char) * (last - first + 2));
                memset(s, 0, sizeof(char) * (last - first + 2));
                strncpy(s, &str[first], last - first + 1);
                stack->push(s);
            }
        }
        else
        {
            //do nothing..
        }

        i++;
    }
    cout << "True" << endl;
    return;
}