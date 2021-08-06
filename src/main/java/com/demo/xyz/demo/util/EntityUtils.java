package com.demo.xyz.demo.util;

import java.time.LocalDateTime;

/**
 * alex.xu
 * 实体类相关工具类 
 * 解决问题： 1、快速对实体的常驻字段，如：createdTime、updatedTime、createdBy,updatedBy等值快速注入
 */
public class EntityUtils<T> {
	
	private static String CREATE_TIME = "createTime";
	private static String UPDATE_TIME = "updateTime";
//	private static String CREATED_BY = "createdBy";
//	private static String UPDATED_BY = "updatedBy";
	
	public static <T>  void updateFields(T t, LocalDateTime createTime, LocalDateTime updateTime) {

		if(createTime != null) {
			ReflectionUtils.setFieldValue(t, CREATE_TIME, createTime);
		}
		
		
		if(updateTime != null) {
			ReflectionUtils.setFieldValue(t, UPDATE_TIME, updateTime);
		}
		
		
//		if(createdBy != null && createdBy > 0) {
//			ReflectionUtils.setFieldValue(t, CREATED_BY, createdBy);
//		}
//		
//		if(updatedBy != null && updatedBy > 0) {
//			ReflectionUtils.setFieldValue(t, UPDATED_BY, updatedBy);
//		}
	}
	
}
