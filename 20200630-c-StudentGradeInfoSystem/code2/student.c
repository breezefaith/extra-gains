#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#include "student.h"

void showMenus()
{
    printf("*****************************\n");
    printf("1. 信息录入\n");
    printf("2. 信息显示\n");
    printf("3. 信息查询\n");
    printf("4. 添加学生\n");
    printf("5. 删除学生\n");
    printf("6. 统计\n");
    printf("0. 退出\n");
    printf("*****************************\n");
}

void execMenu()
{
    int menu = -1;
    while (1)
    {
        printf("请输入选项: ");
        scanf("%d", &menu);
        switch (menu)
        {
        case 1:
            inputStudents();
            return;
        case 2:
            showStudents();
            return;
        case 3:
            findStudents();
            return;
        case 4:
            addStudents();
            return;
        case 5:
            removeStudent();
            return;
        case 6:
            statistics();
            return;
        case 0:
            exitSystem();
            return;
        default:
            printf("输入有误，请重新输入!\n");
        }
    }
}

void inputStudents()
{
    int n = 0;
    int ch = 1;
    int i = 0;
    FILE *fp;
    printf("请选择录入方式: 1. 从文件导入；2. 手工录入\n");
    scanf("%d", &ch);
    if (ch == 1)
    {
        if ((fp = fopen("record.txt", "r")) == NULL)
        {
            printf("文件打开失败\n");
            return;
        }
        while (!feof(fp))
        {
            fscanf(fp, "%s%s%d%d%d", stus[stuNum].no, stus[stuNum].name, &(stus[stuNum].clang), &(stus[stuNum].elec), &(stus[stuNum].phy));
            stuNum++;
        }
        printf("录入完成，共录入%d个学生信息\n", stuNum);
    }
    else if (ch == 2)
    {
        addStudents();
    }
    else
    {
        printf("未知的选项\n");
    }
}

void showStudents()
{
    int i = 0;
    printf("学号\t\t姓名\t\tC语言\t电路\t大学物理\n");
    for (i = 0; i < stuNum; i++)
    {
        printf("%s\t\t%s\t\t%d\t%d\t%d\n", stus[i].no, stus[i].name, stus[i].clang, stus[i].elec, stus[i].phy);
    }
}

void findStudents()
{
    int ch = 1;
    int idx = -1;
    int i = 0;
    char no[30];
    char name[30];
    printf("请选择录入方式: 1. 根据学号查询；2. 根据姓名查询\n");
    scanf("%d", &ch);

    if (ch == 1)
    {
        printf("请输入学号：");
        scanf("%s", no);

        printf("学号\t\t姓名\t\tC语言\t电路\t大学物理\n");
        for (i = 0; i < stuNum; i++)
        {
            if (strcmp(stus[i].no, no) == 0)
            {
                printf("%s\t\t%s\t\t%d\t%d\t%d\n", stus[i].no, stus[i].name, stus[i].clang, stus[i].elec, stus[i].phy);
            }
        }
    }
    else if (ch == 2)
    {
        printf("请输入姓名：");
        scanf("%s", name);

        printf("学号\t\t姓名\t\tC语言\t电路\t大学物理\n");
        for (i = 0; i < stuNum; i++)
        {
            if (strcmp(stus[i].name, name) == 0)
            {
                printf("%s\t\t%s\t\t%d\t%d\t%d\n", stus[i].no, stus[i].name, stus[i].clang, stus[i].elec, stus[i].phy);
            }
        }
    }
    else
    {
        printf("未知的选项\n");
    }
}

void addStudents()
{
    int n = 0;
    int i = 0;
    printf("请输入学生数量：");
    scanf("%d", &n);
    for (i = 0; i < n; i++)
    {
        if (stuNum == MAX_NUM)
        {
            printf("学生数量已满，无法新增!\n");
            return;
        }
        printf("请输入学号：");
        scanf("%s", stus[stuNum].no);
        printf("请输入姓名：");
        scanf("%s", stus[stuNum].name);
        printf("请输入C语言成绩：");
        scanf("%d", &(stus[stuNum].clang));
        printf("请输入电路成绩：");
        scanf("%d", &(stus[stuNum].elec));
        printf("请输入大学物理成绩：");
        scanf("%d", &(stus[stuNum].phy));
        stuNum++;
        printf("添加成功\n");
    }
}

