package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/features/contactUs.feature",
        glue = "stepDefinitions",
        plugin = {"pretty"},
        monochrome = true
)
public class ContactUsCucumberTest extends AbstractTestNGCucumberTests {
}
