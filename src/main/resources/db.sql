DROP table IF EXISTS `t_product`;
create table `t_product`(
	id int primary key auto_increment,
	name varchar(100) not null,
	price double

);