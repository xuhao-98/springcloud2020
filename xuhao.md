**Consul:**
   ~~~~
   consul是一套开源的分布式服务发现和配置管理系统，由HashiCrop公司用go语言编写。
   提供了微服务系统中心的服务治理、配置中心、控制总线等功能。这些功能中的每一个都可以根绝需求单独使用，
也可以一起使用以构建全方位的服务网格。
   ~~~~
 下载Consul
 
 官网下载地址： https://www.consul.io/downloads.html
 
 官网下载太慢，我找了一个百度云地址，提取码：247m
 https://pan.baidu.com/s/1nMXRms3_ZPhgqawrcEcsdA
 
 安装Consul
 
 先附上官网的 Consul 安装动画片 o(￣︶￣)o ：
 https://learn.hashicorp.com/consul/getting-started/services
 
 步骤：
 
 下载完成后解压，根据自己实际情况选择路径
 
 解压完成后，在解压路径下的地址栏输入“cmd”，打开命令行窗口。并键入“consul”，若出现一连串英文则表示安装成功，
 启动：
 
 命令consul agent -dev启动，看到Consul agent running! 启动成功
 
 通过以下地址可以访问 Consul 首页
 http://localhost:8500
 