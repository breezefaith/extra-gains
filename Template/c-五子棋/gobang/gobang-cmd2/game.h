// vs中可使用scanf函数
#define _CRT_SECURE_NO_WARNINGS

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 17		//棋盘大小，15*15有两行数字标识
#define CONDITION 5		//胜利条件：五子连珠

void CheckerBoard_Init();			//棋盘初始化函数
void CheckerBoard_Display();			//棋盘显示函数
int CheckerBoard_Play();				//对局开始函数
int White_Play(int x, int y);			//白子落子期间
int Black_Play(int x, int y);			//黑子落子期间

typedef struct {
	int x;
	int y;
}coordinate;		//坐标结构体

char CheckerBoard[MAX][MAX];		//地图二维数组
coordinate position;				//用来接收用户输入的坐标
int Step_Number;					//用来计算步数
int Victory_Flag;					//胜利结果