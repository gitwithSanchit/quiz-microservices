# Quiz Microservices Application

A backend Quiz Application built using Spring Boot Microservices architecture.

## 🚀 Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Eureka Server (Service Registry)
- OpenFeign (Inter-service communication)
- API Gateway
- Postman (API Testing)
- GitHub

---

## 🏗 Architecture

- Eureka Server  
- API Gateway  
- Quiz Service  
- Question Service  

Each service has its own database.

---

## 📌 Features

- Create quiz by category and number of questions
- Fetch quiz questions
- Submit quiz answers
- Calculate score
- Microservice communication using Feign
- Service discovery using Eureka

---

## ▶ How to Run Locally

1. Start Eureka Server  
2. Start API Gateway  
3. Start Question Service  
4. Start Quiz Service  

Make sure PostgreSQL is running and databases are created.

---

## 🔗 Sample Endpoints

Create Quiz:

POST  
