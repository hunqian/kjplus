package org.ybk.basic.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 * Title: 提供一些操作日期的静态函数
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c)
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version
 */

public final class DateUtil {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String DEFAULT_TIME_PATTERN = "HH:mm:ss";
	public static final String ZH_TIME = "yyyy年MM月dd日  HH:mm:ss";
	public static final String DATE_PATTERN_DAY = "yyyy-MM-dd";
	public static final String DATE_PATTERN_SHORT_DAY = "MM-dd";

	public static final String DATETIME_PATTERN_SHORT_TIME = "HH:mm";

	private static final int ONE_DAY_INSEC = 24 * 60 * 60;
	private static final int ONE_HOUR_INSEC = 60 * 60;

	private static final int MONTH_DAY_NUM[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// 判断日期是否正确
	public static boolean isExistDate(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		return cal.get(Calendar.DATE) == dd && cal.get(Calendar.MONTH) + 1 == mm && cal.get(Calendar.YEAR) == yyyy;
	}

	public static Date toDate(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		return cal.getTime();
	}

	public static String formatDate(Date value) {
		return formatDate(value, DEFAULT_DATE_PATTERN);
	}

	// 根据时间描述获得时间时间显示
	public static String formatDate(int timeInSec) {
		if (timeInSec < 0)
			return "";
		// 转换成milisec
		long time = timeInSec * (long) 1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		return formatDate(calc.getTime(), DEFAULT_DATE_PATTERN);
	}

	public static String formatShortDay(Date value) {
		return formatDate(value, DATE_PATTERN_SHORT_DAY);
	}

	public static String formatDay(Date value) {
		return formatDate(value, DATE_PATTERN_DAY);
	}

	public static String formatTime(long timeInSec) {
		if (timeInSec < 0)
			return "";
		// 转换成milisec
		long time = timeInSec * (long) 1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		return sdf.format(calc.getTime());
	}

	public static String formatTime(Date value) {
		if (value == null)
			return "";
		// 转换成milisec
		Calendar calc = Calendar.getInstance();
		calc.setTime(value);
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		return sdf.format(calc.getTime());
	}

	public static String formatShortTime(long timeInSec) {
		if (timeInSec < 0)
			return "";
		// 转换成milisec
		long time = timeInSec * (long) 1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		String vl = sdf.format(calc.getTime());
		if (vl.length() > 16)
			return vl.substring(5, 16);
		else
			return vl;
	}

	public static String formatShortTime(Date value) {
		if (value == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		String vl = sdf.format(value);
		if (vl.length() > 16)
			return vl.substring(5, 16);
		else
			return vl;
	}

	public static String formatVeryShortTime(Date value) {
		return formatDate(value, DATETIME_PATTERN_SHORT_TIME);
	}

	// 时间格式化
	public static String timeStampformatString(Timestamp value) {
		SimpleDateFormat sdf = new SimpleDateFormat(ZH_TIME);
		return sdf.format(value);
	}

	// 根据时间描述获得时间时间显示
	public static String formatDateTime(int timeInSec) {
		if (timeInSec <= 0)
			return "";
		// 转换成milisec
		long time = timeInSec * (long) 1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		return formatDate(calc.getTime(), DEFAULT_DATETIME_PATTERN);
	}

	// 根据时间描述获得时间时间显示,
	// 短格式,如果是当天，只返回时分秒，否则返回年月日
	public static String formatShortDateTime(long timeInSec) {
		if (timeInSec < 0)
			return "";
		// 转换成milisec
		long time = timeInSec * (long) 1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		String timeStr = formatDate(calc.getTime(), DEFAULT_DATETIME_PATTERN);
		String curday = Util.currDay();
		if (Util.isNull(timeStr))
			return timeStr;
		if (!timeStr.startsWith(curday))
			return timeStr.substring(0, 10);
		else
			return timeStr.substring(11);
	}

	// 根据时间描述获得时间时间显示,
	// 短格式,如果是当天，只返回时分，否则返回月日，不包含年
	public static String formatVeryShortDateTime(Date date) {
		if (date == null)
			return "";
		String timeStr = formatDate(date, DEFAULT_DATETIME_PATTERN);
		String curday = Util.currDay();
		if (Util.isNull(timeStr))
			return timeStr;
		if (!timeStr.startsWith(curday))
			return timeStr.substring(5, 10);
		else
			return timeStr.substring(11, 16);
	}

	// 根据时间描述获得时间时间显示,
	// 短格式,如果是当天，只返回时分，否则返回月日，不包含年
	public static String formatShortDateTime(Date date) {
		if (date == null)
			return "";
		String timeStr = formatDate(date, DEFAULT_DATETIME_PATTERN);
		String curday = Util.currDay();
		if (Util.isNull(timeStr))
			return timeStr;
		if (!timeStr.startsWith(curday))
			return timeStr.substring(0, 10);
		else
			return timeStr.substring(11);
	}

	// 根据时间描述获得时间时间显示,
	// 短格式,如果是当天，只返回时分，否则返回月日，不包含年
	public static String formatVeryShortDateTime(long timeInSec) {
		if (timeInSec < 0)
			return "";
		// 转换成milisec
		long time = timeInSec * (long) 1000;
		String timeStr = formatDate(new Date(time), DEFAULT_DATETIME_PATTERN);
		String curday = Util.currDay();
		if (Util.isNull(timeStr))
			return timeStr;
		if (!timeStr.startsWith(curday))
			return timeStr.substring(5, 10);
		else
			return timeStr.substring(11, 16);
	}

	public static String formatDateTime(Date value) {
		if (value == null)
			return "";
		return formatDate(value, DEFAULT_DATETIME_PATTERN);
	}

	public static String formatDate(Date value, String pattern) {
		if (value == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return value == null ? "" : sdf.format(value);
	}

	public static Date toDate(String value) {
		return toDate(value, DEFAULT_DATE_PATTERN);
	}

	public static Date toDateTime(String value) {
		return toDate(value, DEFAULT_DATETIME_PATTERN);
	}

	public static Date toDate(String value, String pattern) {
		if (value == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(value);
		} catch (ParseException ex) {
		}
		return date;
	}

	public static String lastOneMinute() {
		Calendar ca = Calendar.getInstance();
		ca.add(Calendar.MINUTE, -1);
		Date newdate = ca.getTime();
		String date = formatDateTime(newdate);
		return date;
	}

	public static Timestamp newTime() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static String convertDate(Timestamp t) {
		String msg = null;
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long between = (System.currentTimeMillis() - t.getTime()) / 1000;// 除以1000是为了转换成秒
		long year = between / (24 * 3600 * 365);
		long month = between / (24 * 3600) / 30;
		long day1 = between / (24 * 3600);
		long hour1 = between % (24 * 3600) / 3600;
		long minute1 = between % 3600 / 60;
		long second1 = between % 60 / 60;
		if (year > 0) {
			msg = year + "年前";
		} else if (month > 0) {
			msg = month + "月前";
		} else if (day1 > 0) {
			msg = day1 + "天前";
		} else if (hour1 > 0) {
			msg = hour1 + "小时前";
		} else if (minute1 > 0) {
			msg = minute1 + "分钟前";
		} else {
			msg = "刚刚";
		}
		return msg;
	}

	// 判断是否闰年
	private static boolean isLeapYear(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
			return true;
		else
			return false;
	}

	// 2014-2
	public static List<String> getDayList(int year, int month) {
		List<String> days = new ArrayList<String>();
		if (month <= 0 || month > 12)
			return days;
		if (year < 0)
			return days;
		int dayNum = MONTH_DAY_NUM[month - 1];
		// 判断闰月
		if (month == 2) {
			boolean leapYear = isLeapYear(year);
			if (leapYear)
				dayNum = 29;
		}
		String dayStr = null;
		for (int i = 1; i <= dayNum; i++) {
			if (i < 10)
				dayStr = "0" + i;
			else
				dayStr = Integer.toString(i);
			if (month < 10)
				days.add(year + "-0" + month + "-" + dayStr);
			else
				days.add(year + "-" + month + "-" + dayStr);
		}
		return days;
	}

	// 获得时间对于的分钟数量,01:22返回88
	public static int getTimeInMinute(String minuteStr) {
		if (Util.isNull(minuteStr))
			return -1;
		int p = minuteStr.indexOf(":");
		if (p < 0)
			return -1;
		int totalMinute = 0;
		String hourStr = minuteStr.substring(0, p);
		String minStr = minuteStr.substring(p + 1);
		try {
			totalMinute += Integer.parseInt(hourStr) * 60;
		} catch (NumberFormatException nfe) {
			return -1;
		}
		try {
			totalMinute += Integer.parseInt(minStr);
		} catch (NumberFormatException nfe) {
			return -1;
		}
		return totalMinute;
	}

	// 获得当前时间，以秒为单位
	public static int getCurTimeInSec() {
		return (int) (System.currentTimeMillis() / 1000);
	}

	// 获得当天的0时秒数
	public static int getCurDayBeginInSec() {
		String curDay = currDayOffset(0) + " 00:00:00";
		return getTimeInSec(curDay);
	}
		
	// 获得时间的秒显示
	public static int getTimeInSec(Date time) {
		if (time == null)
			return 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	public static int getTimeInSec(String time) {
		if (Util.isNull(time))
			return 0;
		Date timeDate = DateUtil.parseTimeStr(time);
		return getTimeInSec(timeDate);
	}

	// 获得当前时间，以秒为单位
	public static int getLongTimeInSec(long time) {
		return (int) (time / 1000);
	}

	// 获得当前年
	public static int getCurYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	// 获得当前月
	public static int getCurMonth() {
		return Calendar.getInstance().get(Calendar.MONTH) + 1;
	}

	/**
	 * 将特定的日期转化为秒数 时间格式为：2015-05-21 09:00:00
	 * 
	 * @param time
	 * @return long类型
	 */
	public static int getDateInSecond(String time) {
		Date date = Util.parseDateStr(time);
		Calendar calc = Calendar.getInstance();
		calc.setTime(date);
		return (int) (calc.getTimeInMillis() / 1000);
	}

	// 同上
	public static int getDateInSecond(Date date) {
		Calendar calc = Calendar.getInstance();
		calc.setTime(date);
		return (int) (calc.getTimeInMillis() / 1000);
	}

	/**
	 * 将特定的日期转化为秒数 时间格式为：2015-05-21 09:00:00
	 * 
	 * @param time
	 * @return long类型
	 */
	public static long getDateToSecond(String time) {
		Date date = Util.parseDateStr(time);
		Calendar calc = Calendar.getInstance();
		calc.setTime(date);
		return calc.getTimeInMillis() / 1000;
	}

	/**
	 * 将秒数转化为日期 格式为：2015-05-21 09:00:00
	 * 
	 * @param time
	 * @return 返回String类型
	 */
	public static String getSecondToDate(long time) {
		long timemilli = time * (long) 1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(timemilli);
		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return format.format(calc.getTime());
	}

	// 获取日期，格式：yyyy-MM-dd HH:mm:ss
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	// 秒转换成两位的时间，格式：HH:mm:ss
	public static String turnSecondsToTimestring(int seconds) {
		String result = "";
		int hour = 0, min = 0, second = 0;
		hour = seconds / 3600;
		min = (seconds - hour * 3600) / 60;
		second = seconds - hour * 3600 - min * 60;
		if (hour < 10) {
			result += "0" + hour + ":";
		} else {
			result += hour + ":";
		}
		if (min < 10) {
			result += "0" + min + ":";
		} else {
			result += min + ":";
		}
		if (second < 10) {
			result += "0" + second;
		} else {
			result += second;
		}

		return result;
	}

	public static Date curDateOffset(int day) {
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_YEAR, day);
		return calc.getTime();
	}

	public static Date curTimeOffset(int sec) {
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.SECOND, sec);
		return calc.getTime();
	}

	public static Date dayOffset(Date day, int offset) {
		Calendar calc = Calendar.getInstance();
		calc.setTime(day);
		calc.add(Calendar.DAY_OF_YEAR, offset);
		return calc.getTime();
	}

	public static String dayOffset(String dayStr, int offset) {
		Date day = parseDateStr(dayStr);
		Calendar calc = Calendar.getInstance();
		calc.setTime(day);
		calc.add(Calendar.DAY_OF_YEAR, offset);
		return formatDate(calc.getTime());
	}
	
	public static String currDayOffset(int offset) {
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.DAY_OF_YEAR, offset);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calc.getTime());
	}

