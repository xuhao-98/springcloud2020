尚硅谷2020最新版SpringCloud(H版&alibaba)框架开发教程全套完整版从入门到精通(大牛讲授spring cloud)
0. 视频地址
视频教程

1. 笔记
doc目录

  工具

  下载MindManager 2020

  激活码

   2019: MP19-777-APE8-1162-BD8E

   2020: MP20-345-DP56-7778-919A
  github下载失败
  gitee导入github仓库

  2. 启动前准备
  2.1 数据库
  执行sql脚本 doc/db2019.sql
  修改数据库的配置
   cloud-provider-payment8001\src\main\resources\application.yml中
  mysql的用户名和密码
  2.2 修改hosts
   找到C:\Windows\System32\drivers\etc路径下的hosts文件,添加

   127.0.0.1 eureka7001.com
   127.0.0.1 eureka7002.com
  2.3 修改zookeeper的地址
   cloud-provider-payment8004\src\main\resources\application.yml

   spring.cloud.zookeeper.connect-string=localhost:2181

  3 软件
   Zookeeper
   consul
   JMeter
   RabbitMq
   Seata-server
   zipkin-server
