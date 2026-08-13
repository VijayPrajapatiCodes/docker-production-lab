# 🐳 Docker Production Lab

> A hands-on Docker learning and practical repository focused on containerizing Spring Boot applications and building production-oriented multi-container environments.

![Docker](https://img.shields.io/badge/Docker-Containerization-2496ED?logo=docker&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Backend-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-Database-4479A1?logo=mysql&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-Cache-DC382D?logo=redis&logoColor=white)
![Docker Compose](https://img.shields.io/badge/Docker%20Compose-Multi--Container-2496ED?logo=docker&logoColor=white)
![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)

---

## 📌 About This Repository

**Docker Production Lab** is my hands-on Docker learning repository where I document Docker concepts and build practical projects around **Java/Spring Boot backend applications**.

The repository covers the journey from Docker fundamentals to running a Spring Boot application with MySQL and Redis using Docker Compose and production-oriented Docker practices.

The focus is on:

- Understanding Docker fundamentals
- Working with Docker images and containers
- Creating Dockerfiles
- Containerizing Spring Boot applications
- Docker networking
- Docker volumes
- Docker Compose
- Spring Boot + MySQL + Redis
- Environment variables
- Health checks
- Container logs
- Multi-stage Docker builds
- Resource limits
- Docker image optimization

---

# 🧠 What I Learned

## 01. Docker Fundamentals

Covered the core concepts required to understand Docker:

- Docker basics
- Containers
- Images
- Docker CLI
- Container lifecycle
- Running containers
- Managing containers
- Basic Docker commands

Basic Docker flow:

```text
Docker Image
      ↓
Docker Container
      ↓
Running Application
```

---

# 02. Docker Installation

Learned how to set up Docker and verify the Docker environment.

Basic verification:

```bash
docker --version
docker info
docker run hello-world
```

---

# 03. Docker Commands

Practiced commonly used Docker commands.

### Containers

```bash
docker ps
docker ps -a
docker run
docker start
docker stop
docker restart
docker rm
docker rm -f
```

### Images

```bash
docker images
docker pull
docker build
docker rmi
docker image inspect
```

### Logs

```bash
docker logs
docker logs -f
```

### Volumes

```bash
docker volume ls
docker volume inspect
docker volume rm
```

### Networks

```bash
docker network ls
docker network inspect
```

---

# 04. Docker Images

Learned how Docker images work and how containers are created from images.

Basic flow:

```text
Dockerfile
     ↓
docker build
     ↓
Docker Image
     ↓
docker run
     ↓
Container
```

Example:

```bash
docker build -t my-app .
```

Run:

```bash
docker run -d --name my-app my-app
```

---

# 05. Dockerfile

Learned how to create custom Docker images using a Dockerfile.

Important Dockerfile instructions covered:

```text
FROM
WORKDIR
COPY
RUN
EXPOSE
ENV
CMD
ENTRYPOINT
```

Example:

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
```

Dockerfile flow:

```text
Dockerfile
     ↓
Docker Build
     ↓
Docker Image
     ↓
Docker Container
```

---

# 06. Spring Boot + Docker

Learned how to containerize a Spring Boot application.

Basic flow:

```text
Spring Boot Application
          ↓
       Maven Build
          ↓
         JAR
          ↓
      Docker Image
          ↓
       Container
```

Build Spring Boot JAR:

```bash
mvnw.cmd clean package
```

Build Docker image:

```bash
docker build -t springboot-app .
```

Run:

```bash
docker run -d --name springboot-app -p 8081:8081 springboot-app
```

Check:

```bash
docker ps
```

Logs:

```bash
docker logs springboot-app
```

---

# 07. Docker Networking

Learned how Docker containers communicate with each other.

Topics covered:

- Docker networking basics
- Bridge networks
- Container-to-container communication
- Port mapping
- Docker DNS
- Service names

Example:

```text
Spring Boot Container
        |
        | mysql:3306
        ↓
MySQL Container
```

Inside Docker networking, services can communicate using their service/container names.

For example:

```text
mysql:3306
```

instead of:

```text
localhost:3306
```

because inside a container:

```text
localhost
   ↓
Current Container
```

Docker Compose provides a network where services can discover each other using service names.

---

# 08. Docker Volumes

Learned how Docker volumes provide persistent storage.

Without a volume:

```text
MySQL Container
      ↓
    Data
      ↓
Container Removed
      ↓
Potential Data Loss
```

With a volume:

```text
MySQL Container
      ↓
/var/lib/mysql
      ↓
mysql-data Volume
      ↓
Persistent Data
```

Example:

```yaml
services:

  mysql:
    image: mysql:8

    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  mysql-data:
```

Useful commands:

```bash
docker volume ls
docker volume inspect mysql-data
docker volume rm mysql-data
```

Covered:

- Named volumes
- Bind mounts
- Persistent data
- MySQL volumes
- Volume inspection
- Volume lifecycle

---

# 09. Docker Compose

Learned how Docker Compose manages multiple services together.

Instead of manually running:

```text
Spring Boot
MySQL
Redis
```

Docker Compose can manage the complete application stack.

Basic command:

```bash
docker compose up -d
```

Build and start:

```bash
docker compose up -d --build
```

Check services:

```bash
docker compose ps
```

Logs:

```bash
docker compose logs
```

Follow logs:

```bash
docker compose logs -f
```

Stop:

```bash
docker compose down
```

Compose can manage:

- Services
- Networks
- Volumes
- Environment variables
- Service dependencies
- Health checks

---

# 10. Spring Boot + MySQL + Redis

Built a multi-container backend architecture using Docker Compose.

Architecture:

```text
                    Docker Compose
                         |
          ┌──────────────┼──────────────┐
          ↓              ↓              ↓
     Spring Boot       MySQL          Redis
      Container       Container      Container
          |              |              |
          |              ↓              |
          |         mysql-data          |
          |            Volume            |
          |                              |
          └──────── Docker Network ──────┘
```

Spring Boot communicates with MySQL:

```text
mysql:3306
```

Spring Boot communicates with Redis:

```text
redis:6379
```

---

# 🐬 MySQL with Docker

MySQL can run as a Docker service.

Example:

```yaml
mysql:
  image: mysql:8

  environment:
    MYSQL_ROOT_PASSWORD: root
    MYSQL_DATABASE: backend_db

  volumes:
    - mysql-data:/var/lib/mysql
```

Spring Boot database URL inside Docker:

```text
jdbc:mysql://mysql:3306/backend_db
```

Important:

```text
mysql
  ↓
Docker Compose service name
```

---

# 🔴 Redis with Docker

Redis is used as an in-memory data store and is useful for use cases such as:

- Caching
- Temporary data
- OTP storage
- Sessions
- Fast lookups

Redis default port:

```text
6379
```

Docker Compose:

```yaml
redis:
  image: redis:latest
```

Spring Boot container:

```text
redis:6379
```

Basic Redis practical:

```bash
docker compose exec redis redis-cli
```

Test:

```text
SET name Vijay
GET name
```

---

# 🔐 Environment Variables

Learned how to keep configuration outside application code.

Example:

```yaml
environment:

  DB_HOST: mysql
  DB_PORT: 3306
  DB_NAME: backend_db
  DB_USERNAME: root
  DB_PASSWORD: root

  REDIS_HOST: redis
  REDIS_PORT: 6379
```

Also covered:

```text
.env
.env.example
${VARIABLE}
```

Environment variables are useful for separating:

```text
Application Code
      +
Environment Configuration
```

Sensitive credentials should not be committed to Git.

---

# 🏭 Production Docker

Covered production-oriented Docker concepts:

```text
Multi-Stage Builds
Health Checks
Docker Logs
Resource Limits
Image Optimization
```

---

# 11. Multi-Stage Docker Build

Learned how to separate application building from application runtime.

Build stage:

```text
Maven
JDK
Source Code
     ↓
Spring Boot JAR
```

Runtime stage:

```text
JRE
+
Spring Boot JAR
```

Example:

```dockerfile
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
```

Benefits:

- Smaller final image
- Build tools excluded from final image
- Cleaner production image
- Better separation between build and runtime

Flow:

```text
Source Code
     ↓
Maven Build Stage
     ↓
JAR
     ↓
Runtime Stage
     ↓
Final Docker Image
     ↓
Container
```

---

# ❤️ 12. Health Checks

Learned that:

```text
Container Running
       ≠
Application Healthy
```

Health checks can verify whether a service is actually healthy.

Spring Boot Actuator health endpoint:

```text
/actuator/health
```

Example:

```json
{
  "status": "UP"
}
```

MySQL health check can use:

```text
mysqladmin ping
```

Example:

```yaml
healthcheck:
  test:
    [
      "CMD",
      "mysqladmin",
      "ping",
      "-h",
      "localhost",
      "-uroot",
      "-proot"
    ]
  interval: 5s
  timeout: 5s
  retries: 10
```

Compose can use health conditions for service dependencies.

---

# 📋 13. Docker Logs

Learned how to inspect container logs for debugging and monitoring.

View logs:

```bash
docker logs springboot-app
```

Follow logs:

```bash
docker logs -f springboot-app
```

Last 100 lines:

```bash
docker logs --tail 100 springboot-app
```

Docker Compose:

```bash
docker compose logs app
```

Follow:

```bash
docker compose logs -f app
```

Logs are useful for:

- Application errors
- Startup problems
- Database connection problems
- Runtime debugging
- Monitoring

---

# ⚡ 14. Resource Limits

Learned the concept of controlling container resource consumption.

Main resources:

```text
CPU
Memory
```

Example:

```yaml
services:

  app:
    build: .
    mem_limit: 512m
```

Resource limits can help prevent one service from consuming excessive host resources.

Resource configuration and enforcement can depend on the Docker/Compose deployment environment.

---

# 🏎️ 15. Docker Image Optimization

Learned production-oriented image optimization.

Main concepts:

- Multi-stage builds
- Runtime-only images
- `.dockerignore`
- Avoiding unnecessary files
- Docker layer caching
- Smaller images
- Cleaner production images

Example:

```text
Build Environment
       ↓
      JAR
       ↓
Runtime-only Image
       ↓
Production Container
```

`.dockerignore` example:

```text
.git
.idea
*.log
screenshots
README.md
```

When using a Dockerfile that copies a JAR from the host `target/` directory, do not ignore `target/`.

When Docker itself builds the JAR in a multi-stage build, ignoring the host `target/` directory is generally appropriate.

---

# 🧪 Practical Docker Workflow

The practical workflow used in this repository:

```text
Write Spring Boot Application
          ↓
Build JAR
          ↓
Create Dockerfile
          ↓
Build Docker Image
          ↓
Run Container
          ↓
Create Docker Network
          ↓
Connect Containers
          ↓
Add MySQL
          ↓
Add Redis
          ↓
Add Docker Volume
          ↓
Use Docker Compose
          ↓
Add Environment Variables
          ↓
Add Health Checks
          ↓
Inspect Logs
          ↓
Use Multi-Stage Build
          ↓
Optimize Production Image
```

---

# 🏗️ Main Practical Architecture

```text
                         Docker Compose
                              |
               ┌──────────────┼──────────────┐
               |              |              |
               ↓              ↓              ↓
        Spring Boot         MySQL          Redis
          :8081              :3306          :6379
               |              |
               |              ↓
               |         mysql-data
               |           Volume
               |
               └────── Docker Network
```

Spring Boot:

```text
Application
    ↓
Docker Container
```

MySQL:

```text
MySQL Container
    ↓
Persistent Volume
```

Redis:

```text
Redis Container
    ↓
Fast In-Memory Data
```

---

# 📂 Repository Structure

```text
docker-production-lab/
│
├── 01-Docker-Fundamentals/
├── 02-Docker-Installation/
├── 03-Docker-Commands/
├── 04-Docker-Images/
├── 05-Dockerfile/
├── 06-Spring-Boot-Docker/
├── 07-Docker-Networking/
├── 08-Docker-Volumes/
├── 09-Docker-Compose/
├── 10-SpringBoot-MySQL-Redis/
├── 11-Production-Docker/
│
├── screenshots/
│   ├── Bash.png
│   ├── Docker.png
│   ├── DockerCLI.png
│   └── SpringbootAPI.png
│
├── springboot-learning/
├── SpringDocker/
│
├── README.md
└── .gitignore
```

---

# 📸 Practical Screenshots

## Docker Environment

![Docker](screenshots/Docker.png)

## Docker CLI

![Docker CLI](screenshots/DockerCLI.png)

## Bash / Terminal

![Bash](screenshots/Bash.png)

## Spring Boot API

![Spring Boot API](screenshots/SpringbootAPI.png)

---

# 🎯 Key Skills Demonstrated

```text
Docker
Docker CLI
Docker Images
Docker Containers
Dockerfile
Spring Boot Containerization
Docker Networking
Docker DNS
Port Mapping
Docker Volumes
Docker Compose
MySQL Containers
Redis Containers
Environment Variables
Health Checks
Container Logs
Multi-Stage Builds
Resource Limits
Image Optimization
```

---

# 🚀 Learning Progression

```text
Docker Fundamentals
        ↓
Docker Installation
        ↓
Docker Commands
        ↓
Docker Images
        ↓
Dockerfile
        ↓
Spring Boot + Docker
        ↓
Docker Networking
        ↓
Docker Volumes
        ↓
Docker Compose
        ↓
Spring Boot + MySQL + Redis
        ↓
Production Docker
```

---

# 👨‍💻 Author

## Vijay Prajapati

Java Backend Developer focused on building backend applications with:

```text
Java
Spring Boot
Spring Security
MySQL
SQL
Docker
Redis
REST APIs
```

---

# ⭐ Repository Goal

The goal of this repository is to develop a strong practical understanding of Docker and its integration with Java/Spring Boot backend applications.

This repository follows a simple approach:

```text
Learn
  ↓
Practice
  ↓
Build
  ↓
Containerize
  ↓
Connect
  ↓
Persist
  ↓
Optimize
```

> **Docker is not just about running containers — the goal is to understand how backend services are built, connected, persisted, and prepared for production.**
