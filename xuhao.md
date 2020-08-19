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



**Feign、OpenFeign	服务接口调用**

**Feign能干什么**：Feign在编写java http客户端变得更容易

前面在使用Ribbon+RestTemplate时，利用RestTemplate对HTTp请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务以来的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一些客户端类来包装这些依赖服务调用。所以Feign在此基础上做了进一步封装，由他来帮助我们定义和实现依赖服务接口的定义。在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置他，即可完成对服务提供方的接口绑定，简化了使用SpringCloud Ribbon时，自动封装服务调用客户端的开发量。

Feign集成了Ribbon：利用Ribbon维护了Payment的服务列表信息，并且通过轮询实现了客户端的负载均衡。而与Ribbon不同的是，通过feign只需要定义服务绑定的接口并且以声明式的方法，优雅而简单的实现了服务调用。

![image-20200730171537192](C:\Users\EDZ\AppData\Roaming\Typora\typora-user-images\image-20200730171537192.png)

**Feign的日志级别**

NONE: 默认的，不显示任何日志

BASIC: 记录请求方法、url、响应状态以及执行时间；

HEADERS：除了BASIC中定义的信息之外，还有请求和相应的头信息；

FULL：除了HEADERS中定义的信息之外，还有请求和相应的正文级元素。



**Hystrix**

分布式系统面临的问题：

服务雪崩：多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务。这就是所谓的“扇出”。如果某个链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，这就是所谓的“雪崩效应”。

对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都会在几秒内饱和。比失败更加糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份列队，线程和其他系统的资源紧张，导致系统发生更多的级联故障。这些都需要对故障和延迟进行隔离和管理，以便单个依赖关系，不能取消整个应用程序或系统。

所以，通常当你发现一个模块下的某个实例失败后，这时候这个模块依然还会接收流量，然后这个有问题的模块还调用了其他的模块，这样就会发生级联故障，或者叫雪崩。

**什么是Hystrix服务熔断？**

Hystrix是一个用于处理分布式系统的延迟和容错的开源库，在分布式系统中，许多依赖不可避免的会调用失败，比如超时、异常等，Hystrix能够保证在一个依赖出现问题的情况下，不会导致整体服务失败，避免级联故障，以提高分布式系统的弹性。

“熔断器”本身就一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控，向调用方返回一个符合预期的、可处理的备选响应，而不是长时间的等待或者抛出调用方无法处理的异常，这样就保证了服务调用方的线程不会被长时间、不必要地占用，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

Hystrix的作用：服务降级、服务熔断、接近实时的的监控。。。

**重要概念**

1.服务降级 fallback

   当服务器繁忙的时候，会提示请稍后再试，不让客户等待并提交一个友好的提示，fallback；  

  发生服务降级的情况：

（1）程序运行异常；

（2）超时；

（3）服务熔断触发微服务降级；

（4）线程池/信号量打满也会导致服务降级。

2.服务熔断 break

类比保险丝一样，达到最大的访问后，直接拒绝访问，拉闸断电。

3.服务限流 flowlimit

秒杀高并发操作，严禁一切蜂窝过来拥挤，大家排队，有序进行



**熔断机制的概述**

熔断机制就是应对雪崩效应的一种微服务链路保护机制。当扇出链路的某个微服务出错不可用或者响应时间太长，会进行服务的降级，进而熔断该节点微服务的调用，快速返回错误的响应信心。

当检测到该节点微服务调用响应正常后，<u>恢复使用改链路</u>。

在SpringCloud框架机制通过Hystrix实现，Hystrix在监控微服务间调用的状况，当失败的调用调用到一定值，缺省是5秒内20次调用失败，就会启动熔断机制。熔断机制的注解是@HystrixCommand



**熔断类型**

1.熔断打开

  	请求不在进行当前服务，内部设置时钟一般为MTTR（平均故障处理时间），当打开时长达到所设时钟则进入半熔断状态。

