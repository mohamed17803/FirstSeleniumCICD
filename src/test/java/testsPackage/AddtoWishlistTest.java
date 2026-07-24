package testsPackage;

import data.RegisterData;

import io.qameta.allure.*;
import org.Pages.WishlistFeature;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;


@Epic("Wishlist Management")
@Story("Add and remove items from wishlist and validate their persistence")
@Severity(SeverityLevel.NORMAL)


public class AddtoWishlistTest extends BaseTest {

    @Test(description = "Add items to wishlist ")
    public void AddToWishlistFeature() throws InterruptedException {
        String registerPath = "src/test/resources/testDatafiles/registerData.json";
        RegisterData registerData = JsonUtils.readJsonFile(registerPath, RegisterData.class);



        Assert.assertNotNull(registerData, "RegisterData is null");


        WishlistFeature wishlistFeature = new WishlistFeature(driver);
        wishlistFeature
                .UrlNavigate()
                .LoginNavigate()
                .LoginForm(registerData.getEmail(), registerData.getPassword())
                .ClickLogin();wishlistFeature.HomeNavigate()
                .AddtoWishlists().GoWishlist().CheckAddedItems();
    }
}
