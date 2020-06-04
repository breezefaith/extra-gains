#pragma once
#include "Fraction.h"

class iFraction :public Fraction
{
protected:
	int integer;
public:
	iFraction();
	iFraction(int n, int d);
	iFraction(int i, int n, int d);
	void displayMix();
	friend Fraction convertF(iFraction iF);
};

Fraction convertF(iFraction iF);