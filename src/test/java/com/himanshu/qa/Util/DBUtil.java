/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.himanshu.qa.Config.Config;

public class DBUtil {
	
	//public static final Logger LOG = LogUtil.createLogger(DBUtil.class, "SQLDBLogs", true);

	
	static Config config;
    public static Connection connection;
    static{
    	try {
			config	=	Config.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	

	 public static void sqlConnectionwithDBName(String DBname) throws Exception 
	 {

	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + config.getConfig("ForwardPort") + "/" + DBname,config.getConfig("DBUsername"),config.getConfig("DBPassword"));
			System.out.println("SQL DB connection created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	 }
    
	 public static Connection  sqlConnectionwithDBNameEx(String DBname) throws Exception 
	 {

	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + config.getConfig("ForwardPort") + "/" + DBname,config.getConfig("DBUsername"),config.getConfig("DBPassword"));
			System.out.println("SQL DB connection created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	 }
	public static void sqlConnection() throws Exception {

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:"+config.getConfig("ForwardPort"), config.getConfig("DBUsername"), config.getConfig("DBPassword"));
			System.out.println("SQL DB connection created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sqlConnectionite3(String dbName)
	{
	 try
	 {
		 System.out.println();
		 Class.forName("com.mysql.jdbc.Driver");
		 connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + config.getConfig("ForwardPort") + "/" + dbName, config.getConfig("DBUsername") ,config.getConfig("DBPassword"));
		 System.out.println("Sql db connection created successfully !!!");
	 }catch(Exception e){
		 e.printStackTrace();
	 }	
		
	}
	
	public static Connection sqlConnectionite3Ex(String dbName)
	{
	 try
	 {
		 System.out.println();
		 Class.forName("com.mysql.jdbc.Driver");
		 connection=DriverManager.getConnection("jdbc:mysql://127.0.0.1:" + config.getConfig("ForwardPort") + "/" + dbName, config.getConfig("DBUsername") ,config.getConfig("DBPassword"));
		 System.out.println("Sql db connection created successfully !!!");
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	return connection;	
		
	}
	
	public static Connection sqlConnectionRelStag(String dbName)
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("jdbc:mysql://"+config.getConfig("DATABASE.IP")+":"+config.getConfig("ForwardPort") + "/" + dbName);
			connection = DriverManager.getConnection("jdbc:mysql://"+config.getConfig("DATABASE.IP")+":"+config.getConfig("ForwardPort") + "/" + dbName,config.getConfig("DBUsername"),config.getConfig("DBPassword"));
			System.out.println("SQL DB connection created successfully !!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeConnection() throws SQLException{
		connection.close();
		System.out.println("SQL DB connection closed");
	}
	
  }