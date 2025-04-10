# NewsAndCategoriesWeb
## Overview
The following repository contains an app that is built with Spring Boot and that 
uses REST services to modify news and categories they belong to.
The data then gets inserted in PostgreSQL database.
Databse migrations are managed using Liquibase.
## Guidelines
1. Clone this repository
2. Open this project with your preferred IDE
3. Download PostgreSQL image for docker
```
$ docker pull postgres
```
4. Create PostgreSQL container. You can change password and username. Don't forget to change settings in application.yml file
```
$ docker run --name postgres -e POSTGRES_PASSWORD=qwerty -p 5432:5432 -d postgres
```
```yml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/news
    username: admin
    password: qwerty
    driver-class-name: org.postgresql.Driver
```
5. After you make sure that 'postgres' container is running, you need to create 'news' database
```
$ docker exec -it postgres psql -U postgres
```
After that you will access psql. Now you can create new database
```
$ CREATE DATABASE news;
```
6. Launch the application. To make sending requests easier I would recommend downloading and using Postman
7.  Navigate to NewsController.java and CategoriesController.java to see their RequestMappings
```java
@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {
    private final CategoryCRUDService categoryService;
```
```java
@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsCRUDService newsService;
```
8. Example of POST query in Postman
![{DBD0187F-A47A-4ECD-BCDE-F73A998BE019}](https://github.com/user-attachments/assets/6cc291e8-fbd9-4f1e-80cd-fb9af440250e)

## Result of app execution
![{FC600370-F88A-4762-A7F1-72B567F878DE}](https://github.com/user-attachments/assets/f1adf22b-58e4-4209-8513-fbfd5666e58a)

![{DCA6DB90-00A1-4EA8-BC38-0A0B805EDCC8}](https://github.com/user-attachments/assets/24716e6f-4ce2-41b0-b65e-b85427c096ab)

![{1BAC5984-6441-42DE-A6AB-168F46A7E783}](https://github.com/user-attachments/assets/4fee7b0b-2b8e-4fe1-92b6-4bbe7215be48)

