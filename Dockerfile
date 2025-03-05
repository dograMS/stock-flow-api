FROM maven:3.9.0-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
