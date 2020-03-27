package org.loushang.framework.util;

import java.util.Date;

import org.joda.time.DateTime;

public class DateUtil {

	private final static String DAY_FORMAT_EXPR = "yyyyMMdd";
	private final static String MONTH_FORMAT_EXPR = "yyyyMM";
	private final static String YEAR_FORMAT_EXPR = "yyyy";

	/**
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentTime() {
		return getCurrentTime("yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @param format
	 * @return 返回当前日期时间
	 */
	public static String getCurrentTime(String format) {
		return new DateTime(new Date()).toString(format);
	}

	/**
	 * @return 时间戳
	 */
	public static long getTimestamp() {
		return new Date().getTime();
	}

	/**
	 * @return 默认返回yyyyMMdd格式
	 */
	public static String getToday() {
		return getToday(DAY_FORMAT_EXPR);
	}

	/**
	 * @param format
	 * @return 返回当前日期
	 */
	public static String getToday(String format) {
		return new DateTime(new Date()).toString(format);
	}

	/**
	 * @return 返回明天的日期
	 */
	public static String getTomorrow() {
		return getTomorrow(DAY_FORMAT_EXPR);
	}

	/**
	 * @param format
	 * @return 返回明天的日期支持格式化
	 */
	public static String getTomorrow(String format) {
		return new DateTime(new Date()).plusDays(1).toString(format);
	}

	/**
	 * @return 返回昨天的日期
	 */
	public static String getYesterday() {
		return getYesterday(DAY_FORMAT_EXPR);
	}

	/**
	 * @return 返回昨天的日期支持格式化
	 */
	public static String getYesterday(String format) {
		return new DateTime(new Date()).minusDays(1).toString(format);
	}

	/**
	 * @param day
	 * @return 返回后一天 yyyyMMdd
	 */
	public static String getNextDay(String day) {
		return getNextDay(day, 1);
	}

	/**
	 * @param day
	 * @param step
	 * @return 返回指定日期之后几天的日期 yyyyMMdd
	 */
	public static String getNextDay(String day, int step) {
		return DateTime.parse(day).plusDays(step).toString(DAY_FORMAT_EXPR);
	}

	/**
	 * @param day
	 * @return 返回前一天 yyyyMMdd
	 */
	public static String getPreviousDay(String day) {
		return getPreviousDay(day, 1);
	}

	/**
	 * @param day
	 * @param step
	 * @return 返回指定日期前几日的日期yyyyMMdd
	 */
	public static String getPreviousDay(String day, int step) {
		return DateTime.parse(day).minusDays(step).toString(DAY_FORMAT_EXPR);
	}

	/**
	 * @param date
	 * @return 返回当前日期的下一个月 yyyyMM
	 */
	public static String getNextMonth(String date) {
		return getNextMonth(date, 1);
	}

	public static String getNextMonth(String date, int step) {
		return DateTime.parse(date).plusMonths(step).toString(MONTH_FORMAT_EXPR);
	}

	public static String getPreviousMonth(String date) {
		return getPreviousMonth(date, 1);
	}

	public static String getPreviousMonth(String date, int step) {
		return DateTime.parse(date).minusMonths(step).toString(MONTH_FORMAT_EXPR);
	}

	public static String getNextYear(String date) {
		return getNextYear(date, 1);
	}

	public static String getNextYear(String date, int step) {
		return DateTime.parse(date).plusYears(step).toString(YEAR_FORMAT_EXPR);
	}

	public static String getPreviousYear(String date) {
		return getPreviousYear(date, 1);
	}

	public static String getPreviousYear(String date, int step) {
		return DateTime.parse(date).minusYears(step).toString(YEAR_FORMAT_EXPR);
	}

}
