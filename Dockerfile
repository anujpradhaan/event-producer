FROM java:8
EXPOSE 8080
ADD target/producer-0.0.1-SNAPSHOT.jar producer-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "producer-0.0.1-SNAPSHOT.jar"]