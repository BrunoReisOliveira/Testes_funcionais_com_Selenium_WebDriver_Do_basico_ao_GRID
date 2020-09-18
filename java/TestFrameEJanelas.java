import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestFrameEJanelas {
	
	//variável do tipo global para 
	//uso em todo o projeto
	
	private WebDriver driver;
	
	//antes de cada teste será executado
	//o conteúdo desse método
	@Before
	public void inicializa(){
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// retorna a raíz do projeto depois de incluir os arquivos na pasta resources
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After	
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void deveInteragirComFrame(){
		
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!",msg);
		alert.accept();		
		//retorna o foco para a página principal
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
			
	}
	
	@Test
	
	public void deveInteragirComJanelas(){
				
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("Bruno Reis de Oliveira");
		
	
	}
	
	@Test
	
	public void deveInteragirComJanelasSemTitulos(){
				
		driver.findElement(By.id("buttonPopUpHard")).click();
		//verifica o id atual da janela
		System.out.println(driver.getWindowHandle());
		//verifica o id atual das janelas
		System.out.println(driver.getWindowHandles());
		//altera para a popup através de casting
		//isso ajuda quando não temos um título da janela secundária
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		//retorna para janela principal
		//notar que o array é indexado por 0
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		
	}

}
