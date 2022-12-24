#
# Build stage
#

# Enable below block of code for maven builds
FROM maven:3.6.0-jdk-11-slim AS build_maven
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Enable below block of code for gradle builds
##FROM gradle:6.8-jdk8 AS build_gradle
##COPY src /home/app/src
##COPY build.gradle /home/app
##WORKDIR /home/app
##RUN gradle --no-daemon build

FROM openjdk:11-jre-slim
##COPY --from=build_gradle /home/app/build/libs/nbsadapter.jar /tmp
COPY --from=build_maven /home/app/target/nbsadapter-1.0.1.jar /tmp/nbsadapter.jar
ENTRYPOINT ["java", "-cp", "/tmp/nbsadapter.jar", "-Dspring.profiles.active=development", "org.springframework.boot.loader.JarLauncher"]


