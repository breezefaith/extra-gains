#include <iostream>
#include "Person.h"

using namespace std;

int main()
{
	//定义男生对象
	Person man(8000);
	//显示其起薪
	man.displaySalary();
	//工作5年
	for (int i = 0;i < 5;i++) {
		man++;
	}

	//定义女生对象
	Person woman(8000);
	//女生工作3年
	for (int i = 0;i < 3;i++) {
		woman++;
	}

	//两人结婚后各自工资仍上涨
	for (int i = 0; i < 2; i++)
	{
		man++;
		woman++;
	}
	//计算男生工资
	man = man + woman;
	//显示总工资
	man.displaySalary();
	return 0;
}
