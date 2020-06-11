#include<iostream>
#include "iFraction.h"

using namespace std;

int main()
{
	iFraction a, b(3, 4), c(1, 3, 4);
	a.displayMix();
	b.displayMix();
	c.displayMix();

	return 0;
}