2.熔断关闭

​	  熔断关闭不会对服务进行熔断。

3.熔断半开

​	  部分请求根据规则调用当前服务，如果请求成功且符合规则则认为当前服务恢复正常，关闭熔断。

**服务断路器在什么情况下起作用**

```java
@HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
        @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),// 是否开启断路器
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),// 请求次数
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"), // 时间窗口期
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"),// 失败率达到多少后跳闸
})
```

涉及到断路器的三个重要参数：快照时间窗、请求总是阈值、错误百分比阈值

​	1.快照时间窗：断路器确定是否打开需要统计一些请求和错误数据，而统计的时间范围就是快照时间窗，默认为最近的10秒。

​	2.请求总数阈值：在快照时间窗内，必须满足请求总数阈值才有资格熔断。默认为20，意味着在10 秒内，如果该hystrix命令的调用次数不足20次，即使所有的请求都超时或其他原因失败，断路器都不会打开。

​	3.错误百分比阈值：当请求总数在快照时间窗内超过了阈值，比如发生了30次调用，如果在这30次中，有15次发生了超时异常，也就是超了50%的错误百分比，在默认设定的50%阈值情况下，这时候就会将断路器打开。

**断路器开启或者关闭的条件：**

​	1.当满足一定的阈值的时候（默认10秒内超过20个请求次数）

​	2.当失败率达到一定的时候（默认10秒内超过50%的请求失败）

​	3.达到以上阈值，断路器将会开启

​	4.当开启的时候，所有请求都不会进行转发

​	5.一段时间之后（默认是5秒），这个时候断路器是半开状态，会让其中一个请求进行转发。如果成功，断路器会关闭，若失败，继续开启。重复4和5



**服务网关--GateWay**

​		GateWay是在Spring生态系统之上构建的API网关服务，基于Spring5，SpringBoot 2和Project Reactor等技术。GetWay在提供一种简单而有效的方式对API进行路由，以及提供一些强大的过滤功能，例如：熔断、限流、重试等。

​		springcloud GateWay，使用的是WebFlux中的reactor-netty响应式编程组件，底层使用了Netty通讯框架



**GateWay的特性：**

​	1、基于SpringFramework 5，Project Reactor 和Spring boot 2.0进行构建；

​	2、动态路由：能够匹配任何请求属性；

​	3、可以对路由指定Predicate（断言）和Filter（过滤器）；

​	4、集成Hystrix的断路器功能；

​	5、集成Spring Cloud服务发现功能；

​	6、易于编写的Predicate和Filter；

​	7、请求限流功能；

​	8、支持路径重写。



**GateWay三大核心概念**

​	1.Route 路由：

​	路由是构建网关的基本模块，由ID、目标URI、一系列的断言和过滤器组成，如果断言为true则匹配该路由；

​	2.Predicate 断言：

​	开发人员可以匹配HTTP请求中的所有内容（例如请求头和参数），如果请求与断言相匹配则进行该路由；

​	3.Filter 过滤：

​	指的是Spring框架中GateWayFilter的实例，使用过滤器，可以在请求被路由前或者之后对请求进行修改。

**网关路由的两种配置方式**

​	1.在配置文件yml中配置；

​	2.代码中注入RouteLocator的Bean

```html
@Bean
public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder) {
    RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
    routes.route("path_route_atguigu",
            r -> r.path("/guonei")
                    .uri("http://news.baidu.com/guonei")).build();
    return routes.build();
}
```

**动态路由配置**

```yaml
spring:
  application:
    name: cloud-gateway
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true #开启从注册中心动态创建路由的功能，利用微服务名进行路由
      routes:
        - id: payment_routh #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/get/**         # 断言，路径相匹配的进行路由

        - id: payment_routh2 #payment_route    #路由的ID，没有固定规则但要求唯一，建议配合服务名
          #uri: http://localhost:8001          #匹配后提供服务的路由地址
          uri: lb://cloud-payment-service #匹配后提供服务的路由地址
          predicates:
            - Path=/payment/lb/**         # 断言，路径相匹配的进行路由
```

