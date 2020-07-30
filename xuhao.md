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

 **Eureka、zookeeper、consul的区别**
   组件名      语言      CAP     服务健康检查     对外接口暴露     SpringCloud集成
   Eureka     java      Ap         可配支持         HTTP            已经集成
   Consul      go       CP          支持          HTTP/DNS          已经集成
   Zookeeper  java      CP          支持            HTTP            已经集成      

   CAP：
   C：Consistency（强一致性） 
   A: Availability(可用性)
   P：Partition tolerance（分区容错性）

**Ribbon入门**

SpringcloudRibbon是基于实现的一套客户端 ：负载均衡的工具。

简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法和服务调用。Ribbon客服端组件提供一系列的完善的配置，如连接超时、重试等。简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon都会自动的帮助你基于某种规则去连接这些机器，因此我们很容易去使用Ribbon实现的负载均衡算法。

**LB负载均衡是什么（Load Balance）**

简单的说就是将用户的请求平摊的分配到朵儿度武器上，从而达到系统的HA（高可用）。

常见的负载均衡有软件Nginx，LVS，硬件F5等。

**Ribbon本地负载均衡客户端VSNginx负载均衡的区别**

Nginx是服务器的负载均衡，客服端的所有请求都会交给nginx，然后由nginx实现转发请求，所以说负载均衡是由服务端实现的。好比说Nginx是一个医院那么Robbon就像医院里面的各个门诊的医生，任由Nginx发配任务。

Ribbon本地负载均衡，在调用微服务接口的时候，会在注册中心上获取注册信息服务列表之后缓存到JVM本地，从而在本地实现RPC远程调用技术。

**集中式LB**

即在服务的消费方和提供方之间使用独立的LB设施，有该设施负责把访问请求通过某种策略转发至服务的提供方。

**进程内LB**

将LB逻辑成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选择出一个合适的服务器。RIbbon就属于进程LB，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务器提供方的地址。

所谓的Ribbon：

就是负载均衡+RestTemplate调用

**IRule**

根据特定算法中从服务列表选取一个要访问的服务。

1.轮询：com.netflix.loadbalancer.RoundRobinRule

2.随机:com.netflix.loadbalancer.RandomRule

3.先按照RoudRobinRule的策略获取服务，如果获取失败则在指定时间内进行重新获取

com.netflix.loadbalancer.RetryRule

4.对RoundRobinRule的扩展，响应速度越快的实例选择权重越大，越容易被选择

WeightResponseTimeRule

5.会先过滤由于多次访问出现故障而处于断路跳闸状态的服务，然后选择一个并发量小的服务

BestAvailableRule

6.先过滤掉故障实例，在选择并发较小的实例

AvailabilityFileringRule

7.ZoneAvoidanceRule：默认规则，复合判断server所在区域的性能和server的可用性选择服务器。 