/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.xml.XmlTest;

import com.himanshu.qa.Config.Config;
import com.himanshu.qa.Util.BrowserUtil;
import com.himanshu.qa.Util.FileUtil;
import com.himanshu.qa.Util.Setup;

//import com.snapdeal.qa.Util.FileUtil;



public class BaseTest extends Setup{
	
	public static WebDriver driver;
	public static EventFiringWebDriver wbdv=null;
	public Config config;
	public static String browser, environment;
	/*Loads the environment config file and 
	invokes browser*/
	
	  @BeforeTest(alwaysRun=true)
	  public void setUp(XmlTest xmlTest) throws Exception
	  {
		 /* During development, if you want to run a single test case/class,
		  	you can give browser and environment here directly */ 
		  

		   /*String browser = "firefox";
		    String environment = "Staging";*/
		    browser = xmlTest.getParameter("browser"); 
			environment = xmlTest.getParameter("environment");
			System.out.println("Browser is = "+ browser +"  and Environment = "+ environment);

		    FileUtil.loadEnvironment(environment);

			config	=	Config.getInstance();
			//BrowserUtil.invokeBrowser(browser, config.getConfig("OS"));
			//BrowserUtil.invokeBrowser(browser, "win");
	  }
	  
/*	  Quits driver
*/	  
	  @AfterTest(alwaysRun=true)
	  public void tearDown()
	  {
		/*  if(driver!=null)
		 driver.quit();
*/	  }
		
	}
