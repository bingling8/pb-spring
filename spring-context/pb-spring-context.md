[TOC]

# Spring

![](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/images/spring-overview.png)

## spring 核心容器

核心容器由 spring-core，spring-beans，spring-context，spring-context-support和spring-expression（SpEL，Spring 表达式语言，Spring Expression Language）等模块组成，它们的细节如下：

*   spring-core 模块提供了框架的基本组成部分，包括 IoC 和依赖注入功能。

*   spring-beans 模块提供 BeanFactory，工厂模式的微妙实现，它移除了编码式单例的需要，并且可以把配置和依赖从实际编码逻辑中解耦。&#x20;

*   context 模块建立在由 core和 beans 模块的基础上建立起来的，它以一种类似于 JNDI 注册的方式访问对象。Context 模块继承自 Bean 模块，并且添加了国际化（比如，使用资源束）、事件传播、资源加载和透明地创建上下文（比如，通过 Servelet 容器）等功能。Context 模块也支持 Java EE 的功能，比如 EJB、JMX 和远程调用等。ApplicationContext 接口是 Context 模块的焦点。spring-context-support 提供了对第三方集成到 Spring 上下文的支持，比如缓存（EhCache, Guava, JCache）、邮件（JavaMail）、调度（CommonJ, Quartz）、模板引擎（FreeMarker, JasperReports, Velocity）等。&#x20;

*   spring-expression 模块提供了强大的表达式语言，用于在运行时查询和操作对象图。它是 JSP2.1 规范中定义的统一表达式语言的扩展，支持 set 和 get 属性值、属性赋值、方法调用、访问数组集合及索引的内容、逻辑算术运算、命名变量、通过名字从 Spring IoC 容器检索对象，还支持列表的投影、选择以及聚合等。&#x20;

*   它们的完整依赖关系如下图所示：

## &#x20;数据访问/集成 （Data Access/Integeration）

数据访问/集成层包括 JDBC，ORM，OXM，JMS 和事务处理模块，它们的细节如下：

（注：JDBC=Java Data Base Connectivity，ORM=Object Relational Mapping，OXM=Object XML Mapping，JMS=Java Message Service）

*   JDBC 模块提供了 JDBC 抽象层，它消除了冗长的 JDBC 编码和对数据库供应商特定错误代码的解析。
*   ORM 模块提供了对流行的对象关系映射 API 的集成，包括 JPA、JDO 、 Hibernate、Mybatis 等。通过此模块可以让这些 ORM 框架和 spring的其它功能整合，比如前面提及的事务管理。&#x20;
*   OXM 模块提供了对 OXM 实现的支持，比如 JAXB、Castor、XML Beans、JiBX、XStream 等。&#x20;
*   JMS 模块包含生产（produce）和消费（consume）消息的功能。从 Spring 4.1 开始，集成了 spring-messaging 模块。&#x20;
*   事务模块为实现特殊接口类及所有的 POJO 支持编程式和声明式事务管理。（注：编程式事务需要自己写 beginTransaction()、commit()、rollback() 等事务管理方法，声明式事务是通过注解或配置由 spring 自动处理，编程式事务力度更细）&#x20;

## Web&#x20;

Web 层由 Web，Servlet，Web-Socket 和 Web-Portlet 组成，它们的细节如下：

*   Web 模块提供面向 web 的基本功能和面向 web 的应用上下文，比如多部分（multipart）文件上传功能、使用 Servlet 监听器初始化 IoC 容器等。它还包括 HTTP 客户端以及 Spring 远程调用中与 web 相关的部分。
*   Servlet 模块为 web 应用提供了模型视图控制（MVC）和 REST Web服务的实现。Spring 的 MVC 框架可以使领域模型代码和 web 表单完全地分离，且可以与 Spring 框架的其它所有功能进行集成。
*   Web-Socket 模块为 WebSocket-based 提供了支持，而且在 web 应用程序中提供了客户端和服务器端之间通信的两种方式。&#x20;
*   Web-Portlet 模块提供了用于 Portlet 环境的 MVC 实现，并反映了 spring-webmvc 模块的功能。

## AOP

AOP 模块提供了面向方面（切面）的编程实现，允许你定义方法拦截器和切入点对代码进行干净地解耦，从而使实现功能的代码彻底的解耦出来。能够将那些与业务无关，却为业务模块所共同调用的逻辑或责任（例如事务处理、日志管理、权限控制等）封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可拓展性和可维护性。

## Aspects

Aspects 模块提供了与 AspectJ 的集成，这是一个功能强大且成熟的面向切面编程（AOP）框架。

## Instrumentation

Instrumentation 模块在一定的应用服务器中提供了类 instrumentation 的支持和类加载器的实现。

## Messaging

Messaging 模块为 STOMP 提供了支持作为在应用程序中 WebSocket 子协议的使用。它也支持一个注解编程模型，它是为了选路和处理来自 WebSocket 客户端的 STOMP 信息。

## Test

测试模块支持对具有 JUnit 或 TestNG 框架的 Spring 组件的测试。

## 手写一个spring

自己写一个pb-spring, pb-spring Framework设计目标如下：

*   context模块：实现ApplicationContext容器与Bean的管理；
*   aop模块：实现AOP功能；
*   jdbc模块：实现JdbcTemplate，以及声明式事务管理；
*   web模块：实现Web MVC和REST API；
*   boot模块：实现一个简化版的“Spring Boot”，用于打包运行。

# 1 context模块

context就是一个spring IOC容器。

## 1.1 IoC

IoC(Inversion of Control)，常翻译为“控制反转”，别名“依赖注入”(Dependency Injection).

