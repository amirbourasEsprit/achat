FROM openjdk11
ADD target/achat-1.0.jar achat.jar
EXPOSE 8089
CMD ["java", "-jar", "/achat.jar"]