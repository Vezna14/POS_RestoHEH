FROM openjdk:17
#VOLUME /tmp
#si on veut passer le jar en argument de la commande docker build # exemple --build-arg JAR_FILE=build/libs/*.jar -t myorg/myapp .
#ARG JAR_FILE
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]