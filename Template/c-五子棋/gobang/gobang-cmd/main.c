// gobang-cmd.cpp : 此文件包含 "main" 函数。程序执行将在此处开始并结束。
//

#include "pch.h"
#include "game.h"

/*运行游戏*/
void runGame()
{
	char board[ROWS][COLS] = { 0 };
	char ret = 0;
	init_board(board, ROWS, COLS);
	display_board(board, ROWS, COLS);
	srand((unsigned int)time(NULL));

	while (1)
	{
		player_move(board, ROWS, COLS);
		if ((ret = check_win(board, ROWS, COLS)) != ' ')
			break;
		/*if ((check_win(board, ROWS, COLS)) != ' ')
		{
			ret = check_win(board, ROWS, COLS);
			break;
		}*/

		display_board(board, ROWS, COLS);
		computer_move(board, ROWS, COLS);
		if ((ret = check_win(board, ROWS, COLS)) != ' ')
			break;
		/*if ((check_win(board, ROWS, COLS)) != ' ')
		{
			ret = check_win(board, ROWS, COLS);
			break;
		}*/
		display_board(board, ROWS, COLS);
	}

	if (ret == '@')
	{
		printf("真厉害，恭喜你赢了\n");
	}
	else if (ret == '*')
	{
		printf("呵呵！真遗憾！\n");
	}
	else if (ret == 'q')
	{
		printf("平局\n");
	}
	display_board(board, ROWS, COLS);
}

/*显示菜单*/
void showMenu()
{
	printf("**********欢迎进入五子棋游戏***********\n");
	printf("***************记得选择哦**************\n");
	printf("**************（1.play）***************\n");
	printf("**************（0.exit）***************\n");
	printf("**************祝您玩的愉快*************\n");
}

/*程序入口*/
int main()
{
	int input = 0;
	do
	{
		showMenu();
		printf("请选择:");
		scanf("%d", &input);
		switch (input)
		{
		case 1:
			runGame();
			break;
		case 0:
			break;
		default:
			printf("选择错误\n");
			break;
		}
	} while (input);
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
