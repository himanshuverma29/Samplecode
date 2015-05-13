/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.commonOps;

import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class Navigate {
	boolean appWinAppears=false;
	boolean campWinAppears=false;
	public void navigateTo(WebDriver driver, String url){
		driver.get(url);
		Assert.assertTrue(getResponseCode(url)==200);
		removeAppNotification(driver);
	}
	
	/*Returns Response Code*/
	public int getResponseCode(String url) {
		
        try {
            return Request.Get(url).execute().returnResponse().getStatusLine()
                    .getStatusCode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public void removeAppNotification(WebDriver driver){
		try
		{
			appWinAppears =  driver.findElement(By.xpath("//div[@id='android-notification-outer']//div[@class='notification-outer']")).isDisplayed();
		}
		catch(Exception e)
		{
		}
		
		if(appWinAppears==true)
		{
			driver.findElement(By.xpath("//div[@id='android-notification-outer']//div[@class='notification-outer']//button[@class='cancle_btn']")).click();
		}
		
		try {
			campWinAppears = driver.findElement(By.id("app_campaignShow")).isDisplayed();
		} catch (Exception e) {

		}
		if (campWinAppears == true) {
			driver.findElement(By.xpath("//i[@class='cmnsprite campaignCross']")).click();
		}
	}
	
	public void removeAppNotification1(WebDriver driver)
	{
		boolean campWinAppears=false;
		try {
			campWinAppears = driver.findElement(By.id("app_campaignShow")).isDisplayed();
		}
		catch (Exception e) 
		{

		}
		if (campWinAppears == true) {
			driver.findElement(By.xpath("//i[@class='cmnsprite campaignCross']")).click();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		}
	}
	
}
