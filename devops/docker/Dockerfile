FROM postgres:bookworm

ARG DEBIAN_FRONTEND=noninteractive

RUN apt-get update
RUN apt-get install apt-utils -y
RUN apt-get install sudo -y
RUN apt-get install git -y
RUN apt-get install bash-completion -y
RUN apt-get install nginx -y
RUN apt-get install openjdk-17-jre-headless -y
RUN apt-get install maven -y
RUN apt-get install libatk1.0-0\
        libatk-bridge2.0-0\
        libdrm2\
        libxkbcommon0\
        libatspi2.0-0\
        libxcomposite1\
        libxdamage1\
        libxfixes3\
        libxrandr2\
        libgbm1\
        libpango-1.0-0\
        libcairo2 -y

USER postgres
RUN pg_createcluster 16 main_cluster \
    && /etc/init.d/postgresql restart \
    && psql -c "ALTER USER postgres PASSWORD 'postgres'" \
    && psql -c 'CREATE DATABASE test'

USER root
WORKDIR /tmp
RUN git clone https://github.com/5anji/car-hunter.git
WORKDIR /tmp/car-hunter/src/api
RUN /etc/init.d/postgresql restart && mvn clean install
WORKDIR /tmp/car-hunter/src/api/target
EXPOSE 8000-8089
# /etc/init.d/postgresql restart && java -jar web-scraping-0.0.1-SNAPSHOT.jar
# WORKDIR ${UHOME}
CMD /bin/bash -l
