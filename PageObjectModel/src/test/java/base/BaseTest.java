package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\Testdata.xlsx");

	// @BeforeSuite
	public void setUp(String browser) {

		getProperties();
		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("dom.webnotifications.enabled", false);

			
				driver = initializeRemoteDriver(options);
			

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
			driver = initializeRemoteDriver(options);

		} else if (browser.equals("edge")) {

			EdgeOptions options = new EdgeOptions();

			HashMap<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_setting_values.notifications", 2); // 1 = Allow, 2 = Block

			options.setExperimentalOption("prefs", prefs);

			// Launch browser in Docker container
			driver = initializeRemoteDriver(options);

		}
		driver.get(Config.getProperty("testurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts()
				.implicitlyWait(Duration.ofSeconds(Integer.parseInt(Config.getProperty("implicitWait"))));
	}

	public void getProperties() {

		try {
			fis = new FileInputStream(
					"D:\\Selenium Projects\\PageObjectModel\\src\\test\\resources\\properties\\Config.properties");

			Config.load(fis);

			fis = new FileInputStream(
					"D:\\Selenium Projects\\PageObjectModel\\src\\test\\resources\\properties\\OR.properties");

			OR.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	String remoteURL = "http://172.22.224.1:4444";
	public WebDriver initializeRemoteDriver(Capabilities options) {
       
        try {
            return new RemoteWebDriver(new URL(remoteURL), options);
        } catch (MalformedURLException e) {
            log.error("Invalid Remote WebDriver URL: " + remoteURL, e);
            throw new RuntimeException("Failed to initialize Remote WebDriver", e);
        }
    }


	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
		log.info("Browser Closed!!!");
	}
}
