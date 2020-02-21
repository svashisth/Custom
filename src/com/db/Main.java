package com.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main
{

	public static void main1(String[] args) throws ClassNotFoundException, SQLException
	{
		String url = "jdbc:jtds:sqlserver://DBD_SOA.corp.agp.ads:40021/WEBAUTH;sendStringParametersAsUnicode=false;ssl=request";
		// url ="jdbc:jtds:sqlserver://DBD_SOA.corp.agp.ads:40021/IMPERIO;sendStringParametersAsUnicode=false;ssl=require";
		url = "jdbc:jtds:sqlserver://DBD_SOA.corp.agp.ads:40021/AGPCOM;sendStringParametersAsUnicode=false;ssl=require";
		String userName = "APP_WEBAUTH";
		// userName = "APP_Imperio";
		userName = "APP_AGPCOM";
		String password = "9!$B&VJuJ@rQ8";
		// password = "r)maVlLMk0";
		password = "A88_@GpC0m";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, userName, password);

		System.out.println("Conn success ----------------");
		PreparedStatement prepareStatement = conn.prepareStatement("Select * from PreCertSummary (nolock)");
		// prepareStatement = conn.prepareStatement("Select * from Application (nolock)");
		prepareStatement = conn.prepareStatement("SELECT * FROM application_ids");
		boolean execute = prepareStatement.execute();
		ResultSet resultSet = prepareStatement.getResultSet();
		int fetchSize = resultSet.getFetchSize();
		System.out.println("main\t" + fetchSize);
		while (resultSet.next())
		{
			System.out.println("" + resultSet.getString(1) + " - " + resultSet.getString(2));
		}
	}
}
