/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrendingProducts {


	@JsonProperty("trendingPogList")
	private List<String> trendingPogList;

	@JsonProperty("validationErrors")
	private String[] validationErrors;

	@JsonProperty("protocol")
	private String protocol;

	@JsonProperty("successful")
	private String successful;

	public List<String> getTrendingPogList ()
	{
		return trendingPogList;
	}

	public void setTrendingPogList (List<String> trendingPogList)
	{
		this.trendingPogList = trendingPogList;
	}

	public String[] getValidationErrors ()
	{
		return validationErrors;
	}

	public void setValidationErrors (String[] validationErrors)
	{
		this.validationErrors = validationErrors;
	}


	public String getProtocol ()
	{
		return protocol;
	}

	public void setProtocol (String protocol)
	{
		this.protocol = protocol;
	}


	public String getSuccessful ()
	{
		return successful;
	}

	public void setSuccessful (String successful)
	{
		this.successful = successful;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [trendingPogList = "+trendingPogList+", validationErrors = "+validationErrors+", protocol = "+protocol+", successful = "+successful+"]";
	}
}

