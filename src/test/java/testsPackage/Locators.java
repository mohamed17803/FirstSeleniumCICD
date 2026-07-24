package testsPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Locators {
    private WebDriver driver;

    @BeforeMethod
    @Test
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        By dropdown = By.id("dropdown");
      new Select(driver.findElement(dropdown)).selectByIndex(2);
    }
}
