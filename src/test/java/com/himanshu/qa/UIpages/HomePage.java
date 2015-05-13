/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.UIpages;


import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.himanshu.qa.TestBase.BaseTest;

public class HomePage extends BaseTest{

	public String[] search_list={"bags","mobile","shoes","table"};
	
	
	
	@FindBy(id="testAnchor")
	public WebElement account_btn;
	
	public HomePage(WebDriver driver){
		
		this.driver=driver;
	}

	public HomePage pageObject() {

		return PageFactory.initElements(driver, HomePage.class);

	}
	
	
	//arpit
	
	@FindBy(xpath="//span[@class='inline-block']")
	public List <WebElement> Categories_color;
	
	@FindBy(xpath="//div[@class='catblock-header txtColor']/span")
	public List <WebElement> Categories;
	
	@FindBy(xpath="//div[@class='lnks blk']/a")
	public List<WebElement> footer_links;
	
	@FindBy(css=".new.blk")
	public WebElement footer;
	
	@FindBy(className="rvCategoryLink")
	public List <WebElement> recent_categories;
	
	@FindBy (id="recentlyViewed")
	public WebElement recent_view;
	
	@FindBy(id="rvCategories")
	public WebElement recentCategoryBlock;
	
	@FindBy(xpath="//div[@id='recentlyViewed']//div[@class='sp-image']")
	public List <WebElement>recentProducts;
	
	@FindBy(xpath="//div[@id='recentlyViewed']//div[@class='sp-price']")
	public List<WebElement> recentProductsPrice;
	
	@FindBy(xpath="//div[@id='recentlyViewed']//div[@class='sp-image']/img")
	public List<WebElement> recent_images;
	
	@FindBy (xpath="//span[@class='fright']")
	public WebElement tap_button;
	
	@FindBy(css=".sp-scroll-content.hide")
	public WebElement recommended_blk;
	
	@FindBy(xpath="//div[@class='sp-scroll-content hide']//div[@class='scroll-items']")
	public List<WebElement> recommend_images;
	
	@FindBy(xpath="//div[@class='sp-scroll-content hide']//div[@class='scroll-items']//a")
	public List<WebElement> recommend_images_url;
	
	@FindBy(xpath="//div[@class='sp-scroll-content hide']//div[@class='scroll-items']//a")
	public List<WebElement> recommendedProdcutsURL;
	
	@FindBy(xpath="//div[contains(@class,'seamLesspattern promotion' )]//a")
	public List<WebElement> trending_banners;
	
	@FindBy(id="trending-now-wget")
	public WebElement trendingNowBlk;
	
	@FindBy(xpath="//a[@class='trending-outer'][not(contains(@style,'display'))]")
	public List <WebElement> trendingNowProducts;
	
	@FindBy(xpath="//a[@id='rvCategoryBanner']/img")
	public WebElement eventBannerURL;
	
	public void click_tapButton() {
		commonops.implicitWaitTillSeconds(25);
		Assert.assertEquals(webDriverOps.isDisplayed(tap_button), true, "tap button is not present");
		webDriverOps.click(tap_button);
	}

	public String getlabelid() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductId() { 
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
