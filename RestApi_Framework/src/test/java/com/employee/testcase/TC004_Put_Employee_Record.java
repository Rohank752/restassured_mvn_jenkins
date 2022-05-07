/******************************************************
Test Name:Update an employee record
URI: http://dummy.restapiexample.com/api/v1/update/{id}
Request Type: PUT
Request Payload(Body): {"name":"XXXXX","salary":"XXXX","age":"XX"}

********* Validations **********
Response Payload(Body) : {"name":"XXXXX","salary":"XXXX","age":"XX"}
Status Code : 200
Status Line : HTTP/1.1 200 OK
Content Type : text/html; charset=UTF-8
Server Type :  nginx/1.14.1
Content Encoding : gzip
**********************************************************/

package com.employee.testcase;
import com.employee.base.TestBase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;


import com.employee.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC004_Put_Employee_Record extends TestBase {
	
	TC003_Post_Employee_Record tc03=new TC003_Post_Employee_Record();
	int emp_id=tc03.get_employee_id();

	RequestSpecification httpRequest;
	Response response;
	String empName=RestUtils.empName();
	String empSalary=RestUtils.empSal();
	String empAge=RestUtils.empAge();
	
	
	@BeforeClass
	void updateEmployee() throws InterruptedException
	{
		logger.info("*********Started TC004_Put_Employee_Record **********");
		
		RestAssured.baseURI = baseURI_data;
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
		//{"name":"John123X","salary":"123","age":"23"}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName); // Cast
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		// Add a header stating the Request body is a JSON
		httpRequest.header("Content-Type", "application/json");

		// Add the Json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/"+emp_id);
		
		Thread.sleep(5000);

	}
	
	@Test
	void checkResposeBody()
	{
		JsonPath jsonpath=response.jsonPath();
		String succ_msg=jsonpath.getString("status");
		System.out.println("Staus message"+ succ_msg);
		
		
		String responseBody = response.getBody().asString();
		System.out.println("Response body of Update"+responseBody );
				
		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.contains(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
	}
		
	@Test
	void checkStatusCode()
	{
		int statusCode = response.getStatusCode(); // Gettng status code
		Assert.assertEquals(statusCode, 200);
	}
		
	@Test
	void checkstatusLine()
	{
		String statusLine = response.getStatusLine(); // Gettng status Line
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
	}
	
	@Test
	void checkContentType()
	{
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json");
	}

	@Test
	void checkserverType()
	{
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "nginx");
	}

	@Test
	void checkcontentEncoding()
	{
		String contentEncoding = response.header("Content-Encoding");
		Assert.assertEquals(contentEncoding, null);

	}
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC004_Put_Employee_Record **********");
	}

}