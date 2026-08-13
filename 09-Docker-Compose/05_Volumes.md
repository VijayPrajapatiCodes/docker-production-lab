# 💾 Docker Compose Volumes

## 1. Why Volumes?

Containers are replaceable.

Database data should survive container recreation.

Therefore:

    MySQL Container
         ↓
    /var/lib/mysql
         ↓
    mysql-data Volume

---

# 2. Named Volume

Example:

    services:

      mysql:
        image: mysql:8
        volumes:
          - mysql-data:/var/lib/mysql

    volumes:
      mysql-data:

`mysql-data` is a named Docker volume.

---

# 3. Volume Mount

Syntax:

    volume-name:container-path

Example:

    mysql-data:/var/lib/mysql

Meaning:

    mysql-data
         ↓
    /var/lib/mysql

---

# 4. Create Volume Automatically

When Compose starts:

    docker compose up -d

If the declared volume does not exist, Compose can create it.

Check:

    docker volume ls

---

# 5. Inspect Volume

    docker volume inspect mysql-data

Information includes:

    Name
    Driver
    Mountpoint
    Scope

---

# 6. Persistence

Without volume:

    Container
        ↓
      Data
        ↓
    Container removed
        ↓
      Data lost

With volume:

    Container
        ↓
      Volume
        ↓
      Data

Container removed:

    Container ❌

Volume:

    Volume ✅

Data:

    Data ✅

---

# 7. docker compose down

Command:

    docker compose down

Normally removes:

    Compose containers
    Compose network

Named volumes are not removed by the normal `down` command.

Therefore:

    Container ❌
    Network   ❌
    Volume    ✅
    Data      ✅

---

# 8. docker compose down -v

Command:

    docker compose down -v

This also removes Compose-managed named volumes.

Therefore:

    Container ❌
    Network   ❌
    Volume    ❌
    Data      ❌

WARNING:

Do not use `down -v` when you need to preserve the database volume.

---

# 9. MySQL Volume

MySQL stores database data under:

    /var/lib/mysql

Compose:

    mysql:
      image: mysql:8
      volumes:
        - mysql-data:/var/lib/mysql

---

# 10. Complete Example

    services:

      mysql:
        image: mysql:8

        environment:
          MYSQL_ROOT_PASSWORD: root
          MYSQL_DATABASE: backend_db

        volumes:
          - mysql-data:/var/lib/mysql

    volumes:
      mysql-data:

---

# 11. Persistence Practical

Start:

    docker compose up -d

Enter MySQL:

    docker compose exec mysql mysql -uroot -proot

Select database:

    USE backend_db;

Check tables:

    SHOW TABLES;

Check data:

    SELECT * FROM products;

Exit:

    exit

Stop/remove containers:

    docker compose down

Check volume:

    docker volume ls

Start again:

    docker compose up -d

Enter MySQL:

    docker compose exec mysql mysql -uroot -proot

Check:

    USE backend_db;

    SHOW TABLES;

    SELECT * FROM products;

Existing data should remain because the named volume was preserved.

---

# 12. Volume vs Network

Network:

    app
      ↓
    mysql

Purpose:

    Communication

Volume:

    mysql
      ↓
    mysql-data

Purpose:

    Persistence

---

# 13. Volume vs Backup

Important:

    Volume ≠ Backup

A volume provides persistent storage but does not automatically provide a complete backup strategy.

Production systems should have proper database backup and recovery mechanisms.

---

# 14. Bind Mount

Compose can also use a bind mount:

    volumes:
      - ./mysql-data:/var/lib/mysql

This maps a host directory directly to the container.

Named volume:

    mysql-data:/var/lib/mysql

Bind mount:

    ./mysql-data:/var/lib/mysql

---

# 🎯 Key Points

- Compose supports named volumes.
- Volumes preserve database data.
- MySQL commonly mounts `/var/lib/mysql`.
- `docker compose down` normally preserves named volumes.
- `docker compose down -v` removes Compose-managed volumes.
- Volume persistence is different from backup.