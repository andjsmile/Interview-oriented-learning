## 学习MySQL

[在线练习网站](http://sqlfiddle.com/#!9/5aa9aa/1)
### 基础知识-数据库与SQL

数据库(database):某种有组织的方式存储的数据集合

数据库软件(DBMS也叫数据库管理系统)



#### 1.基础语句

保证sql语句的快速运行,关键词保证位置

SELECT>FROM>WHREE>GROUP BY >HAVING>OEDER BY>LIMIT

**DISTINCT**
去除列中的重复值，只返回不同的值

**LIMIT**

注意格式上的区分

limit m,n 是从第m行开始的n行

列元素的行也是从0开始计数

第一个元素是limit 0,1;
第二个元素是limit 1,1;

limit 4 offset 3  从行3开始取4行

**ORDER BY**

在进行多列排序的时候,按照排列的顺序

DESC ,ASC 必须跟在每个要排序列的后面

**WHERE**

= 等于，!= 不等于
BETWEEN x AND y 在指定两个值(x,y)之间
IS NULL 进行空值检查  null 是无值(no value)

在where语句中，自己拿不准where中语句的执行顺序，或者是有歧义的语句
使用括号()，明确的分组操作符，消除歧义


- IN
类似于OR操作符,且执行的操作更快

- NOT 
用来否定它之后的任何条件

**LIKE**

使用通配符(wlidcard)来进行通配搜索，数据进行复杂过滤
主要特点:是可以对未知值进行搜索过滤

mysql的默认搜索方式可以区分大小写的  
'jet%' - 以jet开头的单词
'%anvil%'- 任何包含文本anvil的单词
's%e'  - s起头，e结尾的所有产品

- % 可以匹配0，1，或者多个字符
- _ 只匹配单个字符

**like vs regexp**
LIKE 匹配整个列，被匹配的文本在列值中出现，LIKE将不会找到它，相应的行也不会被返回
REGEXP 在队列值内进行匹配，匹配的文本在列值中出现，将会找到它,相应的行也会被返回

**正则表达式**

REGEXP

- * ,0个或者多个匹配
- ? ,0或1个匹配
- + ,一个或多个匹配
- {n} ,指定数目的匹配
- {n,} ,不少于指定数目的匹配
- {n,m} ,匹配数组的范围


定义符号

- ^ ,文本的开始
- $ ,文本的结尾


**计算字段**

字段field基本上与 列 column的意思相同

- 拼接字段  
mysql中多使用 concat()函数来实现(各元素要用逗号进行分割),
多数的DBMS使用+,||来实现拼接
```sql
SELECT Concat(vend_name,'(',vend_country,')')
FROM vendors
ORDER BY vend_name;
```


- 去空格函数

RTrim(),去掉元素右侧的空格
LTrim(),去掉元素左侧的空格
Trim(),去掉元素的左右两边的空格


- 别名函数alias

AS,使用关键字进行赋予

**数据处理函数**

在使用高级的数据处理函数时候，不同的DBMS软件的处理操作不同
为了代码的可移值行，要对这些代码做好注释，解释处理函数









