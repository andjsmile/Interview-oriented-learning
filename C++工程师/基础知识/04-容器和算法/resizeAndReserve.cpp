/* resize 和 reserve 的区别 */
// resize改变元素个数
// reserve改变capacity 存储容量

#include <iostream>
#include <vector>

using namespace std;

int main(int argc,char const* argv[]){
    vector<int> a;

    a.reserve(100);
    a.resize(50);
    cout<<"size less than capacity"<<endl;
    cout<<"size:"<<a.size()<<" "<<"capacity:"<< a.capacity()<<endl;

    cout<<"resize the new size large than capacity"<<endl;
    a.resize(150);
    cout<<"size:"<<a.size()<<" "<<"capacity:"<<a.capacity()<<endl;

    cout<<" reserve the capacity and capacity less than size"<<endl;
    a.reserve(50);                                                      // the capacity not change 
    cout<<"size:"<<a.size()<<" "<<"capacity:"<<a.capacity()<<endl;

    cout<<"change the size "<<endl;
    a.resize(50);
    cout<<"size:"<<a.size()<<" "<<"capacity:"<<a.capacity()<<endl;

    return 0;
}