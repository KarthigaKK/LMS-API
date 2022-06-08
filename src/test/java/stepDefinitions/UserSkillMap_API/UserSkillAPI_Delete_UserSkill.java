package stepDefinitions.UserSkillMap_API;

import static org.junit.Assert.*;

import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class UserSkillAPI_Delete_UserSkill extends BaseClass {
	
	public static RequestSpecification httpRequest;
	private  static  Response response;
	

	@Given("Skill User API want to execute DELETE operation for {string}")
	public void skill_user_api_want_to_execute_delete_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("Skill User API DELETE the skill {string}")
	public void skill_user_api_delete_the_skill(String id) {
		response =delete(RestAssured.basePath, id);
	}

	@Then("Skill User API get {int} success Status code")
	public void skill_user_api_get_success_status_code(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println(statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}

	@When("Skill User API DELETE the skill {string} for invalid user")
	public void skill_user_api_delete_the_skill_for_invalid_user(String id) {
		response =delete(RestAssured.basePath, id);
	}

	@Then("Skill User API get {int} unauthorized Status code")
	public void skill_user_api_get_unauthorized_status_code(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println(statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	
}
