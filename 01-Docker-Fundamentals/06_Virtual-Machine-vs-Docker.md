# Virtual Machine vs Docker

## Virtual Machine

A Virtual Machine is a virtual computer that runs on a physical host using a hypervisor.

A VM generally includes a complete guest operating system.

## Docker Container

A Docker Container is an isolated environment for running an application.

Containers share the host operating system kernel instead of requiring a separate full guest OS for every container.

## Virtual Machine Architecture

Physical Computer
↓
Host OS
↓
Hypervisor
↓
Virtual Machine
↓
Guest OS
↓
Application

## Docker Architecture

Physical Computer
↓
Host OS
↓
Docker Engine
↓
Container
↓
Application

## VM vs Docker

| Feature | Virtual Machine | Docker Container |
|---|---|---|
| Guest OS | Usually required | Not required as a separate full OS |
| Size | Generally larger | Generally smaller |
| Startup | Usually slower | Usually faster |
| Resource usage | Higher | Lower |
| Isolation | Strong | Process-level isolation |
| Use case | Full OS virtualization | Application/service isolation |

## When to Use VM?

- Full operating system isolation
- Running different operating systems
- Stronger isolation requirements

## When to Use Docker?

- Application packaging
- Microservices
- CI/CD
- Development environments
- Cloud deployment

## Key Concept

VM virtualizes a complete machine.

Docker containers package and isolate applications while sharing the host kernel.