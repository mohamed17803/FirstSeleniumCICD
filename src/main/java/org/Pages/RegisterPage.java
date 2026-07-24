package org.Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    // Initialize the driver
    private final WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    private final By DropDownToggle = By.xpath("//span[@class=\"caret\"]");
    private final By RegisterOption = By.xpath("//a[@href='https://awesomeqa.com/ui/index.php?route=account/register']");
        private final By FirstNameField = By.cssSelector("input[id*='input-firstname']");
    private final By LastNameField = By.cssSelector("input[name*='lastname']");
    private final By EmailTextField = By.cssSelector("input[id*='email']");
    private final By TelephoneTextField = By.cssSelector("input[id*='input-telephone']");
    private final By PasswordField = By.cssSelector("input[name*='password']");
    private final By ConfirmPasswordField = By.cssSelector("input[id*='input-confirm']");
    private final By YesOption = By.cssSelector("input[name*='newsletter'][value='1']");
    private final By PrivacyCheck = By.cssSelector("input[name*='agree'][value='1']");
    private final By ContinueButton = By.cssSelector("input[value*='Continue']");



    // Actions

    // Method to Navigate to URL
    @Step("user navigate to registration page")
    public RegisterPage urlNavigate() {
        String pageURL = "https://awesomeqa.com/ui/index.php?route=common/home";
        driver.navigate().to(pageURL);
        driver.manage().window().maximize();
        return this;
    }

    @Step("user navigate to registration page and sign up")
    public RegisterPage ListBehaviour() {

        driver.findElement(DropDownToggle).click();
        driver.findElement(RegisterOption).click();
         return this;
    }
    @Step("user fills data")
    public RegisterPage RegistrationForm(String FirstName, String LastName, String Email, String Telephone, String Password, String ConfirmPassword) {


        driver.findElement(FirstNameField).sendKeys(FirstName);
        driver.findElement(LastNameField).sendKeys(LastName);
        driver.findElement(EmailTextField).sendKeys(Email);
        driver.findElement(TelephoneTextField).sendKeys(Telephone);
        driver.findElement(PasswordField).sendKeys(Password);
        driver.findElement(ConfirmPasswordField).sendKeys(ConfirmPassword);


        return this;
    }

    @Step("user recognizes data")
    public RegisterPage MarkChoices() {


        driver.findElement(YesOption).click();
        driver.findElement(PrivacyCheck).click();
        return this;
    }

    public void  ClickContinue() {
        driver.findElement(ContinueButton).click();

    }
}