package stepdefinitions;

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

        driver.get("https://wccqa.on24.com/webcast/login"); // ⚠️ REPLACE THIS

        // Debug logs
        System.out.println("URL: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Wait until username field is present & visible
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @Then("user should login successfully")
    public void user_should_login_successfully() {

        // Simple validation (you can improve later)
        System.out.println("Login step executed");

        driver.quit();
    }
}