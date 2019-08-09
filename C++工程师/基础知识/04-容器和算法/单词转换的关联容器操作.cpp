
/*
 * map的创建，搜索以及遍历
 * 一个文件保存字符转换规则
 * 另一个文件是需要转换的文本
 */
#include<iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <map>
using namespace std;

map<string,string> bulidMap(ifstream &map_flie)
{
    string key;
    string value;
    map<string,string> trans_map;

    while(map_flie >>key && getline(map_flie,value))
    {
        if(value.size()>1)
        {
            trans_map[key]=value.substr(1);  // 跳过空格
        }
        else
        {
            throw runtime_error("no ruler for"+key);
        }
        
    }
    return trans_map;
}

const string& transform(const string &word,map<string,string> &trans_map)
{
    auto map_it=trans_map.find(word);
    if(map_it!=trans_map.cend())
    {
        return map_it->second;
    }
    else
    {
        return word;
    }
    
}

void wordTransForm(ifstream &map_file,ifstream &input)
{
    auto trans_map=bulidMap(map_file);      // 创建map表
    string text;
    while (getline(input,text))             // 按行读取
    {
        istringstream stream(text);
        string word;                        // 读取每个单词

        bool first_word=true;               // 控制空格打印
        while(stream>>word)
        {
            if(first_word){
                first_word=false;
            }  
            else
            {
                cout<<" ";
            }
            cout<<transform(word,trans_map);
            
        }
        cout<<endl;
    }
    
}