package selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class HerokuAppTests {

    WebDriver driver;
    WebDriver wait;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void addRemoveElements() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        // Assert empty list ("Delete" buttons not displayed)
        List<WebElement> elementsContainerChildren = driver.findElements(By.xpath("//div[@id='elements']/descendant::*"));
        Assert.assertTrue(elementsContainerChildren.isEmpty());

        // Click "Add Element" button to add "Delete" buttons and verify they are displayed
        WebElement addElementButton = driver.findElement(By.xpath("//button[@onclick='addElement()']"));
        for (int i = 0; i < 3; i++) {
            addElementButton.click();
        }

        elementsContainerChildren = driver.findElements(By.xpath("//div[@id='elements']/descendant::*"));
        Assert.assertEquals(elementsContainerChildren.size(), 3);

        Thread.sleep(1000);
    }

    @Test
    public void checkboxes(){
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        WebElement checkboxA = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        WebElement checkboxB = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        boolean checkboxStateA = checkboxA.isSelected();
        boolean checkboxStateB = checkboxB.isSelected();

        checkboxA.click();
        if(checkboxStateA){
            Assert.assertFalse(checkboxA.isSelected());
        } else {
            Assert.assertTrue(checkboxA.isSelected());
        }

        checkboxB.click();
        if(checkboxStateB){
            Assert.assertFalse(checkboxB.isSelected());
        } else {
            Assert.assertTrue(checkboxB.isSelected());
        }

    }

    @Test
    public void switchWindows() {
        driver.get("http://the-internet.herokuapp.com/windows");

        WebElement clickHereLink = driver.findElement(By.linkText("Click Here"));
        clickHereLink.click();

        for (String window : driver.getWindowHandles()) {
            driver.switchTo().window(window);
        }

        Assert.assertEquals(driver.getCurrentUrl(), "http://the-internet.herokuapp.com/windows/new");

    }

    @Test
    public void iFrames(){
        driver.get("http://the-internet.herokuapp.com/iframe");
        driver.switchTo().frame("mce_0_ifr");
        WebElement text = driver.findElement(By.xpath("//*[@id='tinymce']//p"));
        text.clear();
        text.sendKeys("I've lost my keys");
        driver.switchTo().defaultContent();
        WebElement title = driver.findElement(By.xpath("//div[@class='example']/h3"));
    }
}
