#
# Build stage
#

# Enable below 8 lines for maven builds
##FROM maven:3.6.0-jdk-11-slim AS build
##COPY src /home/app/src
##COPY pom.xml /home/app
##RUN mvn -f /home/app/pom.xml clean package

##FROM openjdk:11-jre-slim
##COPY --from=build /home/app/target/nbsadapter-1.0.1.jar /tmp/nbsadapter.jar
##ENTRYPOINT ["java", "-cp", "/tmp/nbsadapter.jar", "-Dspring.profiles.active=development", "org.springframework.boot.loader.JarLauncher"]




# Enable below 4 lines for gradle builds
FROM gradle:4.7.0-jdk8-alpine AS build
COPY src /home/app/src
COPY build.gradlel /home/app
RUN gradle build

FROM openjdk:11-jre-slim
COPY --from=build /home/app/build/libs/nbsadapter.jar /tmp/nbsadapter.jar
ENTRYPOINT ["java", "-cp", "/tmp/nbsadapter.jar", "-Dspring.profiles.active=development", "org.springframework.boot.loader.JarLauncher"]