	// 当前时间的月份位移
	public static String currMonthOffset(int offset) {
		Calendar calc = Calendar.getInstance();
		calc.add(Calendar.MONTH, offset);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(calc.getTime());
	}

	// 指定时间的月份位移
	public static Date currMonthOffset(Date bdate, int offset) {
		Calendar calc = Calendar.getInstance();
		if (bdate != null)
			calc.setTime(bdate);
		calc.add(Calendar.MONTH, offset);
		return calc.getTime();
	}

	public static Date timeHourOffset(Date time, int offset) {
		Calendar calc = Calendar.getInstance();
		calc.setTime(time);
		calc.add(Calendar.HOUR, offset);
		return calc.getTime();
	}

	// 获得date的长串显示
	public static String getDateTimeStr(Date date) {
		return getTimeStr(date);
	}

	// 获得time的长串显示,方法同上
	public static String getTimeStr(Date date) {
		if (date == null)
			return "";
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	// 获得date的长串显示
	public static String getDateStr(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public static String getDateStr(int dateInSec) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(dateInSec * 1000);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(cal.getTime());
	}

	// 获得指定时间在日历中的天数
	public static int getDayofCal(String day) {
		if (Util.isNull(day))
			return 0;
		String format = "yyyy-MM-dd";
		DateFormat dateFormat = new SimpleDateFormat(format);
		try {
			Date offdate = dateFormat.parse(day);
			Calendar calc = Calendar.getInstance();
			calc.setTime(offdate);
			long offmillis = calc.getTimeInMillis() + 12*60*60*1000;
			long dayNum = offmillis / 1000 / 60 / 60 / 24;
			return (int) dayNum;
		} catch (ParseException pe) {
			return 0;
		}
	}

	// 获得指定时间在日历中的天数
	public static int getDayofCal(Date day) {
		if (day == null)
			return 0;
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		String dayStr = sdf.format(day);
		int dayPlus = 0;
		if (dayStr.endsWith("00:00:00"))
			dayPlus = 1;
		Calendar calc = Calendar.getInstance();
		calc.setTime(day);
		long offmillis = calc.getTimeInMillis();
		long dayNum = offmillis / (1000 * 60 * 60 * 24) + dayPlus;
		return (int) dayNum;
	}

	// 判断当前时间是否位于bday和eday之间
	public static boolean checkCurdayRang(Date bday, Date eday) {
		int bdayNum = 0;
		int cdayNum = getDayofCal(Calendar.getInstance().getTime());
		int edayNum = Integer.MAX_VALUE;
		if (bday != null)
			bdayNum = getDayofCal(bday);
		if (eday != null)
			edayNum = getDayofCal(eday);
		return cdayNum >= bdayNum && cdayNum <= edayNum;
	}

	// 获得一个月的开始或者结束日
	public static Date getMonthDay(int year, int month, boolean firstOrEnd) {
		Calendar calc = Calendar.getInstance();
		calc.set(Calendar.YEAR, year);
		calc.set(Calendar.MONTH, month - 1);
		calc.set(Calendar.DAY_OF_MONTH, 1);
		if (firstOrEnd) {
			return calc.getTime();
		} else {
			String dayStr1 = Util.getDateStr(calc.getTime()).substring(0, 7);
			for (int i = 31; i >= 28; i--) {
				calc.set(Calendar.DAY_OF_MONTH, i);
				calc.set(Calendar.MONTH, month - 1);
				String dayStr2 = Util.getDateStr(calc.getTime()).substring(0, 7);
				if (dayStr2.equals(dayStr1))
					break;
			}
			return calc.getTime();
		}
	}

	public static String getDayShortStr(Date day) {
		if (day == null)
			return "";
		else {
			String dayStr = Util.getDateStr(day);
			if (Util.isNull(dayStr) && dayStr.length() < 5)
				return "";
			else
				return dayStr.substring(5);
		}
	}

	public static String getTimeShortStr(Date time) {
		if (time == null)
			return "";
		else {
			String timeStr = Util.getDateTimeStr(time);
			if (Util.isNull(timeStr))
				return "";
			else {
				if (timeStr.length() <= 15)
					return "";
				else
					return timeStr.substring(11, 16);
			}
		}
	}

	// 解析以秒数为表示的时间
	public static Date parseTimeStrInSec(int timeInSec) {
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(timeInSec * (long) 1000);
		return calc.getTime();
	}

	// 解析
	public static int parseTimeStrInSec(String dtStr) {
		if (Util.isNull(dtStr))
			return 0;
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			rtDate = dateFormat.parse(dtStr);
			return (int)(rtDate.getTime()/1000);
		} catch (ParseException pe) {
		}
		return 0;
	}

