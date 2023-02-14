package com.springboot.security.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

	public static final Object stringToDate(String s, String dateFormat, boolean isDate) {

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(s));
			s = format.format(c.getTime());
			Date d = format.parse(s);
			LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			return isDate?d:date;

		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}

}
