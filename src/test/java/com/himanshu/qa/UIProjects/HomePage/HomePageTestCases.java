/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.UIProjects.HomePage;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.himanshu.qa.TestBase.BaseTest;
import com.himanshu.qa.UIpages.HomePage;
import com.himanshu.qa.Util.APIConfiguration;
import com.himanshu.qa.Util.BrowserUtil;
import com.himanshu.qa.Util.DBUtil;
import com.himanshu.qa.Util.SSHManager;
import com.himanshu.qa.Util.TestListener;

@Listeners(TestListener.class)
public class HomePageTestCases extends BaseTest{

	public static String url;
	String u=null;
	int loop=0;
	private HomePage homepage;
	private HomePageHelper helper;
	public String[] search_list={"bags","mobile","shoes","table"};
	Logger logger = LoggerFactory.getLogger(HomePageTestCases.class);
	Stack< String> img_url= new Stack<String>();
	Stack<String> prod_price= new Stack<String>();
	SoftAssert softassert= new SoftAssert();
	LinkedHashMap<String,String> map1 = new LinkedHashMap<String,String>();
	LinkedHashMap<String,Integer> map2 = new LinkedHashMap<String,Integer>();
	@BeforeClass
	public void beforeClass() throws Exception {
		logger.info("Invoking browser");
		driver = BrowserUtil.invokeBrowser("chrome", "win");
		url=config.getConfig("Driver.Url");
		homepage= new HomePage(driver).pageObject();
		helper= new HomePageHelper();
		Thread.sleep(2000);
		navigate.removeAppNotification1(driver);
		navigate.removeAppNotification(driver);
		logger.info("Go to home page");
		webDriverOps.openpage(url);
	}

	@Test (priority=1)
	public void testFooterDisplay() throws InterruptedException
	{

		logger.info("Scrolling Down");
		webDriverOps.scroll();
		Assert.assertEquals(webDriverOps.isDisplayed(homepage.footer),true);
	}

	@Test (priority=2)
	public void testCategoriesColor()
	{
		if(!BaseTest.environment.contains("main"))
		{
			throw new SkipException("Skipping Categories color test case");
		}

		logger.info("Counting total buckets");
		int l1=homepage.Categories_color.size();
		int i=0;
		logger.info("Printing CSS value of buckets");
		while(i<l1)
		{
			String rgb=homepage.Categories_color.get(i).getCssValue("color");
			String clr=commonops.convert_to_cssValue(rgb);
			System.out.println("Color of "+homepage.Categories_color.get(i).getText()+" : "+clr);
			i++;
		}

	}

	@Test (priority=3)
	public void testFooterCategories() throws InterruptedException
	{
		commonops.fluentWait(homepage.footer_links.get(0));
		int len=homepage.footer_links.size();
		logger.info("For each of the link in footer and checking its response code");
		for(int i=0;i<len;i++)
		{
			String currURL=webDriverOps.getattribute(homepage.footer_links.get(i),"href");
			int responseCode=navigate.getResponseCode(currURL);
			Assert.assertEquals(responseCode, 200);
			commonops.fluentWait(homepage.footer_links.get(0));
		}
	}
	@Test (priority=4)
	public void testRecentListIsDisplayed() throws InterruptedException
	{
		Thread.sleep(2000);
		navigate.removeAppNotification1(driver);
		navigate.removeAppNotification(driver);
		logger.info("Visiting 3 different products");
		logger.info("Going to home page");
		webDriverOps.openpage(url);
		commonops.implicitWaitTillSeconds(5);
		Thread.sleep(5000);
		Assert.assertEquals(webDriverOps.isDisplayed(homepage.recent_view),true);
	}

	@Test(priority=5)

	public void recentlyViewedBanner() throws NumberFormatException, Exception
	{
		webDriverOps.openpage(config.getConfig("prodImageBanner"));
		webDriverOps.openpage(url);
		String bannerUrlUI=webDriverOps.getattribute(homepage.eventBannerURL, "src");
		String cookiesValue=webDriverOps.getCookiebyName("mhprv");
		Set<String> hashSet1 = new HashSet<String>();
		Set<String> hashSet2 = new HashSet<String>();
		hashSet1=helper.modifyCookiesValue(cookiesValue);
		hashSet2=helper.getCategoryIDfromMHPRVCookie(hashSet1);
		SSHManager.createConnection();
		Connection connection=DBUtil.sqlConnectionRelStag("snapdeal");
		boolean flag=false;
		Iterator<String> it = hashSet2.iterator();
		while(it.hasNext())
		{
			String query="SELECT label_url FROM label_metadata WHERE id='"+it.next()+"'";
			String result=helper.getdatafromDB(connection, query, "label_url");
			String query1="SELECT event_banner_img FROM category_mobile_banner WHERE category_page_url='"+result+"' AND end_date > NOW() ORDER BY priority";
			String bannerUrl=helper.getdatafromDB(connection, query1, "event_banner_img");
			System.out.println(bannerUrl);
			if(bannerUrl!=null && bannerUrlUI.contains(bannerUrl))
			{
				flag=true;
				break;
			}
		}
		Assert.assertEquals(flag, true);
	}

	@Test  (priority=5)
	public void testRecentListCategoriesIsDisplayed() throws InterruptedException
	{
		Thread.sleep(2000);
		navigate.removeAppNotification1(driver);
		navigate.removeAppNotification(driver);
		logger.info("Going to homepage");
		webDriverOps.openpage(url);
		Thread.sleep(5000);
		webDriverOps.scrolltoelement(homepage.recent_view);
		commonops.fluentWait(homepage.recentCategoryBlock);
		Assert.assertEquals(webDriverOps.isDisplayed(homepage.recentCategoryBlock),true);
	}

	@AfterClass
	public void close()
	{
		webDriverOps.closeSession();
	}

}
