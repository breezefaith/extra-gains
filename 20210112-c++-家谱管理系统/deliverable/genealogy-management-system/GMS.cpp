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
	//���ļ�������
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
		//����Ϊ��ͷ
		getline(fin, line);
	}
	while (fin.eof() == false)
	{
		getline(fin, line);
		//��Ϊ�����������ȡ
		if (line.length() == 0)
		{
			break;
		}
		//���ǿ��ж����ַ�������Ϊ���������θ�ֵ
		ss << line;
		ss >> id >> name >> fatherName >> birthday >> married >> live >> deathDate >> address;
		//���ǵ���������ַ���ܻ��пո��������ʽ���������ַ��[]��ס
		name = name.substr(1, name.length() - 2);
		fatherName = fatherName.substr(1, fatherName.length() - 2);
		address = address.substr(1, address.length() - 2);
		//���ǵ������������Ƿ���ֵΪtrueʱΪ���ַ�����ʹ��-����ռλ
		deathDate = deathDate == "-" ? "" : deathDate;

		Person* p = new Person();
		p->setId(id);
		p->setName(name);
		p->setBirthday(birthday);
		p->setMarried(married);
		p->setAddress(address);
		p->setLive(live);
		p->setDeathDate(deathDate);

		//��������ĵ�һ���ڵ㼴Ϊ���Ƚڵ�
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
		//���µ�ǰid������������ֵ
		IdGenerator::update(id);
	}
	//�ر��ļ�������
	fin.close();
}

void GMS::writeFile()
{
	//���ļ������
	ofstream fout(GMS::filePath);
	if (fout.is_open() == false)
	{
		cout << "�ļ���ʧ��" << endl;
		return;
	}
	//�����ͷ��
	fout << "ID\t" << "����\t\t" << "����\t\t" << "��������\t" << "���\t"
		<< "���ڷ�\t" << "��������\t" << "��ַ\t" << endl;
	//�������������ʽ�����г�Ա�����ָ���ļ���
	this->writeFile(this->ancestor, fout);
	//�ر��ļ������
	fout.close();
}

//�������������ʽ�����г�Ա�����ָ���ļ���
void GMS::writeFile(Person* root, ofstream& fout)
{
	if (root == NULL)
	{
		return;
	}
	//���ǵ���������ַ���ܻ��пո��������ʽ���������ַ��[]��ס
		//���ǵ������������Ƿ���ֵΪtrueʱΪ���ַ�����ʹ��-����ռλ
	fout << root->getId() << "\t[" << root->getName() << "]\t\t["
		<< (root->getFather() == NULL ? "" : root->getFather()->getName()) << "]\t\t"
		<< root->getBirthday() << "\t" << root->isMarried() << "\t" << root->isLive() << "\t"
		<< (root->getDeathDate() == "" ? "-" : root->getDeathDate()) << "\t["
		<< root->getAddress() << "]\t" << endl;
	vector<Person*>& children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->writeFile(*iter, fout);
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
	//��ʾ�㼶�ṹ
	this->showLevel();
	//��ʾ��ͷ
	this->showTableHeader();
	//��ʾ���г�Ա��Ϣ�б�
	this->showAllPersons(this->ancestor);
}

void GMS::showLevel()
{
	if (this->ancestor == NULL)
	{
		return;
	}
	//ͨ������ʵ�ֲ������
	queue<Person*> queue;
	queue.push(this->ancestor);
	while (queue.empty() == false)
	{
		int num = queue.size();
		for (int i = 0; i < num; i++) {
			Person* p = queue.front();
			queue.pop();
			cout << p->getName() << (p->getFather() == NULL ? "" : "������" + p->getFather()->getName() + "��") << "\t";
			vector<Person*>& children = p->getChildren();
			for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
			{
				queue.push(*iter);
			}
		}
		cout << endl;
	}
	cout << endl;
}

