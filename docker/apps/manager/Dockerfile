FROM openjdk:${JDK-VERSION}-jdk-alpine

LABEL author=yuluo \
    email=yuluo829@aliyun.com \
    version="2023.1.28"

RUN sed -i s@/archive.ubuntu.com/@/mirrors.aliyun.com/@g /etc/apt/sources.list
RUN sed -i s@/security.ubuntu.com/@/mirrors.aliyun.com/@g /etc/apt/sources.list
RUN apt clean
RUN apt-get update -y

ARG APP_HOME

VOLUME ${APP_HOME}
WORKDIR ${APP_HOME}

ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]
