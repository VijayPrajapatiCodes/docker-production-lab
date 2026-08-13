# 🌐 Docker Networking Basics

## 1. What is Docker Networking?

Docker Networking allows Docker containers to communicate:

- With other containers
- With the Docker host
- With external networks
- With the Internet

Example:

    Spring Boot Container
            |
            v
       Docker Network
            |
            v
       MySQL Container

Docker Networking is very important when running multi-container applications.

---

# 2. Why Docker Networking is Required?

Suppose we have:

    Spring Boot
    MySQL
    Redis

Spring Boot needs to communicate with:

    Spring Boot
         |
         +----> MySQL
         |
         +----> Redis

Docker Networking provides the communication path between these containers.

---

# 3. Docker Network Architecture

Basic architecture:

    Docker Host
         |
         +----------------------+
         |                      |
    Container A            Container B
         |                      |
         +------ Network -------+

Containers connected to the same Docker network can communicate with each other.

---

# 4. List Docker Networks

Command:

    docker network ls

Example:

    NETWORK ID     NAME      DRIVER    SCOPE
    xxxxxxxx       bridge    bridge    local
    xxxxxxxx       host      host      local
    xxxxxxxx       none      null      local

---

# 5. Default Docker Networks

Docker provides default networks such as:

## bridge

The default bridge network.

## host

Container uses the host networking stack.

## none

Container has no normal network connectivity.

---

# 6. Inspect a Network

Command:

    docker network inspect bridge

This provides information such as:

- Network name
- Driver
- Subnet
- Gateway
- Connected containers
- IP addresses

---

# 7. Create a Custom Network

Command:

    docker network create backend-network

Specify bridge driver:

    docker network create --driver bridge backend-network

---

# 8. Check Custom Network

    docker network ls

Inspect:

    docker network inspect backend-network

---

# 9. Connect Container to Network

Create container directly inside network:

    docker run -d \
      --name nginx-network \
      --network backend-network \
      nginx

Windows PowerShell:

    docker run -d --name nginx-network --network backend-network nginx

---

# 10. Connect Existing Container

If a container already exists:

    docker network connect backend-network container-name

Example:

    docker network connect backend-network my-container

---

# 11. Disconnect Container

    docker network disconnect backend-network my-container

---

# 12. Remove Network

    docker network rm backend-network

A network generally cannot be removed while containers are still connected to it.

---

# 🧪 Practical

## Step 1

    docker network ls

## Step 2

    docker network create --driver bridge backend-network

## Step 3

    docker network inspect backend-network

## Step 4

    docker run -d --name nginx-network --network backend-network nginx

## Step 5

    docker network inspect backend-network

The Nginx container should appear inside the Containers section.

---

# Real Backend Example

A real backend may look like:

    backend-network
          |
          +---- spring-app
          |
          +---- mysql
          |
          +---- redis

Spring Boot can communicate with MySQL and Redis through the Docker network.

---

# Important

Container-to-container communication normally uses:

    container-name:container-port

Example:

    mysql:3306

    redis:6379

Do not normally use localhost for another container.

---

# Cleanup

    docker rm -f nginx-network

    docker network rm backend-network

---

# Summary

Docker Networking provides communication between containers.

Important commands:

    docker network ls
    docker network inspect
    docker network create
    docker network connect
    docker network disconnect
    docker network rm