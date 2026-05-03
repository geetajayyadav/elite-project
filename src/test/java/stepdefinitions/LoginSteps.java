package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

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

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // 🔹 Username field (robust locator)
        WebElement usernameField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[contains(@name,'event') or contains(@id,'event')]")
                )
        );

        usernameField.clear();
        usernameField.sendKeys(username);

        // 🔹 Password field
        WebElement passwordField = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//input[@type='password']")
                )
        );

        passwordField.clear();
        passwordField.sendKeys(password);

        // 🔹 Login button
        WebElement loginBtn = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//button[contains(text(),'Login') or @type='submit']")
                )
        );

        loginBtn.click();

        System.out.println("Entered credentials: " + username + " / " + password);
    }

    @Then("user should see {string}")
    public void user_should_see_result(String result) {

        System.out.println("Expected result: " + result);

        // Temporary validation (you can improve later)
        if(result.equals("success")) {
            System.out.println("Login successful (assumed)");
        } else if(result.equals("error")) {
            System.out.println("Error message expected");
        } else {
            System.out.println("Validation message expected");
        }

        driver.quit();
    }
}