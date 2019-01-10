#include "main.h"
#include "bank.h"
CLQueue Q1;  //队列1
CLQueue Q2;  //队列2
ELQueue Event;  //事件表

int FirstSuccess;  //办理第一业务成功的人数
int SecondSuccess;  //办理第二业务成功的人数
int FirstService;  //办理第一业务的人数
int SecondService;  //办理第二业务的人数
int LastBalance;  //第一个队列中最后一个客户(第二种业务)被接待之前的数额
int OpenWindows = 0;  //开启的是几号窗，默认0号
int FreeTime = 0;  //服务空挡时间，标志什么时候窗口空闲
int IntervalTime;  //到达间隔时间上限
int NextArriveTime = 0;  //下一个到达时间
int OpenTime;  //营业时间
int MaxAmount;  //交易上限
int MaxDealTime;  //交易时间上限
int CurrentTime = 0;  //现在时间，初始为0
int Number = 0;  //客户编码，初始为0
int BankBalance = 0;  //银行余额
int TotalDurtime = 0;  //总逗留时间

//停止营业
void close() {
	while(Q1.front) {
		EnQueue_ELQ(Event);  //新事件入队
		Event.rear->occutime = CurrentTime;
		Event.rear->num = Q1.front->num;
		Event.rear->type = 0;  //从0号窗口离开
		DeQueue_CLQ(Q1);
	}
	while(Q2.front) {
		EnQueue_ELQ(Event);  //新离开事件入队
		Event.rear->occutime = CurrentTime;
		TotalDurtime += CurrentTime - Q2.rear->arrtime;
		Event.rear->num = Q2.front->num;
		Event.rear->type = 1;  //从1号窗口离开
		DeQueue_CLQ(Q2);  //出队
	}
}

//交易
void deal(CLQueue &Q, int windows) {
	int amount = Q.front->amount;
	int durtime = Q.front->durtime;
	BankBalance += amount;	//符合交易条件可以立即完成交易
	if(amount < 0) {
		FirstSuccess++;
	} else {
		SecondSuccess++;
	}
	TotalDurtime += CurrentTime - Q.front->arrtime;  //更新总逗留时间
	EnQueue_ELQ(Event);  //新事件入队
	Event.rear->occutime = CurrentTime;  
	Event.rear->num = Q.front->num;
	Event.rear->type = windows;  //从几号窗口离开
	DeQueue_CLQ(Q);
	if(Q.front && OpenWindows == 0) {
		if(Q.front->amount > 0 || -1 * Q.front->amount < BankBalance) {
			FreeTime += Q.front->durtime;  //更新空闲时间
		}
	} 
}

//检查第二个队列
void check() {  
	Client p, sign;
	if(!Q2.front && OpenWindows == 1) {
		OpenWindows = 0;  //第二个队列没有客户，关闭1窗开启0窗
		if(Q1.front) {
        	if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
				FreeTime += Q1.front->durtime;  //更新空闲时间
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
        			FreeTime -= Q1.front->durtime;  //先不处理队列1的业务
				}
			} 
			if(Q2.front) FreeTime += p->durtime;  //更新空闲时间
			OpenWindows = 1; //关闭0号窗口，开启1号窗口
			break;
		} else {
			Q2.rear->next = p;  //不符合要求重新排到队尾
			Q2.rear = p;
			Q2.front = p->next;
			Q2.rear->next = NULL;
		}
		p = Q2.front;
		if(p == sign) {
			OpenWindows = 0;
			break;   //者检查了一遍
		}
	}
	if(BankBalance <= LastBalance) {
		OpenWindows = 0;  //第二个队列没有客户符合条件，关闭1窗开启0窗
        if(Q1.front) {
        	if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
				FreeTime += Q1.front->durtime;  //更新空闲时间
			}
		}
	} 
}

//判断下一步怎么处理
void judge(CLQueue &Q) {
	int amount = Q.front->amount;
	if(amount < 0 && (-1 * amount) > BankBalance && OpenWindows == 0) {	//Q是Q1时判断银行余额是否不足
		EnQueue_CLQ(Q2);  //转到队列2
		Q2.rear->arrtime = Q.front->arrtime;
		Q2.rear->amount = amount; //交易额-MaxAmount~MaxAmount;
		Q2.rear->durtime = Q.front->durtime;  //处理业务需要的时间
		Q2.rear->num = Q.front->num;
		DeQueue_CLQ(Q1);  //出队
		if(Q.front) {
			if(Q.front->amount > 0 || -1 * Q.front->amount < BankBalance) {
				FreeTime += Q.front->durtime;  //更新空闲时间
			}
		}
		return;
	}
	//符合交易条件可以立即完成交易
    deal(Q, OpenWindows);
	if(amount > 0 || OpenWindows == 1) {  //接待完一个第二种业务的客户检查和处理（如果可能）第二个队列中的客户
		if(amount > 0) LastBalance = BankBalance - amount;
		check();
	}
}

//客户到达
void arrive() {
	EnQueue_CLQ(Q1);  //新客户入队
	EnQueue_ELQ(Event);  //新到达事件入队
	
	Q1.rear->arrtime = Event.rear->occutime = CurrentTime;
	Q1.rear->amount = (rand() % (2 * MaxAmount)) - MaxAmount; //交易额-MaxAmount~MaxAmount
	if(Q1.rear->amount < 0) {
		FirstService++;
	} else {
		SecondService++;
	}
	
	Q1.rear->durtime = (rand() % MaxDealTime) + 1;  //处理业务需要的时间
	Q1.rear->num = Event.rear->num = Number;
	Event.rear->type = 2;  //到达
	if(Q1.front == Q1.rear && OpenWindows == 0) {
		if(Q1.front->amount > 0 || -1 * Q1.front->amount < BankBalance) {
			FreeTime = CurrentTime + Q1.front->durtime;  //更新FreeTime
		}
		
	}
}

