#Build stage

FROM gradle:latest AS BUILD
WORKDIR /usr/app/
COPY . . 
RUN gradle build

# Package stage

FROM openjdk:latest
ENV JAR_NAME=Darts-1.0-SNAPSHOT.jar
ENV APP_HOME=/usr/app
WORKDIR $APP_HOME
COPY --from=BUILD $APP_HOME .
CMD cd $APP_HOME
CMD ls
ENTRYPOINT exec java -jar $APP_HOME/build/libs/$JAR_NAME  