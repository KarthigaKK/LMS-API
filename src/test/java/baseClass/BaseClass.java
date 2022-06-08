package baseClass;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import Utilities.ReadConfig;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import junit.framework.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class BaseClass {
	
	public static RequestSpecification httpRequest;
	//public static Response response;
	public static String userName;
	public static String password;
	public static String skillSchemaPath;
	public static String userSchemaPath;
	public static String userskillSchemaPath;
	public ReadConfig readconfig = new ReadConfig();
	public Properties prop = readconfig.loadProperties(); 
	public static String dbValidationMsg;
	
	public static final Logger logger = LogManager.getLogger(BaseClass.class.getName());
	
	protected BaseClass() {
		logger.info("*********API Application lanuch **********");
		RestAssured.baseURI= prop.getProperty("URL");
		userName = prop.getProperty("Username");
		password = prop.getProperty("password");
		skillSchemaPath = System.getProperty("user.dir") + prop.getProperty("SkillSchema");
		userSchemaPath = System.getProperty("user.dir") + prop.getProperty("UserSchema");
		userskillSchemaPath = System.getProperty("user.dir") + prop.getProperty("UserSkillSchema");
	}

	
	public void invalidUser() {
		logger.info("*********User provided with invalid credentials **********");
		userName = prop.getProperty("invalidUsername");
		password = prop.getProperty("invalidpassword");
	}
	
	public static Response get(String basePath)
	{
		logger.info("*********"+"Get Request started " + basePath +"*************");
		Response usersResponse = RestAssured
									.given()
										.header("Authorization", getAuthorization(userName +":"+password))
										.header("Content-Type","application/json")
									.when()
									  .get(RestAssured.baseURI + basePath);
		getOutputResponse(usersResponse, false);
		logger.info("*********"+"Get Request Ended " + basePath +"*************");
		return usersResponse;
		
		
	}
	
	public static Response put(String basePath, String id, String value)
	{
		logger.info("*********"+"Put Request started " + basePath +"*************");
		boolean isInt = false;
		try {
			Integer.parseInt(id);
			isInt=true;
		}
		catch (Exception e) {}
		
		Response usersResponse = RestAssured
									.given()
										.header("Authorization", getAuthorization(userName +":"+password))
										.header("Content-Type","application/json")
										.body(value)
									.when()
									  .put(RestAssured.baseURI + basePath + "/" + ((isInt) ? Integer.parseInt(id) : id));
		getOutputResponse(usersResponse, true);	
		logger.info("*********"+"Put Request Ended " + basePath +"*************");
		return usersResponse;
	}
	
	public static Response post(String basePath, String value)
	{
		logger.info("*********"+"Post Request started " + basePath +"*************");
		Response usersResponse= RestAssured
									.given()
										.header("Authorization", getAuthorization(userName +":"+password))
										.header("Content-Type","application/json")
										.body(value)
									.when()
									  .post(RestAssured.baseURI + basePath);
				
		getOutputResponse(usersResponse, true);	
		logger.info("*********"+"Put Request Ended " + basePath +"*************");
		return usersResponse;
	}

	public static Response delete(String basePath, String id)
	{
		logger.info("*********"+"Delete Request started " + basePath +"*************");
		boolean isInt = false;
		try {
			Integer.parseInt(id);
			isInt=true;
		}
		catch (Exception e) {}
		
		Response usersResponse = RestAssured
									.given()
										.header("Authorization", getAuthorization(userName +":"+password))
										.header("Content-Type","application/json")
									.when()
									  .delete(RestAssured.baseURI + basePath + "/" + ((isInt) ? Integer.parseInt(id) : id));
		
		getOutputResponse(usersResponse, true);
		logger.info("*********"+"Delete Request Ended " + basePath +"*************");
		return usersResponse;
	}
	
	
	@SuppressWarnings("unchecked")
	public static Response getDBValidation(String basePath) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		logger.info("*********"+"DataBase Connection Starts" + basePath +"*************");
		Response usersResponse = null;
		String connectionUrl = "jdbc:sqlserver://localhost:1433;database=LMSAPI;user=yourusername;password=yourpassword;";
		String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		try {
			Class.forName(driver).newInstance();
			Connection connection = DriverManager.getConnection(connectionUrl);
			Statement statement = connection.createStatement();
			logger.info("*********Select Query for TBL_LMS_USER table from database*************");
            String selectSql = "SELECT skill_id, skill_name from TBL_LMS_USER where skill_id = 3";
            ResultSet resultSet = statement.executeQuery(selectSql);
            JSONObject requestParams = new JSONObject();
            String skillid = "";
            while (resultSet.next()) {
            	requestParams.put("skill_name", resultSet.getString(2));
            	skillid = resultSet.getString(1);
            }
            usersResponse = RestAssured
										.given()
											.header("Authorization", getAuthorization(userName +":"+password))
											.header("Content-Type","application/json")
											.body(requestParams.toJSONString())
										.when()
										  .put(RestAssured.baseURI + basePath + "/" + skillid);
			getOutputResponse(usersResponse, true);	
			logger.info("*********"+"DataBase Connection Ends" + basePath +"*************");
		} 
		catch (SQLException e) {
			logger.info("*******************Database connection exception***********************");
            dbValidationMsg = e.getMessage();
        }
		return usersResponse;
	}

	private static String getAuthorization(String credentials ) {
		logger.info("*********Authorization Encoding Started*************");
		byte[] encodedCredentials = Base64.encodeBase64(credentials.getBytes());
		logger.info("*********Authorization Encoding Done*************");
		return  "Basic " + new String(encodedCredentials);
	
	}
	
	private static void getOutputResponse(Response response, boolean isResMessage) {
		logger.info("*********Response Body Validation Started*************");
		ResponseBody responseBody = response.getBody();
		String responsemessage=responseBody.asPrettyString();
		//assertEquals(responsemessage.contains("message"),true);
		logger.info("*********"+"Response Body "+responsemessage+"*************");
		System.out.println(responseBody.asString());
		if (isResMessage && responseBody.asString() != "") {
			JsonPath jPath = responseBody.jsonPath();
			if (jPath != null && jPath.get("message") != null) {
				System.out.println(jPath.get("message").toString());
				logger.info(jPath.get("message").toString());
				logger.info("*********Response Body Validation Ended*************");
			}
		}		
	}
	
}
