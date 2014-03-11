package data;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeStamp {
	public static Calendar getTimeStamp() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.setTimeZone(TimeZone.getDefault());
		
		return cal;
	}
}
