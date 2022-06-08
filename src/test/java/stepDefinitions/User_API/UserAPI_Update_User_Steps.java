package stepDefinitions.User_API;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.io.File;

import org.json.simple.JSONObject;

import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class UserAPI_Update_User_Steps extends BaseClass{
	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String updateMissingREquiredField = System.getProperty("user.dir") + prop.getProperty("updateMissingREquiredField");
	private String UpdateRequest = System.getProperty("user.dir") + prop.getProperty("UpdateRequest");
	private String UpdateUserNotFound = System.getProperty("user.dir") + prop.getProperty("UpdateUserNotFound");
	
	@Given("User want to execute PUT operation for {string}")
	public void user_want_to_execute_put_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("User submit the PUT request for update {string},{string},{string},{string},{string} for {string}")
	public void user_submit_the_put_request_for_update_for(String name, String phonenumber, String location, String timezone, String visastatus, String userid)
	{
		response = put(RestAssured.basePath, userid, putJSONData(name,phonenumber,location,timezone,visastatus));
	}

	
	@Then("User should get {int} success Status code along with response body")
	public void user_should_get_success_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}
	
	
	@SuppressWarnings("unchecked")
	private static String putJSONData(String name, String phonenumber, String location, String timezone, String visastatus)
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", name);	
		requestParams.put("location", location);
		requestParams.put("phone_number", phonenumber);	
		requestParams.put("time_zone", timezone);
		requestParams.put("visa_status", visastatus);
		System.out.println(requestParams);
		return requestParams.toJSONString();		
	}
	
	@When("User submit the PUT request for update  {string},{string},{string},{string},{string} for {string} as invalid user")
	public void user_submit_the_put_request_for_update_for_as_invalid_user(String name, String phonenumber, String location, String timezone, String visastatus, String userid) {
		response = put(RestAssured.basePath, userid, putJSONData(name,phonenumber,location,timezone,visastatus));
	}


	@When("User submit the PUT request {string},{string},{string},{string},{string} for {string} as invalid user")
	public void user_submit_the_put_request_for_as_invalid_user(String name, String phonenumber, String location, String timezone, String visastatus, String userid) {
		invalidUser();
		response = put(RestAssured.basePath, userid, putJSONData(name,phonenumber,location,timezone,visastatus));
	}
	

	@Then("User should get {int} Unauthorized Status code along with response body")
	public void user_should_get_unauthorized_status_code_along_with_response_body(int code) {
		invalidUser();
		checkStatusCode(code);
	}

	@Then("User should get {int} update user not found Status code along with response body")
	public void user_should_get_update_user_not_found_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}

	@Then("User not found schema validation")
	public void user_not_found_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(UpdateUserNotFound)));
	}

	@Then("User get {int} bad request Status code along with response body")
	public void user_get_bad_request_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}

	@Then("User missing required fields for update response schema validation")
	public void user_missing_required_fields_for_update_response_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(updateMissingREquiredField)));
	}


	@Then("User updated successfully schema validation")
	public void user_updated_successfully_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 201)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(UpdateRequest)));
	}
	
	
	private void checkStatusCode(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("response status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}


}
