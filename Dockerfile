FROM openjdk:17

LABEL maintainer="mouhamed niang"

EXPOSE 8080

ADD target/ecommerce-back-end-0.0.1-SNAPSHOT.jar ecommerce-back-end.jar

ENTRYPOINT ["java", "-jar", "ecommerce-back-end.jar"]
