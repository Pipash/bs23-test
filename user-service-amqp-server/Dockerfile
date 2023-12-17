FROM openjdk:11
VOLUME /tmp
COPY target/user-service-amqp-server-0.0.1-SNAPSHOT.jar user-service-amqp-server.jar
ENTRYPOINT ["java","-jar","/user-service-amqp-server.jar"]