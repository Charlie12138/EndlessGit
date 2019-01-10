#include "main.h"
#include "directive_graph.h"

int n = 0, e = 0; //����ͼ�Ķ������ͱ���
ALGraph G;
VexType *vexs; //vexsΪ������Ϣ
ArcInfo *arcs; //arcsΪ����Ϣ
int i;

//��ȱ���
void ToBFS() {
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("��ȱ���������£�\n");
	if(OK != BFSTraverse_AL(G, visit)) printf("����ʧ�ܣ�");
	printf("\n");
}

//��ȱ���
void ToDFS() {
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("��ȱ���������£�\n");
	if(OK != DFSTraverse_AL(G, visit)) printf("����ʧ�ܣ�");
	printf("\n");
}

//��ͼG��ɾ��k���㵽m����ı߻�
void ToDelete() {
	int k, m;
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("������Ҫɾ���ߵ�k���㣺");
	fflush(stdin);
	scanf("%d", &k);
	printf("\n");
	
	printf("������Ҫɾ���ߵ�m���㣺");
	fflush(stdin);
	scanf("%d", &m);
	printf("\n");
	if(OK != RemoveArc_AL(G, k, m)) {
		printf("ɾ��ʧ�ܣ�\n");
		return;
	}
	printf("ɾ���ɹ���ɾ����\n");
	ToPrint();
}

//��ͼG������k���㵽m����ı߻�
void ToAddArc() {
	int k, m, info;
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("������k�������:");
	fflush(stdin);
	scanf("%d", &k);
	printf("\n");

	printf("������m�������:");
	fflush(stdin);
	scanf("%d", &m);
	printf("\n");

	info = 1; //��Ȩ����ͼ
	if(OK != AddArc_AL(G, k, m, info)) {
		printf("���ʧ�ܣ���ȷ�����ӱ߲�����.\n");
	} else {
		printf("��Ӻ�\n");
		ToPrint();
	}
}

//��ͼG��k������ڽӶ�������ǿգ��������k������ĵ�һ���ڽӶ���
void ToFirstAdjVex() {
	int i, k;
	char a;
	AdjVexNodeP p;
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("������Ҫ����ڼ�������ĵ�һ���ڽӶ��㣺");
	scanf("%d", &k);
	printf("\n");
	i = FirstAdjVex_AL(G, k, p);
	if(i != -1) {
		printf("��%d������ĵ�һ���ڽӶ���Ϊ��", k);
		visit(G, i);
		printf("\n");
	} else {
		printf("��%d���������ڽӶ���.\n", k);
	}
	printf("\n�Ƿ��ӡ��һ���ڽӶ��㣺Y or N ?\n");
	fflush(stdin);
	scanf("%c", &a);
	if(a == 'y' || a == 'Y') {
		i = NextAdjVex_AL(G, k, p);
		printf("��%d���������һ���ڽӶ���Ϊ��", k);
		visit(G, i);
		printf("\n");
	}
}

//��ͼG��k���㸳ֵw
void ToPutVex() {
	VexType w;
	int k;
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("������Ҫ��ֵ���ǵڼ������㣺\n");
	fflush(stdin);
	scanf("%d", &k);
	printf("������Ҫ����ֵ��\n");
	fflush(stdin);
	scanf("%c", &w);
	if(OK == PutVex_AL(G, k, w)) {
		printf("��ֵ�ɹ�����ֵ������ͼ���£�\n");
		ToPrint();
	}
	else printf("��ֵʧ�ܣ�");
}

//ȡͼG��k�����ֵ����ӡ
void ToGetVex() {
	VexType w;
	int k;
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	printf("������Ҫ��ӡ���ǵڼ������㣺\n");
	fflush(stdin);
	scanf("%d", &k);
	if(OK == GetVex_AL(G, k, w)) printf("��%d�����������ǣ�%c\n", k, w);
	else printf("��ȡʧ�ܣ�");
}

//����ͼ
void ToDestroy() {
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	if(OK == DestroyGraph_AL(G)) printf("���ٳɹ�!");
	else printf("����ʧ�ܣ�");
}

//��װ����׼�����д�������ͼ����
void ToCreateDG() {
	printf("����������ͼ��������");
    scanf("%d", &n);
	if(NULL == (vexs = (VexType *)malloc(n * sizeof(VexType)))) printf("�����");
	printf("��˳�����붥����Ϣ��\n");
	for(i = 0; i < n; i++) {
        fflush(stdin);  //������뻺����
		printf("��%d�����㣺", i + 1);
        vexs[i] = getchar();
	}
	
    printf("����������ͼ������");
    scanf("%d", &e);
	if(NULL == (arcs = (ArcInfo *)malloc(e * sizeof(ArcInfo)))) printf("�����");
	printf("\n���������Ϣ��\n");
	for(i = 0; i < e; i++) {
		printf("��%d������Ϣ(v->w)\n", i + 1);
		fflush(stdin);  //������뻺����
		printf("����v��"); arcs[i].v = getchar();
		fflush(stdin);  //������뻺����
		printf("����w��"); arcs[i].w = getchar();
		printf("\n");
        arcs[i].info = 1; //���Ǵ�Ȩͼ
	}
	
	if(OK == CreateDG_AL(G, vexs, n, arcs, e)) {
		printf("�����ɹ�������ͼ���£�\n");
		print(G);
		printf("\n");
	}
	else {
		printf("����ʧ�ܣ�");
		printf("\n");
	}
}

//��ӡ����ͼ
void ToPrint() {
	if(0 == G.n) {
		printf("ͼΪ�գ�");
		return;
	}
	print(G);
}

//��ӡ�˵�����
void IndexPrint() {
	system("color 06");
	printf("*********************************************\n");
	printf("**           ���繤��2��������             **\n");
	printf("**                                         **\n");
	printf("**              ����ͼ                     **\n");
	printf("**                                         **\n");
	printf("**   ��ѡ������Ҫ���еĲ������:           **\n");
	printf("**    0.�˳�                               **\n");
	printf("**    1.��������ͼG                        **\n");
	printf("**    2.��ӡ����ͼG                        **\n");
	printf("**    3.��������ͼG                        **\n");
	printf("**    4.��ӡK����                          **\n");
	printf("**    5.��K���㸳ֵ                        **\n");
	printf("**    6.���K����ĵ�һ���ڽӶ���          **\n");
	printf("**    7.��ͼG������k���㵽m����ı߻�    **\n");
	printf("**    8.ɾ��ͼG��k���㵽m����ı߻�      **\n");
	printf("**    9.��ȱ���                           **\n");
	printf("**    10.��ȱ���                          **\n");
	printf("*********************************************\n\n\n");
}
