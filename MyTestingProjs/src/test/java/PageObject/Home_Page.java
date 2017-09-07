package PageObject;

import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.regex.*;

public class Home_Page {

	public static final String pageURI = "https://online.royalbank.com/cgi-bin/tools/foreign-exchange-calculator/start.cgi";
	final WebDriver driver;
	@FindBy(how = How.XPATH, xpath = "//div[@class='currency-select ui fluid selection dropdown']")
	public List<WebElement> currencyHaveUnitsParents;
	@FindBy(how = How.XPATH, xpath = "//select[@id='currency-have-unit']")
	public WebElement currencyHaveUnitsRO;
	@FindBy(how = How.XPATH, xpath = "//div[@class = 'menu transition visible']/div[starts-with(@class , 'item')]")
	public List<WebElement> currencyHaveUnits;
	@FindBy(how = How.XPATH, xpath = "//input[@id='currency-have-amount']")
	public WebElement currencyHaveAmount;
	@FindBy(how = How.XPATH, xpath = "//span[@id='noncash-currencyHaveRate']")
	public WebElement noncashCurrencyHaveRate;
	@FindBy(how = How.XPATH, xpath = "//select[@id='currency-want-unit']")
	public WebElement currencyWantUnit;
	@FindBy(how = How.XPATH, xpath = "//input[@id='currency-want-amount']")
	public WebElement currencyWantAmount;
	@FindBy(how = How.XPATH, xpath = "//span[@id='noncash-currencyWantRate']")
	public WebElement noncashCurrencyWantRate;
	WebDriverWait wait;

	public Home_Page(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 10);
	}

	// Gets & Sets
	// Left panel
	public List<WebElement> getCurrencyHaveUnitsParents() {
		return currencyHaveUnitsParents;
	}

	public void waitCurrencyHaveUnitsParents() {
		wait.until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return currencyHaveUnitsParents.size() == 2;
			}
		});			
	}

	public Select getCurrencyHaveUnitsRO() {
		return new Select(currencyHaveUnitsRO);
	}

	public List<WebElement> getCurrencyHaveUnits() {
		return (List<WebElement>) currencyHaveUnits;
	}

	public String getCurrencyHaveUnit() {
		return (new Select(currencyHaveUnitsRO)).getFirstSelectedOption().getAttribute("value");
	}

	public WebElement findCurrencyHaveUnit(String unit) throws Exception { // Unit menu should be clicked
		for (WebElement currencyHaveUnit : currencyHaveUnits)
			if (currencyHaveUnit.getAttribute("data-value").equals(unit))
				return currencyHaveUnit;
		throw new Exception("Unit " + unit + ": was not found !");
	}

	public void setCurrencyHaveUnit(WebElement unit) throws InterruptedException { // Click to be set (Unit menu should
																					// be clicked)
		Thread.sleep(500);
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(unit));
		unit.click();
	}

	public double getCurrencyHaveAmount() {
		return getDouble(currencyHaveAmount.getAttribute("value"));
	}

	public void setCurrencyHaveAmount(double currencyHaveAmount) {
		this.currencyHaveAmount.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(currencyHaveAmount));
	}

	public double getNoncashCurrencyHaveRate() {
		return Double.parseDouble(noncashCurrencyHaveRate.getText());
	}

	// Right panel
	public String getCurrencyWantUnit() {
		return currencyWantUnit.getAttribute("value");
	}

	public double getCurrencyWantAmount() {
		return getDouble(currencyWantAmount.getAttribute("value"));
	}

	public void setCurrencyWantAmount(double currencyWantAmount) {
		this.currencyWantAmount.sendKeys(Keys.chord(Keys.CONTROL, "a"), String.valueOf(currencyWantAmount));
	}

	public void waitNotCurrencyWantAmount(double value) {
		waitTillValueNot(currencyWantAmount, String.valueOf(value));
	}

	public double getNoncashCurrencyWantRate() {
		return Double.parseDouble(noncashCurrencyWantRate.getText());
	}

	// Other functions
	public void waitTillValue(WebElement webElement, String value) {
		wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, value));
	}

	public void waitTillValueNot(WebElement webElement, String value) {
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(webElement, value)));
	}

	double getDouble(String val) { // Get number out of string
		Matcher matcher = Pattern.compile("\\d+(\\.\\d+)?").matcher(val);
		matcher.find();
		return Double.parseDouble(matcher.group());
	}
}
