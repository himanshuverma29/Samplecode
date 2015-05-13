/*Author: Himanshu Verma
 Project: SampleCodeAutomation
 */
package com.himanshu.qa.Util;

import static com.jayway.restassured.RestAssured.given;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.specification.RequestSpecification;

public class JSONUtil {
	String apiUrl,apiBody;
	
	public String jsonPost(String APIUrl, String APIBody ){
	
	// Creating object for JSON request
	RequestSpecBuilder builder = new RequestSpecBuilder();
		
	// Set Request Body
	builder.setBody(APIBody);
	
	// Set Type of body sending to the request
	builder.setContentType("application/json; charset=UTF-8");
	
	// Building request 
	RequestSpecification requestSpec = builder.build();
	
	// Hitting request using post method
	Response response = given().authentication().preemptive().basic("", "")
			.spec(requestSpec).when().post(APIUrl);
	
	// Saving response and return
	String res = response.body().asString();
	return res;
	}
	
}
