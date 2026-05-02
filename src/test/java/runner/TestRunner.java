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
    	    "html:target/cucumber-report.html",
    	    "json:target/cucumber.json",
    	    "junit:target/cucumber.xml"
    },
    monochrome = true
)
public class TestRunner {
}