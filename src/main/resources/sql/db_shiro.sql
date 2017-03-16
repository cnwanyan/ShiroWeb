CREATE SCHEMA `db_shiro` DEFAULT CHARACTER SET utf8 ;
use db_shiro;
create table tb_user(
	id int auto_increment,
    username varchar(200),
    password varchar(200),
    name varchar(20),
    primary key(id)
)

insert into tb_user values (1,"admin","0cb506c8c95a66e87c463bf1a270446c","张三"),
						   (2,"root","bf006e276607f226f96120354349d81c","管理员"),
                           (3,"test","505f1793db87ca593cdd000b48260a88","测试"),
                           (4,"cs","9c8cae6e5a1e4fde77b9f3be8f8b92f8","猫熊");

