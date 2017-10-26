FROM jboss/wildfly:latest
RUN /opt/jboss/wildfly/bin/add-user.sh admin admin --silent
ADD customization /opt/jboss/wildfly/customization/
ADD webapp/target/webapp.war /opt/jboss/wildfly/webapp.war
RUN mkdir -p /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main
COPY customization/mysql.module.xml /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/module.xml
COPY customization/mysql-connector-java-5.1.44-bin.jar /opt/jboss/wildfly/modules/system/layers/base/com/mysql/main/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]