**自定义全局过滤器GlobalFilter**

```java
@Component
@Slf4j
public class GateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*****come in GateWayFilter:  *****"+new Date());
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        if (uname == null){
            log.info("非法用户！");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
```

**服务配置Config**

**什么是SpringCloud Config？**

​		SpringCloudConfig为微服务架构中的微服务提供集中化的外部配置支持，配置服务器为各个不同微服务应用的所有环境提供了一个中心化的外部配置。

**SpringCloud Config能做些什么**

​		1、集中管理配置文件；

​		2、不同环境不同配置，动态化的配置更新，分环境部署，比如：dev/test/beta等；

​		3、运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务向配置中心统一拉取配置自己的信息；

​		4、当配置发生变动时，服务不再需要重启即可感知配置的变化并且应用新的配置；

​		5、将配置信息以REST接口的形式暴露。



**Bus消息总线**

​		Bus消息总线支持两种消息代理:RabbitMQ和Kafka

​	Spring Cloud Bus 配合Spring Cloud Config使用可以实现配置的动态刷新.

​	SpringCloudBus是用来将分布式系统的节点与轻量级消息系统链接起来的框架,它整合了java的事件处理机制和消息中间件的功能.

**什么是总线?**

​		在微服务架构的系统中,通常会使用轻量级的消息代理来构建一个共用的消息主题,并让系统中所有的微服务实例都连接上来.由于该主题中产生的消息会被所有实例监听和消费,所以称他为消息总线.在总线上的各个实例,都可以方便地广播一些需要让其他连接在该主题上的实例都知道的消息.

**基本原理**

​		ConfigClient实例都监听MQ同一个topic.当一个服务刷新数据时,它会把这个信息放入到Topic中,同一个Topic的服务就能得到通知,然后去更新自身的配置.





**SpringCloud Alibaba** 

​		2018.10.31 Spring Cloud Alibaba正式入住了Springcloud的官方孵化器,并在mavem中发布了第一个版本.

​		SpringCloudAlibaba是由阿里巴巴的开源组件和云产品组成的,这个项目的目的就是为了让大家熟知spring框架,其优秀的设计模式和抽象理念,以给使用阿里巴巴产品的java开发者带来使用SpringBoot和springcloud的更多便利.

**SpringCloud Alibaba能干什么**

1.服务限流降级：默认支持servlet、feign、RestTempalte、ubbo和RocketMQ限流降级工能的接入，可以再运行时通过控制台实时修改限流降级规则，还支持查看限流降级Metrics监控。

2.服务注册与发现：适配Spring Cloud服务注册与发现标准，默认集成Ribbon的支持。

3.分布式配置管理：支持分布式系统中的外部化配置，配置更改时自动刷新。

4.消息驱动能力：基于SpringCloudStream为微服务应用构建消息驱动能力。

5.阿里与对象存储

6.分布式任务调度

### 组件

- **Sentinel**：把流量作为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。
- **Nacos**：一个更易于构建云原生应用的动态服务发现、配置管理和服务管理平台。
- **RocketMQ**：一款开源的分布式消息系统，基于高可用分布式集群技术，提供低延时的、高可靠的消息发布与订阅服务。
- **Alibaba Cloud ACM**：一款在分布式架构环境中对应用配置进行集中管理和推送的应用配置中心产品。
- **Alibaba Cloud OSS**: 阿里云对象存储服务（Object Storage Service，简称 OSS），是阿里云提供的海量、安全、低成本、高可靠的云存储服务。您可以在任何应用、任何时间、任何地点存储和访问任意类型的数据。
- **Alibaba Cloud SchedulerX**: 阿里中间件团队开发的一款分布式任务调度产品，提供秒级、精准、高可靠、高可用的定时（基于 Cron 表达式）任务调度服务。

