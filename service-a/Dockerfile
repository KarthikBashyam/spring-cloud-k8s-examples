FROM gradle:jdk8 AS builder1
COPY --chown=gradle:gradle . /home/gradle/src
ADD --chown=gradle . /home/gradle
WORKDIR /home/gradle/src
RUN gradle build

FROM openjdk:8-jdk-alpine
COPY --from=builder1 /home/gradle/src/build/libs/service-a-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9002
ENTRYPOINT ["java", "-jar", "/app.jar"]