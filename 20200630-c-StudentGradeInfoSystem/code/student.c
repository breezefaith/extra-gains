#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "student.h"

StudentPtr create(char no[], char name[], int clang, int elec, int phy)
{
    StudentPtr pstu = (StudentPtr)malloc(sizeof(Student));

    strcpy(pstu->no, no);
    strcpy(pstu->name, name);
    pstu->clang = clang;
    pstu->elec = elec;
    pstu->phy = phy;

    return pstu;
}

void addOne(Student stus[], int n, StudentPtr pstu)
{
}

void removeOne(Student stus[], int n, StudentPtr pstu)
{
}

StudentPtr findByNo(Student stus[], int n, char no[])
{
    return 0;
}

StudentPtr findByName(Student stus[], int n, char name[])
{
    return 0;
}

void showList(Student stus[], int n)
{
}

void showDetail(StudentPtr pstu)
{
}