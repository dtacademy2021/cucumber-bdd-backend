package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith (Cucumber.class)

@CucumberOptions (
		

		

		tags = "@smoke",    //"@negative or @smoke", "@smoke and @negative and @login"

		
		features = "src/test/resources/resources",  // feature file folder path
		glue = "step_definitions", // step def classes folder path
		
		plugin = {  "summary", // for generating step definition snippets
				    "html:target/html-report.html", // to generate basic cucumber html report
				    "json:target/cucumber.json", // to generate json report for pretty html report
				    "pretty", // add more details onto the console report
				    "rerun:target/failed-scenarios.txt" // to create a text file with the paths for failed scenarios to be re-run
		         }  
		
		,dryRun = true  // to run the test without launching, we need this to generate step def snippets
		
		,monochrome = true  // to display console characters correctly in Windows
		
		,stepNotifications = true	// to see the step reports for each step of scenarios
		
		,snippets = SnippetType.CAMELCASE // to generate snippets with camelcase syntax
				
		)
public class CukeRunner {

}
