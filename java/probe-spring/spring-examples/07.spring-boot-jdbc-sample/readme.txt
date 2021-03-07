With spring-boot you just do your business. Without overhead, without extra manipulations and configurations.
In example below:

    UserRepository provides information about users from database via JdbcTemplate, plain SQL-queries and RowMapper.
    UserController makes them accessible via HTTP.

In this example spring-boot automatically configures DataSource and JdbcTemplate according to application.properties file.

Sample urls:

    http://localhost:8080/user/test
    http://localhost:8080/user/user?id=2
    http://localhost:8080/user/users?ids=1,3,5,7


TODO: Complete ojdbc support...