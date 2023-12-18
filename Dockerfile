FROM amazoncorretto:17-alpine-jdk

# create a directory for the APP
WORKDIR /app

# copy everything from the current repository to the app directory
COPY . .

# Build the APP
RUN ./gradlew clean build -x test

# Expose port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "./build/libs/demo-0.0.1-SNAPSHOT.jar"]
