## MySQL必知必会


### 1.创建数据库

create database basename;

use basename;

create table tablename(
id   int(11)     not null auto_increment,
name char(20)    not null comment '名称',
url  varchar(20) not null default'' ,
primary key(id)
)ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

### 2.CRUD 

create retrive,update,delete

**insert into tablename values('1','google','1.13.4.6'); **

insert into tablename values(),(),();

insert into tablename('id','name','url') values(),();

insert into tablename('id','name','url') select id ,name,url from otherdatabase;


**update table set   where   ; **

update customers set cust_name='the fundds',cust_email='ed@fundd.com' where cust_id=10005;



**delete from customers where cust_id=10006;**

delete 函数表中的行，但是不会删除表本身
