#include<iostream>
#include <cstdlib>
#include <cstdio>

#define memMax 10240;		//最大内存，10M
using namespace std;
void prspm();
/*一般在内存中给出一块固定的区域放置段表*/
int memLen;		//内存大小
int pageLen;	//页的大小
typedef struct pageL{			//页表
	int pageNum;	//页号
	int MemPgnum;	//页面号（内存页面号）
	int pageFlag;
}pageL;
struct pageLH{
	int length;
	pageL pageList[1024];
} pageLH;
struct segmentL{			//段的大小与划分不归我管，而且段表的大小是一开始就建立好的，页表在内存中有一块固定的存储区。页表的大小由进程或作业的长度决定
	int segNum;			//段号
	int length;			//段长,即多少页
	int rw;				//访存控制
	int ioflg;			//内外标识
	int count;			//访问位
	pageL* startAddr;	//起始位置
	int segmentFlag;
}; 						//段表，因为段表是固定的内存区，所以直接设为数组。segPagList[100];	
struct segmentLH{
	int length;
	segmentL segPagList[200];//段表，因为段表是固定的内存区，所以直接设为数组。200差不多了1024页，每段5页
}segmentLH;

char m[10240];	//无法使用链表法，char类型是一个字节，节省空间，值表示页是否被使用。
int findIdlePage(){		//返回找到空闲内存页的第一个位置
	int j=1;
	for(int i=0;i<memLen*1024/pageLen;i++){
		if(m[i]==0){
			m[i]=1;
			return i;
		}
		j++;
	}
	if(j==memLen*1024/pageLen){
		printf("所有的内存已经使用完了!请输入数字已选择相应的置换算法！！");
	}
}
int totalIdlePage(){
	int n=0;
	for(int i=0;i<memLen*1024/pageLen;i++){
		if(m[i]==0){n++;}
	}
	return n;
}
int findIdleSegAndWrite(int segNum,int segLen){
	int i;
	for(i=0;i<200;i++){
		if(segmentLH.segPagList[i].segmentFlag==0){
			segmentLH.segPagList[i].segNum=segNum;
			segmentLH.segPagList[i].length=segLen;			//段长，即分多少页
			segmentLH.segPagList[i].segmentFlag=1;
			return i;
		}
	}
	printf("段表没有可以填写的段！");
	return -1;	//没有可以填写的段就返回-1
}
int findSeriesIdlePageList(int q){		//参数q表示需要连续空间的页数,返回值为分配数组起始标号。
	int t,n=0;
L1:
	for(t=0;t<q;t++){
		if(pageLH.pageList[n+t].pageFlag!=0){
			n++;
			if(n<1024-q)goto L1;
			printf("没有可用的连续页表空间！");
		}
	}
	for(t=0;t<q;t++){		//将已分配到的页表，标记为正在使用
		pageLH.pageList[t+n].pageFlag=1;
	}
	return n;
}
void single(){
	//怎样判断内存区有没有空闲
	int flag=1;int q=0;int N=0;int p;
	int segNum,length;
	printf("请输入要输入的段的个数：\n");
	scanf("%d",&N);
	printf("请依次输入段长：\n");
	for(int z=1;z<=N;z++){
		scanf("%d",&length);
		p=totalIdlePage();
		/*先看内存有没有足够的页进行分配，有：就先查找可用段，填写段表*/
		q=((length%pageLen==0)?(length/pageLen):((int)length/pageLen)+1);	//程序要分多少页
		if(p>=q){		//内存够分
			int i=findIdleSegAndWrite(z,q);	//找到可以填写的段，填写段长，段号
			int n=findSeriesIdlePageList(q);		//查找页表寻找连续q个页表空间，返回首地址
			p=p-q;		//在上面的函数中已经将页面数减过了，只是没有反映到p中
			segmentLH.segPagList[i].startAddr=&pageLH.pageList[n];	//填写段的页的开始地址，页表的第n项
			for(int j=n;j<q+n;j++){			//填写页表的时候，页表的填写是连续的
				pageLH.pageList[j].pageNum=j-n+1;
				pageLH.pageList[j].MemPgnum=findIdlePage();//返回找到空闲内存页的第一个位置,并将使用位置1
			}
		}
	}
	//while(flag){
	//	int p=0;
	//	printf("请输入一个段:段号,段长(KB)\n");	//段的划分不是由我来进行的，实际上段的名字来标识段，在这里用段号来取代。
	//	getchar();
	//	scanf("%d,%d",&segNum,&length);
	//	p=totalIdlePage();
	//	/*先看内存有没有足够的页进行分配，有：就先查找可用段，填写段表*/
	//	q=((length%pageLen==0)?(length/pageLen):((int)length/pageLen)+1);	//程序要分多少页
	//	if(p>=q){		//内存够分
	//		int i=findIdleSegAndWrite(segNum,q);	//找到可以填写的段，填写段长，段号
	//		int n=findSeriesIdlePageList(q);		//查找页表寻找连续q个页表空间，返回首地址
	//		p=p-q;		//在上面的函数中已经将页面数减过了，只是没有反映到p中
	//		segmentLH.segPagList[i].startAddr=&pageLH.pageList[n];	//填写段的页的开始地址，页表的第n项
	//		for(int j=n;j<q+n;j++){			//填写页表的时候，页表的填写是连续的
	//			pageLH.pageList[j].pageNum=j-n+1;
	//			pageLH.pageList[j].MemPgnum=findIdlePage();//返回找到空闲内存页的第一个位置,并将使用位置1
	//		}
	//	}
	//	printf("程序要分页：%d\n",q);
	//	printf("内存可用页：%d\n",p);
	//	printf("是否退出（1--继续    0--退出）");
	//	getchar();
	//	scanf("%d",&flag);
	//}
	/*for(int i=0;i<pageLH.length;i++){
		printf("%d--%d\n",pageLH.pageList[i].pageNum,pageLH.pageList[i].MemPgnum);
	}*/
}
void mapping(){
	int s,p,d;int i;
	printf("请输入段号，页号，页内地址:s,p,d\n");
	scanf("%d,%d,%d",&s,&p,&d);
	for(i=0;i<100;i++){
		if(segmentLH.segPagList[i].segNum==s){
			if(segmentLH.segPagList[i].length>p){
				if(d<pageLen*1024){
					printf("输入的地址正确：\n");
					int w=(segmentLH.segPagList[i].startAddr[p].MemPgnum-1)*1024*pageLen+d;
					printf("内存页面号：%d\n",(segmentLH.segPagList[i].startAddr[p].MemPgnum-1));
					printf("%dB\n",w);
					return;
				}else{
					printf("偏移地址偏出该页！\n");return;
				}
			}else{
				printf("没有该页!\n");return;
			}
		}

		if((i+1)>=100)printf("没有该段！\n");
	}
}

