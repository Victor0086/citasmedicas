FROM eclipse-temurin:22-jdk AS buildstage 

RUN apt-get update && apt-get install -y maven

WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline


COPY src /app/src
COPY Wallet_RMKNXQLAHHEARTIA /app/wallet

ENV TNS_ADMIN=/app/wallet


RUN mvn clean package -DskipTests


RUN ls -la target/

FROM eclipse-temurin:22-jdk 


COPY --from=buildstage /app/target/*.jar /app/citas.jar


COPY Wallet_RMKNXQLAHHEARTIA /app/wallet

ENTRYPOINT [ "java", "-jar","/app/citas.jar" ] 
