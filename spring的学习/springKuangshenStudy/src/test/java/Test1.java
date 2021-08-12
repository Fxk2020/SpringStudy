import com.aop.dao.UserDao;
import com.aop.dao.UserDaoImpl;
import com.aop.pojo.Farmer;
import com.aop.pojo.Host;
import com.aop.pojo.Rent;
import com.aop.pojo.SellVegetables;
import com.aop.proxy.DynamicProxy;
import com.aop.proxy.Greengrocer;
import com.aop.proxy.Proxy;
import com.config.javaConfig.MyConfig;
import com.config.pogo.User2;
import com.ioc.pojo.*;
import com.mybatis.dao.UserMapperImpl;
import com.transaction.dao.BlogDao;
import com.transaction.pojo.Blog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;
import java.util.List;

public class Test1 {

    public static void main(String[] args) {

        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Student student = (Student) context.getBean("student");

        System.out.println(student);

        Address address1 = (Address) context.getBean("address1");

        System.out.println(address1.getAddress());


//        UserServiceImpl service1 = (UserServiceImpl) context.getBean("service1");
//
//        service1.getUser();

//        Hello hello = (Hello) context.getBean("hello1");
//
//        System.out.println(hello.getA());

//        UserService userService = new UserServiceImpl();
//
//        ((UserServiceImpl)userService).setUserDao(new UserDaoOracleImpl());
//
//        userService.getUser();
    }

    @Test
    public void testP(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Address address1 = (Address) context.getBean("address1");

        System.out.println(address1.getAddress());
    }

    @Test
    public void testC(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Student student = (Student) context.getBean("student1");

        System.out.println(student);
    }

    @Test
    public void testSingle(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Student student = (Student) context.getBean("student1");
        Student student2 = (Student) context.getBean("student1");

        System.out.println(student == student2);
    }

    @Test
    public void testPrototype(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Student student = (Student) context.getBean("student2");
        Student student2 = (Student) context.getBean("student2");

        System.out.println(student == student2);
    }

    @Test
    public void testA(){
        //获取spring的上下文对象
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        Person people = (Person) context.getBean("people");
        people.getCat().a();
        people.getDog().a();
        System.out.println(people);
    }

    @Test
    public void testComponent(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        User user = (User) context.getBean("user");

        System.out.println(user);
    }

    @Test
    public void testJavaConfig(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);

        User2 u = (User2) ctx.getBean("getUser");

        System.out.println(u);
    }

    @Test
    public void testProxy(){
        //真实角色--房东
        Host host = new Host("上海","0001");
        //代理--中介
        Proxy proxy = new Proxy(host);
        proxy.rent();

        System.out.println();

        //真实角色--农民
        Farmer farmer = new Farmer();
        //代理--水果商
        Greengrocer greengrocer = new Greengrocer(farmer);
        greengrocer.sellVegetableForMoney();

    }

    @Test
    public void testDynamicProxy(){
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
    }

    @Test
    public void testAop(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserDao userService = (UserDao) context.getBean("userService");

        userService.add();
    }

    @Test
    public void testAop2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserDao userService = (UserDao) context.getBean("userService");

        userService.add();
    }

    @Test
    public void testMybatis(){

        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        UserMapperImpl userService = (UserMapperImpl) context.getBean("UserService");

        if (userService.loginVerify("fk","123456")==0){
            System.out.println("登录成功！");
        }else {
            System.out.println("密码或账户错误！");
        }

    }

    @Test
    public void testMybatis2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        com.mybatis.dao.UserDao userDao = context.getBean("userDao", com.mybatis.dao.UserDao.class);

        List<com.mybatis.pojo.User> user = (List<com.mybatis.pojo.User>) userDao.getUser();

        for (com.mybatis.pojo.User u:user) {
            System.out.println(u.getUsername());
        }
    }

    @Test
    public void testTransaction(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");

        BlogDao blogDao = (BlogDao) context.getBean("blogDao");

        List<Blog> blogs = blogDao.selectBlogsByUserId(1);
        List<Blog> blogs2 = blogDao.selectAllBlogs();

        for (int i = 0; i < blogs.size(); i++) {
            System.out.println(blogs.get(i).getId());
        }

        System.out.println();
        for (int i = 0; i < blogs2.size(); i++) {
            System.out.println(blogs2.get(i).getId());
        }

    }

}
