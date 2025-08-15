package com.wipro.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

	@Before("execution(* com.wipro.service.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("Entering method: {} with arguments {}", joinPoint.getSignature(), joinPoint.getArgs());
	}

	@AfterReturning(value = "execution(* com.wipro.service.*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Method executed successfully: {} returned: {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(value = "execution(* com.wipro.service.*.*(..))", throwing = "exception")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
		log.error("Exception in method: {} - Message: {}", joinPoint.getSignature(), exception.getMessage());
	}
}
