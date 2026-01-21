# Digital Library Management System

A backend-focused Digital Library Management System built using Java and Spring Boot.  
This project provides REST APIs to manage books, users, book issue/return flow, and automatic fine calculation using MySQL.

---

## 🚀 Features

- Add and manage books
- Register users
- Issue books to users
- Return books with fine calculation
- RESTful APIs tested using Postman
- MySQL database integration
- Clean layered architecture

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- MySQL
- Maven
- Postman
- Git & GitHub

---

## 📂 Project Structure

src/main/java/com/pari/library
│
├── controller → REST controllers
├── service → Business logic
├── repository → JPA repositories
├── model → Entity classes
├── config → Security configuration
└── DigitalLibraryApplication.java


---

## 🔄 Core Functionalities

### Issue Book
- Checks availability of book copies
- Decreases available copies
- Stores issue date
- Maps user and book relationship

### Return Book
- Updates return date
- Increases available copies
- Calculates fine after 7 days (₹10/day)

---

## 📌 API Endpoints

| Method | Endpoint               | Description              |
|-------|------------------------|--------------------------|
| GET   | `/books`               | Get all books            |
| POST  | `/books`               | Add a new book           |
| POST  | `/users`               | Register user            |
| POST  | `/issue`               | Issue a book             |
| PUT   | `/issue/return/{id}`   | Return issued book       |

---

## 🗄 Database

- MySQL
- Tables auto-created using JPA
- Proper relationships between User, Book, and IssuedBook entities

---

## ▶️ How to Run Locally

1. Clone the repository
2. Create MySQL database:
   ```sql
   CREATE DATABASE digital_library;

Update application.properties with database credentials

Run the application

Test APIs using Postman

👩‍💻 Author

Pari
CSE Graduate | Java Backend Developer