FROM eclipse-temurin:17-jdk-alpine
RUN apk --no-cache add maven
WORKDIR /app
COPY pom.xml /app/pom.xml
COPY data /app/target/data
COPY src /app/src
RUN mvn clean package
ENTRYPOINT ["java", "-jar", "/app/target/armydiscordbot.jar"]
