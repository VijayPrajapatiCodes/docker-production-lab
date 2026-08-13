# 🔄 Container-to-Container Communication

## 1. What is Container-to-Container Communication?

Containers connected to the same Docker network can communicate with each other.

Example:

    backend-network
          |
          +---- spring-app
          |
          +---- mysql

Spring Boot can communicate with MySQL through the Docker network.

---

# 2. Practical Setup

Create network:

    docker network create --driver bridge backend-network

Run Nginx:

    docker run -d \
      --name nginx-network \
      --network backend-network \
      nginx

Run Alpine:

    docker run -d \
      --name alpine-test \
      --network backend-network \
      alpine sleep 3600

Windows PowerShell:

    docker run -d --name nginx-network --network backend-network nginx

    docker run -d --name alpine-test --network backend-network alpine sleep 3600

---

# 3. Enter Alpine Container

    docker exec -it alpine-test sh

Prompt:

    / #

---

# 4. Check Nginx DNS

Inside Alpine:

    getent hosts nginx-network

Example:

    172.18.0.2 nginx-network

Docker resolved the container name to its IP address.

---

# 5. Test HTTP Communication

Inside Alpine:

    wget -qO- http://nginx-network

Expected:

    Welcome to nginx!

This proves that Alpine can communicate with Nginx.

---

# 6. Communication Flow

    alpine-test
         |
         | HTTP
         v
    Docker Network
         |
         v
    nginx-network
         |
         v
       Nginx

---

# 7. localhost vs Container Name

Inside Alpine:

    localhost

means:

    alpine-test

It does NOT mean Nginx.

To access Nginx:

    http://nginx-network

---

# 8. Container Port

Nginx listens on:

    80

Therefore:

    http://nginx-network:80

can be used.

Usually:

    http://nginx-network

is enough because HTTP defaults to port 80.

---

# 🧪 Complete Practical

## Create network

    docker network create backend-network

## Create Nginx

    docker run -d --name nginx-network --network backend-network nginx

## Create Alpine

    docker run -d --name alpine-test --network backend-network alpine sleep 3600

## Enter Alpine

    docker exec -it alpine-test sh

## DNS test

    getent hosts nginx-network

## HTTP test

    wget -qO- http://nginx-network

Expected:

    Welcome to nginx!

## Exit

    exit

---

# 9. Verify Network

    docker network inspect backend-network

You should see:

    nginx-network
    alpine-test

---

# Real Spring Boot Example

    backend-network
          |
          +---- spring-app
          |
          +---- mysql

Spring Boot configuration:

    spring.datasource.url=jdbc:mysql://mysql:3306/backend_db

Here:

    mysql

is the MySQL container/service name.

---

# Important

Do not use:

    localhost:3306

for MySQL when MySQL is running in another container.

Use:

    mysql:3306

---

# Cleanup

    docker rm -f nginx-network alpine-test

    docker network rm backend-network