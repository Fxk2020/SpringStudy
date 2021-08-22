# SpringBoot

## 1.SpringMVC

MVC是一种软件架构设计思想,基于MVC架构将我们的应用软件进行分层设计和实现,例如可以分为视图层(View),控制层(Controller),模型层(Model),通过这样的分层设计让我们程序具备更好的灵活性和可可扩展性.因为这样可以将一个复杂应用程序进行简化,实现各司其职,各尽所能.比较适合一个大型应用的开发.

![](https://segmentfault.com/img/bVbRNw3)

1)DispatcherServlet是客户端所有请求处理的入口,负责请求转发。
2)RequestMapping负责存储请求url到后端handler对象之间的映射。
3)Handler 用于处理DispatcherServlet对象转发过来的请求数据。
4)ViewResolver负责处理所有Handler对象响应结果中的view。

## 2.SpringSecurity

过滤器、拦截器

 Spring Security 是一个提供身份验证、授权和针对常见攻击的保护的**框架**。凭借对命令式和反应式应用程序的一流支持，它是保护基于 Spring 的应用程序的事实上的标准。

认证：

授权：

![在这里插入图片描述](https://img-blog.csdnimg.cn/b8920d6272c34d61b9fe73810755e263.png)

在SpringBoot的项目中需要：

```java
@EnableWebSecurity
```

注释和继承这个类：

```java
WebSecurityConfigurerAdapter
```

进行配置：

```java
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 链式编程
     * @param http 授权
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/vip/**").hasRole("vip");

        http.exceptionHandling()
                .accessDeniedHandler(new MyAccessDeniedHandler());

        //没有权限的话--进入登录页面
        http.formLogin().loginPage("/main.html");

        //注销账户
        http.logout()
                .logoutSuccessUrl("/index.html")
                .invalidateHttpSession(true);

        //关闭跨域访问csrf，解决注销404问题--注销账户必须关闭这个csrf
        http.csrf().disable();

//        //记住我功能  cookie
//        http.rememberMe().rememberMeParameter("remember");

        //SecurityConfigurer允许iframe显示
        http.headers().frameOptions().sameOrigin();

    }

    /**
     * 认证
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("fx2k").password(new BCryptPasswordEncoder().encode("fff")).roles("vip");
//                .and()通过and添加用户。

    }
}
```

### 1) 登录逻辑

UserDetailsService类











### bug1

java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"

解决方法指定密码的加密方式。

```java
auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
        .withUser("fx2k").password(new BCryptPasswordEncoder().encode("fff")).roles("vip");
```

## 3.thymeleaf

在onclick方法中添加参数：

```html
<i class="layui-icon layui-icon-read" style="cursor: pointer;color: #0c7cd5"
   th:onclick="pdfSaw([[${course.getCourse_testSrc()}]])">考试范围和主要内容</i>
```

JavaScript方法

```javascript
<!--阅读pdf-->
<script>
    function pdfSaw(url) {

        console.log(url)

        layui.use('layer', function () {
            var layer = layui.layer;
            layer.open({
                type: 2,
                area: ['1000px', '750px'],
                fixed: false, //不固定
                maxmin: true,
                content: url
            });
        });
    };
</script>
```