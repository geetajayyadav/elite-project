package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import stepdefinitions.LoginSteps;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    @After
    public void takeScreenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) LoginSteps.driver;
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

                String path = "target/screenshots/" + scenario.getName() + ".png";
                Files.createDirectories(Paths.get("target/screenshots"));
                Files.write(Paths.get(path), screenshot);

                System.out.println("Screenshot saved: " + path);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}