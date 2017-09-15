package PageObject;

import java.util.List;
import org.openqa.selenium.By;
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
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='MyBoard']"))); // Wait till next page is loaded		
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
	public String getBoardName() throws InterruptedException { // Get current board name
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
	public String getBoardClosedMessage() throws InterruptedException {
		WebElement closedMessage = boardClosedMessage.findElement(By.xpath(".//h1"));
		wait.until(ExpectedConditions.visibilityOf(closedMessage));		
		return closedMessage.getAttribute("innerText");
	}
	public void clickDeleteBoard() throws InterruptedException {
		wait.until(ExpectedConditions.elementToBeClickable(deleteBoardLink)).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Delete']")));
		wait.until(ExpectedConditions.elementToBeClickable(deleteBoardButton)).click();
	}
	public String getBoardDeletedMessage() throws InterruptedException {
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

	// Card [
	public List<WebElement> getCards() {
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
	// Card ]

	// CheckList [
	public List<WebElement> getChecklists() { // Checklist of the current card popped up
		return checklists; 
	}
	public WebElement getChecklist(String checklistName) throws Exception {
		for(WebElement checklist : checklists)
			if(getCheckListName(checklist).equals(checklistName)) return checklist;
		throw new PendingException("Checklist [" + checklistName + "] not found !");
	}
	public List<WebElement> getChecklistItems(WebElement checklist){
		return checklist.findElements(By.xpath(".//div[@class='checklist-item']"));
	}
	public String getCheckListName(WebElement checklist) {
		return checklist.findElement(By.xpath(".//h3[@class='current hide-on-edit']")).getText();
	}		
	public void clickChecklist() throws InterruptedException {
		checklistButton.click();
	}
	public void setNewChecklistTitle(String title) throws InterruptedException {
		newChecklistTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"), title);
	}
	public void clickAddChecklist() throws InterruptedException {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='primary wide confirm js-add-checklist']")));
		wait.until(ExpectedConditions.elementToBeClickable(addChecklist)).click();
	}
	public void clickDelChecklist(WebElement checklist) throws InterruptedException {
		WebElement deleteChecklistLink = checklist.findElement(By.xpath(".//a[@class='hide-on-edit quiet js-confirm-delete']"));
		wait.until(ExpectedConditions.elementToBeClickable(deleteChecklistLink)).click();
		wait.until(ExpectedConditions.elementToBeClickable(deleteChecklistButton)).click();
	}
	public void clickAddItem(WebElement checklist, String item) {
		WebElement addItem = checklist.findElement(By.xpath(".//textarea[@placeholder='Add an item…']"));
		addItem.sendKeys(Keys.chord(Keys.CONTROL, "a"), item);
		addItem.click(); // Click 'Add an item' link
		checklist.findElement(By.xpath(".//input[@value='Add']")).click(); // Click 'Add' button to confirm
	}
	public String getItemName(WebElement item) {
		return item.getAttribute("innerText");
	}
	public WebElement getItem(List<WebElement> items, String itemName) {
		for(WebElement item : items) {
			// gggg
			if(item.getAttribute("innerText").equals(itemName)) return item;
		}
		throw new PendingException("Item " + itemName + " not found !");
	}
	// CheckList ]
	
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
