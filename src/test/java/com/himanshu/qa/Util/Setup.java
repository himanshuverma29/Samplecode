/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import org.codehaus.jackson.map.ObjectMapper;

import com.himanshu.qa.commonOps.CommonOperations;
import com.himanshu.qa.commonOps.Navigate;
import com.himanshu.qa.commonOps.OnFailure;
import com.himanshu.qa.commonOps.WebDriverOperations;

public class Setup {

	public static ObjectMapper objectMapper = new ObjectMapper();
	public static JSONUtil jsonUtil = new JSONUtil();
	public static BrowserUtil browserUtil = new BrowserUtil();
	public static ConnectionUtil connectionUtil = new ConnectionUtil();
	public static DBUtil dbUtil = new DBUtil();
	public static EmailUtil emailUtil = new EmailUtil();
	public static ExcelUtil excelUtil = new ExcelUtil();
	public static FileUtil fileUtil = new FileUtil();
	public static LogUtil logUtil = new LogUtil();
	public static MongoQuery mongoQuery = new MongoQuery();
	public static MongoUtil mongoUtil = new MongoUtil();
	public static ReportUtil reportUtil = new ReportUtil();
	public static RetryAnalyzer retryAnalyzer = new RetryAnalyzer();
	public static SSHManager sshManager = new SSHManager();
	public static TestListener testListener = new TestListener();
	public static WaitUtil waitUtil = new WaitUtil();
	public static WebDriverOperations webDriverOps = new WebDriverOperations();
	public static Navigate navigate=new Navigate();
	public static CommonOperations commonops = new CommonOperations();
	public static OnFailure onFailure = new OnFailure();
	public static SendEmail sendEmail = new SendEmail();
	public static DBQuery dbquery=new DBQuery();
	public static APIConfiguration apiConfiguration = new APIConfiguration();
}
