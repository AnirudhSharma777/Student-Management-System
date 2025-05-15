# Student Management System

A full-featured Student Management System built using **Spring Boot**, **MySQL (via Docker)**, and **JPA/Hibernate**. This application provides RESTful APIs for managing student data including create, read, update, and delete operations.

---

## 📦 Features

- CRUD operations for Student entities
- Embedded Address object per student
- Uses MySQL container via Docker
- Secure password handling (with future support for hashing)
- Exception handling (e.g., student not found)
- DTO-based request/response layer
- Configurable with `application.yml`

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Data JPA
- MySQL 8 (via Docker)
- Hibernate
- Lombok

---

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/AnirudhSharma777/Student-Management-System.git
cd student-management-system
````

### 2. Start MySQL using Docker Compose

```bash
docker-compose up -d
```

This sets up a MySQL 8 container with:

* Database: `student_db`
* User: `username`
* Password: `password`

### 3. Configure Spring Boot

Ensure your `application.yml` contains:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/student_db
    username: username
    password: password
    driver-class-name: com.mysql.cj.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.MySQLDialect
```

### 4. Run the Application

Use your IDE or the command line:

```bash
./gradlew bootRun
```

## 🧪 API Endpoints

| Method | Endpoint             | Description             |
| ------ | -------------------- | ----------------------- |
| GET    | `/api/students`      | List all students       |
| GET    | `/api/students/{id}` | Get student by ID       |
| POST   |`/api/v1/auth/register`| Create new student     |
| POST   |`/api/v1/auth/login`  | Create new student      |
| PUT    | `/api/students/{id}` | Update existing student |
| DELETE | `/api/students/{id}` | Delete student by ID    |

---

## 🗃 Project Structure

```
├── config/
├── controller/
├── dto/
├── entities/
├── exceptions/
├── mapper/
├── repository/
├── service/
├── ManagementSystemApplication.java
```

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
