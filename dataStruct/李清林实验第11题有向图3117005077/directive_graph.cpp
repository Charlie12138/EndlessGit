#include "directive_graph.h"
#include "lqueue.h"

//��ӡ����ͼ
void print(ALGraph G) {
	int i, j, k, l;
	AdjVexNodeP p;
	for(i = 0; i < G.n; i++) {

		if(-1 == FirstAdjVex_AL(G, i, p)) {

			//�ҳ�����ͨ�����еĵ�������λ��
			for(l = 0; l < G.n; l++) {
				for(k = FirstAdjVex_AL(G, l, p); k >= 0; k = NextAdjVex_AL(G, l, p)) {
					if(i == k) break; //l������ڽӶ�����i����
				}
				if(i == k) break;
			}
			
			if(i != k) printf(" %c\n", G.vexs[i].data); //���ڽӶ���
		}
		
		for(j = FirstAdjVex_AL(G, i, p); j >= 0; j = NextAdjVex_AL(G, i, p)) {
			printf("%c->%c\n", G.vexs[i].data, G.vexs[j].data);
		}
	}
}

//�������
Status visit(ALGraph G, int k) {
	if(k < 0 || k > G.n) return ERROR;
	printf("%c ", G.vexs[k].data);
	return OK;
}

//������ȱ���ͼG
Status BFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int)) {
	int i, j, k;
	AdjVexNodeP p;
	LQueue Q; InitQueue_LQ(Q); //��ʼ��������Q
	for(i = 0; i < G.n; i++) G.tags[i] = UNVISITED; //��ʼ����־����
	for(i = 0; i < G.n; i++) {
		if(UNVISITED == G.tags[i]) { //���μ�����ж���
			if(ERROR == visit(G, i)) return ERROR;
			G.tags[i] = VISITED;
			EnQueue_LQ(Q, i); //����i��㣬���
			while(OK == DeQueue_LQ(Q, k)) { //���ӵ�Ԫ��k
				//�����ж�k����������ڽӶ���j����δ���ʣ����ʲ����
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

//����ͨͼG��k�����������������ȱ���
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
//������ȱ���ͼG
Status DFSTraverse_AL(ALGraph G, Status(*visit)(ALGraph, int)) {
    int i;
    for(i = 0; i < G.n; i++) G.tags[i] = UNVISITED; //��ʼ����־����
    for(i = 0; i < G.n; i++) {
    	if(UNVISITED == G.tags[i])
			if(ERROR == DFS_AL(G, i, visit)) return ERROR;
	}
    return OK;
}

//��ͼG��ɾ��k���㵽m����ı߻�
Status RemoveArc_AL(ALGraph G, int k, int m) {
	AdjVexNodeP p, q;
	if(k < 0 || m < 0 || k > G.n || m > G.n) return ERROR;
	FirstAdjVex_AL(G, k, p);
	q = p;
	while(p !=NULL) {
		if(p->adjvex == m) { //�ҵ�k���㵽m����ı߻�
			if(q == G.vexs[k].firstArc) { //m��k�ĵ�һ���ڽӶ���
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

//��ͼG������k���㵽m����ı߻򻡣���Ϊ��Ȩͼ��infoΪȨֵ������Ϊ1
Status AddArc_AL(ALGraph &G, int k, int m, int info) {
	AdjVexNodeP p;
	if(k < 0 || m < 0 || k > G.n || m > G.n) return ERROR;
	if((UDG == G.kind || DG == G.kind) && info != 1) return ERROR; //info��ͼ�����Ͳ�ƥ��
	p = G.vexs[k].firstArc;
	while(p != NULL) {
		if(m == p->adjvex) return ERROR; //�����ڣ�����
		p = p->nextArc;
	}
	p = (AdjVexNode *)malloc(sizeof(AdjVexNode)); //Ϊ����m����p���
	if(p == NULL) return OVERFLOW;
	p->adjvex = m;
	p->info = info;
	p->nextArc = G.vexs[k].firstArc;
	G.vexs[k].firstArc = p;
	G.e++;
	return OK;
}

//��k������ڽ������У���pָ����һ�����
//��p�ǿգ����ش洢��p����е���һ���ڽӶ����λ�򣬷��򷵻�-1
int NextAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p) {
	if(k < 0 || k > G.n) {
		p = NULL;
		return -1;
	}
	if(NULL == p) return -1;
	p = p->nextArc; //��ָ��pָ����һ������
	if(NULL != p) return p->adjvex;
	return -1;
}

//��ͼG��k������ڽӶ�������ǿգ�����ָ��pָ���һ����㣬
//��������洢���ڽӶ���λ�򣬷�����ָ��pΪNULL��������-1
int FirstAdjVex_AL(ALGraph G, int k, AdjVexNodeP &p) {
    if(k < 0 || k > G.n) { //k���㲻����
		p = NULL;
		return -1;
	}
	p = G.vexs[k].firstArc; //��ָ��pָ��k����Ķ�Ӧ���ڽ�����ĵ�һ�����
	if(NULL != p) return p->adjvex; //���ص�һ�����洢�Ķ���λ��
	return -1; //k�������ڽӶ���
}

//��ͼG��k���㸳ֵw
Status PutVex_AL(ALGraph &G, int k, VexType w) {
	if(k < 0 || k > G.n) return ERROR;
	G.vexs[k].data = w;
	return OK;
}

//ȡͼG��k�����ֵ��w
Status GetVex_AL(ALGraph &G, int k, VexType &w) {
	if(k < 0 || k > G.n) return ERROR;
	w = G.vexs[k].data;
	return OK;
}

//���Ҷ���v��ͼ��G�е�λ��
int LocateVex_AL(ALGraph &G, VexType v) {
	for(int i = 0; i < G.n; i++) {
		if(G.vexs[i].data == v) return i;
	}
	return -1;
}

//����ͼG
Status DestroyGraph_AL(ALGraph &G) {
	AdjVexNode *p, *q;
	if(NULL == &G) return OK;
	for(int i = 0; i < G.n; i++) {
		if(NULL != G.vexs[i].firstArc) {
			q = p = G.vexs[i].firstArc->nextArc;
		}
		G.vexs[i].data = 0;
		free(G.vexs[i].firstArc); //�ͷŶ�������
		while(q) {
			q = p->nextArc;
			free(p);
			p = q;
		}
	}
	free(G.tags);//�ͷű�־����
	G.n = G.e = 0;
	return OK;
}

//������n�������e����������ͼG��vexsΪ������Ϣ��arcsΪ����Ϣ
Status CreateDG_AL(ALGraph &G, VexType *vexs, int n, ArcInfo *arcs, int e) {
	int i, j, k;
	VexType v, w;
	AdjVexNode *p;
	G.n = n; G.e = e; //���붥�����ͻ���
	G.vexs = (VexNode *)malloc(n * sizeof(VexNode));
	G.tags = (int *)malloc(n * sizeof(int));
	if(NULL == G.vexs || NULL == G.tags) return OVERFLOW;
	
	for(i = 0; i < G.n; i++) { //��ʼ����־����ͽ�����������
		G.tags[i] = UNVISITED;
		G.vexs[i].data = vexs[i];
		G.vexs[i].firstArc = NULL;
	}
	
	for(k = 0; k < G.e; k++) { //�����ڽ�����
		i = LocateVex_AL(G, arcs[k].v);
		j = LocateVex_AL(G, arcs[k].w); //ȷ��λ��
		if(i < 0 || j < 0) return ERROR;
		p = (AdjVexNode *)malloc(sizeof(AdjVexNode)); //Ϊ����w����p���
		if(NULL == p) return OVERFLOW;
		p->adjvex = j;
		p->nextArc = G.vexs[i].firstArc; //��i������ڽ������ͷ����p���
		G.vexs[i].firstArc = p;
	}
	return OK;
}
