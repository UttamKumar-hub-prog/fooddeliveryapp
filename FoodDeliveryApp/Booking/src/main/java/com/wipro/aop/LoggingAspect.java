package com.wipro.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
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
		log.info("Method called: {} with args {}", joinPoint.getSignature(), joinPoint.getArgs());
	}

	@AfterReturning(value = "execution(* com.wipro.service.*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Method completed: {} with result {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(value = "execution(* com.wipro.service.*.*(..))", throwing = "ex")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
		log.error("Exception in method: {} with message {}", joinPoint.getSignature(), ex.getMessage());
	}

	@Around("execution(* com.wipro.service.*.*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("Execution time of {} :: {} ms", joinPoint.getSignature(), executionTime);
		return proceed;
	}
}
