FROM openjdk:11
ARG JAR_FILE=build/libs/*.jar
COPY ./target/authentication-0.0.1-SNAPSHOT.jar TxT-common-api.jar
ENTRYPOINT ["java", "-jar", "/TxT-common-api.jar"]
