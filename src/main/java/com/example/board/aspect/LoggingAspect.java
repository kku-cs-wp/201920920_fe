package com.example.board.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    // 서비스 메서드 실행 전 로깅
    @Before("execution(* com.example.board.service.*.*(..))")
    public void logBeforeServiceMethods(JoinPoint joinPoint) {
        logger.info("Entering method: {} with arguments {}", 
                    joinPoint.getSignature().toShortString(), 
                    joinPoint.getArgs());
    }

    // 서비스 메서드 실행 후 로깅
    @AfterReturning(pointcut = "execution(* com.example.board.service.*.*(..))", returning = "result")
    public void logAfterServiceMethods(JoinPoint joinPoint, Object result) {
        logger.info("Exiting method: {} with result {}", 
                    joinPoint.getSignature().toShortString(), 
                    result);
    }

    // 예외 발생 시 로깅
    @AfterThrowing(pointcut = "execution(* com.example.board.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in method: {} with message {}", 
                     joinPoint.getSignature().toShortString(), 
                     error.getMessage());
    }
}
