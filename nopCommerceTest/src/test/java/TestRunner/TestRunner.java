package TestRunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/Signin.feature",    // here, .// means folder 
		glue="stepDefinitions",    // define file under stepDefinitions package
		dryRun=true,               // cross check all method should be executed properly or not. If we set it "false" then it execute actual run 
		monochrome=true,           // it will eliminate unnecessary characters in console window 
		plugin= {"pretty", 
				"html:target/HTMLReport/html_report.html", 
				"html:target/JSONReport/json_report.json"
				}
		)


public class TestRunner {

}
