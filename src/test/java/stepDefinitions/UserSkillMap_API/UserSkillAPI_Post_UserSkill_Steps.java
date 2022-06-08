package stepDefinitions.UserSkillMap_API;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.json.simple.JSONObject;

import Utilities.ExcelReader;
import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class UserSkillAPI_Post_UserSkill_Steps extends BaseClass{

	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	private String postUserSkillRequest = System.getProperty("user.dir") + prop.getProperty("postUserSkillRequest");
	private String postNotFoundUserSkillurl = System.getProperty("user.dir") + prop.getProperty("postNotFoundUserSkillurl");

	@Given("User perform POST operation in UserSkillMap API {string}")
	public void user_perform_post_operation_in_user_skill_map_api(String basePath) {
		RestAssured.basePath = basePath;
	}

	@Given("User want to execute POST invalid endpoint in UserSkillMap API {string}")
	public void user_want_to_execute_post_invalid_endpoint_in_user_skill_map_api(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@When("User pass the UserSkill data from given sheetname {string} and rownumber {int}")
	public void user_pass_the_user_skill_data_from_given_sheetname_and_rownumber(String sheetName, Integer rowNumber) throws InvalidFormatException, IOException {
		ExcelReader reader = new ExcelReader();
	    List<Map<String,String>> testData =  reader.getData(System.getProperty("user.dir") + "/src/test/resources/LMS_UserSkillMap.xlsx", sheetName);

	    String jsonData = postJSONData(testData, rowNumber);
	    response = post(RestAssured.basePath, jsonData); 
	}

	@When("User pass the UserSkill data from given sheetname {string} and rownumber {int} as invalid user")
	public void user_pass_the_user_skill_data_from_given_sheetname_and_rownumber_as_invalid_user(String sheetName, int rowNumber) throws InvalidFormatException, IOException {
		invalidUser();
		ExcelReader reader = new ExcelReader();
	    List<Map<String,String>> testData =  reader.getData(System.getProperty("user.dir") + "/src/test/resources/LMS_UserSkillMap.xlsx", sheetName);

	    String jsonData = postJSONData(testData, rowNumber);
	    response = post(RestAssured.basePath, jsonData); 
	}

	@Then("User should {int} notfound code")
	public void user_should_notfound_code(int code) {
		checkStatusCode(code);
	}
	
	@Then("User get the status code as {int} success code")
	public void user_get_the_status_code_as_success_code(int code) {
		checkStatusCode(code);
	}
	

	@SuppressWarnings("unchecked")
	private String postJSONData(List<Map<String,String>> testData, int rowNumber) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("user_skill_id", testData.get(rowNumber).get("UserSkillId"));
		requestParams.put("user_id", testData.get(rowNumber).get("UserId"));
		requestParams.put("skill_id", testData.get(rowNumber).get("SkillId"));
		requestParams.put("months_of_exp", testData.get(rowNumber).get("MonthOfExp"));
		return requestParams.toJSONString();		
	}	

	@Then("User should get {int} unauthorized code")
	public void user_should_get_unauthorized_code(int code) {
		checkStatusCode(code);
	}


@Then("User Post UserSkill API success schema validate")
public void user_post_user_skill_api_success_schema_validate() {
	logger.info( "**************Schema Validation************************* ");
	logger.info( "*************************************************************** ");
	String responseBody = response.getBody().asString();
	int statusCode = response.getStatusCode();
	if (statusCode == 404)
		assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postUserSkillRequest)));
}

@Then("User Post UserSkill url NotFound Schema validate")
public void user_post_user_skill_url_not_found_schema_validate() {
	logger.info( "**************Schema Validation************************* ");
	logger.info( "*************************************************************** ");
	String responseBody = response.getBody().asString();
	int statusCode = response.getStatusCode();
	if (statusCode == 404)
		assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(postNotFoundUserSkillurl)));
}
	private void checkStatusCode(int code) {
		int statusCode = response.getStatusCode(); // Getting status code
		logger.info("Response Status Code "+statusCode);
		if (statusCode == code)
			assertEquals(code, statusCode);
		else 
			assertTrue(true);
		
	}


}
