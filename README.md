# todo_service
Design and implement a backend service allowing basic management of a simple to-do list.

Building a RESTful Web Service with Spring Boot using an H2 in-memory database"

# Technologies used
* Java 17
* Spring Boot
* H2 Database(in-memory database)
* Postman(for API testing)
* Docker

# Libraries used
* Lombok
* SLF4j
* Junit5
* Jacoco

#Build
* Maven

#Services
* add - add an item to TODO list (Todo item has id, description, status, creation date, due date)
* update - update description of an item
* markItem - mark an item as done/not done
* getAllItems - fetch all items (based on status as well)
* getItemsById - fetch specific item by itemId
* cron scheduled job to update past due items, this will run every 24 hours once

#How to Build Service
mvn clean install
* build docker image using either of below commands
  * docker build . 
  * docker build --tag=todo-service:latest .
* Run docker image using either of below command
  * docker run -p8080:808 #docker-image-id
  * docker run -p8080:8080 todo-service

#How to run test cases
mvn test

#How to run services locally
#Add 
* path -  /todo/add
* Method - POST
* url - localhost:8080/todo/add
* Request - 
   {         
       "description": "test2",
       "dueDate": "2022-09-02"
   }          
* Response - 
  {
      "id": 2,
      "description": "test2",
      "creationDate": "2022-09-05T19:38:50.952+00:00",
      "dueDate": "2022-09-02T00:00:00.000+00:00",
      "status": "not done"
  }

#Update
* path -  /todo/update
* Method - PUT
* url - localhost:8080/todo/update?itemId=1&description=task1

#markItem
* path -  /todo/markItem
* Method - POST
* url - localhost:8080/todo/markItem?itemId=4&isDone=true

#getItemsById
* path -  /todo/getItemsById
* Method - GET
* url - localhost:8080/todo/getItemsById?itemId=2

#getAllItems
* path -  /todo/getAllItems
* Method - GET
* url - localhost:8080/todo/getAllItems

#getAllItems
* path -  /todo/getAllItems
* Method - GET
* url - localhost:8080/todo/getAllItems?status=not done

  


