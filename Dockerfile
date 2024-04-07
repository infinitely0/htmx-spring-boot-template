FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -Dmaven.test.skip=true package

FROM amazoncorretto:17

VOLUME /tmp

EXPOSE 1212

COPY --from=builder /app/target/htmx-spring-boot.jar /app/htmx-spring-boot.jar

ENTRYPOINT ["java", "-jar", "/app/htmx-spring-boot.jar"]
