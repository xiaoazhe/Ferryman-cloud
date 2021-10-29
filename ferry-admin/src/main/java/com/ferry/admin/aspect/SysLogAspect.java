package com.ferry.admin.aspect;

import com.alibaba.fastjson.JSONObject;
import com.ferry.server.admin.entity.SysLog;
import com.ferry.admin.service.SysLogService;
import com.ferry.admin.util.HttpUtils;
import com.ferry.admin.util.IPUtils;
import com.ferry.admin.util.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 系统日志，切面处理类，记录日志
 *
 * @author Louis
 * @date Jan 19, 2019
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("execution(* com.ferry.*.*.service.*.*(..))")
    public void logPointCut() {

    }

    @Pointcut("execution( * com.ferry.*.controller.*.*(..))")//两个..代表所有子目录，最后括号里的两个..代表所有参数
    public void log() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        // 保存日志
        saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        String userName = SecurityUtils.getUsername();
        if (joinPoint.getTarget() instanceof SysLogService) {
            return;
        }
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        SysLog sysLog = new SysLog();

//		Method method = signature.getMethod();
//		com.louis.merak.admin.annotation.SysLog syslogAnno = method.getAnnotation(com.louis.merak.admin.annotation.SysLog.class);
//		if(syslogAnno != null){
//			//注解上的描述
//			sysLog.setOperation(syslogAnno.value());
//		}

        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");

        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSONObject.toJSONString(args[0]);
            if (params.length() > 200) {
                params = params.substring(0, 200) + "...";
            }
            sysLog.setParams(params);
        } catch (Exception e) {
        }
        // 获取request
        HttpServletRequest request = HttpUtils.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(IPUtils.getIpAddr(request));
        // 用户名
        sysLog.setUserName(userName);
        // 执行时长(毫秒)
        sysLog.setTime(time);
        // 保存系统日志
        sysLogService.save(sysLog);
    }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            // 记录下请求内容
            logger.info("请求地址 :{} ", request.getRequestURL().toString());
            logger.info("HTTP METHOD : {}", request.getMethod());
            // 获取真实的ip地址
            logger.info("IP : {}", IPUtils.getIpAddr(request));
            logger.info("CLASS_METHOD : {}", joinPoint.getSignature().getDeclaringTypeName() + "."
                    + joinPoint.getSignature().getName());
            logger.info("参数 : {}", Arrays.toString(joinPoint.getArgs()));
        }

    }

    @AfterReturning(returning = "ret", pointcut = "log()")// returning的值和doAfterReturning的参数名一致
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容(返回值太复杂时，打印的是物理存储空间的地址)
        logger.info("返回值 :{} ", ret);
    }

    @Around("log()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();// ob 为方法的返回值
        logger.info("耗时 : {}", (System.currentTimeMillis() - startTime));
        return ob;
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "ex")
    public void doAfterException(JoinPoint joinPoint, Exception ex) {

    }
}
