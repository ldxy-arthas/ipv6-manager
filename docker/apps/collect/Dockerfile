FROM golang:1.19.5 as build

LABEL author=yuluo \
    email=yuluo829@aliyun.com \
    version="2023.1.28"

RUN sed -i s@/archive.ubuntu.com/@/mirrors.aliyun.com/@g /etc/apt/sources.list
RUN sed -i s@/security.ubuntu.com/@/mirrors.aliyun.com/@g /etc/apt/sources.list
RUN apt clean
RUN apt-get update -y

FROM alpine:latest
RUN apk update && apk add alpine-sdk git && rm -rf /var/cache/apk/*

ENV GO111MODULE=on
ENV GOPROXY=https://goproxy.cn,direct

VOLUME ${APP_HOME}
WORKDIR ${APP_HOME}

COPY . .
RUN go mod download

RUN go build -o ./app ./main.go

ENTRYPOINT ["./app"]
