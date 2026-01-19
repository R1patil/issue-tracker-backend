# Issue Tracker Backend API

A **company-grade Spring Boot REST API** implementing **JWT Authentication**, **role-ready security**, **global exception handling**, and **MySQL persistence**. This project is designed to reflect **real-world backend engineering practices** used in production systems.

---

## 🚀 Tech Stack

* **Java 21**
* **Spring Boot 3.3.2**
* **Spring Security (JWT based)**
* **Spring Data JPA (Hibernate)**
* **MySQL**
* **BCrypt Password Encoder**
* **Maven**

---

## 🧠 High-Level Architecture

```
Client (Postman / Frontend)
        │
        ▼
AuthController / UserController
        │
        ▼
UserService (Business Logic)
        │
        ▼
UserRepository (JPA)
        │
        ▼
MySQL Database
```

JWT is used to **secure APIs** after login. Requests flow through a **JWT filter**, which validates the token before allowing access.

---

## 🔐 Authentication Flow (JWT)

1. User registers with **email + password**
2. Password is **hashed using BCrypt** and stored in DB
3. User logs in using `/api/auth/login`
4. Server:

   * Validates email
   * Matches password
   * Generates **JWT token**
5. Client sends token in every request:

```
Authorization: Bearer <JWT_TOKEN>
```

6. `JwtFilter` validates token and sets authentication context

---

## 📌 API Endpoints

### 🔑 Authentication APIs

#### ▶ Register User

```
POST /api/users/register
```

**Request Body**

```json
{
  "name": "Om",
  "email": "om@test.com",
  "password": "password123"
}
```

**Behavior**

* Encrypts password using BCrypt
* Saves user to DB

---

#### ▶ Login User

```
POST /api/auth/login
```

**Request Body**

```json
{
  "email": "om@test.com",
  "password": "password123"
}
```

**Response**

```
JWT_TOKEN_STRING
```

---

### 👤 User APIs (Protected)

#### ▶ Get All Users

```
GET /api/users
```

**Headers**

```
Authorization: Bearer <JWT_TOKEN>
```

---

## 🧪 Sample Database State

```
mysql> select * from users;
+----+----------------+-------+--------------------------------------------------------------+
| id | email          | name  | password                                                     |
+----+----------------+-------+--------------------------------------------------------------+
|  1 | rahul@test.com | Rahul | $2a$10$5lxqZNRsEdyUHuUcgTCV2uDe.rAUoV9TNrfHhwYo0HlXv2J8AaCUW |
|  4 | sam@test.com   | Sam   | NULL                                                         |
|  7 | om@test.com    | Om    | $2a$10$wRhQK1n4nsLIB4nsgE6jOOFVdCoG83ZE6NLK6dTrZBrAUQTYyrabK |
+----+----------------+-------+--------------------------------------------------------------+
```

---

## ⚠️ Global Exception Handling

All controller-level exceptions are handled using:

```
@RestControllerAdvice
```

Handled cases:

* User not found
* Invalid credentials
* Validation errors
* Runtime exceptions

Returns **clean JSON responses** instead of stack traces.

---

## 🧯 Common Errors Explained (Interview Ready)

### ❌ 403 Forbidden

* Missing JWT token
* Invalid token
* Endpoint not permitted in SecurityConfig

### ❌ 415 Unsupported Media Type

* Missing header:

```
Content-Type: application/json
```

### ❌ 500 Internal Server Error

* Weak JWT secret key
* NULL password in DB
* Missing BCrypt encoding

---

## 🛡 Security Design Decisions

* Passwords are **never stored in plain text**
* JWT secret key >= **256 bits**
* Stateless authentication (no sessions)
* Filter-based token validation

---

## 🏗️ Project Structure

```
src/main/java/com/company/issuetracker
│
├── config        → Security & Bean configs
├── controller    → REST APIs
├── dto           → Request/Response DTOs
├── entity        → JPA entities
├── exception     → Global exception handler
├── repository    → JPA repositories
├── security      → JWT filter & util
├── service       → Business logic
```

---

## 📦 How to Run Locally

```bash
mvn clean spring-boot:run
```

Server starts at:

```
http://localhost:8080
```

---

## 📈 Future Enhancements

* Role-based authorization (ADMIN / USER)
* Refresh tokens
* Issue / Ticket module
* Swagger (OpenAPI)
* Docker support

---

## 👨‍💻 Author

**Rahul Patil**
Backend Developer | Java | Spring Boot | Security

