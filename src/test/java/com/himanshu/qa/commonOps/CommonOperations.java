/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.commonOps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import com.google.common.base.Function;
import com.himanshu.qa.Config.Config;
import com.himanshu.qa.TestBase.BaseTest;

public class CommonOperations extends BaseTest{

	
	

	public WebElement fluentWait(final WebElement ele)
	{
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver)
			{
				return ele;
			}
		});
		return foo;
	};
	
	
	public void app_campaign()
	{
		boolean campWinAppears=false;

		try {

		campWinAppears = driver.findElement(By.cssSelector(".blk.iconblock")).isDisplayed();

		} catch (Exception e) {

		}



		if (campWinAppears == true) {

		driver.findElement(By.className("cancle_btn")).click();

		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		

		}
	}
	
	public String convert_to_cssValue(String rgb)
	{
		String rgb1[]=rgb.replaceAll("(rgba)|(rgb)|(\\()|(\\s)|(\\))","").split(",");
		String hex = String.format("#%s%s%s", toBrowserHexValue(Integer.parseInt(rgb1[0])), toBrowserHexValue(Integer.parseInt(rgb1[1])), toBrowserHexValue(Integer.parseInt(rgb1[2])));
		return hex;
	}
	private static String toBrowserHexValue(int number) {
        StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
        while (builder.length() < 2) {
            builder.append("0");
        }
        return builder.toString();
    }
	
	
	public boolean brokenImageCheck(WebElement ele)
	{
		boolean imagePresent = (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",ele);
		return imagePresent;
	}


	public void implicitWaitTillSeconds(int i)
	{
		driver.manage().timeouts().implicitlyWait(i, TimeUnit.SECONDS);
	}

	
	public ArrayList<String> listadd(int len, List<WebElement> element)
	{
		ArrayList<String> myarr= new ArrayList<String>();
		
		for(int i=0;i<len;i++)
		{
			try{
			myarr.add(element.get(i).getText().substring(0, 27));
			}
			catch(Exception e)
			{
				myarr.add(element.get(i).getText());
			}
			
		}
		return myarr;
	}
	
	
	public void selectByValue(WebElement element, String value)
	{
		Select sel = new Select(element);
		sel.selectByValue(value);
	}
	
	public void printContentsOfList(WebDriver driver, List<String> l) throws IOException{
    	for(String js: l){
    		System.out.println(js);
    	}
	}	
	
	
	public String getPogId(String url)
	{
		String[] url_array =url.substring(0, url.lastIndexOf('?')).split("/");
		int size_array = url_array.length;
		String pogID = url_array[size_array - 1];
		return pogID;
	}
	
	
	public String[] getSupcVendor(String href){
		String [] supc_vendor = new String[2];
		String[] array1 = href.split("supc=");
		String[] array2 = array1[1].split("&");
		supc_vendor[0] = array2[0];
		supc_vendor[1] = array2[1].replace("vendorCode=", "");
		return supc_vendor;
	}
	

	public String getPogIdEx(String url)
	{
		String[] url_array =url.split("/");
		int size_array = url_array.length;
		String pogID = url_array[size_array - 1];
		return pogID;
	}
	
}
	
	
	
