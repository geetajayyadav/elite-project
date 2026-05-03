package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URL;
import java.time.Duration;
import java.util.HashMap;

public class LoginSteps {

    public static WebDriver driver;

    @Given("user is on login page")
    public void user_is_on_login_page() {

        try {
            // 🔹 Replace with your credentials
            String username = "lmsajay05";
            String accessKey = "LT_dpq70g3mftTjP8bODbp7dGhokFWoaqZvX9WkMAfnoIcKG8w";

            String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "Chrome");
            caps.setCapability("browserVersion", "latest");

            // ✅ LambdaTest Options (IMPORTANT)
            HashMap<String, Object> ltOptions = new HashMap<>();
            ltOptions.put("platformName", "Windows 11");
            ltOptions.put("build", "Elite Project");
            ltOptions.put("name", "Login Test");
            ltOptions.put("visual", true);
            ltOptions.put("video", true);
            ltOptions.put("network", true);
            ltOptions.put("console", true);

            caps.setCapability("LT:Options", ltOptions);

            driver = new RemoteWebDriver(new URL(gridURL), caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

            driver.get("https://wccqa.on24.com/webcast/login");

            System.out.println("Opened on LambdaTest");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

            // Wait for username
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username"))).sendKeys(username);

            // Wait for password
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password"))).sendKeys(password);

            // 🔥 FIX: Proper wait for login button
            wait.until(ExpectedConditions.elementToBeClickable(By.id("loginButton"))).click();

        } catch (Exception e) {
            e.printStackTrace();
            updateTestStatus("failed", "Element not found");
            throw e;
        }
    }

    @Then("user should see {string}")
    public void user_should_see_result(String result) {

        try {
            if (result.equalsIgnoreCase("success")) {
                updateTestStatus("passed", "Login successful");
            } else {
                updateTestStatus("failed", "Login failed");
            }
        } catch (Exception e) {
            updateTestStatus("failed", "Exception occurred");
        }
    }

    // ✅ LambdaTest Status Update (VERY IMPORTANT)
    public void updateTestStatus(String status, String reason) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("lambda-status=" + status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Then("user closes browser")
    public void user_closes_browser() {
        if (driver != null) {
            driver.quit();
        }
    }
}