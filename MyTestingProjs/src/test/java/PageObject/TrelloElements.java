package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
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
		return board.findElement(By.xpath("//span[@class='board-tile-details-name']")).getText();
	}
}
