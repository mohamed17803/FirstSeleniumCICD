package org.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SearchFeature {
    // Initialize the driver

    private final WebDriver driver;
    public SearchFeature(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By SearchField = By.cssSelector("input[name=\"search\"]");
    private final By  SearchButton =By.cssSelector("i[class=\"fa fa-search\"]");
    private final By DesiredItem = By.cssSelector("div[class=\"product-thumb\"] a[href=\"https://awesomeqa.com/ui/index.php?route=product/product&product_id=43&search=Macbook\"]");


    // Actions

    // Method to Navigate to URL
    @Step("User navigate to Home page")
    public SearchFeature UrlNavigate() {
        System.out.println("Navigating to URL...");
        String pageURL = "https://awesomeqa.com/ui/index.php?route=common/home";
        driver.navigate().to(pageURL);
        driver.manage().window().maximize();
        return this;
    }

    @Step("User search for Macbook ")
    public SearchFeature UserSearch(String ProductName) {
        System.out.println("Searching for product...");

        driver.findElement(SearchField).sendKeys(ProductName);
        driver.findElement(SearchButton).click();
        return this ;
    }

    @Step(" Validate if the Desired Product appears")
    public void CheckItem(){
        System.out.println("Waiting for item...");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement item = wait.until(ExpectedConditions.visibilityOfElementLocated(DesiredItem));
        Assert.assertTrue(item.isDisplayed(), " Desired product is not displayed on the page.");

    }
    }

