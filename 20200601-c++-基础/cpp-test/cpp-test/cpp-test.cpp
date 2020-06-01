#include <iostream>
#include <fstream>
#include "Person.h"
#include "Point.h"
#include "Circle.h"

using namespace std;

int main()
{
#pragma region t3
	cout << "第3题" << endl;
	Person man(8000);
	man.displaySalary();
	for (int i = 0;i < 5;i++) {
		man++;
	}

	Person woman(8000);
	for (int i = 0;i < 3;i++) {
		woman++;
	}

	for (int i = 0; i < 2; i++)
	{
		man++;
		woman++;
	}
	man = man + woman;
	man.displaySalary();
#pragma endregion

#pragma region t4
	cout << "第4题" << endl;
	int arr[4];
	ofstream fout1("d:\\source.txt");
	for (int i = 0;i < 4;i++) {
		cin >> arr[i];
		fout1 << arr[i] << endl;
	}
	fout1.close();

	ifstream fin("d:\\source.txt");
	ofstream fout2("d:\\destination.txt");

	int n;
	while (!fin.eof())
	{
		fin >> n;
		fout2 << n * 10 + n / 10 << endl;
	}
	fout2.close();
	fin.close();
#pragma endregion

#pragma region t5
	cout << "第5题" << endl;
	Point p1(3, 4), p2(4, 5);
	Circle c1(p1, 4), c2(p2, 3);
	func(p1, p2);
	func(c1, c2);
#pragma endregion
	return 0;
}
