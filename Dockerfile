FROM gradle:8.6.0 AS build
COPY src /home/app/src
COPY build.gradle /home/app
COPY settings.gradle /home/app

WORKDIR /home/app
RUN gradle -p /home/app clean build --no-daemon

RUN ls -R /home/app/build/libs

FROM openjdk:17-jdk-slim

COPY --from=build /home/app/build /home/app
COPY --from=build /home/app/build/libs/FinanceTracker-0.0.1-SNAPSHOT.jar /home/app/app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
