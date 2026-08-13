# 📦 Build JAR — Spring Boot

## 1. Introduction

JAR ka full form **Java ARchive** hai.

Spring Boot application ko Maven ke through package karke ek executable JAR file banayi ja sakti hai.

Docker ke andar hum isi JAR ko Java Runtime ke through run karenge.

### Basic Flow

```text
Spring Boot Source Code
        ↓
       Maven
        ↓
      Compile
        ↓
      Package
        ↓
       JAR
        ↓
      Docker
```

---

## 2. JAR Kya Contain Karta Hai?

Spring Boot executable JAR mein application ko run karne ke liye required application classes aur resources packaged hote hain.

Example:

```text
target/
└── SpringDocker-0.0.1-SNAPSHOT.jar
```

Docker ke andar ise:

```bash
java -jar app.jar
```

se run kiya ja sakta hai.

---

## 3. Maven Wrapper

Spring Boot project mein commonly:

```text
mvnw
mvnw.cmd
```

files hoti hain.

Windows PowerShell mein Maven Wrapper:

```powershell
.\mvnw.cmd
```

use hota hai.

Iska benefit ye hai ki project Maven Wrapper ke through Maven version manage kar sakta hai.

---

# 🧪 PRACTICAL

## 4. Project Structure

Example:

```text
SpringDocker/
│
├── src/
├── target/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── Dockerfile
```

---

## 5. JAR Build Karna

Project ke root folder mein terminal open karo.

Command:

```powershell
.\mvnw.cmd clean package -DskipTests
```

---

## 6. Command Explanation

### `clean`

```text
clean
```

Purane build output ko remove karta hai.

Usually:

```text
target/
```

clean ho jata hai.

### `package`

```text
package
```

Application ko compile karke package karta hai aur JAR generate karta hai.

### `-DskipTests`

```text
-DskipTests
```

Tests execute nahi honge.

---

## 7. Build Successful Hone Ke Baad

Terminal mein:

```text
BUILD SUCCESS
```

aana chahiye.

---

## 8. JAR Check Karna

PowerShell:

```powershell
dir target
```

Ya:

```powershell
Get-ChildItem target
```

Example:

```text
target/
├── classes/
├── generated-sources/
└── SpringDocker-0.0.1-SNAPSHOT.jar
```

---

## 9. JAR Ko Locally Run Karna

Docker mein daalne se pehle JAR ko directly test kar sakte hain:

```powershell
java -jar target/SpringDocker-0.0.1-SNAPSHOT.jar
```

Application start hone ke baad:

```text
http://localhost:8080/user
```

test karo.

Expected:

```text
Hello from Spring Docker
```

Stop:

```text
Ctrl + C
```

---

## 10. Docker Ke Liye JAR

Dockerfile mein JAR copy karenge:

```dockerfile
COPY target/SpringDocker-0.0.1-SNAPSHOT.jar app.jar
```

Then:

```dockerfile
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Application Docker container ke andar start hogi.

---

## 11. Important Workflow

Code change hone ke baad:

```text
Code Change
     ↓
Maven Package
     ↓
New JAR
     ↓
Docker Build
     ↓
New Docker Image
```

Commands:

```powershell
.\mvnw.cmd clean package -DskipTests
```

Then:

```powershell
docker build -t spring-docker:1.0 .
```

---

# 🎯 Complete Practical

```powershell
.\mvnw.cmd clean package -DskipTests
```

```powershell
dir target
```

```powershell
java -jar target/SpringDocker-0.0.1-SNAPSHOT.jar
```

Test:

```text
http://localhost:8080/user
```

Stop:

```text
Ctrl + C
```

---

# ✅ Key Points

- JAR = Java ARchive.
- Maven Spring Boot application ko package karta hai.
- `target/` mein JAR generate hoti hai.
- `java -jar` se executable JAR run kar sakte hain.
- Dockerfile mein isi JAR ko image ke andar copy karenge.