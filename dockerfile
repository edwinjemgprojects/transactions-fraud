FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/transaction-0.0.1.jar
COPY ${JAR_FILE} app_transaction.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_transaction.jar"]