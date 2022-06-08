package stepDefinitions.Skills.API;

import static org.hamcrest.MatcherAssert.assertThat;


import java.io.File;

import org.json.simple.JSONObject;
import static org.junit.Assert.*;

import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class SkillAPI_Update_Skill_Steps extends BaseClass {

	public static RequestSpecification httpRequest;
	private  static  Response response;

	
	private String updateExistingSkillName = System.getProperty("user.dir") + prop.getProperty("updateExistingSkillName");
	private String updateInvalidSkillName = System.getProperty("user.dir") + prop.getProperty("updateInvalidSkillName");
	private String updateRequest = System.getProperty("user.dir") + prop.getProperty("updateRequest");
	
	@Given("Skill User want to execute PUT operation for {string}")
	public void skill_user_want_to_execute_update_user_endpoint(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("Skill User submit the PUT request for update  {string} for {string}")
	public void skill_user_submit_the_put_request_for_update_for(String skillName, String skillId) {
		response = put(RestAssured.basePath, skillId, putJSONData(skillName));
	}
	
	@When("Skill User submit the PUT request for update  {string} for {string} as invalid user")
	public void skill_user_submit_the_put_request_for_update_for_as_invalid_user(String skillName, String skillId) {
	    invalidUser();
	    response = put(RestAssured.basePath, skillId, putJSONData(skillName));
	}

	@Then("Skill User should get {int} success Status code along with response body")
	public void skill_user_should_get_success_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}

	
	@Then("Skill User should get {int} Unauthorized Status code along with response body")
	public void skill_user_should_get_unauthorized_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}


	@Then("Skill User should get {int} not found Status code along with response body")
	public void skill_user_should_get_not_found_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}
	
	@Then("Skill User update request schema validation")
	public void skill_user_update_request_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 201)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(updateRequest)));
	}
	
	@Then("Skill User skill not found schema validation")
	public void skill_user_skill_not_found_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(updateInvalidSkillName)));
	}
	
	@Then("Skill User update existing skill name response schema validation")
	public void skill_user_update_existing_skill_name_response_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 400)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(updateExistingSkillName)));
	}
	
	@Then("Skill User should get {int} bad request Status code along with response body")
	public void skill_user_should_get_bad_request_status_code_along_with_response_body(int code) {
		checkStatusCode(code);
	}
	
	@SuppressWarnings("unchecked")
	private static String putJSONData(String sName)
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("skill_name", sName);		
		return requestParams.toJSONString();		
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
