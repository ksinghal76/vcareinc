package com.vcareinc.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
	private static final String DATE_FORMAT = "MM/dd/yyyy";
	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	public static Timestamp getTimestamp(String date) throws ParseException {
		return (new Timestamp(new SimpleDateFormat(DATE_FORMAT).parse(date).getTime()));
	}

	public static Timestamp getTimestamp(String date, String dateFormat) throws ParseException {
		return (new Timestamp(new SimpleDateFormat(dateFormat).parse(date).getTime()));
	}

	public static String getStringforDate(Timestamp time) {
		return (new SimpleDateFormat(DATE_FORMAT)).format(time);
	}

	public static String getStringforDate(Timestamp time, String dateFormat) {
		return (new SimpleDateFormat(dateFormat)).format(time);
	}

	public static boolean isValidDateString(String date) {
		return isValidDateString(date, DATE_FORMAT);
	}

	public static boolean isValidDateString(String date, String dateFormat) {
		boolean isValid = false;
		try {
			new SimpleDateFormat(dateFormat).parse(date);
			isValid = true;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return isValid;
	}
}
