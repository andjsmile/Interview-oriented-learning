/* 智能指针的一些定义理解和实现 
 * 2019.04.25
 * dengshuo 
 * */

#include <iostream>
#include <memory>
using namespace std;

// define the class
class Child;

class Parent{
private:
    shared_ptr<Child> ChildPtr;
public:
    void setChild(shared_ptr<Child> child){
        this->ChildPtr=child;
    }
    void doSomething()
    {
        if(this->ChildPtr.use_count()){

        }
    }
    ~Parent();                  // destructor
};

class Child{
private:
    shared_ptr<Parent> ParentPtr;

public:
    void setParent(shared_ptr<Parent> parent){
        this->ParentPtr=parent;
    }
    void doSomething(){
        if(this->ParentPtr.use_count()){

        }
    }
    ~Child();
};

/* run error  - the smart pointer reference each other*/
int main(){
    weak_ptr<Parent> wpp;
    weak_ptr<Child> wpc;
    {
        shared_ptr<Parent> p(new Parent);
        shared_ptr<Child> c(new Child);
        p->setChild(c);
        c->setParent(p);

        wpp=p;
        wpc=c;

        cout<<p.use_count()<<endl;
        cout<<c.use_count()<<endl;
    }
    cout<<wpp.use_count()<<endl;
    cout<<wpc.use_count()<<endl;

    return 0;
}