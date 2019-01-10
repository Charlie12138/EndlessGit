#include "directive_graph.h"
#include "lqueue.h"

//打印有向图
void print(ALGraph G) {
	int i, j, k, l;
	AdjVexNodeP p;
	for(i = 0; i < G.n; i++) {

		if(-1 == FirstAdjVex_AL(G, i, p)) {

			//找出非连通分量中的单个顶点位序
			for(l = 0; l < G.n; l++) {
				for(k = FirstAdjVex_AL(G, l, p); k >= 0; k = NextAdjVex_AL(G, l, p)) {
					if(i == k) break; //l顶点的邻接顶点是i顶点
				}
				if(i == k) break;
			}
			
			if(i != k) printf(" %c\n", G.vexs[i].data); //无邻接顶点
		}
		
		for(j = FirstAdjVex_AL(G, i, p); j >= 0; j = NextAdjVex_AL(G, i, p)) {
			printf("%c->%c\n", G.vexs[i].data, G.vexs[j].data);
		}
	}
}

//输出顶点
Status visit(ALGraph G, int k) {
	if(k < 0 || k > G.n) return ERROR;
	printf("%c ", G.vexs[k].data);
	return OK;
}

//广度优先遍历图G
Status BFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int)) {
	int i, j, k;
	AdjVexNodeP p;
	LQueue Q; InitQueue_LQ(Q); //初始化链队列Q
	for(i = 0; i < G.n; i++) G.tags[i] = UNVISITED; //初始化标志数组
	for(i = 0; i < G.n; i++) {
		if(UNVISITED == G.tags[i]) { //依次检查所有顶点
			if(ERROR == visit(G, i)) return ERROR;
			G.tags[i] = VISITED;
			EnQueue_LQ(Q, i); //访问i结点，入队
			while(OK == DeQueue_LQ(Q, k)) { //出队到元素k
				//依次判断k顶点的所有邻接顶点j，若未访问，访问并入队
				for(j = FirstAdjVex_AL(G, k, p); j >= 0; j = NextAdjVex_AL(G, k, p)) {
					if(UNVISITED == G.tags[j]) {
						if(ERROR == visit(G, j)) return ERROR;
						G.tags[j] = VISITED;
						EnQueue_LQ(Q, j);
					}
				}
			}
		}
	}
	return OK;
}

//从连通图G的k顶点出发进行深度优先遍历
Status DFS_AL(ALGraph G, int k, Status(*visit)(ALGraph, int)) {
    int i;
    AdjVexNodeP p;
    if(ERROR == visit(G, k)) return ERROR;
    G.tags[k] = VISITED;
	for(i = FirstAdjVex_AL(G, k, p); i >= 0; i = NextAdjVex_AL(G, k, p)) {
		if(UNVISITED == G.tags[i])
			if(ERROR == DFS_AL(G, i, visit)) return ERROR;
	}
	return OK;
}
//深度优先遍历图G
Status DFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int)) {
    int i;
    for(i = 0; i < G.n; i++) G.tags[i] = UNVISITED; //初始化标志数组
    for(i = 0; i < G.n; i++) {
    	if(UNVISITED == G.tags[i])
			if(ERROR == DFS_AL(G, i, visit)) return ERROR;
	}
    return OK;
}

//在图G中删除k顶点到m顶点的边或弧
Status RemoveArc_AL(ALGraph G, int k, int m) {
	AdjVexNodeP p, q;
	if(k < 0 || m < 0 || k > G.n || m > G.n) return ERROR;
	FirstAdjVex_AL(G, k, p);
	q = p;
	while(p !=NULL) {
		if(p->adjvex == m) { //找到k顶点到m顶点的边或弧
			if(q == G.vexs[k].firstArc) { //m是k的第一个邻接顶点
				G.vexs[k].firstArc = p->nextArc;
			}
			else q->nextArc = p->nextArc;
			free(p);
			return OK;
		}
		q = p;
		NextAdjVex_AL(G, k, p);
	}
	return ERROR;
}

