package kr.co.ahope.common.utils;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.co.ahope.employee.controller.EmplController;

public class CalendarUtil {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(EmplController.class);

	Calendar cal = Calendar.getInstance();

	private int year;
	private int month;
	private int date;
	private final int DEFAULT_YEAR = cal.get(Calendar.YEAR);
	private final int DEFAULT_MONTH = cal.get(Calendar.MONTH);
	private final int DEFAULT_DATE = cal.get(Calendar.DATE);
	private int prevMonthStartDay;
	private int prevMonthLastDate;
	private int currentMonthStartDay;
	private int currentMonthLastDate;
	private int nextMonthStartDay;
	private int nextMonthLastDate;
	private int[] currentCalendarDay = new int[42];

	public Map<String, Object> getDateInfo(int requestMonth, int requestYear) {

		if (requestYear == -99 && requestMonth == -99) {
			year = DEFAULT_YEAR;
			month = DEFAULT_MONTH;
			date = DEFAULT_DATE;
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
			if (month == DEFAULT_MONTH && year == DEFAULT_YEAR) {
				date = DEFAULT_DATE;
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

	public int getDEFAULT_YEAR() {
		return DEFAULT_YEAR;
	}

	public int getDEFAULT_MONTH() {
		return DEFAULT_MONTH;
	}

	public int getDEFAULT_DATE() {
		return DEFAULT_DATE;
	}
	
	
}