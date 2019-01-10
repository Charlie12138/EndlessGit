#include "main.h"
#include "bank.h"
CLQueue Q1;  //����1
CLQueue Q2;  //����2
ELQueue Event;  //�¼���

int FirstSuccess;  //�����һҵ��ɹ�������
int SecondSuccess;  //����ڶ�ҵ��ɹ�������
int FirstService;  //�����һҵ�������
int SecondService;  //����ڶ�ҵ�������
int LastBalance;  //��һ�����������һ���ͻ�(�ڶ���ҵ��)���Ӵ�֮ǰ������
int OpenWindows = 0;  //�������Ǽ��Ŵ���Ĭ��0��
int FreeTime = 0;  //����յ�ʱ�䣬��־ʲôʱ�򴰿ڿ���
int IntervalTime;  //������ʱ������
int NextArriveTime = 0;  //��һ������ʱ��
int OpenTime;  //Ӫҵʱ��
int MaxAmount;  //��������
int MaxDealTime;  //����ʱ������
int CurrentTime = 0;  //����ʱ�䣬��ʼΪ0
int Number = 0;  //�ͻ����룬��ʼΪ0
int BankBalance = 0;  //�������
int TotalDurtime = 0;  //�ܶ���ʱ��

//ֹͣӪҵ
void close() {
	while(Q1.front) {
		EnQueue_ELQ(Event);  //���¼����
		Event.rear->occutime = CurrentTime;
		Event.rear->num = Q1.front->num;
		Event.rear->type = 0;  //��0�Ŵ����뿪
		DeQueue_CLQ(Q1);
	}
	while(Q2.front) {
		EnQueue_ELQ(Event);  //���뿪�¼����
		Event.rear->occutime = CurrentTime;
		TotalDurtime += CurrentTime - Q2.rear->arrtime;
		Event.rear->num = Q2.front->num;
		Event.rear->type = 1;  //��1�Ŵ����뿪
		DeQueue_CLQ(Q2);  //����
	}
}

//����
void deal(CLQueue &Q, int windows) {
	int amount = Q.front->amount;
	int durtime = Q.front->durtime;
	BankBalance += amount;	//���Ͻ�����������������ɽ���
	if(amount < 0) {
		FirstSuccess++;
	} else {
		SecondSuccess++;
	}
	TotalDurtime += CurrentTime - Q.front->arrtime;  //�����ܶ���ʱ��
	EnQueue_ELQ(Event);  //���¼����
	Event.rear->occutime = CurrentTime;  
	Event.rear->num = Q.front->num;
	Event.rear->type = windows;  //�Ӽ��Ŵ����뿪
	DeQueue_CLQ(Q);
	if(Q.front && OpenWindows == 0) {
		if(Q.front->amount > 0 || -1 * Q.front->amount < BankBalance) {
			FreeTime += Q.front->durtime;  //���¿���ʱ��
		}
	} 
}

//���ڶ�������
void check() {  
	Client p, sign;
	if(!Q2.front && OpenWindows == 1) {
		OpenWindows = 0;  //�ڶ�������û�пͻ����ر�1������0��
		if(Q1.front) {
        	if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
				FreeTime += Q1.front->durtime;  //���¿���ʱ��
			}
		}
		return;  
	}
    if(!Q2.front && OpenWindows == 0) {
    	return;
	}
	p = sign = Q2.front;
	while(BankBalance > LastBalance) {
        if(-1 * p->amount <= BankBalance) {
        	if(Q1.front) {
        		if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
        			FreeTime -= Q1.front->durtime;  //�Ȳ��������1��ҵ��
				}
			} 
			if(Q2.front) FreeTime += p->durtime;  //���¿���ʱ��
			OpenWindows = 1; //�ر�0�Ŵ��ڣ�����1�Ŵ���
			break;
		} else {
			Q2.rear->next = p;  //������Ҫ�������ŵ���β
			Q2.rear = p;
			Q2.front = p->next;
			Q2.rear->next = NULL;
		}
		p = Q2.front;
		if(p == sign) {
			OpenWindows = 0;
			break;   //�߼����һ��
		}
	}
	if(BankBalance <= LastBalance) {
		OpenWindows = 0;  //�ڶ�������û�пͻ������������ر�1������0��
        if(Q1.front) {
        	if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
				FreeTime += Q1.front->durtime;  //���¿���ʱ��
			}
		}
	} 
}