//在图G中增加k顶点到m顶点的边或弧，若为带权图，info为权值，否则为1
Status AddArc_AL(ALGraph &G, int k, int m, int info) {
	AdjVexNodeP p;
	if(k < 0 || m < 0 || k > G.n || m > G.n) return ERROR;
	if((UDG == G.kind || DG == G.kind) && info != 1) return ERROR; //info和图的类型不匹配
	p = G.vexs[k].firstArc;
	while(p != NULL) {
		if(m == p->adjvex) return ERROR; //弧存在，返回
		p = p->nextArc;
	}
	p = (AdjVexNode *)malloc(sizeof(AdjVexNode)); //为顶点m分配p结点
	if(p == NULL) return OVERFLOW;
	p->adjvex = m;
	p->info = info;
	p->nextArc = G.vexs[k].firstArc;
	G.vexs[k].firstArc = p;
	G.e++;
	return OK;
}

//在k顶点的邻接链表中，令p指向下一个结点
//若p非空，返回存储在p结点中的下一个邻接顶点的位序，否则返回-1
int NextAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p) {
	if(k < 0 || k > G.n) {
		p = NULL;
		return -1;
	}
	if(NULL == p) return -1;
	p = p->nextArc; //令指针p指向下一个顶点
	if(NULL != p) return p->adjvex;
	return -1;
}

//若图G中k顶点的邻接顶点链表非空，则令指针p指向第一个结点，
//并返回其存储的邻接顶点位序，否则，令指针p为NULL，并返回-1
int FirstAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p) {
    if(k < 0 || k > G.n) { //k顶点不存在
		p = NULL;
		return -1;
	}
	p = G.vexs[k].firstArc; //令指针p指向k顶点的对应的邻接链表的第一个结点
	if(NULL != p) return p->adjvex; //返回第一个结点存储的顶点位序
	return -1; //k顶点无邻接顶点
}

//对图G的k顶点赋值w
Status PutVex_AL(ALGraph &G, int k, VexType w) {
	if(k < 0 || k > G.n) return ERROR;
	G.vexs[k].data = w;
	return OK;
}

//取图G的k顶点的值到w
Status GetVex_AL(ALGraph &G, int k, VexType &w) {
	if(k < 0 || k > G.n) return ERROR;
	w = G.vexs[k].data;
	return OK;
}

//查找顶点v在图中G中的位序
int LocateVex_AL(ALGraph &G, VexType v) {
	for(int i = 0; i < G.n; i++) {
		if(G.vexs[i].data == v) return i;
	}
	return -1;
}

//销毁图G
Status DestroyGraph_AL(ALGraph &G) {
	AdjVexNode *p, *q;
	if(NULL == &G) return OK;
	for(int i = 0; i < G.n; i++) {
		if(NULL != G.vexs[i].firstArc) {
			q = p = G.vexs[i].firstArc->nextArc;
		}
		G.vexs[i].data = 0;
		free(G.vexs[i].firstArc); //释放顶点数组
		while(q) {
			q = p->nextArc;
			free(p);
			p = q;
		}
	}
	free(G.tags);//释放标志数组
	G.n = G.e = 0;
	return OK;
}

//创建含n个顶点和e条弧的有向图G，vexs为顶点信息，arcs为边信息
Status CreateDG_AL(ALGraph &G, VexType *vexs, int n, ArcInfo *arcs, int e) {
	int i, j, k;
	VexType v, w;
	AdjVexNode *p;
	G.n = n; G.e = e; //读入顶点数和弧数
	G.vexs = (VexNode *)malloc(n * sizeof(VexNode));
	G.tags = (int *)malloc(n * sizeof(int));
	if(NULL == G.vexs || NULL == G.tags) return OVERFLOW;
	
	for(i = 0; i < G.n; i++) { //初始化标志数组和建立顶点数组
		G.tags[i] = UNVISITED;
		G.vexs[i].data = vexs[i];
		G.vexs[i].firstArc = NULL;
	}
	
	for(k = 0; k < G.e; k++) { //建立邻接链表
		i = LocateVex_AL(G, arcs[k].v);
		j = LocateVex_AL(G, arcs[k].w); //确定位序
		if(i < 0 || j < 0) return ERROR;
		p = (AdjVexNode *)malloc(sizeof(AdjVexNode)); //为顶点w分配p结点
		if(NULL == p) return OVERFLOW;
		p->adjvex = j;
		p->nextArc = G.vexs[i].firstArc; //在i顶点的邻接链表表头插入p结点
		G.vexs[i].firstArc = p;
	}
	return OK;
}
