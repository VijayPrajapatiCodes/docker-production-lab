# 🏗️ Build Docker Image

## 1. Docker Image Kya Hai?

Docker Image ek packaged template hota hai jisse Docker containers create kiye ja sakte hain.

Spring Boot application ke case mein:

```text
Java Runtime
     +
Spring Boot JAR
     +
Docker Configuration
     ↓
Docker Image
```

Example:

```text
spring-docker:1.0
```

---

# 2. Prerequisites

Docker image build karne se pehle JAR available honi chahiye.

```powershell
.\mvnw.cmd clean package -DskipTests
```

Check:

```powershell
dir target
```

---

# 3. Dockerfile

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# 🧪 PRACTICAL

## Step 1 — Build Image

Project root mein:

```powershell
docker build -t spring-docker:1.0 .
```

---

# 4. Command Explanation

### `docker build`

Docker image build karta hai.

### `-t`

Image ko name aur tag deta hai.

### `spring-docker:1.0`

```text
Repository/Name = spring-docker
Tag             = 1.0
```

### `.`

Current directory ko build context banata hai.

---

# 5. Build Process

Docker roughly:

```text
Dockerfile
    ↓
FROM
    ↓
WORKDIR
    ↓
COPY
    ↓
EXPOSE
    ↓
ENTRYPOINT
    ↓
Docker Image
```

---

# 6. Image Check

```powershell
docker image ls
```

Specific:

```powershell
docker image ls spring-docker
```

Expected:

```text
REPOSITORY      TAG
spring-docker   1.0
```

---

# 7. Image Inspect

```powershell
docker image inspect spring-docker:1.0
```

Isse image ki detailed configuration dekh sakte hain.

---

# 8. Image History

```powershell
docker image history spring-docker:1.0
```

Image ki layers/history dekhne ke liye useful hai.

---

# 9. Image Tag

Same image ko another tag de sakte hain:

```powershell
docker tag spring-docker:1.0 spring-docker:latest
```

Check:

```powershell
docker image ls spring-docker
```

---

# 10. New Version Build

Agar application code change hua:

```powershell
.\mvnw.cmd clean package -DskipTests
```

Then:

```powershell
docker build -t spring-docker:1.1 .
```

Ab:

```text
spring-docker:1.0
spring-docker:1.1
```

ho sakte hain.

---

# 11. Docker Build Context

Command:

```powershell
docker build -t spring-docker:1.0 .
```

mein `.` ka matlab current directory hai.

Docker build context ke andar required files available honi chahiye.

Example:

```text
SpringDocker/
│
├── Dockerfile
├── pom.xml
├── src/
└── target/
    └── SpringDocker-0.0.1-SNAPSHOT.jar
```

---

# 12. Build Cache

Docker build layers ko cache kar sakta hai.

Agar Dockerfile ka koi layer change nahi hua, Docker cached layer reuse kar sakta hai.

Isse build fast ho sakta hai.

---

# 13. No Cache Build

Agar completely fresh build karna ho:

```powershell
docker build --no-cache -t spring-docker:1.0 .
```

Normally `--no-cache` ki zarurat nahi hoti.

---

# 🎯 Complete Practical

## 1. Build JAR

```powershell
.\mvnw.cmd clean package -DskipTests
```

## 2. Check JAR

```powershell
dir target
```

## 3. Build Docker Image

```powershell
docker build -t spring-docker:1.0 .
```

## 4. Check Image

```powershell
docker image ls spring-docker
```

## 5. Inspect

```powershell
docker image inspect spring-docker:1.0
```

## 6. History

```powershell
docker image history spring-docker:1.0
```

---

# 🎯 Complete Flow

```text
Source Code
    ↓
Maven Package
    ↓
JAR
    ↓
Dockerfile
    ↓
docker build
    ↓
spring-docker:1.0
```

---

# ✅ Key Points

- `docker build` image create karta hai.
- `-t` image name/tag set karta hai.
- `.` build context hai.
- `docker image ls` images list karta hai.
- `docker image inspect` detailed information deta hai.
- `docker image history` image layers/history dikhata hai.
- Code change ke baad JAR rebuild karke image rebuild karni chahiye.