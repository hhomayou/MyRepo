package TestSteps;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import PageObject.Setup;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CurrencyTest_Steps extends Setup {

	WebElement currencyHaveUnitsParent0;
	WebElement currencyHaveUnitsParentHave;

	@Given("^Initial test$")
	public void initialTest() throws Throwable {
		BeforeClassMethod();
	}

	// Scenario 1 a
	
	@Then("^Test all rates$")
	public void Test_all_rates() throws Throwable {
		currencyHaveUnitsParent0 = wait
				.until(ExpectedConditions.elementToBeClickable(homePage.getCurrencyHaveUnitsParents().get(0)));
		currencyHaveUnitsParent0.click(); // Load CurrencyHaveUnits items
		int i = 0;
		for (WebElement getCurrencyHaveUnit : homePage.getCurrencyHaveUnits()) {
			homePage.setCurrencyHaveUnit(getCurrencyHaveUnit);
			homePage.waitTillValue(homePage.currencyHaveUnitsRO, homePage.getCurrencyHaveUnit());
			// Test the amounts against the rates
			double currencyHave = homePage.getCurrencyHaveAmount();
			double rateHave = homePage.getNoncashCurrencyHaveRate();
			double currencyWant = homePage.getCurrencyWantAmount();
			double rateWant = homePage.getNoncashCurrencyWantRate();
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
		homePage.waitCurrencyHaveUnitsParents();
		currencyHaveUnitsParentHave = wait
				.until(ExpectedConditions.elementToBeClickable(homePage.getCurrencyHaveUnitsParents().get(0)));
		wait.until(ExpectedConditions
				.not(ExpectedConditions.textToBePresentInElementValue(homePage.currencyWantAmount, "$100.00")));
	}
	
	@When("^User enters \"([^\"]*)\" (\\d+) \"([^\"]*)\" (\\d+.\\d+)$")	
	public void user_enters_currencyHave_amountHave_currencyWant_amountWant(String currencyHave, double amountHave, String currencyWant, double amountWant) throws Throwable {
		// for (CurrencyExchange currencyExchange : ReadMySQL()) {
		//	for (CurrencyExchange currencyExchange : ReadExcel("C:\\Users\\hhomayounfar\\Desktop\\testDB.xlsx")) {
		CurrencyExchange currencyExchange = new CurrencyExchange(currencyHave, amountHave, currencyWant, amountWant);	
		double currencyWantAmountOld = homePage.getCurrencyWantAmount();
		homePage.setCurrencyHaveAmount(currencyExchange.getCurrencyHave()); // Set amount have
		currencyHaveUnitsParentHave.click(); // Open menu unit-have
		homePage.waitNotCurrencyWantAmount(currencyWantAmountOld); // wait till amount-want is updated
		//
			String currencyHaveUnitOld = homePage.getCurrencyHaveUnit();
			currencyWantAmountOld = homePage.getCurrencyWantAmount();
			WebElement unitHave = homePage.findCurrencyHaveUnit(currencyExchange.getUnitHave());
			homePage.setCurrencyHaveUnit(unitHave); // Set unit have
			if (!currencyHaveUnitOld.equals(homePage.getCurrencyHaveUnit()))
				homePage.waitNotCurrencyWantAmount(currencyWantAmountOld); // wait till amount-want is updated
			//
			String currencyWantUnitOld = homePage.getCurrencyWantUnit();
			currencyWantAmountOld = homePage.getCurrencyWantAmount();
			homePage.waitCurrencyHaveUnitsParents();
			//
			homePage.getCurrencyHaveUnitsParents().get(1).click(); // Open menu Unit want
			WebElement unitWant = homePage.findCurrencyHaveUnit(currencyExchange.getUnitWant());
			homePage.setCurrencyHaveUnit(unitWant); // Set unit-want
			if (!currencyWantUnitOld.equals(homePage.getCurrencyWantUnit()))
				homePage.waitNotCurrencyWantAmount(currencyWantAmountOld); // wait till amount-want is updated
			System.out.print("Testing: " + currencyExchange.getCurrencyHave() + " " + currencyExchange.getUnitHave()
					+ " == " + currencyExchange.getCurrencyWant() + " " + currencyExchange.getUnitWant());
			if (currencyExchange.getCurrencyWant() - homePage.getCurrencyWantAmount() > 1.5)
				throw new PendingException("currencyWant[" + currencyExchange.getCurrencyWant() + "] is not " + homePage.getCurrencyWantAmount());
			System.out.println(" Ok");
	}

	@Then("^End test$")
	public void EndTest() throws Throwable {
		afterClassMethod();
	}
}
