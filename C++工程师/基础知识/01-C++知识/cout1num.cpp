/*======== 计算一个数中 1的个数 =============**/

#include <iostream>
#include <cmath>
using namespace std;
/* 对输入的要求比较高 */
int count1num(int num){
    int count=0;
    while(num>0){
        if(num%10 == 1){
            count++;
        }
        num/=10;
    }
    return count;
}

int main()
{
    int num=121;
    int res=count1num(num);
    cout<<num<<" have "<<res<<"nums of 1"<<endl;
}