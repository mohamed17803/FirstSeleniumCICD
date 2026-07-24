package testsPackage;

import factory.DriverFactory;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;

    @Parameters("browser")
    @BeforeMethod
    public void setUp(String browser) {
        DriverFactory.init(browser);
        driver = DriverFactory.get();
    }

    @AfterMethod
    @Description("Close the browser after the Test")
    public void tearDown() {
        DriverFactory.quit();
    }
}
