# 🐬 Spring Boot + MySQL + Docker

## 1. MySQL Container

MySQL ko Docker container mein run kar sakte hain.

Example:

    docker run -d \
      --name mysql \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=backend_db \
      mysql:8

---

# 2. MySQL Container

MySQL ke andar:

    MySQL Server
        ↓
    backend_db
        ↓
    Tables
        ↓
    Data

---

# 3. Persistent Storage

Database ke liye Docker Volume use karna chahiye.

    mysql:
      volumes:
        - mysql-data:/var/lib/mysql

    volumes:
      mysql-data:

---

# 4. Why Volume?

Container remove hone ke baad bhi database data preserve karna hai.

Without volume:

    Container
       ↓
      Data
       ↓
    Container removed
       ↓
      Data lost

With volume:

    Container
       ↓
      Volume
       ↓
      Data

Container remove:

    Container ❌

Volume:

    Volume ✅

Data:

    Data ✅

---

# 5. Spring Boot Connection

Agar Spring Boot aur MySQL same Docker Compose network mein hain:

    jdbc:mysql://mysql:3306/backend_db

Yahan:

    mysql

MySQL service ka naam hai.

---

# 6. application.yml

    spring:
      datasource:
        url: jdbc:mysql://mysql:3306/backend_db
        username: root
        password: root

---

# 7. Important

Container-to-container communication:

    app → mysql:3306

Host se MySQL access:

    localhost:3306

Dono different concepts hain.

---

# 8. Docker Compose Example

    services:

      app:
        build: .
        ports:
          - "8081:8081"

      mysql:
        image: mysql:8

        environment:
          MYSQL_ROOT_PASSWORD: root
          MYSQL_DATABASE: backend_db

        volumes:
          - mysql-data:/var/lib/mysql

    volumes:
      mysql-data:

---

# 9. Healthcheck

MySQL ready hone ka wait karne ke liye:

    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
      interval: 5s
      timeout: 5s
      retries: 10

---

# 10. Practical

Start:

    docker compose up -d

Check:

    docker compose ps

MySQL logs:

    docker compose logs mysql

Enter MySQL:

    docker compose exec mysql mysql -uroot -proot

Database:

    SHOW DATABASES;

    USE backend_db;

Tables:

    SHOW TABLES;

Exit:

    exit

---

# 🎯 Key Points

- MySQL can run inside Docker.
- Database data should use a volume.
- Spring Boot should use `mysql:3306` for container-to-container communication.
- MySQL normally listens on port 3306.
- Healthcheck can be used to verify MySQL readiness.