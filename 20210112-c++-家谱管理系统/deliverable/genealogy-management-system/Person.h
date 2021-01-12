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

	vector<Person*> children; //子女的指针容器
	Person* father; //指向父亲的指针

public:
	Person();
	~Person();
	/*获取姓名*/
	string getName();
	/*设置姓名*/
	void setName(string name);
	/*获取生日*/
	string getBirthday();
	/*设置生日*/
	void setBirthday(string birthday);
	/*获取是否已婚*/
	bool isMarried();
	/*设置是否已婚*/
	void setMarried(bool married);
	/*获取地址*/
	string getAddress();
	/*设置地址*/
	void setAddress(string address);
	/*获取是否健在*/
	bool isLive();
	/*设置是否健在*/
	void setLive(bool live);
	/*获取死亡时间*/
	string getDeathDate();
	/*设置死亡时间*/
	void setDeathDate(string deathDate);
	/*获取id*/
	int getId();
	/*设置id*/
	void setId(int id);
	/*获取孩子列表引用*/
	vector<Person*>& getChildren();
	/*设置孩子列表*/
	void setChildren(vector<Person*> children);
	/*获取父亲指针*/
	Person* getFather();
	/*设置父亲之中*/
	void setFather(Person* father);
	/*添加孩子*/
	void addChild(Person* child);
	/*显示成员信息*/
	void display();
};

