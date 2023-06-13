FROM --platform=linux/amd64 maven:3.8.5-openjdk-17 as build
ADD target/*.jar app.jar
ADD https://github.com/ufoscout/docker-compose-wait/releases/download/2.9.0/wait /wait
RUN chmod +x /wait
CMD /wait &&  java -jar app.jar