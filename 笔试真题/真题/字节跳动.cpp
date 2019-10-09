#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main{
    
    int n;
    cin>>n;
    
    vector<int>nums(n,0);
    for(int i=0;i<n;++i)
    {
        cin>>nums[i];
    }
    vector<int>dp(n,0);
    for(int i=0;i<n-1;++i)
    {
        int j=i+1;
        int count=0;
        while(j<n)
        {
                //count=0;
            if(nums[j]>nums[i])
            {
                dp[i]=0;
                break;
            }
            else if(nums[j]<nums[j+1] && num[j+1]<nums[i] &&j<n-1)
            {
                count++;
                j++;
            }
            else 
            {
                count=1;
                break;
            }
        }
        dp[i]=count;
    }
    int max_time=dp[0]；
    for(int i=0;i<n;++i)
    {
        if(dp[i]>max_time)
            max_time=dp[i];
    }
    int index=0;
    for(int i=0;i<n;++i)
    {
        if(dp[i]==max_time)
        {
            index=i;
            break;
        }
    }
    cout<<nums[index]<<endl;
    
    return 0;
}