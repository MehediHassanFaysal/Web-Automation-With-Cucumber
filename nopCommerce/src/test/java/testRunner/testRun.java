package testRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features=".//Features/",
		glue="stepDefintions",
		dryRun=false,     // true means it just cross check to available methods
		monochrome=true, // eliminate unnecessary character from console 
		plugin= {
				"pretty",
				"html:target/html_report/html-report.html",
				"json:target/json_report/json-report.json"
		},
		tags= "@sanity or @regression"
//		tags="@x"
		)


public class testRun {}
