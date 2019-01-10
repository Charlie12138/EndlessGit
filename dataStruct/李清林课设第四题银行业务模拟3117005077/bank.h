#ifndef BANK_H_INCLUDED
#define BANK_H_INCLUDED
#include "main.h"

typedef int Time;  //ʱ��
typedef int Money;  //���

typedef struct EventNode {
	int num;  //�ͻ����
	int type;  //�¼����ͣ�0/1���ĸ������뿪��2�����¼�
	Time occutime;  //����ʱ��
	struct EventNode *next;
}EventNode, *event;

typedef struct ELQueue{
	event front;  //��ͷָ��
	event rear;  //��βָ��
}ELQueue;

typedef struct CustNode {
	int num;  //�ͻ����
	Time arrtime;  //����ʱ��
	Time durtime;  //����ʱ��
	Money amount;  //����С
	struct CustNode *next;
} CustNode, *Client;

typedef struct CLQueue{
	Client front;  //��ͷָ��
	Client rear;  //��βָ��
}CLQueue;

extern CLQueue Q1;  //����1
extern CLQueue Q2;  //����2
extern ELQueue Event;  //�¼���

extern int FirstSuccess;  //�����һҵ��ɹ�������
extern int SecondSuccess;  //����ڶ�ҵ��ɹ�������
extern int FirstService;  //�����һҵ�������
extern int SecondService;  //����ڶ�ҵ�������
extern int LastBalance;  //��һ�����������һ���ͻ�(�ڶ���ҵ��)���Ӵ�֮ǰ������
extern int OpenWindows;  //�������Ǽ��Ŵ���Ĭ��0��
extern int NextArriveTime;  //��һ������ʱ��
extern int FreeTime;   //����յ�ʱ�䣬��־ʲôʱ�򴰿ڿ���
extern int IntervalTime;  //������ʱ������
extern int MaxAmount;  //��������
extern int MaxDealTime;  //����ʱ������
extern int CurrentTime;  //����ʱ�䣬��ʼΪ0
extern int Number;  //�ͻ����룬��ʼΪ0
extern int BankBalance;  //�������
extern int TotalDurtime;  //�ܶ���ʱ��


//���ٶ���
void DestoryE(ELQueue &Q);

//���ٶ���
void DestoryC(CLQueue &Q);

//�����еĳ���
Status DeQueue_ELQ(ELQueue &Q);

//���������
Status EnQueue_ELQ(ELQueue &Q);

//����һ���¼��ն���
void InitQueue_ELQ(ELQueue &Q);

//�����еĳ���
Status DeQueue_CLQ(CLQueue &Q);

//����һ���ն���
void InitQueue_CLQ(CLQueue &Q);

//���������
Status EnQueue_CLQ(CLQueue &Q);
#endif
