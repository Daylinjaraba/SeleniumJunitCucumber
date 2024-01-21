package pages;

import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.initialize;


public class ResultsPage {
	@FindBy(xpath = ".//h3[@class='LC20lb MBeuO DKV0Md']")
    public List<WebElement> results;
	
	public ResultsPage() {
		PageFactory.initElements(initialize.getDriver(), this);
	}
	
	public boolean validateResult(String phrase) {
		phrase=phrase.toLowerCase();
		boolean search=true;
		for(WebElement element:results) {
			if(!element.getText().toLowerCase().contains(phrase) && !element.getText().isEmpty()) {
				search=false;
				break;
			}		
		}
		return search;
	}
}
