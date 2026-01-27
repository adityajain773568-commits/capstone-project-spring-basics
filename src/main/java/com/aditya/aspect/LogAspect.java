package com.aditya.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(* com.aditya.service.impl.*.*(..))")
    public void serviceMethodExpression(){

    }

    @Before("serviceMethodExpression()")
    public void LogBeforeAdvice(JoinPoint joinPoint){
        LOGGER.info("Entered {} with arguments as {}" , joinPoint.getSignature(),joinPoint.getArgs());
    }

    @AfterReturning(pointcut ="serviceMethodExpression()" , returning = "result")
    public void LogAfterReturningAdvice(JoinPoint joinPoint , Object result){
        LOGGER.info("Completed {} with result as {}" , joinPoint.getSignature() , result.getClass());
    }

    @AfterThrowing(pointcut ="serviceMethodExpression()" , throwing = "exception")
    public void LogAfterThrowingAdvice(JoinPoint joinPoint , Exception exception){
        LOGGER.error("Completed {} with exception as {}", joinPoint.getSignature() , exception.getMessage());
    }

}
