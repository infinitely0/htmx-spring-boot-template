# htmx + Spring Boot

Sample application integrating htmx with Spring Boot.

The aim of this project is less about demonstrating all the features of htmx or Spring Boot, and
more about providing a simple, generic template for getting started with htmx and Spring Boot
development. With this in mind, dependencies are kept to a minimum and the project includes only
what I've found is common to most web applications:

- MVC components (model, controller, repository, and service) for a basic HTTP resource
- Thymeleaf for layouts, templating and working with htmx
- Error handling examples for form submissions and redirection strategies for 4xx and 5xx errors.
- Docker configured for running a PostgreSQL database and the server in containers
- Flyway for migrations, with an example migration to create a table
- Integration tests setup using H2 as an in-memory database

There is no authentication. I considered adding Spring Security, Shiro, or Keycloak but for
different reasons these seemed unsuitable for this project.

Bootstrap is used for styling but it can easily be replaced with another CSS framework. Lombok is
present but used only to reduce boilerplate. You can remove it and write your own getters and
setters.

# Application

The application is a standard to-do app demonstrating the CRUD operations and error-handling. It
behaves like a single-page application but does not involve any JavaScript.

![image](https://github.com/infinitely0/htmx-spring-boot-template/assets/6633057/032f8340-daf6-451f-ab4e-a5d048c8af64)

The index page explains how routing and resource handling works.

# Usage

You can run this demo in containers with Docker or locally with Maven.

## Docker

To start both the database and sever in containers, run `docker compose up`. The website will be
accessible at [http://localhost:8080](http://localhost:8080).

## Maven

To start locally, create a Postgres database (or use the preconfigured one from Docker, above -
just delete the server container to free the port) then run the Spring Boot server with Maven:
`./mvnw spring-boot:run`.
