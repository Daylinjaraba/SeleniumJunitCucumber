package definitions;

import org.junit.Assert;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomeGooglePage;
import pages.ResultsPage;
import utils.initialize;

public class GoogleSearchSteps {
	private HomeGooglePage homeGooglePage;
	private ResultsPage resultsPage;
	

    public GoogleSearchSteps() {
    	homeGooglePage = new HomeGooglePage();
    	resultsPage = new ResultsPage();
    }
	@Before
	public void initializeDriver() {
		initialize.initializeDriver();
	}
	
	@Given("I visit the page: {string}")
	public void i_visit_the_page(String url) {
		initialize.openPage(url);
		
	}

	@When("I search the following phrase: {string}")
	public void i_search_the_following_phrase(String phrase) {
		homeGooglePage.search(phrase);
	}

	@Then("I validate the outcomes contain the phrase: {string}")
	public void i_validate_the_outcomes_contain_the_phrase(String phrase) {
	    Assert.assertTrue(resultsPage.validateResult(phrase));
	}
	
	@After
	public void close(Scenario scenario) {
		if(scenario.isFailed()) {
			initialize.attachScreenshot(scenario);
		}
		initialize.tearDown();
	}
	
}
