package com.ihltx.utility.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * DateUtil
 * Datetime utility class
 * @author liulin 84611913@qq.com
 *
 */
public class DateUtil {

	/**
	 * For MySQL, you need to set the time zone in my.ini to [default-time-zone] is "+08:00"
	 */
	public static String timeZone = "Asia/Shanghai";

	/**
	 * Current date to [yyyy-MM-dd HH:mm:ss] format string
	 * 
	 * @return String
	 */
	public static String date() {
		return date(new Date(),"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 *
	 * Specify date to [yyyy-MM-dd HH:mm:ss] format string
	 * 
	 * @param date					The specified date
	 * @return String
	 */
	public static String date(Date date) {
		return date(date,"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * format the current date using the specified format string, such as [yyyy-MM-dd HH:mm:ss]
	 * 
	 * @param format				The specified format string, such as [yyyy-MM-dd HH:mm:ss.SSS]
	 * @return String
	 */
	public static String date(String format) {
		return date(new Date(),format);
	}

	/**
	 *  format the specify date using the specified format string, such as [yyyy-MM-dd HH:mm:ss]
	 * 
	 * @param date					The specified date
	 * @param format				The specified format string, such as [yyyy-MM-dd HH:mm:ss.SSS]
	 * @return String
	 */
	public static String date(Date date, String format) {
		SimpleDateFormat sdf =new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.format(date);
	}

	/**
	 * Gets the current timestamp in seconds
	 * 
	 * @return Long
	 */
	public static Long getTime() {
		return new Date().getTime() / 1000;
	}

	/**
	 * Gets the current timestamp in milliseconds
	 * 
	 * @return Long
	 */
	public static Long getTimeMillis() {
		return new Date().getTime();
	}

	/**
	 * Gets the time stamp of the specified date in seconds
	 *
	 * @param date					The specified date
	 * @return Long
	 */
	public static Long getTime(Date date) {
		return date.getTime() / 1000;
	}

	/**
	 * Gets the time stamp of the specified date in milliseconds
	 *
	 * @param date					The specified date
	 * @return Long
	 */
	public static Long getTimeMillis(Date date) {
		return date.getTime();
	}
	

	/**
	 * Gets the elapsed time based on the specified date, in milliseconds
	 * 
	 * @param startDate					The specified date
	 * @return Long
	 */
	public static Long getUseTimeMillis(Date startDate) {
		return new Date().getTime()-startDate.getTime();
	}
	

	/**
	 * Gets the elapsed time based on the specified date, in seconds
	 *
	 * @param startDate					The specified date
	 * @return Double
	 */
	public static Double getUseTimeSeconds(Date startDate) {
		return (new Date().getTime()-startDate.getTime()) * 1.0/1000;
	}
	
	/**
	 * Convert [yyyy-MM-dd HH:mm:ss] format date string to time stamp(seconds)
	 * @param date				The specified date string, such as [yyyy-MM-dd HH:mm:ss]
	 * @return Long
	 * 		Long integer in seconds
	 * @throws ParseException
	 */
	public static Long getTime(String date) throws ParseException {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.parse(date).getTime()/1000;
	}
	
	/**
	 * Convert [yyyy-MM-dd HH:mm:ss] format date string to time stamp(milliseconds)
	 * @param date				The specified date string, such as [yyyy-MM-dd HH:mm:ss]
	 * @return Long
	 * 		Long integer in milliseconds
	 * @throws ParseException
	 */
	public static Long getTimeMillis(String date) throws ParseException {
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		return sdf.parse(date).getTime();
	}
	
	
	/**
	 * Convert long type millisecond time stamp to [yyyy-MM-dd HH:mm:ss] format string
	 * @param millis			Long type millisecond time
	 * @return String
	 * 		[yyyy-MM-dd HH:mm:ss] format string
	 */
	public static String long2Date(Long millis) {
		Date d=new Date(millis);
		return date(d);
	}
	
	/**
	 * Convert string type millisecond time stamp to [yyyy-MM-dd HH:mm:ss] format string
	 * @param millis			String type millisecond time
	 * @return String
	 * 		[yyyy-MM-dd HH:mm:ss] format string
	 */
	public static String string2Date(String millis) {
		Date d=new Date(Long.valueOf(millis));
		return date(d);
	}
	
	
	/**
	 * Add date for [yyyy-MM-dd] format date string (unit: day)
	 * 
	 * @param date				String type [yyyy-MM-dd] format date
	 * @param day				add days, positive is increase, negative is decrease
	 * @return String
	 * 		[yyyy-MM-dd] format date string
	 */
	public static String addDate(String date, int day)  throws ParseException {		
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		long ms = sdf.parse(date + " 00:00:00").getTime();
		Calendar   calendar = new GregorianCalendar(); 
		calendar.setTime(new Date(ms)); 
		calendar.add(Calendar.DATE, day);
		return date(calendar.getTime(),"yyyy-MM-dd");
	}

	/**
	 * Convert [yyyyMMdd] format date string to [yyyy-MM-dd] format date string
	 * @param date				 [yyyyMMdd] format date string
	 * @return String
	 * 		[yyyy-MM-dd] format date string
	 */
	public static String toDate(String date) {
		char[] chars = date.toCharArray();
		char[] dateChars =new char[10];
		dateChars[0]=chars[0];
		dateChars[1]=chars[1];
		dateChars[2]=chars[2];
		dateChars[3]=chars[3];
		dateChars[4]='-';
		dateChars[5]=chars[4];
		dateChars[6]=chars[5];
		dateChars[7]='-';
		dateChars[8]=chars[6];
		dateChars[9]=chars[7];
		return new String(dateChars);
	}
}
