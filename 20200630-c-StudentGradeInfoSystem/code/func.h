#ifndef FUNC_H
#define FUNC_H

#define ADMIN_PASSWORD "aaaaaa"

#define MAX_NUM 50

StudentPtr stus[MAX_NUM];
int stuNum;

void showMenus();

void execMenu(int menu);

void inputStudents();

void showStudents();

void addStudent();

void removeStudent();

void statistics();

void exitSystem();

#endif