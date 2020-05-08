#include <iostream>
#include <cstdio>
#include <cstdlib>
#include <cstring>

using namespace std;

typedef struct Node
{
    char num;
    struct Node *next;

    Node()
    {
        num = 0;
        next = NULL;
    }
    Node(const char n)
    {
        num = n;
        next = NULL;
    }
} Node;

class BigInteger
{
private:
    Node *head;
    int len;
    void show(Node *p)
    {
        if (p == NULL)
        {
            return;
        }
        else
        {
            show(p->next);
            cout << p->num;
        }
    }

public:
    BigInteger()
    {
        head = new Node();
        len = 0;
    }

    BigInteger(char *str)
    {
        Node *p = NULL;
        head = new Node();
        p = head;
        for (int i = strlen(str) - 1; i >= 0; i--)
        {
            Node *node = new Node(str[i]);
            p->next = node;
            p = p->next;
            len++;
        }
    }

    BigInteger(const BigInteger &bi)
    {
        Node *p = NULL;
        head = new Node();
        p = head;
        Node *p2 = bi.head->next;
        while (p2 != NULL)
        {
            Node *node = new Node(p2->num);
            p->next = node;
            p = p->next;
            p2 = p2->next;
        }
        len = bi.len;
    }

    BigInteger(const BigInteger *bi)
    {
        Node *p = NULL;
        head = new Node();
        p = head;
        Node *p2 = bi->head->next;
        while (p2 != NULL)
        {
            Node *node = new Node(p2->num);
            p->next = node;
            p = p->next;
            p2 = p2->next;
        }
        len = bi->len;
    }

    bool prepend(char c)
    {
        Node *node = new Node(c);
        node->next = head->next;
        head->next = node;
        len++;
        return true;
    }

    bool append(char c)
    {
        Node *p = head;
        while (p->next != NULL)
        {
            p = p->next;
        }
        Node *node = new Node(c);
        p->next = node;
        len++;

        return true;
    }

    BigInteger *add(const BigInteger &bi1) const
    {
        int carry = 0;
        Node *p1 = head->next;
        Node *p2 = bi1.head->next;
        int bit = 0;
        BigInteger *res = new BigInteger();
        while (p1 != NULL && p2 != NULL)
        {
            bit = p1->num - '0' + p2->num - '0' + carry;
            carry = bit / 10;
            bit -= carry * 10;
            res->append(bit + '0');

            p1 = p1->next;
            p2 = p2->next;
        }

        if (p1 != NULL)
        {
            while (p1 != NULL)
            {
                bit = p1->num - '0' + carry;
                carry = bit / 10;
                bit -= carry * 10;
                res->append(bit + '0');
                p1 = p1->next;
            }
        }
        if (p2 != NULL)
        {
            while (p2 != NULL)
            {
                bit = p2->num - '0' + carry;
                carry = bit / 10;
                bit -= carry * 10;
                res->append(bit + '0');
                p2 = p2->next;
            }
        }

        if (carry > 0)
        {
            res->append(carry + '0');
        }

        return res;
    }

    BigInteger *multiply(char c) const
    {
        int fac = c - '0';
        int carry = 0;
        int temp = 0;
        BigInteger *res = new BigInteger();
        Node *p = head->next;
        while (p != NULL)
        {
            temp = (p->num - '0') * fac + carry;
            carry = temp / 10;
            temp -= carry * 10;

            res->append(temp + '0');
            p = p->next;
        }
        if (carry > 0)
        {
            res->append(carry + '0');
        }
        return res;
    }
    BigInteger *multiply(const BigInteger &bi1) const
    {
        BigInteger *res = new BigInteger("0");
        BigInteger *temp = new BigInteger(*this);
        Node *p1 = bi1.head->next;
        while (p1 != NULL)
        {
            BigInteger *tmp = temp->multiply(p1->num);
            res = res->add(*tmp);
            temp->prepend('0');
            p1 = p1->next;
        }
        return res;
    }

    void show()
    {
        show(head->next);
    }
};
const int MAX_LEN = 201;
void solution();

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
    char num1[MAX_LEN], num2[MAX_LEN];
    BigInteger *bi1 = NULL;
    BigInteger *bi2 = NULL;
    cin >> num1 >> num2;
    bi1 = new BigInteger(num1);
    bi2 = new BigInteger(num2);

    bi1->multiply(bi2)->show();
    cout << endl;
}