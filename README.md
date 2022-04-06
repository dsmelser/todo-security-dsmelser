# TODO Manager w/ Security

## TODO Data Model

    1. text         - String
    2. authorId     - int
    3. isPublic     - boolean
    4. createDate   - LocalDate

## Roles

    1. Guest/Anonymous Role
    2. Author/Standard User Role
    3. Administrator Role

## User Stories

As a __________, I should [not] be able to ____________.

Preconditions: what must be true for the user story to be relevant.
Postconditions: what must be true after the user story ends.

* [ ] As any user, I should be able to see all public Todos.
* [ ] As a guest, I should not be able to see any private Todos.
* [ ] As a guest, I should not be able to create a Todo.
* [ ] As a guest, I should not be able to remove a Todo.
* [ ] As a guest, I should not be able to edit a Todo.
* [ ] As a guest, I should be able to create an account.
* [ ] As a guest, I should be able to log into an existing account.
* [ ] As an Author, I should be able to see _my own_ private Todos.
* [ ] As an Author, I should not be able to see other Author's Todos.
* [ ] As an Author, I should be able to create a Todo.
* [ ] As an Author, I should be able to remove _my own_ Todos.
* [ ] As an Author, I should not be able to remove other Author's Todos.
* [ ] As an Author, I should be able to edit _my own_ Todos.
* [ ] As an Author, I should not be able to edit other Author's Todos.
* [ ] As an Admin, I should be able to see all Todos.
* [ ] As an Admin, I should be able to create a Todo.
* [ ] As an Admin, I should be able to remove all Todos.
* [ ] As an Admin, I should be able to edit all Todos.
* [ ] As an Admin, I should be able to promote an Author to Admin.
* [ ] As an Admin, I should be able to remove Author users.
* [ ] As an Admin, I should be able to edit the name of Authors.
* [ ] As an Admin, I should be able to change the password of Authors.


## Tasks

* [ ] Create Java API
  * [x] Create Java Project (todo-with-security)
  * [x] Modify pom.xml to include the parent tag (spring-boot-starter-parent)
  * [x] Modify pom.xml to include the following dependencies
    * [x] spring-boot-starter-security
    * [x] jjwt-api
    * [x] jjwt-impl
    * [x] jjwt-jackson
    * [x] mysql-connector-java
    * [x] spring-boot-starter-jdbc
  * [x] Create base package (todo)
  * [x] Create models package
    * [x] Create AppUser class
      * [x] Extend from the User (org.springframework.security.core.userdetails)
      * [x] Add Set&lt;String&gt; roles field variable
      * [x] Add Integer userId field variable
      * [x] Generate getters/setters
      * [x] Generate hashCode/equals
      * [x] Add constructor which takes Integer userId, String username, String password, and Set&lt;String&gt; roles
        * [x] call super(username, password, roles.stream().map( r -> new SimpleGrantedAuthority( "ROLE_" + r )).collect( Collectors.toList() ) )
        * [x] assign to this.userId
        * [x] assign to this.roles
    * [x] Create Todo class
      * [x] Create String text field variable
      * [x] Create Integer userId field variable
      * [x] Create Boolean isPublic field variable
      * [x] Create LocalDate createDate field variable
      * [x] Generate getters/setters
      * [x] Generate hashCode/equals
* [ ] Create mysql schemas (test/prod)
  * [x] create sql folder in project folder
  * [x] create todo-test.sql
  * [x] create todo-prod.sql
  * [x] drop database if exists todo_X
  * [x] create database todo_X
  * [x] use todo_X
  * [x] create table users
    * [x] userId        int primary key auto_increment
    * [x] username      varchar(300) not null unique
    * [x] password      varchar(2048) not null,
  * [x] create table todos
    * [x] todoId        int primary key auto_increment
    * [x] todoText      text not null
    * [x] authorId      int not null
    * [x] isPublic      bit(1) not null
    * [x] createDate    date not null
    * [x] constraint fk_todos_users foreign key (authorId) references users(userId)
  * [x] create table roles
    * [x] roleId        int primay key auto_increment
    * [x] roleName      varchar(20) not null unique
  * [x] create table userroles
    * [x] userId        int not null,
    * [x] roleId        int not null,
    * [x] constraint pk_userroles (userId, roleId),
    * [x] constraint fk_users_userroles foreign key (userId) references users(userId)
    * [x] constraint fk_roles_userroles foreign key (roleId) references roles(roleId)
  * [ ] insert into users (username, password) values ('bob', '$2a$12$HqaU3VlN09ufZ60R8VrLHuIX8H6b1iFDA9AG./vzThpIzhxEIF8nC');   -- pw is password
  * [ ] insert into users (username, password) values ('june', '$2a$12$k2TB.cQ1TLHLOYn.pbbiTuQ5HoUxozWkl.ZgFZ.9eioAeMxndT5AS');  -- pw is admin-password
  * [ ] insert into roles (roleName) VALUES ('AUTHOR'), ('ADMIN');
  * [ ] insert into userroles (userId, roleId) VALUES (1,1), (2,2);
  * [ ] insert into todos (todoText, authorId, isPublic, createDate) values ('this is a private todo', 1, 0, '2020-04-06'), ('this is a public todo', 2, 1, '2020-04-05');
  * [ ] generate reset stored procedure in db (set_known_good_state)
    * [ ] delete from userroles;
    * [ ] delete from users;
    * [ ] alter table users set auto_increment = 1;
    * [ ] delete from roles;
    * [ ] alter table user set auto_increment = 1;
    * [ ] delete from todos;
    * [ ] alter table todos set auto_increment = 1;
    * [ ] (copy all inserts from prod)
* [ ] Create React Front-End