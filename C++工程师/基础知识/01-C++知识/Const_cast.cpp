//* const_cast 转换 * /

#include <iostream>
using namespace std;
class CCtest{
public:
    void setNumber(int);
    void printNumber() const;                     // the funciton is const,指针指向的变量不能改变

private:
    int number;
};

void CCtest::setNumber(int num){
    number=num;
}

void CCtest::printNumber()const{
    cout<<" the before number:"<<" "<<number<<endl;
    const_cast<CCtest*>(this)->number--;          // 类型转换，将指针转换成为 可改变指向的指针
    cout<<" after cast the number is: "<<number<<endl;
}

int main(int argc,char* const argv[])
{
    CCtest test;
    test.setNumber(8);
    test.printNumber();
    return 0;
}