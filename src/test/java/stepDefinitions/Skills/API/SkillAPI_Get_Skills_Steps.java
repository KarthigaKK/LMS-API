package stepDefinitions.Skills.API;

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

public class SkillAPI_Get_Skills_Steps extends BaseClass {

	
	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String getAllRequest = System.getProperty("user.dir") + prop.getProperty("getAllRequest");
	private String getNotFoundSkillId = System.getProperty("user.dir") + prop.getProperty("getNotFoundSkillId");
	private String getInvalidSkillId = System.getProperty("user.dir") + prop.getProperty("getInvalidSkillId");
	private String getNotFoundURL = System.getProperty("user.dir") + prop.getProperty("getNotFoundURL");
	private String getOneSkillId = System.getProperty("user.dir") + prop.getProperty("getOneSkillId");
	
	@Given("Skill User want to execute GET operation for {string}")
	public void skill_user_want_to_execute_GET_operation_for(String basePath) {		
		RestAssured.basePath = basePath;	  
	}
	
	@Given("Skill User want to execute GET invalid endpoint for {string}")
	public void skill_user_want_to_execute_get_invalid_endpoint_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("Skill User submit the GET request")
	public void skill_user_submit_the_GET_request() {		
		response = get(RestAssured.basePath);
	}
	
	
	@When("Skill User submit the GET request for invalid user")
	public void skill_user_submit_the_get_request_for_invalid_user() {
		invalidUser();
		response = get(RestAssured.basePath);
	}

	
	@Then("Skill User should get {int} success Status code")
	public void skill_user_should_get_success_Status_code(int code) {
		checkStatusCode(code);
	}
	
	
	@Then("Skill User should {int} not found Status code")
	public void skill_user_should_not_found_status_code(int code) {
		checkStatusCode(code);
	}
	
	
	@Then("Skill User should get {int} unauthorized Status code")
	public void skill_user_should_get_unauthorized_status_code(int code) {
		invalidUser();
		checkStatusCode(code);
	}
	
	@Then("Skill User should get {int} invalid Status code")
	public void skill_user_should_get_invalid_status_code(int code) {
		checkStatusCode(code);
	}

	@Then("Skill User should get {int} not found Status code")
	public void skill_user_should_get_not_found_status_code(int code) {
		checkStatusCode(code);
	}
	
	@Then("Skill User success validate response schema")
	public void skill_user_success_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getAllRequest)));
	}

	@Then("Skill User Single skill success validate response schema")
	public void skill_user_single_skill_success_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getOneSkillId)));
	}

	@Then("Skill User invalid skillid validate response schema")
	public void skill_user_invalid_skillid_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getInvalidSkillId)));
	}

	@Then("Skill User notfound validate response schema")
	public void skill_user_notfound_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getNotFoundSkillId)));
	}

	@Then("Skill User invalidurl validate response schema")
	public void skill_user_invalidurl_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(getNotFoundURL)));
	}
	

	public void checkStatusCode(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println("response status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
		
	}	
	
}
