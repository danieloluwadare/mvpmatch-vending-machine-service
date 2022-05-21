package com.mvpMatch.vendingmachineservice.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration

@Aspect
@Configuration
class EnableMvpSecurityAspect {
    private val log = LoggerFactory.getLogger(javaClass)

    @Around("@annotation(com.mvpMatch.vendingmachineservice.annotations.EnableMvpSecurity)")
    @Throws(Throwable::class)
    fun around(joinPoint: ProceedingJoinPoint): Any {
        log.info("anotation test")

        println("anotation works")
        return joinPoint.proceed()
    }
}