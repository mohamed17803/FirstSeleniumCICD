package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;

    @Given("the user is on the home page")
    public void theUserIsOnTheHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.UrlNavigate();
    }

    @When("the user navigates to the login page")
    public void theUserNavigatesToTheLoginPage() {
        loginPage.LoginNavigate();
    }

    @And("the user logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String email, String password) throws InterruptedException {
        loginPage.LoginForm(email, password).ClickLogin();
    }

    @Then("the user should be logged in successfully")
    public void theUserShouldBeLoggedInSuccessfully() {
        loginPage.LoginAssert();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
