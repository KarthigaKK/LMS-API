package runner;

import org.junit.runner.RunWith;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features="Features",
		glue={"stepDefinitions.Skills.API", "stepDefinitions.User_API", "stepDefinitions.UserSkillMap_API","stepDefinitions.UserSkillMapGet_API"},
		dryRun = false,
		monochrome =true,
		plugin = {"pretty","json:target/cucumber-reports/reports.json",
				"junit:target/cucumber-reports/Cucumber.xml",
				"html:target/cucumber-reports/reports.html",
				"html:test-output/index.html"}
		)
		
public class Runner {

}
