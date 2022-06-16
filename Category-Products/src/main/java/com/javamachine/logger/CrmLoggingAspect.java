package com.javamachine.logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.jboss.logging.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;

import com.javamachine.dto.AuthRequest;
import com.javamachine.exception.LoginDetailInvalidException;

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
	public void before(JoinPoint joinPoint) throws LoginDetailInvalidException {
		
		String theMethod = joinPoint.getSignature().toShortString();
		myLogger.info("==========> in @Before : Calling method: "+theMethod);
		
		Object[] args = joinPoint.getArgs();
		
//		for(Object arg : args) {
//			AuthRequest authRequest = (AuthRequest) arg;
//			if((authRequest.getUserName() == null && authRequest.getPassword() == null)|| (authRequest.getUserName().isEmpty() && authRequest.getPassword().isEmpty())) {
////				System.out.println("In EXception +++++++++++++++++++++");
//				throw new LoginDetailInvalidException("email and password is missing");
//			}else if(authRequest.getUserName() == null || authRequest.getUserName().isEmpty()) {
//				throw new LoginDetailInvalidException("email is missing");
//			}else if(authRequest.getPassword() == null || authRequest.getPassword().isEmpty()) {
//				throw new LoginDetailInvalidException("password is missing");
//			}else if(authRequest.getUserName().) {
//				
//			}
//		}
		for(Object temp:args) {
			myLogger.info("=======> Getting Arguments :  "+temp.toString());
		
		}
	}
	
	
	@AfterReturning(pointcut="forAppFlow()" ,returning="result")
	public void afterReturning(JoinPoint joinPoint , Object result) {
		String returnStr = joinPoint.getSignature().toShortString();
		myLogger.info("=======> @After Returning from MEthod : "+returnStr);
		
		myLogger.info("=======> @After Returning Object Result : "+result.toString());
	}
		
	
}
