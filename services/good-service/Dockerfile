FROM amazoncorretto:21.0.5-alpine
WORKDIR /app
COPY /build/libs/good-service.jar /app
CMD ["java", "-jar", "good-service.jar"]
EXPOSE 8081