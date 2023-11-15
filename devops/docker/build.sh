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
        docker pull ${CONTAINER}
        docker build \
               -t ${CONTAINER_NAME} \
               -f ${DOCKERFILE} .
        ;;
    run)
        docker container ls -la | grep dev-container && docker container rm -f dev-container
        # docker stop dev-container
        CONTAINER=$(docker run \
                           --rm -d -P -it \
                           -h ${CONTAINER_NAME} \
                           -e POSTGRES_PASSWORD=postgres \
                           -e SERVER_PORT=8081 \
                           -p 8081:8081 \
                           --hostname=${CONTAINER_NAME} \
                           --name dev-container \
                           ${CONTAINER_NAME} \
                 )

        if [ -z $CONTAINER ]; then
            error_echo "Container ${CONTAINER_NAME} start failed"
            exit 1
        else
            if [ "$OSTYPE" == "msys" ]; then
                winpty docker exec -it dev-container bash -l
            else
                docker exec -it dev-container bash -l
            fi
        fi
        ;;
    *)
        error_echo "Unknown command ${COMMAND}"
        usage
        exit 1
        ;;
esac
