

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class TesteGoogle {
	
	//notação para Junit reconhecer o método como método de teste
	@Test
	public void teste () {
		//como já adicionei a variável de ambiente, não preciso incluir a linha abaixo
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Bruno\\Documents\\qa\\geckodriver-v0.27.0-win64\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		//gerencia a posiçãoda janela
		//driver.manage().window().setPosition(new Point(100,100));
		//gerencia a dimensão da janela
		driver.manage().window().setSize(new Dimension(1200,765));
		//inicia o browser maximizado
		//driver.manage().window().maximize();
		//WebDriver driver = new InternetExplorerDriver();
		driver.get("https://www.google.com");
		
		Assert.assertEquals("Google",driver.getTitle());
		
		//fecha todas as abas e instâncias do driver.
		driver.quit();
		
		
	}
}
