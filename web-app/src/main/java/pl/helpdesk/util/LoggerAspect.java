/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.helpdesk.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.omg.PortableInterceptor.RequestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <aop:aspectj-autoproxy proxy-target-class="true"> 
 *   <aop:include * name="loggerAspect" /> 
 * </aop:aspectj-autoproxy>
 * <bean id="loggerAspect" class="com.service.LoggerAspect"> </bean>
 *
 * @author matik06
 */
@Aspect
public class LoggerAspect {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoggerAspect.class);
    @Autowired
    private RequestInfo requestInfo;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object logWebRequest(ProceedingJoinPoint pjp) throws Throwable {
        Object obj;
        Long startTime = System.currentTimeMillis();
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            LOGGER.info("User: " + user);
        }
//        LOGGER.info("Controller UUID: " + requestInfo.getRequestUUID());
//        LOGGER.info("Controller URL: " + requestInfo.getRequestMethod());
        LOGGER.info("Controller Method: " + pjp.getSignature());
        logArguments(pjp);
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            LOGGER.error("Controller:" + e.getMessage());
            throw e;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Returns: " + obj);
            LOGGER.debug("Controller Response Time: "
                    + (System.currentTimeMillis() - startTime) + " ms");
        }
        return obj;
    }

    @Around("execution(public * com.common.service..*.*(..))")
    public Object logCommonServiceRequest(ProceedingJoinPoint pjp)
            throws Throwable {
        return logServiceRequest(pjp);
    }

    @Around("execution(public * com.modules.*.service..*.*(..))")
    public Object logModulesServiceRequest(ProceedingJoinPoint pjp)
            throws Throwable {
        return logServiceRequest(pjp);
    }

    private Object logServiceRequest(ProceedingJoinPoint pjp) throws Throwable,
            Exception {
        Object obj;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Service: " + pjp.getTarget().getClass() + " -- "
                    + pjp.getSignature());
            logArguments(pjp);
        }
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            LOGGER.error("Service Exception:" + e.getMessage());
            throw e;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Service Returns: " + obj);
        }
        return obj;
    }

    @Around("execution(public * com.common.dao..*.*(..))")
    public Object logCommonDAORequest(ProceedingJoinPoint pjp) throws Throwable {
        return logDAORequest(pjp);
    }

    @Around("execution(public * com.modules.*.dao..*.*(..))")
    public Object logModulesDAORequest(ProceedingJoinPoint pjp)
            throws Throwable {
        return logDAORequest(pjp);
    }

    private Object logDAORequest(ProceedingJoinPoint pjp) throws Throwable,
            Exception {
        Object obj;
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DAO: " + pjp.getTarget().getClass() + " -- "
                    + pjp.getSignature());
            logArguments(pjp);
        }
        try {
            obj = pjp.proceed();
        } catch (Exception e) {
            LOGGER.error("DAO Exception:" + e.getMessage());
            throw e;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("DAO Returns: " + obj);
        }
        return obj;
    }

    private void logArguments(ProceedingJoinPoint pjp) {
        StringBuffer argLog = new StringBuffer();
        for (Object arg : pjp.getArgs()) {
            argLog.append(arg);
            argLog.append(",");
        }
        LOGGER.info("Args: " + argLog);
    }
}
