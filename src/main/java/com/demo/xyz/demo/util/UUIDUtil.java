package com.demo.xyz.demo.util;

import java.util.UUID;

/**
 * uuid生成器
 * 
 * @author charles
 *
 */
public class UUIDUtil {

	public static String generateUUID() {

		String uuid = UUID.randomUUID().toString().toLowerCase();

		return uuid.replaceAll("-", "");

	}

}
