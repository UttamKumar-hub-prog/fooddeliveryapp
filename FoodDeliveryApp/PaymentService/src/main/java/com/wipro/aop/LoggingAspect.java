package com.wipro.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.wipro.service.*.*(..)) || execution(* com.wipro.controller.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        log.info("  Entering: {} with args: {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterReturning(value = "execution(* com.wipro.service.*.*(..)) || execution(* com.wipro.controller.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("  Exiting: {} with result: {}", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "execution(* com.wipro.service.*.*(..)) || execution(* com.wipro.controller.*.*(..))", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("  Exception in {} with message: {}", joinPoint.getSignature(), ex.getMessage(), ex);
    }
}
