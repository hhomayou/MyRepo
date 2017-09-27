package automationFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import PageObject.Setup;
import TestObjects.CurrencyExchange;

public class TestUtilities {
	//
	// Useful functions
	//
	public static String getXPath(WebElement element) { 
        String jscript = "function getPathTo(node) {" + 
                "  var stack = [];" + 
                "  while(node.parentNode !== null) {" + 
                "    stack.unshift(node.tagName);" + 
                "    node = node.parentNode;" + 
                "  }" + 
                "  return stack.join('/');" + 
                "}" + 
                "return getPathTo(arguments[0]);"; 
        return (String) ((RemoteWebDriver) Setup.driver).executeScript(jscript, element); 
    } 
	
	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		// System.out.println("round = " + ((double) tmp / factor));
		return (double) tmp / factor;
	}

	public static void waitTillValue(WebElement webElement, String value) {
		Setup.wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, value));
	}

	public static void waitTillValueNot(WebElement webElement, String value) {
		Setup.wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(webElement, value)));
	}

	public static WebElement waitTillReady(String xpath) throws Exception {
		return waitTillReady(xpath, null);
	}

	public static WebElement waitTillReady(String xpath, String newValue) throws Exception {
		WebElement element = null;
		Exception exception = null;
		long startTime = System.currentTimeMillis();
		do {
			if (System.currentTimeMillis() - startTime > 30000) { // 15 * 2000
				System.out.println("Failed !");
				throw exception;
			}
			try {
				By locator = By.xpath(xpath);
				Setup.wait.until(ExpectedConditions.presenceOfElementLocated(locator));
				element = Setup.driver.findElement(locator);
				element.getText();
				if(newValue != null) {
					if(newValue.equals("click()"))
						Setup.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
					else 
						element.sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);
				}
				if (exception != null)
					System.out.println("Success");
				return element; // It's ready
			} catch (Exception e) {
				exception = e;
				System.out.print("[" + e.getMessage() + "]...retrying....\n\npagesource=" + Setup.driver.getPageSource());
				Thread.sleep(1000);
			}
		} while (true);
	}

	// Reading external tables
	public static List<CurrencyExchange> readExcel(String fullFilePath) throws IOException {
		String excelFilePath = fullFilePath;
		System.out.println("\nReading Excel file: " + fullFilePath);
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		List<CurrencyExchange> currencyExchanges = new ArrayList<CurrencyExchange>();
		// Skip the header
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		Cell cell = cellIterator.next();
		cell.getStringCellValue();
		cell.getStringCellValue();
		cell.getStringCellValue();
		cell.getStringCellValue();
		cell.getStringCellValue();
		// Read row values
		while (iterator.hasNext()) {
			nextRow = iterator.next();
			cellIterator = nextRow.cellIterator();
			cell = cellIterator.next();
			// Read columns
			CurrencyExchange currencyExchange = new CurrencyExchange();
			cell.getNumericCellValue(); // UserId not needed now
			cell = cellIterator.next();
			currencyExchange.setUnitHave(cell.getStringCellValue());
			cell = cellIterator.next();
			currencyExchange.setCurrencyHave(cell.getNumericCellValue());
			cell = cellIterator.next();
			currencyExchange.setUnitWant(cell.getStringCellValue());
			cell = cellIterator.next();
			currencyExchange.setCurrencyWant(cell.getNumericCellValue());
			currencyExchanges.add(currencyExchange);
		}
		workbook.close();
		inputStream.close();
		return currencyExchanges;
	}

	static public List<CurrencyExchange> readMySQL() throws SQLException, ClassNotFoundException {
		String myUrl = "jdbc:mysql://localhost/new_schema";
		System.out.println("Reading MySQL host: " + myUrl);
		Class.forName("org.gjt.mm.mysql.Driver");
		Connection conn = DriverManager.getConnection(myUrl, "root", "0000");
		String query = "SELECT * FROM testcase1";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<CurrencyExchange> currencyExchanges = new ArrayList<CurrencyExchange>();
		while (rs.next()) {
			CurrencyExchange currencyExchange = new CurrencyExchange(rs.getString("CurrencyHave"),
					rs.getDouble("AmountHave"), rs.getString("CurrencyWant"), rs.getDouble("AmountWant"));
			currencyExchanges.add(currencyExchange);
		}
		return currencyExchanges;
	}
}
