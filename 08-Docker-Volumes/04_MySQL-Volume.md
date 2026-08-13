# 🐬 MySQL + Docker Volume

## 1. Why MySQL Needs a Volume?

MySQL is a stateful application.

It stores important data:

    users
    orders
    products
    payments

If MySQL data is stored only inside a container's writable layer, deleting the container can cause data loss.

Therefore, MySQL should use persistent storage.

---

# 2. MySQL Data Directory

The MySQL Docker image uses:

    /var/lib/mysql

as the main database data directory.

Therefore, the volume is mounted here:

    mysql-data:/var/lib/mysql

---

# 3. Architecture

    MySQL Container
          |
          ↓
    /var/lib/mysql
          |
          ↓
    mysql-data
       Volume
          |
          ↓
    Persistent Database

---

# 4. Create MySQL Volume

Command:

    docker volume create mysql-data

Check:

    docker volume ls

Inspect:

    docker volume inspect mysql-data

---

# 5. Run MySQL with Volume

Basic command:

    docker run -d \
      --name mysql-volume \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=backend_db \
      -v mysql-data:/var/lib/mysql \
      mysql:8

Windows CMD single-line version:

    docker run -d --name mysql-volume -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=backend_db -v mysql-data:/var/lib/mysql mysql:8

---

# 6. Command Explanation

    --name mysql-volume

Container name:

    mysql-volume

---

    -e MYSQL_ROOT_PASSWORD=root

Sets the root password.

---

    -e MYSQL_DATABASE=backend_db

Creates the initial database during first initialization when the data directory is empty.

---

    -v mysql-data:/var/lib/mysql

Mounts:

    Docker Volume
        ↓
    mysql-data

to:

    MySQL Container
        ↓
    /var/lib/mysql

---

    mysql:8

Uses the MySQL 8 image.

---

# 7. Check Container

    docker ps

Expected:

    mysql-volume
    mysql:8
    Up ...

MySQL may take some time during first initialization.

---

# 8. Check MySQL Logs

    docker logs mysql-volume

Live logs:

    docker logs -f mysql-volume

Look for a message indicating that MySQL is ready for connections.

---

# 9. Connect to MySQL

Command:

    docker exec -it mysql-volume mysql -uroot -proot

Expected prompt:

    mysql>

---

# 10. Check Databases

Inside MySQL:

    SHOW DATABASES;

Expected database:

    backend_db

---

# 11. Select Database

    USE backend_db;

Expected:

    Database changed

---

# 12. Create Table

    CREATE TABLE users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL
    );

---

# 13. Check Tables

    SHOW TABLES;

Expected:

    users

---

# 14. Insert Data

    INSERT INTO users (name, email)
    VALUES ('Vijay', 'vijay@example.com');

---

# 15. Read Data

    SELECT * FROM users;

Expected:

    id | name  | email
    ------------------------------
    1  | Vijay | vijay@example.com

---

# 16. Exit MySQL

    exit

---

# 17. Verify Volume

    docker volume ls

Expected:

    mysql-data

Inspect:

    docker volume inspect mysql-data

---

# 18. Delete MySQL Container

Important:

    docker rm -f mysql-volume

This deletes the container.

It does NOT delete the separately managed named volume:

    mysql-data

---

# 19. Verify Volume Still Exists

    docker volume ls

Expected:

    mysql-data

---

# 20. Recreate MySQL Using Same Volume

Create a new container:

    docker run -d \
      --name mysql-volume-new \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=backend_db \
      -v mysql-data:/var/lib/mysql \
      mysql:8

Windows CMD single-line:

    docker run -d --name mysql-volume-new -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=backend_db -v mysql-data:/var/lib/mysql mysql:8

---

# 21. Check MySQL

Wait for initialization/startup and check:

    docker logs mysql-volume-new

Then:

    docker exec -it mysql-volume-new mysql -uroot -proot

---

# 22. Check Existing Database

    SHOW DATABASES;

Then:

    USE backend_db;

Check:

    SHOW TABLES;

The existing:

    users

table should still exist.

---

# 23. Check Existing Data

    SELECT * FROM users;

Expected:

    1 | Vijay | vijay@example.com

🔥 This proves MySQL data persisted after the original container was deleted.

---

# 24. Spring Boot + MySQL

If Spring Boot and MySQL are running in the same Docker network:

    Spring Boot
         |
         ↓
    mysql:3306
         |
         ↓
    MySQL Container
         |
         ↓
    mysql-data Volume

