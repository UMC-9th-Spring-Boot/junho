package com.example.umc9th.aop;

import org.aspectj.lang.annotation.Pointcut;

class CommonPointCut {
    @Pointcut("execution(* com.example.umc9th..*Controller.*(..))")
    public void controllerPointcut() {
    }

    @Pointcut("execution(* com.example.umc9th..*Service.*(..))")
    public void servicePointcut() {
    }
}
