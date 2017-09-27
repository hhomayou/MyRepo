package TestObjects;

public class CurrencyExchange {

	String unitHave;
	double currencyHave;
	String unitWant;
	double currencyWant; // Expected

	public CurrencyExchange() {
	}
	
	public CurrencyExchange(String unitHave, double currencyHave, String unitWant, double currencyWant) {
		this.unitHave = unitHave;
		this.currencyHave = currencyHave;
		this.unitWant = unitWant;
		this.currencyWant = currencyWant;
	}


	public String getUnitHave() {
		return unitHave;
	}
	public void setUnitHave(String unitHave) {
		this.unitHave = unitHave;
	}

	public double getCurrencyHave() {
		return currencyHave;
	}
	public void setCurrencyHave(double currencyHave) {
		this.currencyHave = currencyHave;
	}

	public String getUnitWant() {
		return unitWant;
	}
	public void setUnitWant(String unitWant) {
		this.unitWant = unitWant;
	}

	public double getCurrencyWant() {
		return currencyWant;
	}
	public void setCurrencyWant(double currencyWant) {
		this.currencyWant = currencyWant;
	}
}
