package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basepage.BasePage;

public class NewCarPage extends BasePage {

	public NewCarPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	@FindBy(xpath = "//div[normalize-space()='BMW']")
	public WebElement BMW;

	@FindBy(xpath="//div[normalize-space()='Tata']")
	public WebElement Tata;
	
	@FindBy(xpath="//div[normalize-space()='Kia']")
	public WebElement Kia;
	
	@FindBy(xpath="//div[normalize-space()='Toyota']")
	public WebElement Toyota;
	
	@FindBy(xpath="//div[normalize-space()='Honda']")
	public WebElement Honda;
	
	@FindBy(xpath="//div[normalize-space()='MG']")
	public WebElement MG;
	
	@FindBy(xpath="//div[normalize-space()='Mahindra']")
	public WebElement Mahindra;
	
	@FindBy(xpath="//div[normalize-space()='Hyundai']")
	public WebElement Hyundai;
	
	public BMWCarPage goToBMW() {
		BMW.click();
		return new BMWCarPage(driver);
	}

	public KiaCarPage goToKia() {
		Kia.click();
		return new KiaCarPage(driver);
	}

	public TataCarPage goToTata() {
		Tata.click();
		return new TataCarPage(driver);
	}

	public ToyotaCarPage goToToyota() {
		Toyota.click();
		return new ToyotaCarPage(driver);
	}
	
	public HondaCarPage goToHonda() {
		Honda.click();
		return new HondaCarPage(driver);
	}
	
	public MGCarPage goToMG() {
		MG.click();
		return new MGCarPage(driver);
	}
	
	public MahindraCarPage goToMahindra() {
		Mahindra.click();
		return new MahindraCarPage(driver);
	}
	
	public HyundaiCarPage goToHyundai() {
		Hyundai.click();
		return new HyundaiCarPage(driver);
	}
}
