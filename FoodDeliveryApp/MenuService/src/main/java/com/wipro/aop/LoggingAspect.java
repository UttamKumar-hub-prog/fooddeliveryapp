package com.wipro.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

	@Before("execution(* com.wipro.controller.*.*(..)) || execution(* com.wipro.service.*.*(..))")
	public void logBefore(JoinPoint joinPoint) {
		log.info("Entering Method: {} with Arguments: {}", joinPoint.getSignature(), joinPoint.getArgs());
	}

	@AfterReturning(pointcut = "execution(* com.wipro.controller.*.*(..)) || execution(* com.wipro.service.*.*(..))", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		log.info("Exiting Method: {} with Result: {}", joinPoint.getSignature(), result);
	}

	@AfterThrowing(pointcut = "execution(* com.wipro.controller.*.*(..)) || execution(* com.wipro.service.*.*(..))", throwing = "error")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
		log.error("Exception in Method: {} with Cause: {}", joinPoint.getSignature(), error.getMessage());
	}

	@Around("execution(* com.wipro.controller.*.*(..)) || execution(* com.wipro.service.*.*(..))")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object proceed = joinPoint.proceed();
		long executionTime = System.currentTimeMillis() - start;
		log.info("{} executed in {} ms", joinPoint.getSignature(), executionTime);
		return proceed;
	}
}
