#ifndef LQUEUE_H_INCLUDED
#define LQUEUE_H_INCLUDED
#include "main.h"

typedef int ElemType;

typedef struct LQNode {
	ElemType data;
	struct LQNode *next;
} LQNode, *QueuePtr; //��㼰��ָ������

typedef struct {
	QueuePtr front; //��ͷָ��
	QueuePtr rear; //��βָ��
} LQueue; //������

//����һ���ն���
void InitQueue_LQ(LQueue &Q);

//�ڶ��еĶ�β����Ԫ��
Status EnQueue_LQ(LQueue &Q, ElemType e);

//�����еĳ���
Status DeQueue_LQ(LQueue &Q, ElemType &e);

#endif
