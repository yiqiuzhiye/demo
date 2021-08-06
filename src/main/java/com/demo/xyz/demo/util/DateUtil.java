package com.demo.xyz.demo.util;

import com.cloudminds.crss.common.core.constant.SignConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateUtil {

    private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public final static String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    public final static String DATE_FORMAT_2 = "yyyy/MM/dd HH:mm:ss";
    public final static String DATE_FORMAT_3 = "yyyy-MM-dd";
    public final static String DATE_FORMAT_4 = "yyyy/MM/dd";
    public final static String DATE_FORMAT_5 = "yyyy";
    public final static String DATE_FORMAT_6 = "yyyyMMddHHmmss";
    public final static String DATE_FORMAT_7 = "HH:mm";
    public final static String DATE_FORMAT_8 = "yyyy-MM-dd HH:mm";
    public final static String DATE_FORMAT_9 = "yyyy-MM-dd HH:mm:ss SSS";


    public final static String MONDAY_SHORT_NAME = "mon";
    public final static String TUESDAY_SHORT_NAME = "tues";
    public final static String WEDNESDAY_SHORT_NAME = "wed";
    public final static String THURSDAY_SHORT_NAME = "thur";
    public final static String FRIDAY_SHORT_NAME = "fri";
    public final static String SATURDAY_SHORT_NAME = "sat";
    public final static String SUNDAY_SHORT_NAME = "sun";

    public final static Map<Integer, String> WEEKDAY_SHORTNAME_MAP = new HashMap<Integer, String>();

    static {
        WEEKDAY_SHORTNAME_MAP.put(1, MONDAY_SHORT_NAME);
        WEEKDAY_SHORTNAME_MAP.put(2, TUESDAY_SHORT_NAME);
        WEEKDAY_SHORTNAME_MAP.put(3, WEDNESDAY_SHORT_NAME);
        WEEKDAY_SHORTNAME_MAP.put(4, THURSDAY_SHORT_NAME);
        WEEKDAY_SHORTNAME_MAP.put(5, FRIDAY_SHORT_NAME);
        WEEKDAY_SHORTNAME_MAP.put(6, SATURDAY_SHORT_NAME);
        WEEKDAY_SHORTNAME_MAP.put(7, SUNDAY_SHORT_NAME);
    }

    public static String getWeekDayShortName(Integer number) {
        return WEEKDAY_SHORTNAME_MAP.get(number);
    }

    public static Long getTimeStamp(String strDate) {

        DateFormat df = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            log.error("getTimeStamp({}) got Exception, error:{}", strDate, e.getMessage(), e);
            return null;
        }
        return date.getTime();

    }

    public static Date getDate(String strDate) {

        DateFormat df = new SimpleDateFormat(DATE_FORMAT_1);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;

    }

    public static Date getDate(String strDate, String pattern) {

        DateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = df.parse(strDate);
        } catch (ParseException e) {
            log.error("getDate() error:{}", e.getMessage(), e);
            return null;
        }
        return date;

    }

    public static Date getDate(String strDate, String pattern, TimeZone timeZone) {

        DateFormat df = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            df.setTimeZone(timeZone);
            date = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;

    }

    public static String getDateStr(Date date, String pattern) {

        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);

    }

    public static String getDateStr(LocalDateTime date, String pattern) {

        return LocalDateTimeUtils.formatTime(date, pattern);

    }

    public static String getDateStr(Date date, String pattern, TimeZone timeZone) {

        DateFormat df = new SimpleDateFormat(pattern);
        df.setTimeZone(timeZone);
        return df.format(date);

    }

    public static String formatShorttime(String shortTime, String pattern) {

        try {
            if (pattern == null || pattern.trim().length() == 0) {

                pattern = "HH:mm";
            }
            DateFormat df = new SimpleDateFormat(pattern);
            Date dShortTime = df.parse(shortTime);
            return df.format(dShortTime);
        } catch (Exception ex) {
            logger.error("Format shortTime:{} exception.", shortTime, ex);
            return null;

        }

    }

    public static Date getShortDate(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public static boolean isWorkday(Date date) {

        int workday = getWorkday(date);

        return (workday >= 1 && workday <= 5);
    }

    public static int getWorkday(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int workday = calendar.get(Calendar.DAY_OF_WEEK);
        if (workday == 1) {
            workday = 7;
        } else {
            workday = workday - 1;
        }

        return workday;

    }


    public static int getWorkday(LocalDateTime dateTime) {

        return getWorkday(LocalDateTimeUtils.convertLDTToDate(dateTime));
    }

    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    public static Date getFormatDate(Date date, String pattern) {
        String dateStr = getDateStr(date, pattern);
        return getDate(dateStr, pattern);
    }

    public static Date concatDates(Date src1, String p1, Date src2, String p2, String pattern) {

        String newDateStr = DateUtil.getDateStr(src1, p1) + " " + DateUtil.getDateStr(src2, p2);
        return DateUtil.getDate(newDateStr, pattern);

    }

    /**
     * 是否是单周
     *
     * @param beginDate 起始时间为基准计算
     * @param calDate   需要计算的时间
     * @return
     */
    public static boolean isOddWeek(Date beginDate, Date calDate) {
        beginDate = DateUtil.getFormatDate(beginDate, "yyyy-MM-dd");
        calDate = DateUtil.getFormatDate(calDate, "yyyy-MM-dd");
        int workday = getWorkday(beginDate);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginDate);
        calendar.add(Calendar.DAY_OF_MONTH, (workday - 1) * -1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(calDate);
        int distance = calendar2.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
        logger.info("beginDate:{},calDate:{} return distance:{}", beginDate, calDate, distance);
        int retval = distance / 7;
        return retval % 2 == 0;

    }

    public static boolean isEvenWeek(Date beginDate, Date calDate) {

        return isOddWeek(beginDate, calDate);

    }

    public static Date getWeekStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return calendar.getTime();

    }

    public static Date getWeekEndDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return calendar.getTime();

    }

    /**
     * 两个时间相减
     *
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @param timeUnit  要返回时间单位
     * @return 时间差值
     */
    public static long sub(Date endTime, Date startTime, TimeUnit timeUnit) {
        // 微秒
        long diff = endTime.getTime() - startTime.getTime();
        switch (timeUnit) {
            case MICROSECONDS:
                return diff;
            case SECONDS:
                return diff / 1000;
            case MINUTES:
                return diff / (1000 * 60);
            case HOURS:
                return diff / (1000 * 60 * 60);
            case DAYS:
                return diff / (1000 * 60 * 60 * 24);
            default:
                return 0;
        }
    }

    /**
     * 两个时间相减
     *
     * @param startTime 起始时间
     * @param endTime   结束时间
     * @param timeUnit  要返回时间单位
     * @return 时间差值
     */
    public static long sub(LocalDateTime startTime, LocalDateTime endTime, TimeUnit timeUnit) {
        Duration duration = Duration.between(startTime, endTime);
        switch (timeUnit) {
            case MICROSECONDS:
                return duration.toMillis();
            case SECONDS:
                return duration.getSeconds();
            case MINUTES:
                return duration.toMinutes();
            case HOURS:
                return duration.toHours();
            case DAYS:
                return duration.toDays();
            default:
                return 0;
        }
    }


    /**
     * 一天的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static final Date dateBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static final LocalDateTime localDateTimeBegin(Date date) {

        Date dateBegin = DateUtil.dateBegin(date);

        if (dateBegin != null) {
            return LocalDateTimeUtils.convertDateToLDT(dateBegin);
        }
        return null;

    }

    /**
     * 一天的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static final LocalDateTime localDateTimeEnd(Date date) {
        Date dateEnd = DateUtil.dateEnd(date);

        if (dateEnd != null) {
            return LocalDateTimeUtils.convertDateToLDT(dateEnd);
        }
        return null;

    }

    /**
     * 一天的结束时间
     *
     * @param date 日期
     * @return Date
     */
    public static final Date dateEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     *
     * @param calendar 日期
     * @return Calendar
     */
    public static final Calendar dateEnd(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }

    /**
     * 一天的开始时间
     *
     * @param calendar 日期
     * @return Calendar
     */
    public static final Calendar dateBegin(Calendar calendar) {
        if (calendar == null) {
            return null;
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }


    /**
     * Description: 一天的开始时间<br>
     *
     * @param year
     * @param month
     * @param date
     * @return
     * @see
     */
    public static final Date dateBegin(Integer year, Integer month, Integer date) {
        if (year == null) {
            return null;
        }

        if (month == null) {
            month = 0; // 0代表1月份
        } else {
            month--;
        }

        if (date == null) {
            date = 1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    /**
     * 一天的结束时间
     *
     * @param year
     * @param month
     * @param date
     * @return
     * @see
     */
    public static final Date dateEnd(Integer year, Integer month, Integer date) {
        if (year == null) {
            return null;
        }

        if (month == null) {
            month = 0;
        } else {
            month--;
        }

        if (date == null) {
            date = 1;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(year, month, date);
        return calendar.getTime();
    }

    /**
     * 一月的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static final Date monthBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateBegin(calendar);
        return calendar.getTime();
    }

    public static final int getMonthsBeginDate(Date date) {
        if (date == null) {
            return 1;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 一月的结束时间
     *
     * @param date 日期
     * @return Date
     */
    public static final Date monthEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }


    public static final Date monthEnd(int year, int month) {

        StringBuilder sb = new StringBuilder();
        sb.append(year);
        sb.append(SignConstants.SIGN_DASH);
        String mon = "";
        if (month < 10) {
            mon = "0" + month;
        } else {
            mon = String.valueOf(month);
        }
        sb.append(mon);
        sb.append("-01");
        Date date = DateUtil.getDate(sb.toString(), DateUtil.DATE_FORMAT_3);
        return monthEnd(date);
    }

    public static final int getMonthsEndDate(Date date) {
        if (date == null) {
            return 31;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 一年的开始时间
     *
     * @param date 日期
     * @return Date
     */
    public static final Date yearBegin(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.getActualMinimum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, month);
        dateBegin(calendar);
        return calendar.getTime();
    }

    /**
     * 一年的结束时间
     *
     * @param date 日期
     * @return Date
     */
    public static final Date yearEnd(Date date) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int day = calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
        calendar.set(Calendar.DATE, day);
        dateEnd(calendar);
        return calendar.getTime();
    }

    /**
     * 获取当天日期的下一天日期
     *
     * @param date 日期
     * @param days 追加的天数
     * @return Date
     */
    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        return calendar.getTime();
    }

    public static int compareTimeString(String hhmmEnd, String hhmmStart) {
        if (StringUtils.isEmpty(hhmmStart)) {
            hhmmStart = "00:00";
        }
        if (StringUtils.isEmpty(hhmmEnd)) {
            hhmmEnd = "00:00";
        }
        Date curDate = new Date();
        String dateString = DateUtil.getDateStr(curDate, DateUtil.DATE_FORMAT_3);

        String date1 = dateString + " " + hhmmStart;

        String date2 = dateString + " " + hhmmEnd;

        Date dStart = DateUtil.getDate(date1, DateUtil.DATE_FORMAT_8);
        Date dEnd = DateUtil.getDate(date2, DateUtil.DATE_FORMAT_8);

        long gap = DateUtil.sub(dEnd, dStart, TimeUnit.SECONDS);
        int result = 0;
        if (gap > 0) {
            result = 1;
        } else if (gap < 0) {
            result = -1;
        }
        return result;
    }


    public static void main(String[] args) {


        Date wkStartDate = DateUtil.getWeekStartDate();
        Date wkEndDate = DateUtil.getWeekEndDate();

        System.out.println(wkStartDate + " " + wkEndDate);
        if (Boolean.TRUE) {
            return;
        }

        Date dateStart = DateUtil.getWeekStartDate();
        System.out.println(DateUtil.getWorkday(dateStart)); //1 周一

        Date dateEnd = DateUtil.getWeekEndDate();
        System.out.println(DateUtil.getWorkday(dateEnd)); // 7 周日
        System.out.println("dateStart:" + dateStart + ",dateEnd:" + dateEnd);

        Date curDate = new Date();
        Date startDate = DateUtil.getDate("2019-12-06 14:01:01", DateUtil.DATE_FORMAT_1);
        Date endDate = DateUtil.getDate("2019-12-06 15:59:05", DateUtil.DATE_FORMAT_1);

        Float f1 = 0F;
        Float f2 = 0.00F;

        Float f3 = 1281.0F;
        Float f4 = 1279.0F;
        Float timeRate = NumberUtils.formatFloat((f3 - f4) / f4, 3); //还是不是四舍五入
        System.out.println(timeRate);
        long hours = DateUtil.sub(LocalDateTimeUtils.convertDateToLDT(startDate), LocalDateTimeUtils.convertDateToLDT(endDate), TimeUnit.HOURS);
        System.out.println(hours);
        System.out.println(f1.compareTo(f2));
        System.out.println(LocalDateTimeUtils.convertDateToLDT(startDate).compareTo(LocalDateTimeUtils.convertDateToLDT(endDate)));
    }

    /**
     * LocalDateTime转Date
     *
     * @param LocalDateTime
     * @return Date
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date getTenantTZDate(Date date, String dateFormate) {

        if (date == null || StringUtils.isEmpty(dateFormate)) {
            return null;
        }

        String dateStr = DateUtil.getDateStr(date, dateFormate);

        //获取租户所在timeZone
        String tenantTimeZoneId = TimeZoneUtil.TIME_ZONE_SHANGHAI;
        String df = dateFormate;

        String tenantTZDateStr = TimeZoneUtil.string2Timezone(df, dateStr, df, tenantTimeZoneId);

        return DateUtil.getDate(tenantTZDateStr, df);
    }

    /**
     * 获取N月前的时间戳
     *
     * @return
     */
    public static Long getTimeStampForMonth(Integer n) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, n);
        Date date = c.getTime();
        String formatDate = format.format(date);
        return getTimeStamp(formatDate);
    }
}
