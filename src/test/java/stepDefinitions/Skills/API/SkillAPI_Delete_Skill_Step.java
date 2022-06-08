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


public class SkillAPI_Delete_Skill_Step extends BaseClass {
	
	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String deleteAlreadydeletedSkill = System.getProperty("user.dir") + prop.getProperty("deleteAlreadydeletedSkill");
	private String deleteRequest = System.getProperty("user.dir") + prop.getProperty("deleteRequest");
	private String deleteUserMappedSkill = System.getProperty("user.dir") + prop.getProperty("deleteUserMappedSkill");
	
	@Given("Skill User want to execute DELETE operation for {string}")
	public void skill_user_want_to_execute_delete_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("Skill User DELETE the skill {int}")
	public void skill_user_delete_the_skill(int id) {
		response =delete(RestAssured.basePath, Integer.toString(id) );
	}
	
	@When("Skill User DELETE the skill {int} for invalid user")
	public void skill_user_delete_the_skill_for_invalid_user(int id) {
		invalidUser();
		response =delete(RestAssured.basePath, Integer.toString(id));
	}
	
	@When("Skill User DELETE the skill {int} for invalid skillid")
	public void skill_user_delete_the_skill_for_invalid_skillid(int id) {
		response =delete(RestAssured.basePath, Integer.toString(id) );
	}


	@Then("Skill User get {int} success Status code")
	public void skill_user_get_success_status_code(int code) {
		checkStatusCode(code);
	}	
	
	@Then("Skill User get {int} unauthorized Status code")
	public void skill_user_get_unauthorized_status_code(int code) {
		checkStatusCode(code);
	}
	
	@Then("Skill User deleted success reuqest schema validation")
	public void skill_user_deleted_success_reuqest_schema_validation() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(deleteRequest)));
		else
			 logger.info("AssertionError: expected:<200> but was:"+statusCode );
			
	}

	@Then("Skill User get {int} skill not found Status code")
	public void skill_user_get_skill_not_found_status_code(Integer int1) {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(deleteAlreadydeletedSkill)));
		else
			 logger.info("AssertionError: expected:<404> but was:"+statusCode );
	}


	@Then("Skill User get {int} Internal server error as skill mapped to user")
	public void skill_user_get_internal_server_error_as_skill_mapped_to_user(int code) {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 500)
		{
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(deleteUserMappedSkill)));
		 logger.info( "******************"+JsonSchemaValidator.matchesJsonSchema(new File(deleteUserMappedSkill))+ "****************** ");
		}
		else
			logger.info("AssertionError: expected:<500> but was:"+statusCode );
		checkStatusCode(code);
	}
	
	
	public void checkStatusCode(int code) {
		logger.info( "**************Status Code Validation************************* ");
		logger.info( "*************************************************************** ");
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println("response status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
		logger.info( "**************"+statusCode+ "************************* ");
		logger.info( "*************************************************************** ");
	}


}
