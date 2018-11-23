FROM openjdk:8-jdk

ENV name="video-web-0.0.1-SNAPSHOT.jar"

WORKDIR /home


COPY ./video/video-web/target/$name  .

EXPOSE 8081

CMD java -jar $name
