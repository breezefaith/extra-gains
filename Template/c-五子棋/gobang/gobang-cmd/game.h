// vs中可使用scanf函数
#define _CRT_SECURE_NO_WARNINGS

#pragma once

#ifndef __GAME_H__
#define __GAME_H__

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// 定义棋盘行数与列数
#define ROWS 5
#define COLS 5

/*初始化棋盘*/
void init_board(char board[ROWS][COLS], int row, int col);
/*展示棋盘*/
void display_board(char board[ROWS][COLS], int row, int col);
/*玩家执子*/
void player_move(char board[ROWS][COLS], int row, int col);
/*电脑执子*/
void computer_move(char board[ROWS][COLS], int row, int col);
/*判断棋盘是否已满，1：已满，0：未满*/
static int is_full(char board[ROWS][COLS], int row, int col);
/*判断是否获胜，@：玩家胜，*：电脑胜，q：平局*/
char check_win(char board[ROWS][COLS], int row, int col);

#endif