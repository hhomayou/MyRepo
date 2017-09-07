package automationFramework;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features =
	//	"Feature/Trello.Feature"
		"Feature/CurrencyTest.Feature"
	,
	
	plugin = {"pretty", "html:target/cucumber"}
	
	,
	
	glue = "TestSteps"
	
)
public class TestRunner{

}
