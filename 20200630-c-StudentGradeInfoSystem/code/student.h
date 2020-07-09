#ifndef STUDENT_H
#define STUDENT_H

typedef struct Student
{
    char no[30];
    char name[30];
    int clang;
    int elec;
    int phy;
} Student, *StudentPtr;

// StudentPtr create(char no[], char name[], int clang, int elec, int phy);

// void addOne(Student stus[], int n, StudentPtr pstu);

// void removeOne(Student stus[], int n, StudentPtr pstu);

// StudentPtr findByNo(Student stus[], int n, char no[]);

// StudentPtr findByName(Student stus[], int n, char name[]);

// void showList(Student stus[], int n);

// void showDetail(StudentPtr pstu);

#endif