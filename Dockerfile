# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# Copy built jar from builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose container port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-jar","app.jar"]