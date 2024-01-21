package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.initialize;

public class HomeGooglePage {
	
	@FindBy(name="q")
	private WebElement searchBox;
	
	public HomeGooglePage() {
		PageFactory.initElements(initialize.getDriver(), this);
	}
	
	public void search(String phrase) {
//		driver.findElement(By.name("q")).sendKeys(phrase);
//		driver.findElement(By.name("q")).submit();
		searchBox.sendKeys(phrase);
		searchBox.submit();
		initialize.waitForPageLoad();
	}
	

	
}
