#include "bank.h"
extern CLQueue Q1;  //����1
extern CLQueue Q2;  //����2
extern ELQueue Event;  //�¼���

//���ٶ���
void DestoryC(CLQueue &Q) {
	while(Q.front)//��ͷ��㿪ʼ�ͷ������������еĽ��
    {
        Q.rear = Q.front->next;
        free(Q.front);
        Q.front = Q.rear;
    }
}

//���ٶ���
void DestoryE(ELQueue &Q) {
	while(Q.front)//��ͷ��㿪ʼ�ͷ������������еĽ��
    {
        Q.rear = Q.front->next;
        free(Q.front);
        Q.front = Q.rear;
    }
}

//�����еĳ���
Status DeQueue_ELQ(ELQueue &Q) {
	event p;
	if(NULL == Q.front) return ERROR;
	p = Q.front;
	Q.front = Q.front->next;
	if(Q.rear == p) Q.rear = NULL;  //����ֻ��һ����㣬ɾȥ����б��
	free(p);
	return OK;
}

//���������
Status EnQueue_ELQ(ELQueue &Q) {
	EventNode *p;
	p = (event)malloc(sizeof(EventNode));
	if(NULL == p) return OVERFLOW;
	p->next = NULL;
	if(NULL == Q.front) Q.front = p;  //c����ն���
	else Q.rear->next = p;
	Q.rear = p;
	return OK;
}

//����һ���ն���
void InitQueue_ELQ(ELQueue &Q) {
	Q.front = Q.rear = NULL;
}

//�����еĳ���
Status DeQueue_CLQ(CLQueue &Q) {
	Client p;
	if(NULL == Q.front) return ERROR;
	p = Q.front;
	Q.front = Q.front->next;
	if(Q.rear == p) Q.rear = NULL;  //����ֻ��һ����㣬ɾȥ����б��
	free(p);
	return OK;
}

//���������
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

//����һ���ն���
//���ô�ͷ���ĵ�����ʹ�öӿ���ǿվ���ͬһ�ṹ��ʽ��
void InitQueue_CLQ(CLQueue &Q) {
	Q.front = Q.rear = NULL;
}
