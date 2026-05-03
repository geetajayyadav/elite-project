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
            // ✅ Screenshot (UNCHANGED)
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
            // 🔥 REAL FIX: Write video into JSON
            // ===============================
            String sessionId = LoginSteps.sessionId;

            if (sessionId != null) {

                //String videoUrl = "https://automation.lambdatest.com/video/" + sessionId;
                String videoUrl = "https://automation.lambdatest.com/logs/?sessionID=" + sessionId;

                String message = "🎥 LambdaTest Video: " + videoUrl;

                // ✅ THIS goes into cucumber.json
                scenario.attach(message.getBytes(), "text/plain", "LambdaTest Video");

                System.out.println("Video URL: " + videoUrl);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}