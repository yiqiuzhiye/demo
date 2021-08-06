package com.demo.xyz.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimeZone;

/*
 * @author kingboy
 * @Date 2017/7/22 下午2:12
 * @Description LocalDateTimeUtils is used to Java8中的时间类
 * https://blog.csdn.net/kingboyworld/article/details/75808108
 * 
 */
@Slf4j
public class LocalDateTimeUtils {

	/**
	 * 一天的毫秒数
	 */
	public static final long DAY_MS = 1000 * 60 * 60 * 24;

	// 获取当前时间的LocalDateTime对象
	// LocalDateTime.now();

	// 根据年月日构建LocalDateTime
	// LocalDateTime.of();

	// 比较日期先后
	// LocalDateTime.now().isBefore(),
	// LocalDateTime.now().isAfter(),

	// Date转换为LocalDateTime
	public static LocalDateTime convertDateToLDT(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	// LocalDateTime转换为Date
	public static Date convertLDTToDate(LocalDateTime time) {
		return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
	}

	// 获取指定日期的毫秒
	public static Long getMilliByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	// 获取指定日期的秒
	public static Long getSecondsByTime(LocalDateTime time) {
		return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
	}

	// 获取指定时间的指定格式
	public static String formatTime(LocalDateTime time, String pattern) {
		return time.format(DateTimeFormatter.ofPattern(pattern));
	}

	// 获取当前时间的指定格式
	public static String formatNow(String pattern) {
		return formatTime(LocalDateTime.now(), pattern);
	}

	// 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
	public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
		return time.plus(number, field);
	}

