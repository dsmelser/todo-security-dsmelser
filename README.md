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
  * [ ] Create models package
    * [ ] Create AppUser class
      * [ ] Extend from the User (org.springframework.security.core.userdetails)
      * [ ] Add Set&lt;String&gt; roles field variable
      * [ ] Add int userId field variable
      * [ ] Generate getters/setters
      * [ ] Generate hashCode/equals
    * [ ] Create Todo class
      * [ ] Create String text field variable
      * [ ] Create int userId field variable
      * [ ] Create boolean isPublic field variable
      * [ ] Create LocalDate createDate field variable
      * [ ] Generate getters/setters
      * [ ] Generate hashCode/equals
* [ ] Create mysql schemas (test/prod)
* [ ] Create React Front-End