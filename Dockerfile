FROM jboss/wildfly:latest
#FROM Gang Swiezakow

ADD customization /opt/jboss/wildfly/customization/

CMD ["/opt/jboss/wildfly/customization/execute.sh"]