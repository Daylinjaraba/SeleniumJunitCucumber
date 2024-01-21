package utils;

import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class initialize {
	private static WebDriver driver;
	private static final Duration EXPLICIT_TIMEOUT = Duration.ofSeconds(20);


	private initialize() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
	}

	public static void openPage(String url) {
		getDriver().get(url);
		waitForPageLoad();
		
	}

	public static WebDriver getDriver() {
		if(driver == null) {
			initializeDriver();
		}
		return driver;
	}
	public static void initializeDriver() {
		if(driver == null) {
			new initialize();
		}
	}
	public static void tearDown() {
		if(driver != null) {
			driver.quit();
//			driver.close();
		}
		driver=null;
	}
	
	public static WebElement waitForElementPresence(WebDriver driver, WebElement element, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
	
	public static void waitForPageLoad() {
        ExpectedCondition<Boolean> pageLoadCondition = webDriver ->
                ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete");

        WebDriverWait wait = new WebDriverWait(driver, EXPLICIT_TIMEOUT);
        wait.until(pageLoadCondition);
    }
	
	public static void attachScreenshot(Scenario scenario) {
        byte[] screenshot = ((TakesScreenshot) initialize.getDriver()).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", scenario.getName());
    }
	

}
