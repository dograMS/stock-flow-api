FROM maven:3.9.0-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build  /app/target/stock-flow-api*.jar app.jar
EXPOSE 16000
ENTRYPOINT ["java", "-jar", "app.jar"]


