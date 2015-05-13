/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.commonOps;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.himanshu.qa.TestBase.BaseTest;

public class WebDriverOperations extends BaseTest {
	
	

	public WebDriverOperations() {
		// TODO Auto-generated constructor stub
	}

	public void click(WebElement element) {

		element.click();
	}

	public void sendKeys(WebElement element, String text) {
	    element.clear();
		element.sendKeys(text);
	}
	public void sendKeysAfterTextRemove(WebElement element, String text) {
	    element.clear();
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
		element.sendKeys(text);
	}


	public void selectOptionByIndex(WebElement element, int index) {
       
		Select dropDown = new Select(element);
		dropDown.selectByIndex(index);
	}
	
	public void selectOptionByText(WebElement element,String text){
		
		Select dropDown = new Select(element);
		dropDown.selectByVisibleText(text);
		
	}

	public boolean isElementVisible(WebElement element) {

		if (element.isDisplayed()) {

			return true;
		}

		return false;
	}
	
	public String getText(WebElement element){

		return element.getText();
		
	}
	
	public void  makeElementVisible(String id,WebDriver driver){
		
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("document.getElementById(" + id + ").style.display='block';");
		
	}
	
	public void scroll(){
		
		((JavascriptExecutor) driver)
		.executeScript("javascript:window.onload=toBottom();"
				+ "function toBottom(){"
				+ "window.scrollTo(0,Math.max(document.documentElement.scrollHeight,"
				+ "document.body.scrollHeight,document.documentElement.clientHeight));"
				+ "}");
		
	}
	
	/****************  john.haider@snapdeal.com and arpit.aggarwal@snapdeal.com   ****************/
	
		public void goBack()
		{
			driver.navigate().back();
		}
		
		public void openpage(String url)
		{
			driver.get(url);
		}
		
		public String getCssValue(WebElement element, String element_property)
		{
			return element.getCssValue(element_property);
		}
		
		
		public boolean isDisplayed(WebElement element)
		{
			
			return element.isDisplayed(); 
		}
		
		
		public void refreshPage()
		{
			driver.navigate().refresh();
		}
		
		public String getCurrentPageURL()
		{
			return driver.getCurrentUrl();
		}
		
		public String getattribute(WebElement element, String attribute)
		{
			return element.getAttribute(attribute);
		}
		
		
		public void closeSession()
		{
			driver.quit();
		}
		
		public byte[] createAttachment(){
	    	return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES); 
	    }
		
		public void removeCookiebyName(String name)
		{
			driver.manage().deleteCookieNamed(name);
		}
		
		public String getCookiebyName(String name)
		{
			
			return driver.manage().getCookieNamed(name).toString();
		}
		
		public void removeallcookies()
		{
			driver.manage().deleteAllCookies();
		}
	
		public void scrolltoelement(WebElement ele)
		{
			WebElement element = ele;
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);
		}
		
	
}