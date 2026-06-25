# Expense Tracker API

Reactive REST API built with Spring WebFlux and R2DBC that serves as the backend for the [Expense Tracker](https://github.com/alfredogit14/react-expense-tracker) frontend.

## Tech Stack

- Java 21
- Spring Boot 3.2
- Spring WebFlux (reactive, non-blocking)
- Spring Data R2DBC (reactive database access)
- Spring Security + JWT authentication
- H2 in-memory database
- Functional routing style

## Endpoints

| Method | Endpoint | Auth | Description |
|--------|----------|------|-------------|
| POST | `/api/auth/login` | No | Login and get JWT token |
| GET | `/api/transactions` | Yes | Get all transactions for current user |
| POST | `/api/transactions` | Yes | Create a new transaction |
| DELETE | `/api/transactions/{id}` | Yes | Delete a transaction |

## Getting Started

**Requirements:** Java 21, Gradle

```bash
git clone https://github.com/alfredogit14/expense-tracker-api.git
cd expense-tracker-api
./gradlew bootRun
```

Server starts on `http://localhost:8080`

## Usage Example

**Login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "demo", "password": "password123"}'
```

**Create transaction:**
```bash
curl -X POST http://localhost:8080/api/transactions \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"description": "Groceries", "amount": -50.0, "category": "food"}'
```

**Get all transactions:**
```bash
curl http://localhost:8080/api/transactions \
  -H "Authorization: Bearer <token>"
```

## Demo credentials

| Username | Password |
|----------|----------|
| demo | password123 |
| admin | admin123 |
