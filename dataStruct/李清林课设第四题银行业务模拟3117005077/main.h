#ifndef MAIN_H_INCLUDED
#define MAIN_H_INCLUDED

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#define ERROR       -1                    //����
#define OK          0                     //��ȷ
#define OVERFLOW    -2                    //���

typedef int Status;

void close();//ֹͣӪҵ
void check(int lastBalance);  //���ڶ�������
void judge();  //�����һ�����еĿͻ�ҵ��
void arrive();  //�ͻ�����
void result();//���
void simulation();//ģ��
void initial();  //��ʼ������
void indexPrint(); //��ӡ��ʼ����
#endif
