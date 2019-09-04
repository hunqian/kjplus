package org.ybk.basic.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public final class DateUtils {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	public static final String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	public static final String ZH_TIME = "yyyy年MM月dd日  HH:mm:ss";
	
	private static final int MONTH_DAY_NUM[] = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};

	// 判断日期是否正确
	public static boolean isExistDate(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		return cal.get(Calendar.DATE) == dd
				&& cal.get(Calendar.MONTH) + 1 == mm
				&& cal.get(Calendar.YEAR) == yyyy;
	}

	public static Date toDate(int yyyy, int mm, int dd) {
		Calendar cal = Calendar.getInstance();
		cal.set(yyyy, mm - 1, dd);
		return cal.getTime();
	}

	public static String formatDate(Date value) {
		return formatDate(value, DEFAULT_DATE_PATTERN);
	}
	
	//时间格式化
	public static String timeStampformatString(Timestamp value) {
		SimpleDateFormat sdf = new SimpleDateFormat(ZH_TIME);
		return sdf.format(value);
	}
	
	//根据时间描述获得时间时间显示
	public static String formatDateTime(int timeInSec) {
		if(timeInSec<0)
			return "";
		//转换成milisec
		long time = timeInSec*1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		return formatDate(calc.getTime(), DEFAULT_DATETIME_PATTERN);
	}
	
	//根据时间描述获得时间时间显示,
	//短格式,如果是当天，只返回时分秒，否则返回年月日
	public static String formatShortDateTime(long timeInSec) {
		if(timeInSec<0)
			return "";
		//转换成milisec
		long time = timeInSec*1000;
		Calendar calc = Calendar.getInstance();
		calc.setTimeInMillis(time);
		String timeStr = formatDate(calc.getTime(), DEFAULT_DATETIME_PATTERN);
		String curday = Util.currDay();
		if(Util.isNull(timeStr))
			return timeStr;
		if(!timeStr.startsWith(curday))
			return timeStr.substring(0,10);
		else
			return timeStr.substring(11);
	}

	//根据时间描述获得时间时间显示,
	//短格式,如果是当天，只返回时分，否则返回月日，不包含年
	public static String formatVeryShortDateTime(long timeInSec) {
		if(timeInSec<0)
			return "";
		//转换成milisec
		long time = timeInSec*1000;
		String timeStr = formatDate(new Date(time), DEFAULT_DATETIME_PATTERN);
		String curday = Util.currDay();
		if(Util.isNull(timeStr))
			return timeStr;
		if(!timeStr.startsWith(curday))
			return timeStr.substring(5,10);
		else
			return timeStr.substring(11,16);
	}

	public static String formatDateTime(Date value) {
		return formatDate(value, DEFAULT_DATETIME_PATTERN);
	}

	public static String formatDate(Date value, String pattern) {
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
	
	//判断是否闰年
	private static boolean isLeapYear(int year){
		if((year%4==0&&year%100!=0)||year%400==0)
			return true;
		else
			return false;
	}
	
	//2014-2
	public static List<String> getDayList(int year,int month){
		List<String> days = new ArrayList<String>();
		if(month<=0||month>12)
			return days;
		if(year<0)
			return days;
		int dayNum = MONTH_DAY_NUM[month-1];
		//判断闰月
		if(month == 2){
			boolean leapYear = isLeapYear(year);
			if(leapYear)
				dayNum = 29;
		}
		String dayStr = null;
		for(int i=1;i<=dayNum;i++){
			if(i<10)
				dayStr = "0" + i;
			else
				dayStr = Integer.toString(i);
			if(month<10)
				days.add(year+"-0"+month+"-"+dayStr);
			else
				days.add(year+"-"+month+"-"+dayStr);
		}
		return days;
	}
	
	//获得时间对于的分钟数量,01:22返回88
	public static int getTimeInMinute(String minuteStr){
		if(Util.isNull(minuteStr))
			return -1;
		int p = minuteStr.indexOf(":");
		if(p<0)
			return -1;
		int totalMinute = 0;
		String hourStr = minuteStr.substring(0,p);
		String minStr = minuteStr.substring(p+1);
		try{
			totalMinute += Integer.parseInt(hourStr)*60;
		}catch(NumberFormatException nfe){
			return -1;
		}
		try{
			totalMinute += Integer.parseInt(minStr);
		}catch(NumberFormatException nfe){
			return -1;
		}
		return totalMinute;
	}
	
	//获得当前时间，以秒为单位
	public static int getCurTimeInSec(){
		return (int)(System.currentTimeMillis()/1000);
	}
	
	//获得2016/06/01格式
	public static String getYmdPath(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	//获得2016/06/01格式
	public static String getYmdhmPath(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd/HH/mm");
		return sdf.format(Calendar.getInstance().getTime());
	}
	
	public static void main(String[] args) {
		/*List<String> days = getDayList(2014,2);
		System.out.println(days);
		System.out.println(formatDate(Calendar.getInstance().getTime()));*/
		//System.out.println(getTimeInMinute("01:a2"));
		
		//一小时以前
		String timeStr = formatDateTime(1502189700);
		System.out.println(timeStr);
	}
}
