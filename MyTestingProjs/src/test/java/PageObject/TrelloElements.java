package PageObject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.PendingException;

public class TrelloElements {

	WebDriverWait wait = Setup.wait;

	@FindBy(how = How.XPATH, xpath = "//input[@id='login']")
	WebElement login;
	@FindBy(how = How.XPATH, xpath = "//input[@type='email']")
	WebElement email;
	@FindBy(how = How.XPATH, xpath = "//input[@type='password']")
	WebElement password;
	@FindBy(how = How.XPATH, xpath = "//div[@class='boards-page-board-section mod-no-sidebar']")
	List<WebElement> teams;
	@FindBy(how = How.XPATH, xpath = "//span[@class='board-header-btn-text']")
	WebElement boardTitle;
	@FindBy(how = How.XPATH, xpath = "//div[@class='list-header js-list-header u-clearfix is-menu-shown']")
	WebElement boardList;
	@FindBy(how = How.XPATH, xpath = "//a[@class='list-card js-member-droppable ui-droppable']")
	List<WebElement> cards;
	@FindBy(how = How.XPATH, xpath = "//div[@class='window']")
	WebElement card; // Current card popped up 
	@FindBy(how = How.XPATH, xpath = "//span[@class='header-btn-icon icon-lg icon-board-back-to-home light']")
	WebElement backToMain;
	@FindBy(how = How.XPATH, xpath = "//div[@class='boards-page-board-section u-clearfix']")
	WebElement newTeamParent;
	@FindBy(how = How.XPATH, xpath = "//input[@id='org-display-name']")	
	WebElement newTeamName;
	@FindBy(how = How.XPATH, xpath = "//textarea[@id='org-desc']")	
	WebElement newTeamDescription;
	@FindBy(how = How.XPATH, xpath = "//input[@class='primary wide js-save']")	
	WebElement createTeam;	
	@FindBy(how = How.XPATH, xpath = "//h1[@class='u-inline']")
	WebElement newTeam;
	@FindBy(how = How.XPATH, xpath = "//a[@data-tab='settings']")
	WebElement teamSetting;
	@FindBy(how = How.XPATH, xpath = "//a[@class='quiet-button']/span")
	WebElement deleteTeam;
	@FindBy(how = How.XPATH, xpath = "//input[@value='Delete Forever']")
	WebElement deleteTeamConfirm;
	@FindBy(how = How.XPATH, xpath = "//input[@id='boardNewTitle']")
	WebElement newBoardTitle;
	@FindBy(how = How.XPATH, xpath = "//select[@name='org-id']")
	List<WebElement> newBoardTeams;
	@FindBy(how = How.XPATH, xpath = "//input[@value='Create']")
	WebElement createBoard;
	@FindBy(how = How.XPATH, xpath = "//div[@class='board-header u-clearfix js-board-header']")
	WebElement board;
	@FindBy(how = How.XPATH, xpath = "//li[a[contains(text(),'More')]]")
	WebElement more;
	@FindBy(how = How.XPATH, xpath = "//a[@class='board-menu-navigation-item-link js-close-board']")
	WebElement closeBoardLink;
	@FindBy(how = How.XPATH, xpath = "//input[@value='Close']")
	WebElement closeBoardButton;
	@FindBy(how = How.XPATH, xpath = "//div[@class='big-message quiet']")
	WebElement boardClosedMessage;
	@FindBy(how = How.XPATH, xpath = "//a[@class='quiet js-delete']")
	WebElement deleteBoardLink;
	@FindBy(how = How.XPATH, xpath = "//input[@value='Delete']")
	WebElement deleteBoardButton;
	@FindBy(how = How.XPATH, xpath = "//div[@class='big-message quiet']")
	WebElement boardDeletedMessage;
	@FindBy(how = How.XPATH, xpath = "//li[@class='boards-page-board-section-list-item' and .//span[@title='MyBoard']]")
	WebElement myBoard;	
	@FindBy(how = How.XPATH, xpath = "//a[@class='button-link js-add-checklist-menu']")
	WebElement checklistButton;
	@FindBy(how = How.XPATH, xpath = "//input[@id='id-checklist']")
	WebElement newChecklistTitle;
	@FindBy(how = How.XPATH, xpath = "//input[@class='primary wide confirm js-add-checklist']") //"//input[@value='Add']")
	WebElement addChecklist;
	@FindBy(how = How.XPATH, xpath = "//div[@class='checklist']") // Checklists of current card popped up
	List<WebElement> checklists;
	@FindBy(how = How.XPATH, xpath = "//input[@value='Delete Checklist']")
	WebElement deleteChecklistButton; // Delete Confirmation
	@FindBy(how = How.XPATH, xpath = "//a[@class='icon-lg icon-close dark-hover cancel js-cancel-checklist-item']")
	WebElement cancelAddItem;
	@FindBy(how = How.XPATH, xpath = "//a[@class='option delete js-delete-item']")
	WebElement delItem;
	@FindBy(how = How.XPATH, xpath = "//a[@class='button-link js-change-card-members']")
	WebElement membersButton;
	@FindBy(how = How.XPATH, xpath = "//div[li[contains(@class, 'item js-member-item')]]")
	List<WebElement> cardMembers;
	@FindBy(how = How.XPATH, xpath = "//a[@class='pop-over-header-close-btn icon-sm icon-close']")
	WebElement closeMemberButton;		
	@FindBy(how = How.XPATH, xpath = "//textarea[@class='comment-box-input js-new-comment-input']")
	WebElement commentTextarea;		
	@FindBy(how = How.XPATH, xpath = "//input[@class='primary confirm mod-no-top-bottom-margin js-add-comment']")
	WebElement saveCommentButton;		
	@FindBy(how = How.XPATH, xpath = "//input[@class='primary confirm js-save-edit']")
	WebElement saveEditedCommentButton;		
	@FindBy(how = How.XPATH, xpath = "//input[@value='Delete Comment']")
	WebElement deleteCommentButton;		
	@FindBy(how = How.XPATH, xpath = "//div[@class='phenom mod-comment-type' and ancestor::div[@class='window-overlay']]")
	List<WebElement> comments;

