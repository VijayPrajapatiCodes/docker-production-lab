# 🐳 Docker Volumes

## 1. What is a Docker Volume?

Docker Volume is a mechanism used to persist data outside the writable layer of a container.

Normally:

    Container
        |
        └── Application Data

If the container is deleted, data stored only inside the container can be lost.

With a volume:

    Container
        |
        ↓
      Volume
        |
        ↓
    Persistent Data

The volume exists independently from the container.

---

# 2. Why Do We Need Volumes?

Suppose MySQL is running inside a container:

    MySQL Container
          |
          └── Database Data

If the container is removed:

    docker rm -f mysql

the container itself is deleted.

For databases, we don't want important data to disappear with the container.

Therefore:

    MySQL Container
          |
          ↓
       Volume
          |
          ↓
    Database Data

Now the container can be recreated while the data remains in the volume.

---

# 3. Docker Volume vs Container Storage

Without volume:

    Container
       |
       └── Data

With volume:

    Container
       |
       ↓
    Docker Volume
       |
       ↓
      Data

The volume has its own lifecycle.

---

# 4. Create a Volume

Command:

    docker volume create my-volume

Example:

    docker volume create mysql-data

---

# 5. List Volumes

    docker volume ls

Example:

    DRIVER    VOLUME NAME
    local     mysql-data

---

# 6. Inspect a Volume

    docker volume inspect mysql-data

This shows information such as:

- Name
- Driver
- Mountpoint
- Scope

Example:

    docker volume inspect mysql-data

---

# 7. Use Volume with Container

Syntax:

    docker run -d \
      --name container-name \
      -v volume-name:/container/path \
      image

Example:

    docker run -d \
      --name mysql-container \
      -v mysql-data:/var/lib/mysql \
      mysql

Windows PowerShell:

    docker run -d --name mysql-container -v mysql-data:/var/lib/mysql mysql

---

# 8. Volume Mount

Example:

    -v mysql-data:/var/lib/mysql

Meaning:

    mysql-data
         ↓
    /var/lib/mysql

Inside the container:

    /var/lib/mysql

is backed by the Docker volume:

    mysql-data

---

# 9. Practical: Create Volume

Run:

    docker volume create demo-volume

Check:

    docker volume ls

Expected:

    demo-volume

---

# 10. Practical: Run Container with Volume

Run an Alpine container:

    docker run -it \
      --name volume-test \
      -v demo-volume:/data \
      alpine sh

Windows PowerShell:

    docker run -it --name volume-test -v demo-volume:/data alpine sh

You will enter:

    / #

---

# 11. Create Data Inside Container

Inside Alpine:

    echo "Hello Docker Volume" > /data/message.txt

Check:

    cat /data/message.txt

Expected:

    Hello Docker Volume

---

# 12. Exit Container

    exit

Now remove the container:

    docker rm volume-test

The container is gone.

But the volume should still exist.

Check:

    docker volume ls

You should see:

    demo-volume

---

# 13. Create New Container Using Same Volume

Run:

    docker run -it \
      --name volume-test-2 \
      -v demo-volume:/data \
      alpine sh

Windows PowerShell:

    docker run -it --name volume-test-2 -v demo-volume:/data alpine sh

Now:

    cat /data/message.txt

Expected:

    Hello Docker Volume

🔥 This proves that the data survived the first container deletion.

---

# 14. Important Concept

Container:

    Temporary

Volume:

    Persistent

Example:

    Container A
        |
        ↓
    demo-volume
        |
        ↓
    message.txt

Delete Container A:

    Container A ❌

Volume:

    demo-volume ✅

Create Container B:

    Container B
        |
        ↓
    demo-volume
        |
        ↓
    message.txt

Data is available again.

---

# 15. Volume Lifecycle

    Create Volume
          ↓
    Attach to Container
          ↓
    Store Data
          ↓
    Remove Container
          ↓
    Volume remains
          ↓
    Attach to another Container

---

# 16. Remove Volume

First make sure the volume is not being used.

Then:

    docker volume rm demo-volume

---

# 17. Remove Unused Volumes

Docker provides:

    docker volume prune

This removes unused anonymous and named local volumes after confirmation.

Be careful before using it because unused volumes may still contain data you want to keep.

---

# 18. Volume Drivers

Docker volumes use a volume driver.

The default driver is:

    local

Check:

    docker volume inspect demo-volume

---

# 19. Named Volume

Example:

    docker volume create mysql-data

Then:

    docker run -d \
      --name mysql \
      -v mysql-data:/var/lib/mysql \
      mysql

Named volume:

    mysql-data

is easy to identify and manage.

---

# 20. Anonymous Volume

A volume can also be created without explicitly naming it.

Example:

    docker run -d \
      --name app \
      -v /data \
      alpine

Docker creates an anonymous volume.

For production database storage, named volumes are generally easier to manage.

---

# 🧪 COMPLETE PRACTICAL

## Step 1 — Create Volume

    docker volume create demo-volume

## Step 2 — Verify

    docker volume ls

## Step 3 — Run Container

    docker run -it --name volume-test -v demo-volume:/data alpine sh

## Step 4 — Create File

Inside container:

    echo "Docker Volume Working" > /data/test.txt

## Step 5 — Verify

    cat /data/test.txt

Expected:

    Docker Volume Working

## Step 6 — Exit

    exit

## Step 7 — Remove Container

    docker rm volume-test

## Step 8 — Verify Volume

    docker volume ls

demo-volume should still exist.

## Step 9 — Start New Container

    docker run -it --name volume-test-2 -v demo-volume:/data alpine sh

## Step 10 — Verify Data

    cat /data/test.txt

Expected:

    Docker Volume Working

🔥 Persistence successfully verified.

---

# 21. Volume Inspection

Run:

    docker volume inspect demo-volume

You can see the Docker-managed mount location.

Do not manually modify Docker's internal volume storage unless you know exactly what you are doing.

---

# 22. Real Spring Boot Use Case

Suppose:

    Spring Boot
         |
         +---- MySQL

MySQL needs persistent storage.

Architecture:

    Spring Boot Container
            |
            | Docker Network
            ↓
       MySQL Container
            |
            ↓
       mysql-data
            |
            ↓
      Persistent Database

If MySQL container is recreated:

    Old MySQL Container ❌

    New MySQL Container ✅
            |
            ↓
       mysql-data
            |
            ↓
      Existing Database

---

# 23. Important Difference

Docker Volume:

    Managed by Docker

Bind Mount:

    Uses a directory/file from the host machine

Example Volume:

    -v mysql-data:/var/lib/mysql

Example Bind Mount:

    -v C:\mysql-data:/var/lib/mysql

Bind mounts will be studied separately.

---

# 24. Useful Commands

List volumes:

    docker volume ls

Create:

    docker volume create my-volume

Inspect:

    docker volume inspect my-volume

Remove:

    docker volume rm my-volume

Remove unused:

    docker volume prune

---

# 25. Final Architecture

    Docker Host
         |
         +-----------------------+
         |                       |
         v                       v
    Container A             Container B
         |                       |
         +-----------+-----------+
                     |
                     v
                 my-volume
                     |
                     v
               Persistent Data

---

# 🎯 Key Points

1. Containers are replaceable.
2. Volumes persist independently of containers.
3. Volumes are especially important for databases.
4. Named volumes are easy to identify and manage.
5. Container deletion does not automatically delete a separately managed volume.
6. Volume data can be reused by another container.
7. `docker volume ls` lists volumes.
8. `docker volume inspect` provides volume details.
9. `docker volume rm` removes a volume.
10. `docker volume prune` removes unused volumes.
