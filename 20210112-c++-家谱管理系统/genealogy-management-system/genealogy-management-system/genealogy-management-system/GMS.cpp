#include "GMS.h"

const string GMS::filePath = "record.gms";

GMS::GMS()
{
	ancestor = NULL;
}


GMS::~GMS()
{
}

void GMS::readFile()
{
	ifstream fin(GMS::filePath);
	if (fin.is_open() == false)
	{
		cout << "文件打开失败" << endl;
		return;
	}

	int id = -1;
	string name = "";
	string birthday = "";
	bool married = false;
	string address = "";
	bool live = false;
	string deathDate = "";
	string fatherName = "";

	string line;
	stringstream ss;
	if (fin.eof() == false)
	{
		getline(fin, line);
	}
	while (fin.eof() == false)
	{
		getline(fin, line);
		if (line.length() == 0)
		{
			break;
		}
		ss << line;
		ss >> id >> name >> fatherName >> birthday >> married >> live >> deathDate >> address;
		name = name.substr(1, name.length() - 2);
		deathDate = deathDate == "-" ? "" : deathDate;
		fatherName = fatherName.substr(1, fatherName.length() - 2);
		address = address.substr(1, address.length() - 2);

		Person* p = new Person();
		p->setId(id);
		p->setName(name);
		p->setBirthday(birthday);
		p->setMarried(married);
		p->setAddress(address);
		p->setLive(live);
		p->setDeathDate(deathDate);

		if (this->ancestor == NULL)
		{
			this->ancestor = p;
		}

		Person* father = this->searchByName(this->ancestor, fatherName);
		p->setFather(father);
		if (father != NULL)
		{
			father->addChild(p);
		}
		IdGenerator::update(id);
	}
	fin.close();
}

void GMS::writeFile()
{
	ofstream fout(GMS::filePath);
	if (fout.is_open() == false)
	{
		cout << "文件打开失败" << endl;
		return;
	}
	fout << "ID\t" << "姓名\t\t" << "父亲\t\t" << "出生日期\t" << "婚否\t"
		<< "健在否\t" << "死亡日期\t" << "地址\t" << endl;
	this->writeFile(this->ancestor, fout);
	fout.close();
}

void GMS::writeFile(Person* root, ofstream& fout)
{
	if (root == NULL)
	{
		return;
	}
	fout << root->getId() << "\t[" << root->getName() << "]\t\t["
		<< (root->getFather() == NULL ? "" : root->getFather()->getName()) << "]\t\t"
		<< root->getBirthday() << "\t" << root->isMarried() << "\t" << root->isLive() << "\t"
		<< (root->getDeathDate() == "" ? "-" : root->getDeathDate()) << "\t["
		<< root->getAddress() << "]\t" << endl;
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->writeFile(*iter, fout);
	}
}

void GMS::preorder(Person * root, vector<Person*>& persons)
{
	if (root == NULL)
	{
		return;
	}
	persons.push_back(root);
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->preorder(*iter, persons);
	}
}

void GMS::start()
{
	int op = -1;
	cout << "使用已有家谱还是新建家谱？（1-已有家谱，2-新建家谱）：";
	cin >> op;
	while (op != 1 && op != 2)
	{
		cout << "无效的选项" << endl;
		cout << "使用已有家谱还是新建家谱？（1-已有家谱，2-新建家谱）：";
		cin >> op;
	}
	if (op == 1)
	{
		this->readFile();
		if (this->ancestor == NULL)
		{
			cout << "当前系统无任何成员，请先添加祖先！" << endl;
			this->setAncestor();
		}
	}
	else
	{
		cout << "请先添加祖先!" << endl;
		this->setAncestor();
	}
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
		case 9:
			this->quit();
			break;
		default:
			cout << "无效的选项，请重新输入" << endl;
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
	cout << "9.退出系统" << endl;
	cout << "***************************" << endl;
	cout << "请选择功能：";
}

void GMS::displayGenealogy()
{
	this->showTableHeader();
	this->showAllPersons(this->ancestor);
}

void GMS::showAllPersons(Person *root)
{
	if (root == NULL)
	{
		return;
	}
	root->display();
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->showAllPersons(*iter);
	}
}

