package testsPackage;

import data.ContactUsData;
import data.RegisterData;
import io.qameta.allure.*;
import org.Pages.ContactUsFeature;
import org.testng.annotations.Test;
import utils.JsonUtils;

@Epic("User Support Communication")
@Story("Submit inquiry through contact form and validate message delivery confirmation")
@Severity(SeverityLevel.MINOR)

public class ContactUsTest extends BaseTest {

    @Test(description = "Check if user can use the contact us feature")
    public void ContactUsCheck() throws InterruptedException {

        String dataPath = "src/test/resources/testDatafiles/ContactUs.json";
        ContactUsData contactUsData = JsonUtils.readJsonFile(dataPath, ContactUsData.class);

        String filePath = "src/test/resources/testDatafiles/registerData.json";
        RegisterData data = JsonUtils.readJsonFile(filePath, RegisterData.class);



        assert data != null;

        ContactUsFeature contactUsFeature = new ContactUsFeature(driver);

        contactUsFeature.UrlNavigate().LoginNavigate()
                .LoginForm(data.getEmail(), data.getPassword())
                .ClickLogin();

        assert contactUsData != null;
        contactUsFeature.HomeNavigate()
                .HomeNavigateToContactUsDirect()
                .FillingForm(contactUsData.getWrittenMail())
                .ValidateMessage(contactUsData.getConfirmationMessage());

    }
}
