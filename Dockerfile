
FROM openjdk:17-jdk-slim


WORKDIR /app

COPY target/bdget-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet_RMKNXQLAHHEARTIA /app/oracle_wallet


EXPOSE 8080

ENV TNS_ADMIN=/app/oracle_wallet

CMD ["java", "-jar", "app.jar"]