#ifndef MAIN_H_INCLUDED
#define MAIN_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#define UNVISITED   0                     //��û������
#define VISITED     1                     //�����ʹ���
#define INFINITY    MAXINT                //�����������������ֵ������
#define ERROR       -1                    //����
#define OK          0                     //��ȷ
#define OVERFLOW    -2                    //���
typedef int Status;

//�˵�����
void IndexPrint();

//��װ����׼�����д�������ͼ����
void ToCreateDG();

//��ӡ����ͼ
void ToPrint();

//����ͼ
void ToDestroy();

//ȡͼG��k�����ֵ����ӡ
void ToGetVex();

//��ͼG��k���㸳ֵw
void ToPutVex();

//�����k������ĵ�һ���ڽӶ���
void ToFirstAdjVex();

//��ͼG������k���㵽m����ı߻�
void ToAddArc();

//��ͼG��ɾ��k���㵽m����ı߻�
void ToDelete();

//��ȱ���
void ToDFS();

//��ȱ���
void ToBFS();

#endif
