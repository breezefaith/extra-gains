#include "GMS.h"

GMS::GMS()
{
	ancestor = NULL;
}


GMS::~GMS()
{
}

void GMS::start()
{
	int op = -1;
	while (true)
	{
		this->showMenu();
		cin >> op;
		switch (op)
		{
		case 1:
			this->displayGenealogy();
			break;
		case 2:
			this->displayGeneration();
			break;
		case 3:
			this->searchByName();
			break;
		case 4:
			this->searchByBirthday();
			break;
		case 5:
			this->getRelationshipByNames();
			break;
		case 6:
			this->addChildren();
			break;
		case 7:
			this->remove();
			break;
		case 8:
			this->update();
			break;
		case 0:
			this->quit();
			break;
		default:
			cout << "无效的选项\n" << endl;
			break;
		}
	}
}

void GMS::showMenu()
{
	cout << "***************************" << endl;
	cout << "1.显示家谱" << endl;
	cout << "2.显示第n代所有人信息" << endl;
	cout << "3.按姓名查询" << endl;
	cout << "4.按出生日期查询" << endl;
	cout << "5.查询两人关系" << endl;
	cout << "6.添加孩子" << endl;
	cout << "7.删除成员" << endl;
	cout << "8.修改成员" << endl;
	cout << "0.退出系统" << endl;
	cout << "***************************" << endl;
	cout << "请选择功能：";
}

void GMS::displayGenealogy()
{
}

void GMS::displayGeneration()
{
	int gen = -1;
	cout << "请输入第几代：";
	cin >> gen;

}

void GMS::searchByName()
{
	string name = "";
	cout << "姓名：";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	cout << "本人信息：" << endl;
	p->display();
	cout << "父亲信息：" << endl;
	if (p->getFather() != NULL)
	{
		p->getFather()->display();
	}
	else
	{
		cout << "无" << endl;
	}
	cout << "孩子信息：" << endl;
	vector<Person*> children = p->getChildren();
	if (children.size() == 0)
	{
		cout << "无" << endl;
	}
	else
	{
		for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
		{
			(*iter)->display();
		}
	}
}

Person* GMS::searchByName(Person* root, string name)
{
	Person* res = NULL;
	if (root == NULL)
	{
		return NULL;
	}
	if (name == root->getName())
	{
		return root;
	}
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		res = this->searchByName(*iter, name);
		if (res != NULL)
		{
			break;
		}
	}
	return res;
}

void GMS::searchByBirthday()
{
	string birthday = "";
	cout << "出生日期：";
	cin >> birthday;
	vector<Person*> persons;
	searchByBirthday(this->ancestor, birthday, persons);
	cout << "查询结果：" << endl;
	for (vector<Person*>::const_iterator iter = persons.cbegin(); iter != persons.cend(); iter++)
	{
		(*iter)->display();
	}
}

void GMS::searchByBirthday(Person* root, string birthday, vector<Person*>& persons)
{
	if (root == NULL)
	{
		return;
	}
	if (birthday == root->getBirthday())
	{
		persons.push_back(root);
	}
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->searchByBirthday(*iter, birthday, persons);
	}
}

void GMS::getRelationshipByNames()
{
}

void GMS::addChildren()
{
	if (this->ancestor == NULL)
	{
		cout << "当前系统无任何成员，请先添加祖先！" << endl;
		this->setAncestor();
		return;
	}

	string name = "";
	cout << "父亲姓名：";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	cout << "请输入孩子信息" << endl;
	Person* child = new Person();
	this->modifyPerson(child);
	p->addChild(child);
	cout << "添加成功" << endl;
}

void GMS::remove()
{
	string name = "";
	cout << "姓名：";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	if (p->getFather() != NULL)
	{
		vector<Person*> children = p->getFather()->getChildren();
		for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
		{
			if ((*iter) == p)
			{
				children.erase(iter);
				break;
			}
		}
	}
	this->remove(p);
}
void GMS::remove(Person* root)
{
	if (root == NULL)
	{
		return;
	}
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->remove(*iter);
	}
	delete root;
}

void GMS::update()
{
	string name = "";
	cout << "姓名：";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	this->modifyPerson(p);
	cout << "修改成功！" << endl;
}

void GMS::quit()
{
	exit(0);
}

void GMS::setAncestor()
{
	this->ancestor = new Person();
	this->modifyPerson(this->ancestor);
	cout << "添加成功！" << endl;
}

void* GMS::modifyPerson(Person* p)
{
	string name = "";
	string birthday = "";
	bool married = false;
	string address = "";
	bool live = false;
	string deathDate = "";

	cout << "姓名：";
	cin >> name;
	cout << "出生日期：";
	cin >> birthday;
	cout << "是否已婚：";
	cin >> married;
	cout << "地址：";
	cin >> address;
	cout << "是否健在：";
	cin >> live;
	if (live == false)
	{
		cout << "死亡日期：";
		cin >> deathDate;
	}

	p->setName(name);
	p->setBirthday(birthday);
	p->setMarried(married);
	p->setAddress(address);
	p->setLive(live);
	p->setDeathDate(deathDate);
}
