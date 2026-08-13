# Docker Architecture

## Main Components

Docker architecture mainly consists of:

- Docker Client
- Docker Daemon
- Docker Engine
- Docker Images
- Docker Containers
- Docker Registry

## Docker Client

Docker Client is the command-line interface used to interact with Docker.

Examples:

- docker run
- docker ps
- docker pull
- docker stop
- docker rm

## Docker Daemon

Docker Daemon (`dockerd`) is the background service responsible for managing Docker objects such as:

- Images
- Containers
- Networks
- Volumes

The Docker Client sends requests to the Docker Daemon.

## Docker Image

A Docker Image is a read-only template used to create containers.

## Docker Container

A Docker Container is a running instance of a Docker Image.

## Docker Registry

A Docker Registry stores Docker Images.

Example:

- Docker Hub

## Basic Flow

You
↓
Docker CLI
↓
Docker Daemon
↓
Docker Image
↓
Docker Container
↓
Running Application

## Example

docker run redis

Flow:

Docker CLI
↓
Docker Daemon
↓
Check Redis Image
↓
Pull from Docker Hub if required
↓
Create Redis Container
↓
Start Redis Container