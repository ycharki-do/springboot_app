FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /app
COPY  /BackEnd/bookStore_backend/. .
RUN mvn package 

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build target/books-spring-boot.jar .
EXPOSE 8087
ENTRYPOINT ["java","-jar","books-spring-boot.jar"]
