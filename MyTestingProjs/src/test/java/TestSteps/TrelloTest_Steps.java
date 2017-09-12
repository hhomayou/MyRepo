package TestSteps;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
	
	WebElement login, card, team;
	List<WebElement> boards;

	// Login
	
	@Given("^User opens Trello login page$")
	public void user_opens_Trello_login_page() throws Throwable {
		beforeClassMethod("https://trello.com/login");
		trelloElements = PageFactory.initElements(driver, TrelloElements.class); // Initial elements
		login = trelloElements.getLogin();
		wait.until(ExpectedConditions.elementToBeClickable(login));
	}

	@When("^User enters login info$")
	public void user_enters_login_info() throws Throwable {
		// Page loaded, login now
		if(!trelloElements.getEmail().equals(emailTest)) { // Email is not entered yet 
			trelloElements.setEmail(emailTest);
			trelloElements.setPassword(passwordTest);
			trelloElements.waitPassword(passwordTest);
		}
		login.click();
		// Wait till all team/boards are displayed		
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				boards = trelloElements.getBoards();
				return boards.size() > 1;
			}
		});			
		// Logged into Boards page
		System.out.println("User [" + emailTest + "] logged in successfully");
	}

	@Then("^List of teams and boards is displayed in the main page$")
	public void list_of_teams_and_boards_is_displayed_in_the_main_page() throws Throwable {
		trelloElements.displayBoardTeamNames();		
	}	

	// Display a board

	@Given("^User is in the main page$")
	public void User_is_in_the_main_page() throws Throwable {
		// Nothing to do
	}	
	
	@When("^User clicks on MyBoard box$")
	public void user_clicks_on_MyBoard_box() throws Throwable {
		trelloElements.clickTeamBoard("MyTeam");			
		WebElement taskList = trelloElements.getTaskList();
		wait.until(ExpectedConditions.elementToBeClickable(taskList));
	}

	@Then("^Page MyBoard is loaded and it displays all the info$")
	public void page_MyBoard_is_loaded_and_it_displays_all_the_info() throws Throwable {
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

	// Display a card
	
	@Given("^User is in MyBoard page$")
	public void user_is_in_MyBoard_page() throws Throwable {
		// Do nothing
	}

	@When("^User clicks on first card$")
	public void user_clicks_on_first_card() throws Throwable {
		card = trelloElements.getCards().get(0);
		card.click();
		card = trelloElements.getCard();
		wait.until(ExpectedConditions.elementToBeClickable(card));		
	}

	@Then("^Card info is loaded and popped up$")
	public void Card_info_is_loaded_and_popped_up() throws Throwable {
		System.out.println("Checklist: " + card.findElement(By.xpath(".//h3[@class='current hide-on-edit']")).getAttribute("innerText"));
		System.out.println("Items:");
		for(String item: trelloElements.getItems())
			System.out.println("  " + item);			
	}

	@Then("^User closes the card popup$")
	public void user_closes_the_card_popup() throws Throwable {
		trelloElements.closeCard();
	}
	
	// Create a team

	@When("^User clicks on create new team link$")
	public void user_clicks_on_create_new_team_link() throws Throwable {
		trelloElements.clickNewteam();
	}

	@Then("^Create Team popup appears$")
	public void create_Team_popup_appears() throws Throwable {
		// Do nothing
	}

	@Then("^User enters Name and Description$")
	public void user_enters_Name_and_Description() throws Throwable {
		trelloElements.setTeamName("Team1");
		trelloElements.waitTeamName("Team1");
		trelloElements.setTeamDescription("Team description1");
		trelloElements.clickCreateTeam();
	}
	
	@Then("^The new team is created and displayed$")
	public void the_new_team_is_created_and_displayed() throws Throwable {
		System.out.println("Team [" + trelloElements.getNewTeam().getText() + "] added successfully");
	}
	
	// Delete a team
	
	@Given("^User is in the Team page$")
	public void user_is_in_the_team_page() throws Throwable {
		team = trelloElements.getNewTeam(); // Get the new team
	}

	@When("^User selects Setting tab$")
	public void user_selects_setting_tab() throws Throwable {
		trelloElements.clickTeamSetting();
	}

	@Then("^Click Delete this team$")
	public void click_delete_this_team() throws Throwable {
		trelloElements.clickDeleteTeam();
	}

	@Then("^Confirm and delete the team$")
	public void confirm_and_delete_the_team() throws Throwable {
		trelloElements.clickDeleteTeamConfirm();
		System.out.println("Team [" + team.getText() + "] deleted successfully");
	}

	@Then("^A confirmation is displayed$")
	public void a_confirmation_is_displayed() throws Throwable {
	}	

	// Create a board
	
	@When("^User clicks on create new board box$")
	public void user_clicks_on_create_new_board_box() throws Throwable {
		trelloElements.clickTeamBoard("MyTeam");			
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

	// Scenario Test ends
	
	@Given("^The test is over$")
	public void the_test_is_over() throws Throwable {
		// Do nothing
	}

	@When("^There is no more test$")
	public void there_is_no_more_test() throws Throwable {
		// Do nothing
	}
	
	@Then("^The page closes$")
	public void the_page_closes() throws Throwable {
		Thread.sleep(3000);
		afterClassMethod();
	}
	
}
