START WITH:
dm env dev
eval $("C:\Program Files\Docker Toolbox\docker-machine.exe" env dev)

CUSTOMER:
in root folder:
cd /c/workspace/crude/SpringRestMongoDb
mvn install
docker build -t rmichniewskihome/application:back-end_customers .
docker push rmichniewskihome/application:back-end_customers
on AWS ec2
docker pull rmichniewskihome/application:back-end_customers
remove old container if exists
docker rm -f back-end_customers
docker run -d --name=back-end_customers -p 8080:8080 --rm rmichniewskihome/application:back-end_customers

LOGIN:
in root folder:
cd /c/workspace/crude/spring-boot-security-jwt-example/spring-boot-authentication-service
mvn install
docker build -t rmichniewskihome/application:back-end_login .
docker push rmichniewskihome/application:back-end_login
on AWS ec2
docker pull rmichniewskihome/application:back-end_login
remove old container if exists
docker rm -f back-end_login
docker run -d --name=back-end_login -p 8082:8082 --rm rmichniewskihome/application:back-end_login

FRONT:
in root folder:
cd /c/workspace/crude/Angular6SpringBoot
local:
npm install ? - to create node_modules
ng serve
ng build --prod
docker build -t rmichniewskihome/application:front-end .
docker push rmichniewskihome/application:front-end

on AWS ec2:
docker pull rmichniewskihome/application:front-end
remove old container if exists
docker rm -f front-end
docker run -d --name=front-end -p 80:80 --rm rmichniewskihome/application:front-end

in crude directory
docker-compose -f Dockercompose.yml up -d

WIN:
docker run -p 9090:9090 -v //c/workspace/crude/prometheus/prometheus.yml://etc/promeheus -d prom/prometheus:v2.9.2
EC2:
docker run -d --name=prometheus -p 9090:9090 -v /etc/prometheus/:/etc/prometheus/ prom/prometheus
docker run -d --name=grafana -p 3000:3000 grafana/grafana

JENKINS:
docker run -d --name=jenkins -p 8089:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts
http://3.94.109.100:8089/

prometheus
DZONE

grafana

- update IP addresses especially front-end
- create prometheus.yml file
#Global configurations
global:
  scrape_interval:     3600s # Set the scrape interval to every 5 seconds.
  evaluation_interval: 3600s # Evaluate rules every 5 seconds.
scrape_configs:
  - job_name: 'login-application'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['3.94.109.100:8082'] <------- udpate IP

ZUUL:
docker run -d --name=zuul -p 8762:8762 --rm rmichniewskihome/application:zuul

EUREKA:
docker run -d --name=eureka -p 8761:8761 --rm rmichniewskihome/application:eureka