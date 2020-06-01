package project.mundofii.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static  Calendar convertStringToCalendar(String temp, String dateFormat) throws ParseException {
		
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		
		Date date = format.parse(temp);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar;
		
	}
	
	public static String convertCalendarToString(Calendar calendar) {
		return null;
	}
	
}
