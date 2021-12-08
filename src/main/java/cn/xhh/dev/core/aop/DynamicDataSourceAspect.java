package cn.xhh.dev.core.aop;

import cn.xhh.dev.common.annotation.DataSource;
import cn.xhh.dev.core.datasource.DynamicDataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 1.@Order(-1)保证该AOP在@Transactional之前执行
 * @author Administrator
 */
@Aspect
@Component
@Order(-1)
public class DynamicDataSourceAspect {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut("@annotation(cn.xhh.dev.common.annotation.DataSource)"
            + "|| @within(cn.xhh.dev.common.annotation.DataSource)")
    public void dsPointCut()  {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Method targetMethod = this.getTargetMethod(point);
        //获取要切换的数据源
        DataSource dataSource = targetMethod.getAnnotation(DataSource.class);
        if (dataSource != null)  {
            DynamicDataSourceContextHolder.setDataSourceType(dataSource.name().name());
        }
        try {
            return point.proceed();
        }
        finally  {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.removeDataSourceType();
        }
    }

    /**
     * 获取目标方法
     */
    private Method getTargetMethod(ProceedingJoinPoint pjp) throws NoSuchMethodException {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method agentMethod = methodSignature.getMethod();
        return pjp.getTarget().getClass().getMethod(agentMethod.getName(), agentMethod.getParameterTypes());
    }
}