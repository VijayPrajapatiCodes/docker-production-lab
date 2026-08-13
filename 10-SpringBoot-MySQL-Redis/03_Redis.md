# 🔴 Spring Boot + Redis + Docker

## 1. What is Redis?

Redis is an in-memory data store.

It is commonly used for:

- Caching
- Sessions
- OTP storage
- Temporary data
- Rate limiting
- Fast lookups
- Distributed locks

---

# 2. Redis vs MySQL

MySQL:

    Persistent Database

Redis:

    In-memory Data Store

Example:

    User
      ↓
    MySQL
      ↓
    Permanent data

    OTP
      ↓
    Redis
      ↓
    Temporary data

---

# 3. Redis Docker Image

    redis:latest

Run:

    docker run -d \
      --name redis \
      -p 6379:6379 \
      redis:latest

Windows one-line:

    docker run -d --name redis -p 6379:6379 redis:latest

---

# 4. Redis Port

Default Redis port:

    6379

Host:

    localhost:6379

Container-to-container:

    redis:6379

---

# 5. Docker Compose

    services:

      redis:
        image: redis:latest

---

# 6. Spring Boot Connection

If Redis service name is:

    redis

Spring Boot should connect to:

    redis:6379

Not:

    localhost:6379

when Spring Boot itself is running inside another container.

---

# 7. Environment Variables

    environment:
      REDIS_HOST: redis
      REDIS_PORT: 6379

Spring Boot:

    spring:
      data:
        redis:
          host: ${REDIS_HOST}
          port: ${REDIS_PORT}

---

# 8. Practical

Start Redis:

    docker compose up -d redis

Check:

    docker compose ps

Logs:

    docker compose logs redis

Enter Redis CLI:

    docker compose exec redis redis-cli

Test:

    SET name Vijay

    GET name

Expected:

    "Vijay"

Exit:

    exit

---

# 9. Spring Boot Architecture

    Spring Boot
         |
         ↓
    redis:6379
         |
         ↓
    Redis Container

---

# 10. Example Use Case

OTP:

    User requests OTP
          ↓
    Spring Boot
          ↓
    Redis
          ↓
    OTP stored with expiration
          ↓
    User verifies OTP
          ↓
    Spring Boot
          ↓
    Redis GET
          ↓
    Verification

---

# 🎯 Key Points

- Redis is an in-memory data store.
- Default port is 6379.
- Redis is commonly used for caching and temporary data.
- Docker Compose service name can be used as DNS.
- Spring Boot container should use `redis:6379`.