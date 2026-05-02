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
        driver.manage().window().maximize();
        driver.get("https://wccqa.on24.com/webcast/login");
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        // ✅ Click login button (update selector as needed)
        driver.findElement(By.id("loginBtn")).click();
    }

    @Then("user should login successfully")
    public void user_should_login_successfully() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // ✅ Verify login by checking URL or a post-login element
        wait.until(ExpectedConditions.urlContains("dashboard"));
        System.out.println("✅ Login successful! URL: " + driver.getCurrentUrl());
    }

    // ✅ Always quit driver after each scenario
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}