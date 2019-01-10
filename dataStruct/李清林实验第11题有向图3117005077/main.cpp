#include "main.h"

int main(int argc, char *argv[]) {
	int num;  //操作序号
	while(1){
 		IndexPrint();
  		printf("请输入操作序号：");
  		scanf("%d", &num);
        fflush(stdin);  //清空输入缓冲区
        
    	if(num == 0) {
    		ToDestroy(); //销毁图
			exit(-1); //退出
		} 
        if(num == 1) ToCreateDG(); //封装数据准备进行创建有向图操作
		if(num == 2) ToPrint(); //打印有向图
		if(num == 3) ToDestroy(); //销毁图
		if(num == 4) ToGetVex(); //取图G的k顶点的值并打印
		if(num == 5) ToPutVex(); //对图G的k顶点赋值w
		if(num == 6) ToFirstAdjVex(); //输出第k个顶点的第一个邻接顶点
		if(num == 7) ToAddArc(); //在图G中增加k顶点到m顶点的边或弧
		if(num == 8) ToDelete(); //在图G中删除k顶点到m顶点的边或弧
		if(num == 9) ToDFS(); //深度遍历
		if(num == 10) ToBFS(); //广度遍历
		
        system("PAUSE"); //冻结屏幕
        system("CLS"); //清屏操作
   }
}
