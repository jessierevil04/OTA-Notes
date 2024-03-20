# Note-Taking Application
###### A Simple Note-Taking Backend Application using Spring-Boot with Swagger
###### The application consists of a RESTful API that allows users to create, retrieve, update, and delete notes. 
###### Each note can consist of a title and a body.

## Technologies
- Maven
- Java 17
- Spring Boot 3.2.3

## Prerequisite
- Maven
- JDK 17

## Running the Application
- The repository has included the packaged jar, ready to be executed
- Below is the command to run the application
- The command must be executed inside the project's root folder
```
java -jar target/notes-0.0.1-SNAPSHOT.jar
```

## Building the Application
- Below is the command to build the application
- The command must be executed inside the project's root folder
```
mvn package
```

## Accessing the Applicaiton
- The API can be accessed using any API client
- Below is the local base URL
```
http://localhost:8080/notes
```
- Below is the local Swagger UI URL
```
http://localhost:8080/swagger-ui/index.html
```

## Assumptions
- The Note ID is numeric
- The Note ID is auto generated
- The Note ID is auto incremented
- The Note Title is mandatory
- The Note Body is mandatory
- User Handling is not needed for this application
- User Authentication is not needed for this application
- Transactional and Rollback Handling is not necessary. Storage is implemented as List
