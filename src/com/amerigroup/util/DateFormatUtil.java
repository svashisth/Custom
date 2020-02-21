package com.amerigroup.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatUtil
{

	public static void main(String[] args)
	{
		 try
		{
			Date date = new SimpleDateFormat("yyyy-dd-MM", Locale.US).parse("2016-06-27-05:00");
			System.out.println("main\t" + date);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
	}
}
