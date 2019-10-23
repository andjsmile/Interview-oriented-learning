#include <iostream>
#include <vector>
#include <deque>
using namespace std;

vector<int> getMaxWindow(vector<int>arr,int w){
    vector<int>res;
    if(arr.size()<0 || w<1 || arr.size()<w){
        return res;
    }
    deque<int> dq;
    for(int i=0;i<arr.size();++i){
        while(!dq.empty() && arr[dq.back()]<=arr[i]){
            dq.pop_back();
        }
        dq.push_back(i);
        if(i - dq.front() == w){
            dq.pop_front();
        }
        if(i>=w-1){
            res.push_back(arr[dq.front()]);
        }
    }
    return res;


}

void printArr(vector<int>res){
    for(int i=0;i<res.size();++i){
        cout<<res[i]<<" ";
    }
    cout<<endl;
}
int main(){
    int arr[]={4,3,5,4,3,3,6,7};
    vector<int> varr(arr,arr+8);
    printArr(varr);
    int w=3;
    vector<int> res=getMaxWindow(varr,w);
    printArr(res);
    return 0;
}