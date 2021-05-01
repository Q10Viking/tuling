package cn.hzz.test;

import cn.hzz.service.IUserService;
import cn.hzz.service.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        System.out.println(ioc.getBean(UserServiceImpl.class).getClass());
    }
}
