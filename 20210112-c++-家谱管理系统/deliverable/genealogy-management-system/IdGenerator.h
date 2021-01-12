#pragma once
/*唯一标识生成器*/
class IdGenerator
{
private:
	static int current;/*当前最新id值 + 1*/
public:
	/*生成id*/
	int static generate();
	/*修改当前最新id值*/
	void static update(int id);
};

