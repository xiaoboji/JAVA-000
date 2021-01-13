# ActiveMQ简单实例

## 一、搭建ActiveMQ
### win10+vmware+centos7
- 获取ActiveMq版本
```bash
wget http://mirror.bit.edu.cn/apache//activemq/5.16.0/apache-activemq-5.16.0-bin.tar.gz
tar -zxvf apache-activemq-5.16.0-bin.tar.gz
```
- 安装JDK

这里忽略JDK的安装（大概内容是：解压JDK8，并配JAVA_HOME，在/etc/profile中添加全局环境变量）

- 启动ActiveMQ
```bash
./activemq start
```
- 验证测试

在win10上无法访问 虚拟机ip:8161,解决方法

关闭linux防火墙
```bash
systemctl stop firewalld.service
```

修改conf/jetty.xml文件，把127.0.0.1修改成0.0.0.0，然后重启，解决了访问问题,具体原因待分析
```bash
<bean id="jettyPort" class="org.apache.activemq.web.WebConsolePort" init-method="start">
         <!-- the default port number for the web console -->
    <property name="host" value="127.0.0.1"/>
    <property name="port" value="8161"/>
</bean>
```
主机可以访问 虚拟机ip:8161,账户名/密码, admin/admin
### windows安装 
- 下载
- 解压
- 启动

## 二、基于JMS，分别实现对于queue和topic的消息生产和消费
