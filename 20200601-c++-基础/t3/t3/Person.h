#pragma once
class Person
{
private:
	int salary;

public:
	Person(int s);
	~Person();

	/*重载后++运算符*/
	Person operator++(int);
	/*重载+运算符*/
	Person& operator+(const Person& p);
	/*显示工资*/
	void displaySalary();
};

