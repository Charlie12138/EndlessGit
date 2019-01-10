#include "bank.h"
extern CLQueue Q1;  //队列1
extern CLQueue Q2;  //队列2
extern ELQueue Event;  //事件表

//销毁队列
void DestoryC(CLQueue &Q) {
	while(Q.front)//从头结点开始释放链队列中所有的结点
    {
        Q.rear = Q.front->next;
        free(Q.front);
        Q.front = Q.rear;
    }
}

//销毁队列
void DestoryE(ELQueue &Q) {
	while(Q.front)//从头结点开始释放链队列中所有的结点
    {
        Q.rear = Q.front->next;
        free(Q.front);
        Q.front = Q.rear;
    }
}

//链队列的出队
Status DeQueue_ELQ(ELQueue &Q) {
	event p;
	if(NULL == Q.front) return ERROR;
	p = Q.front;
	Q.front = Q.front->next;
	if(Q.rear == p) Q.rear = NULL;  //队列只有一个结点，删去后队列变空
	free(p);
	return OK;
}

//链队列入队
Status EnQueue_ELQ(ELQueue &Q) {
	EventNode *p;
	p = (event)malloc(sizeof(EventNode));
	if(NULL == p) return OVERFLOW;
	p->next = NULL;
	if(NULL == Q.front) Q.front = p;  //c插入空队列
	else Q.rear->next = p;
	Q.rear = p;
	return OK;
}

//构造一个空队列
void InitQueue_ELQ(ELQueue &Q) {
	Q.front = Q.rear = NULL;
}

//链队列的出队
Status DeQueue_CLQ(CLQueue &Q) {
	Client p;
	if(NULL == Q.front) return ERROR;
	p = Q.front;
	Q.front = Q.front->next;
	if(Q.rear == p) Q.rear = NULL;  //队列只有一个结点，删去后队列变空
	free(p);
	return OK;
}

//链队列入队
Status EnQueue_CLQ(CLQueue &Q) {
	Client p;
	p = (Client)malloc(sizeof(CustNode));
	if(NULL == p) return OVERFLOW;
	p->next = NULL;
	if(!Q.front) Q.front = p;
	else Q.rear->next = p;
	Q.rear = p;
	return OK;
}

//构造一个空队列
//采用带头结点的单链表，使得队空与非空具有同一结构形式。
void InitQueue_CLQ(CLQueue &Q) {
	Q.front = Q.rear = NULL;
}
