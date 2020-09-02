import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
	//variável do tipo global para 
	//uso em todo o projeto
	
	private WebDriver driver;
	
	//configurando a classe DSL como global	
	private DSL dsl;
	
	//antes de cada teste será executado
	//o conteúdo desse método
	@Before
	public void inicializa(){
		
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		// retorna a raíz do projeto depois de incluir os arquivos na pasta resources
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		
	}
	
	@After	
	public void finaliza(){
		driver.quit();
	}
	
	@Test
	public void testeTextField() {			
		// compara um texto
		Assert.assertEquals("Campo de Treinamento", driver.getTitle());
		dsl.escreve("elementosForm:nome", "Teste de escrita");
		// compara o texto do campo diretamente
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
		
	}

	@Test
	public void deveInteragirComTextArea() {
		
		dsl.escreve("elementosForm:sugestoes", "teste\teste2\nteste3\nteste4");
		Assert.assertEquals("teste\teste2\nteste3\nteste4",dsl.obterValorCampo("elementosForm:sugestoes"));

	}

	@Test
	public void deveInteragirComRadioButton() {

		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
		
	}

	@Test
	public void deveInteragirComCheckBox() {

		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		
	}

	@Test
	public void deveInteragirComComboBox() {
		// combo.selectByIndex(2);
		// combo.selectByValue("2grauincomp");		
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo",dsl.obterPrimeiroValorSelecionadoCombo("elementosForm:escolaridade"));
		
	}

	@Test
	public void deveVerificarValoresCombo() {

		WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());
		boolean encontrou = false;
		// for melhorado
		for (WebElement option : options) {

			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}

		}

		Assert.assertTrue(encontrou);
		

	}

	// combo de múltipla escolha
	@Test
	public void deveVerificarValoresComboMultiplo() {
		
		dsl.selecionarCombo("elementosForm:esportes","Natacao");
		dsl.selecionarCombo("elementosForm:esportes","Corrida");
		dsl.selecionarCombo("elementosForm:esportes","O que eh esporte?");
		
		WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		// verifica o número de ítens selecionado
		List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectedOptions.size());

		// para retirar a seleção feita
		combo.deselectByVisibleText("Corrida");
		allSelectedOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectedOptions.size());

	}

	@Test
	public void deveInteragirComBotoes() {

		dsl.clicarBotao("buttonSimple");
		
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		
	}
	
	@Test
	
	public void deveInteragirComLinkVoltar() {
		
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!",dsl.obterTexto("resultado"));
					
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
				
		//forma não muito recomendada
		/*Assert.assertTrue(driver.findElement(By.tagName("body"))
				.getText().contains("Campo de Treinamento"));*/
		Assert.assertEquals("Campo de Treinamento",dsl.obterTexto(By.tagName("h3")));
					
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...",
		
		dsl.obterTexto(By.className("facilAchar")));
			
	}
	
}
