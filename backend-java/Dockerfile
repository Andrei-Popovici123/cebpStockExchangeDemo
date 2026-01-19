FROM eclipse-temurin:24-jdk-alpine

WORKDIR /app

COPY target/server-0.0.1-SNAPSHOT.jar stockService.jar

EXPOSE 8080


ENTRYPOINT ["java","-jar","stockService.jar"]