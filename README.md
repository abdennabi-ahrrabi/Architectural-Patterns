
# Layered (n-tier) Architecture Todo Application

This project is a demonstration of the **Layered (n-tier) Architecture** pattern using a simple **Todo Application**. It is built with **Spring Boot**, **Spring Data JPA**, and an **H2 in-memory database** for rapid development and testing. The application implements a clean separation of concerns by dividing the code into multiple layers, such as the presentation, business logic, and data access layers.

## Table of Contents

1. [Overview](#overview)
2. [Technologies Used](#technologies-used)
3. [Project Structure](#project-structure)
4. [Application Architecture](#application-architecture)
    - [1. Presentation Layer (Controller)](#1-presentation-layer-controller)
    - [2. Business Logic Layer (Service)](#2-business-logic-layer-service)
    - [3. Data Access Layer (Repository)](#3-data-access-layer-repository)
    - [4. Database Layer (H2 Database)](#4-database-layer-h2-database)
5. [How to Run](#how-to-run)
6. [API Endpoints](#api-endpoints)
7. [Example JSON for Todo Entity](#example-json-for-todo-entity)
8. [Database Interaction](#database-interaction)
9. [Future Enhancements](#future-enhancements)
10. [Contributing](#contributing)
11. [License](#license)

---

## Overview

The **Layered (n-tier) Architecture** divides an application into layers where each layer has specific responsibilities. The purpose of this architecture is to enhance modularity, testability, and maintainability. In this project, we implement a **Todo Application** that allows users to create, retrieve, and delete tasks.

The main layers in this application are:
- **Presentation Layer**: Exposes REST API endpoints to the client.
- **Business Logic Layer**: Contains the core application logic.
- **Data Access Layer**: Interacts with the database for persistence operations.
- **Database Layer**: Uses an in-memory database (H2) to store `Todo` objects.

## Technologies Used

- **Spring Boot**: For creating RESTful web services and simplifying application development.
- **Spring Data JPA**: To abstract database interactions and work with ORM (Object-Relational Mapping).
- **H2 Database**: An in-memory relational database used for testing and prototyping.
- **Java 17**: The version of Java used in this project.
- **Gradle or Maven**: Dependency management and build automation.

## Project Structure

```plaintext
src/
 └── main/
     └── java/com/ahrrabi/Layered/n_tier/demo/
         ├── controller/       # Presentation layer (REST API)
         ├── model/            # Model (Entity) layer
         ├── repository/       # Data Access layer
         └── service/          # Business Logic layer
 └── test/                     # Unit and Integration Tests
 └── resources/                # Configuration files
 └── build.gradle              # Gradle build script (or pom.xml for Maven)
```

---

## Application Architecture

This project follows the **Layered Architecture** design pattern, which separates the application into four distinct layers:

### 1. Presentation Layer (Controller)

- **Responsibility**: The `TodoController` is responsible for handling HTTP requests and responses. It acts as a bridge between the client (frontend) and the business logic layer (service).

- **Example**:
  ```java
  @RestController
  @RequestMapping("/api/todos")
  public class TodoController {
      @Autowired
      private TodoService todoService;

      @GetMapping
      public List<Todo> getAllTodos() {
          return todoService.getAllTodos();
      }

      @PostMapping
      public Todo createTodo(@RequestBody Todo todo) {
          return todoService.createTodo(todo);
      }

      @DeleteMapping("/{id}")
      public void deleteTodoById(@PathVariable Long id) {
          todoService.deleteTodoById(id);
      }
  }
  ```

### 2. Business Logic Layer (Service)

- **Responsibility**: The `TodoService` contains the core business logic of the application. It interacts with the `TodoRepository` to retrieve, create, or delete `Todo` items. The service layer is responsible for applying any necessary business rules.

- **Example**:
  ```java
  @Service
  public class TodoService {
      @Autowired
      private TodoRepository todoRepository;

      public List<Todo> getAllTodos() {
          return todoRepository.findAll();
      }

      public Todo createTodo(Todo todo) {
          return todoRepository.save(todo);
      }

      public Optional<Todo> getTodoById(Long id) {
          return todoRepository.findById(id);
      }

      public void deleteTodoById(Long id) {
          todoRepository.deleteById(id);
      }
  }
  ```

### 3. Data Access Layer (Repository)

- **Responsibility**: The `TodoRepository` is the data access layer that communicates with the database using JPA (Java Persistence API). It abstracts the database queries and provides built-in methods to perform CRUD operations.

- **Example**:
  ```java
  @Repository
  public interface TodoRepository extends JpaRepository<Todo, Long> {}
  ```

### 4. Database Layer (H2 Database)

- **Responsibility**: The H2 database is an in-memory database that stores the `Todo` records during application runtime. It is primarily used for testing and development purposes.

- **Accessing the H2 Console**:
    - URL: `http://localhost:8080/h2-console`
    - JDBC URL: `jdbc:h2:mem:testdb`
    - Username: `sa`
    - Password: (leave blank)

---

## How to Run

To run this application locally, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/Layered-Architecture-TodoApp.git
   cd Layered-Architecture-TodoApp
   ```

2. **Build and run the application**:
    - If using **Gradle**:
      ```bash
      ./gradlew bootRun
      ```
    - If using **Maven**:
      ```bash
      mvn spring-boot:run
      ```

3. **Access the application**:
    - The application will be running on `http://localhost:8080`.
    - The H2 database console can be accessed at `http://localhost:8080/h2-console`.

---

## API Endpoints

- **GET /api/todos**: Retrieve all todos.
- **GET /api/todos/{id}**: Retrieve a specific todo by ID.
- **POST /api/todos**: Create a new todo.
- **DELETE /api/todos/{id}**: Delete a todo by ID.

---

## Example JSON for Todo Entity

Here is an example of a `Todo` object represented in JSON format:

```json
{
  "id": 1,
  "task": "Learn Spring Boot",
  "completed": false
}
```

---

## Database Interaction

- **Spring Data JPA** is used to automatically translate method calls (e.g., `save()`, `findAll()`) into SQL queries.
- **Hibernate** is the ORM used under the hood to manage the persistence of the `Todo` entity.

---

## Future Enhancements

1. **Authentication & Authorization**: Add user authentication (e.g., using Spring Security).
2. **Persistent Database**: Replace the H2 in-memory database with a persistent database like MySQL or PostgreSQL for production environments.
3. **Testing**: Add more comprehensive unit and integration tests to ensure reliability.

---

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests to improve the project. Please follow the general coding guidelines and make sure to include proper documentation.

