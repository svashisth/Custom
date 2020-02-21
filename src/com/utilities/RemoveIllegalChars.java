package com.utilities;

public class RemoveIllegalChars
{

	public static void main(String[] args)
	{
		String foo = "CT Anthem MediBlue�Plus (HMO)";
		foo = foo.replaceAll("[^\\s+A-Za-z0-9()\\[\\]]", "");
		System.out.println("main\t" + foo);
	}
}
