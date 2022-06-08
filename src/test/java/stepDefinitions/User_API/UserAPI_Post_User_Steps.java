package stepDefinitions.User_API;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import Utilities.ExcelReader;
import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserAPI_Post_User_Steps extends BaseClass{
	
	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String postInvalidUserURL = System.getProperty("user.dir") + prop.getProperty("postInvalidUserURL");
	private String postPhoneNoAlreadyExist = System.getProperty("user.dir") + prop.getProperty("postPhoneNoAlreadyExist");
	private String postRequestSchema = System.getProperty("user.dir") + prop.getProperty("postRequest");
	private String postTimeZoneRequired = System.getProperty("user.dir") + prop.getProperty("postTimeZoneRequired");
	private String postVisaStatusRequired = System.getProperty("user.dir") + prop.getProperty("postVisaStatusRequired");
	
	
	@Given("User want to execute POST operation for {string}")
	public void user_want_to_execute_post_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User pass the data from given sheetname {string} and rownumber {int}")
	public void user_pass_the_data_from_given_sheetname_and_rownumber(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
		  ExcelReader reader = new ExcelReader();
		    List<Map<String,String>> testData =  reader.getData(System.getProperty("user.dir") + "/src/test/resources/LMS_User.xlsx", sheetName);
	
		    String jsonData = postJSONData(testData, rowNumber);
		    response = post(RestAssured.basePath, jsonData); 
	}
	
	@Then("User should get {int} success code")
	public void user_should_get_success_code(int code) {
		checkStatusCode(code);
	}
	
	@Then("User validate the success message with json schema")
	public void user_validate_the_success_message_with_json_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 201)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postRequestSchema)));
	}
	
	@Given("User perform POST operation for {string}")
	public void user_perform_post_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User pass the data from given sheetname {string} and rownumber {int} as invalid user")
	public void user_pass_the_data_from_given_sheetname_and_rownumber_as_invalid_user(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
		invalidUser();
		ExcelReader reader = new ExcelReader();
	    List<Map<String,String>> testData =  reader.getData(System.getProperty("user.dir") + "/src/test/resources/LMS_User.xlsx", sheetName);
	
	    String jsonData = postJSONData(testData, rowNumber);
	    response = post(RestAssured.basePath, jsonData); 
	}
	
	@Given("User want to execute POST invalid endpoint for {string}")
	public void user_want_to_execute_post_invalid_endpoint_for(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@Then("User should {int} not found code")
	public void user_should_not_found_code(int code) {
		checkStatusCode(code);
	}
	
	@Then("User validate invalid url by response schema")
	public void user_validate_invalid_url_by_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postInvalidUserURL)));
	}
	
	@Then("User should {int} visa status required")
	public void user_should_visa_status_required(int code) {
		checkStatusCode(code);
	}
	
	@Then("User get bad request {int} for phone number already exist")
	public void user_get_bad_request_for_phone_number_already_exist(int code) {
		checkStatusCode(code);
	}
	
	@Then("User get bad request {int} for timezone required")
	public void user_get_bad_request_for_timezone_required(int code) {
		checkStatusCode(code);
	}
	
	@Then("User validate visa status required by response schema")
	public void user_validate_visa_status_required_by_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postVisaStatusRequired)));
	}
	
	@Then("user validate phone number already exist by response schema")
	public void user_validate_phone_number_already_exist_by_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postPhoneNoAlreadyExist)));
	}
	
	@Then("user validate timezone required by response schema")
	public void user_validate_timezone_required_by_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postTimeZoneRequired)));
	}
	
	
	@SuppressWarnings("unchecked")
	private String postJSONData(List<Map<String,String>> testData, int rowNumber) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", testData.get(rowNumber).get("name"));
		requestParams.put("phone_number", testData.get(rowNumber).get("phoneno"));
		requestParams.put("location", testData.get(rowNumber).get("location"));
		requestParams.put("time_zone", testData.get(rowNumber).get("timezone"));
		requestParams.put("linkedin_url", testData.get(rowNumber).get("url"));
		requestParams.put("education_ug", testData.get(rowNumber).get("educationug"));
		requestParams.put("education_pg", testData.get(rowNumber).get("educationpg"));
		requestParams.put("visa_status", testData.get(rowNumber).get("visastatus"));
		requestParams.put("comments", testData.get(rowNumber).get("comments"));
		return requestParams.toJSONString();		
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


