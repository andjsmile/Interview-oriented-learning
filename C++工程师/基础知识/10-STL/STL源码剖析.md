

### 1.STL

1.STL六大组件

彼此可以组合套用

- 1.容器 containers  ,vector、list、deque、set、map等  STL容器像是 class template

- 2.算法 algorithms  ,sort、find、search      像是函数模版  function template

- 3.迭代器 iterator  ,容器和算法之间的胶合剂

- 4.仿函数 functors  ,行为类似函数，作为算法的某种策略

- 5.配接器 adapter   ,用来修饰容器，仿函数，迭代器接口的东西

- 6.配置器 allocator ,负责空间配置和管理

2.静态常量整数成员在class内部直接初始化


3.STL中迭代器所标识的区间都是，都是前闭后开区间


### 2.空间配置器

// c++内存配置操作
// 配置内存，构造对象
// 析构对象，释放内存

// 配置内存 alloc::allocator,释放内存 alloc::deallocate
// 对象构造 constructor      析构对象 destory()

STL定义五个全局函数,作用于未初始化的空间上

construct(),destroy(),uninitialized_copy(),uninitialized_fill(),uninitialized_fill_n()



### 3.迭代器 Iterator ，traits编程技法

迭代器:提供一种方法，可以依序遍历容器所含的各个元素，而又无需暴露内部的表述方式


迭代器是一种 smart pointer

auto_ptr 已经被 unique_ptr向下兼容    使用的时候使用unique_ptr


迭代器的响应类型别

特性萃取机

```cpp
template<class I>
struct iterator_traits
{
    typedef typename I::value_type value_type;
    typedef typename I::pointer    pointer;
    typedef typename I::reference  reference;
    typedef typename I::difference_type difference_type;
    typedef typename I::iterator_category iterator_category;

}
```

### 4.容器

