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

	vector<Person*> children; //��Ů��ָ������
	Person* father; //ָ���׵�ָ��

public:
	Person();
	~Person();
	/*��ȡ����*/
	string getName();
	/*��������*/
	void setName(string name);
	/*��ȡ����*/
	string getBirthday();
	/*��������*/
	void setBirthday(string birthday);
	/*��ȡ�Ƿ��ѻ�*/
	bool isMarried();
	/*�����Ƿ��ѻ�*/
	void setMarried(bool married);
	/*��ȡ��ַ*/
	string getAddress();
	/*���õ�ַ*/
	void setAddress(string address);
	/*��ȡ�Ƿ���*/
	bool isLive();
	/*�����Ƿ���*/
	void setLive(bool live);
	/*��ȡ����ʱ��*/
	string getDeathDate();
	/*��������ʱ��*/
	void setDeathDate(string deathDate);
	/*��ȡid*/
	int getId();
	/*����id*/
	void setId(int id);
	/*��ȡ�����б�����*/
	vector<Person*>& getChildren();
	/*���ú����б�*/
	void setChildren(vector<Person*> children);
	/*��ȡ����ָ��*/
	Person* getFather();
	/*���ø���֮��*/
	void setFather(Person* father);
	/*��Ӻ���*/
	void addChild(Person* child);
	/*��ʾ��Ա��Ϣ*/
	void display();
};

