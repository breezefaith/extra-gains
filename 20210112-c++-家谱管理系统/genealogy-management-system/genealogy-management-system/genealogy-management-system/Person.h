#pragma once
#include<iostream>
#include <vector>
#include<string>
using namespace std;

class Person
{
private:
	string name;/*����*/
	string birthday;/*��������*/
	bool married;/*�Ƿ����*/
	string address;/*��ַ*/
	bool live;/*�Ƿ���*/
	string deathDate;/*��������*/

	int id;/*Ψһ��ʶ*/
	int generation;/*�ڼ���*/

	vector<Person*> children; //��Ů��ָ������
	Person* father; //ָ���׵�ָ��

public:
	Person();
	~Person();
	string getName();
	void setName(string name);
	string getBirthday();
	void setBirthday(string birthday);
	bool isMarried();
	void setMarried(bool married);
	string getAddress();
	void setAddress(string address);
	bool isLive();
	void setLive(bool live);
	string getDeathDate();
	void setDeathDate(string deathDate);
	int getId();
	void setId(int id);
	int getGeneration();
	void setGeneration(int generation);
	vector<Person*> getChildren();
	void setChildren(vector<Person*> children);
	Person* getFather();
	void setFather(Person* father);
	void addChild(Person* child);
	void display();
};

