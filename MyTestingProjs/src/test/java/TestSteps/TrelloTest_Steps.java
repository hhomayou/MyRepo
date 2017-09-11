package TestSteps;

import org.openqa.selenium.By;
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
	
	WebElement login, board, card;

	// Scenario 1 Login
	
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

	// Scenario 2 Display board
	
	@Given("^The user is in the main page$")
	public void the_user_is_in_the_main_page() throws Throwable {
		// Nothing to do
	}	
	
	@When("^The user clicks on MyBoard box$")
	public void the_user_clicks_on_MyBoard_box() throws Throwable {
		WebElement taskList = trelloElements.getTaskList();
		wait.until(ExpectedConditions.elementToBeClickable(taskList));
	}

	@Then("^The page MyBoard is loaded and it displays all the info$")
	public void the_page_MyBoard_is_loaded_and_it_displays_all_the_info() throws Throwable {
		// MyBoard page is loaded
		System.out.println("List name: " + trelloElements.getTaskListTitle() + ", " + trelloElements.getTaskListCardTot() + ": ");
		for(WebElement card: trelloElements.getCards()) {
			System.out.print("Card: " + card.findElement(By.xpath(".//span[@class='list-card-title js-card-name']")).getAttribute("innerText"));
			System.out.print(", Items: " + card.findElement(By.xpath(".//span[@class='badge-text']")).getAttribute("innerText"));			
			System.out.print(", assigned to: ");
			for(WebElement member:card.findElements(By.xpath(".//span[@class='member-initials']")))
				System.out.print(" " + member.getAttribute("innerText"));
			System.out.println();
		}
	}

	// Scenario 3 Display card
	
	@Given("^The user is in the MyBoard page$")
	public void the_user_is_in_the_MyBoard_page() throws Throwable {
		// Do nothing
	}

	@When("^The user clicks on first card$")
	public void the_user_clicks_on_first_card() throws Throwable {
		card = trelloElements.getCards().get(0);
		card.click();
		card = trelloElements.getCard();
		wait.until(ExpectedConditions.elementToBeClickable(card));		
	}

	@Then("^The card info is loaded and popped up$")
	public void the_card_info_is_loaded_and_popped_up() throws Throwable {
		System.out.println("Checklist: " + card.findElement(By.xpath(".//h3[@class='current hide-on-edit']")).getAttribute("innerText"));
		System.out.println("Items:");
		for(String item: trelloElements.getItems())
			System.out.println("  " + item);			
	}

	@Then("^The user closes the card popup$")
	public void the_user_closes_the_card_popup() throws Throwable {
		trelloElements.closeCard();
	}

	// Scenario 4 Add a team
	
	@When("^The user clicks on create new team link$")
	public void the_user_clicks_on_create_new_team_link() throws Throwable {
	}

	@Then("^Create Team popup appears$")
	public void create_Team_popup_appears() throws Throwable {
	}

	@Then("^User enters Name and Description$")
	public void user_enters_Name_and_Description() throws Throwable {
	}

	@Then("^The new team$")
	public void the_new_team() throws Throwable {
	}

	@When("^The user clicks on create new board box$")
	public void the_user_clicks_on_create_new_board_box() throws Throwable {
	}

	@Then("^Create Board popup appears$")
	public void create_Board_popup_appears() throws Throwable {
	}

	@Then("^User enters Title and selects first Team$")
	public void user_enters_Title_and_selects_first_Team() throws Throwable {
	}

	@Then("^The new board is created$")
	public void the_new_board_is_created() throws Throwable {
	}

	@Then("^The page closes$")
	public void the_page_closes() throws Throwable {
		afterClassMethod();
	}
	
}
