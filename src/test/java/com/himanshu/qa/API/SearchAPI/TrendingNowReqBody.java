/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

public class TrendingNowReqBody {

	public TrendingNowReqBody()
	{
		this.requestProtocol="PROTOCOL_JSON";
		this.responseProtocol="PROTOCOL_JSON";
		this.startOffset="0";
		this.endOffset="20";
		
	}
	  private String requestProtocol;

	    private String endOffset;

	    private String responseProtocol;

	    private String startOffset;

	    public String getRequestProtocol ()
	    {
	        return requestProtocol;
	    }

	    public void setRequestProtocol (String requestProtocol)
	    {
	        this.requestProtocol = requestProtocol;
	    }

	    public String getEndOffset ()
	    {
	        return endOffset;
	    }

	    public void setEndOffset (String endOffset)
	    {
	        this.endOffset = endOffset;
	    }

	    public String getResponseProtocol ()
	    {
	        return responseProtocol;
	    }

	    public void setResponseProtocol (String responseProtocol)
	    {
	        this.responseProtocol = responseProtocol;
	    }

	    public String getStartOffset ()
	    {
	        return startOffset;
	    }

	    public void setStartOffset (String startOffset)
	    {
	        this.startOffset = startOffset;
	    }

	    @Override
	    public String toString()
	    {
	        return "ClassPojo [requestProtocol = "+requestProtocol+", endOffset = "+endOffset+", responseProtocol = "+responseProtocol+", startOffset = "+startOffset+"]";
	    }
}
