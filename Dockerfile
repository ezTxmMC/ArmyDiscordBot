FROM eclipse-temurin:17-jdk-alpine
RUN apk --no-cache add maven
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package
COPY /app/target/*.jar /opt/ArmyDiscordBot.jar
ENTRYPOINT ["java", "-jar", "/opt/ArmyDiscordBot.jar"]
