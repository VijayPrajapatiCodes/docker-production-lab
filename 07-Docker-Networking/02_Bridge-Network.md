# 🌉 Docker Bridge Network

## 1. What is Bridge Network?

Bridge is one of the most commonly used Docker network drivers.

It allows containers connected to the same network to communicate with each other.

Architecture:

    Docker Host
         |
         v
    backend-network
         |
      +--+--+
      |     |
      v     v
    App   MySQL

---

# 2. Default Bridge Network

Docker provides a default network named:

    bridge

Check:

    docker network ls

Inspect:

    docker network inspect bridge

---

# 3. Custom Bridge Network

Create:

    docker network create --driver bridge backend-network

Check:

    docker network ls

Inspect:

    docker network inspect backend-network

---

# 4. Subnet and Gateway

A Docker bridge network normally has:

    Subnet
    Gateway

Example:

    Subnet: 172.18.0.0/16
    Gateway: 172.18.0.1

Docker automatically assigns IP addresses to containers.

Example:

    nginx-network -> 172.18.0.2
    alpine-test   -> 172.18.0.3

---

# 5. Start Container in Bridge Network

    docker run -d \
      --name nginx-network \
      --network backend-network \
      nginx

Windows PowerShell:

    docker run -d --name nginx-network --network backend-network nginx

---

# 6. Verify

    docker network inspect backend-network

The container should appear under:

    Containers

---

# 7. Multiple Containers

Run another container:

    docker run -d \
      --name alpine-test \
      --network backend-network \
      alpine sleep 3600

Now:

    backend-network
          |
          +---- nginx-network
          |
          +---- alpine-test

---

# 8. Why Custom Bridge Network?

Custom bridge networks provide better isolation and easier container discovery compared with putting everything on the default bridge.

A custom network is useful for application stacks.

Example:

    backend-network
          |
          +---- spring-app
          +---- mysql
          +---- redis

---

# 🧪 Complete Practical

## Create Network

    docker network create --driver bridge backend-network

## Start Nginx

    docker run -d --name nginx-network --network backend-network nginx

## Start Alpine

    docker run -d --name alpine-test --network backend-network alpine sleep 3600

## Inspect

    docker network inspect backend-network

---

# 9. Disconnect

    docker network disconnect backend-network alpine-test

Check:

    docker network inspect backend-network

---

# 10. Reconnect

    docker network connect backend-network alpine-test

Check:

    docker network inspect backend-network

---

# Cleanup

    docker rm -f nginx-network alpine-test

    docker network rm backend-network

---

# Summary

Bridge network allows containers to communicate through a Docker-managed network.

Important commands:

    docker network create
    docker network inspect
    docker network connect
    docker network disconnect