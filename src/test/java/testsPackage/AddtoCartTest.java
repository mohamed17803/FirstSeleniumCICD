package testsPackage;

import io.qameta.allure.*;
import io.qameta.allure.testng.AllureTestNg;
import org.Pages.AddToCart;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({AllureTestNg.class})

@Epic("Shopping Cart Operations")
@Story("Add items to the shopping cart and verify correct product details and quantities")
@Severity(SeverityLevel.CRITICAL)


public class AddtoCartTest extends BaseTest {

    @Test(description = "Validate if the user can add a product he choose to the cart")
    @Step("Check Using Iphone")
     public void  AddingProductFeature(){

        AddToCart addToCart = new AddToCart(driver);
    addToCart.UrlNavigate().ViewProduct().ClickAddToCart().CheckMsg().ShoppingCartView();

    }
}
