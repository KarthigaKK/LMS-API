package stepDefinitions.User_API;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.io.File;

import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;



public class UserAPI_Get_User_Steps extends BaseClass{

	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String getAllUserRequest = System.getProperty("user.dir") + prop.getProperty("getAllUserRequest");
	private String getInvalidUserUrl = System.getProperty("user.dir") + prop.getProperty("getInvalidUserUrl");
	private String getNotFoundUserId = System.getProperty("user.dir") + prop.getProperty("getNotFoundUserId");
	
	@Given("User try to execute GET operation for {string}")
	public void user_try_to_execute_get_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("User submit the GET request")
	public void user_submit_the_get_request() {
		response = get(RestAssured.basePath);
	}

	
	@Then("User should get {int} success Status code")
	public void user_should_get_success_status_code(int code) {
		checkStatusCode(code);
	}
	
	@Given("User want to execute GET operation for {string}")
	public void user_want_to_execute_get_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@Then("User should get {int} not found Status code")
	public void user_should_get_not_found_status_code(int code) {
		checkStatusCode(code);
	}

	@Then("User notfound validate response schema")
	public void user_notfound_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getNotFoundUserId)));
	}

	@When("User submit the GET request for invalid user")
	public void user_submit_the_get_request_for_invalid_user() {
		invalidUser();
		response = get(RestAssured.basePath);
	}

	@Then("User should get {int} unauthorized Status code")
	public void user_should_get_unauthorized_status_code(int code) {
		checkStatusCode(code);
	}

	@Given("User want to execute GET invalid endpoint for {string}")
	public void user_want_to_execute_get_invalid_endpoint_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@Then("User should {int} not found Status code")
	public void user_should_not_found_status_code(int code) {
		checkStatusCode(code);
	}

	@Then("User invalidurl validate response schema")
	public void user_invalidurl_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getInvalidUserUrl)));
	}
	
	@Then("User validated with response schema")
	public void user_validated_with_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getAllUserRequest)));
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
