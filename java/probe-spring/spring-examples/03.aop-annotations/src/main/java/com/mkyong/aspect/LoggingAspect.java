package com.mkyong.aspect;

import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class LoggingAspect {


    static Logger logger = Logger.getLogger("LoggingAspect");

    @Before("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("logBefore() is running!");
        logger.info("hijacked : " + joinPoint.getSignature().getName());
        logger.info("******");
    }

    @After("execution(* com.mkyong.customer.bo.CustomerBo.addCustomer(..))")
    public void logAfter(JoinPoint joinPoint) {

        logger.info("logAfter() is running!");
        logger.info("hijacked : " + joinPoint.getSignature().getName());
        logger.info("******");

    }

    @AfterReturning(
            pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerReturnValue(..))",
            returning= "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        logger.info("logAfterReturning() is running!");
        logger.info("hijacked : " + joinPoint.getSignature().getName());
        logger.info("Method returned value is : " + result);
        logger.info("******");

    }

    @AfterThrowing(
            pointcut = "execution(* com.mkyong.customer.bo.CustomerBo.addCustomerThrowException(..))",
            throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {

        logger.info("logAfterThrowing() is running!");
        logger.info("hijacked : " + joinPoint.getSignature().getName());
        logger.info("Exception : " + error);
        logger.info("******");

    }

    @Around("execution(* com.mkyong.customer.bo.CustomerBo.addCustomerAround(..))")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        logger.info("logAround() is running!");
        logger.info("hijacked method : " + joinPoint.getSignature().getName());
        logger.info("hijacked arguments : " + Arrays.toString(joinPoint.getArgs()));

        logger.info("Around before is running!");
        joinPoint.proceed();
        logger.info("Around after is running!");

        logger.info("******");

    }

}