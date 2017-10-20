FROM jboss/wildfly:10.1.0.Final

MAINTAINER "Gang Swieżaków"



RUN mkdir -p wildfly/modules/system/layers/base/com/mysql/driver/main
ADD config/mysql.module.xml wildfly/modules/system/layers/base/com/mysql/driver/main/module.xml
ADD config/mysql-connector-java-5.1.44-bin.jar wildfly/modules/system/layers/base/com/mysql/driver/main/


EXPOSE 8080:8888

RUN wildfly/bin/add-user.sh root pass --silent



CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]

ADD docker/standalone.xml $WILDFLY_HOME/standalone/configuration


