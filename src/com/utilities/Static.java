package com.utilities;

public class Static
{
	static int x;
	StringBuffer sb = new StringBuffer();
	static StringBuffer sb2 = new StringBuffer();

	public Static()
	{
		method();
		method2();
	}

	public void method()
	{
		x += 3;
		sb.append(x);
	}

	public void method2()
	{
		x += 3;
		sb2.append(x);
	}

	public static void main(String[] args)
	{
		Static mc = new Static();
		Static mc2 = new Static();
		Static mc3 = new Static();
		System.out.println(mc2.sb + "-" + mc2.sb2);
	}
}
