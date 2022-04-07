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
    * [x] spring-boot-starter-web
  * [x] Create base package (todo)
    * [x] Create App class
      * [x] @SpringBootApplication
      * [x] main
        * [x] SpringApplication.run( App.class, args );
  * [x] Create application.properties file
    * [x] spring.datasource.url=jdbc:mysql://localhost:3306/todo_prod
    * [x] spring.datasource.username=root
    * [x] spring.datasource.password=top-secret-password
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
  * [ ] Create data package
    * [x] Create TodoRepo interface
      * [x] List&lt;Todo&gt; findAllPublic()
      * [x] List&lt;Todo&gt; findByUserId(Integer userId)
      * [x] Todo findById( Integer todoId )
      * [x] Todo add(Todo toAdd)
      * [x] boolean remove(Integer todoId)
      * [x] void edit( Todo updated )
    * [x] Create UserRepo interface
      * [x] AppUser findByUsername( String username )
      * [x] AppUser add( AppUser toAdd )
      * [x] boolean remove( Integer userId )
      * [x] void edit( User updated )
    * [x] Create TodoDbRepo class
      * [x] Add @Repository
      * [x] implements TodoRepo
        * [x] generate functions automatically
    * [x] Create UserMapper class
      * [x] create Set&lt;String&gt; roles field variable
      * [x] create UserMapper constructor which takes in the Set of roles and sets the field variable
      * [x] implements RowMapper&lt;AppUser&gt;
      * [x] auto-generate methods
        * [x] AppUser toBuild = new AppUser(userId, username, password, roles);
    * [x] Create UserDbRepo class
      * [x] Add @Repository
      * [x] implements UserRepo
        * [x] Add @Autowired JdbcTemplate template field variable
        * [x] generate functions automatically
        * [x] create private Set&lt;String&gt; findRolesByUsername(String username)
          * [x] String sql = "SELECT roleName FROM users u inner join userroles ur on ur.userId = u.userId inner join roles r on ur.roleId = r.roleId where username = ?"
          * [x] return template.query( sql, (rowData, rowNum)->rowData.getString("roleName"), username).stream().collect(Collectors.toSet())
        * [x] implement findByUsername(String username)
          * [x] String sql = "select userId, username, password from users where username = ?"
          * [x] return template.query( sql, new UserMapper(findRolesByUsername(username)), username).stream().findAny().orElse(null);
  * [ ] Create domain package
    * [ ] Create UserService class
      * [x] mark with @Service
      * [x] implements UserDetailsService
      * [x] add UserRepo field variable
      * [x] add PasswordEncoder field variable
      * [x] add constructor which takes in a UserRepo & PasswordEncoder
      * [x] @Override loadUserByUsername (can return AppUser as a UserDetails object)
        * [x] use the repo to pass along the user
        * [x] add //TODO: validate (later we'll check to make sure username isn't null/empty/etc)
        * [x] if user is not found (we get a null) throw new UsernameNotFoundException(username + " not found")
        * [x] otherwise, return the user
      * [x] add AppUser create( String username, String password )
        * [x] for now just return null
    * [ ] Create TodoService class
  * [ ] Create security package
    * [ ] create SecurityConfig class
      * [x] @EnableWebSecurity
      * [x] extends WebSecurityConfigurerAdapter
      * [x] @Override protected void configure( HttpSecurity http) throws Exception
        * [x] http.csrf().disable()
        * [x] http.cors()
        * [x] http.authorizeRequests()
          * [ ] .antMatchers( HttpMethod.POST, "/api/security/login").permitAll()
          * [x] .antMatchers("/**").denyAll()
          * [x] .and()
          * [x] .sessionManagement()
            * [x] .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
      * [x] public PasswordEncoder getEncoder(){ return new BCryptPasswordEncoder(); }
        * [x] mark with @Bean
      * [x] @Override protected AuthenticationManager authenticationManager() throws Exception
        * [x] just return super.authenticationManager();
        * [x] mark with @Bean
    * [x] Create JwtConverter class
      * [x] Mark as @Component
      * [x] add a Key field variable (secretKey) assign Keys.secretKeyFor(SignatureAlgorithm.HS256)
      * [x] add public String getTokenFromUser( User toConvert )
        * [ ] generate comma separated string of authorities granted to the user (retrieve those with .getAuthorities() )
        * [ ] return Jwts.builder()
          * [ ] .setIssuer("todo-app")
          * [ ] .setSubject(toConvert.getUsername())
          * [ ] .claim("authorties", commaSeparatedString)
          * [ ] .setExpiration( new Date(System.currentTimeMillis() + 15 * 60 * 1000 ) )
          * [ ] .signWithKey( secretKey )
          * [ ] .build();
      * [x] add public User getUserFromToken( String token )
        * [ ] for now, throw new UnsupportedOperationException()
    * [ ] Create JwtRequestFilter class
      * [x] extends BasicAuthenticationFilter
      * [x] Add a JwtConverter field
      * [x] Add a constructor that takes in a JwtConvert and AuthenticationManager
        * [x] super( authManager )
        * [x] store the JwtConverter in the field variable
      * [x] @Override protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        * [ ] for now, chain.doFilter( request, response );
      * [x] IN SecurityConfig.java
        * [x] add @Autowired JwtConverter field variable
        * [x] right after the .and() call .addFilter( new JwtReqestFilter() )
  * [ ] Create controllers package
    * [ ] Add AuthController class
      * [x] mark as @RestController
      * [x] add @RequestMapping( "/api/security" )
      * [x] add AuthenticationManager field variable
      * [x] add JwtConverter field variable
      * [x] add UserService field variable
      * [x] add a constructor that takes in all field variables and sets them
      * [ ] add ResponseEntity login( @RequestBody Map&lt;String,String&gt; credentials )
        * [x] mark as @PostMapping("/login")
        * [ ] create UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken( credentials.get("username"), credentials.get("password") );
        * [ ] in a try/catch( AuthenticationException ex) block...
          * [ ] Authentication authResult = authManager.authenticate( token );
          * [ ] if( authResult.isAuthenticated() ){
            * [ ] String jwt = converter.getTokenFromUser( (User)authResult.getPrincipal());
            * [ ] return ResponseEnttiy.ok( jwt );
          * [ ] }
          * [ ] catch( AuthenticationException ex ){
            * [ ] ex.printStackTrace( System.err ); }
          * [ ] return new ResponseEntity( HttpStatus.FORBIDDEN );
  
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
  * [x] insert into users (username, password) values ('bob', '$2a$12$HqaU3VlN09ufZ60R8VrLHuIX8H6b1iFDA9AG./vzThpIzhxEIF8nC');   -- pw is password
  * [x] insert into users (username, password) values ('june', '$2a$12$k2TB.cQ1TLHLOYn.pbbiTuQ5HoUxozWkl.ZgFZ.9eioAeMxndT5AS');  -- pw is admin-password
  * [x] insert into roles (roleName) VALUES ('AUTHOR'), ('ADMIN');
  * [x] insert into userroles (userId, roleId) VALUES (1,1), (2,2);
  * [x] insert into todos (todoText, authorId, isPublic, createDate) values ('this is a private todo', 1, 0, '2020-04-06'), ('this is a public todo', 2, 1, '2020-04-05');
  * [x] generate reset stored procedure in db (set_known_good_state)
    * [x] delete from userroles;
    * [x] delete from users;
    * [x] alter table users auto_increment = 1;
    * [x] delete from roles;
    * [x] alter table roles auto_increment = 1;
    * [x] delete from todos;
    * [x] alter table todos auto_increment = 1;
    * [x] (copy all inserts from prod)
  * [x] at end of test schema call set_known_good_state();
* [ ] Create React Front-End