#ifndef DIRECTIVE_H_INCLUDED
#define DIRECTIVE_H_INCLUDED
#include "main.h"
typedef int VRType;
typedef char VexType;
typedef char InfoType;

typedef enum {DG = 1, DN = 2, UDG = 3, UDN = 4} GraphKind; //����ͼ����Ȩ����ͼ������ͼ����Ȩ����ͼ

typedef struct AdjVexNode {
	int adjvex;                           //�ڽӶ����ڶ��������е�λ��
	struct AdjVexNode *nextArc;           //ָ����һ���ڽӶ��㣨��һ���߻򻡣�
	int info;                             //�洢�ߣ����������Ϣ�����Ȩͼ��Ȩֵ��
} AdjVexNode, *AdjVexNodeP;               //�ڽ�����Ľ������

typedef struct VexNode {
	VexType data;                         //����ֵ��VexType�Ƕ������ͣ����û�����
	struct AdjVexNode *firstArc;          //�ڽ������ͷָ��
} VexNode;                                //���������Ԫ������

typedef struct {
	VexNode *vexs;                        //�������飬���ڴ洢������Ϣ
	int n, e;                             //�������ͱߣ�������
	GraphKind kind;                       //ͼ������
	int *tags;                            //��־���飬��������ͼ�ı����б�Ƕ���������
} ALGraph;                                //�ڽӱ�����

typedef struct {
	VexType v, w; //�ߣ������Ķ˵�
	int info;  //�Դ�Ȩͼ��ΪȨֵ
} ArcInfo; //�ߣ�������Ϣ

//��ӡ����ͼ
void print(ALGraph G);

//�������k
Status visit(ALGraph G, int k);

//������ȱ���ͼG
Status BFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int));

//����ͨͼG��k�����������������ȱ���
Status DFS_AL(ALGraph G, int k, Status(*visit)(ALGraph, int));

//������ȱ���ͼG
Status DFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int));

//��ͼG��ɾ��k���㵽m����ı߻�
Status RemoveArc_AL(ALGraph G, int k, int m);

//��ͼG������k���㵽m����ı߻򻡣���Ϊ��Ȩͼ��infoΪȨֵ������Ϊ1
Status AddArc_AL(ALGraph &G, int k, int m, int info);

//��k������ڽ������У���pָ����һ�����
//��p�ǿգ����ش洢��p����е���һ���ڽӶ����λ�򣬷��򷵻�-1
int NextAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p);

//��ͼG��k������ڽӶ�������ǿգ�����ָ��pָ���һ����㣬
//��������洢���ڽӶ���λ�򣬷�����ָ��pΪNULL��������-1
int FirstAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p);

//��ͼG��k���㸳ֵw
Status PutVex_AL(ALGraph &G, int k, VexType w);

//ȡͼG��k�����ֵ��w
Status GetVex_AL(ALGraph &G, int k, VexType &w);

//���Ҷ���v��ͼ��G�е�λ��
int LocateVex_AL(ALGraph &G, VexType v);

//����ͼG
Status DestroyGraph_AL(ALGraph &G);

//������n�������e����������ͼG��vexsΪ������Ϣ��arcsΪ����Ϣ
Status CreateDG_AL(ALGraph &G, VexType *vexs, int n, ArcInfo *arcs, int e);
#endif
