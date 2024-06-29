
package com.comcast.crm.generic.databaseutility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	Connection con;
	//1
	public void getDbconnection(String url, String username, String password) throws Throwable
	{
		try {
		Driver dr=new Driver();
		DriverManager.registerDriver(dr);
		
	    con=DriverManager.getConnection(url, username, password);
		}catch(Exception e) {
			
		}
		
	}
	
	
	public void getDbconnection() throws Throwable
	
	{
		try {
		Driver dr=new Driver();
		DriverManager.registerDriver(dr);
		
	    con=DriverManager.getConnection("jdbc:mysql://localhost:3308/Projects", "root", "root");
		}catch(Exception e) {
			
		}
		
	}
	//2
	public void closeDbconnection() throws Throwable
	{
		try {
	
		con.close();
		}catch(Exception e) {
			
		}
	}
	
	//3
	public ResultSet executeConselectQuery(String Query) throws Throwable {
		ResultSet result=null;
		try {
	Statement st=con.createStatement();
	result=st.executeQuery(Query);

	}catch(Exception e) {
	
	}
	return result;
}
	//4
	public int ExecuteNonselectQuery(String Query) throws Throwable
	{
		int rs= 0;
		try{
		Statement st=con.createStatement();
		 rs=st.executeUpdate(Query);
		}catch(Exception e) {
			
		}
		return rs;
	
	}
}