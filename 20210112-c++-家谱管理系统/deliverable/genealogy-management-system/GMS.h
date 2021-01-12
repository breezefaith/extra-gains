#pragma once

#include<iostream>
#include<string>
#include<fstream>
#include <sstream>
#include <cstdio>
#include<queue>

#include "Person.h"
#include "IdGenerator.h"

using namespace std;

/*���׹���ϵͳ��*/
class GMS
{
private:
	/*��������*/
	Person* ancestor;

	/*��ʾ�˵�*/
	void showMenu();
	/*��ʾ����*/
	void displayGenealogy();
	/*���㼶չʾ����*/
	void showLevel();
	/*չʾ���г�Ա�б�*/
	void showAllPersons(Person* root);
	/*��ʾ��n�����г�Ա*/
	void displayGeneration();
	/*���ݴ�������*/
	void searchByGeneration(Person* root, int tarGen, int curGen, vector<Person*>& persons);
	/*�������Ʋ���*/
	void searchByName();
	Person* searchByName(Person* root, string name);
	/*�������ղ���*/
	void searchByBirthday();
	void searchByBirthday(Person* root, string birthday, vector<Person*>& persons);
	/*���������������ж����˹�ϵ*/
	void getRelationshipByNames();
	/*��Ӻ���*/
	void addChildren();
	/*ɾ����Ա*/
	void remove();
	void remove(Person* root);
	/*�޸ĳ�Ա*/
	void update();
	/*�˳�����д���ļ�*/
	void quit();
	/*��ʾ��Ա�б��ͷ*/
	void showTableHeader();
	/*��������*/
	void setAncestor();
	/*�޸�ָ����Ա*/
	void modifyPerson(Person* p);
	/*�����ļ�·��*/
	static const string filePath;
	/*��ȡ�ļ����ؼ�����Ϣ*/
	void readFile();
	/*��������Ϣд���ļ�*/
	void writeFile();
	void writeFile(Person* root, ofstream& fout);
public:
	GMS();
	~GMS();
	/*���м��׹���ϵͳ*/
	void start();
};

