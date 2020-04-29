#include<iostream>
using namespace std;
struct Page {
	int state;
	int frame;
};
struct Seg {
	int state;
	int PageSize;
	struct Page *PageTable;
	int PCB;
	int PCBSeg;
} SegTable[100];
struct PCB {
	int PID;
	int SegNum;
	int FrameNum;
	int Seg[5];//最多5段
} PCB[100];
int FrameSize;
int FrameNum;
int Frame[10000];
int SegNum;
int PID = 0;
int PcbNum = 0;
int LeftFrames;
void Create() {
	int num;
	int size;
	int Frames;
	int i, j, k;
	PCB[PcbNum++].PID = PID++;
	cout << "输入段数:";
	cin >> num;
	while (num > 5) {
		cout << "进程最多分为5段，请重新输入段数:";
		cin >> num;
	}
	PCB[PcbNum - 1].SegNum = num;
	PCB[PcbNum - 1].FrameNum = 0;
	for (i = 0; i < num; i++) {
		cout << "输入第" << i << "段的大小：";
		cin >> size;
		Frames = (size - 1) / FrameSize + 1;
		while (Frames > LeftFrames) {
			cout << "仅剩余" << LeftFrames << "页，请重新输入段大小:" << endl;
			cin >> size;
			Frames = (size - 1) / FrameSize + 1;
		}
		LeftFrames -= Frames;
		for (j = 0; j < 100; j++) {
			if (SegTable[j].state == 0) break;
		}
		PCB[PcbNum - 1].Seg[i] = j;

		PCB[PcbNum - 1].FrameNum += Frames;
		SegTable[j].PCB = PcbNum - 1;
		SegTable[j].PCBSeg = i;
		SegTable[j].state = 1;
		SegTable[j].PageSize = Frames;
		SegTable[j].PageTable = new Page[Frames];
		memset(SegTable[j].PageTable, 0, sizeof(SegTable[j].PageTable));
		int Num = 0;
		for (k = 0; k < FrameNum; k++)
		{
			if (Frame[k] == 0) {
				Frame[k] = 1;
				SegTable[j].PageTable[Num].frame = k;
				SegTable[j].PageTable[Num].state = 1;
				Num++;
			}
			if (Num == Frames) break;
		}
	}
}

void Recycle_Pro() {
	int num;
	int id;
	int j;
	cout << "请输入进程号：" << endl;
	cin >> id;
	for (j = 0; j < PcbNum; j++) {
		if (PCB[j].PID == id)
		{num = j; break;}
	}
	while (j == PcbNum)
	{
		cout << "不存在这样的进程，请查看进程状态后重新输入:" << endl;
		cin >> id;
		for (j = 0; j < PcbNum; j++) {
			if (PCB[j].PID == id)
			{num = j; break;}
		}
	}
	for (int i = 0; i < PCB[num].SegNum; i++) {
		for (int j = 0; j < SegTable[PCB[num].Seg[i]].PageSize; j++) {
			Frame[SegTable[PCB[num].Seg[i]].PageTable[j].frame] = 0;
		}
		SegTable[PCB[num].Seg[i]].PageSize = 0;
		SegTable[PCB[num].Seg[i]].state = 0;
		delete SegTable[PCB[num].Seg[i]].PageTable;
		SegTable[PCB[num].Seg[i]].PageTable = 0;
	}
	for (int i = num + 1; i < PcbNum; i++) {
		PCB[i - 1] = PCB[i];

	}
	PcbNum--;
}
void Recycle_Seg() {
	int num;
	cout << "请输入段号:";
	cin >> num;
	while (SegTable[num].state == 0) {
		cout << "此段未分配，请重新输入:" << endl;
		cin >> num;
	}
	SegTable[num].state = 0;
	for (int i = 0; i < SegTable[num].PageSize; i++) {
		Frame[SegTable[num].PageTable[i].frame] = 0;
	}
	for (int i = SegTable[num].PCBSeg + 1; i < PCB[SegTable[num].PCB].SegNum; i++)
	{
		PCB[SegTable[num].PCB].Seg[i - 1] = PCB[SegTable[num].PCB].Seg[i];
	}
	PCB[SegTable[num].PCB].FrameNum -= SegTable[num].PageSize;
	PCB[SegTable[num].PCB].SegNum--;
	SegTable[num].PageSize = 0;
	delete SegTable[num].PageTable;
	SegTable[num].PageTable = 0;
}
void ShowPro() {
	if (PcbNum == 0)
	{
		cout << "内存中不存在任何进程！" << endl;
		return ;
	}
	cout << "进程号  " << "分段数目" << "分页数目" << endl;
	for (int i = 0; i < PcbNum; i++) {
		printf("%-8d%-8d%-8d\n", PCB[i].PID, PCB[i].SegNum, PCB[i].FrameNum);
	}
}
void ShowSegTable() {
	cout << "段号    " << "状态    " << "页表大小  " << "页表始址" << endl;
	for (int i = 0; i < SegNum; i++) {
		printf("%-8d%-8d%-10d%-8d\n", i, SegTable[i].state, SegTable[i].PageSize, SegTable[i].PageTable);
	}
}
void ShowPageTable() {
	int num;
	cout << "请输入段号：" << endl;
	cin >> num;
	while (SegTable[num].state == 0) {
		cout << "此段未分配，请重新输入:" << endl;
		cin >> num;
	}
	cout << "页号    " << "状态    " << "存储块#" << endl;
	for (int i = 0; i < SegTable[num].PageSize; i++) {
		printf("%-8d%-8d%-8d\n", i, SegTable[num].PageTable[i].state, SegTable[num].PageTable[i].frame);
	}
}
void ShowMemory() {
	cout << "起始页    " << "大小    " << "状态   " << endl;
	int j = Frame[0];
	int num = 0;
	int addr = 0;
	for (int i = 0; i < FrameNum; i++)
	{
		if (Frame[i] == j) num++;
		else {
			printf("%-10d%-8d%-10d\n", addr, num, j);
			j = Frame[i];
			addr = i;
			num = 1;
		}
	}
	printf("%-10d%-8d%-10d\n", addr, num, j);
}
void init() {
	cout << "输入页面数目:";
	cin >> FrameNum;
	while (FrameNum > 10000) {
		cout << "页匡数目大于系统最大值，请重新输入:";
		cin >> FrameNum;
	}
	LeftFrames = FrameNum;
	cout << "输入每页大小:";
	cin >> FrameSize;
	cout << "输入段表大小:";
	cin >> SegNum;
	while (SegNum > 100) {
		cout << "段表大于系统最大值，请重新输入:" ;
		cin >> SegNum;
	}
	memset(Frame, 0, sizeof(Frame));
	memset(SegTable, 0, sizeof(SegTable));
	getchar();
}
int main() {
	char c;
	init();
	while (1) {
		puts("----------------------------------------------------");
		puts("1.创建进程                     2.回收进程\n");
		puts("3.回收段                       4.显示段表\n");
		puts("5.显示页表                     6.展示内存\n");
		puts("7.显示进程状态                 0.离开\n");
		puts("----------------------------------------------------");
		c = getchar();
		switch (c) {
		case '1': Create(); getchar(); break;
		case '2': Recycle_Pro(); getchar(); break;
		case '3': Recycle_Seg(); getchar(); break;
		case '4': ShowSegTable(); getchar(); break;
		case '5': ShowPageTable(); getchar(); break;
		case '6': ShowMemory(); getchar(); break;
		case '7': ShowPro(); getchar(); break;
		case '0': return 0;
		default: getchar();
		}
	}
}
