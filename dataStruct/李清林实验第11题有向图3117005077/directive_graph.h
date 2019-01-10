#ifndef DIRECTIVE_H_INCLUDED
#define DIRECTIVE_H_INCLUDED
#include "main.h"
typedef int VRType;
typedef char VexType;
typedef char InfoType;

typedef enum {DG = 1, DN = 2, UDG = 3, UDN = 4} GraphKind; //有向图、带权有向图、无向图、带权无向图

typedef struct AdjVexNode {
	int adjvex;                           //邻接顶点在顶点数组中的位序
	struct AdjVexNode *nextArc;           //指向下一个邻接顶点（下一条边或弧）
	int info;                             //存储边（弧）相关信息（如带权图的权值）
} AdjVexNode, *AdjVexNodeP;               //邻接链表的结点类型

typedef struct VexNode {
	VexType data;                         //顶点值，VexType是顶点类型，由用户定义
	struct AdjVexNode *firstArc;          //邻接链表的头指针
} VexNode;                                //顶点数组的元素类型

typedef struct {
	VexNode *vexs;                        //顶点数组，用于存储顶点信息
	int n, e;                             //顶点数和边（弧）数
	GraphKind kind;                       //图的类型
	int *tags;                            //标志数组，可用于在图的遍历中标记顶点访问与否
} ALGraph;                                //邻接表类型

typedef struct {
	VexType v, w; //边（弧）的端点
	int info;  //对带权图，为权值
} ArcInfo; //边（弧）信息

//打印有向图
void print(ALGraph G);

//输出顶点k
Status visit(ALGraph G, int k);

//广度优先遍历图G
Status BFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int));

//从连通图G的k顶点出发进行深度优先遍历
Status DFS_AL(ALGraph G, int k, Status(*visit)(ALGraph, int));

//深度优先遍历图G
Status DFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int));

//在图G中删除k顶点到m顶点的边或弧
Status RemoveArc_AL(ALGraph G, int k, int m);

//在图G中增加k顶点到m顶点的边或弧，若为带权图，info为权值，否则为1
Status AddArc_AL(ALGraph &G, int k, int m, int info);

//在k顶点的邻接链表中，令p指向下一个结点
//若p非空，返回存储在p结点中的下一个邻接顶点的位序，否则返回-1
int NextAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p);

//若图G中k顶点的邻接顶点链表非空，则令指针p指向第一个结点，
//并返回其存储的邻接顶点位序，否则，令指针p为NULL，并返回-1
int FirstAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p);

//对图G的k顶点赋值w
Status PutVex_AL(ALGraph &G, int k, VexType w);

//取图G的k顶点的值到w
Status GetVex_AL(ALGraph &G, int k, VexType &w);

//查找顶点v在图中G中的位序
int LocateVex_AL(ALGraph &G, VexType v);

//销毁图G
Status DestroyGraph_AL(ALGraph &G);

//创建含n个顶点和e条弧的有向图G，vexs为顶点信息，arcs为边信息
Status CreateDG_AL(ALGraph &G, VexType *vexs, int n, ArcInfo *arcs, int e);
#endif
