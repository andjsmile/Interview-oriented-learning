

[MySQL面试高频一百问(工程师方向)](https://juejin.im/post/5d351303f265da1bd30596f9)


### SQL

structured query language  结构化的查询语言

用于访问和处理数据库的标准的计算机语言

RMDBS  relational database management system 关系性数据库管理系统

SQL语句中对大小写不敏感

- SELECT       数据库中提取数据
- UPDATE       更新数据库中的数据
- DELETE       从数据库中删除数据
- INSERT INTO  向数据库中插入数据
- CREATE DATABASE  创建新数据库
- ALTER DATABASE   修改数据库
- DROP  DATABSE    删除数据库
- CREATE TABLE     创建新表
- ALTER TABLE      变更数据库表
- DROP TABLE       删除表
- CREATE INDEX     创建索引
- DROP INDEX       删除索引


```sql
SELECT column_name FROM table_name;
-- SELECT DISTINCT 语句用于返回唯一不同的值。
SELECT DISTINCT cloumn_name FROM table_name;

-- WHERE 子句用来过滤记录，用于提取哪些满足条件的记录
SELECT column_name,column_name FROM table_name WHERE column_name operator value;

-- AND OR 运算符用于基于一个以上的条件对记录进行过滤。
SELECT * FROM Websites WHERE country='CN' AND alexa > 50;

-- ORDER BY 默认是升序排列，降序使用DESC
SELECT column_name,column_name
FROM table_name
ORDER BY column_name,column_name ASC|DESC;

SELECT * FROM Websites ORDER BY alexa DESC;

-- INSERT INTO 语句用于向表中插入新记录。
INSERT INTO table_name (column1,column2,column3,...)
VALUES (value1,value2,value3,...);

-- UPDATE 语句用于更新表中的记录。
-- 执行没有 WHERE 子句的 UPDATE 要慎重，再慎重。
UPDATE table_name
SET column1=value1,column2=value2,...
WHERE some_column=some_value;

UPDATE Websites SET alexa='5000', country='USA' WHERE name='菜鸟教程';


-- DELETE 语句用于删除表中的记录。
DELETE FROM table_name WHERE some_column=some_value;

-- 删除所有的数据
DELETE FROM table_name;
DELETE * FROM table_name;
```

WHERE 语句中 文本字段和数值字段

将文本字段使用单引号 ''
数值的字段 不要使用引号



### MySQL

mySql是最好的RDBMS realtional database mangement system 关系数据库管理系统

启动关闭mysql
```sql
-- 启动前，先看sql是否在运行
ps -ef | grep mysqld

-- 开启服务器
cd /usr/bin
./mysqld_safe &

-- 关闭
root@host# cd /usr/bin
./mysqladmin -u root -p shutdown
Enter password: ******
```

1.索引
索引是一种数据结构，可以帮助我们快速的进行数据查找，协助快速查询，更新数据表中的数据

但是会增加数据库的空间
插入和修改数据要花费较多的时间(索引要变动)

适合作为索引:
唯一、不为空、经常被查询的字段

2.索引的数据结构
具体数据结构的实现与存储搜索引擎相关，mysql中使用较多的索引有hash索引，B+树索引


hash索引-hash表，检索效率高，可以一次定位
B+树    -多路平衡查找树(根结点出发查找到叶子结点) B+树索引需要从根节点到枝节点，最后才能访问到页节点这样多次的IO访问


**为什么不都用Hash索引而使用B+树索引？**
Hash索引仅仅能满足"=","IN"和""查询，不能使用范围查询,因为经过相应的Hash算法处理之后的Hash值的大小关系，并不能保证和Hash运算前完全一样；
Hash索引无法被用来避免数据的排序操作，因为Hash值的大小关系并不一定和Hash运算前的键值完全一样；
Hash索引不能利用部分索引键查询，对于组合索引，Hash索引在计算Hash值的时候是组合索引键合并后再一起计算Hash值，而不是单独计算Hash值，所以通过组合索引的前面一个或几个索引键进行查询的时候，Hash索引也无法被利用；
Hash索引在任何时候都不能避免表扫描，由于不同索引键存在相同Hash值，所以即使取满足某个Hash键值的数据的记录条数，也无法从Hash索引中直接完成查询，还是要回表查询数据；
Hash索引遇到大量Hash值相等的情况后性能并不一定就会比B+树索引高

3.事务

事务是一系列操作，要符合ACID属性

atomicity 原子性
consistency 一致性
isolation   隔离性(独立性)
durability  持久性

隔离性:
- 读未提交  （脏读）
- 读提交     (支持不可重复读)
- 可重复读    (导致 幻读)
- 串行化
InnoDB默认使用的是可重复读隔离级别

MySQL中默认事务隔离级别是“可重复读”时并不会锁住读取到的行
事务隔离级别：未提交读时，写数据只会锁住相应的行。
事务隔离级别：可重复读时，写数据会锁住整张表。
事务隔离级别：串行化时，读写数据都会锁住整张表。

4.主键 & 外键

主键:主键是数据库确保数据行在整张表唯一性的保障,一个数据列只能有一个主键，且主键的取值不能缺失，不能为null

表中没有主键，建议添加一个自增长的ID列作为主键
设定主键后，数据表中的删、改、查可以快速确保操作数据范围安全

外键:在一个表中存在的另一个表中的主键成为外键

5.drop & truncate & delete

drop直接删掉表。
truncate删除表中数据，再插入时自增长id又从1开始。
delete删除表中数据，可以加where字句。


6.临时表  temproary table 

临时表可以手动删除：
DROP TEMPORARY TABLE IF EXISTS temp_tb;
临时表只在当前连接可见，当关闭连接时，MySQL会自动删除表并释放所有空间。因此在不同的连接中可以创建同名的临时表，并且操作属于本连接的临时表。
创建临时表的语法与创建表语法类似，不同之处是增加关键字TEMPORARY，

7.关系性数据库和非关系性数据库

非关系性数据库(NoSQL):
- 性能:基于键值对，不需要sql层的解析，性能高
- 可拓展性:基于键值对，数据之间没有耦合性，非常容易进行水平拓展

关系性数据库 SQL:
- 复杂查询:可以使用sql语句在一个表或多个表之间做复杂的数据查询
- 事务支持:使对数据安全性要求较高的数据访问得以实现

8.数据库三大范式

第一范式:确保每列具有原子性，所有字段都不不可分割的原子值

第二范式:确保数据表的每列都和主键相关，非主键列完全依赖与主键，而不能是部分依赖

第三范式:数据表的每列数据都和主键直接相关，而不是间接相关，非主键列只能依赖于主键


9.优化

查询语句不同元素执行的先后顺序
select--from--where--group by--having--order by 

- select:查看结果集中的哪个列，或列的计算结果
- from:需要从哪个数据表检索数据
- where:过滤表中数据的条件
- group by:如何将上面过滤出的数据分组
- having:对上面已经分组的数据进行过滤的条件
- order by :按照什么样的顺序来查看返回的数据

explain :

对于复杂、效率低的sql语句，我们通常是使用explain sql 来分析sql语句，
这个语句可以打印出，语句的执行。这样方便我们分析，进行优化


10.数据库的锁

mysql有三种锁的级别: 页级、表级、行级。

- 表级锁：开销小，加锁快；不会出现死锁；锁定粒度大，发生锁冲突的概率最高,并发度最低。
- 行级锁：开销大，加锁慢；会出现死锁；锁定粒度最小，发生锁冲突的概率最低,并发度也最高。
- 页面锁：开销和加锁时间界于表锁和行锁之间；会出现死锁；锁定粒度界于表锁和行锁之间，并发度一般


死锁:

是指两个或两个以上的进程在执行过程中。因争夺资源而造成的一种互相等待的现象,若无外力作用,它们都将无法推进下去



#### SQL

1. 

**DDL 数据定义语句**
CREATE/ ALTER /DROP  TABLE DATABASE

**DML 数据管理语句**

INSERT 插入
DELETE 删除
UPDATE 更新
SELECT 选择


创建表  

常见类型 int ,char varchar,datatime

CREATE TABLE `students` ( 
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY;
    `name` VARCHAR(20) NOT NULL;
    `nickname` VARCHAR(20) NULL;
    `sex`      CHAR(1)     NOT NULL;    -- SMALLINT
    `in_time`  DATETIME    NULL;
    )DEFAULT CHARSET 'UTF-8';

插入表

INSERT INTO `students` VALUE
(1,'张三','三哥','男',now());    -- 这个不推荐使用

INSERT INTO `students` (`name`,`nickname`,`sex`,`in_time` )VALUE('张三','三哥','男',now());

INSERT INTO `students` (`name`,`nickname`,`sex`
)VALUES('张三','三哥','男'),('李四','四妹','女');

查询语句

SELECT 
    select_expr ,..

FROM 
    table_references

[WHERE where definition]

[GROUP BY {col_name|expr |position}]

[HAVING where defintion]

[ORDER BY {col_name|expr|position} [ASC|DESC],]

[LIMIT {[offset],row_count}]


偏移量，一页的数量   （offset-1）*row_count 


修改语句

UPDATE table_references
SET 
[WHERE ]

删除语句

DELETE  FROM table_references
[WHERE where_defintion]



python操作数据库的API
链接 mysql数据库

python的数据库API，也是DB-API,这是连接到数据库客户端的C语言库接口


p12

**完整性约束的SQL定义**

在更新数据库的时候，表中不能出现不符合完整性要求的记录，保证用户提供正确有效的数据

在编写数据库应用程序，对每个更新操作都进行完整性检查


三种完整性约束

- 实体完整性
- 参照完整性
- 用户自定义完整性


**实体完整性**

通过主键(主码)来实现  PRIMARY KEY

主码的属性不能为空值，表中不能出现主码值完全相同的两个键值

**参照完整性(引用完整性)**

在更新记录时，参照完整性保持表之间已定义的关系。参照完整性基于外键与主键之间或外键与唯一键之间的关系。参照完整性确保键值在所有表中一致

**用户自定义完整性约束**

基于属性的check约束
基于元组的约束

[数据库高频面试题](https://www.nowcoder.com/discuss/135748?type=all&order=time&pos=&page=1)


聚集索引vs 非聚集索引

clustered index  聚集索引表记录的排列顺序和索引的排列顺序一致，所以查询效率快

nonclustered index 非聚合索引 
非聚集索引指定了表中记录的逻辑顺序，但是记录的物理和索引不一定一致

**联结表**

