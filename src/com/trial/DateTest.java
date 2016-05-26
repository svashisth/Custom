package com.trial;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTest
{

	public static void main(String[] args) throws ParseException
	{
		Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.US).parse("2016-05-04T05:32:42-04:00");
		System.out.println("main\t" + date);
	}
}
