#pragma once
#include "Point.h"

/*定义常量PI*/
const double PI = std::atan(1.0) * 4;

class Circle : public Point
{
private:
	double radium;
public:
	/*带参构造函数*/
	Circle(Point& p, double r);
	double calc(Circle& c);
};