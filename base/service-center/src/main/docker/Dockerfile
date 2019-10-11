FROM java
label maintainer="mycsoft@qq.com"
workdir ../../../
#run mvn package -Dmaven.test.skip=true
add target/service-center-0.0.1.jar app.jar
expose 10000
entrypoint ["java","-jar","app.jar"]
