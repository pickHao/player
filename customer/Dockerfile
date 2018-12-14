FROM openjdk:8-jdk

ENV name="customer-web-0.0.1-SNAPSHOT.jar"

WORKDIR /home


COPY ./customer/customer-web/target/$name  .

EXPOSE 8085

CMD java -jar $name
