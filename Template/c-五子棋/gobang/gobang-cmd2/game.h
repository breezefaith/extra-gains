// vs�п�ʹ��scanf����
#define _CRT_SECURE_NO_WARNINGS

#pragma once

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 17		//���̴�С��15*15���������ֱ�ʶ
#define CONDITION 5		//ʤ����������������

void CheckerBoard_Init();			//���̳�ʼ������
void CheckerBoard_Display();			//������ʾ����
int CheckerBoard_Play();				//�Ծֿ�ʼ����
int White_Play(int x, int y);			//���������ڼ�
int Black_Play(int x, int y);			//���������ڼ�

typedef struct {
	int x;
	int y;
}coordinate;		//����ṹ��

char CheckerBoard[MAX][MAX];		//��ͼ��ά����
coordinate position;				//���������û����������
int Step_Number;					//�������㲽��
int Victory_Flag;					//ʤ�����