**NACOS**

Nacos = Eureka+Config+Bus

#### 下载并安装

```html
# 下载源码
git clone https://github.com/alibaba/nacos.git

# 安装到本地仓库
cd nacos/
mvn -Prelease-nacos clean install -U
```

### 启动服务

```html
cd distribution/target/nacos-server-0.7.0/nacos/bin

# Linux
./startup.sh -m standalone

# Windows
startup.cmd
```

### 访问服务

打开浏览器访问：http://127.0.0.1:8848/nacos

**Nacos集群配置**

​	默认Nacos使用嵌套数据库实现数据的存储,所以,如果启动多个默认配置下的Nacos节点,数据存储是存在一致性问题的.为了解决这个问题,Nacos采用了集中式存储的方式来支持集群化部署,目前只支持Mysql的存储.

**Nacos支持三种部署方式**

​	单机模式-用于测试和单机试用.

​	集群模式-用于生产环境,确保高可用.

​	多集群模式-用于垛数据中心场景.

**Nacos持久化配置**

​		nacos默认自带的嵌入式数据库derby



## 使用熔断器防止服务雪崩

### 概述

在微服务架构中，根据业务来拆分成一个个的服务，服务与服务之间可以通过 `RPC` 相互调用，在 Spring Cloud 中可以用 `RestTemplate + LoadBalanceClient` 和 `Feign` 来调用。为了保证其高可用，单个服务通常会集群部署。由于网络原因或者自身的原因，服务并不能保证 100% 可用，如果单个服务出现问题，调用这个服务就会出现线程阻塞，此时若有大量的请求涌入，`Servlet` 容器的线程资源会被消耗完毕，导致服务瘫痪。服务与服务之间的依赖性，故障会传播，会对整个微服务系统造成灾难性的严重后果，这就是服务故障的 **“雪崩”** 效应。

为了解决这个问题，业界提出了熔断器模型。

阿里巴巴开源了 Sentinel 组件，实现了熔断器模式，Spring Cloud 对这一组件进行了整合。在微服务架构中，一个请求需要调用多个服务是非常常见的，较底层的服务如果出现故障，会导致连锁故障。当对特定的服务的调用的不可用达到一个阀值熔断器将会被打开。

熔断器打开后，为了避免连锁故障，通过 `fallback` 方法可以直接返回一个固定值。

### Sentinel

随着微服务的流行，服务和服务之间的稳定性变得越来越重要。 Sentinel 以流量为切入点，从流量控制、熔断降级、系统负载保护等多个维度保护服务的稳定性。

### Sentinel 的特征

能够监控保护我的微服务

- **丰富的应用场景：** Sentinel 承接了阿里巴巴近 10 年的 **双十一大促流量** 的核心场景，例如秒杀（即突发流量控制在系统容量可以承受的范围）、消息削峰填谷、实时熔断下游不可用应用等。

- **完备的实时监控：** Sentinel 同时提供实时的监控功能。您可以在控制台中看到接入应用的单台机器秒级数据，甚至 500 台以下规模的集群的汇总运行情况。

- **广泛的开源生态：** Sentinel 提供开箱即用的与其它开源框架/库的整合模块，例如与 Spring Cloud、Dubbo、gRPC 的整合。您只需要引入相应的依赖并进行简单的配置即可快速地接入 Sentinel。

- **完善的 SPI 扩展点：** Sentinel 提供简单易用、完善的 SPI 扩展点。您可以通过实现扩展点，快速的定制逻辑。例如定制规则管理、适配数据源等。

  

**流控规则**

流控模式:

1.QPS默认(直接)->失败 (拒敌于城外)

​    线程数:(关门打狗)

2.关联:当关联数量达到一定阈值时就限流自己

3.链路

​    流控效果:

1.直接失败

2.预热

3.排队等待  

