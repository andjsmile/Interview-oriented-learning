## string、int 常见类型之间相互转换

### int & string 之间的转换

- C++中更多的是使用流对象来实现类型转换

**针对流对象 sstream实现**

int,float 类型都可以实现
```cpp
#include <sstream>
//int convert to string
void int2str(const int &int_temp,string &string_temp){
    stringstream s_stream;
    s_stream<<int_temp;
    string_temp=s_stream.str();
    //s_stream>>string_temp;  // 也可以实现
}

//string convert to int
void str2int(const string &string_temp,int &int_temp){
    stringstream s_stream(string_temp);
    s_stream>>int_temp;  
}
```

- 其他的方法

**c_str()函数**

string.c_str() 可以将string字符串转换成一个指向与string相同字符数组的头指针 char* []数组

```cpp
// atoi
void str2int(string &string_temp,int &int_temp){
    int_temp=atoi(string_temp.c_str());
}

// stoi实现
void str2int_stoi_version(string& string_temp,int &int_temp){
    int_temp=stoi(string_temp);
}
```


### 字符数组char* 与string之间的转换

- 字符数组转为string

```cpp
char ch [] = "ABCDEFGHIJKL";
string str(ch);//也可string str = ch;

// other way

char ch [] = "ABCDEFGHIJKL";
string str;
str = ch;//在原有基础上添加可以用str += ch;
```

- string转为字符数组

```cpp
char buf[8];
string str("ABCDEFG");
length = str.copy(buf,8);     //str.copy() return number of character copied
buf[length] = '\0';   //末尾置0


char buf[8];
string str("ABCDEFG");
strcpy(buf, str.c_str());//strncpy(buf, str.c_str(), 8);
```

**strcpy()函数**


```cpp
//char* strcpy( char* dest, const char* src );
#include <iostream>
#include <cstring>
#include <memory>
int main()
{
    const char* src = "Take the test.";
//  src[0] = 'M'; // can't modify string literal
    auto dst = std::make_unique<char[]>(std::strlen(src)+1); // +1 for the null terminator
    std::strcpy(dst.get(), src);
    dst[0] = 'M';
    std::cout << src << '\n' << dst.get() << '\n';
}
// Take the test.
// Make the test.
```
**strncpy()函数**
```cpp
// char *strncpy( char *dest, const char *src, std::size_t count );

#include <iostream>
#include <cstring>
int main()
{
    const char* src = "hi";
    char dest[6] = {'a', 'b', 'c', 'd', 'e', 'f'};
    std::strncpy(dest, src, 5);
 
    std::cout << "The contents of dest are: ";
    for (char c : dest) {
        if (c) {
            std::cout << c << ' ';
        } else {
            std::cout << "\\0" << ' ';
        }
    }
    std::cout << '\n';
}
//The contents of dest are: h i \0 \0 \0 f
```


### int 和 char
```cpp
int a=1;
char c=a+'0'; //c的值就是'1'的ASCII码值
```



