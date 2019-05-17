#include <iostream>
#include <cmath>
using namespace std;

//#define ABS_FLOAT_0  0.0001
const float ABS_FLOAT_0= 0.0001;

/*数组不够，结构定义来凑*/
struct TriaPoint{
    float x;
    float y;
};


/* 面积计算函数 */
float getTriangleArea(TriaPoint p0,TriaPoint p1,TriaPoint p2){
    TriaPoint AB,BC;
    AB.x=p1.x - p0.x;
    AB.y=p1.y - p0.y;

    BC.x=p2.x - p1.x;
    BC.y=p2.y - p1.x;

    return fabs((AB.x * BC.y - AB.y * BC.x))/2.0f;    // 三角形面积计算公式
}

/* 点在边上*/
// input  四个点 //
bool isInTriangle(const TriaPoint A,const TriaPoint B,const TriaPoint C,const TriaPoint D){
    float Sabc,Sabd,Sacd,Sbcd;
    Sabc=getTriangleArea(A,B,C);
    Sabd=getTriangleArea(A,B,D);
    Sacd=getTriangleArea(A,C,D);
    Sbcd=getTriangleArea(B,C,D);

    float sum_s=Sabd + Sacd + Sbcd;
    if( fabs(sum_s - Sabc)< ABS_FLOAT_0){
        return true;
    }
    else
        return false;
}