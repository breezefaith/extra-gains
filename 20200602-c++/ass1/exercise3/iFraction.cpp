#include <iostream>
#include "iFraction.h"

iFraction::iFraction():Fraction()
{
	integer = 0;
}

iFraction::iFraction(int n, int d): Fraction(n, d)
{
	integer = 0;
}

iFraction::iFraction(int i, int n, int d) : Fraction(n, d)
{
	integer = i;
}

void iFraction::displayMix()
{
	int g = gcd(numerator, denominator);
	Fraction nf(numerator / g, denominator / g);
	std::cout << integer << "+" << nf.getNumerator() << "/" << nf.getDenominator() << std::endl;
}

Fraction convertF(iFraction iF)
{
	int n = iF.integer * iF.denominator + iF.numerator;
	return Fraction(n, iF.denominator);
}