void removeStudent()
{
    int idx = -1;
    int i = 0;
    char no[30];
    char passwd[30];
    printf("请输入要删除的学生学号：");
    scanf("%s", no);
    for (i = 0; i < stuNum; i++)
    {
        if (strcmp(no, stus[i].no) == 0)
        {
            idx = i;
            break;
        }
    }
    if (idx == -1)
    {
        printf("不存在该学生!\n");
        return;
    }
    printf("请输入管理员密码：");
    scanf("%s", passwd);
    if (strcmp(passwd, ADMIN_PASSWORD) != 0)
    {
        printf("管理员密码错误，删除失败");
        return;
    }
    for (i = idx; i < stuNum; i++)
    {
        strcpy(stus[i].no, stus[i + 1].no);
        strcpy(stus[i].name, stus[i + 1].name);
        stus[i].clang = stus[i + 1].clang;
        stus[i].elec = stus[i + 1].elec;
        stus[i].phy = stus[i + 1].phy;
    }
    stuNum--;
    printf("删除成功\n");
}

void statistics()
{
    int ch = 1;
    int idx = -1;
    int i = 0;
    char no[30];
    char name[30];
    printf("请选择统计科目: 1. C语言；2. 电路； 3. 大学物理\n");
    scanf("%d", &ch);

    if (ch == 1)
    {
        statisticsClang();
    }
    else if (ch == 2)
    {
        statisticsElec();
    }
    else if (ch == 3)
    {
        statisticsPhy();
    }
    else
    {
        printf("未知的选项\n");
    }
}

void statisticsClang()
{
    int num_seg_90to100 = 0;
    int num_seg_80to90 = 0;
    int num_seg_70to80 = 0;
    int num_seg_60to70 = 0;
    int num_seg_less60 = 0;

    int idx_less60[MAX_NUM] = {-1};
    int i = 0;
    for (i = 0; i < stuNum; i++)
    {
        if (stus[i].clang >= 90 && stus[i].clang <= 100)
        {
            num_seg_90to100++;
        }
        else if (stus[i].clang >= 80 && stus[i].clang < 90)
        {
            num_seg_80to90++;
        }
        else if (stus[i].clang >= 70 && stus[i].clang < 80)
        {
            num_seg_70to80++;
        }
        else if (stus[i].clang >= 60 && stus[i].clang < 70)
        {
            num_seg_60to70++;
        }
        else
        {
            idx_less60[num_seg_less60++] = i;
        }
    }

    printf("成绩分段\t\t人数\t比例\n");
    printf("[90,100]\t\t%d\t%.2f\n", num_seg_90to100, num_seg_90to100 * 1.0 / stuNum);
    printf("[80,90)\t\t%d\t%.2f\n", num_seg_80to90, num_seg_80to90 * 1.0 / stuNum);
    printf("[70,80)\t\t%d\t%.2f\n", num_seg_70to80, num_seg_70to80 * 1.0 / stuNum);
    printf("[60,70)\t\t%d\t%.2f\n", num_seg_60to70, num_seg_60to70 * 1.0 / stuNum);
    printf("[0,60)\t\t%d\t%.2f\n", num_seg_less60, num_seg_less60 * 1.0 / stuNum);
    printf("不及格名单：\n");
    printf("学号\t\t姓名\t\tC语言\t电路\t大学物理\n");
    for (i = 0; i < num_seg_less60; i++)
    {
        printf("%s\t\t%s\t\t%d\t%d\t%d\n", stus[idx_less60[i]].no, stus[idx_less60[i]].name, stus[idx_less60[i]].clang, stus[idx_less60[i]].elec, stus[idx_less60[i]].phy);
    }
}

