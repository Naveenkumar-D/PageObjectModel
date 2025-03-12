package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import base.BasePage;

public class UsedCarPage extends BasePage{

	public UsedCarPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath="//span[@class='o-fzpihx o-eemiLE o-zmksK'][normalize-space()='Mumbai']")
	public WebElement mumbai;
	
	@FindBy(xpath="//span[@class='o-fzpihx o-eemiLE o-zmksK'][normalize-space()='Delhi']")
	public WebElement delhi;
	
	public MumbaiCityPage goToMumbai() {
		mumbai.click();
		return new MumbaiCityPage(driver);
	}

	public DelhiCityPage goToDelhi() {
		delhi.click();
		return new DelhiCityPage(driver);
	}
}
