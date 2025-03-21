package basepage;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CarBase {

	public WebDriver driver;

	public CarBase(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//It is common code for all Brand cars that's why we need to add it in CarBase.java
		@FindBy(xpath="//h1[@class='o-eqqVmt o-cJrNdO o-Hyyko o-bPYcRG o-dOKno o-bNCMFw']")
		public WebElement carTitle;
		public String getCarTitle() {
			
			return carTitle.getText();
			
		}
		
		//It is common code for all Brand cars to find car name with car prize
		@FindBy(xpath="//h3[@class='o-jjpuv o-cVMLxW o-mHabQ o-fzpibK']")
		public List<WebElement> carName;
		
		@FindBy(xpath="//span[@class='o-cJrNdO o-byFsZJ o-bkmzIL o-bVSleT ']")
		public List<WebElement> carPrice;
		public void getCarNameAndCarPrize() {
			for(int i=0;i<carPrice.size(); i++) {
				System.out.println("Car Name is: "+carName.get(i).getText()+" "+"Car price is: "+carPrice.get(i).getText());
			}
		}
		
		//It is common code for all city cars to find car name with car prize
				@FindBy(xpath="//div/div/li/div/div[2]/a/h3")
				public List<WebElement> usedCarName;
				
				@FindBy(xpath="//span[@class='o-Hyyko o-cJrNdO o-eqqVmt o-fzpibr']")
				public List<WebElement> usedCarPrice;
				public void getCarNameAndCarPrizeForUsedCars() {
					for(int i=0;i<usedCarPrice.size(); i++) {
						System.out.println("Car Name is: "+usedCarName.get(i).getText()+" "+"Car price is: "+usedCarPrice.get(i).getText());
					}
				}
}
