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
		cout << "�ļ���ʧ��" << endl;
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
		cout << "�ļ���ʧ��" << endl;
		return;
	}
	fout << "ID\t" << "����\t\t" << "����\t\t" << "��������\t" << "���\t"
		<< "���ڷ�\t" << "��������\t" << "��ַ\t" << endl;
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
	cout << "ʹ�����м��׻����½����ף���1-���м��ף�2-�½����ף���";
	cin >> op;
	while (op != 1 && op != 2)
	{
		cout << "��Ч��ѡ��" << endl;
		cout << "ʹ�����м��׻����½����ף���1-���м��ף�2-�½����ף���";
		cin >> op;
	}
	if (op == 1)
	{
		this->readFile();
		if (this->ancestor == NULL)
		{
			cout << "��ǰϵͳ���κγ�Ա������������ȣ�" << endl;
			this->setAncestor();
		}
	}
	else
	{
		cout << "�����������!" << endl;
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
			cout << "��Ч��ѡ�����������" << endl;
			break;
		}
	}
}

void GMS::showMenu()
{
	cout << "***************************" << endl;
	cout << "1.��ʾ����" << endl;
	cout << "2.��ʾ��n����������Ϣ" << endl;
	cout << "3.��������ѯ" << endl;
	cout << "4.���������ڲ�ѯ" << endl;
	cout << "5.��ѯ���˹�ϵ" << endl;
	cout << "6.��Ӻ���" << endl;
	cout << "7.ɾ����Ա" << endl;
	cout << "8.�޸ĳ�Ա" << endl;
	cout << "9.�˳�ϵͳ" << endl;
	cout << "***************************" << endl;
	cout << "��ѡ���ܣ�";
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
	cout << "������ڼ�����";
	cin >> gen;
	while (gen <= 0)
	{
		cout << "��Ч���֣����������룺";
		cin >> gen;
	}
	vector<Person*> persons;
	this->searchByGeneration(this->ancestor, gen, 1, persons);
	cout << "��ѯ�����" << endl;
	if (persons.size() == 0)
	{
		cout << "��" << endl;
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
	cout << "������";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}
	cout << "������Ϣ��" << endl;
	this->showTableHeader();
	p->display();
	cout << "������Ϣ��" << endl;
	if (p->getFather() != NULL)
	{
		this->showTableHeader();
		p->getFather()->display();
	}
	else
	{
		cout << "��" << endl;
	}
	cout << "������Ϣ��" << endl;
	vector<Person*> children = p->getChildren();
	if (children.size() == 0)
	{
		cout << "��" << endl;
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
	cout << "�������ڣ�";
	cin >> birthday;
	vector<Person*> persons;
	searchByBirthday(this->ancestor, birthday, persons);
	cout << "��ѯ�����" << endl;
	if (persons.size() == 0)
	{
		cout << "��" << endl;
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
	cout << "����������";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}
	cout << "�����뺢����Ϣ" << endl;
	Person* child = new Person();
	this->modifyPerson(child);
	child->setGeneration(p->getGeneration() + 1);
	p->addChild(child);
	cout << "��ӳɹ�" << endl;
}

void GMS::remove()
{
	string name = "";
	cout << "������";
	//getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
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
	cout << "ɾ���ɹ���" << endl;
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
	cout << "������";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}
	cout << "�������޸ĺ���Ϣ" << endl;
	this->modifyPerson(p);
	cout << "�޸ĳɹ���" << endl;
}

void GMS::quit()
{
	this->writeFile();
	exit(0);
}

void GMS::showTableHeader()
{
	cout << "ID\t" << "����\t\t" << "����\t\t" << "��������\t" << "���\t"
		<< "���ڷ�\t" << "��������\t" << "��ַ\t" << endl;
}

void GMS::setAncestor()
{
	this->ancestor = new Person();
	this->modifyPerson(this->ancestor);
	this->ancestor->setGeneration(1);
	cout << "��ӳɹ���" << endl;
}

void GMS::modifyPerson(Person* p)
{
	string name = "";
	string birthday = "";
	bool married = false;
	string address = "";
	bool live = false;
	string deathDate = "";

	cout << "������";
	// getchar();
	cin >> name;// getline(cin, name);
	Person* exist = this->searchByName(this->ancestor, name);
	while (exist != NULL)
	{
		cout << "�Ѵ���ͬ����Ա�����������룬����������+���ֱ�ʾ��������1������2" << endl;
		cout << "������";
		// getchar();
		cin >> name;// getline(cin, name);
		exist = this->searchByName(this->ancestor, name);
	}
	cout << "�������ڣ���ʽΪyyyy-MM-dd����1980-07-21����";
	cin >> birthday;
	cout << "���1-�ѻ飬0-δ�飩��";
	cin >> married;
	cout << "��ַ��";
	cin >> address;
	cout << "���ڷ�1-���ڣ�0-��������";
	cin >> live;
	if (live == false)
	{
		cout << "�������ڣ���ʽΪyyyy-MM-dd����1980-07-21����";
		cin >> deathDate;
	}

	p->setName(name);
	p->setBirthday(birthday);
	p->setMarried(married);
	p->setAddress(address);
	p->setLive(live);
	p->setDeathDate(deathDate);
}
