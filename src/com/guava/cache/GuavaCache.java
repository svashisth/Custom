package com.guava.cache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class GuavaCache
{

	private static LoadingCache<String, Boolean> data = CacheBuilder.newBuilder()
		       .maximumSize(1000)
		       .expireAfterWrite(60, TimeUnit.MINUTES)
		       .build(
		           new CacheLoader<String, Boolean>() {
		             public Boolean load(String key) throws InterruptedException {
		               return getData(key);
		             }
					
		           });
	
	private static Boolean getData(String key) throws InterruptedException
	{
		int iKey = Integer.parseInt(key);
		if(iKey > 1 && iKey < 2000){
			Thread.sleep(1000);
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public static void main(String[] args) throws ExecutionException
	{
		Boolean boolean1 = data.get("2");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("2");
		System.out.println("main\t" + boolean1);		
		boolean1 = data.get("2");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("3");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("3");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("4");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("4");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("5");
		System.out.println("main\t" + boolean1);
		boolean1 = data.get("5");
		System.out.println("main\t" + boolean1);
		
	}
}
