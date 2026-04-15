# Security-and-Final-Enhancements
# Creating My First Spring Boot API with Validation & Database Persistence (Spring Boot + JPA) & Security and Final Enhancements

## Project Description

This project is a Spring Boot REST API that allows users to manage tasks. Users can create, read, update, and delete tasks (CRUD operations). The API includes input validation to ensure correct data entry such as title length and required fields.

This project demonstrates how to build a structured backend using controllers, services, and models in Spring Boot.

The application follows a layered architecture:

- Controller: Handles HTTP requests
- Service: Contains business logic
- Model: Represents task data

---

## How to Run the Application

### Option 1: Using an IDE

- Open the project in IntelliJ IDEA or VS Code  
- Locate `CampusTaskboardApplication.java`  
- Right-click and click Run  
- The server will start at: http://localhost:8080  

---

### Option 2: Using Terminal

- Open a terminal in the project folder  
- Run:

```

./mvnw spring-boot:run

````

- Open in browser or Postman:  
http://localhost:8080/api/tasks  

---

## API Endpoints Documentation

### Testing the API

The API was tested using Postman.

Each endpoint (GET, POST, PUT, DELETE) was tested using:
http://localhost:8080/api/tasks

Headers used:
Content-Type: application/json

---

### GET all tasks
GET /api/tasks  
Returns a list of all tasks

---

### GET task by ID
GET /api/tasks/{id}  
Returns a single task by ID

---

### POST create task
POST /api/tasks  
Creates a new task

Example JSON:

{
  "title": "Complete Homework 5",
  "description": "Finish Spring Boot API assignment",
  "completed": false,
  "priority": "HIGH"
}

---

### PUT update task
PUT /api/tasks/{id}  
Updates an existing task

Example JSON:

{
  "title": "Updated Task",
  "description": "Updated description",
  "completed": true,
  "priority": "HIGH"
}

---

### DELETE task
DELETE /api/tasks/{id}  
Deletes a task

---

## Validation

The API validates input using Spring Validation:

- Title must be between 3 and 100 characters  
- Title cannot be empty  
- Description cannot exceed 500 characters  

If invalid data is submitted, the API returns:
400 Bad Request with error messages

---

## 🚀 Database Persistence (Spring Data JPA + H2)

This project uses Spring Data JPA with an H2 in-memory database. Tasks are stored in a database instead of memory.

---

### New Features Added

- Database persistence using JPA  
- H2 database integration  
- Repository layer for data access  
- Search functionality  
- Pagination and sorting  
- Filtering by completion and priority  

---

### Database Configuration

The application uses the H2 in-memory database.

#### H2 Console
http://localhost:8080/h2-console  

#### Connection Settings

- JDBC URL: jdbc:h2:mem:taskboarddb  
- Username: sa  
- Password: (empty)  

---

## JPA & Repository

- @Entity maps Task class to database  
- JpaRepository handles CRUD operations automatically  
- Custom query methods allow filtering and searching  

Example:

```java
List<Task> findByCompletedTrue();
List<Task> findByPriority(Task.Priority priority);
````

---

## New API Endpoints (Homework 6)

* GET /api/tasks/completed
* GET /api/tasks/incomplete
* GET /api/tasks/priority/HIGH
* GET /api/tasks/search?keyword=homework
* GET /api/tasks/paginated?page=0&size=5&sortBy=title

---

## Database Testing

Tasks created via POST are saved in the database.

Verified using H2 Console:

```sql
SELECT * FROM tasks;
```

---

## Exception Handling

Implemented using:

@RestControllerAdvice

### Custom Exceptions

* TaskNotFoundException
* InvalidTaskDataException

---

## Error Response Example

{
"timestamp": "...",
"status": 404,
"error": "Not Found",
"message": "Task not found",
"path": "/api/tasks/1"
}

---

## DTOs

* TaskRequest: Used for incoming data
* TaskResponse: Used for outgoing responses

---

## Soft Delete

Instead of deleting permanently:

* Tasks are marked as deleted = true
* Data can be restored later

---

## Logging

Each request is logged:

GET /api/tasks - Status: 200 - Duration: 12ms

---

## Actuator

Health Check:
[http://localhost:8080/actuator/health](http://localhost:8080/actuator/health)

Response:

{
"status": "UP"
}

---

Got you — you don’t need a whole new section, you just need to **extend your existing README cleanly** so it flows with what you already wrote.

Here’s exactly what to **append to your current README (right after your existing “Demo Videos” or before the final section)** 👇

---

```markdown
---

## Security and Final Enhancements

### Overview

In this final stage, the API was enhanced with security, versioning, testing, and documentation features to make it closer to a production-ready backend system.

---

### Spring Security Configuration

Spring Security was added to configure and protect the API.

#### Features:

- CORS enabled for frontend communication  
- CSRF disabled for REST API usage  
- Public access to:
  - `/api/tasks/**`
  - `/h2-console/**`
  - `/actuator/**`
- All other endpoints require authentication (future-ready setup)

#### Security File:
```

config/SecurityConfig.java

```

---

### CORS Configuration

CORS allows cross-origin requests from:

- http://localhost:3000  
- http://localhost:8080  

This enables frontend applications to communicate with the backend.

---

### API Versioning

A versioned API endpoint was introduced:

```

/api/v1/tasks

```

#### Benefits:

- Prevents breaking changes  
- Supports future updates  
- Improves API maintainability  

---

### Integration Testing

Integration tests were added to ensure endpoints function correctly.

#### Test Cases:

- Create task (POST)  
- Get task by ID (GET)  
- Handle errors (404 Not Found)  

#### Test File:
```

test/TaskControllerIntegrationTest.java

```

---

### API Documentation (Swagger)

Swagger UI was added using SpringDoc OpenAPI.

#### Access:
http://localhost:8080/swagger-ui.html  

#### Features:

- View all endpoints  
- Test API in browser  
- See request/response formats  

---

### ✅ Validation Enhancement

Custom annotation added:

```

@ValidPriority

```

Ensures only valid values are accepted:

- LOW  
- MEDIUM  
- HIGH  

---
---

### Feature Breakdown

#### Database Integration


#### Advanced Features (Search, Pagination, Filtering)


---

## 🏁 Final Outcome

By completing this project, the API now includes:

- Secure configuration (Spring Security)
- Database persistence (JPA + H2)
- Validation and exception handling
- Integration testing
- API documentation (Swagger)
- Versioned endpoints

This represents a complete backend system built with Spring Boot.
```

---

## Demo Videos

* TaskBoard Application: [https://www.youtube.com/watch?v=mo7y3R6u-RQ](https://www.youtube.com/watch?v=mo7y3R6u-RQ)
* Adding Database: [https://youtu.be/6tCTppEGuNE](https://youtu.be/6tCTppEGuNE)
* Advanced Features: [https://youtu.be/bD4J26PvFlo](https://youtu.be/bD4J26PvFlo)
* Security + Final Features (implmenting soon)


---

