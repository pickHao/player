FROM openjdk:8-jdk

ENV name="comment-web-0.0.1-SNAPSHOT.jar"

WORKDIR /home


COPY ./comment/comment-web/target/$name  .

EXPOSE 8888

CMD java -jar $name
