package com.lw.acl.cache.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyCacheAspect {

    @Around("@annotation(com.lw.acl.cache.aop.cache.MyCache)")
    public void doCache(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //方法增强
        System.out.println("方法执行前");

        Object returnValue = proceedingJoinPoint.proceed();

        //方法执行后
        System.out.println("方法执行后");

    }

}
