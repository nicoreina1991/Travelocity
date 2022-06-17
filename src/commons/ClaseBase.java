package commons;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.security.auth.login.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClaseBase {
	
	public static WebDriver driver=null;
	public static WebDriverWait wait = null;
		
	@BeforeEach
	void inicializarDriver() {
		driver = configuration.createChromeDriver();
		wait = new WebDriverWait(driver, 30);
		driver.manage().window().maximize();
		driver.get(configuration.TRAVELOCITY_URL);
		
	}

	@AfterEach
	void cerrarNavegador() {
		driver.quit();
	}
	

}
