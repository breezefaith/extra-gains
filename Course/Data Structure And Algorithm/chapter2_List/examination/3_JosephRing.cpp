#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

void solution();

typedef struct Node
{
    int number;
    struct Node *next;
    struct Node *prev;

    Node()
    {
        number = 0;
        prev = next = NULL;
    }

    Node(int n)
    {
        number = n;
        prev = next = NULL;
    }
} Node;

class MonkeyList
{
private:
    Node *head;
    Node *rear;
    int len;

    bool remove(int num)
    {
        Node *p = head;
        do
        {
            if (p->number == num)
            {
                p->prev->next = p->next;
                p->next->prev = p->prev;

                if (p == head)
                {
                    head = p->next;
                }
                if (p == rear)
                {
                    rear = p->prev;
                }
                delete p;
                len--;
                // cout << num << " has been deleted." << endl;
                return true;
            }
            p = p->next;
        } while (p != head);

        return false;
    }

public:
    MonkeyList()
    {
        len = 0;
        head = rear = NULL;
    }
    MonkeyList(int l)
    {
        len = l;
        if (len == 0)
        {
            head = rear = NULL;
            return;
        }
        head = new Node(1);
        rear = head;
        for (int i = 2; i <= len; i++)
        {
            Node *node = new Node(i);
            node->next = head;
            head->prev = node;
            rear->next = node;
            node->prev = rear;

            rear = rear->next;
        }
    }

    void countOff(int m)
    {
        Node *p = head;
        int i = 1;
        while (len > 1)
        {
            if (i == m)
            {
                Node *tmp = p->next;
                remove(p->number);
                p = tmp;
                i = 1;
                continue;
            }
            p = p->next;
            i++;
        }
        cout << head->number << endl;
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
    int m, n;
    cin >> n >> m;
    MonkeyList *list = new MonkeyList(n);
    list->countOff(m);
}