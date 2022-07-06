FROM amazoncorretto:11-alpine-jdk
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY build/libs/webshop-0.0.1.jar webshop-0.0.1.jar
ENTRYPOINT ["java","-jar","/webshop-0.0.1.jar"]