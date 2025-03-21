package testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BaseTest;
import basepage.BasePage;
import pages.HomePage;
import pages.NewCarPage;
import utilities.TestUtil;

public class FindNewCarTest extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp1")
	public void findNewCars(Hashtable<String, String> data) {

		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test as the Runmode is No");
		}

		setUp(data.get("browser"));
		HomePage home = new HomePage(driver);
		NewCarPage carpage = home.findNewCar();

		if (data.get("carbrand").equals("Toyota")) {
			carpage.goToToyota();
		}
		else if (data.get("carbrand").equals("BMW")) {
			carpage.goToBMW();
		}
		else if (data.get("carbrand").equals("Kia")) {
			carpage.goToKia();
		}
		else if (data.get("carbrand").equals("MG")) {
			carpage.goToMG();
		}
		else if (data.get("carbrand").equals("Mahindra")) {
			carpage.goToMahindra();
		}
		else if (data.get("carbrand").equals("Tata")) {
			carpage.goToTata();
		}
		else if (data.get("carbrand").equals("Hyundai")) {
			carpage.goToHyundai();
		}
		else if (data.get("carbrand").equals("Honda")) {
			carpage.goToHonda();
		}
		
		String carTitle = BasePage.carBase.getCarTitle();
		System.out.println("Car title is: "+carTitle);

		Assert.assertEquals(carTitle, data.get("cartitle"));
		

	}
}
