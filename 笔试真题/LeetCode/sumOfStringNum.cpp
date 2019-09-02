// 累加字符串中的数字
// 考虑字符串的正负号
//

#include <iostream>
#include <cstdio>
#include <memory>
#include <vector>
using namespace std;

int str2int(char* str,int index,int end);
int sumDigtis(char *str,int len)
{
    int  i=0;
    int sum=0;
    while(i<len)
    {
        int count_sign=0;
        while(str[i]=='-')
        {
            count_sign++;
            i++;
        }
        int index=i;
        while(str[i]>'0' && str[i]<'9')
            i++;
        int sub_sum=str2int(str,index,i);

        if(count_sign%2==0)
            sum+=sub_sum;
        else if(count_sign%2==1)
            sum-=sub_sum;
        i++;
    }
    return sum;
}

int str2int(char* str,int index,int end);
{
    char *sub_str;
    for(int i=index;i<=end;++i)
        sub_str+=str[i];
    int num=std::atoi(sub_str);
    return num;
}
