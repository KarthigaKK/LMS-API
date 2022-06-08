package stepDefinitions.UserSkillMap_API;

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


public class UserSkillAPI_Get_UserSkill_Steps extends BaseClass {

	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String getUserSkillRequest = System.getProperty("user.dir") + prop.getProperty("getUserSkillRequest");
	private String getSingleUserSkillRequest = System.getProperty("user.dir") + prop.getProperty("getSingleUserSkillRequest");
	
	@Given("User execute GET operation for {string}")
	public void user_execute_get_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User do the GET request")
	public void user_do_the_get_request() {
		response = get(RestAssured.basePath);
	}
	
	@When("User submit the GET request as invalid user")
	public void user_submit_the_get_request_as_invalid_user() {
		invalidUser();
		response = get(RestAssured.basePath);
	}
	

	@Then("User get user skill API {int} unauthorized Status code")
	public void user_get_user_skill_api_unauthorized_status_code(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Response Status Code "+statusCode);
		assertEquals(code, statusCode);
	}

	
	@Given("User want to execute GET invalid url {string}")
	public void user_want_to_execute_get_invalid_url(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@Then("User get {int} not found Status code")
	public void user_get_not_found_status_code(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Response Status Code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	
	
	@Then("User receive {int} success Status code")
	public void user_receive_success_status_code(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("response status code"+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	

@Then("validate schema for get all UserSkill request")
public void validate_schema_for_get_all_user_skill_request() {
	logger.info( "**************Schema Validation************************* ");
	logger.info( "*************************************************************** ");
	String responseBody = response.getBody().asString();
	int statusCode = response.getStatusCode();
	if (statusCode == 404)
		assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getUserSkillRequest)));
}

@Then("validate schema for single UserSkill request")
public void validate_schema_for_single_user_skill_request() {
	logger.info( "**************Schema Validation************************* ");
	logger.info( "*************************************************************** ");
	String responseBody = response.getBody().asString();
	int statusCode = response.getStatusCode();
	if (statusCode == 404)
		assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getSingleUserSkillRequest)));
}



}
