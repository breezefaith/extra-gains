#include "Person.h"
#include "IdGenerator.h"

Person::Person()
{
	this->id = IdGenerator::generate();

	this->name = "";
	this->birthday = "";
	this->married = false;
	this->live = false;
	this->deathDate = "";

	this->generation = 0;
}


Person::~Person()
{
}

string Person::getName()
{
	return this->name;
}

void Person::setName(string name)
{
	this->name = name;
}

string Person::getBirthday()
{
	return this->birthday;
}

void Person::setBirthday(string birthday)
{
	this->birthday = birthday;
}

bool Person::isMarried()
{
	return this->married;
}

void Person::setMarried(bool married)
{
	this->married = married;
}

string Person::getAddress()
{
	return this->address;
}

void Person::setAddress(string address)
{
	this->address = address;
}

bool Person::isLive()
{
	return this->live;
}

void Person::setLive(bool live)
{
	this->live = live;
}

string Person::getDeathDate()
{
	return this->deathDate;
}

void Person::setDeathDate(string deathDate)
{
	this->deathDate;
}

int Person::getId()
{
	return this->id;
}

void Person::setId(int id)
{
	this->id = id;
}

int Person::getGeneration()
{
	return this->generation;
}

void Person::setGeneration(int generation)
{
	this->generation = generation;
}

vector<Person*> Person::getChildren()
{
	return this->children;
}

void Person::setChildren(vector<Person*> children)
{
	this->children = children;
}

Person * Person::getFather()
{
	return this->father;
}

void Person::setFather(Person * father)
{
	this->father = father;
}

void Person::addChild(Person * child)
{
	children.push_back(child);
}

void Person::display()
{
	cout << this->id << ": " << this->name << endl;
}
