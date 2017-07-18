FROM ubuntu

# Installing "add-apt-repository" support
RUN apt-get update && \
	apt-get install -y software-properties-common && \
	apt-get clean && \
    rm -rf /var/lib/apt/lists

# Java
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
    add-apt-repository -y ppa:webupd8team/java && \
    apt-get update && \
    apt-get install -y oracle-java8-installer && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /var/cache/oracle-jdk8-installer

# Git
RUN apt-get update && \
	apt-get install -y git-core && \
    rm -rf /var/lib/apt/lists/*

# Gradle
RUN wget https://services.gradle.org/distributions/gradle-2.14-bin.zip && \
    unzip gradle-2.14-bin.zip && \
      rm -rf gradle-2.14-bin.zip

RUN dpkg-divert --local --rename --add /sbin/initctl
RUN ln -s /bin/true /sbin/initctl
RUN echo "deb http://archive.ubuntu.com/ubuntu precise main universe" > /etc/apt/sources.list
RUN apt-get update
RUN apt-get -y install mysql-client mysql-server
RUN sed -i -e"s/^bind-address\s*=\s*127.0.0.1/bind-address = 0.0.0.0/" /etc/mysql/my.cnf
ADD ./startup.sh /opt/startup.sh

RUN \
  cd /tmp && \
  wget http://download.redis.io/redis-stable.tar.gz && \
  tar xvzf redis-stable.tar.gz && \
  cd redis-stable && \
  make && \
  make install && \
  cp -f src/redis-sentinel /usr/local/bin && \
  mkdir -p /etc/redis && \
  cp -f *.conf /etc/redis && \
  rm -rf /tmp/redis-stable* && \
  sed -i 's/^\(bind .*\)$/# \1/' /etc/redis/redis.conf && \
  sed -i 's/^\(daemonize .*\)$/# \1/' /etc/redis/redis.conf && \
  sed -i 's/^\(dir .*\)$/# \1\ndir \/data/' /etc/redis/redis.conf && \
  sed -i 's/^\(logfile .*\)$/# \1/' /etc/redis/redis.conf

# Define mountable directories.
VOLUME ["/data"]

# Define working directory.
WORKDIR /data

# Repository
RUN rm -rf portfolio-spring-boot/
RUN git clone https://Marianna_Tsapiv:maryana1995@bitbucket.org/Oril-Inc/portfolio-spring-boot.git portfolio-spring-boot

# Environment variables
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV M2_HOME /usr/share/maven

# Expose
EXPOSE 8080

# Define default command.CMD ["/bin/bash", "/opt/startup.sh"]
CMD ["redis-server", "/etc/redis/redis.conf"]

# SpringBoot
CMD mongod --fork --logpath /log/mongodb.log && \
    cd bigmarkin-backend && \
    mvn clean spring-boot:run