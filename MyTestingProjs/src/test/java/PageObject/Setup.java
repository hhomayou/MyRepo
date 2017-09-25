package PageObject;

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
import java.util.concurrent.TimeUnit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import TestSteps.CurrencyExchange;

public abstract class Setup {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	// private final static Logger LOGGER = Logger.getLogger(Setup.class.getName());

	public static WebDriver beforeClassMethod(String pageURI) throws IOException, ClassNotFoundException, SQLException {
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
		driver.get(pageURI);
		wait = new WebDriverWait(driver, 4);
		System.out.println("Page: " + driver.getTitle());
		return driver;
	}

	// public static void afterClassMethod() {
	// driver.quit();
	// }

	//
	// Useful functions
	//
	public double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();
		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		// System.out.println("round = " + ((double) tmp / factor));
		return (double) tmp / factor;
	}

	public static void waitTillValue(WebElement webElement, String value) {
		wait.until(ExpectedConditions.textToBePresentInElementValue(webElement, value));
	}

	public static void waitTillValueNot(WebElement webElement, String value) {
		wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElementValue(webElement, value)));
	}

	public static WebElement waitTillReady(String xpathString) throws Exception {
		return waitTillReady(xpathString, null);
	}

	public static WebElement waitTillReady(String xpathString, String newValue) throws Exception {
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		String retry = "";
		do {
			if (System.currentTimeMillis() - startTime > 6000) {
				System.out.println("Failed !");
				throw new Exception("Timeout: element is not ready !");
			}
			try {
				element = Setup.driver.findElement(By.xpath(xpathString));
				element.getText();
				// if(newValue != null)
				// element.sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);
				// if(newValue != null && newValue.equals("click()"))
				// wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				if (retry != "")
					System.out.println("Success");
				return element; // It's ready
			} catch (StaleElementReferenceException e) {
				retry = "retry";
				System.out.print("Element not ready...retrying....");
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				retry = "retry";
				System.out.print("Element not found yet...retrying....");
				Thread.sleep(1000);
			}
		} while (true);
	}

	public static WebElement waitTillClicable(String xpathString) throws Exception {
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		String retry = "";
		do {
			if (System.currentTimeMillis() - startTime > 6000) {
				System.out.println("Failed !");
				throw new Exception("2-Timeout: element is not ready !");
			}
			try {
				element = Setup.driver.findElement(By.xpath(xpathString));
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				if (retry != "")
					System.out.println("Success");
				return element; // It's ready
			} catch (StaleElementReferenceException e) {
				retry = "retry";
				System.out.print("2-Element not ready...retrying....");
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				retry = "retry";
				System.out.print("2-Element not found yet...retrying....\nxpath=" + xpathString + "<");
				Thread.sleep(1000);
			}
		} while (true);
	}

	public static WebElement waitTillSendable(String xpathString, String newValue) throws Exception {
		long startTime = System.currentTimeMillis();
		WebElement element = null;
		String retry = "";
		do {
			if (System.currentTimeMillis() - startTime > 6000) {
				System.out.println("Failed !");
				throw new Exception("3-Timeout: element is not ready !");
			}
			try {
				element = Setup.driver.findElement(By.xpath(xpathString));
				element.sendKeys(Keys.chord(Keys.CONTROL, "a"), newValue);
				if (retry != "")
					System.out.println("Success");
				return element; // It's ready
			} catch (StaleElementReferenceException e) {
				retry = "retry";
				System.out.print("3-Element not ready...retrying....");
				Thread.sleep(1000);
			} catch (NoSuchElementException e) {
				retry = "retry";
				System.out.print("3-Element not found yet...retrying....");
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
