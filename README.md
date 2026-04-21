# Security-and-Final-Enhancements

# Creating My First Spring Boot API with Validation & Database Persistence (Spring Boot + JPA) & Security and Final Enhancements

---

## Project Description

This project is a Spring Boot REST API that allows users to manage tasks. Users can create, read, update, and delete tasks (CRUD operations). The API includes input validation to ensure correct data entry such as title length and required fields.

This project demonstrates how to build a structured backend using controllers, services, and models in Spring Boot.

The application follows a layered architecture:

* Controller: Handles HTTP requests
* Service: Contains business logic
* Model: Represents task data

---

## Technologies Used

* Java 17
* Spring Boot 3.x
* Spring Data JPA
* H2 Database
* Spring Validation
* Spring Security
* Spring Boot Actuator
* Lombok
* Maven
* Postman (API testing)
* Swagger / OpenAPI (API documentation)

---

## How to Run the Application

### Option 1: Using an IDE

* Open the project in IntelliJ IDEA or VS Code
* Locate `CampusTaskboardApplication.java`
* Right-click and click Run
* Server runs at: http://localhost:8080

---

### Option 2: Using Terminal

```
./mvnw spring-boot:run
```

Then open:
http://localhost:8080/api/tasks

---

## API Documentation

### Base URL

http://localhost:8080/api/tasks

---

### Get All Tasks

**GET /api/tasks**

#### Response

```json
[
  {
    "id": 1,
    "title": "Complete Homework 5",
    "completed": false,
    "priority": "HIGH"
  }
]
```

---

### Get Task by ID

**GET /api/tasks/{id}**

---

### Create Task

**POST /api/tasks**

#### Request Body

```json
{
  "title": "Complete Homework 5",
  "description": "Finish Spring Boot API assignment",
  "completed": false,
  "priority": "HIGH"
}
```

#### Response

```json
{
  "id": 1,
  "title": "Complete Homework 5",
  "description": "Finish Spring Boot API assignment",
  "completed": false,
  "priority": "HIGH"
}
```

---

### Update Task

**PUT /api/tasks/{id}**

#### Request Body

```json
{
  "title": "Updated Task",
  "description": "Updated description",
  "completed": true,
  "priority": "HIGH"
}
```

---

### Delete Task

**DELETE /api/tasks/{id}**

---

## Validation

* Title must be between 3 and 100 characters
* Title cannot be empty
* Description cannot exceed 500 characters

Returns:

* 400 Bad Request for invalid input

---

## Database Persistence (Spring Data JPA + H2)

* Uses H2 in-memory database
* Data stored using JPA
* Repository layer handles queries

---

## Database Configuration

H2 Console:
http://localhost:8080/h2-console

* JDBC URL: jdbc:h2:mem:taskboarddb
* Username: sa
* Password: (empty)

---

## JPA & Repository

```java
List<Task> findByCompletedTrue();
List<Task> findByPriority(Task.Priority priority);
```

---

## New API Endpoints

* GET /api/tasks/completed
* GET /api/tasks/incomplete
* GET /api/tasks/priority/HIGH
* GET /api/tasks/search?keyword=homework
* GET /api/tasks/paginated?page=0&size=5&sortBy=title

---

## Database Testing

```sql
SELECT * FROM tasks;
```

---

## Error Handling & Responses

Uses:

@RestControllerAdvice

### Custom Exceptions

* TaskNotFoundException
* InvalidTaskDataException

### Example Error Response

```json
{
  "timestamp": "2026-04-20T12:00:00",
  "status": 404,
  "error": "Not Found",
  "message": "Task not found",
  "path": "/api/tasks/1"
}
```

---

## DTOs

* TaskRequest
* TaskResponse

---

## Soft Delete

* Tasks are marked as deleted = true
* Data is not permanently removed

---

## Logging

Example:

GET /api/tasks - Status: 200 - Duration: 12ms

---

## Actuator

Health Check:
http://localhost:8080/actuator/health

```json
{
  "status": "UP"
}
```

---

## Security and Final Enhancements

### Features

* Spring Security configuration
* CORS enabled
* CSRF disabled
* Public endpoints:

  * /api/tasks/**
  * /h2-console/**
  * /actuator/**

---

### API Versioning

```
/api/v1/tasks
```

---

### Integration Testing

* POST create task
* GET task by ID
* 404 error handling

---

### Swagger Documentation

http://localhost:8080/swagger-ui.html

---

### Validation Enhancement

```java
@ValidPriority
```

Values:

* LOW
* MEDIUM
* HIGH

---

## Feature Breakdown

### Database Integration

* Uses H2 database
* JPA for persistence

### Advanced Features

* Search
* Pagination
* Filtering

---

## Final Outcome

This API includes:

* Security (Spring Security)
* Database (JPA + H2)
* Validation & error handling
* Integration testing
* Swagger documentation
* Versioned endpoints

---

## Demo Videos

* TaskBoard: https://www.youtube.com/watch?v=mo7y3R6u-RQ
* Database: https://youtu.be/6tCTppEGuNE
* Advanced Features: https://youtu.be/bD4J26PvFlo
* Security: (coming soon)
