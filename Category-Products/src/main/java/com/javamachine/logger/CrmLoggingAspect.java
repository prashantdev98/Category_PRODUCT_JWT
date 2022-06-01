package com.javamachine.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CrmLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.javamachine.controller.*.*(..))")
	private void forControllerPackage() {}
	
	@Pointcut("execution(* com.javamachine.service.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("execution(* com.javamachine.repository.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {}
	
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("==========> in @Before : Calling method: "+theMethod);
		
		Object[] args = joinPoint.getArgs();
		
		for(Object temp:args) {
			myLogger.info("=======> Getting Arguments :  "+temp);
		}
	}
	
	
	@AfterReturning(pointcut="forAppFlow()" ,returning="result")
	public void afterReturning(JoinPoint joinPoint , Object result) {
		String returnStr = joinPoint.getSignature().toShortString();
		myLogger.info("=======> @After Returning from MEthod : "+returnStr);
		
		myLogger.info("=======> @After Returning Object Result : "+result.toString());
	}
		
	
}
