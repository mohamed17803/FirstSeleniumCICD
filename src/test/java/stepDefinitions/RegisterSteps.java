package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Pages.RegisterPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegisterSteps {
    private WebDriver driver;
    private RegisterPage registerPage;

    @Given("the registration user is on the home page")
    public void theRegistrationUserIsOnTheHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registerPage = new RegisterPage(driver);
        registerPage.urlNavigate();
    }

    @When("the registration user navigates to the register page")
    public void theRegistrationUserNavigatesToTheRegisterPage() {
        registerPage.ListBehaviour();
    }

    @And("the registration user fills the form with first name {string} and last name {string} and email {string} and telephone {string} and password {string} and confirm password {string}")
    public void theRegistrationUserFillsTheForm(String firstName, String lastName, String email, String telephone, String password, String confirmPassword) {
        registerPage.RegistrationForm(firstName, lastName, email, telephone, password, confirmPassword);
    }

    @And("the registration user marks the registration choices")
    public void theRegistrationUserMarksTheRegistrationChoices() {
        registerPage.MarkChoices();
    }

    @Then("the registration user submits the registration form")
    public void theRegistrationUserSubmitsTheRegistrationForm() {
        registerPage.ClickContinue();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
