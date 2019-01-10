#include "main.h"
#include "bank.h"


int main(int argc, char** argv) {
	int num;  //操作序号
	srand((unsigned)time(NULL));
	while(1){
 		indexPrint();
  		printf("请输入操作序号：");
  		scanf("%d", &num);
        fflush(stdin);  //清空输入缓冲区

		if(num == 0) initial();  //初始化参数
		
		if(num == 1) simulation();  //模拟
		
		if(num == 2) result();  //查看结果
        system("PAUSE"); //冻结屏幕
        system("CLS"); //清屏操作
   }
	return 0;
}
