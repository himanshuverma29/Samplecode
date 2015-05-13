/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WaitUtil {
	
	
	public WebElement fluentWait(WebDriver driver,final By locator) 
	{
	   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(50, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

	   WebElement webElement = wait.until(new Function<WebDriver, WebElement>()
			   {
	                public WebElement apply(WebDriver driver) 
	                {
	                    return driver.findElement(locator);
	              
	          }});
	  return webElement;
	};

	public WebElement fluentWaitForElementTextLength(WebDriver driver,final By locator) 
	{
	   Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

	   WebElement webElement= wait.until(new Function<WebDriver, WebElement>()
			   {
	                public WebElement apply(WebDriver driver) 
	                {
	                	if(driver.findElement(locator).getText().length()>0)	
	                    return driver.findElement(locator); 
	                	return driver.findElement(By.id("invalid"));
	                	
	                
	          }});
	  return webElement;
	};
	
	public static void sleep(long milliseconds)
	{
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static WebDriverWait explicitWaitByVisibilityOfElement(WebDriver driver, int seconds,WebElement el){
		WebDriverWait wait = new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(el));
		return wait;
	}
	
	public static void waitForPageLoad(WebDriver driver) throws InterruptedException{
		driver.wait(10000);
	}

}
