package webAutomationTests;

import org.junit.Assert;
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

class Ejercicio3 {
	
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
	
	public void ejercicio3() {
		driver.get("https://www.travelocity.com/");
	    driver.manage().window().setSize(new Dimension(1366, 728));
	    driver.findElement(By.cssSelector(".all-l-padding-half")).click();
	    driver.findElement(By.cssSelector(".uitk-link:nth-child(2) > .uitk-text")).click();
	    driver.findElement(By.cssSelector(".has-no-placeholder > .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-destination")).sendKeys("Montevideo, Uruguay");
	    driver.findElement(By.cssSelector(".uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.id("d1-btn")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-flex > .uitk-button:nth-child(2)")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector(".uitk-layout-flex > .uitk-button:nth-child(2)"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector(".uitk-date-picker-month:nth-child(2) tr:nth-child(2) > .uitk-date-picker-day-number:nth-child(7) > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".dialog-done")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-grid-item > .uitk-button")).click();
	    
	    WebElement adBtn = driver.findElement(By.cssSelector("h2.faceoff-module-title"));	    
	    Assert.assertEquals(true, adBtn.isDisplayed());
	    
	    String message = driver.findElement(By.xpath("//*[@id=\"app-layer-base\"]/div[1]/main/div/div/div/section/div/div[2]/div/div[2]/section[2]/ol/li[4]/div/div[2]/div/h3")).getText();
	    Assert.assertTrue(message.contains("You could save 10% or more right now"));
	    
	    driver.close();
		
	}
	
	@Test
	public void test() {
		this.ejercicio3();
		System.out.println("La prueba fue exitosa");
	}

}
