FROM jboss/wildfly:10.1.0.Final

MAINTAINER "Gang swiezakow"

#CMD sudo docker run --name="zakupy" -it -e MYSQL_ROOT_PASSWORD=pass -p 3306 -d mysql
#CMD sudo docker exec -it zakupy mysql -uroot -p=pass
#CMD create database zakupydb;


#COPY webapp/target/webapp.war /opt/jboss/wildfly/standalone/deployments/
EXPOSE 8080:8888

RUN wildfly/bin/add-user.sh root pass --silent



CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]


