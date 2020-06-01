#pragma once
class Point
{
private:
	double x;
	double y;
public:
	Point();
	Point(double x1, double y1);
	virtual double calc(Point& p);
};

void func(Point& p1, Point& p2);