//�ж���һ����ô����
void judge(CLQueue &Q) {
	int amount = Q.front->amount;
	if(amount < 0 && (-1 * amount) > BankBalance && OpenWindows == 0) {	//Q��Q1ʱ�ж���������Ƿ���
		EnQueue_CLQ(Q2);  //ת������2
		Q2.rear->arrtime = Q.front->arrtime;
		Q2.rear->amount = amount; //���׶�-MaxAmount~MaxAmount;
		Q2.rear->durtime = Q.front->durtime;  //����ҵ����Ҫ��ʱ��
		Q2.rear->num = Q.front->num;
		DeQueue_CLQ(Q1);  //����
		if(Q.front) {
			if(Q.front->amount > 0 || -1 * Q.front->amount < BankBalance) {
				FreeTime += Q.front->durtime;  //���¿���ʱ��
			}
		}
		return;
	}
	//���Ͻ�����������������ɽ���
    deal(Q, OpenWindows);
	if(amount > 0 || OpenWindows == 1) {  //�Ӵ���һ���ڶ���ҵ��Ŀͻ����ʹ���������ܣ��ڶ��������еĿͻ�
		if(amount > 0) LastBalance = BankBalance - amount;
		check();
	}
}

//�ͻ�����
void arrive() {
	EnQueue_CLQ(Q1);  //�¿ͻ����
	EnQueue_ELQ(Event);  //�µ����¼����
	
	Q1.rear->arrtime = Event.rear->occutime = CurrentTime;
	Q1.rear->amount = (rand() % (2 * MaxAmount)) - MaxAmount; //���׶�-MaxAmount~MaxAmount
	if(Q1.rear->amount < 0) {
		FirstService++;
	} else {
		SecondService++;
	}
	
	Q1.rear->durtime = (rand() % MaxDealTime) + 1;  //����ҵ����Ҫ��ʱ��
	Q1.rear->num = Event.rear->num = Number;
	Event.rear->type = 2;  //����
	if(Q1.front == Q1.rear && OpenWindows == 0) {
		if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
			FreeTime = CurrentTime + Q1.front->durtime;  //����FreeTime
		}
		
	}
}

//���
void result() {
	if(!Event.front) {
		printf("�밴˳����в�����\n");
	} else {
		printf("	����Ӫҵ�ͻ�������%d\n\n", Number);
		printf("        ����Ӫҵ�����һҵ���������%d���ɹ�ȡ��������%d\n\n", FirstService, FirstSuccess);
		printf("        ����Ӫҵ����ڶ�ҵ���������%d���ɹ����������%d\n\n", SecondService, SecondSuccess);
		printf("	�ͻ��������ڶ�����ƽ��ʱ��:%f��\n\n", float(TotalDurtime) / Number);
		printf("	���е�ǰ��%d\n\n", BankBalance);
		printf("*************************�¼������£�**************************\n");

		while(Event.front) {
		    if(Event.front->type != 2) {
		    	printf("     �ͻ����:%-3d      �¼�����:�Ӵ���%d�뿪      ����ʱ��:%3d\n",
					Event.front->num, Event.front->type, Event.front->occutime);
				printf("---------------------------------------------------------------\n");
			} else {
				printf("     �ͻ����:%-3d      �¼�����:����              ����ʱ��:%3d\n",
					Event.front->num, Event.front->occutime);
				printf("---------------------------------------------------------------\n");
			}
			DeQueue_ELQ(Event);
  	 	}
	}
	//��������
	FirstSuccess = 0;  //�����һҵ��ɹ�������
	SecondSuccess = 0;  //����ڶ�ҵ��ɹ�������
	FirstService = 0;  //�����һҵ�������
	SecondService = 0;  //����ڶ�ҵ�������
	LastBalance = 0;  //��һ�����������һ���ͻ�(�ڶ���ҵ��)���Ӵ�֮ǰ������
	OpenWindows = 0;  //�������Ǽ��Ŵ���Ĭ��0��
	FreeTime = 0;  //����յ�ʱ�䣬��־ʲôʱ�򴰿ڿ���
	IntervalTime = 0;  //������ʱ������
	NextArriveTime = 0;  //��һ������ʱ��
	OpenTime = 0;  //Ӫҵʱ��
	MaxAmount = 0;  //��������
	MaxDealTime = 0;  //����ʱ������
	CurrentTime = 0;  //����ʱ�䣬��ʼΪ0
	Number = 0;  //�ͻ����룬��ʼΪ0
	BankBalance = 0;  //�������
	TotalDurtime = 0;
}

