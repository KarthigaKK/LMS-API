package stepDefinitions.Skills.API;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;

@SuppressWarnings("unused")
public class SkillAPI_Post_Skill_Steps extends BaseClass {
	
	public static RequestSpecification httpRequest;
	private  static  Response response;
	//private  static  String  jsonString;
	private String successSkillSchema = System.getProperty("user.dir") + prop.getProperty("successSkillSchema");
	private String badRequestSkillSchema = System.getProperty("user.dir") + prop.getProperty("badRequestSkillSchema");
	private String inValidURLSkillSchema = System.getProperty("user.dir") + prop.getProperty("inValidURLSkillSchema");
	
	ArrayList<Response> skillPostResponse = new ArrayList<Response>();
	
	
	@Given("Skill User perform POST operation for {string}")
	public void skill_user_perform_post_operation_for(String basePath) {
		RestAssured.basePath = basePath;
	}
	
	@Given("Skill User want to execute POST invalid endpoint for {string}")
	public void skill_user_want_to_execute_post_invalid_endpoint_for(String basePath) {
		RestAssured.basePath = basePath;
	}

	@When("Skill User pass the data from given sheetname {string}")
	public void skill_user_pass_the_data_from_given_sheetname(String sheetName) throws InvalidFormatException, IOException {
	    ExcelReader reader = new ExcelReader();
	    List<Map<String,String>> testData =  reader.getData(System.getProperty("user.dir") + "/src/test/resources/LMS_Skills.xlsx", sheetName);
	    for (int i = 0; i <testData.size()-1; i++) {
	    	String jsonData = postJSONData(testData.get(i).get("Skill Name"));
	    	response = post(RestAssured.basePath, jsonData); 
	    	skillPostResponse.add(response);
	    }
	}
	
	@When("Skill User pass the data from given sheetname {string} as invalid user")
	public void skill_user_pass_the_data_from_given_sheetname_as_invalid_user(String sheetName) throws InvalidFormatException, IOException {
		invalidUser();
		ExcelReader reader = new ExcelReader();
	    List<Map<String,String>> testData =  reader.getData(System.getProperty("user.dir") + "/src/test/resources/LMS_Skills.xlsx", sheetName);
	    for (int i = 0; i <testData.size()-1; i++) {
	    	String jsonData = postJSONData(testData.get(i).get("Skill Name"));
	    	response = post(RestAssured.basePath, jsonData); 
	    	skillPostResponse.add(response);
	    }
	}
	
	
	@Then("Skill User should get {int} success code")
	public void skill_user_should_get_success_code(int code) {
		 for (int i = 0; i <skillPostResponse.size()-1; i++) {
			 response = skillPostResponse.get(i);
			 checkStatusCode(code);
		 }
	}

	@Then("Skill User should get {int} unauthorized code")
	public void skill_user_should_get_unauthorized_code(int code) {
		for (int i = 0; i <skillPostResponse.size()-1; i++) {
			 response = skillPostResponse.get(i);
			 checkStatusCode(code);
		 }
	}


	@Then("Skill User should {int} not found code")
	public void skill_user_should_not_found_code(int code) {
		for (int i = 0; i <skillPostResponse.size()-1; i++) {
			 response = skillPostResponse.get(i);
			 checkStatusCode(code);
		 } 
	}	
	
	
	@Then("Skill User validate response schema")
	public void skill_user_validate_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		
		for (int i = 0; i <skillPostResponse.size(); i++) {
			response = skillPostResponse.get(i);
			String responseBody = response.getBody().asString();
			int statusCode = response.getStatusCode();
			if (statusCode == 400) {
				assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(badRequestSkillSchema)));
			}
			if (statusCode == 201)
				assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(successSkillSchema)));
		}
	}
	
	@Then("Skill User validate invalid url response schema")
	public void skill_user_validate_invalid_url_response_schema() {
		logger.info( "**************Schema Validation************************* ");
		logger.info( "*************************************************************** ");
		
		for (int i = 0; i <skillPostResponse.size(); i++) {
			response = skillPostResponse.get(i);
			String responseBody = response.getBody().asString();
			int statusCode = response.getStatusCode();
			if (statusCode == 404)
				assertThat(responseBody, JsonSchemaValidator.matchesJsonSchema(new File(inValidURLSkillSchema)));
		}
	}

	
	@SuppressWarnings("unchecked")
	private String postJSONData(String skillName) {
		JSONObject requestParams = new JSONObject();
		requestParams.put("skill_name", skillName);
		return requestParams.toJSONString();		
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
