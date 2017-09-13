package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrelloElements {

	final WebDriver driver;
	WebDriverWait wait;

	public TrelloElements(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}
	
	@FindBy(how = How.XPATH, xpath = "//input[@id='login']")
	WebElement login;
	@FindBy(how = How.XPATH, xpath = "//input[@type='email']")
	WebElement email;
	@FindBy(how = How.XPATH, xpath = "//input[@type='password']")
	WebElement password;
	@FindBy(how = How.XPATH, xpath = "//div[@class='boards-page-board-section mod-no-sidebar']")
	List<WebElement> teams;
	@FindBy(how = How.XPATH, xpath = "//div[@class='list-header js-list-header u-clearfix is-menu-shown']")
	WebElement taskList;
	@FindBy(how = How.XPATH, xpath = "//a[@class='list-card js-member-droppable ui-droppable']")
	List<WebElement> cards;
	@FindBy(how = How.XPATH, xpath = "//div[@class='window']")
	WebElement card;
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
	
	// Teams and boards [
	
	//  Teams and boards [
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
	public void clickTeamBoards(String teamName) throws Exception { // Clicks on team 'Boards' button
		for(WebElement team : teams)
			if(team.findElement(By.xpath(".//h3[@class='boards-page-board-section-header-name']")).getAttribute("innerText").equals(teamName)) {
				team.findElement(By.xpath(".//a[@class='boards-page-board-section-header-options-item dark-hover']")).click();
				return;
			}
		throw new Exception("Team [" + teamName + "] was not found !");
	}
	public void clickTeamBoard(String boardName) throws Exception { // Clicks on team {boardName}
		for(WebElement team: teams)
			if(team.findElement(By.xpath(".//span[@class='board-tile-details-name']")).getAttribute("innerText").equals(boardName)) {
				wait.until(ExpectedConditions.elementToBeClickable(team.findElement(By.xpath(".//span[@class='board-tile-details is-badged']")))).click();
				return;
			}
		throw new Exception("Board [" + boardName + "] was not found !");
	}
	public void clickNewBoard(String teamName) throws Exception { // Click on 'Create New Board' of teamName 
		wait.until(ExpectedConditions.elementToBeClickable(getTeam(teamName).findElement(By.xpath(".//a[@class='board-tile mod-add']")))).click();
	}
	public void setBoard(String title) throws Exception { // Set name for new board in Create Board box
		newBoardTitle.sendKeys(Keys.chord(Keys.CONTROL, "a"), title);			
	}
	public String getBoardName() throws InterruptedException { // Get current board name
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
		Thread.sleep(1000);
		wait.until(ExpectedConditions.elementToBeClickable(deleteBoardButton)).click();
	}
	public void getBoardDeletedMessage() throws InterruptedException {
		Thread.sleep(1000);	
		WebElement deletedMessage = boardDeletedMessage.findElement(By.xpath(".//h1"));
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return deletedMessage.getAttribute("innerText").equals("Board not found.");
			}
		});
	}
	//  Teams and boards ]
		
	public WebElement getTaskList() {
		return taskList;
	}
	public void waitTaskList(String value) {
		Setup.waitTillValue(taskList, value);
	}
	public String getTaskListTitle() {
		return taskList.findElement(By.xpath("//h2[@class='list-header-name-assist js-list-name-assist']")).getAttribute("textContent");
	}
	public String getTaskListCardTot() {
		return taskList.findElement(By.xpath("//p[@class='list-header-num-cards hide js-num-cards']")).getAttribute("innerText");
	}

	public List<WebElement> getCards() {
		return taskList.findElements(By.xpath("//a[@class='list-card js-member-droppable ui-droppable']"));
	}

	public WebElement getCard() {
		return card;
	}
	public WebElement waitCard() {
		return card;
	}
	public void closeCard() {
		WebElement close = card.findElement(By.xpath(".//a[@class='icon-lg icon-close dialog-close-button js-close-window']"));
		wait.until(ExpectedConditions.elementToBeClickable(close));
		close.click();
	}
	
	public List<String> getItems(){
		List<String> items = new ArrayList<String>();
		for(WebElement item :card.findElements(By.xpath(".//div[@class='checklist-item']")))
				items.add(item.getAttribute("innerText"));
		return items;
	}
	
	public void goMainPage() {
		wait.until(ExpectedConditions.elementToBeClickable(backToMain));
		backToMain.click();
	}
	
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
}