void swch(){
	int flag=1,f=0;
	while(flag){
		int n;
		printf("0------------退出---------------0\n");
		printf("1---------程序段处理------------1\n");
		printf("2----------地址转换-------------2\n");
		printf("3------显示段页内存映射表-------3\n");
		getchar();
		scanf("%d",&n);
		switch(n){
		case 0:flag=0;break;
		case 1:single();f=1;break;
		case 2:if(f!=0)mapping();else{printf("请先输入程序段！\n");}break;
		case 3:if(f!=0)prspm();else{printf("请先输入程序段！\n");};break;
		default:printf("输入有误！！\n");
		}
	}
}
void prspm(){
	printf("         段页内存映射表          \n");
	printf("---------------------------------\n");
	for(int i=0;i<200;i++){
		if(segmentLH.segPagList[i].segmentFlag!=0){
			printf("%d",segmentLH.segPagList[i].segNum);	//段号
			printf("----%d\n",segmentLH.segPagList[i].length);	//段长
			for(int j=0;j<segmentLH.segPagList[i].length;j++){
				/*页号****页面号*/
				printf(" |____%d____%d\n",segmentLH.segPagList[i].startAddr[j].pageNum,segmentLH.segPagList[i].startAddr[j].MemPgnum);
			}
			printf("\n");
		}
	}
	printf("---------------------------------\n");
}
int main(){
	printf("现在进行初始化设置。。。\n");
	printf("请指定内存的大小(MB)：");
	scanf("%d",&memLen);
	printf("请设置页的大小（KB）：");	//一般来讲1--4KB
	scanf("%d",&pageLen);
	for(int i=0;i<memLen;i++){	//初始化内存列表
		m[i]=0;
	}
	pageLH.length=0;
	for(int i=0;i<200;i++){
		segmentLH.segPagList[i].segmentFlag=0;	
	}
	for(int i=0;i<1024;i++){
		pageLH.pageList[i].pageFlag=0;
	}
	swch();
	/*for(int i=0;i<1024;i++){
		if(pageLH.pageList[i].pageFlag!=0){
			printf("%d",i);
			printf("页  号：%d     ",pageLH.pageList[i].pageNum);
			printf("页面号：%d\n",pageLH.pageList[i].MemPgnum);
		}
	}*/
	/*for(int i=0;i<memMax;i++){
		if(m[i]!=0)printf("内存：%d:%d\n",i,m[i]);
	}*/
	system("pause");
	return 0;
}
