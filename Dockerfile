FROM eclipse-temurin:17-jdk as BUILD

COPY . /usr/src/app
WORKDIR /usr/src/app
RUN chmod +x gradlew && ./gradlew bootJar --no-daemon

FROM eclipse-temurin:17-jre
ENV PORT 8080
EXPOSE 8080
COPY --from=BUILD /usr/src/app/build/libs /opt/app
WORKDIR /opt/app

CMD ["/bin/bash", "-c", "find -type f -name '*-SNAPSHOT.jar' | xargs java -jar"]
