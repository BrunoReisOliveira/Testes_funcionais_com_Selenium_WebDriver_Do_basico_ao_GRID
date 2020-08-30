import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/*Nome, sobrenome,sexo,comida,escolaridade e esporte

Clicar em cadastrar e verificar a saída com os dados*/


public class TestCadastro {
	
	@Test
	public void DeveInteragirComFormCadastro(){
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// retorna a raíz do projeto depois de incluir os arquivos na pasta resources
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys("Bruno");
		driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Reis de Oliveira");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		new Select(driver.findElement(By.id("elementosForm:escolaridade")))
		.selectByVisibleText("Superior");
		new Select(driver.findElement(By.id("elementosForm:esportes")))
		.selectByVisibleText("Futebol");
		driver.findElement(By.id("elementosForm:cadastrar")).click();
		Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Bruno"));
		Assert.assertEquals("Sobrenome: Reis de Oliveira",driver.findElement(By.id("descSobrenome")).getText());
		Assert.assertEquals("Sexo: Masculino",driver.findElement(By.id("descSexo")).getText());
		Assert.assertEquals("Comida: Vegetariano",driver.findElement(By.id("descComida")).getText());
		Assert.assertEquals("Escolaridade: superior",driver.findElement(By.id("descEscolaridade")).getText());
		Assert.assertEquals("Esportes: Futebol",driver.findElement(By.id("descEsportes")).getText());		
		driver.quit();
		
	}	


}

