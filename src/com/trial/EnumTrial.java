package com.trial;

import java.util.ArrayList;

public class EnumTrial
{

	private enum BRAND{
		AGP, ANTHEM, EMP;
		
		public static boolean contains(String brandName){
			for (BRAND brand : values()){
				if(brand.toString().compareToIgnoreCase(brandName) == 0)
					return true;
			}
			return false;
		}
	}
	
	public static void main(String[] args)
	{
		String text = "agp";
		EnumTrial trial = new EnumTrial();
		
		System.out.println("main "+ BRAND.contains(text));
	}	

}
