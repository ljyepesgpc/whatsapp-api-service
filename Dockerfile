# ============================
# STAGE 1: Build
# ============================
FROM openjdk:8-jre-alpine

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# ============================
# STAGE 2: Runtime
# ============================
FROM openjdk:8-jre-slim

RUN useradd -m spring
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
USER spring

ENTRYPOINT ["java","-Xmx256m","-Xms128m","-jar","app.jar"]
