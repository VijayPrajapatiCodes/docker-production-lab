# 💾 Persistent Data in Docker

## 1. What is Persistent Data?

Persistent data is data that should remain available even when a container is stopped, removed, or recreated.

Examples:

- Database records
- Uploaded files
- Application data
- Important logs
- Persistent configuration data

---

# 2. Why Persistence is Important?

Containers are designed to be replaceable.

Example:

    Old Container
         ↓
       Delete
         ↓
    New Container

Application can be recreated, but important data should remain.

Therefore:

    Application
        ↓
    Container

and:

    Important Data
        ↓
    Persistent Storage

should be treated separately.

---

# 3. Without Persistent Storage

    MySQL Container
          |
          ↓
      Database Data
          |
          ↓
    Container Deleted
          |
          ↓
       Data Risk

---

# 4. With Persistent Storage

    MySQL Container
          |
          ↓
       Volume
          |
          ↓
      Database Data

Container:

    Deleted ❌

Volume:

    Remains ✅

Data:

    Remains ✅

---

# 5. Container Stop vs Container Remove

## docker stop

    docker stop mysql

The container stops but still exists.

It can be started again:

    docker start mysql

---

## docker rm

    docker rm mysql

The container is removed.

If important data was stored only in the container's writable layer, it may be lost.

If data was stored in a volume:

    Container ❌
    Volume    ✅
    Data      ✅

---

# 6. Persistence Lifecycle

    Create Volume
          ↓
    Attach Volume
          ↓
    Application writes Data
          ↓
    Container Stops
          ↓
    Data Remains
          ↓
    Container Removed
          ↓
    Volume Remains
          ↓
    New Container
          ↓
    Same Volume
          ↓
    Existing Data

---

# 7. Persistent Data Example

    mysql-data
         |
         ↓
    MySQL Container
         |
         ↓
    /var/lib/mysql
         |
         ↓
    Database

If MySQL container is recreated:

    New MySQL Container
         |
         ↓
    mysql-data
         |
         ↓
    Existing Database

---

# 8. Volume is Not a Backup

Important:

    Volume ≠ Backup

A volume provides persistence, but it is not automatically a backup solution.

A backup strategy may involve:

    Database
       ↓
    Persistent Volume
       ↓
    Backup
       ↓
    Remote/External Storage

---

# 9. Persistent Storage Options

Common Docker storage approaches:

## Docker Volume

    docker-volume:/data

Docker manages the storage.

---

## Bind Mount

    C:\data:/data

Host manages the source path.

---

## tmpfs Mount

Data is stored in memory.

Concept:

    RAM
     ↓
    Container

It is not persistent across container lifecycle.

---

# 10. Database Persistence

Databases are one of the most important use cases.

Example:

    Spring Boot
         |
         ↓
       MySQL
         |
         ↓
    mysql-data
         |
         ↓
    Database Data

---

# 11. Docker Compose and Persistence

Example:

    services:

      mysql:
        image: mysql:8
        volumes:
          - mysql-data:/var/lib/mysql

    volumes:
      mysql-data:

The named volume can be reused when containers are recreated.

---

# 12. Persistence Test

A simple persistence test:

    Container A
         |
         ↓
    Volume
         |
         ↓
    test.txt

Delete Container A:

    Container A ❌

Create Container B:

    Container B
         |
         ↓
    Same Volume
         |
         ↓
    test.txt ✅

If `test.txt` is still available, persistence has been successfully demonstrated.

---

# 🧪 Practical

## Step 1

    docker volume create demo-volume

## Step 2

    docker run -it --name volume-test -v demo-volume:/data alpine sh

## Step 3

    echo "Persistent Data" > /data/test.txt

## Step 4

    cat /data/test.txt

## Step 5

    exit

## Step 6

    docker rm volume-test

## Step 7

    docker run -it --name volume-test-2 -v demo-volume:/data alpine sh

## Step 8

    cat /data/test.txt

Expected:

    Persistent Data

This proves that the data survived container deletion.

---

# 13. Real Application Architecture

    Docker Network
          |
    +-----+------+
    |            |
    ↓            ↓
Spring Boot    MySQL
Container    Container
|
↓
mysql-data
Volume
|
↓
Persistent Data

---

# 🎯 Key Points

- Persistent data survives container lifecycle changes.
- Containers are replaceable.
- Volumes are commonly used for persistent data.
- Bind mounts can also persist data.
- Volume does not automatically mean backup.
- Database data should be stored using persistent storage.
- Persistent storage is critical for MySQL and other stateful applications.