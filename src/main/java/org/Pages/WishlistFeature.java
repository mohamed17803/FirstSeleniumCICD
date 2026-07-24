    package org.Pages;

    import io.qameta.allure.Step;
    import org.openqa.selenium.By;
    import org.openqa.selenium.JavascriptExecutor;
    import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.WebElement;
    import org.openqa.selenium.support.ui.ExpectedConditions;
    import org.openqa.selenium.support.ui.WebDriverWait;
    import org.testng.Assert;

    import java.time.Duration;
    import java.util.List;

    public class WishlistFeature extends LoginPage {

        private final WebDriver driver;

        public WishlistFeature(WebDriver driver) {
            super(driver);
            this.driver = driver;
        }

        // Locators
        private final By ItemHeartOne = By.cssSelector("button[onclick=\"wishlist.add('43');\"] i[class=\"fa fa-heart\"]");
        private final By ItemHeartTwo = By.cssSelector("button[onclick=\"wishlist.add('40');\"] i[class=\"fa fa-heart\"]");
        private final By ItemThree   = By.cssSelector("button[onclick=\"wishlist.add('30');\"] i[class=\"fa fa-heart\"]");
        private final By WishlistTab = By.cssSelector("a[href=\"https://awesomeqa.com/ui/index.php?route=account/wishlist\"] [class=\"fa fa-heart\"]");
        private final By ItemModelOne = By.cssSelector("div[class=\"table-responsive\"] img[src=\"https://awesomeqa.com/ui/image/cache/catalog/demo/iphone_1-47x47.jpg\"]");
        private final By ItemModelTwo = By.cssSelector("img[src=\"https://awesomeqa.com/ui/image/cache/catalog/demo/canon_eos_5d_1-47x47.jpg\"]");
        private final By ItemModelThree = By.cssSelector("img[src=\"https://awesomeqa.com/ui/image/cache/catalog/demo/macbook_1-47x47.jpg\"]");

         // Actions

        @Override
        public LoginPage UrlNavigate() {
            return super.UrlNavigate();
        }

        @Override
        public LoginPage LoginNavigate() {
            return super.LoginNavigate();
        }

        @Override
        public LoginPage LoginForm(String Emailaddress, String Password) {
            return super.LoginForm(Emailaddress, Password);
        }

        @Override
        public LoginPage ClickLogin() throws InterruptedException {
            return super.ClickLogin();
        }

        @Step("User navigates  back to Home page")
        public WishlistFeature HomeNavigate() {
            String pageURL = "https://awesomeqa.com/ui/index.php?route=common/home";
            driver.navigate().to(pageURL);
            driver.manage().window().maximize();
            return this;
        }

        @Step("Add items to Wishlist ")
        public WishlistFeature AddtoWishlists() throws InterruptedException {
            List<By> ItemHearts = List.of(ItemHeartOne, ItemHeartTwo, ItemThree);

            for (int i = 0; i < ItemHearts.size(); i++) {
                WebElement element = driver.findElement(ItemHearts.get(i));
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", element);
                System.out.println("Clicked item " + (i + 1));

            }

            Thread.sleep(3000);

            return this;
        }


        @Step("Go to the Wishlist")
        public WishlistFeature GoWishlist() throws InterruptedException {

        driver.findElement(WishlistTab).click();
        System.out.println("clicked the tab");
        Thread.sleep(7000);
        return this ;
        }
        @Step("Check items exist in the Wishlist")
        public WishlistFeature CheckAddedItems() {

            List<By> wishlistItems = List.of(ItemModelOne, ItemModelTwo, ItemModelThree);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));

            for (int i = 0; i < wishlistItems.size(); i++) {


                By itemLocator = wishlistItems.get(i);


                try {
                    WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(itemLocator));
                    Assert.assertTrue(item.isDisplayed(), "Item " + (i + 1) + " is not displayed in the wishlist.");
                    System.out.println("Item " + (i + 1) + " is visible in the wishlist.");
                } catch (Exception e) {
                    Assert.fail("Item " + (i + 1) + " not found in the wishlist.");
                }
            }



            return this;
        }


    }
