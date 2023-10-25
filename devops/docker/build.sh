#!/usr/bin/env bash

set -e
set -u

function error_echo() {
    >&2 echo "$1"
}

function usage() {
    error_echo ""
    error_echo "$0 command"
    error_echo "  command    : build, run"
}

COMMAND=${1:?Command must be specified in the first parameter}
shift

DOCKERFILE="Dockerfile"

while getopts h cmd_flag; do
    case "${cmd_flag}" in
        h) usage
           exit 1
           ;;
        *) usage
           exit 1
           ;;
    esac
done

CONTAINER_NAME=car-hunter:debian12
CONTAINER=postgres:bookworm
case ${COMMAND} in
    build)
        GNAME=`id -g -n`
        GID=`id -g`
        docker pull ${CONTAINER}
        docker build \
               --build-arg=UNAME=${USER} \
               --build-arg=UID=${UID} \
               --build-arg=GNAME=${GNAME} \
               --build-arg=GID=${GID} \
               --build-arg=UHOME=${HOME} \
               -t ${CONTAINER_NAME} \
               -f ${DOCKERFILE} .
        ;;
    run)
        docker container ls -la | grep dev-container && docker container rm -f dev-container
        # docker stop dev-container
        CONTAINER=$(docker run \
                           --rm -d -P -it \
                           -h ${CONTAINER_NAME} \
                           --user ${USER} \
                           -v /home/${USER}:/home/${USER} \
                           -v $(dirname $SSH_AUTH_SOCK):$(dirname $SSH_AUTH_SOCK) \
                           -v /sys/fs/cgroup:/sys/fs/cgroup:ro \
                           -e SSH_AUTH_SOCK=$SSH_AUTH_SOCK \
                           -e DISPLAY -e "QT_X11_NO_MITSHM=1" \
                           -e POSTGRES_PASSWORD=postgres \
                           -e SERVER_PORT=8081 \
                           -p 8081:8081 \
                           --volume="/tmp/.X11-unix:/tmp/.X11-unix:rw" \
                           --device=/dev/net/tun --sysctl="net.ipv6.conf.default.disable_ipv6=0" \
                           --privileged \
                           --shm-size="1g" \
                           --hostname=${CONTAINER_NAME} \
                           --name dev-container \
                           ${CONTAINER_NAME} \
                 )
        if [ -z $CONTAINER ]; then
            error_echo "Container ${CONTAINER_NAME} start failed"
            exit 1
        else
            docker exec -it dev-container bash -l
        fi
        ;;
    *)
        error_echo "Unknown command ${COMMAND}"
        usage
        exit 1
        ;;
esac
