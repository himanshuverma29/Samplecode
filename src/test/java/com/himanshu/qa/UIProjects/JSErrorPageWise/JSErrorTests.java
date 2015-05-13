/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.UIProjects.JSErrorPageWise;


import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.himanshu.qa.TestBase.BaseTest;
import com.himanshu.qa.UIpages.HomePage;
import com.himanshu.qa.Util.BrowserUtil;
//import com.snapdeal.qa.commonOps.OnFailure;

//@Listeners(OnFailure.class)
public class JSErrorTests extends BaseTest{
	private HomePage homePage;
 
	public List<String> listFromCreateAttachmentJSErrors = null;
	public String pageName;
	@BeforeClass
	public void beforeClass() throws Exception {
		homePage = new HomePage(driver).pageObject();
		driver = BrowserUtil.invokeBrowser("chrome", "mac");
	}
	
    @AfterClass
    public void afterClass() throws IOException {
    	FileReader fr = fileUtil.openOrCreateFileToRead(System.getProperty("user.dir")+"/src/test/resources/"+"HTMLReportBody.html");
    	if (fr.read() != -1) {
    		JSErrorHelper.generateHTMLReport();
        }    	
    	sendEmail.setEmail("tech.mobileqa@snapdeal.com");
        //driver.quit();
    }
   
    @AfterMethod
    public void afterMethod(ITestResult tr) throws InterruptedException, IOException{
    	Thread.sleep(3000);
    	listFromCreateAttachmentJSErrors = onFailure.createAttachmentJSErrors(driver, tr.getName().toString(), pageName);
    	/* To Print The Data On Console.*/
    	/*if(listFromCreateAttachmentJSErrors.size()!=0)
    	commonops.printContentsOfList(driver,listFromCreateAttachmentJSErrors);*/
    }
    
    @Test(priority = 1)
    public void testHomePage() throws Exception {
    	pageName = "Home Page";
    	navigate.navigateTo(driver, config.getConfig("BaseUrl"));
    }
}