# 1. Front building
FROM node:20 AS frontend-build
WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm install
COPY frontend/ .
RUN npm run build

# 2. Backend build on Maven
FROM maven:3.9.2-eclipse-temurin-17 AS backend-build
WORKDIR /app/backend
COPY backend/pom.xml .
COPY backend/src ./src
RUN mvn clean package -DskipTests

# 3. JDK
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=backend-build /app/backend/target/backend-0.0.1-SNAPSHOT.jar ./backend.jar
COPY --from=frontend-build /app/frontend/dist ./static

# Launching
CMD ["java", "-jar", "backend.jar"]
