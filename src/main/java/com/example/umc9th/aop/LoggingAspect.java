package com.example.umc9th.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect extends CommonPointCut {

    /**
     * @Before: 대상 메소드가 실행되기 전에 실행됩니다.
     * 주로 메소드에 전달된 인자를 로깅하는 데 사용됩니다.
     */
    @Before("controllerPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("[@Before]Controller Method Started: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    /**
     * @AfterReturning: 대상 메소드가 성공적으로 실행되고 결과를 반환한 후에 실행됩니다.
     * 'returning' 속성을 사용하여 반환값을 가져올 수 있습니다.
     */
    @AfterReturning(pointcut = "servicePointcut()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("[AfterReturning]Service Method Succeeded: {} | Result: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    /**
     * @AfterThrowing: 대상 메소드에서 예외가 발생했을 때 실행됩니다.
     * 'throwing' 속성을 사용하여 발생한 예외를 가져올 수 있습니다.
     */
    @AfterThrowing(pointcut = "servicePointcut()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("[AfterThrowing]Service Method Exception: {} | Exception: {}",
                joinPoint.getSignature().toShortString(),
                exception.getMessage());
    }

    /**
     * @After: 대상 메소드의 실행 결과(성공/실패)와 상관없이 항상 마지막에 실행됩니다.
     * try-catch-finally 구문의 finally와 유사합니다.
     */
    @After("controllerPointcut()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("[@After]Controller Method Finished: {}", joinPoint.getSignature().toShortString());
    }

    /**
     * @Around: 대상 메소드의 실행 전, 후, 그리고 예외 발생 시점 모두에 개입할 수 있는 가장 강력한 Advice 입니다.
     * 메소드 실행 시간을 측정하거나, 실행 여부를 결정하는 등 다양한 제어가 가능합니다.
     * 반드시 ProceedingJoinPoint.proceed()를 호출해야 대상 메소드가 실행됩니다.
     */
    @Around("servicePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[@Around]Executing: {}", joinPoint.getSignature().toShortString());

        long startTime = System.currentTimeMillis();

        // 대상 메소드 실행
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        log.info("[@Around]Finished: {} | Execution Time: {}ms",
                joinPoint.getSignature().toShortString(),
                executionTime);

        return result;
    }
}
