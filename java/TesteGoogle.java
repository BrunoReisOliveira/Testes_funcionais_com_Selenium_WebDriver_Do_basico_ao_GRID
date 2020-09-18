import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	//variável do tipo global para 
	//uso em todo o projeto
	
	private WebDriver driver;
	
	//antes de cada teste será executado
	//o conteúdo desse método
	@Before
	public void inicializa(){
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		
	}
	
	@After	
	public void finaliza(){
		driver.quit();
	}
	//notação para Junit reconhecer o método como método de teste
	@Test
	public void teste () {
		//como já adicionei a variável de ambiente, não preciso incluir a linha abaixo
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Bruno\\Documents\\qa\\geckodriver-v0.27.0-win64\\geckodriver.exe");
		
		//WebDriver driver = new ChromeDriver();
		//gerencia a posiçãoda janela
		//driver.manage().window().setPosition(new Point(100,100));
		
		//inicia o browser maximizado
		//driver.manage().window().maximize();
		//WebDriver driver = new InternetExplorerDriver();
		driver.get("https://www.google.com");		
		Assert.assertEquals("Google",driver.getTitle());				
		
	}
}
