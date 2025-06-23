FROM eclipse-temurin:17-jdk-alpine
RUN apk --no-cache add maven
WORKDIR /opt
COPY pom.xml .
COPY src src
RUN mvn clean package
COPY target/*.jar /opt/ArmyDiscordBot.jar
ENTRYPOINT ["java", "-jar", "ArmyDiscordBot.jar"]
