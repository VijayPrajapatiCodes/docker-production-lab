# Docker Engine

## What is Docker Engine?

Docker Engine is the core Docker platform responsible for creating, running, and managing containers and other Docker resources.

## Main Responsibilities

Docker Engine manages:

- Containers
- Images
- Networks
- Volumes

## Main Components

Docker Engine involves:

- Docker Daemon
- Docker REST API
- Container Runtime

## Docker Daemon

Docker Daemon (`dockerd`) is the background service responsible for managing Docker objects.

## Docker REST API

The Docker REST API allows clients such as the Docker CLI to communicate with the Docker Daemon.

## Container Runtime

Container runtime components are responsible for executing containers.

Modern Docker uses components such as:

- containerd
- runc

## Basic Architecture

Docker CLI
↓
Docker Daemon
↓
Container Runtime
↓
Container

## Docker Engine vs Docker Daemon

Docker Engine is the overall Docker platform/core system.

Docker Daemon (`dockerd`) is the background service that manages Docker resources.

## Example

docker run redis

Flow:

Docker CLI
↓
Docker Engine
↓
Docker Daemon
↓
Check Redis Image
↓
Pull Image if Required
↓
Create Redis Container
↓
Start Container