# 🌐 Docker Compose Networks

## 1. What is a Compose Network?

Docker Compose automatically creates a network for a Compose project unless configured otherwise.

Services connected to the same network can communicate with each other.

Architecture:

    Compose Network
          |
       +--+--+
       |     |
       ↓     ↓
      app   mysql

---

# 2. Spring Boot to MySQL

Spring Boot:

    jdbc:mysql://mysql:3306/backend_db

Here:

    mysql

is the MySQL service name.

Flow:

    Spring Boot
         ↓
    Docker DNS
         ↓
    mysql
         ↓
    MySQL Container

---

# 3. localhost vs Service Name

Inside the Spring Boot container:

    localhost

means the Spring Boot container itself.

Therefore:

    jdbc:mysql://localhost:3306/backend_db

is generally incorrect for connecting to another MySQL container.

Correct:

    jdbc:mysql://mysql:3306/backend_db

---

# 4. Docker DNS

Compose provides service discovery through Docker's internal DNS.

Example:

    services:

      app:
        ...

      mysql:
        image: mysql:8

The `app` service can resolve:

    mysql

to the MySQL service/container.

---

# 5. Default Compose Network

If no custom network is specified, Compose creates a project network.

Example:

    docker compose up -d

Then:

    docker network ls

may show:

    projectname_default

The exact name depends on the Compose project name.

---

# 6. Inspect Network

Command:

    docker network inspect projectname_default

This shows connected containers and network configuration.

---

# 7. Custom Network

Example:

    services:

      app:
        build: .
        networks:
          - backend-network

      mysql:
        image: mysql:8
        networks:
          - backend-network

    networks:
      backend-network:

Both services share:

    backend-network

---

# 8. Multiple Networks

Example:

    services:

      frontend:
        image: nginx
        networks:
          - frontend

      app:
        build: .
        networks:
          - frontend
          - backend

      mysql:
        image: mysql:8
        networks:
          - backend

    networks:
      frontend:
      backend:

Architecture:

    frontend
       |
       ↓
      app
       |
       ↓
     mysql

The app service belongs to both networks.

---

# 9. Network vs Port

Internal communication:

    app → mysql:3306

does not require publishing MySQL port to the host.

Host access:

    localhost:3306
         ↓
    MySQL Container:3306

requires:

    ports:
      - "3306:3306"

---

# 10. Network vs Volume

Network:

    Container ↔ Container

Purpose:

    Communication

Volume:

    Container ↔ Persistent Storage

Purpose:

    Data Persistence

---

# 11. Practical

Check Compose services:

    docker compose ps

List networks:

    docker network ls

Find project network.

Inspect:

    docker network inspect projectname_default

Look for:

    app
    mysql

Both should be connected to the same network.

---

# 12. DNS Practical

Enter app container:

    docker compose exec app sh

Try:

    getent hosts mysql

If the utility exists in the image, it should resolve the MySQL service name to an internal Docker IP.

---

# 13. Real Architecture

    Browser
       |
       | localhost:8081
       ↓
    Spring Boot
       |
       | mysql:3306
       ↓
    MySQL
       |
       | /var/lib/mysql
       ↓
    mysql-data Volume

---

# 🎯 Key Points

- Compose creates a default network for services.
- Services on the same network can communicate.
- Service names work as DNS names.
- `mysql:3306` is used for container-to-container communication.
- `localhost` refers to the current container.
- Host port mapping is different from internal networking.