void GMS::displayGeneration()
{
	int gen = -1;
	cout << "请输入第几代：";
	cin >> gen;
	while (gen <= 0)
	{
		cout << "无效数字，请重新输入：";
		cin >> gen;
	}
	vector<Person*> persons;
	this->searchByGeneration(this->ancestor, gen, 1, persons);
	cout << "查询结果：" << endl;
	if (persons.size() == 0)
	{
		cout << "无" << endl;
	}
	else
	{
		this->showTableHeader();
		for (vector<Person*>::const_iterator iter = persons.cbegin(); iter != persons.cend(); iter++)
		{
			(*iter)->display();
		}
	}
}

void GMS::searchByGeneration(Person* root, int tarGen, int curGen, vector<Person*>& persons)
{
	if (root == NULL)
	{
		return;
	}
	if (tarGen == curGen)
	{
		persons.push_back(root);
		return;
	}
	vector<Person*> children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->searchByGeneration(*iter, tarGen, curGen + 1, persons);
	}
}

void GMS::searchByName()
{
	string name = "";
	cout << "姓名：";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	cout << "本人信息：" << endl;
	this->showTableHeader();
	p->display();
	cout << "父亲信息：" << endl;
	if (p->getFather() != NULL)
	{
		this->showTableHeader();
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
		this->showTableHeader();
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
	if (persons.size() == 0)
	{
		cout << "无" << endl;
	}
	else
	{
		this->showTableHeader();
		for (vector<Person*>::const_iterator iter = persons.cbegin(); iter != persons.cend(); iter++)
		{
			(*iter)->display();
		}
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
	string name = "";
	cout << "父亲姓名：";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	cout << "请输入孩子信息" << endl;
	Person* child = new Person();
	this->modifyPerson(child);
	child->setGeneration(p->getGeneration() + 1);
	p->addChild(child);
	cout << "添加成功" << endl;
}

void GMS::remove()
{
	string name = "";
	cout << "姓名：";
	//getchar();
	cin >> name;// getline(cin, name);
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
	cout << "删除成功！" << endl;
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
		Person* p = *iter;
		iter = children.erase(iter);
		this->remove(p);
	}
	delete root;
}

void GMS::update()
{
	string name;
	cout << "姓名：";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "无此成员！" << endl;
		return;
	}
	cout << "请输入修改后信息" << endl;
	this->modifyPerson(p);
	cout << "修改成功！" << endl;
}

void GMS::quit()
{
	this->writeFile();
	exit(0);
}

void GMS::showTableHeader()
{
	cout << "ID\t" << "姓名\t\t" << "父亲\t\t" << "出生日期\t" << "婚否\t"
		<< "健在否\t" << "死亡日期\t" << "地址\t" << endl;
}

void GMS::setAncestor()
{
	this->ancestor = new Person();
	this->modifyPerson(this->ancestor);
	this->ancestor->setGeneration(1);
	cout << "添加成功！" << endl;
}

void GMS::modifyPerson(Person* p)
{
	string name = "";
	string birthday = "";
	bool married = false;
	string address = "";
	bool live = false;
	string deathDate = "";

	cout << "姓名：";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* exist = this->searchByName(this->ancestor, name);
	while (exist != NULL)
	{
		cout << "已存在同名成员，请重新输入，建议用姓名+数字表示，如张三1，李四2" << endl;
		cout << "姓名：";
		// getchar();
		cin >> name;// getline(cin, name);
		exist = this->searchByName(this->ancestor, name);
	}
	cout << "出生日期（格式为yyyy-MM-dd，如1980-07-21）：";
	cin >> birthday;
	cout << "婚否（1-已婚，0-未婚）：";
	cin >> married;
	cout << "地址：";
	cin >> address;
	cout << "健在否（1-健在，0-逝世）：";
	cin >> live;
	if (live == false)
	{
		cout << "死亡日期（格式为yyyy-MM-dd，如1980-07-21）：";
		cin >> deathDate;
	}

	p->setName(name);
	p->setBirthday(birthday);
	p->setMarried(married);
	p->setAddress(address);
	p->setLive(live);
	p->setDeathDate(deathDate);
}
