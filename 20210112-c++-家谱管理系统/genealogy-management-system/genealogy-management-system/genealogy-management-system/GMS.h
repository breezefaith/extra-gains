#pragma once

#include<iostream>
#include<string>

#include "Person.h"

using namespace std;

/*家谱管理系统类*/
class GMS
{
private:
	Person* ancestor;

	void setAncestor();
	void * modifyPerson(Person * p);
	Person * searchByName(Person * root, string name);
public:
	GMS();
	~GMS();
	void start();
	void showMenu();
	void displayGenealogy();
	void displayGeneration();
	void searchByName();
	void searchByBirthday();
	void searchByBirthday(Person * root, string birthday, vector<Person*>& persons);
	void getRelationshipByNames();
	void addChildren();
	void remove();
	void remove(Person * root);
	void update();
	void quit();
};

