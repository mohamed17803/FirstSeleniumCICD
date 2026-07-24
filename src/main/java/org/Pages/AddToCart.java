package org.Pages;

import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class AddToCart {


    // Initialize the driver
    private final WebDriver driver;

    public AddToCart(WebDriver driver) {
        this.driver = driver;
    }


    // Locators


    private final By IphoneItem = By.cssSelector("div[class=\"caption\"] a[href=\"https://awesomeqa.com/ui/index.php?route=product/product&product_id=40\"]");
    private final By AddToCartButton = By.id("button-cart");
    private final By SuccessMsg = By.cssSelector("div.alert.alert-success.alert-dismissible");
    private final By ShoppingCart  = By.cssSelector("a[href=\"https://awesomeqa.com/ui/index.php?route=checkout/cart\"] span[class=\"hidden-xs hidden-sm hidden-md\"]");
    private final By ItemModel = By.cssSelector("div[class=\"table-responsive\"] img[src=\"https://awesomeqa.com/ui/image/cache/catalog/demo/iphone_1-47x47.jpg\"]");

    // Actions

    // Method to Navigate to URL
    @Step("user navigate to Home page")
    public AddToCart UrlNavigate() {
        String pageURL = "https://awesomeqa.com/ui/index.php?route=common/home";
        driver.navigate().to(pageURL);
        driver.manage().window().maximize();
        return this;
    }

    // Method to view the IPhone Product
    @Step(" User Opens a product for a better view  ")
    public  AddToCart ViewProduct(){

    driver.findElement(IphoneItem).click();

        return this ;
    }

    @Step("User clicks Add to cart button ")
    public AddToCart ClickAddToCart(){

        driver.findElement(AddToCartButton).click();
        return this ;
    }
    @Step("Validate that Success Message Appears")
    public AddToCart CheckMsg() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(SuccessMsg));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loggedInElement.isDisplayed(), "Success: You have added iPhone to your shopping cart!.");

        softAssert.assertAll();

        return this;
    }
    @Step("Open the Shopping Cart")
    public void ShoppingCartView(){
        WebElement element = driver.findElement(ShoppingCart);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ItemModel));
    }

}