//结果
void result() {
	if(!Event.front) {
		printf("请按顺序进行操作。\n");
	} else {
		printf("	本次营业客户人数：%d\n\n", Number);
		printf("        本次营业办理第一业务的人数：%d，成功取款人数：%d\n\n", FirstService, FirstSuccess);
		printf("        本次营业办理第二业务的人数：%d，成功存款人数：%d\n\n", SecondService, SecondSuccess);
		printf("	客户在银行内逗留的平均时间:%f分\n\n", float(TotalDurtime) / Number);
		printf("	银行当前余额：%d\n\n", BankBalance);
		printf("*************************事件表如下：**************************\n");

		while(Event.front) {
		    if(Event.front->type != 2) {
		    	printf("     客户序号:%-3d      事件类型:从窗口%d离开      发生时间:%3d\n",
					Event.front->num, Event.front->type, Event.front->occutime);
				printf("---------------------------------------------------------------\n");
			} else {
				printf("     客户序号:%-3d      事件类型:到达              发生时间:%3d\n",
					Event.front->num, Event.front->occutime);
				printf("---------------------------------------------------------------\n");
			}
			DeQueue_ELQ(Event);
  	 	}
	}
	//参数清零
	FirstSuccess = 0;  //办理第一业务成功的人数
	SecondSuccess = 0;  //办理第二业务成功的人数
	FirstService = 0;  //办理第一业务的人数
	SecondService = 0;  //办理第二业务的人数
	LastBalance = 0;  //第一个队列中最后一个客户(第二种业务)被接待之前的数额
	OpenWindows = 0;  //开启的是几号窗，默认0号
	FreeTime = 0;  //服务空挡时间，标志什么时候窗口空闲
	IntervalTime = 0;  //到达间隔时间上限
	NextArriveTime = 0;  //下一个到达时间
	OpenTime = 0;  //营业时间
	MaxAmount = 0;  //交易上限
	MaxDealTime = 0;  //交易时间上限
	CurrentTime = 0;  //现在时间，初始为0
	Number = 0;  //客户编码，初始为0
	BankBalance = 0;  //银行余额
	TotalDurtime = 0;
}

//模拟
void simulation() {
   	if(BankBalance == 0) {
		printf("请先初始化参数。\n");
	} else {
		NextArriveTime += (rand() % IntervalTime) + 1;  //第一个客户的到达时间
		printf("***********************客户表如下：********************\n");
		while(CurrentTime < OpenTime) {
			CurrentTime++;

			if(CurrentTime == NextArriveTime) {  //当前时间来到下一个客户的到达时间，调用arrive
				arrive();
				printf("客户序号：%-3d, 到达时间：%-3d, 交易所需时间：%-3d, 金额：%-3d\n", Q1.rear->num, Q1.rear->arrtime, Q1.rear->durtime, Q1.rear->amount);
				printf("-----------------------------------------------------\n");
				Number++;
				NextArriveTime += (rand() % IntervalTime) + 1;
			}

 	  		//当前时间来到空闲时间，调用deal
 			if(FreeTime <= CurrentTime && OpenWindows == 0 && Q1.front) {
				judge(Q1);
			}
	
			if(FreeTime <= CurrentTime && OpenWindows == 1 && Q2.front){
				judge(Q2);
			}
		}
		if(CurrentTime == OpenTime) close();  //停止营业
		printf("模拟结束，输入2查看结果。\n");
	}

}

//初始化参数
void initial() {
	printf("请输入银行的初始存款：");
	fflush(stdin);  //清空输入缓冲区
    scanf("%d",&BankBalance);
    while(BankBalance <= 0) {
    	printf("请输入大于0的整数：");
    	fflush(stdin);  //清空输入缓冲区
    	scanf("%d",&BankBalance);
	}

	printf("请输入交易上限：");
	fflush(stdin);  //清空输入缓冲区
	scanf("%d",&MaxAmount);
	while(MaxAmount <= 0) {
	    printf("请输入大于0的整数：");
	    fflush(stdin);  //清空输入缓冲区
	    scanf("%d",&MaxAmount);
	}
	
	printf("请输入营业时间：");
	fflush(stdin);  //清空输入缓冲区
    scanf("%d",&OpenTime);
    while(OpenTime <= 0 && OpenTime <= 1440) {
       	printf("请输入大于0且小于1440的整数：");
       	fflush(stdin);  //清空输入缓冲区
       	scanf("%d",&OpenTime);
	}
	
	printf("请输入交易服务时间上限（0~30分）：");
	fflush(stdin);  //清空输入缓冲区
    scanf("%d",&MaxDealTime);
    while(MaxDealTime <= 0) {
        printf("请输入1~30的整数：");
        fflush(stdin);  //清空输入缓冲区
        scanf("%d",&MaxDealTime);
	}

	printf("请输入客户到达时间间隔上限（1~15）：");
	fflush(stdin);  //清空输入缓冲区
   	scanf("%d",&IntervalTime);
    while(MaxDealTime <= 0) {
        printf("请输入大于0的整数：");
        fflush(stdin);  //清空输入缓冲区
        scanf("%d",&IntervalTime);
	}
}

//打印菜单界面
void indexPrint() {
	system("color 03");
	printf("*********************************************\n");
	printf("**           网络工程2班李清林             **\n");
	printf("**                                         **\n");
	printf("**              银行业务模拟               **\n");
    printf("**               0:设置模拟参              **\n");
    printf("**               1:开始模拟                **\n");
    printf("**               2:打印事件表              **\n");
    printf("*********************************************\n");
}
