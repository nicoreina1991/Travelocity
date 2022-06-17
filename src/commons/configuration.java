package commons;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;;

public class configuration {
	
	public static String  DRIVER_DIR= System.getProperty("user.dir")
			+File.separator
			+"driver"
			+File.separator;
	public static String CHROME_DRIVER_PATH = DRIVER_DIR + "chromedriver";	
	public static String GECKO_DRIVER_PATH = DRIVER_DIR  + "geckodriver";
	
	public static String TRAVELOCITY_URL= "https://www.travelocity.com/";
	public static String TRAVELOCITY_URL_VIEW= "https://www.travelocity.com/";
	
	public static String userName="nicolas.reina"; // Borrar y probar con un usuario v�lido
	public static String userPassword= "Nico290791las!"; //Borrar y probar con una contrase�a v�lida
	
	private static String modifyWindows(String path) {	
		if(System.getProperty("os.name").toLowerCase().contains("windows")) {
			return path+".exe";
		}else {
			return path;
		}
	}
	
	public static WebDriver createChromeDriver() {
		System.setProperty("webdriver.chrome.driver",modifyWindows(CHROME_DRIVER_PATH));
		return new ChromeDriver();
		
	}
	
	public static WebDriver createChromeDriver2() {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")
			+File.separator
			+"driver"
			+File.separator+"chromedriver.exe");
		return new ChromeDriver();
	}
	public static WebDriver createGeckoDriver() {
		System.setProperty("webdriver.gecko.driver",modifyWindows(GECKO_DRIVER_PATH));
		return new FirefoxDriver();
		
	}
	
	public static WebDriver createFirefoxHeadlesBrowsering() {
		
		System.setProperty("webdriver.gecko.driver", modifyWindows(GECKO_DRIVER_PATH));
		FirefoxOptions options = new FirefoxOptions();
		options.setHeadless(true);
		return new FirefoxDriver(options);		
		
	}
}
