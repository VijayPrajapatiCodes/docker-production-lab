# 🔐 Docker Compose Environment Variables

## 1. What are Environment Variables?

Environment variables are configuration values supplied to applications and containers.

Examples:

    DB_HOST=mysql
    DB_PORT=3306
    DB_NAME=backend_db
    DB_USERNAME=root
    DB_PASSWORD=root

---

# 2. Why Use Environment Variables?

Avoid hard-coding configuration.

Instead of:

    username: root
    password: root

use:

    username: ${DB_USERNAME}
    password: ${DB_PASSWORD}

Benefits:

- Configuration can change without modifying application code.
- Different environments can use different values.
- Sensitive values can be kept outside the Compose file.
- Configuration becomes easier to manage.

---

# 3. .env File

Project structure:

    SpringDocker/
    ├── docker-compose.yml
    ├── Dockerfile
    ├── .env
    ├── pom.xml
    └── src/

Example `.env`:

    MYSQL_ROOT_PASSWORD=root
    MYSQL_DATABASE=backend_db

    DB_HOST=mysql
    DB_PORT=3306
    DB_NAME=backend_db
    DB_USERNAME=root
    DB_PASSWORD=root

---

# 4. Variable Substitution

Compose:

    environment:
      MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
      MYSQL_DATABASE: ${MYSQL_DATABASE}

`.env`:

    MYSQL_ROOT_PASSWORD=root
    MYSQL_DATABASE=backend_db

Compose substitutes the variables with their values.

---

# 5. Spring Boot Configuration

Application YAML:

    spring:
      datasource:
        url: jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}

Environment:

    DB_HOST=mysql
    DB_PORT=3306
    DB_NAME=backend_db
    DB_USERNAME=root
    DB_PASSWORD=root

---

# 6. Compose environment Section

Example:

    app:
      environment:
        DB_HOST: ${DB_HOST}
        DB_PORT: ${DB_PORT}
        DB_NAME: ${DB_NAME}
        DB_USERNAME: ${DB_USERNAME}
        DB_PASSWORD: ${DB_PASSWORD}

This passes the values into the app container.

---

# 7. env_file

Example:

    app:
      env_file:
        - .env

This can load variables from an environment file into the container environment.

---

# 8. Default Values

Compose interpolation can specify a default value:

    DB_PORT: ${DB_PORT:-3306}

Meaning:

If `DB_PORT` is defined:

    use DB_PORT

Otherwise:

    use 3306

---

# 9. .gitignore

If `.env` contains secrets, add:

    .env

to:

    .gitignore

Do not commit real credentials to GitHub.

---

# 10. .env.example

Instead of committing the real `.env`, create:

    .env.example

Example:

    MYSQL_ROOT_PASSWORD=change-me
    MYSQL_DATABASE=backend_db

    DB_HOST=mysql
    DB_PORT=3306
    DB_NAME=backend_db
    DB_USERNAME=root
    DB_PASSWORD=change-me

Developers can create their own `.env`.

---

# 11. Complete Example

## .env

    MYSQL_ROOT_PASSWORD=root
    MYSQL_DATABASE=backend_db

    DB_HOST=mysql
    DB_PORT=3306
    DB_NAME=backend_db
    DB_USERNAME=root
    DB_PASSWORD=root

---

## docker-compose.yml

    services:

      app:
        build: .
        ports:
          - "8081:8081"

        environment:
          DB_HOST: ${DB_HOST}
          DB_PORT: ${DB_PORT}
          DB_NAME: ${DB_NAME}
          DB_USERNAME: ${DB_USERNAME}
          DB_PASSWORD: ${DB_PASSWORD}

        depends_on:
          mysql:
            condition: service_healthy

      mysql:
        image: mysql:8

        environment:
          MYSQL_ROOT_PASSWORD: ${MYSQL_ROOT_PASSWORD}
          MYSQL_DATABASE: ${MYSQL_DATABASE}

        volumes:
          - mysql-data:/var/lib/mysql

        healthcheck:
          test: ["CMD", "mysqladmin", "ping", "-h", "localhost", "-uroot", "-p${MYSQL_ROOT_PASSWORD}"]
          interval: 5s
          timeout: 5s
          retries: 10
          start_period: 20s

    volumes:
      mysql-data:

---

## application.yml

    server:
      port: 8081

    spring:
      datasource:
        url: jdbc:mysql://${DB_HOST}:${DB_PORT}/${DB_NAME}
        username: ${DB_USERNAME}
        password: ${DB_PASSWORD}

      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true

---

# 12. Practical

Check Compose configuration:

    docker compose config

This renders the Compose configuration after variable interpolation and helps identify configuration problems.

Start:

    docker compose up -d --build

Check:

    docker compose ps

Check application logs:

    docker compose logs app

Check MySQL logs:

    docker compose logs mysql

---

# 13. Configuration Flow

    .env
      ↓
    Docker Compose
      ↓
    Container Environment
      ↓
    Spring Boot
      ↓
    Database Connection

Example:

    DB_HOST=mysql
          ↓
    jdbc:mysql://mysql:3306/backend_db
          ↓
        MySQL

---

# 14. Development vs Production

Development may use:

    DB_USERNAME=root
    DB_PASSWORD=root

Production should use properly managed credentials/secrets.

Do not treat `.env` as a complete production secret-management solution.

---

# 15. Important Security Rules

Never commit:

    Real database passwords
    API keys
    SMTP passwords
    JWT secrets
    Cloud credentials

Use:

    .env

locally and:

    .env.example

for documentation.

For production, use an appropriate secret-management mechanism.

---

# 🎯 Key Points

- Environment variables separate configuration from application code.
- `.env` can provide Compose variable values.
- `${VARIABLE}` is used for Compose interpolation.
- `environment` passes variables into containers.
- `env_file` can load environment variables from a file.
- `.env` containing secrets should not be committed.
- `.env.example` documents required variables.
- `docker compose config` is useful for validating/interpolating configuration.