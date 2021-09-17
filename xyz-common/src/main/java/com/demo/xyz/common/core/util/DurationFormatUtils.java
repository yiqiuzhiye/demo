package com.demo.xyz.common.core.util;

public class DurationFormatUtils {
	/**
	 * 将时长以秒为单位的格式化为HH:mm:ss格式
	 * 
	 * @param scdDuration
	 *            单位秒(s)
	 * @return
	 */
	public static String formatHHmmss(int scdDuration) {

		int hours = scdDuration / 3600;
		int minutes = (scdDuration - hours * 3600) / 60;
		int seconds = scdDuration - hours * 3600 - minutes * 60;
		StringBuilder sb = new StringBuilder();
		if (hours < 10) {
			sb.append("0");
		}
		sb.append(hours);
		sb.append(":");

		if (minutes < 10) {
			sb.append("0");
		}
		sb.append(minutes);
		sb.append(":");
		if (seconds < 10) {
			sb.append("0");
		}

		sb.append(seconds);

		return sb.toString();
	}

	/**
	 * 将分钟数转换为可读的天 小时 分钟
	 * 
	 * @param scdDuration
	 *            单位秒(s)
	 * @return
	 */
	public static String formatDdhhmm(int minDuration) {

		int day = (minDuration / 60 / 24);
		int hour = minDuration / 60 % 24;
		int min = minDuration % 60;
		StringBuilder sb = new StringBuilder("");
		if (day > 0) {
			sb.append(day + "天");
		}
		if (hour > 0) {
			sb.append(hour + "小时");
		}
		if (min > 0) {
			sb.append(min + "分钟");
		}

		return sb.toString();
	}

}
