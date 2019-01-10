#ifndef LQUEUE_H_INCLUDED
#define LQUEUE_H_INCLUDED
#include "main.h"

typedef int ElemType;

typedef struct LQNode {
	ElemType data;
	struct LQNode *next;
} LQNode, *QueuePtr; //结点及其指针类型

typedef struct {
	QueuePtr front; //队头指针
	QueuePtr rear; //队尾指针
} LQueue; //链队列

//构造一个空队列
void InitQueue_LQ(LQueue &Q);

//在队列的队尾插入元素
Status EnQueue_LQ(LQueue &Q, ElemType e);

//链队列的出队
Status DeQueue_LQ(LQueue &Q, ElemType &e);

#endif
