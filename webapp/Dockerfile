FROM jboss/wildfly:latest

ADD customization /opt/jboss/wildfly/customization/

COPY target/webapp.war /opt/jboss/wildfly/config/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]