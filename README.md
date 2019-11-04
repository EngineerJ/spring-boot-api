Spring Boot API that connects with MySQL. The database is included as a SQL file.

The application consist of:
* Post controller: handles http requests.
* Post model: is the representation of the post object and interacts with the database with JPA.
* Post repository: for interaction to the MySQL database.
* Post database: the post database is ment to be imported in phpMyAdmin. post_database.sql

Please modify the application.properties so it can connect to the MySQL database.
