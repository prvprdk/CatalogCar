FROM gradle:7.6.1-jdk-alpine
COPY . .
RUN gradle build
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "build/libs/catalogOfCars-0.0.1-SNAPSHOT.jar"]