drop database if exists todo_test;
create database todo_test;
use todo_test;

create table users (

	userId		int primary key auto_increment,
    username	varchar(300) not null unique,
    `password`	varchar(2048) not null
);

create table todos (

	todoId		int primary key auto_increment,
    todoText	text not null,
    authorId	int not null,
    isPublic	bit(1) not null,
    createDate	date not null,
    
    constraint fk_todos_users foreign key (authorId) references users(userId)
);

create table roles (

	roleId		int primary key auto_increment,
    roleName	varchar(20) not null unique
);


create table userroles (
	userId		int not null,
    roleId		int not null,
    
    constraint pk_userroles primary key (userId, roleId),
    constraint fk_users_userroles foreign key (userId) references users(userId),
    constraint fk_roles_userroles foreign key (roleId) references roles(roleId)
);