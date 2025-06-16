# Use Maven with JDK 21
FROM maven:3.9.6-eclipse-temurin-21 as builder

WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Step 2: Use a lightweight Java image to run the app
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
