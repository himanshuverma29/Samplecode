/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SimilarProductSRO
{
	@JsonProperty("pogId")
    private String pogId;

	@JsonProperty("similarPOGIds")
    private List<String> similarPOGIds;

    public String getPogId ()
    {
        return pogId;
    }

    public void setPogId (String pogId)
    {
        this.pogId = pogId;
    }

    public List<String> getSimilarPOGIds ()
    {
        return similarPOGIds;
    }

    public void setSimilarPOGIds (List<String> similarPOGIds)
    {
        this.similarPOGIds = similarPOGIds;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [pogId = "+pogId+", similarPOGIds = "+similarPOGIds+"]";
    }
}
			
			
