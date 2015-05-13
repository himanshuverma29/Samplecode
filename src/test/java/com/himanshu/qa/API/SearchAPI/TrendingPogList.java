/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

import org.codehaus.jackson.annotate.JsonProperty;

public class TrendingPogList
{
	@JsonProperty("trendingPog")
	private String trendingPog;

	public String getTrendingPog ()
	{
		return trendingPog;
	}

	public void setTrendingPog (String trendingPog)
	{
		this.trendingPog = trendingPog;
	}

	@Override
	public String toString()
	{
		return "ClassPojo [trendingPog = "+trendingPog+"]";
	}
}



