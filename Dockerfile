# ============================
# STAGE 1: Build
# ============================
FROM maven:3.9.6-eclipse-temurin-8 AS builder

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# ============================
# STAGE 2: Runtime
# ============================
FROM openjdk:8-jre-alpine
# Crear usuario no root (Alpine usa adduser)
RUN adduser -D -h /home/spring spring
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
USER spring

ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]