void statisticsElec()
{
    int num_seg_90to100 = 0;
    int num_seg_80to90 = 0;
    int num_seg_70to80 = 0;
    int num_seg_60to70 = 0;
    int num_seg_less60 = 0;

    int idx_less60[MAX_NUM] = {-1};
    int i = 0;
    for (i = 0; i < stuNum; i++)
    {
        if (stus[i].elec >= 90 && stus[i].elec <= 100)
        {
            num_seg_90to100++;
        }
        else if (stus[i].elec >= 80 && stus[i].elec < 90)
        {
            num_seg_80to90++;
        }
        else if (stus[i].elec >= 70 && stus[i].elec < 80)
        {
            num_seg_70to80++;
        }
        else if (stus[i].elec >= 60 && stus[i].elec < 70)
        {
            num_seg_60to70++;
        }
        else
        {
            idx_less60[num_seg_less60++] = i;
        }
    }

    printf("分数段\t\t人数\t比例\n");
    printf("[90,100]\t\t%d\t%.2f\n", num_seg_90to100, num_seg_90to100 * 1.0 / stuNum);
    printf("[80,90)\t\t%d\t%.2f\n", num_seg_80to90, num_seg_80to90 * 1.0 / stuNum);
    printf("[70,80)\t\t%d\t%.2f\n", num_seg_70to80, num_seg_70to80 * 1.0 / stuNum);
    printf("[60,70)\t\t%d\t%.2f\n", num_seg_60to70, num_seg_60to70 * 1.0 / stuNum);
    printf("[0,60)\t\t%d\t%.2f\n", num_seg_less60, num_seg_less60 * 1.0 / stuNum);
    printf("不及格名单：\n");
    printf("学号\t\t姓名\t\tC语言\t电路\t大学物理\n");
    for (i = 0; i < num_seg_less60; i++)
    {
        printf("%s\t\t%s\t\t%d\t%d\t%d\n", stus[idx_less60[i]].no, stus[idx_less60[i]].name, stus[idx_less60[i]].clang, stus[idx_less60[i]].elec, stus[idx_less60[i]].phy);
    }
}

void statisticsPhy()
{
    int num_seg_90to100 = 0;
    int num_seg_80to90 = 0;
    int num_seg_70to80 = 0;
    int num_seg_60to70 = 0;
    int num_seg_less60 = 0;

    int idx_less60[MAX_NUM] = {-1};
    int i = 0;
    for (i = 0; i < stuNum; i++)
    {
        if (stus[i].phy >= 90 && stus[i].phy <= 100)
        {
            num_seg_90to100++;
        }
        else if (stus[i].phy >= 80 && stus[i].phy < 90)
        {
            num_seg_80to90++;
        }
        else if (stus[i].phy >= 70 && stus[i].phy < 80)
        {
            num_seg_70to80++;
        }
        else if (stus[i].phy >= 60 && stus[i].phy < 70)
        {
            num_seg_60to70++;
        }
        else
        {
            idx_less60[num_seg_less60++] = i;
        }
    }

    printf("分数段\t\t人数\t比例\n");
    printf("[90,100]\t\t%d\t%.2f\n", num_seg_90to100, num_seg_90to100 * 1.0 / stuNum);
    printf("[80,90)\t\t%d\t%.2f\n", num_seg_80to90, num_seg_80to90 * 1.0 / stuNum);
    printf("[70,80)\t\t%d\t%.2f\n", num_seg_70to80, num_seg_70to80 * 1.0 / stuNum);
    printf("[60,70)\t\t%d\t%.2f\n", num_seg_60to70, num_seg_60to70 * 1.0 / stuNum);
    printf("[0,60)\t\t%d\t%.2f\n", num_seg_less60, num_seg_less60 * 1.0 / stuNum);
    printf("不及格名单：\n");
    printf("学号\t\t姓名\t\tC语言\t电路\t大学物理\n");
    for (i = 0; i < num_seg_less60; i++)
    {
        printf("%s\t\t%s\t\t%d\t%d\t%d\n", stus[idx_less60[i]].no, stus[idx_less60[i]].name, stus[idx_less60[i]].clang, stus[idx_less60[i]].elec, stus[idx_less60[i]].phy);
    }
}

void exitSystem()
{
    char ch = 'N';
    while (1)
    {
        printf("确定要退出？y/N: ");
        getchar();
        scanf("%c", &ch);
        if (ch == '\n' || ch == '\r' || ch == ' ')
        {
            ch = 'N';
        }
        if (ch != 'y' && ch != 'Y' && ch != 'n' && ch != 'N')
        {
            printf("输入有误，请重新输入!\n");
        }
        else
        {
            if (ch == 'y' || ch == 'Y')
            {
                exit(0);
            }
            else
            {
                return;
            }
        }
    }
}