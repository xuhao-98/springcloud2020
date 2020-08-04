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