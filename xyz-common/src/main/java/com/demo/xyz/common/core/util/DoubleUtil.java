package com.demo.xyz.common.core.util;

import org.apache.commons.lang.math.NumberUtils;

import java.text.DecimalFormat;

public class DoubleUtil {

	public static Double formatDouble(Double val) {
		DecimalFormat df = new DecimalFormat("0.00");

		return NumberUtils.toDouble(df.format(val));
	}

	public static Float formatFloat(Float val) {
		DecimalFormat df = new DecimalFormat("0.00");

		return NumberUtils.toFloat(df.format(val));
	}

}
