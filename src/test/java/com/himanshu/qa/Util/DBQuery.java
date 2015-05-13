/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import org.testng.Reporter;

import com.himanshu.qa.Util.DBUtil;

public class DBQuery {
	
	public static String getOrderIDStatus(String testNumber) throws Exception
	{
		String query	=	"select count(*) from testing_table where code='"+testNumber+"'";
		Reporter.log("<br>Query executed : "+query);
		String result	=	executeQueryString(query);
		Reporter.log("<br>Query result : "+result);
		return result;
	}
	
	
	private static String executeQueryString(String query) throws SQLException
	{
		ResultSet resultSet = null;
        String result;
		try {
			Statement statement = DBUtil.connection.createStatement();
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
                  e.printStackTrace();
		}
		resultSet.next();
		result	=	resultSet.getString(1);
		return result;
	}
	
	
	public static String getClientId() throws SQLException
	{
		
		String query	=	"SELECT VALUE FROM testing_table WHERE  NAME='himanshu'";
		
		Reporter.log("<br>Executing Query : "+query);
		String result	=	executeQueryString(query);
		
		Reporter.log("<br>Returning result : " + result);
		return result;
		
	}
	

}
