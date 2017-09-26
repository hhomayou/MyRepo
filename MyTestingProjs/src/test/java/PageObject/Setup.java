package PageObject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Setup {
	public static WebDriver driver;
	public static WebDriverWait wait;
	// private final static Logger LOGGER = Logger.getLogger(Setup.class.getName());

	public static WebDriver beforeClassMethod(String pageURI) throws IOException, ClassNotFoundException, SQLException {
		System.setProperty("webdriver.chrome.driver", "Resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get(pageURI);
		wait = new WebDriverWait(driver, 15);
		System.out.println("Page: " + driver.getTitle());
		return driver;
	}

	// public static void afterClassMethod() {
	// driver.quit();
	// }
}
