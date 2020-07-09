#include <iostream>
#include <cstring>
#include <string>
#include <cstdlib>
using namespace std;

class Student
{
protected:
    string name;
    string num;

public:
    Student(string n, string n1)
    {
        name = n;
        num = n1;
    }

    string get_name()
    {
        return name;
    }

    string get_num()
    {
        return num;
    }

    virtual void display()
    {
        cout << "姓名为" << name << " "
             << "学号为" << num << " ";
    }
};

class HSStudent : public Student
{
protected:
    int score[7];

public:
    HSStudent(string n, string n1, int a[7]) : Student(n, n1)
    {
        for (int i = 0; i < 6; i++)
        {
            score[i] = a[i];
        }
    }

    void display()
    {
        int i, j;
        int c[7];
        int k = 0;
        memset(c, 0, sizeof(c));
        int b[7];

        cout << "的成绩为 ";
        for (i = 0; i < 6; i++)
        {
            b[i] = score[i];
        }
        for (i = 0; i < 6; i++)
        {
            for (int j = 0; j < 6 - i - 1; j++)
            {
                if (score[j] < score[j + 1])
                {
                    int f = score[j];
                    score[j] = score[j + 1];
                    score[j + 1] = f;
                }
            }
        }
        for (i = 0; i < 6; i++)
        {

            for (j = 0; j < 6; j++)
            {
                if (score[i] == b[j] && c[j] == 0)
                {
                    if (j == 0)
                    {
                        cout << "数学" << score[i] << " ";
                    }
                    else if (j == 1)
                    {
                        cout << "语文" << score[i] << " ";
                    }
                    else if (j == 2)
                    {
                        cout << "英语" << score[i] << " ";
                    }
                    else if (j == 3)
                    {
                        cout << "生物" << score[i] << " ";
                    }
                    else if (j == 4)
                    {
                        cout << "物理" << score[i] << " ";
                    }
                    else
                    {
                        cout << "化学" << score[i] << " ";
                    }
                    c[j] = 1;
                }
            }
        }
    }

    int sum()
    {
        int sum = 0;
        for (int i = 0; i < 6; i++)
        {
            sum += score[i];
        }
        return sum;
    }

    double average()
    {
        int sum = 0;
        for (int i = 0; i < 6; i++)
        {
            sum += score[i];
        }
        return sum / 6.0;
    }
};

int main()
{
    cout << "请输入姓名，学号，以及数学、语文、英语、生物、物理、化学六门成绩" << endl;
    string name, no;
    int a[7];

    cin >> name >> no;
    for (int i = 0; i < 6; i++)
    {
        cin >> a[i];
    }

    Student s(name, no);
    HSStudent s1(name, no, a);
    Student *p = &s;
    p->display();
    p = &s1;
    p->display();
    cout << endl;
    cout << "输入您想要执行的功能:1:求总分 2：求平均分" << endl;
    int choice;
    cin >> choice;
    if (choice == 1)
    {
        cout << s1.sum() << endl;
    }
    else
    {
        cout << s1.average() << endl;
    }

    return 0;
}
