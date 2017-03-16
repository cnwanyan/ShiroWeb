CREATE SCHEMA `db_shiro` DEFAULT CHARACTER SET utf8 ;
use db_shiro;
create table tb_user(
	id int auto_increment,
    username varchar(200),
    password varchar(200),
    name varchar(20),
    primary key(id)
)

insert into tb_user values (1,"admin","df655ad8d3229f3269fad2a8bab59b6c","张三"),
						   (2,"root","28cf327cd6b46986752f4a396aecfa23","管理员"),
                           (3,"test","505f1793db87ca593cdd000b48260a88","测试"),
                           (4,"cs","5144bdf22d02bfc624ac107160c59b96","猫熊");

