#pragma once
class Person
{
private:
	int salary;

public:
	Person(int s);
	~Person();

	/*���غ�++�����*/
	Person operator++(int);
	/*����+�����*/
	Person& operator+(const Person& p);
	/*��ʾ����*/
	void displaySalary();
};

