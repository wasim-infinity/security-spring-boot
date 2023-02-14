package com.springboot.security.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class CommonUtils {

	public static final LocalDate stringToDate(String s, String dateFormat) {

		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(format.parse(s));
			s = format.format(c.getTime());
			Date d = format.parse(s);
			System.out.println("date >> " + d);
			LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			System.out.println("local date >>> " + date);
			return date;

		} catch (ParseException e) {
			// e.printStackTrace();
			return null;
		}
	}

}
