#include "main.h"
#include "directive_graph.h"

int n = 0, e = 0; //有向图的顶点数和边数
ALGraph G;
VexType *vexs; //vexs为顶点信息
ArcInfo *arcs; //arcs为边信息
int i;

//广度遍历
void ToBFS() {
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("广度遍历结果如下：\n");
	if(OK != BFSTraverse_AL(G, visit)) printf("遍历失败！");
	printf("\n");
}

//深度遍历
void ToDFS() {
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("深度遍历结果如下：\n");
	if(OK != DFSTraverse_AL(G, visit)) printf("遍历失败！");
	printf("\n");
}

//在图G中删除k顶点到m顶点的边或弧
void ToDelete() {
	int k, m;
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("请输入要删除边的k顶点：");
	fflush(stdin);
	scanf("%d", &k);
	printf("\n");
	
	printf("请输入要删除边的m顶点：");
	fflush(stdin);
	scanf("%d", &m);
	printf("\n");
	if(OK != RemoveArc_AL(G, k, m)) {
		printf("删除失败！\n");
		return;
	}
	printf("删除成功，删除后：\n");
	ToPrint();
}

//在图G中增加k顶点到m顶点的边或弧
void ToAddArc() {
	int k, m, info;
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("请输入k顶点序号:");
	fflush(stdin);
	scanf("%d", &k);
	printf("\n");

	printf("请输入m顶点序号:");
	fflush(stdin);
	scanf("%d", &m);
	printf("\n");

	info = 1; //无权有向图
	if(OK != AddArc_AL(G, k, m, info)) {
		printf("添加失败！请确认所加边不存在.\n");
	} else {
		printf("添加后：\n");
		ToPrint();
	}
}

//若图G中k顶点的邻接顶点链表非空，则输出第k个顶点的第一个邻接顶点
void ToFirstAdjVex() {
	int i, k;
	char a;
	AdjVexNodeP p;
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("请输入要输出第几个顶点的第一个邻接顶点：");
	scanf("%d", &k);
	printf("\n");
	i = FirstAdjVex_AL(G, k, p);
	if(i != -1) {
		printf("第%d个顶点的第一个邻接顶点为：", k);
		visit(G, i);
		printf("\n");
	} else {
		printf("第%d个顶点无邻接顶点.\n", k);
	}
	printf("\n是否打印下一个邻接顶点：Y or N ?\n");
	fflush(stdin);
	scanf("%c", &a);
	if(a == 'y' || a == 'Y') {
		i = NextAdjVex_AL(G, k, p);
		printf("第%d个顶点的下一个邻接顶点为：", k);
		visit(G, i);
		printf("\n");
	}
}

//对图G的k顶点赋值w
void ToPutVex() {
	VexType w;
	int k;
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("请输入要赋值的是第几个顶点：\n");
	fflush(stdin);
	scanf("%d", &k);
	printf("请输入要赋的值：\n");
	fflush(stdin);
	scanf("%c", &w);
	if(OK == PutVex_AL(G, k, w)) {
		printf("赋值成功！赋值后有向图如下：\n");
		ToPrint();
	}
	else printf("赋值失败！");
}

//取图G的k顶点的值并打印
void ToGetVex() {
	VexType w;
	int k;
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	printf("请输入要打印的是第几个顶点：\n");
	fflush(stdin);
	scanf("%d", &k);
	if(OK == GetVex_AL(G, k, w)) printf("第%d个顶点现在是：%c\n", k, w);
	else printf("获取失败！");
}

//销毁图
void ToDestroy() {
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	if(OK == DestroyGraph_AL(G)) printf("销毁成功!");
	else printf("销毁失败！");
}

//封装数据准备进行创建有向图操作
void ToCreateDG() {
	printf("请输入有向图顶点数：");
    scanf("%d", &n);
	if(NULL == (vexs = (VexType *)malloc(n * sizeof(VexType)))) printf("溢出！");
	printf("按顺序输入顶点信息↓\n");
	for(i = 0; i < n; i++) {
        fflush(stdin);  //清空输入缓冲区
		printf("第%d个顶点：", i + 1);
        vexs[i] = getchar();
	}
	
    printf("请输入有向图边数：");
    scanf("%d", &e);
	if(NULL == (arcs = (ArcInfo *)malloc(e * sizeof(ArcInfo)))) printf("溢出！");
	printf("\n请输入边信息↓\n");
	for(i = 0; i < e; i++) {
		printf("第%d条边信息(v->w)\n", i + 1);
		fflush(stdin);  //清空输入缓冲区
		printf("顶点v："); arcs[i].v = getchar();
		fflush(stdin);  //清空输入缓冲区
		printf("顶点w："); arcs[i].w = getchar();
		printf("\n");
        arcs[i].info = 1; //不是带权图
	}
	
	if(OK == CreateDG_AL(G, vexs, n, arcs, e)) {
		printf("创建成功！有向图如下：\n");
		print(G);
		printf("\n");
	}
	else {
		printf("创建失败！");
		printf("\n");
	}
}

//打印有向图
void ToPrint() {
	if(0 == G.n) {
		printf("图为空！");
		return;
	}
	print(G);
}

//打印菜单界面
void IndexPrint() {
	system("color 06");
	printf("*********************************************\n");
	printf("**           网络工程2班李清林             **\n");
	printf("**                                         **\n");
	printf("**              有向图                     **\n");
	printf("**                                         **\n");
	printf("**   请选择你想要进行的操作序号:           **\n");
	printf("**    0.退出                               **\n");
	printf("**    1.创建有向图G                        **\n");
	printf("**    2.打印有向图G                        **\n");
	printf("**    3.销毁有向图G                        **\n");
	printf("**    4.打印K顶点                          **\n");
	printf("**    5.给K顶点赋值                        **\n");
	printf("**    6.输出K顶点的第一个邻接顶点          **\n");
	printf("**    7.在图G中增加k顶点到m顶点的边或弧    **\n");
	printf("**    8.删除图G中k顶点到m顶点的边或弧      **\n");
	printf("**    9.深度遍历                           **\n");
	printf("**    10.广度遍历                          **\n");
	printf("*********************************************\n\n\n");
}
