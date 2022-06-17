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

import static org.junit.Assert.assertEquals;

import java.util.*;

class Ejercicio5 {
	
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
	
	public void ejercicio5() {
		driver.get("https://www.travelocity.com/");
	    driver.manage().window().setSize(new Dimension(1656, 895));
	    driver.findElement(By.cssSelector(".uitk-tab:nth-child(6) .uitk-tab-text")).click();
	    driver.findElement(By.id("cruise-destination")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("cruise-destination"));
	      dropdown.findElement(By.xpath("//option[. = 'Europe']")).click();
	    }
	    driver.findElement(By.cssSelector(".uitk-layout-grid-item > .uitk-button")).click();
	    
	    String goingToAssert = driver.findElement(By.xpath("//span[@id='destination-select']")).getText();
	    Assert.assertTrue(goingToAssert.contains("Europe"));
	    
	    driver.findElement(By.id("length-10-14-ember1076")).click();
	    {
	      WebElement element = driver.findElement(By.id("length-10-14-ember2860"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    
	    WebElement filterSelected = driver.findElement(By.cssSelector("[aria-labelledby='cruise-length-options length-10-14-ember2850-label']"));	    
	    Assert.assertEquals(true, filterSelected.isSelected());
	    
	    String results = driver.getPageSource();
	    Assert.assertTrue(results.contains("Up to "));
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    vars.put("window_handles", driver.getWindowHandles());
	    driver.findElement(By.id("selectSailingButton-AQoCcmMSAmFuGIDIiJiZMCAOKgNzdGgyAmdiOgNzdGhCAmdi-AN")).click();
	    vars.put("win4273", waitForWindow(2000));
	    driver.switchTo().window(vars.get("win4273").toString());
	}
	
	@Test
	public void test() {
		this.ejercicio5();
		System.out.println("La prueba fue exitosa");
	}

}