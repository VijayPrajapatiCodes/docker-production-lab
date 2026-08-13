# 📄 docker-compose.yml

## 1. Purpose
  
  `docker-compose.yml` defines the configuration of services used by a Docker Compose application.

Basic structure:
  
  services:
    
    app:
      ...
    
    mysql:
      ...

---

# 2. services

Example:
  
  services:
    
    app:
      ...
    
    mysql:
      ...
  
  `services` is the main section where application services are defined.

Example services:
  
  app
  mysql
  redis
  kafka

---

# 3. image

Example:
  
  mysql:
    image: mysql:8
  
  `image` specifies the Docker image used by the service.

---

# 4. build
   
   Example:
     
     app:
       build: .
  
  `build: .` means Docker should use the current directory as the build context.
  
  Usually the Dockerfile is located there.
   
   Architecture:
     
     Dockerfile
     ↓
     build
     ↓
     Docker Image
     ↓
     Container

---

# 5. image vs build

Image:
  
  image: mysql:8
  
  Uses an existing image.

Build:
  
  build: .
  
  Builds an image using a Dockerfile.

Spring Boot:
  
  app:
    build: .

MySQL:
  
  mysql:
    image: mysql:8

---

# 6. ports

Syntax:
  
  ports:
    - "HOST_PORT:CONTAINER_PORT"

Example:
  
  ports:
    - "8081:8081"

Meaning:
  
  Host
  localhost:8081
  ↓
  Container:8081

---

# 7. environment

Example:
  
  environment:
    MYSQL_ROOT_PASSWORD: root
    MYSQL_DATABASE: backend_db
  
  Environment variables are provided to the container.

Equivalent Docker command:
  
  -e MYSQL_ROOT_PASSWORD=root
  -e MYSQL_DATABASE=backend_db

---

# 8. depends_on

Example:
  
  app:
    depends_on:
      mysql:
        condition: service_healthy
  
  This defines a dependency between services.
  
  The application waits for the MySQL service to satisfy the configured health condition before starting.

Important:
  
  A simple `depends_on` without a health condition does not guarantee that the application is actually ready to accept connections.

---

# 9. healthcheck

Example:
  
  healthcheck:
    test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
    interval: 5s
    timeout: 5s
    retries: 10
    start_period: 20s
  
  Healthcheck verifies whether a service is healthy.

---

# 10. volumes

Example:
  
  mysql:
    volumes:
      - mysql-data:/var/lib/mysql

volumes:
  
  mysql-data:
  
  This creates a named Docker volume and mounts it into MySQL.

---

# 11. restart

Example:
  
  restart: unless-stopped

Common restart policies:
  
  no
  always
  on-failure
  unless-stopped

---

# 12. Complete Spring Boot + MySQL Example

services:
  
  app:
    build: .
    ports:
      - "8081:8081"
    depends_on:
      mysql:
        condition: service_healthy
  
  mysql:
    image: mysql:8
    
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: backend_db
    
    volumes:
      - mysql-data:/var/lib/mysql
    
    healthcheck:
      test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-proot"]
      interval: 5s
      timeout: 5s
      retries: 10
      start_period: 20s

volumes:
  mysql-data:

---

# 13. Practical

Build:
  
  docker compose build

Start:
  
  docker compose up -d

Check:
  
  docker compose ps

Logs:
  
  docker compose logs app

MySQL logs:
  
  docker compose logs mysql

Stop:
  
  docker compose down

---

# 🎯 Key Points

- `services` defines services.
- `image` uses an existing image.
- `build` builds an image from a Dockerfile.
- `ports` maps host and container ports.
- `environment` provides configuration.
- `depends_on` defines dependencies.
- `healthcheck` checks service health.
- `volumes` provides persistent storage.
- `restart` controls restart behavior.