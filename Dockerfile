FROM openjdk:12-alpine
COPY target/AWSManagerService-*.jar /awsmanager-microservice.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "awsmanager-microservice.jar"]