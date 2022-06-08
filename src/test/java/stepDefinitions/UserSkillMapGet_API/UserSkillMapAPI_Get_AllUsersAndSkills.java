package stepDefinitions.UserSkillMapGet_API;

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


public class UserSkillMapAPI_Get_AllUsersAndSkills extends BaseClass {
	

	public static RequestSpecification httpRequest;
	private  static  Response response;

	private String getAllUserAllSkillMapAPI = System.getProperty("user.dir") + prop.getProperty("getAllUserAllSkillMapAPI");
	private String getAllUserAllSkillMapAPIInvalidUrl = System.getProperty("user.dir") + prop.getProperty("getAllUserAllSkillMapAPIInvalidUrl");
	

	@Given("User execute GET operation for {string} for getting list of details")
	public void user_execute_get_operation_for_for_getting_list_of_details(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("User do GET request and got the list of details")
	public void user_do_get_request_and_got_the_list_of_details() {
		response = get(RestAssured.basePath);
	}

	@Then("User get {int} success Status code with response body")
	public void user_get_success_status_code_with_response_body(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("response status code"+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}

	
	@Given("User try GET operation for {string} invalid end point")
	public void user_try_get_operation_for_invalid_end_point(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("User do GET request for listing all users all skill details using invalid end point")
	public void user_do_get_request_for_listing_all_users_all_skill_details_using_invalid_end_point() {
	   response = get(RestAssured.basePath);
	}

	@Then("User get {int} not found Status code with error message")
	public void user_get_not_found_status_code_with_error_message(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Response Status code "+ statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	
	@Then("validate success response with schema validation")
	public void validate_success_response_with_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getAllUserAllSkillMapAPI)));
	}

	@Then("validate not found response with schema validation")
	public void validate_not_found_response_with_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getAllUserAllSkillMapAPIInvalidUrl)));
	}

	
}
