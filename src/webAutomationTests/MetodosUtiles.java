package webAutomationTests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import commons.configuration;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

class MetodosUtiles {
	
	private Map<String, Object> vars;
	JavascriptExecutor js;
	WebDriver driver = null;
	private WebDriverWait wait = null;

	@BeforeEach
	void inicializarDriver() {
		driver = configuration.createChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] {"enable-automation"});
		WebDriver driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get(configuration.TRAVELOCITY_URL);
		js = (JavascriptExecutor) driver;
	    vars = new HashMap<String, Object>();
	
	}
	@AfterEach
	void cerrarNavegador() {
		driver.quit();
	}
	
	public String waitForWindow(int timeout) {
	    try {
	      Thread.sleep(timeout);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	    Set<String> whNow = driver.getWindowHandles();
	    Set<String> whThen = (Set<String>) vars.get("window_handles");
	    if (whNow.size() > whThen.size()) {
	      whNow.removeAll(whThen);
	    }
	    return whNow.iterator().next();
	  }
	
	public void skipBotDetection() {
		driver.get("https://www.travelocity.com/");
	    driver.manage().window().setSize(new Dimension(1366, 728));
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#home_children_button")));
	    driver.findElement(By.cssSelector("#home_children_button")).click(); 
	    //driver.findElement(By.xpath("//*[@id=\"home_children_button\"]")).click();
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("ChallengeSelectableOverlay__StyledElement-sc-6lu34v-0 eJOgpi")));
	    driver.findElement(By.className("ChallengeSelectableOverlay__StyledElement-sc-6lu34v-0 eJOgpi")).click(); // Click en el pingüino
	    //driver.findElement(By.xpath("//*[@id=\"image6\"]/a")).click(); // Click en el pingüino
	    //driver.findElement(By.cssSelector("#image6 > a")).click(); // Click en el pingüino
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Flights")));

	}

}
