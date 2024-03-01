FROM openjdk:17
#VOLUME /tmp
#si on veut passer le jar en argument de la commande docker build # exemple --build-arg JAR_FILE=build/libs/*.jar -t myorg/myapp .
#ARG JAR_FILE
COPY build/libs/Projet_Gestock-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]