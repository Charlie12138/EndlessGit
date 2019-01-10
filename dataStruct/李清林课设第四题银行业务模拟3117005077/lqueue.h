#ifndef LQUEUE_H_INCLUDED
#define LQUEUE_H_INCLUDED
#include "main.h"
#include "bank.h"
typedef CustNode ElemType;

typedef struct {
	QueuePtr front;  //队头指针
	QueuePtr rear;  //队尾指针
};

#endif
