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
        "json:target/cucumber.json",
        "html:target/cucumber-report.html"
    },
    monochrome = true,
    publish = false
)
public class TestRunner {
}