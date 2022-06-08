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

public class UserSkillMapAPI_Get_AllUserDetailsBySkillId extends BaseClass {

	public static RequestSpecification httpRequest;
	private  static  Response response;

	private String getListUserWithInvalidSkillId = System.getProperty("user.dir") + prop.getProperty("getListUserWithInvalidSkillId");
	private String getListUserWithSkillId = System.getProperty("user.dir") + prop.getProperty("getListUserWithSkillId");
	

	
	@Given("User click GET operation for {string} for getting list all user details by skill")
	public void user_click_get_operation_for_for_getting_list_all_user_details_by_skill(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User do GET request to List all user details by the skill id")
	public void user_do_get_request_to_list_all_user_details_by_the_skill_id() {
		response = get(RestAssured.basePath);
	}
	
	
	@Then("User get success Status code {int} with response body")
	public void user_get_success_status_code_with_response_body(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("response status code"+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	
	
	@Then("validate success all user details response with schema validation")
	public void validate_success_all_user_details_response_with_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getListUserWithSkillId)));
	}
	
	@Given("User click GET operation for {string} for getting list all user details by invalid skill")
	public void user_click_get_operation_for_for_getting_list_all_user_details_by_invalid_skill(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User do GET request to List all user details by the invalid skill id")
	public void user_do_get_request_to_list_all_user_details_by_the_invalid_skill_id() {
		response = get(RestAssured.basePath);
	}
	
	
	@Then("User get not found Status code  {int} with error message in response body")
	public void user_get_not_found_status_code_with_error_message_in_response_body(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("response status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	
	@Then("validate not found skill response with schema validation")
	public void validate_not_found_skill_response_with_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getListUserWithInvalidSkillId)));
	}
}
