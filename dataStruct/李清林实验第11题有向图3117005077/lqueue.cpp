#include "lqueue.h"

//构造一个空队列
void InitQueue_LQ(LQueue &Q) {
	Q.front = Q.rear = NULL;
}

//在队列的队尾插入元素
Status EnQueue_LQ(LQueue &Q, ElemType e) {
	LQNode *p;
	p = (LQNode *)malloc(sizeof(LQNode));
	if(NULL == p) return OVERFLOW;
	p->data = e;
	p->next = NULL;
	if(NULL == Q.front) Q.front = p; //e插入空队列
	else Q.rear->next = p; //e插入非空队列
	Q.rear = p; //队尾指针指向新的队尾
	return OK;
}

//链队列的出队
Status DeQueue_LQ(LQueue &Q, ElemType &e) {
	LQNode *p;
	if(NULL == Q.front) return ERROR;
	p = Q.front; //指向队头指针
	e = Q.front->data; //保存队头结点的数据
	Q.front = p->next;
	if(Q.rear == p) Q.rear = NULL; //队列只有一个结点，删去后队列变空
	free(p);
	return OK;
}
