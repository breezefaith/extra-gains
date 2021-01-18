// gobang-cmd2.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//
#include <stdio.h>

#include "pch.h"
#include "game.h"

int main()
{
	CheckerBoard_Init();		//棋盘初始化
	CheckerBoard_Display();		//棋盘显示
	while (!Victory_Flag)		//当有结果时跳出循环
	{
		Victory_Flag = CheckerBoard_Play();	//用来接收对局结果
	}
	switch (Victory_Flag)
	{
	case 1:			//如果接收为1，则白棋胜利
		printf("=======================================\n");
		printf("          White Win! Steps:%d\n", Step_Number);
		printf("=======================================\n");
		break;
	case 2:			//如果接收为2，则黑旗胜利
		printf("=======================================\n");
		printf("          Black Win! Steps:%d\n", Step_Number);
		printf("=======================================\n");
		break;
	case 3:			//如果接收为3，则平局
		printf("=======================================\n");
		printf("              Draw! Steps:%d\n", Step_Number);
		printf("=======================================\n");
	}
	return 0;
}

// 运行程序: Ctrl + F5 或调试 >“开始执行(不调试)”菜单
// 调试程序: F5 或调试 >“开始调试”菜单

// 入门提示: 
//   1. 使用解决方案资源管理器窗口添加/管理文件
//   2. 使用团队资源管理器窗口连接到源代码管理
//   3. 使用输出窗口查看生成输出和其他消息
//   4. 使用错误列表窗口查看错误
//   5. 转到“项目”>“添加新项”以创建新的代码文件，或转到“项目”>“添加现有项”以将现有代码文件添加到项目
//   6. 将来，若要再次打开此项目，请转到“文件”>“打开”>“项目”并选择 .sln 文件
