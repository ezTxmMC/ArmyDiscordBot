FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY pom.xml .
COPY src src
RUN mvn clean package
COPY target/*.jar /opt/ArmyDiscordBot.jar
ENTRYPOINT ["java", "-jar", "/opt/ArmyDiscordBot.jar"]
