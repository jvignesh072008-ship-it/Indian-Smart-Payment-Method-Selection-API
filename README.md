# Indian-Smart-Payment-Method-Selection-API
Smart Payment Selection API built with Java 26, Spring Boot, Maven, and Lombok. Automatically chooses UPI, IMPS, or NEFT based on transaction rules using the Strategy Design Pattern. No database or repository layer required.

# Smart Payment Method Selection API

## Overview

The **Smart Payment Method Selection API** is a Java Spring Boot capstone project that simulates an intelligent banking payment gateway. Unlike traditional payment systems where users must manually choose a payment method, this API automatically determines the most suitable payment method based on transaction details and predefined business rules.

The application is built using **Java 26**, **Spring Boot**, **Maven**, and **Lombok**, following a clean layered architecture without using any database or repository layer. All transaction processing is performed in memory.

---

## Features

* Automatic payment method selection
* Dynamic request handling through REST APIs
* No database required
* No repository layer
* Strategy Design Pattern implementation
* Layered architecture
* Input validation
* Global exception handling
* RESTful API design
* JSON request and response
* Easily extensible for future payment methods

---

## Technology Stack

* Java 26
* Spring Boot
* Maven
* Lombok
* Spring Web
* Spring Validation
* Jackson
* REST API
* Strategy Design Pattern

---

## Project Architecture

```text
src
└── main
    ├── java
    │   └── com.example.smartpayment
    │       ├── controller
    │       ├── service
    │       ├── service.impl
    │       ├── strategy
    │       ├── dto
    │       ├── model
    │       ├── enums
    │       ├── exception
    │       ├── config
    │       ├── util
    │       └── SmartPaymentApplication.java
    └── resources
        └── application.properties
```

---

## Business Problem

In India, multiple payment methods such as **UPI**, **IMPS**, and **NEFT** are available for transferring money. Users often need to decide which payment method is appropriate based on transaction amount and banking rules.

This project eliminates that decision-making process by automatically selecting the best payment method without requiring the user to specify it.

---

## Payment Selection Rules

| Condition                                        | Payment Method |
| ------------------------------------------------ | -------------- |
| Amount ≤ ₹5,000                                  | UPI            |
| Amount > ₹5,000 and ≤ ₹2,00,000 (Same Bank)      | IMPS           |
| Amount > ₹5,000 and ≤ ₹2,00,000 (Different Bank) | NEFT           |
| Amount > ₹2,00,000                               | NEFT           |

These rules can be easily extended to support additional payment methods.

---

## API Endpoint

**POST**

```http
/api/payment/transfer
```

---

## Sample Request

```json
{
  "sender": {
    "senderName": "Arun",
    "accountNumber": "1234567890",
    "bankName": "SBI",
    "amount": 7500,
    "purpose": "Rent"
  },
  "receiver": {
    "receiverName": "Rahul",
    "accountNumber": "9988776655",
    "bankName": "HDFC"
  }
}
```

---

## Sample Response

```json
{
  "transactionId": "TXN202600001",
  "receiverName": "Rahul",
  "receiverAccount": "9988776655",
  "amount": 7500,
  "paymentMethod": "NEFT",
  "status": "SUCCESS",
  "message": "Money transferred successfully.",
  "timestamp": "2026-07-13T18:45:30"
}
```

---

## Project Highlights

* Automatic payment method identification
* Dynamic transaction processing
* Clean REST API design
* Strategy Pattern for payment selection
* Robust exception handling
* Request validation
* Production-style folder structure
* No persistent storage required
* Easily scalable and maintainable

---

## Validation

The application validates:

* Sender name
* Receiver name
* Account number
* Bank name
* Amount greater than zero
* Required request fields
* Invalid or malformed JSON

---

## Exception Handling

The project includes centralized exception handling for:

* Invalid Amount
* Invalid Account Number
* Invalid Bank Name
* Missing Required Fields
* Validation Errors
* Unexpected Exceptions

---

## Future Enhancements

* RTGS payment support
* Payment gateway integration
* Transaction history
* User authentication with JWT
* Transaction logging
* Notification service (SMS/Email)
* Rule configuration from external files
* Unit and integration testing
* Docker deployment
* Cloud deployment

---

## Learning Outcomes

This project demonstrates:

* Spring Boot REST API development
* Java 26 programming
* Maven project management
* Lombok usage
* Strategy Design Pattern
* Layered Architecture
* Exception Handling
* Request Validation
* JSON Serialization/Deserialization
* Clean Code Principles
* SOLID Design Principles

---

## Author

**Vignesh J**

Artificial Intelligence and Data Science

Java Spring Boot Capstone Project

---

## License

This project is developed for educational and learning purposes.

