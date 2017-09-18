package TestSteps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PageObject.CurrencyElements;
import PageObject.Setup;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CurrencyTest_Steps extends Setup {

	WebElement currencyHaveUnitsParent0;
	WebElement currencyHaveUnitsParentHave;
	public static CurrencyElements currencyElements;

	@Given("^Initial test$")
	public void initialTest() throws Throwable {
		driver = beforeClassMethod("https://online.royalbank.com/cgi-bin/tools/foreign-exchange-calculator/start.cgi");
		currencyElements = PageFactory.initElements(driver, CurrencyElements.class); // Initial elements
	}

	// Scenario 1
	
	@Then("^Test all rates$")
	public void Test_all_rates() throws Throwable {
		currencyHaveUnitsParent0 = wait
				.until(ExpectedConditions.elementToBeClickable(currencyElements.getCurrencyHaveUnitsParents().get(0)));
		currencyHaveUnitsParent0.click(); // Load CurrencyHaveUnits items
		int i = 0;
		for (WebElement getCurrencyHaveUnit : currencyElements.getCurrencyHaveUnits()) {
			currencyElements.setCurrencyHaveUnit(getCurrencyHaveUnit);			
			currencyElements.waitCurrencyHaveUnit(currencyElements.getSelectValue(getCurrencyHaveUnit));
			// Test the amounts against the rates
			double currencyHave = currencyElements.getCurrencyHaveAmount();
			double rateHave = currencyElements.getNoncashCurrencyHaveRate();
			double currencyWant = currencyElements.getCurrencyWantAmount();
			double rateWant = currencyElements.getNoncashCurrencyWantRate();
			System.out.print("A-Testing currencyHave[" + currencyHave + "] * rateHave[" + rateHave + "] ("
					+ round(currencyHave * rateHave, 2) + ") == currencyWant[" + currencyWant + "]");
			if (Math.abs(currencyHave * rateHave - currencyWant) > 0.9)
				throw new PendingException("currencyHave * rateHave <> currencyWant");
			System.out.print(" Success\n");
			System.out.print("B-Testing currencyWant[" + currencyWant + "] * rateWant[" + rateWant + "] ("
					+ round(currencyWant * rateWant, 2) + ") == currencyHave[" + currencyHave + "]");
			if (Math.abs(currencyWant * rateWant - currencyHave) > 0.9)
				throw new PendingException("currencyWant * rateWant <> currencyHave");
			System.out.print(" Success\n");
			currencyHaveUnitsParent0.click();
			if (++i == 3) break;
		}
		currencyHaveUnitsParent0.click();
	}
	
	// Scenario 2

	@Given("^Page is loaded$")
	public void page_is_loaded() throws Throwable {
		currencyElements.waitCurrencyHaveUnitsParents();
		currencyHaveUnitsParentHave = wait
				.until(ExpectedConditions.elementToBeClickable(currencyElements.getCurrencyHaveUnitsParents().get(0)));
		wait.until(ExpectedConditions
				.not(ExpectedConditions.textToBePresentInElementValue(currencyElements.currencyWantAmount, "$100.00")));
	}
	
	@When("^User enters \"([^\"]*)\" (\\d+) \"([^\"]*)\" (\\d+.\\d+)$")	
	public void user_enters_currencyHave_amountHave_currencyWant_amountWant(String currencyHave, double amountHave, String currencyWant, double amountWant) throws Throwable {
		// for (CurrencyExchange currencyExchange : readMySQL()) {
		//	for (CurrencyExchange currencyExchange : readExcel("C:\\Users\\hhomayounfar\\Desktop\\testDB.xlsx")) {
		CurrencyExchange currencyExchange = new CurrencyExchange(currencyHave, amountHave, currencyWant, amountWant);	
		double currencyWantAmountOld = currencyElements.getCurrencyWantAmount();
		currencyElements.setCurrencyHaveAmount(currencyExchange.getCurrencyHave()); // Set amount have
		currencyHaveUnitsParentHave.click(); // Open menu unit-have
		currencyElements.waitNotCurrencyWantAmount(currencyWantAmountOld); // wait till amount-want is updated
		//
		String currencyHaveUnitOld = currencyElements.getCurrencyHaveUnit();
		currencyWantAmountOld = currencyElements.getCurrencyWantAmount();
		WebElement unitHave = currencyElements.findCurrencyHaveUnit(currencyExchange.getUnitHave());
		currencyElements.setCurrencyHaveUnit(unitHave); // Set unit have
		if (!currencyHaveUnitOld.equals(currencyElements.getCurrencyHaveUnit()))
			currencyElements.waitNotCurrencyWantAmount(currencyWantAmountOld); // wait till amount-want is updated
		//
		String currencyWantUnitOld = currencyElements.getCurrencyWantUnit();
		currencyWantAmountOld = currencyElements.getCurrencyWantAmount();
		currencyElements.waitCurrencyHaveUnitsParents();
		//
		currencyElements.getCurrencyHaveUnitsParents().get(1).click(); // Open menu Unit want
		WebElement unitWant = currencyElements.findCurrencyHaveUnit(currencyExchange.getUnitWant());
		currencyElements.setCurrencyHaveUnit(unitWant); // Set unit-want
		if (!currencyWantUnitOld.equals(currencyElements.getCurrencyWantUnit()))
			currencyElements.waitNotCurrencyWantAmount(currencyWantAmountOld); // wait till amount-want is updated
		System.out.print("Testing: " + currencyExchange.getCurrencyHave() + " " + currencyExchange.getUnitHave()
				+ " == " + currencyExchange.getCurrencyWant() + " " + currencyExchange.getUnitWant());
		if (currencyExchange.getCurrencyWant() - currencyElements.getCurrencyWantAmount() > 1.5)
			throw new PendingException("currencyWant[" + currencyExchange.getCurrencyWant() + "] is not " + currencyElements.getCurrencyWantAmount());
		System.out.println(" Ok");
		driver.quit();
	}
}
