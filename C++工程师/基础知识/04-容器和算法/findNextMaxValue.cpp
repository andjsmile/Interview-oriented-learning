#include <vector>
#include <stack>
#include <iostream>
#include <climits>                                // get the INT_MAX
using namespace std;

vector<int> findMax(vector<int> num){
    if(num.size()==0) return num;  
    vector<int> res(num.size());
    int i=0;
    stack<int> s;
    while(i<num.size()){
        if(s.empty()|| num[s.top()]>=num[i]){
            s.push(i++);                          // store the index
        }
        else{
            res[s.top()]=num[i];                  
            s.pop();
        }
    }
    while(!s.empty()){                            // the maxvalue next is int_max
        res[s.top()]=INT_MAX;
        s.pop();
    }
    for(int i=0;i<res.size();i++)
        cout<<res[i]<<endl;
    return res;
}