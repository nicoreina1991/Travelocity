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

import static org.junit.Assert.assertTrue;

import java.util.*;

class Ejercicio2 {

	private Map<String, Object> vars;
	JavascriptExecutor js;
	WebDriver driver = null;
	private WebDriverWait wait = null;

	@BeforeEach
	void inicializarDriver() {
		driver = configuration.createChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
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

	public void ejercicio2() {			
		// Comienza el script para la letra de la actividad
		driver.findElement(By.cssSelector(".uitk-tab:nth-child(2) .uitk-tab-text")).click();
	    driver.findElement(By.cssSelector("#location-field-leg1-origin-menu .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-leg1-origin")).sendKeys("LAS");
	    driver.findElement(By.cssSelector(".uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.cssSelector(".has-no-placeholder > .uitk-fake-input")).click();
	    driver.findElement(By.id("location-field-leg1-destination")).sendKeys("LAX");
	    driver.findElement(By.cssSelector("#location-field-leg1-destination-menu .uitk-typeahead-result-item:nth-child(1) strong")).click();
	    driver.findElement(By.id("d1-btn")).click();
	    driver.findElement(By.cssSelector(".uitk-button-only-icon:nth-child(2)")).click();
	    driver.findElement(By.cssSelector(".uitk-date-picker-month:nth-child(2) tr:nth-child(2) > .uitk-date-picker-day-number:nth-child(7) > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".uitk-date-picker-month:nth-child(2) tr:nth-child(4) > .uitk-date-picker-day-number:nth-child(6) > .uitk-date-picker-day")).click();
	    driver.findElement(By.cssSelector(".dialog-done")).click();
	    driver.findElement(By.id("add-hotel-checkbox")).click();
	    {
	      WebElement element = driver.findElement(By.id("add-hotel-checkbox"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    {
	      WebElement element = driver.findElement(By.tagName("body"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element, 0, 0).perform();
	    }
	    driver.findElement(By.id("add-car-checkbox")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-grid-item > .uitk-button")).click();
		
		// Validación de elementos presentes para la página de resultados
		
	    WebElement val1 = driver.findElement(By.cssSelector(".uitk-button-large"));
	    WebElement val2 = driver.findElement(By.cssSelector("#sort"));
	    WebElement val3 = driver.findElement(By.cssSelector("#neighborhood-legend > .uitk-heading-7"));
	    WebElement val4 = driver.findElement(By.cssSelector("#poi-legend > .uitk-heading-7"));
	    WebElement val5 = driver.findElement(By.cssSelector("fieldset:nth-of-type(6) .uitk-heading-7"));
	    
	    Assert.assertEquals(true, val1.isDisplayed());
	    Assert.assertEquals(true, val2.isDisplayed());
	    Assert.assertEquals(true, val3.isDisplayed());
	    Assert.assertEquals(true, val4.isDisplayed());
	    Assert.assertEquals(true, val5.isDisplayed()); 	 
		
		driver.findElement(By.id("sort")).click();
		{
			WebElement dropdown = driver.findElement(By.id("sort"));
			dropdown.findElement(By.xpath("//option[. = 'Price']")).click();
		}
		js.executeScript("window.scrollTo(0,500)");
		vars.put("window_handles", driver.getWindowHandles());
		driver.findElement(By.linkText("More information about Boutique Hostel, Opens in a new window")).click();
		vars.put("win3594", waitForWindow(2000));
		vars.put("root", driver.getWindowHandle());
		driver.switchTo().window(vars.get("win3594").toString());
		js.executeScript("window.scrollTo(0,100)");
		
		// Validación de elementos presentes para la página de detalles
		
	    WebElement val6 = driver.findElement(By.cssSelector(".uitk-heading-3"));
	    WebElement val7 = driver.findElement(By.cssSelector("[data-stid='sticky-button']"));
	    WebElement val8 = driver.findElement(By.cssSelector(".uitk-image-roundcorner-all > .uitk-image-link"));
	    WebElement val9 = driver.findElement(By.cssSelector("[data-stid='reviews-link']"));
	    WebElement val10 = driver.findElement(By.cssSelector("h2.uitk-heading-3"));
	    
	    Assert.assertEquals(true, val6.isDisplayed());
	    Assert.assertEquals(true, val7.isDisplayed());
	    Assert.assertEquals(true, val8.isDisplayed());
	    Assert.assertEquals(true, val9.isDisplayed());
	    Assert.assertEquals(true, val10.isDisplayed());
	    
	    driver.findElement(By.cssSelector(".uitk-spacing-padding-inlineend-three > .uitk-button")).click();
	    js.executeScript("window.scrollTo(0,690)");
	    driver.findElement(By.cssSelector(".uitk-spacing:nth-child(3) > div > .uitk-layout-flex .uitk-button")).click();
	    driver.findElement(By.cssSelector("#AQrVAgq_AnY1LXNvcy00ZWIxMmYzODQ0NGQ3MjY3MGM0NDUyYzMzMzQyZTAwYi0wLTEtc3QtdjUtc29zLTI5NmFhNDAxY2Y3NDU5ODQ0MTJlZjJiMjExOTMwZTgzLTMtMX4yLlN-QVFvRUNJSHhCQklIQ05RRUVBY1lHeWdEV0FKd0FIQUF-QVFvdENpc0kxWUlCRWdNek5UQVlpNUFCSUl1NEFTanB5SlFDTUxUSmxBSTRUa0FBV0FGcUNFVkRUMEpCVTBsREVnb0lBUkFCR0FFcUFsVkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb2pDaUVJenBZQkVnTTJORGdZaTdnQklJdVFBU2lXMzVVQ01PZmZsUUk0UjBBQVdBRVNDZ2dCRUFFWUFTb0NUa3NZQVNJRUNBRVFBU2dDS0FNb0JEQU4RKVyPwvUYWEAiAQEqBRIDCgExEj8KFgoKMjAyMi0wOC0xMxIDTEFTGgNMQVgKFgoKMjAyMi0wOC0yNhIDTEFYGgNMQVMSBxIFQ09BQ0gaAhABIAI\\= .uitk-card-link")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-flex:nth-child(1) > .uitk-card .uitk-button")).click();
	    {
	      WebElement element = driver.findElement(By.cssSelector("#AQrhAgrBAnY1LXNvcy00ZWIxMmYzODQ0NGQ3MjY3MGM0NDUyYzMzMzQyZTAwYi0wLTEtc3QtdjUtc29zLTcxNDE4YTliOWUzNmY5MjA2ZjI1YThmMmFmNTllNTMyLTQtMX4yLlN-QVFvRUNJSHhCQklIQ05RRUVBY1lHeWdEV0FKd0FIQUF-QVFvdENpc0kxWUlCRWdNek5UQVlpNUFCSUl1NEFTanB5SlFDTUxUSmxBSTRUa0FBV0FGcUNFVkRUMEpCVTBsREVnb0lBUkFCR0FFcUFsVkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb2tDaUlJenBZQkVnUXpPVEV6R0l1NEFTQ0xrQUVveU4tVkFqQ1g0SlVDT0VkQUFGZ0JFZ29JQVJBQkdBRXFBazVMR0FFaUJBZ0JFQUVvQWlnREtBUXdEUREpXI_C9RhYQCICAQIqBRIDCgExKgcIARIDCgExEj8KFgoKMjAyMi0wOC0xMxIDTEFTGgNMQVgKFgoKMjAyMi0wOC0yNhIDTEFYGgNMQVMSBxIFQ09BQ0gaAhABIAIgAQ\\=\\= .uitk-card-link"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    driver.findElement(By.cssSelector("#AQrfAgq_AnY1LXNvcy00ZWIxMmYzODQ0NGQ3MjY3MGM0NDUyYzMzMzQyZTAwYi0wLTEtc3QtdjUtc29zLTI5NmFhNDAxY2Y3NDU5ODQ0MTJlZjJiMjExOTMwZTgzLTMtMX4yLlN-QVFvRUNJSHhCQklIQ05RRUVBY1lHeWdEV0FKd0FIQUF-QVFvdENpc0kxWUlCRWdNek5UQVlpNUFCSUl1NEFTanB5SlFDTUxUSmxBSTRUa0FBV0FGcUNFVkRUMEpCVTBsREVnb0lBUkFCR0FFcUFsVkJHQUVpQkFnQkVBRW9BaWdES0FRd0FRLkFRb2pDaUVJenBZQkVnTTJORGdZaTdnQklJdVFBU2lXMzVVQ01PZmZsUUk0UjBBQVdBRVNDZ2dCRUFFWUFTb0NUa3NZQVNJRUNBRVFBU2dDS0FNb0JEQU4RKVyPwvUYWEAiAgECKgUSAwoBMSoHCAESAwoBMRI_ChYKCjIwMjItMDgtMTMSA0xBUxoDTEFYChYKCjIwMjItMDgtMjYSA0xBWBoDTEFTEgcSBUNPQUNIGgIQASACIAE\\= .uitk-card-link")).click();
	    driver.findElement(By.cssSelector(".uitk-button:nth-child(4)")).click();
	    driver.findElement(By.cssSelector(".uitk-layout-flex:nth-child(1) > .uitk-layout-flex > .uitk-layout-position .uitk-button")).click();
	    driver.findElement(By.cssSelector(".uitk-button-large")).click();
	    {
	      WebElement element = driver.findElement(By.linkText("Roundtrip Private Sedan"));
	      Actions builder = new Actions(driver);
	      builder.moveToElement(element).perform();
	    }
	    // Validación de elementos presentes para la página Who's travelling
		
	    WebElement val11 = driver.findElement(By.cssSelector(".flights"));
	    WebElement val12 = driver.findElement(By.cssSelector("#seat-map-button"));
	    WebElement val13 = driver.findElement(By.cssSelector("[data-cko-rfrr-id='CKO.FLIGHT.FIRSTNAME']"));
	    WebElement val14 = driver.findElement(By.cssSelector("[data-cko-change='updateLxTravelerDropDown']"));
	    WebElement val15 = driver.findElement(By.cssSelector("#coupon-code"));
	    
	    Assert.assertEquals(true, val11.isDisplayed());
	    Assert.assertEquals(true, val12.isDisplayed());
	    Assert.assertEquals(true, val13.isDisplayed());
	    Assert.assertEquals(true, val14.isDisplayed());
	    Assert.assertEquals(true, val15.isDisplayed());
	    
	    driver.close();
	    driver.switchTo().window(vars.get("root").toString());
	    driver.close();
	    driver.switchTo().window(vars.get("undefined").toString());
	}
	
	@Test
	public void test() {
		this.ejercicio2();
		System.out.println("La prueba fue exitosa");
	}
}
