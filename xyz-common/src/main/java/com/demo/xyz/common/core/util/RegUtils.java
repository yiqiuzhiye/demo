package com.demo.xyz.common.core.util;

import java.util.regex.Pattern;

public class RegUtils {

	public static final String REGEX_URL = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?/{0,1}$";

	public static final String REGEX_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	public static final String REGEX_PHONE = "^(\\d{3}\\+)?(\\d{3,4}-?)?\\d{7,9}$";

	public static final String REGEX_MOBILE = "^(\\+\\d{2,3}\\-)?\\d{11}$";

	public static boolean isValid(String content, String regex) {

		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		if (pattern.matcher(content).matches()) {
			return true;
		}

		return false;
	}

}
