create table products (
id bigint not null auto_increment,
description varchar(255) not null,
name varchar(255) not null,
price float(53) not null,
status int, primary key (id)
);