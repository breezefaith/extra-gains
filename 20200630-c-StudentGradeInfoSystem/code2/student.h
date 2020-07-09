#ifndef STUDENT_H
#define STUDENT_H

#define ADMIN_PASSWORD "aaaaaa"

#define MAX_NUM 50

typedef struct Student
{
    char no[30];
    char name[30];
    int clang;
    int elec;
    int phy;
} Student, *StudentPtr;

Student stus[MAX_NUM];
int stuNum;

void showMenus();

void execMenu();

void inputStudents();

void showStudents();

void findStudents();

void addStudents();

void removeStudent();

void statistics();

void statisticsClang();

void statisticsElec();

void statisticsPhy();

void exitSystem();

#endif