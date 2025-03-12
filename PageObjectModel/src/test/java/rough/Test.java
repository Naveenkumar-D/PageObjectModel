package rough;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.NewCarPage;

public class Test {
	public static void main(String[] args) {

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
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.carwale.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		HomePage home = new HomePage(driver);
		NewCarPage carpage = home.findNewCar();

	}

}
