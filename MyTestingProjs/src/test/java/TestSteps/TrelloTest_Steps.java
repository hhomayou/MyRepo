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
	
	WebElement login;
	WebElement board;

	// Scenario 1
	
	@Given("^A user wants to use Trello page$")
	public void a_user_wants_to_use_Trello_page() throws Throwable {
		beforeClassMethod("https://trello.com/login");
		trelloElements = PageFactory.initElements(driver, TrelloElements.class); // Initial elements
		login = trelloElements.getLogin();
		wait.until(ExpectedConditions.elementToBeClickable(login));
	}

	@When("^The user enters login info$")
	public void the_user_enters_login_info() throws Throwable {
		// Page loaded, login now
		if(!trelloElements.getEmail().equals(emailTest)) { // Email is not entered yet 
			trelloElements.setEmail(emailTest);
			trelloElements.setPassword(passwordTest);
			trelloElements.waitPassword(passwordTest);
		}
		login.click();
	}

	@Then("^The page Boards is loaded and it displays all the boards and teams$")
	public void the_page_Boards_is_loaded_and_it_displays_all_the_boards_and_teams() throws Throwable {
		board = trelloElements.getBoard();
		wait.until(ExpectedConditions.elementToBeClickable(board));
		// Logged into Boards page
		System.out.println(trelloElements.getBoardTeamNames());
		board.click();
	}

	// Scenario 2
	
	@Given("^The user is in the Boards page$")
	public void the_user_is_in_the_Boards_page() throws Throwable {
		// Nothing to do
	}	
	
	@When("^The users clicks on MyBoard box$")
	public void the_users_clicks_on_MyBoard_box() throws Throwable {
		WebElement taskList = trelloElements.getTaskList();
		wait.until(ExpectedConditions.elementToBeClickable(taskList));
	}

	@Then("^The page MyBoard is loaded and it displays all the lists$")
	public void the_page_MyBoard_is_loaded_and_it_displays_all_the_cards() throws Throwable {
		// MyBoard page is loaded
		System.out.println("============>" + trelloElements.getTaskListTitle());
	}

	@Then("^Test ends$")
	public void test_ends() throws Throwable {
		Thread.sleep(5000);
		afterClassMethod();
	}
	
}
