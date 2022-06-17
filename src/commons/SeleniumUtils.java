package commons;

import org.openqa.selenium.WebElement;

public class SeleniumUtils {
	
	
	
	public static void printElementInfo(String nombre, WebElement element) {
		
		String separador="----------------------------------------------------";
		System.out.println("Element: "+ nombre);
		System.out.println(separador);
		System.out.println("Objeto WeblElement: "+ element );
		System.out.println("HTML Externo: "+ element.getAttribute("outerHTML"));
		System.out.println("HTML Interno: "+ element.getAttribute("innerHTML"));
		System.out.println("texto: "+ element.getText());
		System.out.println("Valor: "+ element.getAttribute("value"));
		System.out.println("tag: "+ element.getTagName());
		System.out.println("visible: " +element .isDisplayed());
		System.out.println("Seleccionable: "+ element.isSelected());
		System.out.println("Habilitado: "+ element.isEnabled());
		
	}
	
	
	
	

}
