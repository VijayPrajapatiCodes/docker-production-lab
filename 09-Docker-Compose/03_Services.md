# ⚙️ Docker Compose Services

## 1. What is a Service?

A service represents an application component defined in Docker Compose.

Example:

    services:

      app:
        build: .

      mysql:
        image: mysql:8

Here:

    app   → Spring Boot service
    mysql → MySQL service

---

# 2. Service and Container

A service is configuration.

A container is a running instance created from that configuration.

Concept:

    Service
       ↓
    Container

---

# 3. image

    mysql:
      image: mysql:8

Uses the MySQL 8 Docker image.

---

# 4. build

    app:
      build: .

Builds the Spring Boot Docker image using the Dockerfile.

---

# 5. ports

    app:
      ports:
        - "8081:8081"

Mapping:

    Host:8081
        ↓
    Container:8081

---

# 6. environment

    mysql:
      environment:
        MYSQL_ROOT_PASSWORD: root
        MYSQL_DATABASE: backend_db

Provides environment variables to MySQL.

---

# 7. depends_on

    app:
      depends_on:
        mysql:
          condition: service_healthy

The application depends on MySQL being healthy.

---

# 8. healthcheck

    mysql:
      healthcheck:
        test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
        interval: 5s
        timeout: 5s
        retries: 10
        start_period: 20s

Docker uses this check to determine whether the service is healthy.

---

# 9. restart

    restart: unless-stopped

Automatically restarts the container according to the restart policy.

---

# 10. container_name

Example:

    container_name: my-mysql

This gives a specific container name.

However, `container_name` is not required for normal Compose usage.

---

# 11. command

A service command can override the image's default command.

Example:

    command: ["java", "-jar", "app.jar"]

Use only when required.

---

# 12. Scaling Services

Example:

    docker compose up -d --scale app=3

Concept:

             app service
                  |
       +----------+----------+
       ↓          ↓          ↓
     app-1      app-2      app-3

Important:

If a service publishes a fixed host port such as:

    8081:8081

multiple replicas cannot all bind that same host port.

A load balancer/reverse proxy or another networking design may be needed for multiple replicas.

---

# 13. Service Name and DNS

Example:

    services:

      app:
        ...

      mysql:
        image: mysql:8

Spring Boot can connect using:

    jdbc:mysql://mysql:3306/backend_db

`mysql` is the service name.

Docker's internal DNS resolves the service name on the Compose network.

---

# 14. Important Commands

Build specific service:

    docker compose build app

Start:

    docker compose up -d app

Stop:

    docker compose stop app

Logs:

    docker compose logs app

Follow logs:

    docker compose logs -f app

---

# 15. Practical

Check:

    docker compose ps

Start:

    docker compose up -d

Check:

    docker compose ps

Logs:

    docker compose logs app

MySQL logs:

    docker compose logs mysql

---

# 🎯 Key Points

- Services represent application components.
- A service configuration can create containers.
- `image` uses an existing image.
- `build` builds an image.
- `depends_on` defines dependencies.
- `healthcheck` determines service health.
- Service names can be used for Docker DNS.