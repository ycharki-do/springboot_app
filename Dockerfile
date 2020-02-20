FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /app
COPY  . /app
RUN mvn package -Dmaven.test.skip=true

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/bookStore-0.0.1-SNAPSHOT.jar /app
EXPOSE 8087
ENTRYPOINT ["java","-jar","/app/bookStore-0.0.1-SNAPSHOT.jar"]
