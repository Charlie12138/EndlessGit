#include "main.h"
#include "bank.h"


int main(int argc, char** argv) {
	int num;  //�������
	srand((unsigned)time(NULL));
	while(1){
 		indexPrint();
  		printf("�����������ţ�");
  		scanf("%d", &num);
        fflush(stdin);  //������뻺����

		if(num == 0) initial();  //��ʼ������
		
		if(num == 1) simulation();  //ģ��
		
		if(num == 2) result();  //�鿴���
        system("PAUSE"); //������Ļ
        system("CLS"); //��������
   }
	return 0;
}
