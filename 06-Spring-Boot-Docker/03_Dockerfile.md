# 🐳 Spring Boot Dockerfile

## 1. Introduction

Dockerfile ek text file hoti hai jisme Docker image build karne ke instructions define hote hain.

Spring Boot ke case mein Dockerfile JAR ko Docker image ke andar package karne mein help karta hai.

### Basic Flow

```text
Spring Boot JAR
      ↓
Dockerfile
      ↓
Docker Image
      ↓
Docker Container
      ↓
Spring Boot Application
```

---

# 2. Project Structure

```text
SpringDocker/
│
├── src/
│
├── target/
│   └── SpringDocker-0.0.1-SNAPSHOT.jar
│
├── Dockerfile
├── pom.xml
├── mvnw
└── mvnw.cmd
```

Dockerfile project ke root folder mein hona chahiye.

---

# 3. Complete Dockerfile

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# 4. FROM

```dockerfile
FROM eclipse-temurin:21-jre
```

`FROM` Docker image ka base define karta hai.

Yahan Java 21 JRE use ho raha hai.

```text
Base Image
     ↓
Java 21 JRE
     ↓
Spring Boot JAR
```

---

# 5. WORKDIR

```dockerfile
WORKDIR /app
```

Container ke andar working directory set karta hai.

Application files `/app` ke andar work karengi.

Example:

```text
/app
   └── app.jar
```

---

# 6. COPY

```dockerfile
COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar
```

Host machine se JAR Docker image ke andar copy hoti hai.

Host:

```text
target/SpringDocker-0.0.1-SNAPSHOT.jar
```

Container:

```text
/app/app.jar
```

---

# 7. EXPOSE

```dockerfile
EXPOSE 8080
```

Container application ke port ko document/declare karta hai.

Spring Boot:

```text
8080
```

Important:

`EXPOSE` host port publish nahi karta.

Host port publish karne ke liye:

```powershell
-p 8080:8080
```

use karte hain.

---

# 8. ENTRYPOINT

```dockerfile
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Container start hone par:

```bash
java -jar app.jar
```

execute hota hai.

Isse Spring Boot application start hoti hai.

---

# 🧪 PRACTICAL

## Step 1 — JAR Build

```powershell
.\mvnw.cmd clean package -DskipTests
```

---

## Step 2 — JAR Check

```powershell
dir target
```

JAR available honi chahiye:

```text
SpringDocker-0.0.1-SNAPSHOT.jar
```

---

## Step 3 — Dockerfile Create

Project root mein `Dockerfile` banao:

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## Step 4 — Image Build

```powershell
docker build -t spring-docker:1.0 .
```

---

## Step 5 — Image Check

```powershell
docker image ls
```

Specific image:

```powershell
docker image ls spring-docker
```

---

## Step 6 — Container Run

```powershell
docker run -d --name spring-docker-app -p 8080:8080 spring-docker:1.0
```

---

## Step 7 — Container Check

```powershell
docker ps
```

---

## Step 8 — Logs

```powershell
docker logs spring-docker-app
```

Live logs:

```powershell
docker logs -f spring-docker-app
```

---

## Step 9 — API Test

```text
http://localhost:8080/user
```

Expected:

```text
Hello from Spring Docker
```

---

# 9. Container Stop

```powershell
docker stop spring-docker-app
```

Start again:

```powershell
docker start spring-docker-app
```

---

# 10. Container Remove

```powershell
docker rm spring-docker-app
```

Running container ko forcefully remove:

```powershell
docker rm -f spring-docker-app
```

---

# 11. Common Error — Container Name Exists

Agar:

```text
Conflict. The container name "/spring-docker-app"
is already in use
```

aaye:

```powershell
docker ps -a
```

Then:

```powershell
docker rm -f spring-docker-app
```

Phir:

```powershell
docker run -d --name spring-docker-app -p 8080:8080 spring-docker:1.0
```

---

# 12. Common Error — JAR Not Found

Agar Docker build mein `COPY` error aaye:

```powershell
dir target
```

Check karo JAR exist karti hai ya nahi.

Agar nahi:

```powershell
.\mvnw.cmd clean package -DskipTests
```

Then:

```powershell
docker build -t spring-docker:1.0 .
```

---

# 🎯 Complete Flow

```text
Spring Boot Code
      ↓
Maven
      ↓
JAR
      ↓
Dockerfile
      ↓
docker build
      ↓
Docker Image
      ↓
docker run
      ↓
Docker Container
      ↓
localhost:8080
```

---

# ✅ Key Points

- Dockerfile image build instructions contain karta hai.
- `FROM` base image select karta hai.
- `WORKDIR` working directory set karta hai.
- `COPY` JAR ko image mein copy karta hai.
- `EXPOSE` application port declare karta hai.
- `ENTRYPOINT` container start hone par application run karta hai.