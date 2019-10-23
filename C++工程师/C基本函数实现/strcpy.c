// strcpy函数的原型
#include<stdio.h>
#include<stdlib.h>
#include<assert.h>
#include<string.h>
char* strcpy(char* dest,char* src);

// 函数实现
/**
 * 检查指针有效性
 * 返回目的指针dest
 * 源字符的末尾'\0'需要拷贝
*/

char* strcpy1(char* dest,char* src){
    assert(dest!=NULL && src!=NULL);
    char* res=dest;
    while((*dest++=*src++)!='\0');

    //while(*src!='\0'){
    //   *dest++=*src++;
    //}
    //dest++;
    //*dest='\0';
    return res;

}

// 返回char *
/***
 *  返回dest的原始值，使函数支持链式表达式
 * */


// 内存重叠
/**
 * src未处理的部分已经被dest覆盖了，
 * src<=dest<=src+strlrn(src)
 * 使用memcpy函数来解决内存重叠问题
 * 
 * strlen()是计算不含终止null的字符串长度
 * sizeof()的计算是包含null字节的缓冲区长度
*/

char* strcpy2(char *dest,char* src){
    assert(dest!=NULL || src!=NULL);
    char* res=dest;
    memcpy(dest,src,strlen(src)+1);  //保留'\0'
    return res;
}

char * my_memcpy(char* dest,char* src,int cnt){
    assert(dest!=NULL || src!=NULL);
    char* res=dest;
    // 内存重叠，高位开始复制
    if(dest>=src && dest<=src+cnt-1){
        dest=dest+cnt-1;
        src=src+cnt-1;
        while(cnt--){
            *dest--=*src--;
        }
    }
    else{
        while(cnt--){
            *dest++=*src++;
        }
    }
    return res;
}