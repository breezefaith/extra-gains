#pragma once
/*Ψһ��ʶ������*/
class IdGenerator
{
private:
	static int current;/*��ǰ����idֵ + 1*/
public:
	/*����id*/
	int static generate();
	void static update(int id);
};