Spring Boot configuration:

    spring.datasource.url=jdbc:mysql://mysql:3306/backend_db
    spring.datasource.username=root
    spring.datasource.password=root

---

# 25. Why Not localhost?

Inside the Spring Boot container:

    localhost

means:

    Spring Boot Container

It does not mean:

    MySQL Container

Therefore:

    jdbc:mysql://localhost:3306/backend_db

is generally wrong when MySQL is in another container.

Use:

    jdbc:mysql://mysql:3306/backend_db

where:

    mysql = MySQL container/service name

---

# 26. Networking + Volume

These are two different concepts.

Networking:

    Spring Boot
         ↓
    mysql:3306
         ↓
    MySQL

Purpose:

    Communication

Volume:

    MySQL
       ↓
    mysql-data

Purpose:

    Persistent Storage

---

# 27. MySQL Host Port

If you want to access MySQL from the host machine, you can publish:

    -p 3306:3306

Example:

    docker run -d \
      --name mysql-volume \
      -p 3306:3306 \
      -e MYSQL_ROOT_PASSWORD=root \
      -e MYSQL_DATABASE=backend_db \
      -v mysql-data:/var/lib/mysql \
      mysql:8

Then host applications can use:

    localhost:3306

However, containers on the same Docker network can communicate directly using:

    mysql:3306

and do not need host port mapping for that internal communication.

---

# 28. Important MySQL Initialization Behavior

Variables such as:

    MYSQL_ROOT_PASSWORD
    MYSQL_DATABASE

are used during initial database setup when the MySQL data directory is empty.

If an existing volume already contains a MySQL database, recreating the container does not mean that the existing database will be automatically reset based only on changed initialization variables.

---

# 29. Complete Architecture

    Docker Host
         |
         |
    Docker Network
         |
    +----+----------------+
    |                     |
    ↓                     ↓
Spring Boot             MySQL
Container              Container
|                     |
| mysql:3306          |
|                     ↓
|               /var/lib/mysql
|                     |
|                     ↓
|                mysql-data
|                   Volume
|                     |
|                     ↓
|                Database Data

---

# 🧪 COMPLETE PRACTICAL

## Step 1 — Create Volume

    docker volume create mysql-data

## Step 2 — Run MySQL

    docker run -d --name mysql-volume -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=backend_db -v mysql-data:/var/lib/mysql mysql:8

## Step 3 — Check

    docker ps

## Step 4 — Logs

    docker logs mysql-volume

## Step 5 — Enter MySQL

    docker exec -it mysql-volume mysql -uroot -proot

## Step 6 — Database

    SHOW DATABASES;

## Step 7 — Select Database

    USE backend_db;

## Step 8 — Create Table

    CREATE TABLE users (
        id INT PRIMARY KEY AUTO_INCREMENT,
        name VARCHAR(100) NOT NULL,
        email VARCHAR(255) UNIQUE NOT NULL
    );

## Step 9 — Insert

    INSERT INTO users (name, email)
    VALUES ('Vijay', 'vijay@example.com');

## Step 10 — Verify

    SELECT * FROM users;

## Step 11 — Exit

    exit

## Step 12 — Delete Container

    docker rm -f mysql-volume

## Step 13 — Verify Volume

    docker volume ls

## Step 14 — Create New Container

    docker run -d --name mysql-volume-new -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=backend_db -v mysql-data:/var/lib/mysql mysql:8

## Step 15 — Connect

    docker exec -it mysql-volume-new mysql -uroot -proot

## Step 16 — Check Database

    SHOW DATABASES;

## Step 17 — Check Table

    USE backend_db;

    SHOW TABLES;

## Step 18 — Check Data

    SELECT * FROM users;

Expected:

    Vijay

🔥 Data survived container deletion.

---

# 🧹 Cleanup

Stop/remove container:

    docker rm -f mysql-volume-new

Remove volume ONLY if you no longer need the database:

    docker volume rm mysql-data

WARNING:

Removing the volume permanently removes the data stored in that volume.

---

# 🎯 Key Points

- MySQL is a stateful application.
- MySQL data is stored under `/var/lib/mysql`.
- Use a named volume for persistent database storage.
- `mysql-data:/var/lib/mysql` mounts the volume.
- Deleting a container does not automatically delete a separately managed named volume.
- The same volume can be attached to a recreated MySQL container.
- Networking handles communication.
- Volumes handle persistence.
- Volume persistence is not the same as backup.