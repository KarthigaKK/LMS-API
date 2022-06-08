package stepDefinitions.Skills.API;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import baseClass.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SkillAPI_Validate_Database extends BaseClass {

	 private static final Logger logger = LogManager.getLogger(SkillAPI_Validate_Database.class.getName());
	 
	public static RequestSpecification httpRequest;
	private  static  Response response;
	
	@Given("Skill User want to execute PUT operation for {string} for DB Validation")
	public void skill_user_want_to_execute_put_operation_for_for_db_validation(String basePath) {
		logger.info( "**************DataBase validation started************************* ");
		RestAssured.basePath = basePath;
	}

	@When("Skill User submit the PUT request in SQL DB")
	public void skill_user_submit_the_put_request_in_sql_db() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		response = getDBValidation(RestAssured.basePath);
	}
	
	@Then("Data base validation falied due to invalid database credentials")
	public void data_base_validation_falied_due_to_invalid_database_credentials() {
		logger.info("******************"+dbValidationMsg);
	    logger.info( "**************DataBase validation ended************************* ");
	}

}
