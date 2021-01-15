#include "game.h"


void init_board(char board[ROWS][COLS], int row, int col)
{
	memset(board, ' ', col*row * sizeof(board[0][0]));
}
void display_board(char board[ROWS][COLS], int row, int col)
{
	int i = 0;
	for (i = 0; i < row; i++)
	{
		printf("%c | %c| %c| %c|%c\n", board[i][0], board[i][1], board[i][2], board[i][3], board[i][4]);
		if (i != 4)
			printf("--|--|--|--|--\n");
	}
}
void player_move(char board[ROWS][COLS], int row, int col)
{
	int x = 0;
	int y = 0;
	while (1)
	{
		printf("到你了哦！请输入坐标：");
		scanf("%d%d", &x, &y);
		x--;
		y--;
		if (((x >= 0) && (x < row) && (y >= 0) && (y < col)))
		{
			if (board[x][y] == ' ')
			{
				board[x][y] = '@';
				break;
			}
			else
			{
				printf("输入错误，请重新输入!");
			}
		}
		else
		{
			printf("输入错误，请重新输入!");
		}
	}
}
void computer_move(char board[ROWS][COLS], int row, int col)
{
	while (1)
	{
		int x = rand() % row;
		int y = rand() % col;
		if (board[x][y] == ' ')
		{
			board[x][y] = '*';
			break;
		}
	}
	printf("应该到电脑走了！\n");
}
static int is_full(char board[ROWS][COLS], int row, int col)
{
	int i = 0;
	int j = 0;
	for (i = 0; i < row; i++)
	{
		for (j = 1; j < col; j++)
		{
			if (board[i][j] == ' ')
				return 0;
		}
	}
	return 1;
}
char check_win(char board[ROWS][COLS], int row, int col)
{
	int i = 0;
	for (i = 0; i < row; i++)
	{
		if ((board[i][0] == board[i][1])
			&& (board[i][1] == board[i][2])
			&& (board[i][2] == board[i][3])
			&& (board[i][3] == board[i][4])
			&& (board[i][4] != ' '))
			return board[i][1];
	}
	for (i = 0; i < col; i++)
	{
		if ((board[0][i] == board[1][i])
			&& (board[1][i] == board[2][i])
			&& (board[2][i] == board[3][i])
			&& (board[3][i] == board[4][i])
			&& (board[4][i] != ' '))
			return board[1][i];
	}
	if ((board[0][0] == board[1][1])
		&& (board[1][1] == board[2][2])
		&& (board[2][2] == board[3][3])
		&& (board[3][3] == board[4][4])
		&& (board[4][4] != ' '))
		return board[1][1];
	if ((board[0][4] == board[1][3])
		&& (board[1][3] == board[2][2])
		&& (board[2][2] == board[3][1])
		&& (board[3][1] == board[4][0])
		&& (board[4][0] != ' '))
		return board[1][1];
	if (is_full(board, row, col))
	{
		return 'q';
	}
	return ' ';
}