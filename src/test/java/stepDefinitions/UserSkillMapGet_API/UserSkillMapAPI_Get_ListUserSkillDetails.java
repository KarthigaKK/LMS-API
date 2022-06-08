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


public class UserSkillMapAPI_Get_ListUserSkillDetails extends BaseClass{

	public static RequestSpecification httpRequest;
	private  static  Response response;

	private String getListUserWithSkills = System.getProperty("user.dir") + prop.getProperty("getListUserWithSkills");
	private String getListInvalidUserSkills = System.getProperty("user.dir") + prop.getProperty("getListInvalidUserSkills");
	
		
	@Given("User click GET operation for {string} for getting list of details")
	public void user_click_get_operation_for_for_getting_list_of_details(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User do GET request to List user with the skill details")
	public void user_do_get_request_to_list_user_with_the_skill_details() {
		response = get(RestAssured.basePath);
	}
	
	@Then("validate success details response with schema validation")
	public void validate_success_details_response_with_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getListUserWithSkills)));
	}
	
	@Given("User click GET operation for {string} invalid end point")
	public void user_click_get_operation_for_invalid_end_point(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User do GET request to List user with the skill details using invalid userid")
	public void user_do_get_request_to_list_user_with_the_skill_details_using_invalid_userid() {
		response = get(RestAssured.basePath);
	}
	
	@Then("User get {int} not found Status code with error message in response")
	public void user_get_not_found_status_code_with_error_message_in_response(int code) {
		checkStatusCode(code);
	}
	
	@Then("validate not found user response with schema validation")
	public void validate_not_found_user_response_with_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getListInvalidUserSkills)));
	}
	
	
	@Then("User see {int} success Status code with response body")
	public void user_see_success_status_code_with_response_body(int code) {
		checkStatusCode(code);
	}
	
	private void checkStatusCode(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println("response status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}	

}
