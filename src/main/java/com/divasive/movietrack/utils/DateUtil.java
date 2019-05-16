package com.divasive.movietrack.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {


    public static String getTime() {

	
	SimpleDateFormat sdf;
	sdf = new SimpleDateFormat(TIME_FORMAT);
	sdf.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
	String text = sdf.format(Calendar.getInstance().getTime());
	return text;
    }


    public static String TIME_FORMAT = "HH:mm:ss";	
	
}
