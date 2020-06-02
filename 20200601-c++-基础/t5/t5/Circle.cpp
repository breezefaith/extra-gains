#include <iostream>
#include <cmath>
#include "Circle.h"

using namespace std;

Circle::Circle(Point& p, double r):Point(p)
{
	radium = r;
}

double Circle::calc(Circle& c)
{
	double diff = c.radium * c.radium * PI - radium * radium * PI;
	cout << diff << endl;
	return diff;
}