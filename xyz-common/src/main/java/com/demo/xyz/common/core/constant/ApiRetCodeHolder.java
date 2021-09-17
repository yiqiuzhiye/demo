package com.demo.xyz.common.core.constant;

import cn.hutool.core.util.ClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class ApiRetCodeHolder implements InitializingBean {

	private final static String CODE_PREFIX = "CODE_";
	private final static String MESSAGE_PREFIX = "MESSAGE_";

	private final static String APP_CODE_EXCEPTON_NAME_PATTERN = "CODE_{}_EXCEPTION";

	private static String applicationName;

	@Value("${spring.application.name}")
	public void setApplicationName(String applicationName) {

		ApiRetCodeHolder.applicationName = applicationName;
	}

	private final static Map<Integer, String> codeMessageMap = new ConcurrentHashMap<>();
	private final static Map<String, Integer> fieldNameCodeMap = new ConcurrentHashMap<>();

	public void init() {

		Set<Class<?>> allClassSet = new HashSet<Class<?>>();
		Set<Class<?>> classSet = ClassUtil.scanPackageBySuper(ApiRetCodeEnum.class.getPackage().getName(),
				ApiRetCodeEnum.class);
		allClassSet.add(ApiRetCodeEnum.class);
		allClassSet.addAll(classSet);
		log.info(">>>>>ApiRetCodeHolder load class:{}", allClassSet);
		for (Class cls : allClassSet) {

			Field[] fields = cls.getFields();
			Map<String, Field> fieldMap = Arrays.asList(fields).stream()
					.collect(Collectors.toMap(Field::getName, f -> f));

			for (Field field : fields) {

				String codeName = field.getName();
				if (codeName.indexOf(CODE_PREFIX) < 0) { // 错误返回码

					continue;
				}

				try {

					String messageName = String.format(MESSAGE_PREFIX + "%s", codeName.replace(CODE_PREFIX, ""));
					Integer codeValue = null;
					String messageValue = null;

					codeValue = (Integer) ReflectionUtils.getField(field, null);
					messageValue = null;
					if (fieldMap.containsKey(messageName)) {
						messageValue = (String) ReflectionUtils.getField(fieldMap.get(messageName), null);
					}
					if (codeValue != null && messageValue != null) {
						codeMessageMap.put(codeValue, messageValue);
					}
					fieldNameCodeMap.put(codeName, codeValue);

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		}

	}

	public static Integer getCode(String message) {

		return codeMessageMap.keySet().stream().filter(k -> message.equalsIgnoreCase(codeMessageMap.get(k))).findFirst()
				.orElse(getAppCodeException());

	}

	public static String getMessage(Integer code) {

		return MapUtils.getString(codeMessageMap, code, "");
	}

	public static Integer getAppCodeException() {

		try {
			String[] nameArr = applicationName.split("-");
			String subCodeName = "";
			for (int i = 1; i < nameArr.length; i++) {
				if (i > 1) {
					subCodeName += "_";
				}
				subCodeName += nameArr[i];

			}
			String codeName = APP_CODE_EXCEPTON_NAME_PATTERN.replace("{}", subCodeName).toUpperCase();
			final Integer codeValue = fieldNameCodeMap.get(codeName);
			if (codeValue == null) {
				return ApiRetCodeEnum.CODE_EXCEPTION;
			}
			log.info("getAppCodeException,applicationName:{},codeName:{},codeValue:{}", applicationName, codeName,
					codeValue);
			return codeValue;
		} catch (Exception ex) {
			ex.printStackTrace();
			return ApiRetCodeEnum.CODE_EXCEPTION;
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			init();
			log.info(">>>>>>applicationName:{}", applicationName);
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("ApiRetCodeHodler init fail", ex);
		}
	}

}
