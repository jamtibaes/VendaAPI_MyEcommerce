FROM openjdk:17
COPY ./build/libs/VendaAPI-0.0.1-SNAPSHOT.jar VendaAPI-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "VendaAPI-0.0.1-SNAPSHOT.jar"]