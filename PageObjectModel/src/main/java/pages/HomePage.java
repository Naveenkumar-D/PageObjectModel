package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	// PageFactory
	@FindBy(xpath = "//*[@id=\"root\"]/div[2]/header/div/nav/ul/li[1]/div")
	public WebElement newCarMenu;

	@FindBy(xpath = "//div[contains(text(),'Find New Cars')]")
	public WebElement findNewCars;

	@FindBy(xpath = "//*[@id=\"root\"]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/div/div[2]/div/div/div[1]/div/input")
	public WebElement searchNewCar;
	
	@FindBy(xpath = "//*[@id=\"root\"]/div[2]/header/div/nav/ul/li[2]/div")
	public WebElement usedCarMenu;
	
	@FindBy(xpath = "//div[contains(text(),'Explore Used Cars')]")
	public WebElement exploreUsedCar;

	public NewCarPage findNewCar() {

		// WebElement newCarMenu1=
		// driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/header/div/nav/ul/li[1]/div"));
		Actions action = new Actions(driver);
		action.moveToElement(newCarMenu).perform();
		findNewCars.click();
		// driver.findElement(By.xpath("//div[contains(text(),'Find New
		// Cars')]")).click();
		return new NewCarPage(driver);
	}
	
	public UsedCarPage exploreUsedCar() {

	
		Actions action = new Actions(driver);
		action.moveToElement(usedCarMenu).perform();
		exploreUsedCar.click();
		// driver.findElement(By.xpath("//div[contains(text(),'Find New
		// Cars')]")).click();
		return new UsedCarPage(driver);
	}

	public void searchNewCar(String carName) {
		searchNewCar.sendKeys(carName);

	}

	public void searchUsedCar() {

	}
}
