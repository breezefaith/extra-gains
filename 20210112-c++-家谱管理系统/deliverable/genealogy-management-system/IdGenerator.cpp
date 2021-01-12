#include "IdGenerator.h"

int IdGenerator::current = 0;

int IdGenerator::generate()
{
	return current++;
}

void IdGenerator::update(int id)
{
	if (id >= IdGenerator::current)
	{
		IdGenerator::current = id + 1;
	}
}
