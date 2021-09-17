package com.demo.xyz.common.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class NumberUtils {

	private NumberUtils() {
	}

	private static Logger logger = LoggerFactory.getLogger(NumberUtils.class);

	/**
	 * 保留两位小数点，自动四舍五入
	 *
	 * @param money
	 * @return
	 */
	public static String formatMoney(double money) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			return decimalFormat.format(money);
		} catch (Exception e) {
			logger.error("数字格式化异常： " + e);
			return String.valueOf(money);
		}
	}

	/**
	 * 保留三位小数点，自动四舍五入
	 *
	 * @param money
	 * @return
	 */
	public static String formatMoney3(String money) {
		try {
			DecimalFormat decimalFormat = new DecimalFormat("#.###");
			float value = Float.parseFloat(money);
			return decimalFormat.format(value);
		} catch (Exception e) {
			logger.error("数字格式化异常： " + e);
			return String.valueOf(money);
		}
	}

	/**
	 * 四舍五入，保留double数值的指定位小数 浮点数的乘除一定要转换成BigDecimal后再乘除，不然回失真，切记！
	 *
	 * @param orignal 原始double值
	 * @param count   需要保留的小数位数
	 * @return
	 */
	public static double formatDouble(double orignal, int count) {
		if (count <= 0) {
			return (int) (orignal + 0.5);
		}

		BigDecimal number = new BigDecimal(orignal);
		number = number.setScale(count, BigDecimal.ROUND_HALF_UP);
		return new Double(number.toString());

//		double pow = Math.pow(10, count);
//		int temp = (int) ((orignal * pow) + 0.5);
//		return temp / pow;
	}

	/**
	 * 四舍五入，保留float数值的指定位小数;注意：浮点数的乘除一定要转换成BigDecimal后再乘除，不然回失真，切记！
	 *
	 * @param orignal 原始float值
	 * @param count   需要保留的小数位数
	 * @return
	 */
	public static float formatFloat(float orignal, int count) {
		if (count <= 0) {
			return (int) (orignal + 0.5);
		}
		BigDecimal number = new BigDecimal(orignal);
		number = number.setScale(count, BigDecimal.ROUND_HALF_UP);
		return new Float(number.toString());
//		int pow = (int) Math.pow(10, count);
//		int temp = (int) ((orignal * pow) + 0.5f);
//		return temp / (pow * 1.0f);
	}

	/**
	 * 保留指定的小数点位数，自动四舍五入
	 *
	 * @param orignal 原始的小数
	 * @param count   需要保留的小数位数
	 * @return
	 */
	public static String formatString(double orignal, int count) {

		StringBuilder sb = new StringBuilder();
		if (count <= 0) {
			sb.append("#");
		} else {
			sb.append("#.");
		}

		for (int i = 0; i < count; i++) {
			sb.append("#");
		}

		try {
			DecimalFormat decimalFormat = new DecimalFormat(sb.toString());
			return decimalFormat.format(orignal);
		} catch (Exception e) {
			logger.error("数字格式化异常： " + e);
			return String.valueOf(orignal);
		}
	}

	/**
	 * <p>
	 * Title: isZero<／p>
	 * <p>
	 * Description:判断 double 数值是否为0 <／p> @param number @return
	 */
	public static boolean isZero(double number) {
		boolean flag = false;
		BigDecimal num = BigDecimal.valueOf(number);
		if (num.compareTo(BigDecimal.ZERO) == 0) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 计算百分比
	 * 
	 * @param x     参数1
	 * @param total 参数2
	 * @return String
	 */
	public static String getPercent(int x, int total) {
		if (x <= 0 && total <= 0) {
			return "0";
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后2位
		numberFormat.setMaximumFractionDigits(1);
		return numberFormat.format((float) x / (float) total * 100);
	}

	/**
	 * 计算百分比
	 * 
	 * @param x     参数1
	 * @param total 参数2
	 * @return String
	 */
	public static String getPercent(double x, double total) {
		if (x <= 0 && total <= 0) {
			return "0";
		}
		// 创建一个数值格式化对象
		NumberFormat numberFormat = NumberFormat.getInstance();
		// 设置精确到小数点后1位
		numberFormat.setMaximumFractionDigits(1);
		return numberFormat.format((float) x / (float) total * 100);
	}

	// 將10進制轉換為16進制
	public static String encodeHEX(Integer numb) {

		String hex = Integer.toHexString(numb);
		return hex;

	}

	// 將16進制字符串轉換為10進制數字
	public static Integer decodeHEX(String hexs) {
		BigInteger bigint = new BigInteger(hexs, 16);
		int numb = bigint.intValue();
		return numb;
	}

	public static Float toFloat(String value) {

		return org.apache.commons.lang3.math.NumberUtils.toFloat(value);

	}

	public static int longToIntUniqueId(Long longVal) {

		char[] bits = Long.toBinaryString(longVal).toCharArray();
		int intVal = 0;
		for (int i = 0; i < bits.length; i++) {

			if (bits[i] == '1') {
				intVal += i;
			}

		}
		return intVal;
	}

	public static void test(String[] args) {

		float a = 2 / 60f;

		float areaFinish = 25.000332f;

		System.out.println(NumberUtils.formatFloat(areaFinish, 2));

		System.out.println(NumberUtils.formatFloat(areaFinish, 4));

		System.out.println(NumberUtils.formatFloat(areaFinish, 6));

		long minute = 2;

		BigDecimal af = new BigDecimal(areaFinish);
		BigDecimal c = new BigDecimal(minute).divide(new BigDecimal(60), 2, BigDecimal.ROUND_HALF_UP);

		BigDecimal d = af.divide(c, 2, BigDecimal.ROUND_HALF_UP);

		System.out.println(c);
		System.out.println(d);

		Float df = new Float(d.toString());

		float efficiency = areaFinish / com.demo.xyz.common.core.util.NumberUtils.formatFloat(minute / 60, 2);

		System.out.println(efficiency);

	}

}
