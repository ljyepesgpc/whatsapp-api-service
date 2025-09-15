# ============================
# STAGE 1: Build
# ============================
FROM maven:3.9.6-eclipse-temurin-21 AS builder

# Set working directory
WORKDIR /app

# Copiar pom.xml y descargar dependencias primero (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar y empaquetar la app
RUN mvn clean package -DskipTests

# ============================
# STAGE 2: Runtime
# ============================
FROM eclipse-temurin:21-jre

# Crear usuario no root por seguridad
RUN useradd -m spring

# Set working directory
WORKDIR /app

# Copiar el jar generado desde el stage anterior
COPY --from=builder /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8080

# Usar el usuario no root
USER spring

# Comando de arranque
ENTRYPOINT ["java","-jar","app.jar"]
