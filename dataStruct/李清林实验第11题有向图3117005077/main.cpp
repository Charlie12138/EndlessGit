#include "main.h"

int main(int argc, char *argv[]) {
	int num;  //�������
	while(1){
 		IndexPrint();
  		printf("�����������ţ�");
  		scanf("%d", &num);
        fflush(stdin);  //������뻺����
        
    	if(num == 0) {
    		ToDestroy(); //����ͼ
			exit(-1); //�˳�
		} 
        if(num == 1) ToCreateDG(); //��װ����׼�����д�������ͼ����
		if(num == 2) ToPrint(); //��ӡ����ͼ
		if(num == 3) ToDestroy(); //����ͼ
		if(num == 4) ToGetVex(); //ȡͼG��k�����ֵ����ӡ
		if(num == 5) ToPutVex(); //��ͼG��k���㸳ֵw
		if(num == 6) ToFirstAdjVex(); //�����k������ĵ�һ���ڽӶ���
		if(num == 7) ToAddArc(); //��ͼG������k���㵽m����ı߻�
		if(num == 8) ToDelete(); //��ͼG��ɾ��k���㵽m����ı߻�
		if(num == 9) ToDFS(); //��ȱ���
		if(num == 10) ToBFS(); //��ȱ���
		
        system("PAUSE"); //������Ļ
        system("CLS"); //��������
   }
}
