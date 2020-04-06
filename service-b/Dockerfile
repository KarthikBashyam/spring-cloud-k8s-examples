FROM openjdk:8-jdk-alpine
COPY build/libs/service-b-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 9003
ENTRYPOINT ["java", "-jar", "/app.jar"]