package cn.tulingxuyuan;

import cn.tulingxueyuan.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IocTest {

    @Test
    public void testIoc(){
        ApplicationContext ioc = new ClassPathXmlApplicationContext("spring-ioc.xml");
        User user = ioc.getBean("user", User.class);
        System.out.println(user);
    }
}
