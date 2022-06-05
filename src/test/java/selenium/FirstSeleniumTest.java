package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class FirstSeleniumTest {

    public static void main(String[] args) throws InterruptedException {

        //WebDriverManager library
        WebDriverManager.chromedriver().setup();

        ChromeDriver driver = new ChromeDriver();

        driver.get("http://training.skillo-bg.com/posts/all");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        WebElement loginButton = driver.findElement(By.xpath("//*[@id='nav-link-login']"));
        loginButton.click();

        WebElement usernameOrEmailField = driver.findElement(By.xpath("//input[@id='defaultLoginFormUsername']"));
        usernameOrEmailField.click();
        usernameOrEmailField.sendKeys("angel131we");

        WebElement passwordField = driver.findElement(By.xpath("//input[@id='defaultLoginFormPassword']"));
        passwordField.click();
        passwordField.sendKeys("Test123!");

        WebElement signInButton = driver.findElement(By.xpath("//button[@id='sign-in-button']"));
        signInButton.click();

        WebElement successfulLoginMessage = driver.findElement(By.xpath("//*[text()[contains(.,'Successful login!')]]"));
        Assert.assertTrue(successfulLoginMessage.isDisplayed());

        driver.close();
    }
}
