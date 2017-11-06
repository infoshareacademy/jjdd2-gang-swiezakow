FROM jboss/wildfly:latest

ADD customization /opt/jboss/wildfly/customization/

COPY target/report-rest-server.war /opt/jboss/wildfly/config/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]