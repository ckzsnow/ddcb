package com.dd.utils;

import java.sql.Timestamp;
import java.util.Calendar;

public class TimeFormat {

	public static String getFormatTime(Timestamp tm) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tm);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DATE);
		int hour = calendar.get(Calendar.HOUR);
		int minute = calendar.get(Calendar.MINUTE);
		String amPmFlag = calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM";
		String weekDayStr = "";
		switch(weekDay) {
		case 1:
			weekDayStr = "一";
			break;
		case 2:
			weekDayStr = "二";
			break;
		case 3:
			weekDayStr = "三";
			break;
		case 4:
			weekDayStr = "四";
			break;
		case 5:
			weekDayStr = "五";
			break;
		case 6:
			weekDayStr = "六";
			break;
		case 7:
			weekDayStr = "日";
			break;
		}
		return String.valueOf(month) + "/" + String.valueOf(day) + " 周" + weekDayStr + "  " + hour + ":" + minute + amPmFlag;
	}
	
	public static void main(String[] args) {
		Timestamp tm = new Timestamp(System.currentTimeMillis());
		System.out.println(getFormatTime(tm));
	}
	
}
