#ifndef MAIN_H_INCLUDED
#define MAIN_H_INCLUDED

#include <iostream>
#include <stdlib.h>
#include <stdio.h>
#include <time.h>
#define ERROR       -1                    //错误
#define OK          0                     //正确
#define OVERFLOW    -2                    //溢出

typedef int Status;

void close();//停止营业
void check(int lastBalance);  //检查第二个队列
void judge();  //处理第一个队列的客户业务
void arrive();  //客户到达
void result();//结果
void simulation();//模拟
void initial();  //初始化参数
void indexPrint(); //打印开始界面
#endif
