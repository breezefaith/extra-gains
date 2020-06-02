#include <iostream>
#include <fstream>

using namespace std;

int main()
{
	//定义变量接收输入的整数
	int arr[4];
	//定义文件写入流
	ofstream fout1("d:\\source.txt");
	for (int i = 0;i < 4;i++) {
		cin >> arr[i];
		fout1 << arr[i] << endl;
	}
	//关闭流以保存
	fout1.close();

	//定义读取流
	ifstream fin("d:\\source.txt");
	//定义写入流
	ofstream fout2("d:\\destination.txt");

	int n;
	while (!fin.eof())
	{
		//依次读入整数n
		fin >> n;
		//10->101 20->202 30->303 45->454
		fout2 << n * 10 + n / 10 << endl;
	}
	//关闭写入流
	fout2.close();
	//关闭读取流
	fin.close();

	return 0;
}
