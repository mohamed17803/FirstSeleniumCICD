package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Pages.WishlistFeature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WishlistSteps {
    private WebDriver driver;
    private WishlistFeature wishlistFeature;

    @Given("the wishlist user is on the home page")
    public void theWishlistUserIsOnTheHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wishlistFeature = new WishlistFeature(driver);
        wishlistFeature.UrlNavigate();
    }

    @When("the wishlist user navigates to the login page")
    public void theWishlistUserNavigatesToTheLoginPage() {
        wishlistFeature.LoginNavigate();
    }

    @And("the wishlist user logs in with email {string} and password {string}")
    public void theWishlistUserLogsInWithEmailAndPassword(String email, String password) throws InterruptedException {
        wishlistFeature.LoginForm(email, password);
        wishlistFeature.ClickLogin();
    }

    @And("the wishlist user returns to the home page")
    public void theWishlistUserReturnsToTheHomePage() {
        wishlistFeature.HomeNavigate();
    }

    @And("the wishlist user adds products to the wishlist")
    public void theWishlistUserAddsProductsToTheWishlist() throws InterruptedException {
        wishlistFeature.AddtoWishlists();
    }

    @And("the wishlist user opens the wishlist page")
    public void theWishlistUserOpensTheWishlistPage() throws InterruptedException {
        wishlistFeature.GoWishlist();
    }

    @Then("the wishlist user should see the added products")
    public void theWishlistUserShouldSeeTheAddedProducts() {
        wishlistFeature.CheckAddedItems();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
