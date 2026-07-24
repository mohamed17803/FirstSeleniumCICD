package testsPackage;

import data.RegisterData;
import io.qameta.allure.*;
import org.Pages.RegisterPage;
import org.testng.annotations.Test;
import utils.JsonUtils;
import io.qameta.allure.testng.AllureTestNg;
import org.testng.annotations.Listeners;

@Epic("Product Search Functionality")
@Story("Search for products using keywords and validate relevant results")
@Severity(SeverityLevel.BLOCKER)


@Listeners({AllureTestNg.class})
public class RegisterTest extends BaseTest {

    @Test(description = "Validate if the user can sign up using valid data")
    @Step("Validate if the user can sign up")
    public void RegisterFeatureTest() {
        // Setting JSON Path
        String filePath = "src/test/resources/testDatafiles/registerData.json";

        // Reading  JsonUtils
        RegisterData data = JsonUtils.readJsonFile(filePath, RegisterData.class);

        if (data != null) {
            // Passing Data to the method
            RegisterPage registerPage = new RegisterPage(driver);
            registerPage.urlNavigate()
                    .ListBehaviour()
                    .RegistrationForm(
                            data.getFirstName(),
                            data.getLastName(),
                            data.getEmail(),
                            data.getTelephone(),
                            data.getPassword(),
                            data.getConfirmPassword()
                    )
                    .MarkChoices()
                    .ClickContinue();
        } else {
            System.out.println(" Failed to load registration data from JSON file.");
        }
    }
}
