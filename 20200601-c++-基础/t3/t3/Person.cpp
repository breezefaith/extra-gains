#include<iostream>
#include "Person.h"

using namespace std;

Person::Person(int s)
{
	this->salary = s;
}

Person::~Person()
{
}

Person Person::operator++(int)
{
	Person p(this->salary);
	this->salary += 1000;
	return p;
}

Person& Person::operator+(const Person& p)
{
	this->salary += p.salary;
	return *this;
}

void Person::displaySalary()
{
	cout << this->salary << endl;
}