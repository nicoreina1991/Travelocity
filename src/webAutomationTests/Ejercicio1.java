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
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.*;

class Ejercicio1 {
	
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

	public void ejercicio1() {
		driver.get("https://www.travelocity.com/");
	    driver.manage().window().setSize(new Dimension(1366, 728));
	    driver.findElement(By.cssSelector(".uitk-tab:nth-child(2) .uitk-tab-text")).click();
	    driver.findElement(By.cssSelector(".uitk-tab-button:nth-child(1) .uitk-tab-text")).click();
	    driver.findElement(By.cssSelector("#location-field-leg1-origin-menu .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-leg1-origin")).sendKeys("LAS");
	    driver.findElement(By.cssSelector(".uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.cssSelector(".has-no-placeholder > .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-leg1-destination")).sendKeys("LAX");
	    driver.findElement(By.cssSelector("#location-field-leg1-destination-menu .uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.cssSelector(".uitk-link-no-wrap > .uitk-icon")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-flex:nth-child(1) .uitk-layout-flex-item:nth-child(3) .uitk-icon")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-flex:nth-child(1) .uitk-layout-flex-item:nth-child(1) .uitk-icon")).click();
	    driver.findElement(By.cssSelector(".uitk-button-floating-full-width")).click();
	    driver.findElement(By.id("d1-btn")).click();
	    driver.findElement(By.cssSelector(".uitk-button-only-icon:nth-child(2)")).click();
	    driver.findElement(By.cssSelector(".uitk-date-picker-month:nth-child(2) tr:nth-child(2) > .uitk-date-picker-day-number:nth-child(5) > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".uitk-date-picker-month:nth-child(2) tr:nth-child(2) > .uitk-date-picker-day-number:nth-child(7) > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".dialog-done")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-grid-item > .uitk-button")).click();
	    
	    // Validación 1A - Existe un box que permite ordenar los resultados
	    WebElement sortBybtn = driver.findElement(By.cssSelector("#listings-sort"));
	    Assert.assertEquals(true, sortBybtn.isDisplayed());
	    
	    driver.findElement(By.id("listings-sort")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("listings-sort"));
	      dropdown.findElement(By.xpath("//option[. = 'Duration (Shortest)']")).click();
	    }
	    // Validación 1C - la duración de cada vuelo está en cada resultado. Se cuenta cuántos resultados hay, cuántos son NonStop y cuántos Stop.
	    List<WebElement> forms1 = driver.findElements(By.xpath("//*[@id=\"app-layer-base\"]/div[4]/div[3]/div[1]/section/main/ul"));
	    int resultsCount = forms1.size();  
	    List<WebElement> forms2 = driver.findElements(By.xpath("//*[@id=\"app-layer-base\"]/div[4]/div[3]/div[1]/section/main/ul[contains(text(),'Nonstop')]"));
	    int nonStopCount = forms2.size();    
	    List<WebElement> forms3 = driver.findElements(By.xpath("//*[@id=\"app-layer-base\"]/div[4]/div[3]/div[1]/section/main/ul[contains(text(),'stop')]"));
	    int stopCount = forms3.size();
	    int resultsDisplayed = nonStopCount + stopCount;
	    
	    // Compara la cantidad de resultados con la suma de NonStop y Stop (son los tipos de resultados a mostrar)
	    Assert.assertEquals(resultsCount, resultsDisplayed);
	    
	    js.executeScript("window.scrollTo(0,3042)");
	    driver.findElement(By.cssSelector("#AQrYAgrCAnY1LXNvcy0yN2Q3YmM2OTMwNmUwM2IxNTdlNjUzNWRiM2VhYmQ1OC0xMy0xLXN0LXY1LXNvcy03ZGVjMzJmMjY0NmUyMDQwMmZlYjg3NDg0ZjdiYTgyOC0wLTF-Mi5TfkFRb0VDSUh4QkJJSENOUUVFQWNZR3lnQ1dBSndBSEFBfkFRb3FDaWdJd1lJQkVnUXlNemc0R0l1UUFTQ0x1QUVvdkxTVUFqQ0J0WlFDT0ZOQUFGZ0JhZ1JOUVVsT0Vnb0lBUkFCR0FFcUFrRkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb25DaVVJd213U0JERXdPREFZaTdnQklJdVFBU2pXeTVRQ01KM01sQUk0VEVBQVdBRnFBa1JPRWdvSUFSQUJHQUVxQWtJMkdBRWlCQWdCRUFFb0FpZ0RLQVF3QVERZmZmZmbmZUAiAQEqBRIDCgExEj8KFgoKMjAyMi0wOC0xMRIDTEFTGgNMQVgKFgoKMjAyMi0wOC0xMxIDTEFYGgNMQVMSBxIFQ09BQ0gaAhABIAIaCggBEgYaACICCAI\\= .uitk-card-link")).click();
	    driver.close();
	    driver.findElement(By.cssSelector(".uitk-tab:nth-child(2) .uitk-tab-text")).click();
	    driver.findElement(By.cssSelector(".uitk-tab-button:nth-child(1) .uitk-tab-text")).click();
	    driver.findElement(By.cssSelector("#location-field-leg1-origin-menu .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-leg1-origin")).sendKeys("LAS");
	    driver.findElement(By.cssSelector(".uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.cssSelector(".has-no-placeholder > .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-leg1-destination")).sendKeys("LAX");
	    driver.findElement(By.cssSelector("#location-field-leg1-destination-menu .uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.id("d1-btn")).click();
	    driver.findElement(By.cssSelector(".uitk-date-picker-month:nth-child(2) tr:nth-child(3) > .uitk-date-picker-day-number:nth-child(5) > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".end > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".dialog-done")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-grid-item > .uitk-button")).click();
	    driver.findElement(By.id("listings-sort")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("listings-sort"));
	      dropdown.findElement(By.xpath("//option[. = 'Duration (Shortest)']")).click();
	    }
	    driver.findElement(By.cssSelector("#AQrYAgrCAnY1LXNvcy02NDMyYWY4OWI4YjNmNThiNjY3NzNmMDg3MWVkYWRjYy0xNC0xLXN0LXY1LXNvcy0yNGU3MGMwYjA5OGMzODc5NGUwZWFkNWFmZjY3Yzc0YS0wLTF-Mi5TfkFRb0VDSUh4QkJJSENOUUVFQWNZR3lnQ1dBSndBSEFBfkFRb3FDaWdJd1lJQkVnUXlNemc0R0l1UUFTQ0x1QUVvd2ZtUkFqQ0ctcEVDT0ZOQUFGZ0JhZ1JOUVVsT0Vnb0lBUkFCR0FFcUFrRkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb25DaVVJd213U0JERXdPREFZaTdnQklJdVFBU2kyaFpJQ01QMkZrZ0k0VEVBQVdBRnFBa1JPRWdvSUFSQUJHQUVxQWtJMkdBRWlCQWdCRUFFb0FpZ0RLQVF3QVERZmZmZmbGZ0AiAQEqBRIDCgExEj8KFgoKMjAyMi0wNy0xNBIDTEFTGgNMQVgKFgoKMjAyMi0wNy0xNRIDTEFYGgNMQVMSBxIFQ09BQ0gaAhABIAIaCggBEgYaACICCAI\\= .uitk-card-link")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-flex:nth-child(1) > .uitk-card .uitk-button")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("#AQrjAgrDAnY1LXNvcy02NDMyYWY4OWI4YjNmNThiNjY3NzNmMDg3MWVkYWRjYy0xNC0xLXN0LXY1LXNvcy01ODQ0YzcwZmQ1ZDIxMDY1NDI0ODkxNjEzMjI4MDQwZC03LTF-Mi5TfkFRb0VDSUh4QkJJSENOUUVFQWNZR3lnQ1dBSndBSEFBfkFRb3FDaWdJd1lJQkVnUXlNemc0R0l1UUFTQ0x1QUVvd2ZtUkFqQ0ctcEVDT0ZOQUFGZ0JhZ1JOUVVsT0Vnb0lBUkFCR0FFcUFrRkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb29DaVlJd2FZQkVnUXpNek00R0l1NEFTQ0xrQUVvaFlpU0FqRFFpSklDT0ZoQUFGZ0JhZ0pUVmhJS0NBRVFBUmdCS2dKQlV4Z0JJZ1FJQVJBQktBSW9BeWdFTUFFEaRwPQrXo21AIgIBAioFEgMKATEqBwgBEgMKATESPwoWCgoyMDIyLTA3LTE0EgNMQVMaA0xBWAoWCgoyMDIyLTA3LTE1EgNMQVgaA0xBUxIHEgVDT0FDSBoCEAEgAhoKCAESBhoAIgIIAiAB .uitk-card-link"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.cssSelector("#AQriAgrCAnY1LXNvcy02NDMyYWY4OWI4YjNmNThiNjY3NzNmMDg3MWVkYWRjYy0xNC0xLXN0LXY1LXNvcy0yNGU3MGMwYjA5OGMzODc5NGUwZWFkNWFmZjY3Yzc0YS0wLTF-Mi5TfkFRb0VDSUh4QkJJSENOUUVFQWNZR3lnQ1dBSndBSEFBfkFRb3FDaWdJd1lJQkVnUXlNemc0R0l1UUFTQ0x1QUVvd2ZtUkFqQ0ctcEVDT0ZOQUFGZ0JhZ1JOUVVsT0Vnb0lBUkFCR0FFcUFrRkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb25DaVVJd213U0JERXdPREFZaTdnQklJdVFBU2kyaFpJQ01QMkZrZ0k0VEVBQVdBRnFBa1JPRWdvSUFSQUJHQUVxQWtJMkdBRWlCQWdCRUFFb0FpZ0RLQVF3QVERZmZmZmbGZ0AiAgECKgUSAwoBMSoHCAESAwoBMRI_ChYKCjIwMjItMDctMTQSA0xBUxoDTEFYChYKCjIwMjItMDctMTUSA0xBWBoDTEFTEgcSBUNPQUNIGgIQASACGgoIARIGGgAiAggCIAE\\= .uitk-card-link")).click();
	    vars.put("window_handles", driver.getWindowHandles());
	    driver.findElement(By.cssSelector(".uitk-layout-flex:nth-child(1) > .uitk-card .uitk-button")).click();
	   
	    // Validación 6A - Total price is present
	    WebElement totalPrice = driver.findElement(By.cssSelector("[data-stid='price-summary-card'] .uitk-table-react-cell-textalign-right > .uitk-heading"));
	    Assert.assertEquals(true, totalPrice.isDisplayed());
	    
	    // Validación 6B - Departure and return information is present
	    WebElement departureInfo = driver.findElement(By.xpath("//*[@id=\"app-layer-base\"]/div/div/div[4]/div/main/div/section[2]/div/div[1]/div[1]"));
	    Assert.assertEquals(true, departureInfo.isDisplayed());
	    
	    // Validación 6C - Price guarantee text is present	    
	    String priceGuarantee = driver.findElement(By.xpath("//*[@id=\\\"app-layer-base\\\"]/div/div/div[4]/div/main/div/section[2]/div/div[1]/div[1]/div[2]/div/div[2]")).getText();
	    Assert.assertTrue(priceGuarantee.contains("Price summary"));
	    
	    vars.put("win4935", waitForWindow(2000));
	    vars.put("root", driver.getWindowHandle());
	    driver.switchTo().window(vars.get("win4935").toString());
	    driver.findElement(By.cssSelector(".uitk-button > span")).click();
	    
	    // Validación 8 - 5 validaciones de elementos presentes
	    
	    WebElement val1 = driver.findElement(By.cssSelector("h2.faceoff-module-title"));
	    WebElement val2 = driver.findElement(By.cssSelector(".toggle-passenger"));
	    WebElement val3 = driver.findElement(By.cssSelector(".alpha3CountryCode"));
	    WebElement val4 = driver.findElement(By.cssSelector(".date-of-birth-month"));
	    WebElement val5 = driver.findElement(By.cssSelector("[aria-selected='true']"));
	    
	    Assert.assertEquals(true, val1.isDisplayed());
	    Assert.assertEquals(true, val2.isDisplayed());
	    Assert.assertEquals(true, val3.isDisplayed());
	    Assert.assertEquals(true, val4.isDisplayed());
	    Assert.assertEquals(true, val5.isDisplayed()); 	    
	    
	    driver.close();
	    driver.switchTo().window(vars.get("root").toString());
	    driver.close();
	    
	}
	
	@Test
	public void test() {
		this.ejercicio1();
		System.out.println("La prueba fue exitosa");
	}
}
