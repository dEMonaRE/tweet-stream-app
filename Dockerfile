FROM openjdk:11
EXPOSE 8080
ARG JAR_FILE=target/*.jar
ADD ./target/tweet-stream-app.jar tweet-stream-app.jar

ENTRYPOINT ["java","-Djdk.tls.client.protocols=TLSv1.2","-jar", "/tweet-stream-app.jar"]