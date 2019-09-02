// 打印从1到最大的n位数
// 当n=3 ，1～999；当n=5 ，1～99999
// 很容易犯的错误就是 long long 也无法表示这些大数
// 只能使用字符来处理
// 1.用字符串来模拟数字的加法-复杂
// 2.使用递归 数字全排列来实现
//

#include <iostream>
#include <cstdlib>
using namespace std;

void print1ToMaxNDigits(int n)
{
    if(n<=0) return;
    char *number=new char[n+1];   // need destory
    number[n]='\0';
    
    for(int i=0;i<10;++i)
    {
        number[0]='0'+i;
        printNDigits(number,n,0);
    }
    delete[] number;
}

void printNDigits(char *number,int length,int index)
{
    if(index==length-1)
    {
        printNumber(number);
        return;
    }
    for(int i=0;i<10;++i)
    {
        number[index+1]='0'+i;
        printNDigits(number,lenght,index+1);
    }

}

// 从第一个非0元素开始打印
void printNumber(char *number)
{
    bool isBegin0=true;
    int length=strlen(number);
    for(int i=0;i<length;++i)
    {
        if(isBegin0 && number[i]!='0')
            isBegin0=false;
        if(!isBegin0)
        {
            printf("%c",number[i])''
        }
    }
    printf("\t");
}
