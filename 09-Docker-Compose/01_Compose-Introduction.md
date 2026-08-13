# 🐳 Docker Compose Introduction

## 1. What is Docker Compose?

Docker Compose is a tool used to define and manage multi-container Docker applications using a single YAML configuration file.

Instead of manually running multiple `docker run` commands, we can define the complete application stack in:

    docker-compose.yml

Example:

    Spring Boot
         +
       MySQL
         +
       Redis

can be managed using one Compose configuration.

---

# 2. Why Docker Compose?

Without Compose:

    docker network create backend-network

    docker run mysql...

    docker run redis...

    docker run spring-boot...

Many commands are required.

With Compose:

    docker compose up -d

The complete application stack can be started from one configuration.

---

# 3. Docker Compose Architecture

                    docker-compose.yml
                            |
              +-------------+-------------+
              |             |             |
              ↓             ↓             ↓
         Spring Boot      MySQL         Redis
          Container      Container      Container

---

# 4. Docker Compose File

Common file name:

    docker-compose.yml

Modern Compose also supports:

    compose.yml

For learning, this project uses:

    docker-compose.yml

---

# 5. Basic Compose Example

    services:

      app:
        image: nginx

      mysql:
        image: mysql:8

This defines two services:

    app
    mysql

---

# 6. Important Compose Concepts

Docker Compose mainly works with:

    Services
    Networks
    Volumes
    Environment Variables

Architecture:

    Docker Compose
         |
         +-- Services
         |
         +-- Networks
         |
         +-- Volumes
         |
         +-- Environment Variables

---

# 7. Important Commands

Start services:

    docker compose up

Start in background:

    docker compose up -d

Stop/remove Compose resources:

    docker compose down

Check services:

    docker compose ps

View logs:

    docker compose logs

Follow logs:

    docker compose logs -f

Build images:

    docker compose build

Build and start:

    docker compose up -d --build

---

# 8. Docker Compose vs Dockerfile

Dockerfile:

    Dockerfile
        ↓
    Docker Image
        ↓
    Container

Docker Compose:

    docker-compose.yml
        ↓
    Multiple Services
        ↓
    Multiple Containers

Dockerfile is mainly used to build an image.

Docker Compose is mainly used to define and manage a multi-container application.

---

# 9. Real Spring Boot Example

A real backend can contain:

    Spring Boot
         |
         +--- MySQL
         |
         +--- Redis

Docker Compose can manage all of them:

    docker-compose.yml
            |
       +----+----+
       |         |
       ↓         ↓
    Spring     MySQL
      Boot
       |
       ↓
     Redis

---

# 10. Practical

Create:

    docker-compose.yml

Add:

    services:

      app:
        image: nginx
        ports:
          - "8080:80"

Run:

    docker compose up -d

Check:

    docker compose ps

Open:

    http://localhost:8080

Stop:

    docker compose down

---

# 🎯 Key Points

- Docker Compose manages multi-container applications.
- Compose uses YAML configuration.
- `docker compose up` starts services.
- `docker compose down` stops/removes Compose-managed resources.
- Compose can manage networks, volumes and environment variables.
- Dockerfile builds images.
- Compose manages application services.