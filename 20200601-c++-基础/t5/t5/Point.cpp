#include<iostream>
#include "Point.h"

using namespace std;

Point::Point()
{
	x = y = 0;
}

Point::Point(double x1, double y1)
{
	x = x1;
	y = y1;
}

double Point::calc(Point& p)
{
	//使用sqrt计算两点距离
	double dis = sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y));
	cout << dis << endl;
	return dis;
}

void func(Point& p1, Point& p2)
{
	p1.calc(p2);
}