	// Email, password login [
	public String getEmail() {
		return email.getAttribute("value");
	}
	public void setEmail(String email) {
		this.email.sendKeys(Keys.chord(Keys.CONTROL, "a"), email);
	}
	public void waitEmail(String value) {
		Setup.waitTillValue(email, value);
	}

	public String getPassword() {
		return password.getAttribute("value");
	}
	public void setPassword(String password) {
		this.password.sendKeys(Keys.chord(Keys.CONTROL, "a"), password);
	}
	public void waitPassword(String value) {
		Setup.waitTillValue(password, value);
	}
	
	public WebElement getLogin() {
		return login;
	}
	public void waitLoginDone() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.className(myBoard.getAttribute("class")))); // Wait till next page is loaded
	}
	
	// Team [
	public List<WebElement> getTeams() {
		return teams;
	}
	public WebElement getTeam(String teamName) throws Exception { // Get team from the list of teams
		for(WebElement team : teams)
			if(team.findElement(By.xpath(".//h3[@class='boards-page-board-section-header-name']")).getAttribute("innerText").equals(teamName))
				return team;
		throw new Exception("Team [" + teamName + "] was not found !");
	}

	public void listTeams() {
		System.out.println("List of the teams:");
		for(WebElement team : teams) {
			System.out.print("Team: [" + team.findElement(By.xpath(".//h3[@class='boards-page-board-section-header-name']")).getAttribute("innerText") + "]");
			System.out.println(", Board: [" + team.findElement(By.xpath(".//span[@class='board-tile-details-name']")).getAttribute("innerText") + "]");
		}
	}
	// Team ]

	// Board [
	public void clickMyBoard() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[@class='boards-page-board-section-list-item']"))); // Wait till next page is loaded				
		wait.until(ExpectedConditions.elementToBeClickable(myBoard)).click();		
	}
	public void clickNewBoard(String teamName) throws Exception { // Click on 'Create New Board' of teamName 
		wait.until(ExpectedConditions.elementToBeClickable(getTeam(teamName).findElement(By.xpath(".//a[@class='board-tile mod-add']")))).click();
	}
	public void setBoard(String title) throws Exception { // Set name for new board in Create Board box
		newBoardTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"), title);			
	}
	public String getBoardName() { // Get current board name
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='board-header-btn-text']"))); // Wait till page is loaded
		return board.findElement(By.xpath(".//span[@class='board-header-btn-text']")).getAttribute("innerText");
	}
	public void clickCreateBoard() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(createBoard));
		Thread.sleep(500);		
		createBoard.click();
	}
	public void clickMore() { // Click on ... more link in board page
		wait.until(ExpectedConditions.elementToBeClickable(more)).click();
	}
	public void clickCloseBoard() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(closeBoardLink)).click();
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(closeBoardButton)).click();
	}
	public String getBoardClosedMessage() {
		WebElement closedMessage = boardClosedMessage.findElement(By.xpath(".//h1"));
		wait.until(ExpectedConditions.visibilityOf(closedMessage));		
		return closedMessage.getAttribute("innerText");
	}
	public void clickDeleteBoard() {
		wait.until(ExpectedConditions.elementToBeClickable(deleteBoardLink)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Delete']")));
		wait.until(ExpectedConditions.elementToBeClickable(deleteBoardButton)).click();
	}
	public String getBoardDeletedMessage() {
		return boardDeletedMessage.findElement(By.xpath(".//h1")).getAttribute("innerText");
	}
	// Board ]
		
	// Board List [
	public String getBoardListTitle() {
		return boardList.findElement(By.xpath("//h2[@class='list-header-name-assist js-list-name-assist']")).getAttribute("textContent");
	}
	public String getBoardListCardTot() {
		return boardList.findElement(By.xpath("//p[@class='list-header-num-cards hide js-num-cards']")).getAttribute("innerText");
	}
	public void displayBoardListInfo() {
		for(WebElement card: getCards()) {
			System.out.print("Card: " + card.findElement(By.xpath(".//span[@class='list-card-title js-card-name']")).getAttribute("innerText"));
			System.out.print(", Items: " + card.findElement(By.xpath(".//span[@class='badge-text']")).getAttribute("innerText"));			
			System.out.print(", assigned to: ");
			for(WebElement member : card.findElements(By.xpath(".//span[@class='member-initials']")))
				System.out.print(" " + member.getAttribute("innerText"));
			System.out.println();
		}
	}
	// Board List ]

	// Board card [
	public List<WebElement> getCards() { // Get cards of the current board
		return boardList.findElements(By.xpath("//a[@class='list-card js-member-droppable ui-droppable']"));
	}
	public WebElement getCard() {
		return card;
	}
	public String getCardName() {
		return card.findElement(By.xpath(".//h2[@class='card-detail-title-assist js-title-helper']")).getAttribute("innerText");
	}
	public void closeCard() {
		WebElement close = card.findElement(By.xpath(".//a[@class='icon-lg icon-close dialog-close-button js-close-window']"));
		wait.until(ExpectedConditions.elementToBeClickable(close));
		close.click();
	}
	// Board Card ]

	// Card member [
	public void clickCardMembers() {
		membersButton.click();
	}
	public WebElement getCardMember(String memberInitial) { // Get the member of the current card popped up
		for(WebElement member : cardMembers) {
			WebElement memberFound = member.findElement(By.xpath("//span[@class='member-initials']"));
			if(memberFound.getText().equals(memberInitial)) return member;
		}
		throw new PendingException("Member [" + memberInitial + "] not found !");
	}
	public boolean getMemberStatus(WebElement member) {
    	String memberStatus = ((JavascriptExecutor) Setup.driver)
     		   .executeScript("return window.getComputedStyle(document.getElementsByClassName('icon-sm icon-check checked-icon')[0] , '').getPropertyValue('display')", member).toString();
    	return memberStatus.equals("block");
	}
	public void closeMember(){
		closeMemberButton.click();
	}
	// Card member ]

	// Card checkList [
	public List<WebElement> getChecklists() { // Checklist of the current card popped up
		return checklists; 
	}
	public WebElement getChecklist(String checklistName) throws Exception {
		for(WebElement checklist : checklists)
			if(getCheckListName(checklist).equals(checklistName)) return checklist;
		return null;
	}
	public String getCheckListName(WebElement checklist) {
		return checklist.findElement(By.xpath(".//h3[@class='current hide-on-edit']")).getText();
	}		
	public void clickChecklist() {
		checklistButton.click();
	}
	public void setNewChecklistTitle(String title) {
		newChecklistTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"), title);
	}
	public void clickAddChecklist() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='primary wide confirm js-add-checklist']")));
		wait.until(ExpectedConditions.elementToBeClickable(addChecklist)).click();
	}
	public void clickDelChecklist(WebElement checklist) {
		WebElement deleteChecklistLink = checklist.findElement(By.xpath(".//a[@class='hide-on-edit quiet js-confirm-delete']"));
		wait.until(ExpectedConditions.elementToBeClickable(deleteChecklistLink)).click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteChecklistButton)).click();
	}
	// Card checkList ]
	
	// Card comment [
	public void setComment(String comment) {
		commentTextarea.sendKeys(Keys.chord(Keys.CONTROL, "a"), comment);
	}
	public WebElement getComment(String commentString) {
		for(WebElement comment : comments)
			if(comment.findElement(By.xpath(".//div[@class='current-comment js-friendly-links js-open-card']/p")).getAttribute("innerText").equals(commentString)) 
					return comment;
		return null;
	}
	public void clickSaveComment() throws InterruptedException { // Save new comment
		wait.until(ExpectedConditions.elementToBeClickable(saveCommentButton)).click();
		Thread.sleep(500);		
	}
	public WebElement clickEditComment(String commentString) throws InterruptedException{
		WebElement comment = getComment(commentString);
		wait.until(ExpectedConditions.elementToBeClickable(comment.findElement(By.xpath(".//a[@class='js-edit-action']")))).click();
		Thread.sleep(500);				
		return comment;
	}
	public void updateComment(WebElement comment, String newComment) throws InterruptedException {
		WebElement commentTextarea = comment.findElement(By.xpath(".//textarea[@class='comment-box-input js-text']"));
		commentTextarea.sendKeys(Keys.chord(Keys.CONTROL, "a"), newComment);
		wait.until(ExpectedConditions.elementToBeClickable(saveEditedCommentButton)).click();
		Thread.sleep(500);
	}
	public void clickDeleteComment(String commentString) {
		WebElement deleteCommentLink = getComment(commentString).findElement(By.xpath(".//a[@class='js-confirm-delete-action']"));
		wait.until(ExpectedConditions.elementToBeClickable(deleteCommentLink)).click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteCommentButton)).click(); // Confirm
	}
	// Card comment ]
	
	// Checklist item [
	public List<WebElement> getChecklistItems(WebElement checklist){
		return checklist.findElements(By.xpath(".//div[@class='checklist-item']"));
	}
	public List<WebElement> getChecklistItemsCompleted(WebElement checklist){
		return checklist.findElements(By.xpath(".//div[@class='checklist-item checklist-item-state-complete']"));
	}
	public void addItem(WebElement checklist, String item) {
		WebElement addItem = checklist.findElement(By.xpath(".//textarea[@placeholder='Add an item…']"));
		addItem.sendKeys(Keys.chord(Keys.CONTROL, "a"), item);
		addItem.click(); // Click 'Add an item' link
		checklist.findElement(By.xpath(".//input[@value='Add']")).click(); // Click 'Add' button to confirm
	}
	public void cancelAddItem(WebElement item) {
		cancelAddItem.click();
	}
	public String getItemName(WebElement item) {
		return item.getAttribute("innerText");
	}
	public void toggleItemCheckbox(WebElement item) {
		item.findElement(By.xpath(".//div[@class='checklist-item-checkbox enabled js-toggle-checklist-item']")).click();
	}
	public WebElement getItem(List<WebElement> items, String itemName) {
		for(WebElement item : items)
			if(item.getText().equals(itemName)) return item;
		return null;
	}
	public void delItem() {
		delItem.click();
	}
	// Checklist item ]
	
	// Team [
	public void clickNewteam() {
		wait.until(ExpectedConditions.elementToBeClickable(newTeamParent)).click();
		WebElement newTeam = newTeamParent.findElement(By.xpath(".//a[@class='quiet-button u-float-left']"));
		wait.until(ExpectedConditions.elementToBeClickable(newTeam));
		newTeam.click();
	}
	public void waitTeamName(String teamName) {
		Setup.waitTillValue(newTeamName, teamName);
	}
	public void setTeamName(String teamName) {
		newTeamName.sendKeys(Keys.chord(Keys.CONTROL, "a"), teamName);
	}
	public String getTeamDescription() {
		return newTeamDescription.getAttribute("value");
	}
	public void setTeamDescription(String teamDescription) {
		newTeamDescription.sendKeys(Keys.chord(Keys.CONTROL, "a"), teamDescription);
	}
	public void clickCreateTeam() {
		wait.until(ExpectedConditions.elementToBeClickable(createTeam)).click();
	}
	public WebElement getNewTeam() {
		return newTeam;
	}
	public void clickTeamSetting() throws Exception {
		wait.until(ExpectedConditions.elementToBeClickable(teamSetting)).click();
	}
	public void clickDeleteTeam() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(deleteTeam));
		Thread.sleep(500);
		deleteTeam.click();
	}
	public void clickDeleteTeamConfirm() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(deleteTeamConfirm));
		Thread.sleep(500);
		deleteTeamConfirm.click();
	}
	// Team ]
}
