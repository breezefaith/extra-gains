#include<iostream>
#include "Fraction.h"

using namespace std;

int main()
{
	Fraction a;
	Fraction b(14, 10);
	Fraction c(5);

	a.output();
	b.output();
	c.output();

	return 0;
}