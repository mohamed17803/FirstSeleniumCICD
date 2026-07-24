package org.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ContactUsFeature  extends  LoginPage{

    // Initialize the driver
    private final WebDriver driver;

    public ContactUsFeature(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    // Locators

    private final By EnquiryField = By.cssSelector("div[class=\"col-sm-10\"] textarea[id=\"input-enquiry\"]");
    private  final  By  SubmitButton = By.cssSelector("input[type=\"submit\"]");
    private final By SentMessage = By.xpath("//div[@id='content']/p[text()='Your enquiry has been successfully sent to the store owner!']");



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
    public ContactUsFeature HomeNavigate() {
        String pageURL = "https://awesomeqa.com/ui/index.php?route=common/home";
        driver.navigate().to(pageURL);
        driver.manage().window().maximize();
        return this;
    }


    @Step("User navigates  directly to Contact Us page")
    public ContactUsFeature HomeNavigateToContactUsDirect() {
        driver.navigate().to("https://awesomeqa.com/ui/index.php?route=information/contact");
        driver.manage().window().maximize();
        return this;
    }



    @Step("User Fills the Form")
    public ContactUsFeature FillingForm(String WrittenMail){

        driver.findElement(EnquiryField).sendKeys(WrittenMail);
        driver.findElement(SubmitButton).click();

        return this ;
    }



    @Step("Validate if the Sent Message appeared and matches expected text")
    public ContactUsFeature ValidateMessage(String expectedMessageFromJson) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement messageElement = wait.until(
                ExpectedConditions.visibilityOfElementLocated(SentMessage)
        );

        String actualMessage = messageElement.getText().trim();
        String expectedMessage = expectedMessageFromJson.trim();

        Assert.assertEquals(actualMessage, expectedMessage,
                "The actual message doesn't match the expected message from JSON.");

        return this;
    }









}
