# ⚡ Docker Resource Limits

## 1. Why Resource Limits?

A container can consume CPU and memory from the host.

Example:

    Spring Boot
         ↓
    High Memory Usage
         ↓
    Host Resources
         ↓
    Other Containers Affected

Resource limits help control consumption.

---

# 2. Memory Limit

Example:

    services:

      app:
        mem_limit: 512m

This limits the container's memory according to the supported Compose/runtime configuration.

---

# 3. CPU Limits

CPU resources can also be limited using the Compose/runtime features supported by the deployment environment.

Concept:

    Container
       ↓
    CPU Limit

---

# 4. Why Production Uses Limits?

Resource limits help with:

- Predictable resource usage
- Preventing one service from consuming everything
- Better isolation
- Better capacity planning

---

# 5. Example

    services:

      app:
        build: .
        mem_limit: 512m

      redis:
        image: redis:latest
        mem_limit: 256m

---

# 6. Important

Resource configuration syntax and enforcement can depend on the Docker/Compose deployment mode.

Always verify the configuration for the environment where the application is deployed.

---

# 🎯 Key Points

- Containers consume host resources.
- Memory limits control memory consumption.
- CPU limits can control CPU consumption.
- Resource limits are useful for production.