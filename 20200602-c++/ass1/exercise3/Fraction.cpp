#include <iostream>
#include <string>
#include "Fraction.h"

Fraction::Fraction()
{
	numerator = 0;
	denominator = 1;
}

Fraction::Fraction(int n)
{
	numerator = n;
	denominator = 1;
}

Fraction::Fraction(int n, int d)
{
	if (d == 0)
	{
		throw DIVIDED_BY_ZERO;
	}
	numerator = n;
	denominator = d;
}

Fraction Fraction::add(const Fraction& f)
{
	int newD = denominator * f.denominator;
	int newN = numerator * f.denominator + f.numerator * denominator;
	int g = gcd(newD, newN);
	return Fraction(newN / g, newD / g);
}

Fraction Fraction::subtract(const Fraction& f)
{
	int newD = denominator * f.denominator;
	int newN = numerator * f.denominator - f.numerator * denominator;
	int g = gcd(newD, newN);
	return Fraction(newN / g, newD / g);
}

Fraction Fraction::multiple(const Fraction& f)
{
	int newD = denominator * f.denominator;
	int newN = numerator * f.numerator;
	int g = gcd(newD, newN);
	return Fraction(newN / g, newD / g);
}

Fraction Fraction::divide(const Fraction& f)
{
	if (f.numerator == 0)
	{
		throw DIVIDED_BY_ZERO;
	}

	int newD = denominator * f.numerator;
	int newN = numerator * f.denominator;
	int g = gcd(newD, newN);
	return Fraction(newN / g, newD / g);
}

int Fraction::compare(const Fraction& f)
{
	Fraction tmp = subtract(f);
	if (tmp.numerator * tmp.denominator == 0) {
		return 0;
	}
	else if (tmp.numerator * tmp.denominator > 0)
	{
		return 1;
	}
	else
	{
		return -1;
	}
}

Fraction Fraction::operator+(const Fraction& f)
{
	return add(f);
}

Fraction Fraction::operator-(const Fraction& f)
{
	return subtract(f);
}

Fraction Fraction::operator*(const Fraction& f)
{
	return multiple(f);
}

Fraction Fraction::operator/(const Fraction& f)
{
	return divide(f);
}

bool Fraction::operator==(const Fraction& f)
{
	return compare(f) == 0;
}

bool Fraction::operator!=(const Fraction& f)
{
	return !operator==(f);
}

bool Fraction::operator<(const Fraction& f)
{
	return compare(f) < 0;
}

bool Fraction::operator>(const Fraction& f)
{
	return compare(f) > 0;
}

bool Fraction::operator<=(const Fraction& f)
{
	return operator<(f) || operator==(f);
}

bool Fraction::operator>=(const Fraction& f)
{
	return operator>(f) || operator==(f);
}

void Fraction::setNumerator(int n)
{
	numerator = n;
}

int Fraction::getNumerator()
{
	return numerator;
}

void Fraction::setDenominator(int d)
{
	if (d == 0)
	{
		throw DIVIDED_BY_ZERO;
	}

	denominator = d;
}

int Fraction::getDenominator()
{
	return denominator;
}

void Fraction::output()
{
	int g = gcd(numerator, denominator);
	Fraction nf(numerator / g, denominator / g);
	//if (nf.numerator == 0)
	//{
	//	std::cout << 0 << std::endl;
	//}
	//else if (nf.denominator == 1)
	//{
	//	std::cout << nf.numerator << std::endl;
	//}
	//else if (nf.denominator == -1)
	//{
	//	std::cout << -1 * nf.denominator << std::endl;
	//}
	//else
	{
		std::cout << nf.numerator << "/" << nf.denominator << std::endl;
	}
}

int gcd(int a, int b)
{
	if (b == 0)
		return a;
	return gcd(b, a % b);
}

int lcm(int a, int b)
{
	return a * b / gcd(a, b);
}

double convertToDecimal(Fraction& f)
{
	return f.getNumerator() * 1.0 / f.getDenominator();
}

Fraction convertToFraction(double d)
{
	long long nume = 0;
	long long denom = 1;
	bool flag = false;
	char ch;
	std::string str = std::to_string(d);

	for (int i = 0; i < (int)str.length(); i++)
	{
		ch = str[i];
		if (ch == '.')
		{
			flag = true;
		}
		else
		{
			nume = nume * 10 + ch - '0';
			if (flag)
			{
				denom *= 10;
			}
		}
	}

	return Fraction(nume, denom);
}
