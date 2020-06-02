#pragma once
class Point
{
private:
	double x;
	double y;
public:
	/*无参构造函数*/
	Point();
	/*带参数的构造函数*/
	Point(double x1, double y1);
	/*cal成员函数*/
	virtual double calc(Point& p);
};

void func(Point& p1, Point& p2);