FROM maven:3-openjdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn package

FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /java
COPY --from=builder /app/target/*.jar /java/app.jar
ENTRYPOINT java $JAVA_OPTS -jar app.jar $JAVA_ARGS