### 系统的 System.arraycopy

[System.arrycopy方法详解](https://segmentfault.com/a/1190000009922279)
数组的四种复制方法

- 1.clone
- 2.for
- 3.System.arraycopy
- 4.arrays.copyof


```java
/*
** src - 源数组
** srcPos 源数组的起始位置
** dest  目标数组
** destPos 目标数组的起始位置
** length  复制长度
*/
public static void arraycopy(Object src,int srcPos,Object dest,int destPos,int length)
```