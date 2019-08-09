#include <iostream>
#include <string>
#include <stack>
#include <vector>
using namespace std;

bool correctOrder(string str1,string str2)
{
    if(str1.size()==0 || str2.size()!=str1.size())
        return false;
    stack<char> s;
    int j=0;
    for(int i=0;i<str1.size();i++){
        s.push(str1[i]);
        while(!s.empty() && s.top()==str2[j]){
            s.pop();
            j++;
        }
    }
    return s.empty();
}

int main(int argc,char const *argv[]){
    int T;
    cin>>T;
    vector<string> string_arr(2*T,"");
    for(int i=0;i<2*T;i++){
        string s;
        cin>>s;
        string_arr[i]=s;
    }
    for(int i=0;i<2*T-1;i+=2){
        if(correctOrder(string_arr[i],string_arr[i+1]))
        {
            cout<<"Y";
        }
        cout<<endl;
    }

    return 0;
}