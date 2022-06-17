package commons;

import static org.junit.jupiter.api.Assertions.*;

import javax.security.auth.login.Configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ClaseBaseTravelocity {
	
	protected WebDriver driver = null;
	protected WebDriverWait wait = null;
	
	@BeforeEach
	public void iniciarBrowser() throws Exception{
		
		// Se crea el driver para correr los casos de prueba
		driver = configuration.createChromeDriver();
		wait=new WebDriverWait(driver, 30);
		driver.get(configuration.TRAVELOCITY_URL);
		
		// Login de usuario 
		WebElement iniciarSesionHeader = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"login-box\"]/div/div/h4"))); //Espera a encontrar el header "Inicio de sesi�n"
		iniciarSesionHeader.click(); // Hace click
		WebElement usernameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"username\"]"))); // Espera a encontrar el campo user
		usernameInput.sendKeys(configuration.userName); //Ingresa el nombre de usuario
		WebElement iniciarSesionbtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"login-form\"]/fieldset/input[2]"))); //Espera a encontrar el bot�n "Iniciar sesi�n"
		iniciarSesionbtn.click(); // Hace click
		WebElement passwordInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"password\"]"))); // Espera a encontrar el campo password
		passwordInput.sendKeys(configuration.userPassword); // Ingresa la contrase�a
		passwordInput.submit(); // Env�a todo
		WebElement textoAssertions= driver.findElement(By.linkText("nicolas.reina ( nicolas.reina )"));
		SeleniumUtils.printElementInfo("nicolas.reina ( nicolas.reina )", textoAssertions);
	}
	
	@AfterEach
	void cerrarNavegador() {
		driver.quit();	
	}

}