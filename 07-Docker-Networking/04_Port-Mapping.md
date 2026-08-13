# 🔌 Docker Port Mapping

## 1. What is Port Mapping?

Port mapping allows applications running inside containers to be accessed from the Docker host.

Syntax:

    -p HOST_PORT:CONTAINER_PORT

Example:

    -p 8080:80

Meaning:

    Host Port 8080
          |
          v
    Container Port 80

---

# 2. Example

Nginx normally listens on:

    80

Run:

    docker run -d \
      --name nginx-network \
      -p 8080:80 \
      nginx

Windows PowerShell:

    docker run -d --name nginx-network -p 8080:80 nginx

---

# 3. Browser Access

Open:

    http://localhost:8080

Flow:

    Browser
       |
       v
    localhost:8080
       |
       v
    Docker Host
       |
       | 8080 -> 80
       v
    Nginx Container
       |
       v
      :80

---

# 4. Host Port vs Container Port

Example:

    -p 8080:80

    8080 = Host Port
    80   = Container Port

---

# 5. Spring Boot Example

Spring Boot runs on:

    8080

Docker:

    docker run -d \
      --name spring-app \
      -p 8080:8080 \
      spring-docker:1.0

Now:

    http://localhost:8080

accesses Spring Boot.

---

# 6. Different Host Port

If host port 8080 is already used:

    docker run -d \
      --name spring-app \
      -p 9090:8080 \
      spring-docker:1.0

Now:

    localhost:9090

maps to:

    container:8080

---

# 7. Container-to-Container vs Port Mapping

Container-to-container:

    spring-app
        |
        v
    mysql:3306

No host port is required for this communication.

Host-to-container:

    Browser
       |
       v
    localhost:8080
       |
       v
    spring-app:8080

Port mapping is required.

---

# 🧪 Practical

## Remove old Nginx

    docker rm -f nginx-network

## Start with port mapping

    docker run -d \
      --name nginx-network \
      --network backend-network \
      -p 8080:80 \
      nginx

Windows PowerShell:

    docker run -d --name nginx-network --network backend-network -p 8080:80 nginx

## Verify

    docker ps

Expected:

    0.0.0.0:8080->80/tcp

## Browser

    http://localhost:8080

---

# 8. Multiple Containers

Container 1:

    -p 8080:80

Container 2:

    -p 8081:80

Example:

    docker run -d --name nginx1 -p 8080:80 nginx

    docker run -d --name nginx2 -p 8081:80 nginx

Access:

    http://localhost:8080
    http://localhost:8081

---

# Important

Two containers cannot normally publish the same host port.

Wrong:

    nginx1 -> 8080:80
    nginx2 -> 8080:80

Correct:

    nginx1 -> 8080:80
    nginx2 -> 8081:80

---

# Summary

Port mapping connects:

    HOST PORT
        ↓
    CONTAINER PORT

Syntax:

    -p HOST:CONTAINER

Example:

    -p 8080:80