#pragma once
#ifndef __GAME_H__
#define __GAME_H__

enum OPTION
{
	EXIT,
	PLAY
};

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define ROWS 5
#define COLS 5

void init_board(char board[ROWS][COLS], int row, int col);
void display_board(char board[ROWS][COLS], int row, int col);
void player_move(char board[ROWS][COLS], int row, int col);
void computer_move(char board[ROWS][COLS], int row, int col);
static int is_full(char board[ROWS][COLS], int row, int col);
char check_win(char board[ROWS][COLS], int row, int col);

#endif