/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

public class RFUrequestBody {

	
	   public RFUrequestBody() {
			
			this.requestProtocol = "PROTOCOL_JSON";
			this.responseProtocol = "PROTOCOL_JSON";
	   }

	    private String requestProtocol;

	    private String responseProtocol;

	    private String productOfferGroupId;

	    public String getRequestProtocol ()
	    {
	        return requestProtocol;
	    }

	    public void setRequestProtocol (String requestProtocol)
	    {
	        this.requestProtocol = requestProtocol;
	    }

	    public String getResponseProtocol ()
	    {
	        return responseProtocol;
	    }

	    public void setResponseProtocol (String responseProtocol)
	    {
	        this.responseProtocol = responseProtocol;
	    }

	    public String getProductOfferGroupId ()
	    {
	        return productOfferGroupId;
	    }

	    public void setProductOfferGroupId (String productOfferGroupId)
	    {
	        this.productOfferGroupId = productOfferGroupId;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [requestProtocol = "+requestProtocol+", responseProtocol = "+responseProtocol+", productOfferGroupId = "+productOfferGroupId+"]";
	    }
	
				
}
