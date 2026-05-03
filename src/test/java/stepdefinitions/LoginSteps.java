package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.time.Duration;

public class LoginSteps {

    public static WebDriver driver;

    @Given("user is on login page")
    public void user_is_on_login_page() {

        try {

            // 🔥 Replace with your credentials
            String username = "lmsajay05";
            String accessKey = "LT_UUaYaPJJBZXinr0KvwL7eDBdFHzJDsCwwTeqnKNGMAhgbeN";

            String gridURL = "https://" + username + ":" + accessKey + "@hub.lambdatest.com/wd/hub";

            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("browserName", "Chrome");
            caps.setCapability("browserVersion", "latest");

            caps.setCapability("LT:Options", new java.util.HashMap<String, Object>() {{
                put("platformName", "Windows 11");
                put("build", "Elite Project");
                put("name", "Login Test");
            }});

            driver = new RemoteWebDriver(new URL(gridURL), caps);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            driver.get("https://wccqa.on24.com/webcast/login");

            System.out.println("Opened on LambdaTest");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        WebElement usernameField = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.xpath("//button | //input[@type='submit']"));
        loginBtn.click();
    }

    @Then("user should see {string}")
    public void user_should_see_result(String result) {

        System.out.println("Result: " + result);

        driver.quit();
    }
}