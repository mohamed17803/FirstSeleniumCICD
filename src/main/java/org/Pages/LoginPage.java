package org.Pages;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.time.Duration;

public class LoginPage {


    // Initialize the driver
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


  // Locators


    private final By DropDownToggle = By.xpath("//span[@class=\"caret\"]");
    private final By LoginOption = By.xpath("//a[@href='https://awesomeqa.com/ui/index.php?route=account/login']");
    private final By EmailAddress =  By.cssSelector("input[placeholder*='E-Mail Address']");
    private  final By PasswordTextField = By.cssSelector("input[placeholder*='Password']");
    private final By LoginButton = By.cssSelector("input[class=\"btn btn-primary\"]");
    private final By LoggedInMessage = By.xpath("//a[contains(text(), 'Edit your account information')]"); // Example of an element to verify successful login


    // Actions

    // Method to Navigate to URL
    @Step("User navigate to Home page")
    public LoginPage UrlNavigate() {
        String pageURL = "https://awesomeqa.com/ui/index.php?route=common/home";
        driver.navigate().to(pageURL);
        driver.manage().window().maximize();
        return this;
    }
    @Step("User navigate to Login page")
    public  LoginPage LoginNavigate(){

        driver.findElement(DropDownToggle).click();
        driver.findElement(LoginOption).click();


        return this;
    }
    @Step("User enter valid Login data ")
    public  LoginPage LoginForm(String Emailaddress , String Password){

        driver.findElement(EmailAddress).sendKeys(Emailaddress);
        driver.findElement(PasswordTextField).sendKeys(Password);
        return this;
    }


    public LoginPage ClickLogin() throws InterruptedException {

        driver.findElement(LoginButton).click();
        Thread.sleep(3000);
        return this; }


    // Method to wait and soft assert successful login

    public void  LoginAssert() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement loggedInElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoggedInMessage));

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(loggedInElement.isDisplayed(), "Login failed, user is not logged in.");

        softAssert.assertAll();

    }


}


















