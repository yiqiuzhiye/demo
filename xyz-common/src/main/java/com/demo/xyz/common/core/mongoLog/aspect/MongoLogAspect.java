package com.demo.xyz.common.core.mongoLog.aspect;//package com.cloudminds.crss.common.core.mongoLog.aspect;
//
//import com.cloudminds.crss.common.core.mongoLog.MongoLog;
//import com.cloudminds.crss.common.core.mongoLog.service.MongoFunc;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.AfterThrowing;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.Resource;
//import java.lang.reflect.Method;
//
///**
// * @author alex.zhao
// * @date 2021-02-21
// * mogonLog 织入
// */
//@Aspect
//@Component
//@Slf4j
//public class MongoLogAspect {
//
//
//    //mongo日志新增接口
//    @Resource
//    private MongoFunc mongoFunc;
//
//    /**
//     *  配置织入切点
//     */
//    @Pointcut("execution(* com.cloudminds.crss..*.controller.*.*(..))")
//    public void mongoLogAspcets(){
//    }
//
//    /**
//     * 处理完请求后执行
//     *
//     * @param joinPoint 切点
//     * @param mongoLog
//     */
//    @AfterReturning(pointcut = "mongoLogAspcets()", returning = "mongoLog")
//    public void doAfterReturning(JoinPoint joinPoint,Object mongoLog)
//    {
//        handleLog(joinPoint,null,mongoLog);
//    }
//
//    /**
//     * 拦截异常操作
//     *
//     * @param joinPoint 切点
//     * @param e 异常
//     */
//    @AfterThrowing(value = "mongoLogAspcets()", throwing = "e")
//    public void doAfterThrowing(JoinPoint joinPoint, Exception e)
//    {
//        handleLog(joinPoint, e, null);
//    }
//
//
//    /**
//     * ====处理数据=====
//     * @param joinPoint
//     * @param e
//     * @param mongoLog
//     */
//    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object mongoLog){
//
//         //获得注解
//        try {
//            MongoLog mongoLogs =getAnnotationLog(joinPoint);
//            if (mongoLogs==null){
//                return;
//            }
//
//
//
//        } catch (Exception exception) {
//            log.error("通知异常=======");
//            log.error("异常信息======+:{}",exception.getMessage());
//            exception.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 是否存在注解，如果存在就获取
//     */
//    private MongoLog getAnnotationLog(JoinPoint joinPoint) throws Exception
//    {
//        Signature signature = joinPoint.getSignature();
//        MethodSignature methodSignature = (MethodSignature) signature;
//        Method method = methodSignature.getMethod();
//        if (method != null)
//        {
//            return method.getAnnotation(MongoLog.class);
//        }
//        return null;
//    }
//
//}
