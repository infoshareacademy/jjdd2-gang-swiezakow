FROM jboss/wildfly:latest

ADD config /opt/jboss/wildfly/config/

COPY webapp/target/webapp.war /opt/jboss/wildfly/config/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]