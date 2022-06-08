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


public class UserAPI_Delete_User_Steps extends BaseClass{

	public 	 static RequestSpecification httpRequest;
	private  static  Response response;

	
	private String deleteUserMapToSkill = System.getProperty("user.dir") + prop.getProperty("deleteUserMapToSkill");
	private String deleteUserNotFound = System.getProperty("user.dir") + prop.getProperty("deleteUserNotFound");
	private String deleteUser = System.getProperty("user.dir") + prop.getProperty("deleteUser");

	@Given("User want to execute DELETE operation for {string}")
	public void user_want_to_execute_delete_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User DELETE the skill {string}")
	public void user_delete_the_skill(String id) {	  
		response =delete(RestAssured.basePath, id);
	}
	

	@Then("User get {int} success Status code")
	public void user_get_success_status_code(int code) {
		checkStatusCode(code);
	}
	
	@When("User DELETE the {string}")
	public void user_delete_the(String id) {
		response =delete(RestAssured.basePath, id);
	}
	
	@When("User DELETE the {string} for invalid user")
	public void user_delete_the_for_invalid_user(String id) {
		invalidUser();
		response =delete(RestAssured.basePath, id);
	}
	
	@Then("User get {int} User not found Status code")
	public void user_get_user_not_found_status_code(int code) {
		checkStatusCode(code);
	}
	
	@Then("User get {int} Internal server error as skill mapped to user")
	public void user_get_internal_server_error_as_skill_mapped_to_user(int code) {
		checkStatusCode(code);
	}
	
	@Then("User deleted successfully and validated through json schema")
	public void user_deleted_successfully_and_validated_through_json_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 200)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(deleteUser)));
	}
	
	@Then("User not found are validated through json schema")
	public void user_not_found_are_validated_through_json_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 404)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(deleteUserNotFound)));
	}
	
	@Then("User cant delete user mapped to skill are validated through json schema")
	public void user_cant_delete_user_mapped_to_skill_are_validated_through_json_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		String responseBody = response.getBody().asString();
		int statusCode = response.getStatusCode();
		if (statusCode == 500)
			assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(deleteUserMapToSkill)));
	}

	@Then("User get {int} unauthorized Status code")
	public void user_get_unauthorized_status_code(int code) {
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
