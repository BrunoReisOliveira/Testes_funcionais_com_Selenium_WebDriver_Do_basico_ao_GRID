import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;;

public class TestAlert {
	
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
	public void deveInteragirComBotaoAlert() {
	
		driver.findElement(By.id("alert")).click();		
		//como o alerta está externo a página
		//devemos focar o selenium nele		
		Alert alert = driver.switchTo().alert();
		String texto = alert.getText();
		Assert.assertEquals("Alert Simples", texto);
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
	}
	
	@Test
	public void deveInteragirComBotaoConfirmOk() {

		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.accept();
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();		
		
	
	}
	
	@Test
	public void deveInteragirComBotaoConfirmCancel() {

		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.dismiss();
		Assert.assertEquals("Negado", alert.getText());
		alert.dismiss();		
	}
	
	@Test
	public void deveInteragirComBotaoPromt() {

		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("29");
		alerta.accept();
		Assert.assertEquals("Era 29?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D",alerta.getText());
		alerta.accept();
		
	}
}
