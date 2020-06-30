#include <stdio.h>
#include <stdlib.h>

#include "student.h"
#include "func.h"

void showMenus()
{
    printf("1. 信息录入\n");
    printf("2. 信息显示\n");
    printf("3. 信息查询\n");
    printf("4. 添加学生\n");
    printf("5. 删除学生\n");
    printf("6. 统计\n");
    printf("0. 退出\n");
}

void execMenu(int menu)
{
}

void inputStudents()
{
    int n = 0;
    int i = 0;
    printf("请输入学生数量：");
    scanf("%d", &n);
    for (int i = 0; i < n; i++)
    {
        addStudent();
    }
}

void showStudents()
{
}

void addStudent()
{
}

void removeStudent()
{
}

void statistics()
{
}

void exitSystem()
{
    char ch = 'N';
    printf("确定要退出？y/N: ");
    scanf("%c", &ch);
    if (ch == 'y')
    {
        exit(0);
    }
}