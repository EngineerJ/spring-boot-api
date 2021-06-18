Spring Boot API that connects with MySQL. The database is included as a SQL file.

The application consist of:
* Post controller: handles http requests.
* Post model: is the representation of the post object and interacts with the database with JPA.
* Post repository: for interaction to the MySQL database.
* Post database: the post database is meant to be imported in phpMyAdmin. post_database.sql

Please modify the application.properties so it can connect to the MySQL database.

You can use XAMP to quickly setup on MySQL db. Don't forget to make the post_database database in the mysql administrator page:
http://localhost/phpmyadmin/index.php?route=/&lang=en

Postman:
To get all the Post objects, do the GET request with:
http://localhost:8080/posts

Add data by selecting the HTTP method as POST, type the URL:
http://localhost:8080/posts

Select Body -> Raw -> change Text for JSON (application/json) 

Add the raw json:
{
      "id" : 2,
      "userId" : 0,
      "title" : "test",
      "body" : "test",
      "createdAt" : "2021-06-18T05:36:33.000+0000",
      "createdBy" : "test",
      "updatedAt" : "2021-06-18T05:36:33.000+0000",
      "updatedBy" : "test"
}

Expect result 201 created for a new row in the MySQL database.