# Docker Container Commands

Docker provides several commands to create, run, inspect, manage, stop, restart, and remove containers.

---

# 1. What is a Docker Container?

A Docker Container is a running or stopped instance created from a Docker Image.

Basic flow:

Docker Image
↓
Docker Container
↓
Application

Example:

nginx Image
↓
my-nginx Container
↓
Nginx Web Server

---

# 2. docker run

## Definition

The `docker run` command creates a new container from an image and starts it.

## Syntax

docker run [OPTIONS] IMAGE [COMMAND]

## Example

docker run nginx

Docker will:

1. Check whether nginx image exists locally.
2. Pull it from Docker Hub if required.
3. Create a new container.
4. Start the container.

## Practical

docker run nginx

## Important

Every time `docker run` is executed, Docker normally creates a new container.

---

# 3. Run Container in Background

## Command

docker run -d nginx

## Meaning

`-d` means Detached Mode.

The container runs in the background and the terminal returns to the command prompt.

## Example

docker run -d nginx

Check:

docker ps

---

# 4. Give a Container a Name

Docker automatically generates a random name if a name is not provided.

Example:

docker run nginx

Docker may generate a name such as:

happy_tesla

Instead, we can provide our own name.

## Command

docker run --name my-nginx nginx

Now the container name is:

my-nginx

## Recommended

For learning and development, meaningful names make container management easier.

---

# 5. Run Container with Name and Detached Mode

docker run -d --name my-nginx nginx

Meaning:

-d
Run in background

--name my-nginx
Give container the name my-nginx

nginx
Image to use

---

# 6. Port Mapping

Containers have their own network namespace.

A service inside the container may listen on a port such as:

80

But that does not automatically mean the host can access it through the same port.

We can publish a container port to the host.

## Syntax

-p HOST_PORT:CONTAINER_PORT

## Example

docker run -d --name my-nginx -p 8080:80 nginx

Meaning:

Host Port = 8080
Container Port = 80

Flow:

Browser
↓
localhost:8080
↓
Docker
↓
Nginx Container
↓
Port 80

Open:

http://localhost:8080

---

# 7. docker ps

## Definition

Shows currently running containers.

## Command

docker ps

Example output:

CONTAINER ID   IMAGE   STATUS   PORTS   NAMES

## Important

docker ps

Only shows running containers.

---

# 8. docker ps -a

## Definition

Shows all containers including:

- Running
- Stopped
- Exited
- Created

## Command

docker ps -a

Example:

docker ps -a

This is very useful when a container is not visible in `docker ps`.

---

# 9. docker start

## Definition

Starts an existing stopped container.

## Command

docker start my-nginx

Important:

`docker start` does NOT create a new container.

It starts an existing container.

---

# 10. docker stop

## Definition

Stops a running container gracefully.

## Command

docker stop my-nginx

After stopping:

docker ps

will not show it.

But:

docker ps -a

will show it as Exited.

---

# 11. docker restart

## Definition

Restarts an existing container.

## Command

docker restart my-nginx

Conceptually:

Running
↓
Stop
↓
Start
↓
Running

---

# 12. docker rm

## Definition

Removes a Docker Container.

Normally the container must be stopped first.

## Command

docker rm my-nginx

## Example

docker stop my-nginx
docker rm my-nginx

Important:

Removing a container does NOT automatically remove its image.

---

# 13. Force Remove

## Command

docker rm -f my-nginx

This can stop and remove a running container.

Use it carefully.

---

# 14. docker rename

A container can be renamed.

## Command

docker rename old-name new-name

Example:

docker rename my-nginx nginx-server

---

# 15. docker inspect

Shows detailed information about a container.

## Command

docker inspect my-nginx

It can show:

- Container ID
- Image
- Network configuration
- IP address
- Mounts
- Environment variables
- Ports
- State
- Configuration

The output is JSON.

---

# 16. docker stats

Shows resource usage of running containers.

## Command

docker stats

Can display information such as:

- CPU usage
- Memory usage
- Network I/O
- Block I/O
- Process information

For one container:

docker stats my-nginx

---

# 17. docker top

Shows processes running inside a container.

## Command

docker top my-nginx

---

# 18. docker pause

Pauses processes inside a container.

docker pause my-nginx

To resume:

docker unpause my-nginx

This is different from stopping the container.

---

# 19. docker kill

Immediately stops a container by sending a kill signal.

docker kill my-nginx

Difference:

docker stop
↓
Graceful shutdown

docker kill
↓
Immediate termination

---

# 20. Container Lifecycle

Basic lifecycle:

Image
↓
docker run
↓
Created + Started
↓
Running
↓
docker stop
↓
Stopped
↓
docker start
↓
Running
↓
docker rm
↓
Deleted

---

# 21. Important Difference

docker run
↓
Creates a NEW container

docker start
↓
Starts an EXISTING container

Example:

docker run --name app nginx

Creates new container.

docker start app

Starts existing stopped container.

---

# 22. Practical Exercise

Run:

docker run -d --name my-nginx -p 8080:80 nginx

Check:

docker ps

Open:

http://localhost:8080

Stop:

docker stop my-nginx

Check:

docker ps

Then:

docker ps -a

Start:

docker start my-nginx

Check:

docker ps

Remove:

docker stop my-nginx
docker rm my-nginx

Finally:

docker ps -a

---

# 23. Important Commands

docker run
docker ps
docker ps -a
docker start
docker stop
docker restart
docker rm
docker rm -f
docker rename
docker inspect
docker stats
docker top
docker pause
docker unpause
docker kill

---

# Interview Questions

## Q1. Difference between docker run and docker start?

docker run creates and starts a new container.

docker start starts an existing stopped container.

## Q2. Difference between docker ps and docker ps -a?

docker ps shows running containers.

docker ps -a shows all containers.

## Q3. Does docker rm delete the image?

No.

docker rm removes the container only.

## Q4. What does -d mean?

Detached mode. The container runs in the background.

## Q5. What does -p 8080:80 mean?

It publishes host port 8080 and forwards traffic to container port 80.

## Q6. Difference between stop and kill?

stop attempts graceful termination.

kill immediately terminates the container process.