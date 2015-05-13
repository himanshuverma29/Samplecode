/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.UIProjects.HomePage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.sql.Statement;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.himanshu.qa.API.SearchAPI.RFUrequestBody;
import com.himanshu.qa.API.SearchAPI.SearchAPI;
import com.himanshu.qa.API.SearchAPI.SimilarRFUproducts;
import com.himanshu.qa.API.SearchAPI.TrendingNowReqBody;
import com.himanshu.qa.API.SearchAPI.TrendingProducts;
import com.himanshu.qa.Config.Config;
import com.himanshu.qa.TestBase.BaseTest;
import com.himanshu.qa.UIpages.HomePage;

public class HomePageHelper extends BaseTest
{
	
	String u=null;
	int loop=0;
	LinkedHashMap<String,String> map1 = new LinkedHashMap<String,String>();
	LinkedHashMap<String,Integer> map2 = new LinkedHashMap<String,Integer>();

	public LinkedHashMap<String, String> check(HomePage homePage)
			{
		if(map2.containsKey(homePage.getlabelid()))
		{
			if(map2.get(homePage.getlabelid())!=6)
			{
				map2.put(homePage.getlabelid(), map2.get(homePage.getlabelid())+1);
			}
		}
		else
		{
			//System.out.println("NewId Is added");
			map2.put(homePage.getlabelid(), 0);
		}

		if(map1.containsKey(homePage.getProductId()+"-"+homePage.getlabelid()))
		{
			//System.out.println("Same Product Visited again");
			map1.remove(homePage.getProductId()+"-"+homePage.getlabelid());
			map1.put(homePage.getProductId()+"-"+homePage.getlabelid(), homePage.getlabelid());
		}

		if(map1.size()<12 && map2.get(homePage.getlabelid())!=6)

		{
			//System.out.println("Visited products is less than 12 and catalog count is less than 6");
			map1.put(homePage.getProductId()+"-"+homePage.getlabelid(), homePage.getlabelid());
		}
		else 
		{

			if(map2.containsValue(6));
			{
				for(String i:map2.keySet())
				{
					if(map2.get(i)==6)
					{
						//		System.out.println(i);
						u=i;
						break;

					}

				}


				for (String j:map1.keySet())
				{
					if(map1.get(j).equals(u))
					{

						u=j;
						break;
					}
				}
				map1.remove(u);

				map1.put(homePage.getProductId()+"-"+homePage.getlabelid(), homePage.getlabelid());
				loop=1;
			}


			if(map1.size()==12 && loop!=1)
			{
				map1.remove(map1.entrySet().iterator().next());

				//System.out.println("Removed First");
			}

		}
		return map1;
			}

	/**
	 * Method to Modify Cookies
	 * @param cookiesValue
	 * @return
	 */
	public Set<String> modifyCookiesValue(String cookiesValue) {
		
		String newcookie=cookiesValue.substring(cookiesValue.indexOf("=")+1,cookiesValue.indexOf(";"));
		Set<String> hashSet = new HashSet<String>(Arrays.asList(newcookie.split("%")));
		Set<String> hashSet1 = new HashSet<String>();
		for(String m:hashSet)
		{
			hashSet1.add(m.replace("7C", ""));
		}

		return hashSet1;
	}

	public List<String> searcRFUProducts(Config config) throws Exception {

		List<String> arr= new ArrayList<String>();
		for(int i=0; i<3;i++)
		{
			webDriverOps.openpage(config.getConfig("Driver.Url")+config.getConfig("RFU"+ Integer.toString(i)));
			commonops.implicitWaitTillSeconds(5);
			Thread.sleep(5);
			SearchAPI searchapi= new SearchAPI();
			System.out.println(config.getConfig("RFUPogid"+ Integer.toString(i)));
			RFUrequestBody similarRFUReq=searchapi.setRFUPogId(config.getConfig("RFUPogid"+ Integer.toString(i)));
			SimilarRFUproducts responceObj =searchapi.getSimilarRFUproductsobj(config.getConfig("RFUurl"),similarRFUReq);
			arr.addAll(responceObj.getSimilarProductSRO().getSimilarPOGIds());
		}
		return arr;

	}
	
	public List<String>trendingproducts(Config config) throws JsonGenerationException, JsonMappingException, IOException, Exception
	{
		List<String> item2= new ArrayList<String>();
		SearchAPI searchapi= new SearchAPI();
		TrendingNowReqBody tnproducts= new TrendingNowReqBody();
		String tnURl=config.getConfig("tnURL");
		TrendingProducts tnresponse=searchapi.getTrendingNowProducts(tnURl, tnproducts);
		List<String> list = tnresponse.getTrendingPogList();
		for(int k=2;k<list.size();k=k+4){
			item2.add(list.get(k));
		}
		return item2;
		
	}

	public String getPogIdfromURL(String pogidHref) {
		String pogid=pogidHref.substring(pogidHref.lastIndexOf("/")+1);
		return pogid;
	}
	
	public String getdatafromDB(Connection connection, String query,String columnName)
	{
		String result = null;
		try
		{
			Statement stmt = connection.createStatement();
			ResultSet result_set = stmt.executeQuery(query);
			while(result_set.next())
			result=result_set.getString(columnName);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	return result;
	}
	
	
	public Set<String> getCategoryIDfromMHPRVCookie(Set<String> Set1)
	{
		Set<String> hashSet2 = new HashSet<String>();
		Iterator<String> it= Set1.iterator();
		while(it.hasNext())
		{
			String modify= it.next();
			hashSet2.add(modify.substring(modify.lastIndexOf('-')+1));
			//System.out.println(it.next().substring(it.next().lastIndexOf('-')));
		}
		return hashSet2;
		
	}
	
	
}	
