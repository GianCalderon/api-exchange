FROM openjdk:11-jre-slim
COPY "./target/api-exchange-1.0.0.jar" "api-exchange.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","/api-exchange.jar"]