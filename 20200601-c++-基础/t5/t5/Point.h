#pragma once
class Point
{
private:
	double x;
	double y;
public:
	/*�޲ι��캯��*/
	Point();
	/*�������Ĺ��캯��*/
	Point(double x1, double y1);
	/*cal��Ա����*/
	virtual double calc(Point& p);
};

void func(Point& p1, Point& p2);