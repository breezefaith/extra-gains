// vs�п�ʹ��scanf����
#define _CRT_SECURE_NO_WARNINGS

#pragma once

#ifndef __GAME_H__
#define __GAME_H__

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

// ������������������
#define ROWS 5
#define COLS 5

/*��ʼ������*/
void init_board(char board[ROWS][COLS], int row, int col);
/*չʾ����*/
void display_board(char board[ROWS][COLS], int row, int col);
/*���ִ��*/
void player_move(char board[ROWS][COLS], int row, int col);
/*����ִ��*/
void computer_move(char board[ROWS][COLS], int row, int col);
/*�ж������Ƿ�������1��������0��δ��*/
static int is_full(char board[ROWS][COLS], int row, int col);
/*�ж��Ƿ��ʤ��@�����ʤ��*������ʤ��q��ƽ��*/
char check_win(char board[ROWS][COLS], int row, int col);

#endif