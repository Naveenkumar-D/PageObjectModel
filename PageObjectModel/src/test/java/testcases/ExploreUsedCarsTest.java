package testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pages.HomePage;
import pages.UsedCarPage;
import utilities.TestUtil;

public class ExploreUsedCarsTest extends BaseTest {

	@Test(dataProviderClass = TestUtil.class, dataProvider = "dp1")
	public void exploreUsedCars(Hashtable<String, String> data) {
		if (!data.get("runmode").equals("Y")) {
			throw new SkipException("Skipping the test as the Runmode is No");
		}
		setUp(data.get("browser"));
		HomePage home= new HomePage(driver);
		UsedCarPage usedCar=home.exploreUsedCar();
		if (data.get("city").equals("Delhi")) {
			usedCar.goToDelhi();
		}
		else if (data.get("city").equals("Mumbai")) {
			usedCar.goToMumbai();
		}
		
		BasePage.carBase.getCarNameAndCarPrizeForUsedCars();
		
	}
}
