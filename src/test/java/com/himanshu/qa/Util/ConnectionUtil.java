/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.sql.SQLException;

import com.himanshu.qa.Util.DBUtil;
import com.himanshu.qa.Util.MongoUtil;

public class ConnectionUtil {
	
	
	
	public static final String sql	=	"sql";
	public static final String mongo	=	"mongo";

	
	
	public static void getConnection(String dbType) throws Exception
	{
		if(dbType.equals(sql))
			DBUtil.sqlConnection();
		else if(dbType.equals(mongo))
			MongoUtil.createMongoConnection();
		else
			System.out.println("NO matching DBType found");
	}
	
	public static void closeConnection(String dbType) throws SQLException
	{
		if(dbType.equals("sql"))
			DBUtil.closeConnection();
		else if(dbType.equals("mongo"))
			MongoUtil.closeConnection();
		else
			System.out.println("NO matching DBType found");	
	}
	
	

	
	
	


}
