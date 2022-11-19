package TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/",    // here, .// means folder 
		glue="stepDefinitions",    // define file under stepDefinitions package
		dryRun=false,               // cross check all method should be executed properly or not. If we set it "false" then it execute actual run 
		monochrome=true,           // it will eliminate unnecessary characters in console window 
		plugin= {"pretty", 
				"html:target/HTMLReport/html_report.html", 
				"html:target/JSONReport/json_report.json"
				},
		tags= ("@sanity or @regression")
		) 


public class TestRunner {

}
