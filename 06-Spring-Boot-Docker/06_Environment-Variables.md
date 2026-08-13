# 🌱 Environment Variables — Spring Boot Docker

## 1. Environment Variables Kya Hain?

Environment variables application ki configuration ko externalize karne ka mechanism hain.

Examples:

```text
SERVER_PORT
SPRING_PROFILES_ACTIVE
DB_HOST
DB_PORT
DB_USERNAME
DB_PASSWORD
JWT_SECRET
```

---

# 2. Environment Variables Kyu Use Karte Hain?

Application configuration ko code/image ke andar hard-code karne ke bajay runtime par provide kar sakte hain.

Example:

```text
Same Docker Image
       │
       ├── Development Configuration
       │
       ├── Testing Configuration
       │
       └── Production Configuration
```

Image same reh sakti hai, configuration environment ke according change ho sakti hai.

---

# 3. Dockerfile ENV

Dockerfile mein:

```dockerfile
ENV APP_NAME=SpringDocker
```

Example:

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar

ENV APP_NAME=SpringDocker

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

# 4. Runtime Environment Variable

Container run karte waqt:

```powershell
docker run -d --name spring-docker-app -p 8080:8080 -e APP_NAME=SpringDocker spring-docker:1.0
```

Yahan:

```text
-e APP_NAME=SpringDocker
```

container ke andar environment variable set karta hai.

---

# 5. Multiple Environment Variables

```powershell
docker run -d `
  --name spring-docker-app `
  -p 8080:8080 `
  -e APP_NAME=SpringDocker `
  -e SPRING_PROFILES_ACTIVE=prod `
  spring-docker:1.0
```

---

# 6. Spring Boot Mein Environment Variables

`application.properties`:

```properties
server.port=${SERVER_PORT:8080}
```

Meaning:

```text
SERVER_PORT available
        ↓
Uski value use hogi

SERVER_PORT available nahi
        ↓
8080 use hoga
```

---

# 🧪 PRACTICAL — Server Port

## Step 1 — application.properties

```properties
server.port=${SERVER_PORT:8080}
```

---

## Step 2 — JAR Build

```powershell
.\mvnw.cmd clean package -DskipTests
```

---

## Step 3 — Docker Image Build

```powershell
docker build -t spring-docker:1.0 .
```

---

## Step 4 — Container Run

```powershell
docker run -d `
  --name spring-docker-app `
  -p 8081:8081 `
  -e SERVER_PORT=8081 `
  spring-docker:1.0
```

Yahan:

```text
Host Port      = 8081
Container Port = 8081
Spring Boot    = 8081
```

Test:

```text
http://localhost:8081/user
```

---

# 7. Environment Variable Check

Container ke andar:

```powershell
docker exec spring-docker-app env
```

Specific variable:

```powershell
docker exec spring-docker-app printenv SERVER_PORT
```

Expected:

```text
8081
```

---

# 8. Dockerfile ENV vs docker run -e

## Dockerfile ENV

```dockerfile
ENV APP_NAME=SpringDocker
```

Image mein default environment variable define karta hai.

## Runtime `-e`

```powershell
docker run -e APP_NAME=Production spring-docker:1.0
```

Runtime par value provide/override kar sakta hai.

---

# 9. Database Configuration

Future mein Spring Boot + MySQL ke liye:

```properties
spring.datasource.url=jdbc:mysql://${DB_HOST}:${DB_PORT}/backend_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Container run:

```powershell
docker run -d `
  -e DB_HOST=mysql `
  -e DB_PORT=3306 `
  -e DB_USERNAME=root `
  -e DB_PASSWORD=root `
  spring-docker:1.0
```

Yahan:

```text
DB_HOST=mysql
```

future Docker networking/Compose setup mein MySQL service/container ko refer karega.

---

# 10. Secrets

Sensitive information ko Dockerfile mein hard-code nahi karna chahiye.

Avoid:

```dockerfile
ENV DB_PASSWORD=root123
```

Avoid:

```dockerfile
ENV JWT_SECRET=my-secret
```

Examples of sensitive values:

```text
Database Password
JWT Secret
API Key
Access Token
```

Inhe runtime configuration ya proper secret-management mechanism se provide karna better hai.

---

# 11. Environment Variables Inspect

Container:

```powershell
docker exec spring-docker-app env
```

Specific:

```powershell
docker exec spring-docker-app printenv APP_NAME
```

---

# 12. Practical — Complete Example

## Dockerfile

```dockerfile
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
```

---

## application.properties

```properties
server.port=${SERVER_PORT:8080}
```

---

## Build JAR

```powershell
.\mvnw.cmd clean package -DskipTests
```

---

## Build Image

```powershell
docker build -t spring-docker:1.0 .
```

---

## Run Container

```powershell
docker run -d --name spring-docker-app -p 8080:8080 -e SERVER_PORT=8080 spring-docker:1.0
```

---

## Check Container

```powershell
docker ps
```

---

## Check Variable

```powershell
docker exec spring-docker-app printenv SERVER_PORT
```

Expected:

```text
8080
```

---

## Check Logs

```powershell
docker logs spring-docker-app
```

---

## Test API

```text
http://localhost:8080/user
```

---

# 🎯 Real-World Flow

```text
Spring Boot
      ↓
application.properties
      ↓
Environment Variables
      ↓
Docker Container
      ↓
Application Configuration
```

---

# 🎯 Docker + Spring Boot Architecture

```text
                 Docker
                   │
                   ▼
          Spring Boot Container
                   │
          ┌────────┴────────┐
          ▼                 ▼
      Environment        JAR
      Variables           │
          │              ▼
          └──────► Spring Boot
```

---

# ✅ Key Points

- Environment variables configuration ko externalize karte hain.
- Dockerfile mein `ENV` use kar sakte hain.
- Runtime par `docker run -e` use kar sakte hain.
- Spring Boot `${VARIABLE:default}` syntax support karta hai.
- Same Docker image different environments mein use ho sakti hai.
- Sensitive values ko Dockerfile mein hard-code nahi karna chahiye.
- Database, Redis, JWT aur other configuration ke liye environment variables important hain.

---

# 🚀 Next Step

```text
02 Build JAR
      ↓
03 Dockerfile
      ↓
04 Build Image
      ↓
05 Run Container
      ↓
06 Environment Variables
      ↓
07 Docker Networking
```

Next major practical:

```text
Spring Boot Container
        ↕
MySQL Container
        ↕
Redis Container
```

Yahan Docker Networking ka actual use samajh aayega.