/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.himanshu.qa.TestBase.BaseTest;

public class SearchAPI  extends BaseTest{

	
	public RFUrequestBody setRFUPogId(String pogId)
	
	{
		RFUrequestBody similarobj= new RFUrequestBody();
		similarobj.setProductOfferGroupId(pogId);
		return  similarobj;
	}

	
	public SimilarRFUproducts getSimilarRFUproductsobj(String RFUurl, RFUrequestBody reqbody) throws JsonGenerationException, JsonMappingException, IOException
	{
		SimilarRFUproducts similarRFU = new SimilarRFUproducts();
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(reqbody);
		String response = jsonUtil.jsonPost(RFUurl, body);
		SimilarRFUproducts responceObj = mapper.readValue(response, SimilarRFUproducts.class);
		return responceObj;
		
	}
	
	public TrendingProducts getTrendingNowProducts(String tnURL, TrendingNowReqBody reqbody) throws JsonGenerationException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		String body = mapper.writeValueAsString(reqbody);
		String response = jsonUtil.jsonPost(tnURL, body);
		TrendingProducts res= mapper.readValue(response,TrendingProducts.class);
		return res;
		
	}
}
