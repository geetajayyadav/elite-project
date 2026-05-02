package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources",
    glue = {"stepdefinitions", "hooks"},
    plugin = {
    	    "pretty",
    	    "html:target/cucumber-reports.html",
    	    "json:target/cucumber-reports/cucumber.json"
    },
    monochrome = true
)
public class TestRunner {
}