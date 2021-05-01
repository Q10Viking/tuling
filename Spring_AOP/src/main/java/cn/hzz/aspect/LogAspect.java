package cn.hzz.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Before("execution(* cn.hzz.service..*.*(..))")
    public void before(){
        System.out.println("Before");
    }
}