	// 解析
	public static Date parseTimeStr(String dtStr) {
		if (Util.isNull(dtStr))
			return null;
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			rtDate = dateFormat.parse(dtStr);
		} catch (ParseException pe) {
		}
		return rtDate;
	}

	// 解析
	public static Date parseDateStr(String dtStr) {
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			rtDate = dateFormat.parse(dtStr);
		} catch (ParseException pe) {
		}
		return rtDate;
	}

	// 当天
	public static String currDay() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	// 时间
	public static String currHour() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("HH");
		return dateFormat.format(date);
	}

	// 年
	public static String currYear() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(date);
	}
		
	// 当天
	public static String currTime() {
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
		return dateFormat.format(date);
	}

	// 当天小时
	public static String currShortTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddHHmm");
		String vl = sdf.format(Calendar.getInstance().getTime());
		return vl;
	}

	// 当天小时
	public static String currShortDay() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String vl = sdf.format(Calendar.getInstance().getTime());
		return vl;
	}

	// 获得当前差异显示
	public static String getCurTimeDiffShow(int diffTimeSec) {
		int curTimeSec = DateUtil.getCurTimeInSec();
		int diffTime = curTimeSec - diffTimeSec;
		//转换成分钟
		diffTime = diffTime/60+1;
		if(diffTime<=59)
			return diffTime + "分钟之前";
		diffTime = diffTime/60;
		if(diffTime<12)
			return diffTime + "小时之前";
		return DateUtil.formatDateTime(diffTimeSec);
	}

	public static String getTimeDiffShow(String beginTime, String endTime) {
		Date b = DateUtil.parseTimeStr(beginTime);
		Date e = DateUtil.parseTimeStr(endTime);
		return getTimeDiffShow(b, e);
	}

	public static String getTimeDiffShow(Date beginTime, Date endTime) {
		int curTimeInSec = DateUtil.getCurTimeInSec();
		int bTimeInSec = 0;
		if (beginTime != null)
			bTimeInSec = DateUtil.getTimeInSec(beginTime);
		int eTimeInSec = Integer.MAX_VALUE;
		if (endTime != null)
			eTimeInSec = DateUtil.getTimeInSec(endTime);

		String timeStr = null;
		int diffTime = 0;
		if (curTimeInSec > eTimeInSec) {
			diffTime = (curTimeInSec - eTimeInSec) / ONE_DAY_INSEC + 1;
			if (diffTime < 7)
				return "已停售" + diffTime + "天";
			else {
				timeStr = DateUtil.formatTime(endTime);
				return "停售于" + timeStr.substring(2, 10);
			}
		}

		/*
		 * if(bTimeInSec == 0) return "销售中";
		 */
		diffTime = bTimeInSec - curTimeInSec;
		// 大于1天
		if (diffTime > ONE_DAY_INSEC) {
			diffTime = diffTime / ONE_DAY_INSEC + 1;
			if (diffTime < 7)
				return "距开售" + diffTime + "天";
			else {
				timeStr = DateUtil.formatTime(bTimeInSec);
				return "开售于" + timeStr.substring(2, 10);
			}
		} else if (diffTime > 0) {
			int diffTime0 = diffTime;
			int hour = diffTime / 3600;
			diffTime = diffTime - hour * 3600;
			int minute = diffTime / 60;
			diffTime = diffTime - minute * 60;
			int second = diffTime;
			String tstr = "距销售";
			if (hour < 10)
				tstr += "0" + hour;
			else
				tstr += hour;
			tstr += ":";
			if (minute < 10)
				tstr += "0" + minute;
			else
				tstr += minute;
			tstr += ":";
			if (second < 10)
				tstr += "0" + second;
			else
				tstr += second;
			// 加上当前
			return tstr + "," + diffTime0;
		}

		if (curTimeInSec < eTimeInSec && eTimeInSec != Integer.MAX_VALUE) {
			diffTime = (eTimeInSec - curTimeInSec) / ONE_DAY_INSEC + 1;
			if (diffTime <= 7)
				return "距停售" + diffTime + "天";
			else {
				timeStr = DateUtil.formatDay(endTime);
				return "停售于" + timeStr.substring(2);
			}
		}
		return "销售中";
	}
	
	public static String convDateFormat(String day,String frmFormat, String toFromat){
		if(Util.isNull(day))
			return "";
		Date rtDate = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat(frmFormat);
		try {
			rtDate = dateFormat.parse(day);
		} catch (ParseException pe) {
			return "";
		}
		if(rtDate == null)
			return "";
		DateFormat tFormat = new SimpleDateFormat(toFromat);
		return tFormat.format(rtDate);
	}

	// 测试显示时间工具
	private static void testTimeDiff() {
		String vl = null;
		// 销售中
		/*
		 * vl = getTimeDiffShow(null,null); System.out.println(vl);
		 */

		// 停止销售于
		vl = getTimeDiffShow(DateUtil.curDateOffset(-7), DateUtil.curDateOffset(-3));
		System.out.println(vl);

		// 距停售x天
		/*
		 * vl = getTimeDiffShow(null,DateUtil.curDateOffset(6));
		 * System.out.println(vl);
		 */

		// 开售于16-05-07
		/*
		 * vl = getTimeDiffShow(DateUtil.curDateOffset(10),null);
		 * System.out.println(vl);
		 */

		// 距销售23:59:59
		/*
		 * vl = getTimeDiffShow(DateUtil.curDateOffset(1),null);
		 * System.out.println(vl);
		 */

		// 距销售1:0:0
		/*
		 * vl = getTimeDiffShow(DateUtil.curTimeOffset(ONE_HOUR_INSEC),null);
		 * System.out.println(vl);
		 */

		// 距销售2:0:15
		/*
		 * vl =
		 * getTimeDiffShow(DateUtil.curTimeOffset(2*ONE_HOUR_INSEC+15),null);
		 * System.out.println(vl);
		 */
	}
	
	//TODO 根据年月日计算年龄,birthTimeString:"1994-11-14"
	public static int getAgeFromBirthTime(String birthTimeString) {
		// 先截取到字符串中的年、月、日
		String strs[] = birthTimeString.trim().split("-");
		int selectYear = Integer.parseInt(strs[0]);
		int selectMonth = Integer.parseInt(strs[1]);
		int selectDay = Integer.parseInt(strs[2]);
		// 得到当前时间的年、月、日
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayNow = cal.get(Calendar.DATE);

		// 用当前年月日减去生日年月日
		int yearMinus = yearNow - selectYear;
		int monthMinus = monthNow - selectMonth;
		int dayMinus = dayNow - selectDay;

		int age = yearMinus;// 先大致赋值
		if (yearMinus < 0) {// 选了未来的年份
			age = 0;
		} else if (yearMinus == 0) {// 同年的，要么为1，要么为0
			if (monthMinus < 0) {// 选了未来的月份
				age = 0;
			} else if (monthMinus == 0) {// 同月份的
				if (dayMinus < 0) {// 选了未来的日期
					age = 0;
				} else if (dayMinus >= 0) {
					age = 1;
				}
			} else if (monthMinus > 0) {
				age = 1;
			}
		} else if (yearMinus > 0) {
			if (monthMinus < 0) {// 当前月>生日月
			} else if (monthMinus == 0) {// 同月份的，再根据日期计算年龄
				if (dayMinus < 0) {
				} else if (dayMinus >= 0) {
					age = age + 1;
				}
			} else if (monthMinus > 0) {
				age = age + 1;
			}
		}
		return age;
	}

	public static void main(String[] args) {
		/*
		 * List<String> days = getDayList(2014,2); System.out.println(days);
		 * System.out.println(formatDate(Calendar.getInstance().getTime()));
		 */
		// System.out.println(getTimeInMinute("01:a2"));
		// testTimeDiff();

		//int curTime = parseTimeStrInSec("2017-01-05 05:00:00");
		//System.out.println(curTime);
		System.out.println(DateUtil.formatDateTime(1511712000));
		//System.out.println(DateUtil.parseTimeStrInSec("2017-12-12 18:00:00"));
		//System.out.println(DateUtil.parseTimeStrInSec("2017-12-12 20:00:00"));
		//System.out.println(DateUtil.parseTimeStrInSec("2016-02-15 23:59:59"));
	}

}