	// 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
	public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
		return time.minus(number, field);
	}

	/**
	 * 获取两个日期的差 field参数为ChronoUnit.*
	 * 
	 * @param startTime
	 * @param endTime
	 * @param field     单位(年月日时分秒)
	 * @return
	 */
	public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
		Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
		if (field == ChronoUnit.YEARS)
			return period.getYears();
		if (field == ChronoUnit.MONTHS)
			return period.getYears() * 12 + period.getMonths();
		return field.between(startTime, endTime);
	}

	// 获取一天的开始时间，2017,7,22 00:00
	public static LocalDateTime getDayStart(LocalDateTime time) {
		return time.withHour(0).withMinute(0).withSecond(0).withNano(0);
	}

	// 获取一天的结束时间，2017,7,22 23:59:59.999999999
	public static LocalDateTime getDayEnd(LocalDateTime time) {
		return time.withHour(23).withMinute(59).withSecond(59).withNano(999999999);
	}

	// 时间戳 转换为 LocalDateTime
	public static LocalDateTime getDateTimeOfTimestamp(long timestamp) {
		Instant instant = Instant.ofEpochMilli(timestamp);
		ZoneId zone = ZoneId.systemDefault();
		return LocalDateTime.ofInstant(instant, zone);
	}

	public static boolean isToday(LocalDateTime dateTime) {

		if (dateTime == null) {
			return false;
		}
		Date curDate = new Date();
		// 时区要取自于租户的时区，这个要等到Charlse那边把租户的时区配置好了，再修改代码从租户那获取。
		String tenantTimeZoneId = TimeZoneUtil.TIME_ZONE_SHANGHAI;
		String srcFormater = DateUtil.DATE_FORMAT_1;
		String srcDateTimeStr = DateUtil.getDateStr(curDate, srcFormater);
		String todayStrTenantTZ = TimeZoneUtil.string2Timezone(srcFormater, srcDateTimeStr, DateUtil.DATE_FORMAT_3,
				tenantTimeZoneId);

		String dateTimeStr = LocalDateTimeUtils.formatTime(dateTime, DateUtil.DATE_FORMAT_3);

		if (todayStrTenantTZ.equalsIgnoreCase(dateTimeStr)) {
			return true;
		}
		return false;
	}

	/**
	 * 计算租户的一天的开始时间
	 * 
	 * @param date
	 * @param offset 时区分钟数，可以为正负偏移
	 * @return
	 */
	public static LocalDateTime getTenantTZDateBigin(LocalDateTime date, int offset) {

		LocalDateTime date2 = date.plusMinutes(offset);
		return getDayStart(date2);

	}

	/**
	 * 计算租户的一天的开始时间
	 * 
	 * @param date
	 * @param offset 时区分钟数，可以为正负偏移
	 * @return
	 */
	public static LocalDateTime getTenantDateTime(LocalDateTime date, int offset) {

		LocalDateTime date2 = date.plusMinutes(offset);
		return date2;

	}

	// userDateStr 必须是 formate:yyyy-MM-DD
	public static LocalDateTime getTenantTZDateEnd(LocalDateTime date, int offset) {

		LocalDateTime date2 = date.plusMinutes(offset);
		return getDayEnd(date2);

	}

	// userDateStr 必须是 formate:yyyy-MM-DD
	public static LocalDateTime getDefaultTZDateBiginBasedUserDate(String userDateStr) {

		if (StringUtils.isEmpty(userDateStr)) {
			return null;
		}

		String userDateBeginStr = userDateStr + " 00:00:00";

		// 获取系统默认timeZone
		String systemTimeZoneId = TimeZone.getDefault().getID();

		log.info("getDefaultTZDateBiginBasedUserDate(), systemTimeZoneId:{}  ", systemTimeZoneId);
		String df = DateUtil.DATE_FORMAT_1;

		String defaultTZDateBeginStr = TimeZoneUtil.string2Timezone(df, userDateBeginStr, df, systemTimeZoneId);

		Date defaultTZDateBegin = DateUtil.getDate(defaultTZDateBeginStr, df);
		LocalDateTime defaultTZDateTimeBegin = LocalDateTimeUtils.convertDateToLDT(defaultTZDateBegin);

		log.info("getDefaultTZDateBiginBasedUserDate({}), defaultTZDateTimeBegin:{}  ", userDateStr,
				defaultTZDateTimeBegin);
		return defaultTZDateTimeBegin;

	}

	// userDateStr 必须是 formate:yyyy-MM-DD
	public static LocalDateTime getDefaultTZDateEndBasedUserDate(String userDateStr) {

		if (StringUtils.isEmpty(userDateStr)) {
			return null;
		}

		String userDateEndStr = userDateStr + " 23:59:59";

		// 获取系统默认timeZone
		String systemTimeZoneId = TimeZone.getDefault().getID();
		log.info("getDefaultTZDateEndBasedUserDate(), systemTimeZoneId:{}  ", systemTimeZoneId);
		String df = DateUtil.DATE_FORMAT_1;

		String defaultTZDateEndStr = TimeZoneUtil.string2Timezone(df, userDateEndStr, df, systemTimeZoneId);

		Date defaultTZDateEnd = DateUtil.getDate(defaultTZDateEndStr, df);
		LocalDateTime defaultTZDateTimeEnd = LocalDateTimeUtils.convertDateToLDT(defaultTZDateEnd);
		log.info("getDefaultTZDateEndBasedUserDate({}), defaultTZDateTimeEnd:{}  ", userDateStr, defaultTZDateTimeEnd);
		return defaultTZDateTimeEnd;

	}

	public static LocalDateTime convertToTenantTZDate(String dateStr, String srcPattern) {

		if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(srcPattern)) {
			return null;
		}

		// 获取租户默认timeZone
		String tenantTimeZoneId = TimeZoneUtil.TIME_ZONE_SHANGHAI;
		String tenantTZDateStr = TimeZoneUtil.string2Timezone(srcPattern, dateStr, srcPattern, tenantTimeZoneId);

		Date tenantTZDate = DateUtil.getDate(tenantTZDateStr, srcPattern);
		return LocalDateTimeUtils.convertDateToLDT(tenantTZDate);

	}

	public static LocalDateTime stringToLocalDateTime(String dateStr, String srcPattern) {

		if (StringUtils.isEmpty(dateStr) || StringUtils.isEmpty(srcPattern)) {
			return null;
		}

		DateTimeFormatter fomatterl = DateTimeFormatter.ofPattern(srcPattern);
		return LocalDateTime.parse(dateStr, fomatterl);

	}

	public static LocalDateTime convertToTenantTZDate(Date date, String srcPattern) {

		if (date == null || StringUtils.isEmpty(srcPattern)) {
			return null;
		}

		String dateStr = DateUtil.getDateStr(date, srcPattern);
		// 获取租户默认timeZone
		return LocalDateTimeUtils.convertToTenantTZDate(dateStr, srcPattern);

	}

	/**
	 * 给定Unix时间戳，按天数进行偏移
	 *
	 * @param time   给定Unix时间戳
	 * @param offset 偏移量
	 * @return 偏移后的Unix时间戳
	 */
	public static long modifiyDayOfMonth(long time, int offset) {
		long millisecond = 1000 * 60 * 60 * 24;
		return offset * millisecond + time;
	}

	// userDateStr 必须是 formate:yyyy-MM-DD
	// 废弃该方法，实际请参考 getTenantTZDateBigin
	@Deprecated
	public static LocalDateTime getTenantTZDateBigin(Date date, String dateFormate) {

		if (date == null || StringUtils.isEmpty(dateFormate)) {
			return null;
		}

		String dateStr = DateUtil.getDateStr(date, dateFormate);

		String tenantTimeZoneId = TimeZoneUtil.TIME_ZONE_SHANGHAI;
		String df = dateFormate;

		String tenantTZDateStr = TimeZoneUtil.string2Timezone(df, dateStr, df, tenantTimeZoneId);

		Date tenantTZDateBegin = DateUtil.dateBegin(DateUtil.getDate(tenantTZDateStr, df));
		LocalDateTime tenantTZDateTimeBegin = LocalDateTimeUtils.convertDateToLDT(tenantTZDateBegin);

		log.info("tenantTZDateTimeBegin({}, {}), tenantTZDateTimeBegin:{}  ", date, dateFormate, tenantTZDateTimeBegin);
		return tenantTZDateTimeBegin;

	}

	// userDateStr 必须是 formate:yyyy-MM-DD
	// 废弃该方法，写法过于复杂，实际请使用@see LocalDateTimeUtils.getTenantTZDateBigin
	@Deprecated
	public static LocalDateTime getTenantTZDateEnd(Date date, String dateFormate) {

		if (date == null || StringUtils.isEmpty(dateFormate)) {
			return null;
		}
		String dateStr = DateUtil.getDateStr(date, dateFormate);

		// 获取系统默认timeZone
		String tenantTimeZoneId = TimeZoneUtil.TIME_ZONE_SHANGHAI;
		String df = dateFormate;

		String tenantTZDateStr = TimeZoneUtil.string2Timezone(df, dateStr, df, tenantTimeZoneId);

		Date tenantTZDateEnd = DateUtil.dateEnd(DateUtil.getDate(tenantTZDateStr, df));
		LocalDateTime tenantTZDateTimeEnd = LocalDateTimeUtils.convertDateToLDT(tenantTZDateEnd);

		log.info("getTenantTZDateEnd({}, {}), tenantTZDateTimeEnd:{}  ", date, dateFormate, tenantTZDateTimeEnd);
		return tenantTZDateTimeEnd;

	}

	/**
	 * 检查任务计划时间是否晚于当前时间
	 * 
	 * @param scheduleTime
	 */
	public static boolean checkTimeLaterThanNow(String scheduleTime) {
		Date curDate = new Date();
		// 时区要取自于租户的时区，这个要等到Charlse那边把租户的时区配置好了，再修改代码从租户那获取。
		String tenantTimeZoneId = TimeZoneUtil.TIME_ZONE_SHANGHAI;
		String srcFormater = DateUtil.DATE_FORMAT_1;
		String srcDateTime = DateUtil.getDateStr(curDate, srcFormater);
		String currentTimeStrTenantTZ = TimeZoneUtil.string2Timezone(srcFormater, srcDateTime, srcFormater,
				tenantTimeZoneId);

		Date currentDateTimeStrTenantTZ = DateUtil.getDate(currentTimeStrTenantTZ, srcFormater);
		String curHHmm = DateUtil.getDateStr(currentDateTimeStrTenantTZ, DateUtil.DATE_FORMAT_7);

		int gap = DateUtil.compareTimeString(curHHmm, scheduleTime);
		if (gap < 0) {
			return true;
		} else {
			return false;
		}
	}
}
