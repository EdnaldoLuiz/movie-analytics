FROM ubuntu:latest as build

RUN apt-get update && apt-get install -y openjdk-17-jdk
COPY . .

RUN apt-get install -y maven && mvn clean install
FROM openjdk:17-jdk

EXPOSE 8080

COPY --from=build /target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]