/* 实现函数可以在main函数运行前运行 */
#include <cstdio>
__attribute((constructor)) void before_main()
{
    printf("%s\n",__FUNCTION__);
}

/* 实现函数可以在main函数运行后运行 */
__attribute((destructor)) void after_main()
{
    printf("%s\n",__FUNCTION__);
}

int main(int argc,char const *argv[]){
    printf("%s\n",__FUNCTION__);
    return 0;
}