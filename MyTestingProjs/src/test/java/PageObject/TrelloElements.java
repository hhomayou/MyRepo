package PageObject;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
	List<WebElement> boards;
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
	WebElement teamName;
	@FindBy(how = How.XPATH, xpath = "//textarea[@id='org-desc']")	
	WebElement teamDescription;
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
	
	public List<WebElement> getBoards() {
		return boards;
	}	
	public void displayBoardTeamNames() {
		System.out.println("List of the boards/teams:");
		for(WebElement board : boards) {
			System.out.print("Team: [" + board.findElement(By.xpath(".//h3[@class='boards-page-board-section-header-name']")).getAttribute("innerText") + "]");
			System.out.println(", Board: [" + board.findElement(By.xpath(".//span[@class='board-tile-details-name']")).getAttribute("innerText") + "]");
		}
	}

	public void clickTeamBoard(String teamName) throws Exception {
		for(WebElement board : this.boards)
			if(board.findElement(By.xpath(".//h3[@class='boards-page-board-section-header-name']")).getAttribute("innerText").equals(teamName)) {
				board.findElement(By.xpath(".//a[@class='boards-page-board-section-header-options-item dark-hover']")).click();
				return;
			}
		throw new Exception("Team [" + teamName + "] was not found !");
	}

	
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
	
	public String getTeamName() {
		return teamName.getAttribute("value");
	}
	public void waitTeamName(String teamName) {
		Setup.waitTillValue(this.teamName, teamName);
	}
	public void setTeamName(String teamName) {
		this.teamName.sendKeys(Keys.chord(Keys.CONTROL, "a"), teamName);
	}

	public String getTeamDescription() {
		return teamDescription.getAttribute("value");
	}
	public void setTeamDescription(String teamDescription) {
		this.teamDescription.sendKeys(Keys.chord(Keys.CONTROL, "a"), teamDescription);
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
