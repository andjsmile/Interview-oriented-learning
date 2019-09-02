#include<iostream>
#include<vector>
#include<string>
#include<cstdlib>
using namespace std;

string change_num(string &str);
string num2string(string &str);

int main()
{
    int num;
    while(cin>>num){
        if(num==0)
            cout<<"零"<<endl;
        else
        {
            /* code */
            string str_num=std::to_string(num);   //直接把00123这种前面的0去掉了
            // 方法一
            string res=change_num(str_num);
        

            // 方法二
            string res2=num2string(str_num);
            cout<<res<<endl;
            cout<<res2<<endl;
        }
        
        
    }
    
    return 0;
}
// 这个方法实现不完整
string change_num(string &str)
{
    /*运行时将下面两行注释去掉，c++11支持列表初始化*/
    //vector<string> number={"零","一","二","三","四","五","六","七","八","九"};
    //vector<string> unit={"十","百","千","万","十","百","千","亿","十","百","千"};
    vector<string> number;
    vector<string> unit;

    string res="";
    int len=str.size();
    for(int i=0;i<len;++i)
    {
        int num=str[i]-'0';
        if(i!=len-1 && num!=0)
        {
            res+=(number[num]+unit[len-2-i]);
        }
        else
        {
            res+=number[num];
        }
        
    }
    return res;
}

// 完整的实现方法
string num2string(string &str)
{
    //vector<string> number={"零","一","二","三","四","五","六","七","八","九"};
    //vector<string> unit={"","十","百","千","万","十万","百万","千万","亿"};
    vector<string> number;
    vector<int>unit;
    string res;
    int len=str.size();
    for(int i=0;i<len;++i){
        int num=str[i]-'0';
        int index=len-1-i;
        if(num==0){
            if((str[i+1] =='0'&& i<len-1) || i==len-1){  // 30003、230这样多余的零
                continue;
            }
            else if(index>=4 && index%4==0)     //处理2103243在万位出现多余的零
            {
                res+=unit[index];
            }
            else
                res+=number[num];         // 正常的零
            

        }
        else
        {
            res+=number[num];
            if(len==2 && str[0]=='1' && i==0)
            {
                res.erase(0);     // 12 为十二   不是 一十二
            }
            if(index%4==0)
                res+=unit[index];   //万、亿位的要输出
            else
            {
                res+=unit[index%4]; //"十万","百万","千万"其实就是一摆设，万不输出
            }
            
        }
        
    }
    return res;
}