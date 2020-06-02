#include <iostream>
#include "Point.h"
#include "Circle.h"

using namespace std;

int main()
{
	// 创建Point对象
	Point p1(3, 4), p2(4, 5);
	//创建Circle对象
	Circle c1(p1, 4), c2(p2, 3);
	//调用func方法
	func(p1, p2);
	func(c1, c2);

	return 0;
}