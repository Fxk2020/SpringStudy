# Spring

## 1.1简介

**Spring框架**是 [Java](https://zh.wikipedia.org/wiki/Java) 平台的一个[开源](https://zh.wikipedia.org/wiki/开放源代码)的全栈（[full-stack](https://zh.wikipedia.org/w/index.php?title=全栈&action=edit&redlink=1)）[应用程序框架](https://zh.wikipedia.org/wiki/软件框架)和[控制反转](https://zh.wikipedia.org/wiki/控制反转)容器实现，一般被直接称为 Spring。该框架的一些核心功能理论上可用于任何 Java 应用，但 Spring 还为基于[Java企业版](https://zh.wikipedia.org/wiki/Jakarta_EE)平台构建的 Web 应用提供了大量的拓展支持。Spring 没有直接实现任何的编程模型，但它已经在 Java 社区中广为流行，基本上完全代替了[企业级JavaBeans](https://zh.wikipedia.org/wiki/EJB)（EJB）模型。

Spring框架以 [Apache License 2.0](https://zh.wikipedia.org/wiki/Apache_License_2.0) 开源许可协议的形式发布，该框架最初由 Rod Johnson 以及 Juergen Hoeller 等人开发。

#### 特点

- 强大的、基于 [JavaBeans](https://zh.wikipedia.org/wiki/JavaBeans) 的、采用[控制反转](https://zh.wikipedia.org/wiki/控制反转)（Inversion of Control，IoC）原则的 配置管理，使得应用程序的组建更加简易快捷。
- 一个可用于 [Java EE](https://zh.wikipedia.org/wiki/Java_EE) 等运行环境的核心 [Bean](https://zh.wikipedia.org/w/index.php?title=Enterprise_Java_Beans&action=edit&redlink=1)[工厂](https://zh.wikipedia.org/w/index.php?title=虚拟工厂模式&action=edit&redlink=1)。
- 数据库[事务](https://zh.wikipedia.org/wiki/事务处理)的一般化抽象层，允许声明式（Declarative）事务管理器，简化事务的划分使之与底层无关。
- 面向切面编程（Aspect-Oriented Programming, AOP） 就是将那些与业务无关，却为业务模块所共同调用的逻辑或责任分开封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。
- **Spring Data**实现了对数据访问接口的统一，支持多种数据库访问框架或组件（如：JDBC、[Hibernate](https://zh.wikipedia.org/wiki/Hibernate)、[MyBatis](https://zh.wikipedia.org/wiki/MyBatis)（[iBatis](https://zh.wikipedia.org/wiki/IBATIS)））作为最终数据访问的实现。
- **Spring MVC** 实现了基于 [MVC](https://zh.wikipedia.org/wiki/MVC) 设计方法的实现，结合基于[Java注解](https://zh.wikipedia.org/wiki/Java注解)的配置，允许开发者开发出低代码侵入的Web应用项目，并简便地实现大部分Web功能（包括请求参数注入、文件上传控制等）。

#### 优点

spring是一个开源的免费的框架。

spring是一个轻量级的、非入侵时的框架。

![image-20210805221208872](/Users/yuanbao/Library/Application Support/typora-user-images/image-20210805221208872.png)

### 弊端

配置繁琐、违背了初心。

框架图

表现层

业务层

持久层

![](https://atts.w3cschool.cn/attachments/image/wk/wkspring/arch1.png)

核心包括：**spring-core**，spring-beans，spring-context，spring-context-support和spring-expression（SpEL，Spring 表达式语言，Spring Expression Language）等模块组成

关系如下：

![](https://atts.w3cschool.cn/attachments/image/20181023/1540290875453691.png)

## 1.2 引入

ssm框架：SpringMvc+Spring+Mybatis

springboot是一个快速开发的脚手架、springcloud是基于springboot实现的。

[官方文档](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#spring-core)

maven中引入依赖：

```java
<dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring</artifactId>
            <version>5.2.15.RELEASE</version>
            <type>pom</type>
        </dependency>

        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.15.RELEASE</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.15.RELEASE</version>
        </dependency>
    </dependencies>
```

## 1.3 IOC理论推导

![在这里插入图片描述](https://img-blog.csdnimg.cn/66b81422a7f4478087b1c82b0d478f8b.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pvZXlfcm8=,size_16,color_FFFFFF,t_70)

#### 1.3.1未使用IOC的代码

业务层（service）调用逻辑层（dao）

```java
//逻辑层

public interface UserDao {

    void getUser();

}

public class UserDaoImpl implements UserDao{
    public void getUser() {
        System.out.println("逻辑实现代码。");
    }
}


//业务层
public interface UserService {

    void getUser();

}
public class UserServiceImpl implements UserService{

//    UserDao userDao = new UserDaoImpl();
    UserDao userDao = new UserDaoMysqlImpl();//逻辑层代码的修改导致业务层代码修改

    public void getUser() {
        userDao.getUser();
    }
}
```

#### 1.3.2 使用IOC之后的代码

**！！！依赖注入--控制反转IOC**

```java
//业务层
public class UserServiceImpl implements UserService{

//    UserDao userDao = new UserDaoImpl();
//    UserDao userDao = new UserDaoMysqlImpl();//逻辑层代码的修改导致业务层代码修改

    UserDao userDao;

    //通过set方法动态赋值，注入不同的值，可以适应用户不同的请求，不需要修改源代码
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
```

**主要改变：程序不再具有主动性，而是变成了被动的接收对象！！！--这就是控制反转，由主动的创建对象改为被动接收对象。程序员不必在管理对象的创建了**

**IOC是Spring框架的核心内容**

#### 1.3.3 配置元数据

xml文件--放在resources目录下。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="..." class="...">  
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <bean id="..." class="...">
        <!-- collaborators and configuration for this bean go here -->
    </bean>

    <!-- more bean definitions go here -->

</beans>
```

| 1    | 该`id`属性是一个字符串，用于标识单个 bean 定义。    |
| ---- | --------------------------------------------------- |
| 2    | 该`class`属性定义 bean 的类型并使用完全限定的类名。 |

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">

<!--    spring通过bean自动创建对象    -->
        <bean id="hello" class="com.ioc.pojo.Hello">
            <property name="str" value="hello spring!"></property>
        </bean>

</beans>
```

#### 1.3.4 实例化一个容器

提供给`ApplicationContext`构造函数的一个或多个位置路径是资源字符串，允许容器从各种外部资源（例如本地文件系统、Java 等）加载配置元数据`CLASSPATH`。

```java
 //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Hello hello = (Hello) context.getBean("hello");

        System.out.println(hello.getStr());
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/794983b473fc4437ba73fc169d7285c3.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2pvZXlfcm8=,size_16,color_FFFFFF,t_70)

由叶子表示该类被spring接管了。

ApplicationContext和ClassPathXmlApplicationContext的关系：好几代。

**IOC就是将对象由Spring创建、管理和装配。**

#### 1.3.5 服务层和dao层由xml定义

服务层：

```xml
<bean id="service1" class="com.ioc.service.UserServiceImpl">
        <!--rel是指向spring管理的一个bean-->
        <property name="UserDao" ref="oracle"/>
    </bean>
```

dao层

```xml
<bean id="mysql" class="com.ioc.dao.UserDaoMysqlImpl">

    </bean>

    <bean id="oracle" class="com.ioc.dao.UserDaoOracleImpl">

    </bean>
```

实例化容器

```xml
//获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserServiceImpl service1 = (UserServiceImpl) context.getBean("service1");

        service1.getUser();
```

修改是无需修改代码，只修改配置文件即可！！！

**spring的初始化对象**

spring默认通过无参构造函数初始化对象；

通过有参构造函数：

- 下标赋值

  ```xml
  <!--  通过下标构造函数  -->
      <bean id="hello3" class="com.ioc.pojo.Hello">
          <constructor-arg index="0" value="hello spring"/>
          <constructor-arg index="1" value="15"/>
      </bean>
  ```

  

- 形参名

  ```xml
  <!--  通过形参名构造函数  -->
      <bean id="hello2" class="com.ioc.pojo.Hello">
          <constructor-arg name="a" value="15"/>
          <constructor-arg name="str" value="hello spring"/>
      </bean>
  ```

  

## 1.4 Spring的配置

#### 别名（alias）

## 1.5 依赖注入DI

### 1.5.1 构造器注入

之前的spring初始化对象

### 1.5.2 set注入

基于 Setter 的 DI 是通过容器在调用无参数构造函数或无参数`static`工厂方法来实例化bean 之后调用 bean 上的 setter 方法来完成的。

以下示例显示了一个只能使用纯 setter 注入进行依赖注入的类。这个类是传统的Java。它是一个不依赖于容器特定接口、基类或注解的 POJO。

#### bean的创建依赖于容器

#### bean的属性由容器注入



#### 1) 普通类型的注入

```xml
 <property name="name" value="fxk"/>
        <property name="age" value="22"/>
```



#### 2）自己创建的类的注入

```xml
<bean id="address_study" class="com.ioc.pojo.Address">
        <property name="address" value="文昌街道"/>
    </bean>
    
    <bean id="student" class="com.ioc.pojo.Student">
        <property name="address" ref="address_study"/>
    </bean>
```

#### 3）数组注入等其他

```xml
<!--        注入数组    -->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>三国演义</value>
                <value>水浒传</value>
                <value>西游记</value>
            </array>
        </property>

<!--        注入MAP   -->
        <property name="hobbies">
            <map>
                <entry key="1" value="0002"/>
                <entry key="2" value="0005"/>
                <entry key="3" value="0005"/>
            </map>
        </property>

<!--注入SET-->
        <property name="games">
            <set>
                <value>红色警戒</value>
                <value>王者荣耀</value>
            </set>
        </property>

<!--注入properties-->
        <property name="info">
            <props>
                <prop key="administrator">administrator@example.org</prop>
                <prop key="support">support@example.org</prop>
                <prop key="development">development@example.org</prop>
            </props>
        </property>
```

### 1.5.3 p命名空间和c命名空间

#### p命名空间注入，直接注入属性的值

--方便赋值，但是不如标准 XML 格式灵活。

需要在头部添加p命名空间

```xml
xmlns:p="http://www.springframework.org/schema/p"
```

```xml
<bean id="address1" class="com.ioc.pojo.Address" p:address="济南市"/>
```



#### C命名空间直接注入构造器

在头部导入约束

```xml
xmlns:c="http://www.springframework.org/schema/c"
```

```xml
<bean id="student1" class="com.ioc.pojo.Student" c:name="fxk" c:age="22" c:address-ref="address1"/>
```



## 2.1Bean作用域

创建 bean 定义时，您创建了一个配方，用于创建由该 bean 定义定义的类的实际实例。bean 定义是一个配方的想法很重要，因为这意味着，与类一样，您可以从单个配方创建许多对象实例。

您不仅可以控制要插入到从特定 bean 定义创建的对象中的各种依赖项和配置值，还可以控制从特定 bean 定义创建的对象的范围。这种方法功能强大且灵活，因为您可以通过配置选择您创建的对象的范围，而不必在 Java 类级别烘焙对象的范围。可以将 Bean 定义为部署在多个范围之一中。Spring Framework 支持六个范围，其中四个仅在您使用 web-aware 时才可用`ApplicationContext`。

### 2.1.1 单例模式

全局默认设置为一个对象。

换句话说，当您定义一个 bean 定义并且它的作用域是一个单例时，Spring IoC 容器会创建该 bean 定义定义的对象的一个实例。该单个实例存储在此类单例 bean 的缓存中，并且对该命名 bean 的所有后续请求和引用都返回缓存对象。

![](https://docs.spring.io/spring-framework/docs/current/reference/html/images/singleton.png)

```java
@Test
    public void testSingle(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Student student = (Student) context.getBean("student1");
        Student student2 = (Student) context.getBean("student1");

        System.out.println(student == student2);
    }
```

结果是true。

### 2.1.2 原型模式

bean 部署的非单一原型范围导致每次对特定 bean 发出请求时都会创建一个新 bean 实例。也就是说，bean 被注入到另一个 bean 中，或者您通过`getBean()`容器上的方法调用来请求它。通常，您应该对所有有状态 bean 使用原型作用域，对无状态 bean 使用单例作用域。

![](https://docs.spring.io/spring-framework/docs/current/reference/html/images/prototype.png)

（数据访问对象 (DAO) 通常不配置为原型，因为典型的 DAO 不保存任何对话状态。我们更容易重用单例图的核心。）

```java
@Test
    public void testPrototype(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Student student = (Student) context.getBean("student2");
        Student student2 = (Student) context.getBean("student2");

        System.out.println(student == student2);
    }
```

结果是false。

## 3.Bean的自动装配

- 自动装配是Spring满足bean依赖的一种方式
- 在spring中有三种装配的方式
  - 在xml中显式配置
  - 在java中显式配置
  - 隐式的自动装配

### 3.1 xml显式装配

一个人有两个宠物

```xml
<bean id="cat" class="com.ioc.pojo.Cat" p:name="小喵"/>
    <bean id="dog" class="com.ioc.pojo.Dog" p:name="旺财"/>
    <bean id="people" class="com.ioc.pojo.Person" p:name="xf" p:dog-ref="dog" p:cat-ref="cat"/>
```

### 3.2 ByName自动装备

```xml
<bean id="cat" class="com.ioc.pojo.Cat" p:name="小喵"/>
    <bean id="dog" class="com.ioc.pojo.Dog" p:name="旺财"/>
<!--    <bean id="people" class="com.ioc.pojo.Person" p:name="xf" p:dog-ref="dog" p:cat-ref="cat"/>-->
    <bean id="people" class="com.ioc.pojo.Person" p:name="xf" autowire="byName"/>
```

通过set方法之后的name寻找id相同的bean。

name必须符合。

### 3.3 使用注解实现自动装配

必须在xml文件中导入约束

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>
```

@Autowired直接在属性上使用：

@Qualifier直接选择注入哪一个bean对象

```java
@Qualifier("dog1")
@Autowired
private Dog dog;
@Autowired
private Cat cat;
```

```xml
<bean id="cat" class="com.ioc.pojo.Cat" p:name="小喵"/>
<bean id="dog1" class="com.ioc.pojo.Dog" p:name="旺财"/>
<bean id="dog2" class="com.ioc.pojo.Dog" p:name="小旺财"/>
<bean id="people" class="com.ioc.pojo.Person" p:name="xf"/>
```

可以不用编写set方法。

## 4 使用注解开发

使用注解必须导入**约束**

```xml
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:c="http://www.springframework.org/schema/c"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
  
   <context:annotation-config/>
```

#### @Component组件

@Component将这个注释放到类上说明这个类被spring管理了。

需要先导入**约束**

**base-packag** 表示管理的包

```xml
<context:component-scan base-package="com.ioc.pojo"/>
```

然后使用@Componen注释

```java
//相当于创建了一个bean
@Component
public class User {

    private String userName;
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

@Component的拓展--说明这些类都被spring托管

- dao：持久层@Repository
- service：业务层@Service
- controller ：表现层@Controller

#### @Value赋值

```java
@Value("fxk")
private String userName;
@Value("123456")
private String password;
```

#### @Scope作用域

```java
//作用域的问题  默认是singleton
@Scope("prototype")
```

### 小结

xml负责bean的管理、注释用来属性的注入

注意！！！

开启注解的支持--

---扫描包

```xml
<context:annotation-config/>
<context:component-scan base-package="com.ioc"/>
```

## 5.使用java配置文件取代xml文件

在 Java 代码中使用注解来配置 Spring容器，@Configuration代表这是个配置类

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }
}
```

等效于Spring中xml文件的

```xml
<beans>
    <bean id="myService" class="com.acme.services.MyServiceImpl"/>
</beans>
```

获得容器：

```java
public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
    MyService myService = ctx.getBean(MyService.class);
    myService.doStuff();
}
```

使用@Component注解表示User2已经被Spring接管了

```java
@Component
public class User2 {

    @Value("fxk")
    private String userName;
    @Value("123456")
    private String password;

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

## 6.代理模式

[java实现代理模式](https://segmentfault.com/a/1190000011291179)

[代理模式](https://link.segmentfault.com/?url=https%3A%2F%2Fzh.wikipedia.org%2Fwiki%2F%E4%BB%A3%E7%90%86%E6%A8%A1%E5%BC%8F)是一种设计模式，提供了对目标对象额外的访问方式，即通过代理对象访问目标对象，这样可以在不修改原目标对象的前提下，提供额外的功能操作，扩展目标对象的功能。

简言之，代理模式就是设置一个中间代理来控制访问原目标对象，以达到增强原对象的功能和简化访问方式。

![](https://segmentfault.com/img/remote/1460000011291184)

角色分析

- 抽象角色：
- 真实角色：真实
- 代理角色：代理者
- 客户：访问者

![](https://www.runoob.com/wp-content/uploads/2014/08/20201015-proxy.svg)

Image抽象角色

RealImage真实角色

ProxyImage代理角色

ProxyPatternDemo客户

### 6.1 静态代理 

- 接口

  ```java
  public interface Rent {
  
      public void rent();
  
  }
  ```

- 真实角色

  ```java
  public class Host implements Rent {
  
      private String name;
      private String houseId;
  
      public Host() {
      }
  
      public Host(String name,String houseId) {
          this.name = name;
          this.houseId = houseId;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getHouseId() {
          return houseId;
      }
  
      public void setHouseId(String houseId) {
          this.houseId = houseId;
      }
  
      @Override
      public void rent() {
          System.out.println(name+"出租房子");
      }
  }
  
  ```

- 代理角色

  ```java
  public class Proxy implements Rent{
  
      private Host host;
  
      public Proxy() {
      }
  
      public Proxy(Host host) {
          this.host = host;
      }
  
      @Override
      public void rent() {
  
          System.out.println("收取中介费");
  
          System.out.println("帮助客户看看"+host.getName()+"的房子");
  
          host.rent();
  
          System.out.println("签署合同");
  
      }
  }
  ```

- 客户端访问代理角色

  ```java
  @Test
      public void testProxy(){
          //真实角色--房东
          Host host = new Host("上海","0001");
          //代理--中介
          Proxy proxy = new Proxy(host);
          proxy.rent();
      }
  ```

  

### 6.2动态代理

动态代理的代理类是动态生成的，不是我们直接写好的。

- 基于接口的--JDK动态代理
- 基于类的--cglib
- java字节码

两个类：Proxy，InvocationHandler

#### Proxy

`Proxy`提供了创建动态代理类和实例的静态方法，它也是由这些方法创建的所有动态代理类的超类。

```java
Foo f = (Foo) Proxy.newProxyInstance(Foo.class.getClassLoader(),
                                          new Class<?>[] { Foo.class },
                                          handler); 
```

#### InvocationHandler

`InvocationHandler`是由代理实例的*调用处理程序*实现的*接口* 。

每个代理实例都有一个关联的调用处理程序。 当在代理实例上调用方法时，方法调用将被编码并分派到其调用处理程序的`invoke`方法。

- #### invoke

  ```
  Object invoke(Object proxy,
                方法 method,
                Object[] args)
         throws Throwable
  ```

  处理代理实例上的方法调用并返回结果。 当在与之关联的代理实例上调用方法时，将在调用处理程序中调用此方法。

  - **参数**

    `proxy` - 调用该方法的代理实例 

    `method` -所述`方法`对应于调用代理实例上的接口方法的实例。 `方法`对象的声明类将是该方法声明的接口，它可以是代理类继承该方法的代理接口的超级接口。

    `args` -包含的方法调用传递代理实例的参数值的对象的阵列，或`null`如果接口方法没有参数。 原始类型的参数包含在适当的原始包装器类的实例中，例如`java.lang.Integer`或`java.lang.Boolean` 。

  - **结果**

    从代理实例上的方法调用返回的值。 如果接口方法的声明返回类型是原始类型，则此方法返回的值必须是对应的基本包装类的实例; 否则，它必须是可声明返回类型的类型。 如果此方法返回的值是`null`和接口方法的返回类型是基本类型，那么`NullPointerException`将由代理实例的方法调用抛出。 如上所述，如果此方法返回的值，否则不会与接口方法的声明的返回类型兼容，一个`ClassCastException`将代理实例的方法调用将抛出。

  - **异常**

    `Throwable` - 从代理实例上的方法调用抛出的异常。 异常类型必须可以分配给接口方法的`throws`子句中声明的任何异常类型`java.lang.RuntimeException`检查的异常类型`java.lang.RuntimeException`或`java.lang.Error` 。 如果检查的异常是由这种方法是不分配给任何的中声明的异常类型`throws`接口方法的子句，则一个[`UndeclaredThrowableException`](itss://chm/java/lang/reflect/UndeclaredThrowableException.html)包含有由该方法抛出的异常将通过在方法调用抛出代理实例。

#### 反射

[反射](https://www.cnblogs.com/chanshuyi/p/head_first_of_reflection.html)

**反射就是在运行时才知道要操作的类是什么，并且可以在运行时获取类的完整构造，并调用对应的方法。**

在 JDK 中，反射相关的 API 可以分为下面几个方面：获取反射的 Class 对象、通过反射创建类对象、通过反射获取类属性方法及构造器。

在反射中，要获取一个类或调用一个类的方法，我们首先需要获取到该类的 Class 对象。

#### 在 Java API 中，获取 Class 类对象有三种方法：

**第一种，使用 Class.forName 静态方法。**当你知道该类的全路径名时，你可以使用该方法获取 Class 类对象。

```java
Class clz = Class.forName("java.lang.String");
```

**第二种，使用 .class 方法。**

这种方法只适合在编译前就知道操作的 Class。

```java
Class clz = String.class;
```

**第三种，使用类对象的 getClass() 方法。**

```java
String str = new String("Hello");
Class clz = str.getClass();
```

#### 通过反射创建类对象

通过反射创建类对象主要有两种方式：通过 Class 对象的 newInstance() 方法、通过 Constructor 对象的 newInstance() 方法。

第一种：通过 Class 对象的 newInstance() 方法。

```java
Class clz = Apple.class;
Apple apple = (Apple)clz.newInstance();
```

第二种：通过 Constructor 对象的 newInstance() 方法

```java
Class clz = Apple.class;
Constructor constructor = clz.getConstructor();
Apple apple = (Apple)constructor.newInstance();
```

通过 Constructor 对象创建类对象可以选择特定构造方法，而通过 Class 对象则只能使用默认的无参数构造方法。下面的代码就调用了一个有参数的构造方法进行了类对象的初始化。

```java
Class clz = Apple.class;
Constructor constructor = clz.getConstructor(String.class, int.class);
Apple apple = (Apple)constructor.newInstance("红富士", 15);
```



#### 实现动态代理--代理的是一个接口（只需要实现接口就可以）

**接口**

```java
public interface SellVegetables {

    void sellVegetableForMoney();

}
```

**直接类**

```java
public class Farmer implements SellVegetables{
    @Override
    public void sellVegetableForMoney() {
        System.out.println("卖菜");
    }
}
```

**创建动态代理--实现InvocationHandler接口**

- 实现invoke方法
- 生成代理类对象

```java
/**
 * 动态代理
 */
public class DynamicProxy implements InvocationHandler {

    //要代理的对象
    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    //代理需要额外做的事情
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("开始事物"+method.getName());

        Object invoke = method.invoke(object, args);

        System.out.println("结束事物"+method.getName());

        return invoke;
    }

    //生成代理类
    public Object getProxy(){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),this);
    }

}
```

**进行动态代理--代理的是接口**

```java
//真实角色--房东
        Host host = new Host("上海","0001");
        //真实角色--农民
        Farmer farmer = new Farmer();

        //代理--中介
        //动态的代理可以代理任何事物
        Rent proxy = (Rent) new DynamicProxy(host).getProxy();
        proxy.rent();


        //代理--买菜
        SellVegetables proxy1 = (SellVegetables) new DynamicProxy(farmer).getProxy();
        proxy1.sellVegetableForMoney();
```

### 7.AOP

面向切面编程

AOP（Aspect-Oriented Programming，面向切面编程），可以说是OOP（Object-Oriented Programing，面向对象编程）的补充和完善。OOP引入封装、继承和多态性等概念来建立一种对象层次结构，用以模拟公共行为的一个集合。当我们需要为分散的对象引入公共行为的时候，OOP则显得无能为力。也就是说，OOP允许你定义从上到下的关系，但并不适合定义从左到右的关系。例如日志功能。日志代码往往水平地散布在所有对象层次中，而与它所散布到的对象的核心功能毫无关系。对于其他类型的代码，如安全性、异常处理和透明的持续性也是如此。这种散布在各处的无关的代码被称为横切（cross-cutting）代码，在OOP设计中，它导致了大量代码的重复，而不利于各个模块的重用。

而AOP技术则恰恰相反，它利用一种称为“横切”的技术，剖解开封装的对象内部，并将那些影响了多个类的公共行为封装到一个可重用模块，并将其名为“Aspect”，即切面。所谓“切面”，简单地说，就是将那些与业务无关，却为业务模块所共同调用的逻辑或责任封装起来，便于减少系统的重复代码，降低模块间的耦合度，并有利于未来的可操作性和可维护性。AOP代表的是一个横向的关系，如果说“对象”是一个空心的圆柱体，其中封装的是对象的属性和行为；那么面向方面编程的方法，就仿佛一把利刃，将这些空心圆柱体剖开，以获得其内部的消息。而剖开的切面，也就是所谓的“方面”了。然后它又以巧夺天功的妙手将这些剖开的切面复原，不留痕迹。

- spring中的方法

  - 在maven中导入依赖

    ```maven
    <!-- https://mvnrepository.com/artifact/org.aspectj/aspectjweaver -->
            <dependency>
                <groupId>org.aspectj</groupId>
                <artifactId>aspectjweaver</artifactId>
                <version>1.9.7</version>
                <scope>runtime</scope>
            </dependency>
    ```

    

  - 在xml中配置

    ```xml
     <bean id="userService" class="com.aop.dao.UserDaoImpl"/>
        <bean id="logBefore" class="com.aop.log.Log"/>
        <bean id="logAfter" class="com.aop.log.Log2"/>
    
        <aop:config>
            <!--切入点   execution要执行的位置
            For example : 'execution(* com.xyz.myapp.service.*.*(..))'-->
            <aop:pointcut id="pointCutss"
                          expression='execution(* com.aop.dao.UserDaoImpl.*(..))' />
    
            <!--切入的内容-->
            <aop:advisor advice-ref="logBefore" pointcut-ref="pointCutss"/>
            <aop:advisor advice-ref="logAfter" pointcut-ref="pointCutss"/>
        </aop:config>
    ```

- 注解实现AOP

  - ```
    @Aspect注释java类
    
    @Aspect
    @Component
    public class Logs {
    
        @Before("execution(* com.aop.dao.UserDaoImpl.*(..))")
        public void before(){
            System.out.println("=======方法执行之前=======");
        }
    
        @After("execution(* com.aop.dao.UserDaoImpl.*(..))")
        public void after(){
            System.out.println("=======方法执行之后=======");
        }
    
        @Around("execution(* com.aop.dao.UserDaoImpl.*(..))")
        public void around(ProceedingJoinPoint point) throws Throwable {
            System.out.println("环绕前");
            point.proceed();
            System.out.println("环绕后");
        }
    
    
    }
    ```

## 8.整合Mybatis

### 8.1 使用Lombok框架编写实体类

Lombok是一款好用顺手的工具，就像Google Guava一样，在此予以强烈推荐，每一个Java工程师都应该使用它。**Lombok是一种Java™实用工具，可用来帮助开发人员消除Java的冗长代码，尤其是对于简单的Java对象（POJO）。它通过注释实现这一目的**。通过在开发环境中实现Lombok，开发人员可以节省构建诸如hashCode()和equals()这样的方法以及以往用来分类各种accessor和mutator的大量时间。

```java
@Data
public class User {

    private int id;
    private String username;
    private String avatar;
    private String email;
    private String password;
    private int status;

}
```

### 8.2 使用Mybatis-Spring

MyBatis-Spring 会帮助你将 MyBatis 代码无缝地整合到 Spring 中。它将允许 MyBatis 参与到 Spring 的事务管理之中，创建映射器 mapper 和 `SqlSession` 并注入到 bean 中，以及将 Mybatis 的异常转换为 Spring 的 `DataAccessException`。 最终，可以做到应用代码不依赖于 MyBatis，Spring 或 MyBatis-Spring。

- 编写数据源（datasource）和SQLSessionFactory

```xml
<!--  数据库  -->
    <!--数据源的配置-->
    <bean id="datasource" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/BdAP?userUnicode=true"/>
        <property name="username" value="root"/>
        <property name="password" value=""/>
    </bean>
    <!--mybatis的配置-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="datasource" />
    </bean>
```

- 编写接口并将接口注入到spring

  ```java
  public interface UserMapper {
  
      @Select("select * from m_user where username=#{name}")
      User findUserByName(@Param("name") String userName);
  
  }
  ```

  ```xml
  <!--  将接口注入spring  -->
      <bean id="userMapper" class="org.mybatis.spring.mapper.MapperFactoryBean">
          <property name="mapperInterface" value="com.mybatis.dao.UserMapper" />
          <property name="sqlSessionFactory" ref="sqlSessionFactory" />
      </bean>
  ```

- 实现接口并由spring初始化

  ```java
  @Component
  public class UserMapperImpl {
  
      private final UserMapper userMapper;
  
      public UserMapperImpl(UserMapper userMapper) {
          this.userMapper = userMapper;
      }
  
      public int loginVerify(String userName,String password){
          User userByName = userMapper.findUserByName(userName);
  
          if (userByName==null){
              return -1;//查无此人
          }else {
              if (userByName.getPassword().equals(password)){
                  return 0;//密码正确
              }else {
                  return 1;//密码错误
              }
          }
      }
  }
  ```

  ```xml
  <!--实现类初始化-->
      <bean id="UserService" class="com.mybatis.dao.UserMapperImpl">
          <constructor-arg index="0" ref="userMapper"/>
      </bean>
  ```

  

### 8.3使用 SqlSession

在 MyBatis 中，你可以使用 `SqlSessionFactory` 来创建 `SqlSession`。 一旦你获得一个 session 之后，你可以使用它来执行映射了的语句，提交或回滚连接，最后，当不再需要它的时候，你可以关闭 session。 使用 MyBatis-Spring 之后，你不再需要直接使用 `SqlSessionFactory` 了，因为你的 bean 可以被注入一个线程安全的 `SqlSession`，它能基于 Spring 的事务配置来自动提交、回滚、关闭 session。

#### SqlSessionTemplate

`SqlSessionTemplate` 是 MyBatis-Spring 的核心。作为 `SqlSession` 的一个实现，这意味着可以使用它无缝代替你代码中已经在使用的 `SqlSession`。 `SqlSessionTemplate` 是线程安全的，可以被多个 DAO 或映射器所共享使用。

当调用 SQL 方法时（包括由 `getMapper()` 方法返回的映射器中的方法），`SqlSessionTemplate` 将会保证使用的 `SqlSession` 与当前 Spring 的事务相关。 此外，它管理 session 的生命周期，包含必要的关闭、提交或回滚操作。另外，它也负责将 MyBatis 的异常翻译成 Spring 中的 `DataAccessExceptions`。

**由于模板可以参与到 Spring 的事务管理中，并且由于其是线程安全的，可以供多个映射器类使用，你应该总是用 `SqlSessionTemplate` 来替换 MyBatis 默认的 `DefaultSqlSession` 实现。**

可以使用 `SqlSessionFactory` 作为构造方法的参数来创建 `SqlSessionTemplate` 对象。

```xml
<!--SqlSessionTemplate的配置-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory" />
    </bean>
```

之前sqlsession做的均改为sqlsessionTemplate去做：

- 编写接口

```java
public interface UserMapper {


    User findUserByName(String userName);


    @Select(" select * from m_user")
    List<User> getUser();

}
```

- 实现接口

```java
public class UserDao implements UserMapper{

    private SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public User findUserByName(String userName) {
        return sqlSession.selectOne("com.mybatis.dao.UserMapper.findUserByName",userName);
    }

    @Override
    public List<User> getUser() {
        return sqlSession.selectList("com.mybatis.dao.UserMapper.getUser");
        
    }
}
```

- 注入SqlSessionTemplate

```xml
<!--SqlSessionTemplate的配置-->
    <bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <constructor-arg index="0" ref="sqlSessionFactory" />
    </bean>

<bean id="userDao" class="com.mybatis.dao.UserDao">
        <property name="sqlSession" ref="sqlSession"/>
    </bean>
```

## 9.事务管理Transaction

- 事务是原子性的（要不都成功，要不都失败）
- 事务在项目开发中十分重要
- 确保完整性和一致性

事务的原则：

- 原子性

   表示组成一个事务的多个数据库操作是一个不可分割的原子单元，只有所有的操作执行成功，整个事务才提交。

- 一致性

  数据不会被破坏

- 隔离性

  在并发数据操作时，不同的事务拥有各自的数据空间，他们的操作不会对对方产生敢逃。

- 持久性

   一旦事务提交成功后，事务中所有的数据操作都必须被持久化到数据库中。

 ### 1.标准配置

一个使用 MyBatis-Spring 的其中一个主要原因是它允许 MyBatis 参与到 Spring 的事务管理中。而不是给 MyBatis 创建一个新的专用事务管理器，MyBatis-Spring 借助了 Spring 中的 `DataSourceTransactionManager` 来实现事务管理。

一旦配置好了 Spring 的事务管理器，你就可以在 Spring 中按你平时的方式来配置事务。并且支持 `@Transactional` 注解和 AOP 风格的配置。在事务处理期间，一个单独的 `SqlSession` 对象将会被创建和使用。当事务完成时，这个 session 会以合适的方式提交或回滚。

事务配置好了以后，MyBatis-Spring 将会透明地管理事务。这样在你的 DAO 类中就不需要额外的代码了。

**要开启 Spring 的事务处理功能，在 Spring 的配置文件中创建一个 `DataSourceTransactionManager` 对象：**

要开启 Spring 的事务处理功能，在 Spring 的配置文件中创建一个 `DataSourceTransactionManager` 对象：

```xml
<!--开启事务处理功能-->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <constructor-arg ref="datasource"/>
</bean>
```

**注意：为事务管理器指定的 `DataSource` 必须和用来创建 `SqlSessionFactoryBean` 的是同一个数据源，否则事务管理器就无法工作了。**

### 2.交由容器管理事务

