#ifndef BANK_H_INCLUDED
#define BANK_H_INCLUDED
#include "main.h"

typedef int Time;  //时间
typedef int Money;  //金额

typedef struct EventNode {
	int num;  //客户编号
	int type;  //事件类型，0/1从哪个窗口离开，2到达事件
	Time occutime;  //发生时间
	struct EventNode *next;
}EventNode, *event;

typedef struct ELQueue{
	event front;  //队头指针
	event rear;  //队尾指针
}ELQueue;

typedef struct CustNode {
	int num;  //客户编号
	Time arrtime;  //到达时间
	Time durtime;  //逗留时间
	Money amount;  //金额大小
	struct CustNode *next;
} CustNode, *Client;

typedef struct CLQueue{
	Client front;  //队头指针
	Client rear;  //队尾指针
}CLQueue;

extern CLQueue Q1;  //队列1
extern CLQueue Q2;  //队列2
extern ELQueue Event;  //事件表

extern int FirstSuccess;  //办理第一业务成功的人数
extern int SecondSuccess;  //办理第二业务成功的人数
extern int FirstService;  //办理第一业务的人数
extern int SecondService;  //办理第二业务的人数
extern int LastBalance;  //第一个队列中最后一个客户(第二种业务)被接待之前的数额
extern int OpenWindows;  //开启的是几号窗，默认0号
extern int NextArriveTime;  //下一个到达时间
extern int FreeTime;   //服务空挡时间，标志什么时候窗口空闲
extern int IntervalTime;  //到达间隔时间上限
extern int MaxAmount;  //交易上限
extern int MaxDealTime;  //交易时间上限
extern int CurrentTime;  //现在时间，初始为0
extern int Number;  //客户编码，初始为0
extern int BankBalance;  //银行余额
extern int TotalDurtime;  //总逗留时间


//销毁队列
void DestoryE(ELQueue &Q);

//销毁队列
void DestoryC(CLQueue &Q);

//链队列的出队
Status DeQueue_ELQ(ELQueue &Q);

//链队列入队
Status EnQueue_ELQ(ELQueue &Q);

//构造一个事件空队列
void InitQueue_ELQ(ELQueue &Q);

//链队列的出队
Status DeQueue_CLQ(CLQueue &Q);

//构造一个空队列
void InitQueue_CLQ(CLQueue &Q);

//链队列入队
Status EnQueue_CLQ(CLQueue &Q);
#endif
