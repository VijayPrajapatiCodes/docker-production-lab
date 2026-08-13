# 🚀 Spring Boot + Docker

## 1. Introduction

Spring Boot application ko Docker container ke andar run kiya ja sakta hai.

Normal setup:

    Java
      ↓
    Maven
      ↓
    Spring Boot
      ↓
    Application

Docker setup:

    Docker Image
         ↓
    Java Runtime
         ↓
    Spring Boot JAR
         ↓
    Container

---

# 2. Why Dockerize Spring Boot?

Docker ke advantages:

- Same environment everywhere
- Easy deployment
- Easy scaling
- Dependency isolation
- Consistent runtime
- Easy integration with MySQL/Redis

---

# 3. Spring Boot Docker Architecture

    Spring Boot
         |
         ↓
    Docker Image
         |
         ↓
    Container
         |
         +---- MySQL
         |
         +---- Redis

---

# 4. Build Spring Boot JAR

Project root mein:

    mvnw.cmd clean package

Linux/macOS:

    ./mvnw clean package

JAR usually:

    target/

ke andar generate hoti hai.

Example:

    target/springboot-learning-0.0.1-SNAPSHOT.jar

---

# 5. Basic Dockerfile

    FROM eclipse-temurin:21-jre

    WORKDIR /app

    COPY target/*.jar app.jar

    EXPOSE 8081

    ENTRYPOINT ["java", "-jar", "app.jar"]

---

# 6. Dockerfile Explanation

## FROM

    FROM eclipse-temurin:21-jre

Java 21 runtime image use karta hai.

## WORKDIR

    WORKDIR /app

Container ke andar working directory set karta hai.

## COPY

    COPY target/*.jar app.jar

Spring Boot JAR ko container ke andar copy karta hai.

## EXPOSE

    EXPOSE 8081

Application ke intended container port ko document karta hai.

## ENTRYPOINT

    ENTRYPOINT ["java", "-jar", "app.jar"]

Container start hone par Spring Boot application run karta hai.

---

# 7. Build Image

    docker build -t springboot-app .

---

# 8. Run Container

    docker run -d \
      --name springboot-app \
      -p 8081:8081 \
      springboot-app

Windows PowerShell mein one-line:

    docker run -d --name springboot-app -p 8081:8081 springboot-app

---

# 9. Check

    docker ps

Logs:

    docker logs springboot-app

Application:

    http://localhost:8081

---

# 10. Important Concept

Container ke andar:

    localhost

ka matlab current Spring Boot container hai.

Agar MySQL alag container mein hai:

    localhost:3306

use nahi karna.

Compose network mein:

    mysql:3306

use karna hai.

---

# 11. Practical Flow

    mvnw.cmd clean package
             ↓
          JAR file
             ↓
       docker build
             ↓
         Image
             ↓
       docker run
             ↓
        Container
             ↓
       Spring Boot

---

# 🎯 Key Points

- Spring Boot ko JAR ke form mein containerize kar sakte hain.
- Java runtime image use hoti hai.
- Dockerfile image banata hai.
- Container Spring Boot application run karta hai.
- Docker Compose ke andar database ko service name se access karna hota hai.