​    **服务降级**

1.RT(平均响应时间,秒级)

​	平均响应时间:超出阈值且在时间窗口内通过的请求>=5,两个条件同时满足后触发降级;

​	窗口期过后关闭断路器;

​	RT最大4900

​	当 1s 内持续进入 N 个请求，对应时刻的平均响应时间（秒级）均超过阈值（`count`，以 ms 为单位），那么在接下的时间窗口（`DegradeRule` 中的 `timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地熔断（抛出 `DegradeException`）。注意 Sentinel 默认统计的 RT 上限是 4900 ms，**超出此阈值的都会算作 4900 ms**，若需要变更此上限可以通过启动配置项 `-Dcsp.sentinel.statistic.max.rt=xxx` 来配置。

2.异常比例

QPS>=5且异常比例超过阈值,触发降级;时间窗口结束后,关机降级

​	当资源的每秒请求量 >= N（可配置），并且每秒异常总数占通过量的比值超过阈值（`DegradeRule` 中的 `count`）之后，资源进入降级状态，即在接下的时间窗口（`DegradeRule` 中的 `timeWindow`，以 s 为单位）之内，对这个方法的调用都会自动地返回。异常比率的阈值范围是 `[0.0, 1.0]`，代表 0% - 100%。

3.异常数(分钟级)

 异常数超过阈值时,触发降级,时间窗口结束后,关闭降级

​	当资源近 1 分钟的异常数目超过阈值之后会进行熔断。注意由于统计时间窗口是分钟级别的，若 `timeWindow` 小于 60s，则结束熔断状态后仍可能再进入熔断状态。

**热点key限流**

 该模式只支持QPS!

​	何为热点？热点即经常访问的数据。很多时候我们希望统计某个热点数据中访问频次最高的 Top K 数据，并对其访问进行限制。比如：

- 商品 ID 为参数，统计一段时间内最常购买的商品 ID 并进行限制
- 用户 ID 为参数，针对一段时间内频繁访问的用户 ID 进行限制

热点参数限流会统计传入参数中的热点参数，并根据配置的限流阈值与模式，对包含热点参数的资源调用进行限流。热点参数限流可以看做是一种特殊的流量控制，仅对包含热点参数的资源调用生效

@SentinelResource

```java
@SentinelResource(value = "testHotKey",blockHandler = "deal_testHotKey")
```

deal_testHotKey 为备用方法

# 系统自适应限流

Sentinel 系统自适应限流从整体维度对应用入口流量进行控制，结合应用的 Load、CPU 使用率、总体平均 RT、入口 QPS 和并发线程数等几个维度的监控指标，通过自适应的流控策略，让系统的入口流量和系统的负载达到一个平衡，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

## 背景

在开始之前，我们先了解一下系统保护的目的：

- 保证系统不被拖垮
- 在系统稳定的前提下，保持系统的吞吐量

长期以来，系统保护的思路是根据硬指标，即系统的负载 (load1) 来做系统过载保护。当系统负载高于某个阈值，就禁止或者减少流量的进入；当 load 开始好转，则恢复流量的进入。这个思路给我们带来了不可避免的两个问题：

- load 是一个“结果”，如果根据 load 的情况来调节流量的通过率，那么就始终有延迟性。也就意味着通过率的任何调整，都会过一段时间才能看到效果。当前通过率是使 load 恶化的一个动作，那么也至少要过 1 秒之后才能观测到；同理，如果当前通过率调整是让 load 好转的一个动作，也需要 1 秒之后才能继续调整，这样就浪费了系统的处理能力。所以我们看到的曲线，总是会有抖动。
- 恢复慢。想象一下这样的一个场景（真实），出现了这样一个问题，下游应用不可靠，导致应用 RT 很高，从而 load 到了一个很高的点。过了一段时间之后下游应用恢复了，应用 RT 也相应减少。这个时候，其实应该大幅度增大流量的通过率；但是由于这个时候 load 仍然很高，通过率的恢复仍然不高。

[TCP BBR](https://en.wikipedia.org/wiki/TCP_congestion_control#TCP_BBR) 的思想给了我们一个很大的启发。我们应该根据系统能够处理的请求，和允许进来的请求，来做平衡，而不是根据一个间接的指标（系统 load）来做限流。最终我们追求的目标是 **在系统不被拖垮的情况下，提高系统的吞吐率，而不是 load 一定要到低于某个阈值**。如果我们还是按照固有的思维，超过特定的 load 就禁止流量进入，系统 load 恢复就放开流量，这样做的结果是无论我们怎么调参数，调比例，都是按照果来调节因，都无法取得良好的效果。

Sentinel 在系统自适应保护的做法是，用 load1 作为启动自适应保护的因子，而允许通过的流量由处理请求的能力，即请求的响应时间以及当前系统正在处理的请求速率来决定。

## 系统规则

系统保护规则是从应用级别的入口流量进行控制，从单台机器的 load、CPU 使用率、平均 RT、入口 QPS 和并发线程数等几个维度监控应用指标，让系统尽可能跑在最大吞吐量的同时保证系统整体的稳定性。

系统保护规则是应用整体维度的，而不是资源维度的，并且**仅对入口流量生效**。入口流量指的是进入应用的流量（`EntryType.IN`），比如 Web 服务或 Dubbo 服务端接收的请求，都属于入口流量。

系统规则支持以下的模式：

- **Load 自适应**（仅对 Linux/Unix-like 机器生效）：系统的 load1 作为启发指标，进行自适应系统保护。当系统 load1 超过设定的启发值，且系统当前的并发线程数超过估算的系统容量时才会触发系统保护（BBR 阶段）。系统容量由系统的 `maxQps * minRt` 估算得出。设定参考值一般是 `CPU cores * 2.5`。
- **CPU usage**（1.5.0+ 版本）：当系统 CPU 使用率超过阈值即触发系统保护（取值范围 0.0-1.0），比较灵敏。
- **平均 RT**：当单台机器上所有入口流量的平均 RT 达到阈值即触发系统保护，单位是毫秒。
- **并发线程数**：当单台机器上所有入口流量的并发线程数达到阈值即触发系统保护。
- **入口 QPS**：当单台机器上所有入口流量的 QPS 达到阈值即触发系统保护。



### Feign 中使用 Sentinel

如果要在您的项目中引入 Sentinel，使用 group ID 为 `org.springframework.cloud` 和 artifact ID 为 `spring-cloud-starter-alibaba-sentinel` 的 starter。

```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```

Sentinel 适配了 Feign 组件。但默认是关闭的。需要在配置文件中配置打开它，在配置文件增加以下代码：

```
feign:
  sentinel:
    enabled: true
```

#### 在 Service 中增加 fallback 指定类

```
package cn.mirrorming.spring.cloud.alibaba.consumer.feign.service;

import cn.mirrorming.spring.cloud.alibaba.consumer.feign.service.fallback.ProviderServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author mirror
 */
@FeignClient(value = "provider", fallback = ProviderServiceFallback.class)
public interface ProviderService {

    @GetMapping(value = "/echo/{message}")
    String echo(@PathVariable String message);
}
```

#### 创建熔断器类并实现对应的 Feign 接口

```
package cn.mirrorming.spring.cloud.alibaba.consumer.feign.service.fallback;

import cn.mirrorming.spring.cloud.alibaba.consumer.feign.service.ProviderService;
import org.springframework.stereotype.Component;

@Component
public class ProviderServiceFallback implements ProviderService {
    @Override
    public String echo(String message) {
        return "sentinel fallback";
    }
}
```

### 测试熔断器

此时我们关闭服务提供者，再次请求 http://localhost:9092/echo/ 浏览器会显示：

```
sentinel fallback
```