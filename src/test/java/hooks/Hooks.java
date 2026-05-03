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
    public void takeScreenshotAndAttachVideo(Scenario scenario) {

        try {
            // ===============================
            // ✅ EXISTING: Screenshot on failure (UNCHANGED)
            // ===============================
            if (scenario.isFailed()) {

                TakesScreenshot ts = (TakesScreenshot) LoginSteps.driver;

                byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);

                String fileName = scenario.getName().replaceAll(" ", "_");
                String path = "target/screenshots/" + fileName + ".png";

                Files.createDirectories(Paths.get("target/screenshots"));
                Files.write(Paths.get(path), screenshot);

                scenario.attach(screenshot, "image/png", fileName);

                System.out.println("Screenshot saved & attached: " + path);
            }

            // ===============================
            // 🔥 NEW: LambdaTest Video Link (ALWAYS ATTACHED)
            // ===============================
            String sessionId = LoginSteps.sessionId;

            if (sessionId != null) {

                String videoUrl = "https://automation.lambdatest.com/video/" + sessionId;

                System.out.println("LambdaTest Video Link: " + videoUrl);

                // Attach link in report
                scenario.attach(videoUrl, "text/plain", "LambdaTest Video");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}