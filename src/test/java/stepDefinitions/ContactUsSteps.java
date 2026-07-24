package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Pages.ContactUsFeature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ContactUsSteps {
    private WebDriver driver;
    private ContactUsFeature contactUsFeature;

    @Given("the contact user is on the home page")
    public void theContactUserIsOnTheHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        contactUsFeature = new ContactUsFeature(driver);
        contactUsFeature.UrlNavigate();
    }

    @When("the contact user navigates to the login page")
    public void theContactUserNavigatesToTheLoginPage() {
        contactUsFeature.LoginNavigate();
    }

    @And("the contact user logs in with email {string} and password {string}")
    public void theContactUserLogsInWithEmailAndPassword(String email, String password) throws InterruptedException {
        contactUsFeature.LoginForm(email, password);
        contactUsFeature.ClickLogin();
    }

    @And("the contact user returns to the home page")
    public void theContactUserReturnsToTheHomePage() {
        contactUsFeature.HomeNavigate();
    }

    @And("the contact user navigates directly to the contact us page")
    public void theContactUserNavigatesDirectlyToTheContactUsPage() {
        contactUsFeature.HomeNavigateToContactUsDirect();
    }

    @And("the contact user submits enquiry {string}")
    public void theContactUserSubmitsEnquiry(String writtenMail) {
        contactUsFeature.FillingForm(writtenMail);
    }

    @Then("the contact user should see confirmation message {string}")
    public void theContactUserShouldSeeConfirmationMessage(String confirmationMessage) {
        contactUsFeature.ValidateMessage(confirmationMessage);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
