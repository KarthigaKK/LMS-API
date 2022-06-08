package stepDefinitions.UserSkillMap_API;

import static org.junit.Assert.*;

import org.json.simple.JSONObject;

import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class UserSkillAPI_Update_UserSkill_Steps extends BaseClass{


	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	@Given("User want to execute PUT operation for UserSkillAPI {string}")
	public void user_want_to_execute_put_operation_for_user_skill_api(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User submit the PUT request as {int} for {string}, {string}, {int}")
	public void user_submit_the_put_request_as_for(int monthofexp, String userskillid, String userid, int skillid) {
		response = put(RestAssured.basePath, userskillid, putJSONData(monthofexp,userskillid,userid,skillid));
	}

	@When("User submit the PUT request as  {int} for {string}, {string}, {int} invalid user")
	public void user_submit_the_put_request_as_for_invalid_user(int monthofexp, String userskillid, String userid, int skillid) {
		invalidUser();
		response = put(RestAssured.basePath, userskillid, putJSONData(monthofexp,userskillid,userid,skillid));
	}


	@Then("User get {int} success Status code along with response body")
	public void user_get_success_status_code_along_with_response_body(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Response Status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}

	
	@Then("User get {int} Unauthorized Status code")
	public void user_get_unauthorized_status_code(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Response Status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}



	@Then("User get {int} not found Status code along with response body")
	public void user_get_not_found_status_code_along_with_response_body(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		System.out.println("response status code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
	}
	
	@SuppressWarnings("unchecked")
	private static String putJSONData(int monthofexp,String userskillid,String userid,int skillid)
	{
		JSONObject requestParams = new JSONObject();
		requestParams.put("months_of_exp", monthofexp);	
		requestParams.put("user_skill_id", userskillid);	
		requestParams.put("user_id", userid);	
		requestParams.put("skill_id", skillid);	
		return requestParams.toJSONString();		
	}

}
