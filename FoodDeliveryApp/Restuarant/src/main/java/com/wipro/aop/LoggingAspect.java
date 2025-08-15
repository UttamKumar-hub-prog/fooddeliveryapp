package com.wipro.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Pointcut("execution(* com.wipro.service.*.*(..)) || execution(* com.wipro.controllers.*.*(..))")
	public void applicationPackagePointcut() {
		// Pointcut for all methods in service and controller
	}

	@Before("applicationPackagePointcut()")
	public void logBefore(JoinPoint joinPoint) {
		log.info("Entering method: {} with args: {}", joinPoint.getSignature(), Arrays.toString(joinPoint.getArgs()));
	}

	@AfterReturning(pointcut = "applicationPackagePointcut()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Exiting method: {} with result: {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		log.error("Exception in method: {} with message: {}", joinPoint.getSignature(), error.getMessage());
	}
}
