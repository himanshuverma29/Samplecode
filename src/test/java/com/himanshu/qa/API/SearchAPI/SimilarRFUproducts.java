/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.API.SearchAPI;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown=true)
public class SimilarRFUproducts
{
	
	@JsonProperty("validationErrors")
    private String[] validationErrors;


	@JsonProperty("responseCode")
    private String responseCode;

	@JsonProperty("protocol")
    private String protocol;

	@JsonProperty("successful")
    private String successful;

    private SimilarProductSRO similarProductSRO;

    public String[] getValidationErrors ()
    {
        return validationErrors;
    }

    public void setValidationErrors (String[] validationErrors)
    {
        this.validationErrors = validationErrors;
    }



    public String getResponseCode ()
    {
        return responseCode;
    }

    public void setResponseCode (String responseCode)
    {
        this.responseCode = responseCode;
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

    public SimilarProductSRO getSimilarProductSRO ()
    {
        return similarProductSRO;
    }

    public void setSimilarProductSRO (SimilarProductSRO similarProductSRO)
    {
        this.similarProductSRO = similarProductSRO;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [validationErrors = "+validationErrors+", message = "+message+", responseCode = "+responseCode+", protocol = "+protocol+", code = "+code+", successful = "+successful+", similarProductSRO = "+similarProductSRO+"]";
    }*/
}
			