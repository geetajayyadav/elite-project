package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"stepdefinitions", "hooks"},   // ✅ IMPORTANT (add hooks)
    plugin = {
        "pretty",

        // ✅ JSON for Jenkins (KEEP SAME PATH)
        "json:target/cucumber-reports/cucumber.json",

        // ✅ HTML report (local debugging)
        "html:target/cucumber-reports/cucumber.html",

        // ✅ Console logs visible in report
        "summary"
    },
    monochrome = true
)
public class TestRunner {
}