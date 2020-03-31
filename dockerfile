FROM openjdk:8-jdk-alpine
WORKDIR /web

ADD ./emailApi/target/email-0.0.1-SNAPSHOT.jar /web
CMD ["java", "-jar", "email-0.0.1-SNAPSHOT.jar"]
