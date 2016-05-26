package com.trial;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;

public class Trial
{

	/*public static void main(String[] args)
	{
		Trial trial = new Trial();
		boolean result = trial.isOne() && trial.isTwo() && trial.isThree() && trial.isFour();
		System.out.println("main "+ result);
	}

	public boolean isOne()
	{
		System.out.println("isOne");
		return true;
	}

	public boolean isTwo()
	{
		System.out.println("isTwo");
		return false;
	}

	public boolean isThree()
	{
		System.out.println("isThree");
		return true;
	}

	public boolean isFour()
	{
		System.out.println("isFour");
		return true;
	}*/
	final static LocalDate localDate = new LocalDate(DateTimeZone.getDefault());
	
	public static void main(String[] args) throws InterruptedException
	{
		/*long start = System.nanoTime();
		Date time = Calendar.getInstance().getTime();
		System.out.println("main\t" + time);
		System.out.println("main\t" + (System.nanoTime() - start) + " ns");
		
		start = System.nanoTime();
		time = new Date();
		System.out.println("main\t" + time);
		System.out.println("main\t" + (System.nanoTime() - start) + " ns");
		
		start = System.nanoTime();
		
		time = localDate.toDateTimeAtCurrentTime().toDate();
		System.out.println("main\t" + time);
		System.out.println("main\t" + (System.nanoTime() - start) + " ns");
		
		for(int j = 0; j < 400; j++){
			Thread.currentThread().sleep(1000);
			time = localDate.toDateTimeAtCurrentTime().toDate();
			if(j % 100 ==0)
				System.out.println("main\t" + time);
		}*/
		
		/*List<String> asList = new ArrayList<String>(Arrays.asList("b,a,b,c,s,e,s,s,k".split(",")));
		System.out.println("main\t" + asList);
		System.out.println("main\t" + asList.size());
		filterGroupIdsForExclusion(asList);
		System.out.println("enclosing_method\t" + asList);*/
		
		/*List<String> asList = new ArrayList<String>(Arrays.asList("bhp,abc;ba;caa;skk;ae;ssn;kab".split(";")));
		System.out.println("main\t" + asList);
		if(asList.contains("bhp"))
			System.out.println("main\t" + true);
		asList = new ArrayList<String>();
		System.out.println("main\t" + asList.get(0));*/
		
		UUID randomUUID = UUID.randomUUID();		
		System.out.println("main\t" + randomUUID);
		
		try
		{
			throw new IllegalArgumentException(); 
		}
		catch (RuntimeException e)
		{
			System.out.println("main in rt \t" + e);
		}
		catch (Exception e)
		{
			System.out.println("main in e \t" + e);
		}
	}
	
	private static void filterGroupIdsForExclusion(List<String> groupIds)
	{
		String excludedGroupIds = "b,s";
		if(excludedGroupIds != null && ! excludedGroupIds.isEmpty()){
			List<String> egidsList = Arrays.asList(excludedGroupIds.split(","));
			groupIds.removeAll(egidsList);
		}
	}

}