//ģ��
void simulation() {
   	if(BankBalance == 0) {
		printf("���ȳ�ʼ��������\n");
	} else {
		NextArriveTime += (rand() % IntervalTime) + 1;  //��һ���ͻ��ĵ���ʱ��
		printf("***********************�ͻ������£�********************\n");
		while(CurrentTime < OpenTime) {
			CurrentTime++;

			if(CurrentTime == NextArriveTime) {  //��ǰʱ��������һ���ͻ��ĵ���ʱ�䣬����arrive
				arrive();
				printf("�ͻ���ţ�%-3d, ����ʱ�䣺%-3d, ��������ʱ�䣺%-3d, ��%-3d\n", Q1.rear->num, Q1.rear->arrtime, Q1.rear->durtime, Q1.rear->amount);
				printf("-----------------------------------------------------\n");
				Number++;
				NextArriveTime += (rand() % IntervalTime) + 1;
			}

 	  		//��ǰʱ����������ʱ�䣬����deal
 			if(FreeTime <= CurrentTime && OpenWindows == 0 && Q1.front) {
				judge(Q1);
			}
	
			if(FreeTime <= CurrentTime && OpenWindows == 1 && Q2.front){
				judge(Q2);
			}
		}
		if(CurrentTime == OpenTime) close();  //ֹͣӪҵ
		printf("ģ�����������2�鿴�����\n");
	}

}

//��ʼ������
void initial() {
	printf("���������еĳ�ʼ��");
	fflush(stdin);  //������뻺����
    scanf("%d",&BankBalance);
    while(BankBalance <= 0) {
    	printf("���������0��������");
    	fflush(stdin);  //������뻺����
    	scanf("%d",&BankBalance);
	}

	printf("�����뽻�����ޣ�");
	fflush(stdin);  //������뻺����
	scanf("%d",&MaxAmount);
	while(MaxAmount <= 0) {
	    printf("���������0��������");
	    fflush(stdin);  //������뻺����
	    scanf("%d",&MaxAmount);
	}
	
	printf("������Ӫҵʱ�䣺");
	fflush(stdin);  //������뻺����
    scanf("%d",&OpenTime);
    while(OpenTime <= 0 && OpenTime <= 1440) {
       	printf("���������0��С��1440��������");
       	fflush(stdin);  //������뻺����
       	scanf("%d",&OpenTime);
	}
	
	printf("�����뽻�׷���ʱ�����ޣ�0~30�֣���");
	fflush(stdin);  //������뻺����
    scanf("%d",&MaxDealTime);
    while(MaxDealTime <= 0) {
        printf("������1~30��������");
        fflush(stdin);  //������뻺����
        scanf("%d",&MaxDealTime);
	}

	printf("������ͻ�����ʱ�������ޣ�1~15����");
	fflush(stdin);  //������뻺����
   	scanf("%d",&IntervalTime);
    while(MaxDealTime <= 0) {
        printf("���������0��������");
        fflush(stdin);  //������뻺����
        scanf("%d",&IntervalTime);
	}
}

//��ӡ�˵�����
void indexPrint() {
	system("color 03");
	printf("*********************************************\n");
	printf("**           ���繤��2��������             **\n");
	printf("**                                         **\n");
	printf("**              ����ҵ��ģ��               **\n");
    printf("**               0:����ģ���              **\n");
    printf("**               1:��ʼģ��                **\n");
    printf("**               2:��ӡ�¼���              **\n");
    printf("*********************************************\n");
}
