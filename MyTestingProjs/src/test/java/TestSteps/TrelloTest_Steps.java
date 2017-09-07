package TestSteps;

//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.ui.ExpectedConditions;
import PageObject.Setup;
//import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TrelloTest_Steps extends Setup {

	@Given("^A user open Trello page$")
	public void a_user_open_Trello_page() throws Throwable {
		System.out.println("aaaaa");
	    //throw new PendingException();
	}

	@When("^Click on MyBoard$")
	public void click_on_MyBoard() throws Throwable {
		System.out.println("bbbbb");
	    //throw new PendingException();
	}

	@Then("^It prints success$")
	public void it_prints_success() throws Throwable {
		System.out.println("ccccc");
	    //throw new PendingException();
	}
	
}
