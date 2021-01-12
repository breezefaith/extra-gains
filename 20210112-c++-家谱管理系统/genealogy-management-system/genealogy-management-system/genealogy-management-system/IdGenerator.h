#pragma once
/*唯一标识生成器*/
class IdGenerator
{
private:
	static int current;/*当前最新id值 + 1*/
public:
	int static generate();/*生成id*/
};

