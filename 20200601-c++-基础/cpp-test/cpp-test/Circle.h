#pragma once
#include "Point.h"

const double PI = std::atan(1.0) * 4;

class Circle : public Point
{
private:
	double radium;
public:
	Circle(Point& p, double r);
	double calc(Circle& c);
};