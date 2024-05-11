FROM maven:3.8.1-openjdk-17 as build
WORKDIR /app
COPY . .
RUN chmod +x ./wait-for-postgres.sh
RUN mvn clean install

FROM openjdk:17-jdk
WORKDIR /app
COPY --from=build /app .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]