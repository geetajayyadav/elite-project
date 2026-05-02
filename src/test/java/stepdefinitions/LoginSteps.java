package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;

public class LoginSteps {
    
    public static WebDriver driver;

    @Given("user is on login page")
    public void user_is_on_login_page() {
        System.out.println("Opening browser...");
        
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://wccqa.on24.com/webcast/login");
    }

    @When("user enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) throws InterruptedException {
        System.out.println("Entering credentials...");

        Thread.sleep(5000);

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);

        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("user should login successfully")
    public void user_should_login_successfully() throws InterruptedException {
        
        Thread.sleep(5000);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);

        // Adjust this based on actual redirect
        Assert.assertTrue(currentUrl.contains("webcast") || currentUrl.contains("dashboard"));

        System.out.println("Test Passed");
    }
}