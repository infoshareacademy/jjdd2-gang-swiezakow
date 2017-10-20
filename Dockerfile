FROM jboss/wildfly:10.1.0.Final

MAINTAINER "Gang Swieżaków"



RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main
COPY docker/mysql.module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main/module.xml
COPY docker/mysql-connector-java-5.1.44-bin.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/driver/main/
COPY docker/standalone.xml /opt/jboss/wildfly/standalone/configuration

EXPOSE 8080:8888

RUN wildfly/bin/add-user.sh root pass --silent
COPY target/webapp.war /opt/jboss/wildfly/standalone/deployments/


CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]




