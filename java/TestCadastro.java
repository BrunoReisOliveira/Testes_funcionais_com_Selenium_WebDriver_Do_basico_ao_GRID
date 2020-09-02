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

/*Nome, sobrenome,sexo,comida,escolaridade e esporte

Clicar em cadastrar e verificar a saída com os dados*/


public class TestCadastro {
	//variável do tipo global para 
	//uso em todo o projeto
	
	private WebDriver driver;
	
	//DSL configurada como global
	private DSL dsl;
	
	//antes de cada teste será executado
	//o conteúdo desse método
	@Before
	public void inicializa(){
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// retorna a raíz do projeto depois de incluir os arquivos na pasta resources
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl=new DSL(driver);
	}
	
	@After	
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void deveRealizarCadastroComSucesso(){
		
		dsl.escreve("elementosForm:nome","Bruno");
		dsl.escreve("elementosForm:sobrenome","Reis de Oliveira");
		dsl.clicarRadio("elementosForm:sexo:0");
		dsl.clicarRadio("elementosForm:comidaFavorita:3");
		dsl.selecionarCombo("elementosForm:escolaridade","Superior");
		dsl.selecionarCombo("elementosForm:esportes","Futebol");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Bruno"));
		Assert.assertEquals("Sobrenome: Reis de Oliveira",dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino",dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Vegetariano",dsl.obterTexto("descComida"));
		Assert.assertEquals("Escolaridade: superior",dsl.obterTexto(By.id("descEscolaridade")));
		Assert.assertEquals("Esportes: Futebol",dsl.obterTexto(By.id("descEsportes")));		
				
	}	
	
	@Test	
	public void deveValidarNomeObrigatorio(){
		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Nome eh obrigatorio", alert.getText());
				
	}
	
	@Test	
	public void deveValidarSobreomeObrigatorio(){

		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");		
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
	}
	
	@Test	
	public void deveValidarSexoObrigatorio(){
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
				
	}
	
	
	@Test	
	public void deveValidarComidaVegetariana(){
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
			
	}
	
	@Test	
	public void deveValidarEsportistaIndeciso(){
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");		
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
				
	}

}

