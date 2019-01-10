#ifndef MAIN_H_INCLUDED
#define MAIN_H_INCLUDED

#include <stdio.h>
#include <stdlib.h>
#define UNVISITED   0                     //还没被访问
#define VISITED     1                     //被访问过了
#define INFINITY    MAXINT                //计算机允许的整数最大值，即∞
#define ERROR       -1                    //错误
#define OK          0                     //正确
#define OVERFLOW    -2                    //溢出
typedef int Status;

//菜单界面
void IndexPrint();

//封装数据准备进行创建有向图操作
void ToCreateDG();

//打印有向图
void ToPrint();

//销毁图
void ToDestroy();

//取图G的k顶点的值并打印
void ToGetVex();

//对图G的k顶点赋值w
void ToPutVex();

//输出第k个顶点的第一个邻接顶点
void ToFirstAdjVex();

//在图G中增加k顶点到m顶点的边或弧
void ToAddArc();

//在图G中删除k顶点到m顶点的边或弧
void ToDelete();

//深度遍历
void ToDFS();

//广度遍历
void ToBFS();

#endif
