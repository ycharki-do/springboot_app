FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /app
COPY  . /app
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build target/books-spring-boot.jar .
EXPOSE 8087
ENTRYPOINT ["java","-jar","bookStore-0.0.1-SNAPSHOT.jar"]
