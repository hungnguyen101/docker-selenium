FROM openjdk:19-jdk-alpine3.16

#create workspace && jump into in container
WORKDIR  /usr/share/udemy

ADD target/selenium-docker.jar              selenium-docker.jar
ADD target/selenium-docker-tests.jar        selenium-docker-tests.jar
ADD target/libs                             libs


#in case of any dependency like .csv .json .xls

#please ADD that as well

#ADD suite files
ADD book-flight-module.xml                  book-flight-module.xml
ADD search-module.xml                       search-module.xml
ADD healthcheck.sh                          healthcheck.sh

#parametries
#BROWSER
#HUB_HOST
#MODULE - Test suite
ENTRYPOINT sh healthcheck.sh

#add library needed inside container:: curl, jq
RUN apk add curl jq