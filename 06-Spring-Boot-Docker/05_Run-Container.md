# 🚀 Run Spring Boot Container

## 1. Introduction

Docker Image ready hone ke baad us image se container create aur start kiya jata hai.

Flow:

```text
Docker Image
     ↓
docker run
     ↓
Docker Container
     ↓
Spring Boot Application
```

---

# 2. Image Check

Pehle check:

```powershell
docker image ls spring-docker
```

Example:

```text
spring-docker:1.0
```

---

# 🧪 PRACTICAL

## 3. Container Run

```powershell
docker run -d --name spring-docker-app -p 8080:8080 spring-docker:1.0
```

---

# 4. Command Explanation

### `docker run`

Container create aur start karta hai.

### `-d`

Detached/background mode.

### `--name`

Container ko custom name deta hai.

```text
spring-docker-app
```

### `-p`

Port mapping.

```text
Host Port : Container Port

8080 : 8080
```

### Image

```text
spring-docker:1.0
```

---

# 5. Check Running Container

```powershell
docker ps
```

Expected:

```text
spring-docker-app
```

Port:

```text
0.0.0.0:8080->8080/tcp
```

---

# 6. Test Application

Browser:

```text
http://localhost:8080/user
```

Expected:

```text
Hello from Spring Docker
```

---

# 7. Container Logs

```powershell
docker logs spring-docker-app
```

Live logs:

```powershell
docker logs -f spring-docker-app
```

Stop live logs:

```text
Ctrl + C
```

---

# 8. Stop Container

```powershell
docker stop spring-docker-app
```

Check:

```powershell
docker ps
```

---

# 9. All Containers

Stopped containers dekhne ke liye:

```powershell
docker ps -a
```

---

# 10. Start Existing Container

```powershell
docker start spring-docker-app
```

---

# 11. Restart

```powershell
docker restart spring-docker-app
```

---

# 12. Remove Container

Stopped container:

```powershell
docker rm spring-docker-app
```

Running container:

```powershell
docker rm -f spring-docker-app
```

`-f` running container ko forcefully stop/remove kar sakta hai.

---

# 13. Container Already Exists

Error:

```text
Conflict. The container name "/spring-docker-app"
is already in use
```

Check:

```powershell
docker ps -a
```

Remove:

```powershell
docker rm -f spring-docker-app
```

Then run again:

```powershell
docker run -d --name spring-docker-app -p 8080:8080 spring-docker:1.0
```

---

# 14. Port Already In Use

Agar host port `8080` already occupied hai:

```powershell
docker run -d --name spring-docker-app -p 8081:8080 spring-docker:1.0
```

Mapping:

```text
Host      Container
8081  →   8080
```

Browser:

```text
http://localhost:8081/user
```

---

# 15. Container Details

```powershell
docker inspect spring-docker-app
```

---

# 16. Container Ke Andar Jaana

```powershell
docker exec -it spring-docker-app sh
```

Check:

```bash
ls
```

Expected:

```text
app.jar
```

Exit:

```bash
exit
```

---

# 17. Container Processes

```powershell
docker top spring-docker-app
```

---

# 18. Container Resource Usage

```powershell
docker stats spring-docker-app
```

Exit:

```text
Ctrl + C
```

---

# 🎯 Complete Practical

## Step 1 — Image

```powershell
docker image ls spring-docker
```

## Step 2 — Run

```powershell
docker run -d --name spring-docker-app -p 8080:8080 spring-docker:1.0
```

## Step 3 — Check

```powershell
docker ps
```

## Step 4 — Logs

```powershell
docker logs spring-docker-app
```

## Step 5 — Browser

```text
http://localhost:8080/user
```

## Step 6 — Stop

```powershell
docker stop spring-docker-app
```

## Step 7 — Start

```powershell
docker start spring-docker-app
```

## Step 8 — Remove

```powershell
docker rm -f spring-docker-app
```

---

# 🎯 Complete Flow

```text
Spring Boot Code
      ↓
JAR
      ↓
Docker Image
      ↓
docker run
      ↓
Container
      ↓
Port Mapping
      ↓
localhost
      ↓
Spring Boot API
```

---

# ✅ Key Points

- Image se container run hota hai.
- `docker run` container create + start karta hai.
- `-d` background mode hai.
- `--name` container name set karta hai.
- `-p` port mapping karta hai.
- `docker ps` running containers dikhata hai.
- `docker logs` application logs dikhata hai.
- `docker stop` container stop karta hai.
- `docker start` stopped container ko start karta hai.
- `docker rm -f` running container ko forcefully remove kar sakta hai.