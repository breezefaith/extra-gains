#pragma once
class Person
{
private:
	int salary;

public:
	Person(int s);
	~Person();

	Person operator++(int);
	Person& operator+(const Person& p);
	void displaySalary();
};

