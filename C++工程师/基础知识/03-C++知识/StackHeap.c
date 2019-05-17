// 程序来加深理解

#include <string.h>
#
int a=0;              // 全局初始化
char *p1;             // 全局未初始化
int main()
{
    int b;             // 栈
    char s[]="abc";    // 栈
    char *p2;          // 栈
    char *p3="123456"; // 123456\0在常量区 p3在栈上
    static int c=0;    // 全局静态初始化区
    p1=(char*)malloc(10);
    p2=(char*)malloc(20);   //在堆区进行分配
    strcpy(p1,"123456");
    return 0;
}