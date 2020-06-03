#pragma once
class Fraction
{
private:
	int numerator;
	int denominator;
public:
	Fraction();
	Fraction(int n);
	Fraction(int n, int d);

	Fraction add(const Fraction& f);
	Fraction subtract(const Fraction& f);
	Fraction multiple(const Fraction& f);
	Fraction divide(const Fraction& f);
	int compare(const Fraction& f);

	Fraction operator+(const Fraction& f);
	Fraction operator-(const Fraction& f);
	Fraction operator*(const Fraction& f);
	Fraction operator/(const Fraction& f);
	bool operator==(const Fraction& f);
	bool operator!=(const Fraction& f);
	bool operator<(const Fraction& f);
	bool operator>(const Fraction& f);
	bool operator<=(const Fraction& f);
	bool operator>=(const Fraction& f);

	static const int DIVIDED_BY_ZERO = -1;

	void setNumerator(int n);
	int getNumerator();
	void setDenominator(int n);
	int getDenominator();
	
	void output();
	double convertToDecimal();
};

int gcd(int a, int b);

int lcm(int a, int b);
