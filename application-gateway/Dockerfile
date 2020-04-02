FROM gradle:jdk10 AS builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build

RUN chmod 777 .gradle/

FROM openjdk:8-jdk-alpine
COPY --from=builder /home/gradle/src/build/libs/application-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
