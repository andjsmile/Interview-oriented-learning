// 基于右值引用的 转移语义

#include <iostream>
using namespace std;

class HasPtrMem{
public:
    HasPtrMem():d(new int(0)){
        cout<<"Construct:"<<++n_cstr<<endl;
    }
    HasPtrMem(const HasPtrMem &h):d(new int(*h.d)){
        cout<<"copy construct:"<<++n_cptr<<endl;
    }

    HasPtrMem(HasPtrMem &&h):d(h.d){
        h.d=nullptr;
        cout<<"move construct:"<<++n_mvtr<<endl;
    }
    ~HasPtrMem(){
        cout<<"destruct:"<<++n_dstr<<endl;
    }
    int *d;
    static int n_cstr;
    static int n_cptr;
    static int n_dstr;
    static int n_mvtr;

};

int HasPtrMem::n_cstr=0;
int HasPtrMem::n_cptr=0;
int HasPtrMem::n_dstr=0;
int HasPtrMem::n_mvtr=0;

HasPtrMem getTemp()
{
    return HasPtrMem();
}
int main()
{
    HasPtrMem a=getTemp();
}