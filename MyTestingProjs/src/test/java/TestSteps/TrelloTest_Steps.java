package TestSteps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PageObject.Setup;
import PageObject.TrelloElements;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TrelloTest_Steps extends Setup {

	public static TrelloElements trelloElements;
	public static final String emailTest = "hhomayounfar@qaconsultants.com";
	public static final String passwordTest = "pswdpswd";

	WebElement login, team, member, comment;
	String boardName;
	private static boolean dunit = false;

	@Before
	public void beforeAll() throws Exception {
		if (!dunit) {
			dunit = true;
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
			if (!trelloElements.getEmail().equals(emailTest)) { // Email is not entered yet
				trelloElements.setEmail(emailTest);
				trelloElements.setPassword(passwordTest);
				trelloElements.waitPassword(passwordTest);
			}
			login.click();
			trelloElements.waitLoginDone();
			System.out.println("User [" + emailTest + "] logged in successfully");
		}
	}

	@After
	public void afterEachScenrio() {
		if (trelloElements.cardIsActvie())
			trelloElements.closeCard();
	}

	// Login and display teams

	@Given("^List of teams and boards is displayed in the main page$")
	public void list_of_teams_and_boards_is_displayed_in_the_main_page() throws Throwable {
		trelloElements.listTeams();
	}

	@When("^User clicks on MyBoard box$")
	public void user_clicks_on_MyBoard_box() throws Throwable { // and goes to MyBoard page
		trelloElements.clickMyBoard();
	}

	@Then("^Page MyBoard is loaded and it displays all the info$")
	public void page_MyBoard_is_loaded_and_it_displays_all_the_info() throws Throwable {
		System.out.println("List name: " + trelloElements.getBoardListTitle() + ", "
				+ trelloElements.getBoardListCardTot() + ": ");
		trelloElements.displayBoardListInfo();
	}

	// Display a card

	@Given("^User clicks on first card$")
	public void user_clicks_on_first_card() throws Throwable {
		trelloElements.getCards().get(0).click();
		System.out.println("\nCard [" + trelloElements.getCardName() + "] opens, checklist(s): ");
		for (WebElement checklist : trelloElements.getChecklists())
			System.out.println("Checklist [" + trelloElements.getCheckListName(checklist) + "]");
		System.out.println("");
	}

	@When("^Card info is loaded and popped up$")
	public void Card_info_is_loaded_and_popped_up() throws Throwable {
		System.out.println("Items:");
		for (WebElement item : trelloElements.getChecklistItems(trelloElements.getChecklist("MyChecklist")))
			System.out.println("  " + trelloElements.getItemName(item));
	}

	@Then("^User closes the card popup$")
	public void user_closes_the_card_popup() throws Throwable {
		trelloElements.closeCard();
	}

	// Create a team

	@Given("^User clicks on create new team link$")
	public void user_clicks_on_create_new_team_link() throws Throwable {
		trelloElements.clickNewteam();
	}

	@When("^User enters name and description$")
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

	@Given("^User is in the team page$")
	public void user_is_in_the_team_page() throws Throwable {
		team = trelloElements.getNewTeam(); // Get the new team
	}

	@When("^User selects setting tab$")
	public void user_selects_setting_tab() throws Throwable {
		trelloElements.clickTeamSetting();
	}

	@Then("^User clicks delete this team$")
	public void user_clicks_delete_this_team() throws Throwable {
		trelloElements.clickDeleteTeam();
	}

	@And("^User confirms and deletes the team$")
	public void user_confirms_and_deletes_the_team() throws Throwable {
		trelloElements.clickDeleteTeamConfirm();
		System.out.println("Team [" + team.getText() + "] deleted successfully");
	}

	// Create a board

	@Given("^User clicks on create new board box$")
	public void user_clicks_on_create_new_board_box() throws Throwable {
		trelloElements.clickNewBoard("MyTeam");
	}

	@When("^User enters Title and selects first Team$")
	public void user_enters_Title_and_selects_first_Team() throws Throwable {
		trelloElements.setBoard("Board1");
		trelloElements.clickCreateBoard();
	}

	@Then("^The new board is created$")
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

	@Then("^User clicks close board link and close button$")
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

	@And("^Go back to main page$")
	public void go_back_to_main_page() throws Throwable {
		afterEachScenrio();
		// if(trelloElements.goBackToMainIsActive()) {
		trelloElements.goBackToMain();
		System.out.println("Going back to main page");
		// }
	}

	// Create a checklist

	@Then("^User click on checklist button$")
	public void user_click_on_checklist_button() throws Throwable {
		trelloElements.clickChecklist();
	}

	@And("^User enters a the new checklist title and click on Add button$")
	public void user_enters_a_the_new_checklist_title_and_click_on_Add_button() throws Throwable {
		trelloElements.setNewChecklistTitle("Checklist1");
		trelloElements.clickAddChecklist();
	}

	@And("^Checklist is added to the card$")
	public void checklist_is_added_to_the_card() throws Throwable {
		System.out.println("Checklist [" + trelloElements.getCheckListName(trelloElements.getChecklist("Checklist1"))
				+ "] added to the card [" + trelloElements.getCardName() + "]");
	}

	// Delete the checklist

	@When("^User clicks on delete link$")
	public void user_clicks_on_delete_link() throws Throwable {
		trelloElements.clickDelChecklist(trelloElements.getChecklist("Checklist1"));
	}

	@Then("^Checklist is deleted$")
	public void checklist_is_deleted() throws Throwable {
		if (trelloElements.getChecklist("Checklist1") != null)
			throw new PendingException("Checklist [Checklist1] not deleted !");
		System.out.println("Checklist [Checklist1] successfully deleted");
	}

	// Add an item to a checklist

	@Then("^User clicks on add an item box of mychecklist and enters item$")
	public void user_clicks_on_add_an_item_box_of_mychecklist_and_enters_item() throws Throwable {
		trelloElements.addItem(trelloElements.getChecklist("MyChecklist"), "Item1");
	}

	@And("^Item is added to the checklist$")
	public void item_is_added_to_the_checklist() throws Throwable {
		WebElement item = trelloElements
				.getItem(trelloElements.getChecklistItems(trelloElements.getChecklist("MyChecklist")), "Item1");
		trelloElements.cancelAddItem(item);
		System.out.println("Item [Item1] added to checklist [MyChecklist]");
	}

	// Complete an item in the checklist

	@When("^User clicks on box left to the item$")
	public void user_clicks_on_box_left_to_the_item() throws Throwable {
		trelloElements.toggleItemCheckbox(trelloElements
				.getItem(trelloElements.getChecklistItems(trelloElements.getChecklist("MyChecklist")), "Item1"));
	}

	@Then("^Item is completed$")
	public void item_is_completed() throws Throwable {
		trelloElements.getItem(trelloElements.getChecklistItemsCompleted(trelloElements.getChecklist("MyChecklist")),
				"Item1");
		System.out.println("Item [Item1] completed in checklist[MyChecklist]");
	}

	// Delete the item from the checklist

	@When("^User clicks on the item$")
	public void user_clicks_on_the_item() throws Throwable {
		WebElement item = trelloElements.getItem(
				trelloElements.getChecklistItemsCompleted(trelloElements.getChecklist("MyChecklist")), "Item1");
		wait.until(ExpectedConditions.elementToBeClickable(item)).click();
	}

	@Then("^Users clicks on Delete link$")
	public void users_clicks_on_Delete_link() throws Throwable {
		trelloElements.delItem();
	}

	@And("^Item is deleted$")
	public void item_is_deleted() throws Throwable {
		if (trelloElements.getItem(
				trelloElements.getChecklistItemsCompleted(trelloElements.getChecklist("MyChecklist")), "Item1") != null)
			throw new PendingException("Item [Item1] not deleted from checklist [MyChecklist] !");
		System.out.println("Item [Item1] deleted from checklist [MyChecklist]");
	}

	// Remove a member

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
		if (trelloElements.getMemberStatus(member))
			throw new PendingException("Member [HH] was not removed from card [MyCard1] !");
		System.out.println("Member [HH] removed from card [MyCard1]");
	}

	@And("^Users closes the members popup$")
	public void users_closes_the_members_popup() throws Throwable {
		trelloElements.closeMember();
	}

	// Add/Remove a member

	@Then("^Member is added to the card$")
	public void member_is_added_from_the_card() throws Throwable {
		if (!trelloElements.getMemberStatus(member))
			throw new PendingException("Member [HH] not added to card [MyCard1] !");
		System.out.println("Member [HH] added to card [MyCard1]");
	}

	// Add comments

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
		if (!trelloElements.commentExists("Comment1"))
			throw new PendingException("Comment [Comment1] not added !");
		System.out.println("Comment [Comment1] added to card [MyCard1]");
	}

	// Edit comment

	@When("^User clicks on comment edit link$")
	public void user_clicks_on_comment_edit_link() throws Throwable {
		trelloElements.clickEditComment("Comment1");
	}

	@Then("^User edits the comment and saves$")
	public void user_edits_the_comment_and_saves() throws Throwable {
		trelloElements.updateComment("Comment1", "Comment2");
	}

	@And("^Comment is updated$")
	public void comment_is_updated() throws Throwable {
		if (!trelloElements.commentExists("Comment2"))
			throw new PendingException("Comment [Comment1] not updated to [Comment2] !");
		System.out.println("Comment [Comment1] updated to [Comment2] in card [MyCard1]");
	}

	@And("^Revers the update$")
	public void revers_the_update() throws Throwable {
		trelloElements.clickEditComment("Comment2");
		trelloElements.updateComment("Comment2", "Comment1");
		if (!trelloElements.commentExists("Comment1"))
			throw new PendingException("Comment [Comment2] not reveresed to [Comment1] !");
		System.out.println("Comment [Comment2] reversed to [Comment1] in card [MyCard1]");
	}

	// Remove comments

	@When("^User clicks on comment delete link$")
	public void user_clicks_on_comment_delete_link() throws Throwable {
		trelloElements.clickDeleteComment("Comment1");
	}

	@Then("^Comment is removed$")
	public void comment_is_removed() throws Throwable {
		if (trelloElements.commentExists("Comment1"))
			throw new PendingException("Comment [Comment1] not removed !");
		System.out.println("Comment [Comment1] removed from card [MyCard1]");
	}

	// Add/change a due date

	@Then("^User clicks on duedate button$")
	public void dueDateClick() {
		trelloElements.duedateClick();
	}

	@And("^User sets the date and click on save button$")
	public void user_sets_the_date_and_click_on_save_button() throws Throwable {
		trelloElements.dueDateSave("26-5-2018");
	}

	@And("^Date is set$")
	public void date_is_set() throws Throwable {
		System.out.println("Duedate [" + trelloElements.dueDateValue() + "] set for [MyCard1]");
	}

	// Remove the due date

	@Then("^User clicks on remove button$")
	public void user_clicks_on_remove_button() throws Throwable {
		trelloElements.dueDateDelete();
	}

	@And("^Duedate is removed$")
	public void duedate_is_removed() throws Throwable {
		if (trelloElements.dueDateValue() != null)
			throw new PendingException("Due date not removed from [MyCard1]");
		System.out.println("Duedate [26-5-2018] removed from [MyCard1]");
	}

	// Add a label

	@Then("^User click on labels button$")
	public void user_click_on_labels_button() throws Throwable {
		trelloElements.labelsClick();
	}

	@And("^User toggle blue label$")
	public void user_toggle_blue_label() throws Throwable {
		trelloElements.labelBlueToggle();
	}

	@And("^Label is added to the card$")
	public void label_is_added_to_the_card() throws Throwable {
		if (!trelloElements.labelBlueIsSelected())
			throw new PendingException("Label [blue] not selected !");
		System.out.println("Label [blue] added to [MyCard1]");
	}

	// Remove the label

	@And("^Label is removed from the card$")
	public void label_is_removed_from_the_card() throws Throwable {
		if (trelloElements.labelBlueIsSelected())
			throw new PendingException("Label [blue] not deselected !");
		System.out.println("Label [blue] removed from [MyCard1]");
	}
}