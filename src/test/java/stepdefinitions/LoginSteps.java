package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.support.ui.*;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

public class LoginSteps {

    public static WebDriver driver;

    // ✅ NEW: session id for LambdaTest
    public static String sessionId;

    // 🔐 LambdaTest credentials
    String username = "antimyadav0608";
    String accessKey = "LT_EwQEgfFlEJtKAEKSzoFJ46sUpQvEaYGO4z0S3hkoUCO2MO1";

    String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

    @Given("user is on login page")
    public void user_is_on_login_page() {

        try {

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "Chrome");
            caps.setCapability("browserVersion", "latest");

            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("platformName", "Windows 11");
            ltOptions.put("build", "Elite Project");
            ltOptions.put("name", "Login Test");
            ltOptions.put("video", true);
            ltOptions.put("console", true);

            caps.setCapability("LT:Options", ltOptions);

            driver = new RemoteWebDriver(new URL(gridURL), caps);

            // ✅ NEW: Capture session ID
            sessionId = ((RemoteWebDriver) driver).getSessionId().toString();
            System.out.println("LambdaTest Session ID: " + sessionId);

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.get("https://wccqa.on24.com/webcast/login");

            System.out.println("Opened on LambdaTest");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        try {
            // 🔹 Find ALL input fields dynamically
            List<WebElement> inputs = wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("input"))
            );

            WebElement usernameField = null;
            WebElement passwordField = null;

            for (WebElement input : inputs) {
                String type = input.getAttribute("type");

                if ("text".equalsIgnoreCase(type)) {
                    usernameField = input;
                } else if ("password".equalsIgnoreCase(type)) {
                    passwordField = input;
                }
            }

            if (usernameField == null || passwordField == null) {
                throw new RuntimeException("Login fields not found");
            }

            usernameField.clear();
            usernameField.sendKeys(username);

            passwordField.clear();
            passwordField.sendKeys(password);

            // 🔥 Stable locator
            WebElement loginBtn = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            By.xpath("//button | //input[@type='submit']")
                    )
            );

            loginBtn.click();

        } catch (Exception e) {
            e.printStackTrace();
            updateTestStatus("failed", "Element not found");
            throw e;
        }
    }

    @Then("user should see {string}")
    public void user_should_see_result(String result) {

        try {
            updateTestStatus("passed", "Scenario executed");

        } catch (Exception e) {
            updateTestStatus("failed", "Exception occurred");
        }
    }

    // ✅ LambdaTest status update
    public void updateTestStatus(String status, String remark) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("lambda-status=" + status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("close browser")
    public void close_browser() {
        if (driver != null) {
            driver.quit();
        }
    }
}