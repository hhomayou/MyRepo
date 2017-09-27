package automationFramework;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src/test/Feature/TrelloLogin.feature",
	plugin = {"pretty", "html:target/cucumber"},	
	glue = "TestSteps"	
)
public class TestRunner{

}
