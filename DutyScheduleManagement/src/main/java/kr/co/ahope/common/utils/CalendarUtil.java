package kr.co.ahope.common.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.ahope.employee.controller.EmplController;

public class CalendarUtil {

	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);

	Calendar cal = Calendar.getInstance();

	private int year;
	private int month;
	private int date;
	private int currentYear = cal.get(Calendar.YEAR);
	private int currentMonth = cal.get(Calendar.MONTH);
	private int currentDate = cal.get(Calendar.DATE);
	private int prevMonthStartDay;
	private int prevMonthLastDate;
	private int currentMonthStartDay;
	private int currentMonthLastDate;
	private int nextMonthStartDay;
	private int nextMonthLastDate;
	private int[] currentCalendarDay = new int[42];

	public Map<String, Object> getDateInfo(int requestMonth, int requestYear) {
		logger.debug("currentDate : " + currentDate);
		if (requestYear == -99 && requestMonth == -99) {
			year = currentYear;
			month = currentMonth;
			date = currentDate;
		} else if (requestMonth == 12) {
			date = 1;
			month = 0;
			year = requestYear + 1;
		} else if (requestMonth == -1) {
			date = 1;
			month = 11;
			year = requestYear - 1;

		} else {
			year = requestYear;
			month = requestMonth;
			if (month == currentMonth && year == currentYear) {
				date = currentDate;
			} else {
				date = 0;
			}
		}

		cal.set(year, month - 1, 1);
		prevMonthStartDay = cal.get(Calendar.DAY_OF_WEEK);
		cal.set(year, month, 0);
		prevMonthLastDate = cal.get(Calendar.DATE);
		cal.set(year, month, 1);
		currentMonthStartDay = cal.get(Calendar.DAY_OF_WEEK);
		cal.set(year, month + 1, 0);
		currentMonthLastDate = cal.get(Calendar.DATE);
		cal.set(year, month + 1, 1);
		nextMonthStartDay = cal.get(Calendar.DAY_OF_WEEK);
		cal.set(year, month + 2, 0);
		nextMonthLastDate = cal.get(Calendar.DATE);

		cal.set(year, month, date);

		int count = 0;

		for (int i = currentMonthStartDay - 2; i >= 0; i--) {
			currentCalendarDay[count++] = prevMonthLastDate - i + 35;
		}

		for (int i = 1; i <= currentMonthLastDate; i++) {
			currentCalendarDay[count++] = i;
		}

		int nextMonthDay = 1;

		while (count % 7 != 0) {
			currentCalendarDay[count++] = 35 + nextMonthDay++;
		}

		int weekCount = count / 7;

		Map<String, Object> dateInfo = new HashMap<String, Object>();
		dateInfo.put("year", year);
		dateInfo.put("month", month);
		dateInfo.put("date", date);
		dateInfo.put("prevMonthStartDay", prevMonthStartDay);
		dateInfo.put("prevMonthLastDate", prevMonthLastDate);
		dateInfo.put("currentMonthStartDay", currentMonthStartDay);
		dateInfo.put("currentMonthLastDate", currentMonthLastDate);
		dateInfo.put("nextMonthStartDay", nextMonthStartDay);
		dateInfo.put("nextMonthLastDate", nextMonthLastDate);
		dateInfo.put("weekCount", weekCount);
		dateInfo.put("currentCalendarDay", currentCalendarDay);
		return dateInfo;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}

	public int getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(int currentDate) {
		this.currentDate = currentDate;
	}

	public Map<String, Object> getTommorowDateInfo(int date2, int month2, int year2) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int todayDate = date2;
		int todayMonth = month2;
		int todayYear = year2;
		int tomorrowDate;
		int tomorrowMonth;
		int tomorrowYear = year2;
		cal.set(year, month + 1, 0);
		currentMonthLastDate = cal.get(Calendar.DATE);
		if (todayDate == currentMonthLastDate) {
			tomorrowDate = 1;
			if (todayMonth == 11) {
				tomorrowMonth = 0;
				tomorrowYear = todayYear + 1;
			} else {
				tomorrowMonth = todayMonth + 1;
			}
		} else {
			tomorrowDate = todayDate + 1;
			tomorrowMonth = todayMonth;
			tomorrowYear = todayYear;
		}
		resultMap.put("tomorrowDate", tomorrowDate);
		resultMap.put("tomorrowMonth", tomorrowMonth);
		resultMap.put("tomorrowYear", tomorrowYear);
		return resultMap;
	}

}