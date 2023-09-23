FROM postgres:bookworm

ARG DEBIAN_FRONTEND=noninteractive
ARG UNAME
ARG UID
ARG GNAME
ARG GID
ARG UHOME

RUN apt-get update
RUN apt-get install apt-utils -y
RUN apt-get install sudo -y
RUN apt-get install git -y
RUN apt-get install bash-completion -y
RUN apt-get install nginx -y
RUN apt-get install openjdk-17-jre-headless -y

RUN groupadd -g ${GID} ${GNAME} -f \
    && useradd -p '${UNAME}' -g ${GNAME} -u ${UID} ${UNAME} \
    && echo "${UNAME}:${UNAME}" | chpasswd \
    && adduser ${UNAME} sudo \
    && usermod -a -G ssl-cert ${UNAME};

USER ${UNAME}
WORKDIR ${UHOME}
CMD /bin/bash -l