IoC的理念就是让别人为你服务，也就是让IoC service Provider 给你服务，如下图所示

<img src="https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/35caf0db3724436a8b285b409393ae8b~tplv-k3u1fbpfcp-image.image#?w=391&h=216&s=5581&e=svg&a=1&b=fcfcfc" alt="IOC-Service-Provider.svg" width="50%" />

作为被注入对象，要想让IoC Servicer Provider为其提供服务，并将所需要的被依赖对象“送过来”，是需要通过某种方式通知对方。有三种方式，即**构造方法注入（constructor injection）、setter方法注入（setter injction）以及接口注入（interface injection）**。

### 1.1.1 构造方法注入

在构造方法里"通知"IoC Service Provder

```java
public InjectedObject(Dependent1 dependent1, Dependent2 dependent2)
{
    this.dependent1=dependent1;
    this.dependent2=dependent2;
}

```

### 1.1.2 setter方法注入

在setter方法里通知IoC Service Provder

```java
private Dependent1 dependent1;
private Dependent2 dependent2;

public Dependent1 getDependent1(){
    return dependent1;
}

public void setDependent1(Dependent1 dependent1){
    this.dependent1 = dependent1
}

public Dependent2 getDependent2(){
    return dependent2;
}

public void setDependent2(Dependent2 dependent2){
    this.dependent2 = dependent2
}

```

### 1.1.3 接口注入

InjectedObject为了让IoC Service Provder为其注入所依赖的Dependent1，首先需要实现一个接口DependentCallable，这个接口会声明一个方法，方法的参数就是Dependent1类型，IoC Service Provder通过这个接口方法将依赖对象注入到对象InjectedObject中。

接口注入使用较少。

## 1.2 IoC Service Provider

IoC Service Provider有两个职责：**业务对象构建**和**业务对象间的依赖绑定** IoC Service Provider实现这两个职责有三种方式：**直接编码、配置文件以及注解**

spring IOC容器是一个超级IoC Service Provider。

### 1.2.1 编码

### 1.2.2 配置文件

### 1.2.3 注解

#### 业务对象构建

```java
@Configuration
@ComponentScan("com.imooc.spring.demo.bean")//项目的包名
public class SpringBeanConfiguration {
}

```

```java
@Component
public class FirstBean {

}
```

#### **业务对象间的依赖绑定**

构造方法注入

```java
@Component
public class SecondBean {
    private FirstBean firstBean;

    @Autowired
    public SecondBean(FirstBean firstBean) {
        this.firstBean = firstBean;
    }
    
    @Override
    public String toString() {
        return "SecondBean{firstBean=" + firstBean + '}';
    }
}
```

Setter注入

```java
@Component
public class ThirdBean {
    private FirstBean firstBean;

    @Autowired
    public void setFirstBean(FirstBean firstBean) {
        this.firstBean = firstBean;
    }

    @Override
    public String toString() {
        return "ThirdBean{firstBean=" + firstBean + '}';
    }
}

```

属性注入

```java
@Component
public class FourthBean {
    @Autowired
    private FirstBean firstBean;

    @Override
    public String toString() {
        return "FourthBean{firstBean=" + firstBean + '}';
    }
}

```

## 1.3具体实现

### 1.3.1 扫描

```java
@ComponentScan("com.imooc.spring.demo.bean")
```

### 1.3.2 Bean生命周期

![1671109620298.jpg](https://camo.githubusercontent.com/104fb5464a3d4e41aa386a8a2f9a3a1e66c0e728422319d0753ae87a63fe6003/68747470733a2f2f7563632e616c6963646e2e636f6d2f7069632f646576656c6f7065722d65636f6c6f67792f36306336643939343636323034353363626533363466613266373964326431312e6a70673f782d6f73732d70726f636573733d696d616765253246726573697a65253243775f31343030253246666f726d617425324377656270 "1671109620298.jpg")

#### 依赖注入

```java
//属性依赖注入
            for (java.lang.reflect.Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    Object bean = getBean(field.getName());
                    field.setAccessible(true);
                    field.set(instance,bean);
                }
            }
```

#### Aware回调

Aware 接口是一个具有标识作用的超级接口，指示 bean 是具有被 Spring 容器通知的能力，通知的方式是采用回调的方式。Aware 接口是一个空接口，具体的实现由各个子接口决定，且该接口通常只**包含一个单个参数并且返回值为void的方法**。可以理解就是 set 方法。该方法的命名方式为 **set + 去掉接口名中的 Aware 后缀**，即 XxxAware 接口，则方法定义为 setXxx()，例如 `BeanNameAware`（`setBeanName`），`ApplicationContextAware`（`setApplicationContext`）。**注意，仅实现Aware接口，不会提供任何默认功能，需要明确的指定实现哪个子接口**。

通俗的来说，`Aware`翻译过来的意思是有感知的,察觉的，如果类上实现了该接口，表明对什么有感知，比如`BeanNameAware`, 表示知道了自己的Bean Name。

#### 初始化机制

`InitializingBean`只有一个方法`afterPropertiesSet`，见名知意，这个方法是在bean的属性值被设置以后执行。 Spring给我们提供了这么一个扩展点，可以用来做很多的事情, 比如可以修改默认设置的属性，添加补充额外的属性值或者针对关键属性做一个校验。

Spring本身也有很多的Bean实现了`InitializingBean`接口, 比如Spring MVC中的`RequestMappingHandlerMapping`就实现了`InitializingBean`接口，在`afterPropertiesSet`中完成了一些初始化工作，比如url和controller方法的映射。

#### BeanPostProcessor机制

在bean初始化前或初始化后做特定的业务逻辑

###
