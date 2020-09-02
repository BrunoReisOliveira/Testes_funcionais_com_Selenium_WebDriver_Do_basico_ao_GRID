import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

//tentativa de fazer o desafio sozinho
//melhores práticas inclusas na classe TestCadastro conforme correção do professor

public class TestRegraNegocioBruno {
	
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
	
	
	/*Testar a regra de negócio do formulário

	campos: nome é obrigatório,sobrenome é obrigatório,sexo é obrigatório, 
	não posso dizer que sou vegetariano e como carne,
	não posso marcar um esporte e perguntar o que é esporte?*/
	
	@Test
	public void deveInteragirComCampoNome() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio",alert.getText());
		alert.accept();
		
	}
	
	@Test
	public void deveInteragirComCampoSobreNome() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio",alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Bruno");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert2 =driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio",alert2.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Reis de Oliveira");
		
	}
	
	@Test
	public void deveInteragirComCampoSexo() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio",alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Bruno");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert2 =driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio",alert2.getText());
		alert2.accept();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Reis de Oliveira");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert3 = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio",alert3.getText());
		alert3.accept();
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());		
		
	}
	
	@Test
	public void deveInteragirComCampoComidaVegetariano() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio",alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Bruno");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert2 =driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio",alert2.getText());
		alert2.accept();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Reis de Oliveira");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert3 = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio",alert3.getText());
		alert3.accept();
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:3")).isSelected());
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert4 = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?",alert4.getText());
		alert4.accept();	
		
	}
	
	@Test
	public void deveInteragirComCampoEsporte() {

		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert =driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio",alert.getText());
		alert.accept();
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Bruno");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert2 =driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio",alert2.getText());
		alert2.accept();
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Reis de Oliveira");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert3 = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio",alert3.getText());
		alert3.accept();
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected());
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert4 = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert4.getText());	
		alert4.accept();
		
	}	

}
