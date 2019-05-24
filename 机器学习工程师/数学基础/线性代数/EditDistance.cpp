// 实现字符串的编辑距离

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

// use dp 
int editDistance(string str1,string str2)
{
    if(str1.size()==0)
        return str2.size();
    if(str2.size()==0)
        return str1.size();
    int len1=str1.size();
    int len2=str2.size();
    vector<vector<int> >dp(len1+1,vector<int>(len2+1,0));
    //先初始化
    for(int i=0;i<=len1;++i){
        dp[i][0]=i;
    }
    for(int j=0;j<=len2;++j){
        dp[0][j]=j;
    }
    for(int i=1;i<=len1;++i){
        for(int j=1;j<=len2;++j){
            int temp=0;
            if(str1[i-1]==str2[j-1])
                temp=1;
            dp[i][j]=max(dp[i-1][j]+1,dp[i][j-1]+1,dp[i-1][j-1]+temp);
        }
    }
    return dp[len1][len2];

}