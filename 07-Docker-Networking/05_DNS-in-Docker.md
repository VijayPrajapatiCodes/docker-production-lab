# 🧠 DNS in Docker

## 1. What is DNS?

DNS means:

    Domain Name System

DNS translates names into IP addresses.

Example:

    google.com
         ↓
    IP Address

Docker also provides internal DNS for containers on user-defined networks.

---

# 2. Docker Container DNS

Suppose:

    backend-network
          |
          +---- nginx-network
          |
          +---- alpine-test

Nginx has:

    172.18.0.2

Docker DNS allows:

    nginx-network

to resolve to:

    172.18.0.2

---

# 3. Create Network

    docker network create --driver bridge backend-network

---

# 4. Start Nginx

    docker run -d \
      --name nginx-network \
      --network backend-network \
      nginx

Windows PowerShell:

    docker run -d --name nginx-network --network backend-network nginx

---

# 5. Start Alpine

    docker run -d \
      --name alpine-test \
      --network backend-network \
      alpine sleep 3600

---

# 6. Enter Alpine

    docker exec -it alpine-test sh

---

# 7. Resolve Container Name

Inside Alpine:

    getent hosts nginx-network

Example:

    172.18.0.2 nginx-network

This proves Docker DNS resolved the container name.

---

# 8. Use Container Name

Inside Alpine:

    wget -qO- http://nginx-network

Expected:

    Welcome to nginx!

Docker resolves:

    nginx-network

to the appropriate container IP.

---

# 9. Why Use Container Name Instead of IP?

Do not hard-code:

    172.18.0.2

because container IP addresses can change.

Prefer:

    nginx-network

Docker DNS resolves the name dynamically.

---

# 10. localhost Problem

Inside Alpine:

    localhost

means:

    alpine-test

It does not mean:

    nginx-network

Therefore:

    wget -qO- http://localhost

will not access Nginx unless something is listening inside Alpine itself.

Use:

    wget -qO- http://nginx-network

---

# 11. DNS Flow

    Application
         |
         v
    nginx-network
         |
         v
    Docker DNS
         |
         v
    172.18.x.x
         |
         v
    Nginx Container

---

# 🧪 Complete Practical

## Create Network

    docker network create --driver bridge backend-network

## Nginx

    docker run -d --name nginx-network --network backend-network nginx

## Alpine

    docker run -d --name alpine-test --network backend-network alpine sleep 3600

## Enter Alpine

    docker exec -it alpine-test sh

## DNS Resolution

    getent hosts nginx-network

Expected:

    172.x.x.x nginx-network

## HTTP Communication

    wget -qO- http://nginx-network

Expected:

    Welcome to nginx!

## Exit

    exit

---

# 12. DNS + Spring Boot + MySQL

Real application:

    backend-network
          |
          +---- spring-app
          |
          +---- mysql
          |
          +---- redis

Spring Boot:

    spring.datasource.url=jdbc:mysql://mysql:3306/backend_db

Redis:

    spring.data.redis.host=redis
    spring.data.redis.port=6379

Here:

    mysql
    redis

are resolved through Docker's internal networking/DNS.

---

# 13. DNS + Port

MySQL:

    mysql:3306

Redis:

    redis:6379

Spring Boot:

    spring-app:8080

The format is:

    container-name:container-port

---

# Important Rules

## Rule 1

Same Docker network is required for normal container-name based communication.

## Rule 2

Use container/service name instead of hard-coded IP.

## Rule 3

localhost refers to the current container.

## Rule 4

Host port mapping is mainly for exposing a container service to the host/external clients.

---

# Cleanup

    docker rm -f nginx-network alpine-test

    docker network rm backend-network

---

# Final Architecture

    Docker Host
         |
         | localhost:8080
         v
    Spring Boot Container
         |
         | mysql:3306
         v
    MySQL Container

    Spring Boot
         |
         | redis:6379
         v
    Redis Container

Docker DNS resolves:

    mysql -> MySQL Container IP
    redis -> Redis Container IP