void GMS::showAllPersons(Person* root)
{
	if (root == NULL)
	{
		return;
	}
	root->display();
	vector<Person*>& children = root->getChildren();
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
	//�������������ʽ������ΪĿ��ֵʱ�����������
	if (root == NULL)
	{
		return;
	}
	if (tarGen == curGen)
	{
		persons.push_back(root);
		return;
	}
	vector<Person*>& children = root->getChildren();
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
	vector<Person*>& children = p->getChildren();
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
	//�������������ʽ����ָ�������ĳ�Ա�����أ�������ʧ�ܷ���NULL
	Person* res = NULL;
	if (root == NULL)
	{
		return NULL;
	}
	if (name == root->getName())
	{
		return root;
	}
	vector<Person*>& children = root->getChildren();
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
	//�������������ʽ������������������ʱ�����������
	if (root == NULL)
	{
		return;
	}
	if (birthday == root->getBirthday())
	{
		persons.push_back(root);
	}
	vector<Person*>& children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); iter++)
	{
		this->searchByBirthday(*iter, birthday, persons);
	}
}

void GMS::getRelationshipByNames()
{
	string name1 = "";
	cout << "����1��";
	// getchar();
	cin >> name1;// getline(cin, name);
	Person* p1 = this->searchByName(this->ancestor, name1);
	if (p1 == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}

	string name2 = "";
	cout << "����2��";
	// getchar();
	cin >> name2;// getline(cin, name);
	Person* p2 = this->searchByName(this->ancestor, name2);
	if (p2 == NULL)
	{
		cout << "�޴˳�Ա��" << endl;
		return;
	}

	//����p1����
	int dis1 = 0;
	Person* pp1 = p1;
	while (pp1->getFather() != NULL)
	{
		dis1++;
		pp1 = pp1->getFather();
	}

	//����p2����
	int dis2 = 0;
	Person* pp2 = p2;
	while (pp2->getFather() != NULL)
	{
		dis2++;
		pp2 = pp2->getFather();
	}

	//�������
	int diff = dis2 - dis1;
	if (diff < -1)
	{
		printf("%s��%sС%d��\n", name1.c_str(), name2.c_str(), -1 * diff);
	}
	else if (diff == -1)
	{
		if (p1->getFather() == p2)
		{
			printf("%s��%s�ĺ���\n", name1.c_str(), name2.c_str());
		}
		else
		{
			printf("%s��%sС%d��\n", name1.c_str(), name2.c_str(), -1 * diff);
		}
	}
	else if (diff == 0)
	{
		if (p2 == p1)
		{
			printf("%s��%s��ͬһ����\n", name1.c_str(), name2.c_str());
		}
		else
		{
			printf("%s��%sͬ��\n", name1.c_str(), name2.c_str());
		}
	}
	else if (diff == 1)
	{
		if (p2->getFather() == p1)
		{
			printf("%s��%s�ĸ���\n", name1.c_str(), name2.c_str());
		}
		else
		{
			printf("%s��%s��%d��\n", name1.c_str(), name2.c_str(), diff);
		}
	}
	else
	{
		printf("%s��%s��%d��\n", name1.c_str(), name2.c_str(), diff);
	}
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
		//��ָ����Ա�Ӹ��׵ĺ����б���ɾ��
		vector<Person*>& children = p->getFather()->getChildren();
		for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); )
		{
			if ((*iter) == p)
			{
				iter = children.erase(iter);
			}
			else
			{
				iter++;
			}
		}
	}
	//ɾ�������뺢���б�
	this->remove(p);
	cout << "ɾ���ɹ���" << endl;
}
void GMS::remove(Person* root)
{
	if (root == NULL)
	{
		return;
	}
	vector<Person*>& children = root->getChildren();
	for (vector<Person*>::const_iterator iter = children.cbegin(); iter != children.cend(); )
	{
		Person* p = *iter;
		//�Ӻ����б���ɾ����ǰ����
		iter = children.erase(iter);
		//ɾ�����������뺢�ӵĺ��ӽڵ��б�
		this->remove(p);
	}
	//ȷ�����ӽڵ�ȫ��ɾ��
	children.clear();
	//ɾ������
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
	//�˳�ϵͳʱ����ǰ����д���ļ�
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
