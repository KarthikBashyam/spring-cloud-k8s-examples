FROM gradle:jdk10 AS builder
COPY --chown=gradle:gradle . /home/gradle/src
ADD --chown=gradle . /home/gradle
WORKDIR /home/gradle/src
RUN chmod 777 /home/gradle
RUN gradle build

FROM openjdk:8-jdk-alpine
COPY --from=builder /home/gradle/src/build/libs/application-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
