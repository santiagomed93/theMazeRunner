package com.bootcamp.santiagomed93.vagoApi.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

@Aspect
@Component
public class LogAspectController {

	@Pointcut("within(com.bootcamp.santiagomed93..*)")
	public void execute() {
	}

	@Before("execute()")
	public void logBefore(JoinPoint jp) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();
		System.out.println("Entering in Method :  " + jp.getSignature().getName());
		System.out.println("Class Name: " + jp.getSignature().getDeclaringTypeName());
		System.out.println("Arguments: " + Arrays.toString(jp.getArgs()));
		System.out.println("Target class: " + jp.getTarget().getClass().getName());

		if (null != request) {
			System.out.println("Start Header Section of request ");
			System.out.println("Method Type : " + request.getMethod());
			System.out.println("Server: " + request.getHeader("host"));
			System.out.println("Request Path info :" + request.getServletPath());
			System.out.println("End Header Section of request ");
			System.out.println(request);
		}

	}

	@AfterReturning(pointcut = "execute()", returning = "result")
	public void logAfter(JoinPoint joinPoint, ResponseEntity<?> result) {
		System.out.println("response: " + result.getStatusCode());
	}

}
