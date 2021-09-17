package com.demo.xyz.common.core.advice;

import com.demo.xyz.common.core.util.JsonUtils;
import com.demo.xyz.common.core.util.ThreadLocalUtils;
import io.opentracing.Tracer;
import io.opentracing.util.GlobalTracer;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;


/**
 * 统一记录接口日志切面类
 *
 * @author alex.xu
 * @version 2019年8月28日
 * @see MethodLogAdvice
 * @since
 */
@Aspect
public class MethodLogAdvice implements ApplicationContextAware {

	private static Logger logger = LoggerFactory.getLogger(MethodLogAdvice.class);

	private ApplicationContext ctx;

	private final int MAX_PAYLOAD_BYTES = 10 * 1024;
	/**
	 * 超过10KB，则不输出响应
	 */
	private final int MAX_IGNORE_RESP_BYTES = 10 * 1024;


	/**
	 * 功能描述:  定义切面环绕通知得处理逻辑
	 *
	 * @param point
	 * @return
	 * @throws Throwable
	 * @see
	 */
	@Around("com.cloudminds.crss.common.core.aspect.pointcut.MethodLogPointcut.pointcutLog()")

	public Object doAroundAdvice(ProceedingJoinPoint point) throws Throwable {

		Tracer tracer = GlobalTracer.get();
		long startTime = System.currentTimeMillis();
		//接口类名称：
		String className = point.getTarget().getClass().getName();

		//拦截的方法名称
		String methodName = point.getSignature().getName();
		//拦截方法参数值
		Object[] args = point.getArgs();
		String reqParams = JsonUtils.beanToJson(args);
		logger.info(">>>>>>>>>>guid{},类名：{},方法名：{}>>start,参数:{}", ThreadLocalUtils.getValue(), className, methodName,
				reqParams);
		//执行方法逻辑
		Object obj = point.proceed();
		long runTime = System.currentTimeMillis() - startTime;
		String result = JsonUtils.beanToJson(obj);

		if (StringUtils.isNotBlank(result) && result.getBytes().length > MAX_IGNORE_RESP_BYTES) {

			result = "-";
		}
		logger
				.info(">>>>>>>>>>guid:{},类名：{},方法名：{}>>end,返回值:{},耗时:{} ms", ThreadLocalUtils.getValue(), className,
						methodName,
						result, runTime);
		try {
			if (tracer != null &&
					tracer.activeSpan() != null) {
				String applicationName = this.ctx.getEnvironment().getProperty("spring.application.name");
				// 请求数据大于10KB则不写入请求
				if (StringUtils.isNotBlank(reqParams) && reqParams.getBytes().length > MAX_PAYLOAD_BYTES) {
					reqParams = "-";
				}
				tracer.activeSpan().setTag("cross.req_data", reqParams).setTag("cross.resp_data", result)
						.setTag("cross.service_name", applicationName);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		ThreadLocalUtils.remove();
		return obj;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

		this.ctx = applicationContext;


	}

	public static void main(String[] args) {
		String str = "{\"code\":0,\"message\":\"success\",\"data\":{\"records\":[{\"id\":\"1390852573880578049\",\"robotType\":\"ginger-lite\",\"robotCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2048000011\",\"robotName\":\"GL0011\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1340933775815192578\",\"deviceType\":3,\"deviceCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2048000011\",\"typeId\":300,\"typeName\":\"Map Missed Or Error\",\"typeCname\":\"地图不存在或错误\",\"eventTime\":1620440068000,\"level\":1,\"details\":\"\",\"createTime\":1620440069061,\"readFlag\":0,\"updateTime\":1620440069061,\"status\":0,\"startTime\":1620440068000,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":4,\"entityType\":\"Robot\",\"entityInstance\":\"Robot\",\"probableCause\":300,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"867654040262319\",\"robotAppVersionCode\":null},{\"id\":\"1390624761328365570\",\"robotType\":\"ginger-lite\",\"robotCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2041000043\",\"robotName\":\"gingerlite430\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1369295911252832257\",\"deviceType\":3,\"deviceCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2041000043\",\"typeId\":1,\"typeName\":\"Connection Lost\",\"typeCname\":\"连接中断\",\"eventTime\":1620385753540,\"level\":2,\"details\":\"\",\"createTime\":1620385754315,\"readFlag\":0,\"updateTime\":1620385754315,\"status\":0,\"startTime\":1620385753540,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":2,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":1,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"865697040003805\",\"robotAppVersionCode\":null},{\"id\":\"1390624675882004481\",\"robotType\":\"ginger-lite\",\"robotCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2041000043\",\"robotName\":\"gingerlite430\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1369295911252832257\",\"deviceType\":3,\"deviceCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2041000043\",\"typeId\":900,\"typeName\":\"Threshold Crossed\",\"typeCname\":\"性能越限\",\"eventTime\":1620385733000,\"level\":1,\"details\":\" usage 85.0 higher than 80.0\",\"createTime\":1620385733944,\"readFlag\":0,\"updateTime\":1620385733944,\"status\":0,\"startTime\":1620385733000,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":3,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":900,\"specificProblem\":\"1\",\"specificProblemName\":\"CPU Usage(%)\",\"specificProblemCname\":\"CPU使用率(%)\",\"remark\":null,\"rcuCode\":\"865697040003805\",\"robotAppVersionCode\":null},{\"id\":\"1390622301603950594\",\"robotType\":\"ginger-lite\",\"robotCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2041000043\",\"robotName\":\"gingerlite430\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1369295911252832257\",\"deviceType\":3,\"deviceCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2041000043\",\"typeId\":48,\"typeName\":\"Low Battery\",\"typeCname\":\"低电量\",\"eventTime\":1620385167000,\"level\":3,\"details\":\"battery low\",\"createTime\":1620385167872,\"readFlag\":0,\"updateTime\":1620385167872,\"status\":0,\"startTime\":1620385167000,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":5,\"entityType\":\"Robot\",\"entityInstance\":\"Robot\",\"probableCause\":48,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"865697040003805\",\"robotAppVersionCode\":null},{\"id\":\"1390616267778879489\",\"robotType\":\"ginger\",\"robotCode\":\"GINGERXR-1XXXXXXXXXXGIN03S2114000002\",\"robotName\":\"RDK9902\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1383070081560129538\",\"deviceType\":3,\"deviceCode\":\"GINGERXR-1XXXXXXXXXXGIN03S2114000002\",\"typeId\":1,\"typeName\":\"Connection Lost\",\"typeCname\":\"连接中断\",\"eventTime\":1620383728531,\"level\":2,\"details\":\"\",\"createTime\":1620383729296,\"readFlag\":0,\"updateTime\":1620383729296,\"status\":0,\"startTime\":1620383728531,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":2,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":1,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"355929099949902\",\"ro100  1525    0  1525    0     0    392      0 --:--:--  0:00:03 --:--:--   392ull},{\"id\":\"1390611700378955778\",\"robotType\":\"ginger\",\"robotCode\":\"0421419030982\",\"robotName\":\"0982\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1385425946544345089\",\"deviceType\":3,\"deviceCode\":\"0421419030982\",\"typeId\":1,\"typeName\":\"Connection Lost\",\"typeCname\":\"连接中断\",\"eventTime\":1620382639511,\"level\":2,\"details\":\"\",\"createTime\":1620382640343,\"readFlag\":0,\"updateTime\":1620382640343,\"status\":0,\"startTime\":1620382639511,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":2,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":1,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"355929099949662\",\"robotAppVersionCode\":null},{\"id\":\"1390502284677804033\",\"robotType\":\"ginger\",\"robotCode\":\"355929090035206\",\"robotName\":\"355929090035206\",\"tenantId\":5,\"tenantCode\":\"cloud_pepper\",\"tenantName\":\"cloudpepper\",\"deviceId\":\"1349920240040574978\",\"deviceType\":3,\"deviceCode\":\"355929090035206\",\"typeId\":1,\"typeName\":\"Connection Lost\",\"typeCname\":\"连接中断\",\"eventTime\":1620356552540,\"level\":2,\"details\":\"\",\"createTime\":1620356553607,\"readFlag\":0,\"updateTime\":1620356553607,\"status\":0,\"startTime\":1620356552540,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":2,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":1,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"355929090035206\",\"robotAppVersionCode\":null},{\"id\":\"1390491877305151490\",\"robotType\":\"ginger\",\"robotCode\":\"0421219006874\",\"robotName\":\"robot_6874\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1367733113775370241\",\"deviceType\":3,\"deviceCode\":\"0421219006874\",\"typeId\":1,\"typeName\":\"Connection Lost\",\"typeCname\":\"连接中断\",\"eventTime\":1620354071502,\"level\":2,\"details\":\"\",\"createTime\":1620354072295,\"readFlag\":0,\"updateTime\":1620354072295,\"status\":0,\"startTime\":1620354071502,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":2,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":1,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"864972049980217\",\"robotAppVersionCode\":null},{\"id\":\"1390202482626850817\",\"robotType\":\"ginger-lite\",\"robotCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2038000024\",\"robotName\":\"GINLITXR-1LXXXXXXXXXGNL01S2038000024\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1315543220982956033\",\"deviceType\":3,\"deviceCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2038000024\",\"typeId\":1,\"typeName\":\"Connection Lost\",\"typeCname\":\"连接中断\",\"eventTime\":1620285074533,\"level\":2,\"details\":\"\",\"createTime\":1620285075228,\"readFlag\":0,\"updateTime\":1620285075228,\"status\":0,\"startTime\":1620285074533,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":2,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":1,\"specificProblem\":null,\"specificProblemName\":null,\"specificProblemCname\":null,\"remark\":null,\"rcuCode\":\"867654040261782\",\"robotAppVersionCode\":null},{\"id\":\"1390202253466857473\",\"robotType\":\"ginger-lite\",\"robotCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2038000024\",\"robotName\":\"GINLITXR-1LXXXXXXXXXGNL01S2038000024\",\"tenantId\":35,\"tenantCode\":\"86gingerlite\",\"tenantName\":\"86gingerlite\",\"deviceId\":\"1315543220982956033\",\"deviceType\":3,\"deviceCode\":\"GINLITXR-1LXXXXXXXXXGNL01S2038000024\",\"typeId\":900,\"typeName\":\"Threshold Crossed\",\"typeCname\":\"性能越限\",\"eventTime\":1620285019000,\"level\":1,\"details\":\" usage 86.0 higher than 80.0\",\"createTime\":1620285020592,\"readFlag\":0,\"updateTime\":1620285020592,\"status\":0,\"startTime\":1620285019000,\"endTime\":null,\"confirmFlag\":-1,\"messageType\":1,\"rdmAlarmType\":3,\"entityType\":\"RCU\",\"entityInstance\":\"RCU\",\"probableCause\":900,\"specificProblem\":\"1\",\"specificProblemName\":\"CPU Usage(%)\",\"specificProblemCname\":\"CPU使用率(%)\",\"remark\":null,\"rcuCode\":\"867654040261782\",\"robotAppVersionCode\":null}],\"total\":545926,\"size\":10,\"current\":1,\"orders\":[],\"searchCount\":true,\"pages\":54593},\"uid\":null}\n";
		System.out.println("length:" + str.getBytes().length / 1024);

	}


}
