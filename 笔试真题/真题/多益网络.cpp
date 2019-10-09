/**
 * 3sum 求三数之和
*/

#include <vector>
#include <iostream>
#include <string>
#include <set>
using namespace std;

int main()
{
    int n;
    while(cin>>n)
    {
        vector<int> nums(n,0);
        for(int i=0;i<n;++i)
        {
            cin>>nums[i];
        }
        // 
        vector<vector<int> >res;
        if(n<3){
            cout<<res<<endl;
        }
        set<vector<int> >no_res;
        sort(nums.begin(),nums.end());
        for(int i=0;i<n-2;++i){
            if(i>0 && nums[i]==nums[i-1])
                continue;
            int start=i+1;
            int end=n-1;
            while(start<end)
            {
                int sum=nums[i]+nums[start]+nums[end];
                if(sum==0)
                {
                    vector<int>cur_res={nums[i],nums[start],nums[end]};
                    no_res.insert(cur_res);
                    start++;
                    end--;

                }
                else if(sum>0)
                {
                    end--;
                }
                else
                {
                    start++;
                }
                
            }

        }
        for(auto t:no_res)
        {
            res.push_back(t);
        }
        cout<<res<<endl;  
    }

}

