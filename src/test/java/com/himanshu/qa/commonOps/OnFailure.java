/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.commonOps;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import com.himanshu.qa.TestBase.BaseTest;
import com.himanshu.qa.UIProjects.JSErrorPageWise.JSErrorHelper;

/**
 * @author John Haider john.haider@snapdeal.com
 *         Date: 10.04.2015
 */
public class OnFailure extends TestListenerAdapter {
	int c =1;
	
    @Step("Hi, I'm step in your testng listener")
    @Override
    public void onTestFailure(ITestResult tr) {
        createAttachment();
    }

    @Attachment
    public byte[] createAttachment(){
    	WebDriverOperations webDriOps = new WebDriverOperations();
    	return webDriOps.createAttachment();
    }
    
    @Attachment
    public List<String> createAttachmentJSErrors(WebDriver driver, String operation, String pageName) throws IOException {
    	List<String> jsErrors = new ArrayList<String>();
    	//HashMap<String , String> hm = new HashMap<String, String>();
    	Map<String , String> hm = new HashMap<String, String>();
    	LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
        	//String jsErrorOneByOne = new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
        	String jsErrorOneByOne = entry.getMessage();
        	jsErrorOneByOne = jsErrorOneByOne.substring(0, jsErrorOneByOne.indexOf(" "));
        	if(entry.getMessage().substring(entry.getMessage().length()-15).contains("403 (Forbidden)") || entry.getMessage().substring(entry.getMessage().length()-15).contains("404 (Not Found)") || entry.getMessage().substring(entry.getMessage().length()-37).contains("503")){
        		if(!BaseTest.environment.contains("Main")){
        			
        		}else{
        			jsErrors.add(jsErrorOneByOne);
        			hm.put(operation, jsErrorOneByOne);
        		}
        	}else{
        		jsErrors.add(jsErrorOneByOne);
        		hm.put(operation, jsErrorOneByOne);
        	}
        }

        if(hm.size()>0){
        	File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
     		FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+"/src/test/resources/"+operation+".jpg")); 
     		JSErrorHelper.generateBodyFileForHTMLReport(driver, pageName, hm, operation);
        }
        return jsErrors;
    }
}