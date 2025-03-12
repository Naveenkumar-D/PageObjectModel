package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.ExcelReader;

public class BaseTest {

	public WebDriver driver;

	public Properties OR = new Properties();
	public Properties Config = new Properties();
	public FileInputStream fis;
	public Logger log = LogManager.getLogger();
	public ExcelReader excel = new ExcelReader(
			"D:\\Selenium Projects\\PageObjectModel\\src\\test\\resources\\excel\\Testdata.xlsx");

	// @BeforeSuite
	public void setUp(String browser) {
		if (driver == null) {
			try {
				fis = new FileInputStream(
						"D:\\Selenium Projects\\PageObjectModel\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(
						"D:\\Selenium Projects\\PageObjectModel\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(new FirefoxProfile());
			options.addPreference("dom.webnotifications.enabled", false);

			driver = new FirefoxDriver(options);

		} else if (browser.equals("chrome")) {
			// Create a map to store preferences
			Map<String, Object> prefs = new HashMap<String, Object>();

			// add key and value to map as follow to switch off browser notification
			// Pass the argument 1 to allow and 2 to block
			prefs.put("profile.default_content_setting_values.notifications", 2);

			// Create an instance of ChromeOptions
			ChromeOptions options = new ChromeOptions();

			// set ExperimentalOption - prefs
			options.setExperimentalOption("prefs", prefs);

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
		}
		if (browser.equals("ie")) {

			driver = new InternetExplorerDriver();
		}
		driver.get(Config.getProperty("testurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(Config.getProperty("implicitWait"))));
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.info("Browser Closed!!!");
	}
}
