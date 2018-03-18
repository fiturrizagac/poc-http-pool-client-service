FROM davidcaste/alpine-java-unlimited-jce:jre8
MAINTAINER Franco Iturrizaga <fiturrizagac@gmail.com>
WORKDIR /opt/client-service
ADD build/libs/poc-http-pool-delay-service.jar app.jar
RUN apk update \
  	&& apk add ca-certificates wget \
  	&& update-ca-certificates \
		&& apk add --update tzdata \
		&& cp /usr/share/zoneinfo/America/Lima /etc/localtime \
		&& echo  "America/Lima" > /etc/timezone \
		&& apk del tzdata \
		&& rm -rf /var/cache/apk/*
ENTRYPOINT ["/bin/sh","-c","sleep 5;java -jar app.jar"]