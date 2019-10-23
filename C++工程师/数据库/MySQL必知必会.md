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
这个使用还是比较多的

**数据处理函数**

在使用高级的数据处理函数时候，不同的DBMS软件的处理操作不同
为了代码的可移值行，要对这些代码做好注释，解释处理函数


时间处理函数

数值处理函数

- Abs() 返回绝对值
- Cos() 返回余弦
- Exp() 返回一个数的指数值
- Mod() 余数

汇总数据

- AVG()
- COUNT()
- MAX()
- MIN()
- SUM()


**分组数据**

GROUP BY  ,HAVING

WHERE 是用来过滤行
GROUP BY 是用来进行分组


**子查询**

select 语句中，子查询总是从内向外进行处理

要进行格式化的输入,便于理解

```sql
SELECT cust_name,cust_contact
FROM customers
WHERE cust_id IN(SELECT cust_id
                 FROM orders
                 WHERE order_num IN(SELECT order_num
                                    FROM orderitems
                                    WHERE prod_id='TNT2'));
```


**联结表**

SQL最强大的功能之一，就是在数据检索查询的执行中联结join表

- 外键(foreign key):外键作为某个表中的一列，包含另一个表中的主键值，定义类两个表之间的关系

- 可伸缩性(scale):能够不断增加的工作量而不失败

这个关系是实现关系性数据库的重点，关系性的数据库伸缩性要比非关系性数据库要好的多

联结是一种机制，用来在一条SELECT语句中关联表


- 内部联结(equijoin)等值联结

```sql
SELECT vend_name,prod_name,prod_price
FROM vendors INNER JOIN products
ON vendors.vend_id=products.vend_id;

SELECT vend_name,prod_name,prod_price
FROM vendors ,products
WHERE vendors.vend_id=products.vend_id;
```

表联结越多，性能下降越厉害


- OUTER JOIN 指定联结的类型

- LEFT OUTER JOIN 从左边的表中选择所有行
- RIGHT OUTER JOIN 从右边表中选择所有行


**组合查询**

unoin

一个语句的排序order by会实现多个表的排序


**全文本搜索**

创建时实现  FULLTEXT(note_text)

Match()指定搜索的列
Against() 指定要使用的搜索表达式
```sql
SELECT note_next
FROM productontes
WHERE Match(note_text) Against('rabbit');

-- 可以进行拓展

SELECT note_next
FROM productontes
WHERE Match(note_text) Against('rabbit' IN BOOLEAN MODE);
```

```sql
SELECT note_text ,Match(note_text) Against('rabbit') AS rank
FROM productnotes;
```



### 插入数据

INSERT

insert语句不会产生输出

尽量使用单条INSERT语句处理多个插入，要比多条Insert快

一个列，对应多组value值来实现

