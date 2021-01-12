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
			cout << "��Ч��ѡ��\n" << endl;
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
	cout << "0.�˳�ϵͳ" << endl;
	cout << "***************************" << endl;
	cout << "��ѡ���ܣ�";
}

void GMS::displayGenealogy()
{
}

void GMS::displayGeneration()
{
	int gen = -1;
	cout << "������ڼ�����";
	cin >> gen;

}

void GMS::searchByName()
{
	string name = "";
	cout << "������";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}
	cout << "������Ϣ��" << endl;
	p->display();
	cout << "������Ϣ��" << endl;
	if (p->getFather() != NULL)
	{
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
		cout << "��ǰϵͳ���κγ�Ա������������ȣ�" << endl;
		this->setAncestor();
		return;
	}

	string name = "";
	cout << "����������";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}
	cout << "�����뺢����Ϣ" << endl;
	Person* child = new Person();
	this->modifyPerson(child);
	p->addChild(child);
	cout << "��ӳɹ�" << endl;
}

void GMS::remove()
{
	string name = "";
	cout << "������";
	cin >> name;
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
	cout << "������";
	cin >> name;
	Person* p = this->searchByName(this->ancestor, name);
	if (p == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}
	this->modifyPerson(p);
	cout << "�޸ĳɹ���" << endl;
}

void GMS::quit()
{
	exit(0);
}

void GMS::setAncestor()
{
	this->ancestor = new Person();
	this->modifyPerson(this->ancestor);
	cout << "��ӳɹ���" << endl;
}

void* GMS::modifyPerson(Person* p)
{
	string name = "";
	string birthday = "";
	bool married = false;
	string address = "";
	bool live = false;
	string deathDate = "";

	cout << "������";
	cin >> name;
	cout << "�������ڣ�";
	cin >> birthday;
	cout << "�Ƿ��ѻ飺";
	cin >> married;
	cout << "��ַ��";
	cin >> address;
	cout << "�Ƿ��ڣ�";
	cin >> live;
	if (live == false)
	{
		cout << "�������ڣ�";
		cin >> deathDate;
	}

	p->setName(name);
	p->setBirthday(birthday);
	p->setMarried(married);
	p->setAddress(address);
	p->setLive(live);
	p->setDeathDate(deathDate);
}
