import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;



public class TestFrameEJanelas {
	
	@Test
	public void deveInteragirComFrame(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// retorna a raíz do projeto depois de incluir os arquivos na pasta resources
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText();
		Assert.assertEquals("Frame OK!",msg);
		alert.accept();
		
		//retorna o foco para a página principal
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		
		driver.quit();	
		
	}
	
	@Test
	
	public void deveInteragirComJanelas(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// retorna a raíz do projeto depois de incluir os arquivos na pasta resources
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.close();
		driver.switchTo().window("");
		driver.findElement(By.tagName("textarea")).sendKeys("Bruno Reis de Oliveira");
		
	
	}
	

}