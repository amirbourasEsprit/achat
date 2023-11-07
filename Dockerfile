FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine
ADD target/achat-1.0.jar achat-1.0.jar
EXPOSE 8089
CMD ["java", "-jar", "/achat-1.0.jar"]