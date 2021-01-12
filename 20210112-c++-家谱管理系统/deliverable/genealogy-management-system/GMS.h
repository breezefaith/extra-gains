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

/*家谱管理系统类*/
class GMS
{
private:
	/*家族祖先*/
	Person* ancestor;

	/*显示菜单*/
	void showMenu();
	/*显示家谱*/
	void displayGenealogy();
	/*按层级展示家谱*/
	void showLevel();
	/*展示所有成员列表*/
	void showAllPersons(Person* root);
	/*显示第n代所有成员*/
	void displayGeneration();
	/*根据代数查找*/
	void searchByGeneration(Person* root, int tarGen, int curGen, vector<Person*>& persons);
	/*根据名称查找*/
	void searchByName();
	Person* searchByName(Person* root, string name);
	/*根据生日查找*/
	void searchByBirthday();
	void searchByBirthday(Person* root, string birthday, vector<Person*>& persons);
	/*输入两人姓名，判断两人关系*/
	void getRelationshipByNames();
	/*添加孩子*/
	void addChildren();
	/*删除成员*/
	void remove();
	void remove(Person* root);
	/*修改成员*/
	void update();
	/*退出程序并写入文件*/
	void quit();
	/*显示成员列表表头*/
	void showTableHeader();
	/*设置祖先*/
	void setAncestor();
	/*修改指定成员*/
	void modifyPerson(Person* p);
	/*家谱文件路径*/
	static const string filePath;
	/*读取文件加载家谱信息*/
	void readFile();
	/*将家谱信息写入文件*/
	void writeFile();
	void writeFile(Person* root, ofstream& fout);
public:
	GMS();
	~GMS();
	/*运行家谱管理系统*/
	void start();
};

