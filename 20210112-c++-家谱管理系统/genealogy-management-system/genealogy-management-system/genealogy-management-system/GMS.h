#pragma once

#include<iostream>
#include<string>
#include<fstream>
#include <sstream>

#include "Person.h"
#include "IdGenerator.h"

using namespace std;

/*家谱管理系统类*/
class GMS
{
private:
	Person* ancestor;
	void showTableHeader();
	void setAncestor();
	void modifyPerson(Person * p);
	Person * searchByName(Person * root, string name);
	void readFile();
	void writeFile();
	void writeFile(Person * root, ofstream& fout);
	static const string filePath;
	void preorder(Person* root, vector<Person*>& persons);
public:
	GMS();
	~GMS();
	void start();
	void showMenu();
	void displayGenealogy();
	void showAllPersons(Person * root);
	void displayGeneration();
	void searchByGeneration(Person * root, int tarGen, int curGen, vector<Person*>& persons);
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

