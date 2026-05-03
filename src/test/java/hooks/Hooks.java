package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import stepdefinitions.LoginSteps;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Hooks {

    @After
    public void takeScreenshotOnFailure(Scenario scenario) {

        if (scenario.isFailed()) {
            try {
                TakesScreenshot ts = (TakesScreenshot) LoginSteps.driver;

                // Capture screenshot
                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

                // Save to folder
                String fileName = scenario.getName().replaceAll(" ", "_");
                String path = "target/screenshots/" + fileName + ".png";

                Files.createDirectories(Paths.get("target/screenshots"));
                Files.write(Paths.get(path), screenshot);

                // ✅ Attach to Cucumber Report (IMPORTANT)
                scenario.attach(screenshot, "image/png", fileName);

                System.out.println("Screenshot saved & attached: " + path);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}