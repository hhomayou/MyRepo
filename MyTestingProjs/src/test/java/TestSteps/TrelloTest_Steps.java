package TestSteps;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PageObject.Setup;
import PageObject.TrelloElements;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TrelloTest_Steps extends Setup {

	public static TrelloElements trelloElements;
	public static final String emailTest = "hhomayounfar@qaconsultants.com";
	public static final String passwordTest = "pswdpswd";
	
	WebElement login, team, member;
	List<WebElement> teams;
	String boardName;
	private static boolean dunit = false;	

    @Before
    public void beforeAll() throws ClassNotFoundException, IOException, SQLException {
        if(!dunit) {
            Runtime.getRuntime().addShutdownHook(new Thread() {
                public void run() { // After all
            		driver.quit();
                }
            });
            // Before all
    		beforeClassMethod("https://trello.com/login");
    		trelloElements = PageFactory.initElements(driver, TrelloElements.class); // Initial elements
    		login = trelloElements.getLogin();
    		wait.until(ExpectedConditions.elementToBeClickable(login));
    		// Page loaded, login now
    		if(!trelloElements.getEmail().equals(emailTest)) { // Email is not entered yet 
    			trelloElements.setEmail(emailTest);
    			trelloElements.setPassword(passwordTest);
    			trelloElements.waitPassword(passwordTest);
    		}
    		login.click();	
    		trelloElements.waitLoginDone();
    		System.out.println("User [" + emailTest + "] logged in successfully");
            dunit = true;
        }
    }	
	
	// Login and display teams
	
	@Given("^User logs in to Trello main page$")
	public void user_logs_in_to_Trello_main_page() throws Throwable {
	}

	@When("^List of teams and boards is displayed in the main page$")
	public void list_of_teams_and_boards_is_displayed_in_the_main_page() throws Throwable {
		trelloElements.listTeams();		
	}	

	@Then("^User clicks on MyBoard box$")
	public void user_clicks_on_MyBoard_box() throws Throwable { // and goes to MyBoard page
    	trelloElements.clickMyBoard();		
	}

	@Then("^Page MyBoard is loaded and it displays all the info$")
	public void page_MyBoard_is_loaded_and_it_displays_all_the_info() throws Throwable {
		System.out.println("List name: " + trelloElements.getBoardListTitle() + ", " + trelloElements.getBoardListCardTot() + ": ");
		trelloElements.displayBoardListInfo();
	}

	// Display a card

	@Given("^User clicks on first card$")
	public void user_clicks_on_first_card() throws Throwable {
		trelloElements.getCards().get(0).click();
		System.out.println("Card [" + trelloElements.getCardName() + "], checklist(s): ");
		for(WebElement checklist : trelloElements.getChecklists())
			System.out.println("Checklist [" + trelloElements.getCheckListName(checklist) + "]");
	}

	@When("^Card info is loaded and popped up$")
	public void Card_info_is_loaded_and_popped_up() throws Throwable {
		System.out.println("Items:");
		for(WebElement item: trelloElements.getChecklistItems(trelloElements.getChecklist("MyChecklist")))
			System.out.println("  " + trelloElements.getItemName(item));			
	}

	@Then("^User closes the card popup$")
	public void user_closes_the_card_popup() throws Throwable {
		trelloElements.closeCard();
	}
	
	// Create a team

	//@Given("^User is in the main page$")
	
	@When("^User clicks on create new team link$")
	public void user_clicks_on_create_new_team_link() throws Throwable {
		trelloElements.clickNewteam();
	}

	@Then("^User enters Name and Description$")
	public void user_enters_Name_and_Description() throws Throwable {
		trelloElements.setTeamName("Team1");
		trelloElements.waitTeamName("Team1");
		trelloElements.setTeamDescription("Team description1");
		trelloElements.clickCreateTeam();
	}
	
	@And("^The new team is created and displayed$")
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

	@And("^Confirm and delete the team$")
	public void confirm_and_delete_the_team() throws Throwable {
		trelloElements.clickDeleteTeamConfirm();
		System.out.println("Team [" + team.getText() + "] deleted successfully");
	}

	@And("^A confirmation is displayed$")
	public void a_confirmation_is_displayed() throws Throwable {
	}	

	// Create a board
	
	@When("^User clicks on create new board box$")
	public void user_clicks_on_create_new_board_box() throws Throwable {
		trelloElements.clickNewBoard("MyTeam");
	}

	@Then("^User enters Title and selects first Team$")
	public void user_enters_Title_and_selects_first_Team() throws Throwable {
		trelloElements.setBoard("Board1");
		trelloElements.clickCreateBoard();
	}

	@And("^The new board is created$")
	public void the_new_board_is_created() throws Throwable {
		System.out.println("Board [" + trelloElements.getBoardName() + "] was added successfully");
	}

	// Delete the board
	
	@Given("^User is in the board page$")
	public void user_is_in_the_board_page() throws Throwable {
		boardName = trelloElements.getBoardName();
	}

	@When("^User clicks on more link$")
	public void user_clicks_on_more_link() throws Throwable {
		trelloElements.clickMore();
	}

	@Then("^User clicks Close Board link and Close button$")
	public void user_clicks_close_board_link_and_close_button() throws Throwable {
		trelloElements.clickCloseBoard();
	}

	@And("^Page displays the board is closed$")
	public void page_displays_the_board_is_closed() throws Throwable {
		System.out.println("Done: " + trelloElements.getBoardClosedMessage());
	}	

    @And("^User clicks on Permanently Delete Board link and delete button$")
	public void user_clicks_on_permanently_delete_board_link_and_delete_button() throws Throwable {
    	trelloElements.clickDeleteBoard();
	}	

    @And("^Board is deleted and page displays Board not found$")
	public void board_is_deleted_and_page_displays_Board_not_found() throws Throwable {
    	trelloElements.getBoardDeletedMessage();
		System.out.println("Board [" + boardName + "] was deleted successfully");
	}
	
    // Create a checklist

	//@When("^User clicks on MyBoard box$")
	
    //@When("^User clicks on first card$")

    @Then("^User click on Add checklist$")
    public void user_click_on_Add_checklist() throws Throwable {
    	trelloElements.clickChecklist();
    }

    @And("^User enters a the new checklist title and click on Add button$")
    public void user_enters_a_the_new_checklist_title_and_click_on_Add_button() throws Throwable {
    	trelloElements.setNewChecklistTitle("Checklist1");
    	trelloElements.clickAddChecklist();
    }

    @And("^Checklist is added to the card$")
    public void checklist_is_added_to_the_card() throws Throwable {
    	System.out.println("Checklist [" + trelloElements.getCheckListName(trelloElements.getChecklist("Checklist1")) +
    			"] added to the card [" + trelloElements.getCardName() + "]");
    }

    // Delete the checklist
    
    @Given("^User is in the card detail popup$")
    public void user_is_in_the_card_detail_popup() throws Throwable {
		System.out.println("Card [" + trelloElements.getCardName() + "] popup is active");
    }

    @When("^User clicks on delete link$")
    public void user_clicks_on_delete_link() throws Throwable {
    	trelloElements.clickDelChecklist(trelloElements.getChecklist("Checklist1"));    	
    }

    @Then("^Checklist is deleted$")
    public void checklist_is_deleted() throws Throwable {
		if(trelloElements.getChecklist("Checklist1") != null)
				throw new PendingException("Checklist [Checklist1] not deleted !");
		System.out.println("Checklist [Checklist1] successfully deleted");
    }

	//@And("^User closes the card popup$")
    
    // Add an item to a checklist
    
    //@Given("^User is in the card detail popup$")   
    
    @When("^User clicks on add an item box of mychecklist and enters item$")
    public void user_clicks_on_add_an_item_box_of_mychecklist_and_enters_item() throws Throwable {    	
    	trelloElements.addItem(trelloElements.getChecklist("MyChecklist"), "Item1");
    }

    @Then("^Item is added to the checklist$")
    public void item_is_added_to_the_checklist() throws Throwable {
    	WebElement item = trelloElements.getItem(trelloElements.getChecklistItems(trelloElements.getChecklist("MyChecklist")), "Item1");
    	trelloElements.cancelAddItem(item);
    	System.out.println("Item [Item1] added to checklist [MyChecklist]");
    }

    // Complete an item in the checklist

    @When("^User clicks on box left to the item$")
    public void user_clicks_on_box_left_to_the_item() throws Throwable {
    	trelloElements.toggleItemCheckbox(trelloElements.getItem(trelloElements.getChecklistItems(trelloElements.getChecklist("MyChecklist")), "Item1"));
    }

    @Then("^Item is completed$")
    public void item_is_completed() throws Throwable {
    	trelloElements.getItem(trelloElements.getChecklistItemsCompleted(trelloElements.getChecklist("MyChecklist")), "Item1");
    	System.out.println("Item [Item1] completed in checklist[MyChecklist]");
    }

    // Delete the item from the checklist   
    
    @When("^User clicks on the item$")
    public void user_clicks_on_the_item() throws Throwable {
    	WebElement item = trelloElements.getItem(trelloElements.getChecklistItemsCompleted(trelloElements.getChecklist("MyChecklist")), "Item1");
		wait.until(ExpectedConditions.elementToBeClickable(item)).click();
    }
 
    @Then("^Users clicks on Delete link$")
    public void users_clicks_on_Delete_link() throws Throwable {
    	trelloElements.delItem();
    }
    
    @And("^Item is deleted$")
    public void item_is_deleted() throws Throwable {    	
		if(trelloElements.getItem(trelloElements.getChecklistItemsCompleted(trelloElements.getChecklist("MyChecklist")), "Item1") != null)
	    	throw new PendingException("Item [Item1] not deleted from checklist [MyChecklist] !");
    	System.out.println("Item [Item1] deleted from checklist [MyChecklist]");
    }
	
    // Remove a member
    //@Given("^User clicks on MyBoard box$")

	//@When("^User clicks on first card$")
    
    @Then("^User clicks on Member button$")
    public void user_clicks_on_Member_button() throws Throwable {
    	trelloElements.clickCardMembers();
    }

    @And("^User clicks on the Member to toggle status$")
    public void user_clicks_on_the_Member_to_toggle_status() throws Throwable {
    	member = trelloElements.getCardMember("HH");
    	member.click();
    }

    @And("^Member is removed from the card$")
    public void member_is_removed_from_the_card() throws Throwable {
     	if(trelloElements.getMemberStatus(member)) throw new PendingException("Member [HH] was not removed from card [MyCard1] !");
     	System.out.println("Member [HH] removed from card [MyCard1]");
    }

    @And("^Users closes the members popup$")
    public void users_closes_the_members_popup() throws Throwable {
    	trelloElements.closeMember();
    }        

    // Add a member

	//@Given("^User clicks on first card$")

    //@When("^User clicks on the Member to toggle status$")

    @Then("^Member is added to the card$")
    public void member_is_added_from_the_card() throws Throwable {
     	if(!trelloElements.getMemberStatus(member)) throw new PendingException("Member [HH] was not added to card [MyCard1] !");
     	System.out.println("Member [HH] added to card [MyCard1]");		
    }

    //@And("^Users closes the members popup$")
    
    // Add comments

	//@Gievn("^User clicks on MyBoard box$")
    
    //@When("^User clicks on first card$")

    @Then("^User types some comments$")
    public void user_types_some_comments() throws Throwable {
    	trelloElements.setComment("Comment1");
    }

    @And("^User clicks on save button$")
    public void user_clicks_on_save_button() throws Throwable {
    	trelloElements.clickSaveComment();
    }

    @And("^Comment is added$")
    public void comment_is_added() throws Throwable {
    	if(trelloElements.getComment("Comment1") == null)
    		throw new PendingException("Comment [Comment1] not added !");			
     	System.out.println("Comment [Comment1] added to card [MyCard1]");		
    }

    //@And("^User closes the card popup$")
    
    // Remove comments
    
	//@Given("^User clicks on first card$")

    @When("^User clicks on comment delete link$")
    public void user_clicks_on_comment_delete_link() throws Throwable {
    	trelloElements.clickDeleteComment("Comment1");
    }

    @Then("^Comment is removed$")
    public void comment_is_removed() throws Throwable {
		if(trelloElements.getComment("Comment1") != null)
			throw new PendingException("Comment [Comment1] not removed !");			
     	System.out.println("Comment [Comment1] removed from card [MyCard1]");
    }

    //@And("^User closes the card popup$")
   
    // Add a Due date

    //@Then("^User clicks on duedate button$")

    @Then("^User sets the date and click on save button$")
    public void user_sets_the_date_and_click_on_save_button() throws Throwable {
    }

    @Then("^Date is set$")
    public void date_is_set() throws Throwable {
    }

    //@Then("^Users closes the duedate popup$")
   
    // Remove the due date
    
    //@Then("^User clicks on duedate button$")
    
    @When("^Duedate is removed$")
    public void duedate_is_removed() throws Throwable {
    }

    @When("^Users closes the duedate popup$")
    public void users_closes_the_duedate_popup() throws Throwable {
    }
}