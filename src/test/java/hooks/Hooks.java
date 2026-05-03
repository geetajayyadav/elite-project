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
	        // Screenshot logic (unchanged)
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

	        // 🔥 FIXED VIDEO LINK
	        String sessionId = LoginSteps.sessionId;

	        if (sessionId != null) {

	            String videoUrl = "https://automation.lambdatest.com/video/" + sessionId;

	            String htmlLink = "<a href='" + videoUrl + "' target='_blank'>🎥 LambdaTest Video</a>";

	            System.out.println("VIDEO LINK: " + videoUrl);

	            scenario.log(htmlLink);   // ✅ THIS WORKS IN REPORT
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
}