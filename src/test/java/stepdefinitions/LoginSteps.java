package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class LoginSteps {

    public static WebDriver driver;

    @Given("user is on login page")
    public void user_is_on_login_page() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wccqa.on24.com/webcast/login");

        System.out.println("Opened URL: " + driver.getCurrentUrl());
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // 🔥 FIND ANY TEXT INPUT (REALISTIC APPROACH)
        List<WebElement> inputs = wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName("input"))
        );

        WebElement usernameField = null;
        WebElement passwordField = null;

        for (WebElement input : inputs) {
            String type = input.getAttribute("type");

            if (type != null && type.equalsIgnoreCase("text")) {
                usernameField = input;
            }

            if (type != null && type.equalsIgnoreCase("password")) {
                passwordField = input;
            }
        }

        if (usernameField == null) {
            throw new RuntimeException("Username field NOT found");
        }

        if (passwordField == null) {
            throw new RuntimeException("Password field NOT found");
        }

        usernameField.clear();
        usernameField.sendKeys(username);

        passwordField.clear();
        passwordField.sendKeys(password);

        // 🔥 Click button (generic)
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button | //input[@type='submit']")
                )
        );

        loginBtn.click();

        System.out.println("Entered: " + username + " / " + password);
    }

    @Then("user should see {string}")
    public void user_should_see_result(String result) {

        System.out.println("Expected result: " + result);

        driver.quit();
    }
}