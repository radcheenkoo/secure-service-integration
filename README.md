# Secure Service Integration

A backend application built with **Java and Spring Boot** that demonstrates secure communication between two independent REST services.

The system consists of an authentication service and a text transformation service. It uses JWT-based authentication an internal service-to-service token and PostgreSQL for persistent storage.

## Architecture

The application consists of three containers:

- **auth-api** — handles user registration authentication JWT generation and protected text-processing requests.
- **data-api** — transforms text and accepts requests only from clients providing a valid internal token.
- **PostgreSQL** — stores user accounts and processing history.

## Request flow

1. A user registers or logs in through `auth-api`.
2. After successful authentication, the user receives a JWT.
3. The user sends a text-processing request with the JWT.
4. `auth-api` validates the request and forwards the text to `data-api` using an internal token.
5. `data-api` validates the internal token and returns the transformed text.
6. `auth-api` stores the processing result in PostgreSQL and returns it to the user.

## Technologies

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA / Hibernate
- JWT authentication
- BCrypt password hashing
- PostgreSQL
- Maven
- Docker and Docker Compose
- REST API / HTTP