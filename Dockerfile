FROM maven:3.6.0-jdk-8-slim as build

# 指定构建过程中的工作目录
WORKDIR /app

# 将src目录下所有文件，拷贝到工作目录中src目录下
COPY src /app/src

# 将pom.xml文件，拷贝到工作目录下
COPY pom.xml /app

# 执行代码编译命令
RUN mvn -f /app/pom.xml clean package -Dspring.profiles.active=prod

# 选择运行时基础镜像
FROM alpine:3.13

ENV MYSQL_HOST 10.0.64.9
ENV MYSQL_USER_NAME root
ENV MYSQL_PASSWORD @Zcs991023
ENV DATABASE_NAME space
ENV APPLICATION_PORT=80

# 安装依赖包，如需其他依赖包，请到alpine依赖包管理(https://pkgs.alpinelinux.org/packages?name=php8*imagick*&branch=v3.13)查找。
RUN apk add --update --no-cache openjdk8-jre-base \
    && rm -f /var/cache/apk/*

# 指定运行时的工作目录
WORKDIR /app

# 将构建产物jar包拷贝到运行时目录中
COPY --from=build /app/target/space-0.0.1.jar .

# 暴露端口
EXPOSE 80

# 执行启动命令
CMD ["java", "-jar", "/app/space-0.0.1.jar"]
