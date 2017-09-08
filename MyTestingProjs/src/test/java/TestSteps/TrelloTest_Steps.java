package TestSteps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PageObject.Setup;
import PageObject.TrelloElements;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TrelloTest_Steps extends Setup {

	public static TrelloElements trelloElements;
	public static final String emailTest = "hhomayounfar@qaconsultants.com";
	public static final String passwordTest = "pswdpswd";
	
	
	@Given("^A user open Trello page and login$")
	public void a_user_open_Trello_page_and_Login() throws Throwable {
		beforeClassMethod("https://trello.com/login");
		trelloElements = PageFactory.initElements(driver, TrelloElements.class); // Initial elements
		WebElement login = trelloElements.getLogin();
		wait.until(ExpectedConditions.elementToBeClickable(login));
		// Page loaded, login now
		if(!trelloElements.getEmail().equals(emailTest)) { // Email is not entered yet 
			trelloElements.setEmail(emailTest);
			trelloElements.setPassword(passwordTest);
			trelloElements.waitPassword(passwordTest);
		}
		login.click();
		WebElement board = trelloElements.getBoard();
		wait.until(ExpectedConditions.elementToBeClickable(board));
		// Logged into dash board				
		System.out.println("Board name and team names = " + trelloElements.getBoardTeamNames());
	}

	@When("^Click on MyBoard$")
	public void click_on_MyBoard() throws Throwable {
		System.out.println("bbbbb");
	}

	@Then("^It prints success$")
	public void it_prints_success() throws Throwable {
		System.out.println("ccccc");
	}

	@Then("^Test ends$")
	public void test_ends() throws Throwable {
		afterClassMethod();
	}
	
}
