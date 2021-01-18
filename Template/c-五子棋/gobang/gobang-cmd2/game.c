#include "pch.h"
#include "game.h"

void CheckerBoard_Init()		//���̳�ʼ������
{
	int i, j;
	for (i = 0; i < MAX; i++)
	{
		for (j = 0; j < MAX; j++)
		{
			if (i == 0 || i == MAX - 1)			//��������̱�Ե�򸳸�����Ӧ����
			{
				CheckerBoard[i][j] = j;
			}
			else if (j == 0 || j == MAX - 1)
			{
				CheckerBoard[i][j] = i;
			}
			else						//����������̱�Ե�򸳸���'.'
			{
				CheckerBoard[i][j] = '.';
			}
		}
	}
}

void CheckerBoard_Display()				//��ʾ���̺���
{
	int i, j;
	for (i = 0; i < MAX; i++)
	{
		for (j = 0; j < MAX; j++)
		{
			if (i == 0 || i == MAX - 1)			//��������̱�Ե���ô�ӡ����
			{
				printf("%2d", CheckerBoard[i][j]);
			}
			else if (j == 0 || j == MAX - 1)
			{
				printf("%2d", CheckerBoard[i][j]);
			}
			else				//����������̱�Ե���ӡ�ַ�
			{
				printf(" %c", CheckerBoard[i][j]);
			}
		}
		printf("\n");
	}
}

int CheckerBoard_Play()			//�Ծ��ڼ亯��
{
	int Flag = 3;
	while (Flag == 3 && Step_Number != (MAX - 2)*(MAX - 2))
	{
		if (!(Step_Number % 2))		//�������Ϊ������������ӽ׶Σ����Ϊ˫����������ӽ׶�
		{
			printf("=======================================\n");
			printf("    Black:x    Steps:%d\n", Step_Number + 1);
			printf("=======================================\n");
		}
		else
		{
			printf("=======================================\n");
			printf("               Steps:%d    White:o\n", Step_Number + 1);
			printf("=======================================\n");
		}
		printf("input position x and y:");
		scanf("%d%d", &position.x, &position.y);		//�����û�������
		scanf("%*[^\n]");			//������뻺����
		scanf("%*c");
		if (position.x == 0 || position.x >= MAX - 1 || position.y == 0 || position.y >= MAX - 1)	//���������������������ʾ�����߽�
		{
			printf("=======================================\n");
			printf("         Beyond the Boundary!\n");
			printf("=======================================\n");
		}
		else if (CheckerBoard[position.x][position.y] == '.')			//���û�����������Ⲣ�Ҵ�λ��û�б��������ռ���ִ�����ӳ���
		{
			if (!(Step_Number % 2))
			{
				Flag = Black_Play(position.x, position.y);		//����Ϊ��ִ�к������ӳ���
			}
			else
			{
				Flag = White_Play(position.x, position.y);		//����Ϊ˫ִ�а������ӳ���
			}
			Step_Number++;		//������һ
		}
		else			//�������λ���Ѿ����ڱ�����ӣ�����ʾ���Ǵ���
		{
			printf("=======================================\n");
			printf("                Cover!\n");
			printf("=======================================\n");
		}
		CheckerBoard_Display();	//��ӡ����
	}
	return Flag;
}

int White_Play(int x, int y)		//�������ӳ���
{
	int i, Transverse = 1, Vertical = 1, LeftOblique = 1, RightOblique = 1;	//�����������������������б��б����������������ﵽ5���������ʤ
	CheckerBoard[x][y] = 'o';						//���û����������λ�øĳ�'o'
	for (i = 1; CheckerBoard[x - i][y] == 'o'; i++)		//��������λ�������ϱ�����ĸ���
	{
		Vertical++;
	}
	for (i = 1; CheckerBoard[x + i][y] == 'o'; i++)		//��������λ�������±�����ĸ���
	{
		Vertical++;
	}
	for (i = 1; CheckerBoard[x][y - i] == 'o'; i++)		//��������λ�ú����������ĸ���
	{
		Transverse++;
	}
	for (i = 1; CheckerBoard[x][y + i] == 'o'; i++)		//��������λ�ú����ұ�����ĸ���
	{
		Transverse++;
	}
	for (i = 1; CheckerBoard[x - i][y + i] == 'o'; i++)	//��������λ����б�����ϱ�����ĸ���
	{
		LeftOblique++;
	}
	for (i = 1; CheckerBoard[x + i][y - i] == 'o'; i++)	//��������λ����б�����±�����ĸ���
	{
		LeftOblique++;
	}
	for (i = 1; CheckerBoard[x + i][y + i] == 'o'; i++)	//��������λ����б�����±�����ĸ���
	{
		RightOblique++;
	}
	for (i = 1; CheckerBoard[x - i][y - i] == 'o'; i++)	//��������λ����б�����ϱ�����ĸ���
	{
		RightOblique++;
	}
	if (Vertical == CONDITION ||
		Transverse == CONDITION ||
		LeftOblique == CONDITION ||
		RightOblique == CONDITION)		//�������ĸ����ﵽ��ʤ�����򷵻�1
	{
		return 1;
	}
	return 3;		//���û�дﵽ�򷵻�3������ѭ��
}

int Black_Play(int x, int y)		//�������ӽ׶Σ�����Ͱ���һ��������ﵽʤ����������2
{
	int i, Transverse = 1, Vertical = 1, LeftOblique = 1, RightOblique = 1;
	CheckerBoard[x][y] = 'x';
	for (i = 1; CheckerBoard[x - i][y] == 'x'; i++)
	{
		Vertical++;
	}
	for (i = 1; CheckerBoard[x + i][y] == 'x'; i++)
	{
		Vertical++;
	}
	for (i = 1; CheckerBoard[x][y - i] == 'x'; i++)
	{
		Transverse++;
	}
	for (i = 1; CheckerBoard[x][y + i] == 'x'; i++)
	{
		Transverse++;
	}
	for (i = 1; CheckerBoard[x - i][y + i] == 'x'; i++)
	{
		LeftOblique++;
	}
	for (i = 1; CheckerBoard[x + i][y - i] == 'x'; i++)
	{
		LeftOblique++;
	}
	for (i = 1; CheckerBoard[x + i][y + i] == 'x'; i++)
	{
		RightOblique++;
	}
	for (i = 1; CheckerBoard[x - i][y - i] == 'x'; i++)
	{
		RightOblique++;
	}
	if (Vertical == CONDITION ||
		Transverse == CONDITION ||
		LeftOblique == CONDITION ||
		RightOblique == CONDITION)
	{
		return 2;
	}
	return 3;
}