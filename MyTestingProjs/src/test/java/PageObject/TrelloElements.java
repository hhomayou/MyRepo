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
	@FindBy(how = How.XPATH, xpath = "//a[@class='board-tile']")
	WebElement board;
	@FindBy(how = How.XPATH, xpath = "//div[@class='list-header js-list-header u-clearfix is-menu-shown']")
	WebElement taskList;
	@FindBy(how = How.XPATH, xpath = "//a[@class='list-card js-member-droppable ui-droppable']")
	List<WebElement> cards;
	@FindBy(how = How.XPATH, xpath = "//div[@class='window']")
	WebElement card;

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
	
	public WebElement getBoard() {
		return board;
	}
	public String getBoardTeamNames() {
		return "Board: " + board.findElement(By.xpath("//span[@class='board-tile-details-name']")).getText() +
				"\nTeam: " + board.findElement(By.xpath("//span[@class='board-tile-details-sub-name']")).getText();
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
}
