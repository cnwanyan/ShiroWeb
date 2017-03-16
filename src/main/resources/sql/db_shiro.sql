CREATE SCHEMA `db_shiro` DEFAULT CHARACTER SET utf8 ;
use db_shiro;
create table tb_user(
	id int auto_increment,
    username varchar(200),
    password varchar(200),
    name varchar(20),
    primary key(id)
)

insert into tb_user values (1,"admin","admin","张三"),
						   (2,"root","root","管理员"),
                           (3,"test","test","测试"),
                           (4,"cs","cs","猫熊");


