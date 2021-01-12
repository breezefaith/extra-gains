#include "IdGenerator.h"

int IdGenerator::current = 0;

int IdGenerator::generate()
{
	return current++;
}
