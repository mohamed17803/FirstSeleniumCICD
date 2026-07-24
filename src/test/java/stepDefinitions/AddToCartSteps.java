package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.Pages.AddToCart;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddToCartSteps {
    private WebDriver driver;
    private AddToCart addToCart;

    @Given("the cart user is on the home page")
    public void theCartUserIsOnTheHomePage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        addToCart = new AddToCart(driver);
        addToCart.UrlNavigate();
    }

    @When("the cart user opens the iPhone product")
    public void theCartUserOpensTheIPhoneProduct() {
        addToCart.ViewProduct();
    }

    @And("the cart user adds the product to the cart")
    public void theCartUserAddsTheProductToTheCart() {
        addToCart.ClickAddToCart();
    }

    @Then("the cart user should see the add to cart success message")
    public void theCartUserShouldSeeTheAddToCartSuccessMessage() {
        addToCart.CheckMsg();
    }

    @And("the cart user should see the product in the shopping cart")
    public void theCartUserShouldSeeTheProductInTheShoppingCart() {
        addToCart.ShoppingCartView();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
