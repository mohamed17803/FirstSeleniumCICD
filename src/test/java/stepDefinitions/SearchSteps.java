package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Pages.SearchFeature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchSteps {
    private WebDriver driver;
    private SearchFeature searchFeature;

    @Given("the search user is on the home page")
    public void theSearchUserIsOnTheHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        searchFeature = new SearchFeature(driver);
        searchFeature.UrlNavigate();
    }

    @When("the search user searches for product {string}")
    public void theSearchUserSearchesForProduct(String productName) {
        searchFeature.UserSearch(productName);
    }

    @Then("the search user should see the desired product")
    public void theSearchUserShouldSeeTheDesiredProduct() {
        searchFeature.CheckItem();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
