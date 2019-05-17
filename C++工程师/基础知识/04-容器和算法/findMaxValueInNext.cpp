/* 对于一个无序的数组，找到每个元素后面比它大的第一个元素 
   时间复杂度为 O(N)
*/

#include <vector>
#include <stack>
#include <iostream>
using namespace std;

vector<int> findMax(vector<int> num){
    if(num.size()==0){
        return num;
    }   
    vector<int> res(num.size());
    int i=0;
    stack<int> s;
    while(i<num.size()){
        if(s.empty()|| num[s.top()]>=num[i]){
            s.push(i++);                // store the index
        }
        else{
            res[s.top()]=num[i];
            s.pop();
        }
    }
}
