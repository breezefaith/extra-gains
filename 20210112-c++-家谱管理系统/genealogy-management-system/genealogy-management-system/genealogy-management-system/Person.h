#pragma once
#include<iostream>
#include <vector>
#include<string>
using namespace std;

class Person
{
private:
	string name;/*姓名*/
	string birthday;/*出生日期*/
	bool married;/*是否婚配*/
	string address;/*地址*/
	bool live;/*是否健在*/
	string deathDate;/*死亡日期*/

	int id;/*唯一标识*/
	int generation;/*第几代*/

	vector<Person*> children; //子女的指针容器
	Person* father; //指向父亲的指针

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

