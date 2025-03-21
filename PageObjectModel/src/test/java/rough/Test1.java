package rough;

import java.time.Duration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import basepage.BasePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.HomePage;
import pages.NewCarPage;
import utilities.TestUtil;

public class Test1 {
	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp1")
	public void findNewCars(Hashtable<String, String> data) {

		

	}
}

