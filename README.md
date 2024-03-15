# CS6510_Group2_Blog-AI-Web-Application

## An AI-Powered Blog Web Application

## Introduction

Blog is a fully-functional personal website exactly like Facebook HomePage. And it is developed based on the classic MEVN stack (MongoDB, ExpressJS, Vue and Node.JS).

## Sample Image
https://assets-global.website-files.com/5e593fb060cf877cf875dd1f/60a98115ba76be068162f5ea_thumbnailimage.jpeg


## Front-end

We will use VUE.js to build our Front-end. 

--Declarative Rendering: Vue extends standard HTML with a template syntax that allows us to declaratively describe HTML output based on JavaScript state.

--Reactivity: Vue automatically tracks JavaScript state changes and efficiently updates the DOM when changes happen.


## Back-end

We will use SpringBoot to build our Back-end.

--Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

--We take an opinionated view of the Spring platform and third-party libraries so you can get started with minimum fuss. Most Spring Boot applications need minimal Spring configuration.

## ToDo List

 - [ ]  Nickname spelling - 1 
 - [ ]  Comment successful 
 - [ ]  Search - no search test case
 - [ ]  changing website name
 
 
 ### Optional feature
 
 - [ ]  multi language/or able to translate
 - [ ]  social networking links
# How to run the project for MacOS
## 1. Pull the code from the remote github repository to your target folder

git clone https://github.com/shinesday/CS5500_Group9_Blog-It-Web-Application.git

## 2. Run the front end

### 2.0. Open the front end project by clicking the blog-front-end folder in vscode.

### 2.1. Enter the script below to run the front end:
```
npm start
```
## 3. Set up and run the backend server
### 3.0. Open the blog-back-end folder by clicking the pom.xml file with Java 1.8.

### 3.1. Set up MySQL

> Download MySQL by the instruction below:

https://dev.mysql.com/doc/refman/8.0/en/macos-installation-pkg.html

> Open the MySQL workbench and create a new MySQL connection(The password set here are used later and should be noted down).

> Enter into the new MySQL connection by clicking and create a new table named blog using the command below.
```
CREATE DATABASE blog;
```
Note the blog database should be set as default. Finally, run the given blog.sql script.

> Edit the application.properties in the res subfolder under the blog-back-end main folder(the port should be 3306).
```
spring.datasource.url=jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=UTF-8&serverTimeZone=UTC
spring.datasource.username=root
spring.datasource.password=<your password>
```
### 3.2. Set up Redis by entering codes below into the terminal

```
brew install redis
redis-server
```
### 3.3. Run the backend server by clicking the run button.


