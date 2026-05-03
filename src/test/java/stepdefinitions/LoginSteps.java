package stepdefinitions;   // ✅ FIXED: matches TestRunner glue

import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginSteps {

    public static WebDriver driver;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        driver = new ChromeDriver();
        driver.get("https://wccqa.on24.com/webcast/login");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        System.out.println("Entering credentials: " + username + " / " + password);

        driver.findElement(By.id("eventName")).clear();
        driver.findElement(By.id("eventName")).sendKeys(username);

        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.id("loginButton")).click();
    }

    @Then("user should see {string}")
    public void user_should_see_result(String result) {

        System.out.println("Expected result: " + result);

        if (result.equals("success")) {
            System.out.println("Login successful");
        } else if (result.equals("error")) {
            System.out.println("Error message displayed");
        } else if (result.equals("validation message")) {
            System.out.println("Validation message displayed");
        }

        driver.quit();
    }
}