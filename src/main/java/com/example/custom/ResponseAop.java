package com.example.custom;

import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Author:Sphinx
 * Date:2019/04/11 9:47
 * Description:
 */
@Order(5)
@Aspect
@Component
@Slf4j
public class ResponseAop {

    //直接在这里定义基本类型会有同步问题
    ThreadLocal<Long> startTime = new ThreadLocal<>();
    /**
     * 切点
     */
    @Pointcut("execution(public * com.example.controller.*.*(..))")
    public void httpResponse() {
    }

    @Before("httpResponse()")
    public void doBefore(JoinPoint joinPoint){
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求的内容
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));//获取请求头中的User-Agent
        log.info("接口路径：{}" , request.getRequestURL().toString());
        log.info("浏览器：{}", userAgent.getBrowser().toString());
        log.info("浏览器版本：{}",userAgent.getBrowserVersion());
        log.info("操作系统: {}", userAgent.getOperatingSystem().toString());
        log.info("IP : {}" , request.getRemoteAddr());
        log.info("请求类型：{}", request.getMethod());
        log.info("类方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数 : {} " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret" , pointcut = "httpResponse()")
    public void doAfterReturning(Object ret){
        //处理完请求后，返回内容
        log.info("方法返回值：{}" , ret);
        log.info("方法执行时间：{}毫秒", (System.currentTimeMillis() - startTime.get()));
    }






}
