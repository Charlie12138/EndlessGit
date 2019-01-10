#include "lqueue.h"

//����һ���ն���
void InitQueue_LQ(LQueue &Q) {
	Q.front = Q.rear = NULL;
}

//�ڶ��еĶ�β����Ԫ��
Status EnQueue_LQ(LQueue &Q, ElemType e) {
	LQNode *p;
	p = (LQNode *)malloc(sizeof(LQNode));
	if(NULL == p) return OVERFLOW;
	p->data = e;
	p->next = NULL;
	if(NULL == Q.front) Q.front = p; //e����ն���
	else Q.rear->next = p; //e����ǿն���
	Q.rear = p; //��βָ��ָ���µĶ�β
	return OK;
}

//�����еĳ���
Status DeQueue_LQ(LQueue &Q, ElemType &e) {
	LQNode *p;
	if(NULL == Q.front) return ERROR;
	p = Q.front; //ָ���ͷָ��
	e = Q.front->data; //�����ͷ��������
	Q.front = p->next;
	if(Q.rear == p) Q.rear = NULL; //����ֻ��һ����㣬ɾȥ����б��
	free(p);
	return OK;
}
