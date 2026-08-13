# 📂 Docker Bind Mounts

## 1. What is a Bind Mount?

A Bind Mount connects a specific file or directory from the Docker host directly to a path inside a container.

Architecture:

    Host Machine
         |
         | Bind Mount
         ↓
    Container

Example:

    Host:
    C:\docker-data

          ↓

    Container:
    /data

---

# 2. Bind Mount Syntax

General syntax:

    -v HOST_PATH:CONTAINER_PATH

Example:

    -v C:\docker-data:/data

Meaning:

    C:\docker-data
          |
          ↓
        /data

---

# 3. Bind Mount vs Volume

Docker Volume:

    -v demo-volume:/data

Bind Mount:

    -v C:\docker-data:/data

Difference:

    Volume
      ↓
    Docker manages storage

    Bind Mount
      ↓
    User chooses host directory

---

# 4. When to Use Bind Mounts?

Bind mounts are useful when the application needs direct access to host files.

Common development use cases:

- Source code
- Configuration files
- Logs
- Development assets
- Local configuration
- HTML/CSS/JS files

---

# 5. Example Architecture

    Windows Host
         |
         ↓
    C:\docker-data
         |
         ↓
    Container
         |
         ↓
       /data

Files created inside `/data` are visible from the host directory.

---

# 6. Windows Bind Mount Example

Create a host directory:

    C:\docker-data

Run:

    docker run -it \
      --name bind-test \
      -v C:\docker-data:/data \
      alpine sh

Single-line Windows command:

    docker run -it --name bind-test -v C:\docker-data:/data alpine sh

---

# 7. Create File Inside Container

Inside container:

    echo "Hello Bind Mount" > /data/message.txt

Check:

    cat /data/message.txt

---

# 8. Check Host

Open:

    C:\docker-data

You should find:

    message.txt

with:

    Hello Bind Mount

The same file is visible from both:

    Host
      ↕
    Container

---

# 9. Host to Container

Create a file on the host:

    C:\docker-data\host.txt

Put:

    Hello from Host

Inside container:

    cat /data/host.txt

The file should be available.

---

# 10. Container to Host

Inside container:

    echo "Hello from Container" > /data/container.txt

On host:

    C:\docker-data\container.txt

The file should exist.

---

# 11. Development Use Case

Suppose a web project exists on the host:

    C:\projects\website

Mount it:

    -v C:\projects\website:/usr/share/nginx/html

Architecture:

    Host Project
        |
        ↓
    Nginx Container
        |
        ↓
    /usr/share/nginx/html

Nginx can serve files directly from the host directory.

---

# 12. Bind Mount for Logs

Host:

    C:\spring-logs

Container:

    /app/logs

Mount:

    -v C:\spring-logs:/app/logs

Architecture:

    Spring Boot
        |
        ↓
    /app/logs
        |
        ↓
    C:\spring-logs

Logs can then be viewed directly from the host.

---

# 13. Read-Only Bind Mount

A bind mount can be mounted as read-only.

Syntax:

    -v HOST_PATH:CONTAINER_PATH:ro

Example:

    -v C:\config:/app/config:ro

The container can read the files but cannot modify them through that mount.

---

# 14. Bind Mount vs Named Volume

| Feature | Volume | Bind Mount |
|---|---|---|
| Managed by Docker | Yes | No |
| Host path chosen directly | No | Yes |
| Development files | Less common | Very useful |
| Database storage | Common | Possible |
| Docker-managed storage | Yes | No |
| Easy host file access | No | Yes |

---

# 15. Important Difference

Volume:

    demo-volume:/data

Bind Mount:

    C:\docker-data:/data

Remember:

    Volume Name
        ↓
    Docker manages it

    Host Path
        ↓
    Bind Mount

---

# 🧪 Complete Practical

## Step 1

Create directory:

    C:\docker-data

## Step 2

Run:

    docker run -it --name bind-test -v C:\docker-data:/data alpine sh

## Step 3

Inside container:

    echo "Hello Bind Mount" > /data/message.txt

## Step 4

Check:

    cat /data/message.txt

## Step 5

Open:

    C:\docker-data

Verify:

    message.txt

## Step 6

Create a host file:

    C:\docker-data\host.txt

## Step 7

Inside container:

    cat /data/host.txt

---

# Cleanup

Exit:

    exit

Remove container:

    docker rm bind-test

The host directory remains because it belongs to the host.

---

# 🎯 Key Points

- Bind Mount connects a host path to a container path.
- Host controls the source directory.
- Useful during development.
- Useful for logs and configuration files.
- `-v HOST_PATH:CONTAINER_PATH` is the common syntax.
- `:ro` can make the mount read-only.