FROM gradle:jdk8 AS builder
COPY --chown=gradle:gradle . /home/gradle/src
ADD --chown=gradle . /home/gradle
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:8-jdk-alpine
COPY --from=builder /home/gradle/src/build/libs/application-gateway-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9001
ENTRYPOINT ["java", "-jar", "/app.jar"]