#pragma once
#include "Point.h"

/*���峣��PI*/
const double PI = std::atan(1.0) * 4;

class Circle : public Point
{
private:
	double radium;
public:
	/*���ι��캯��*/
	Circle(Point& p, double r);
	double calc(Circle& c);
};