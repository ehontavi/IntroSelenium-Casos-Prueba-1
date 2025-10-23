package Unidad3.glue.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseClass {
    //Atributos
    private WebDriver driver;

    //WebDriver Wait (espera)
    private WebDriverWait espera;

    //Metodos (Cliks, ingreso de texto, obtencion de textos, etc.)

    public WebDriver getDriver() {
        return driver;
    }
    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public BaseClass() {
        this.driver = DriverManager.getDriver();
        espera = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    //buscar Elemento Web esperando 10 segundos
    public WebElement esperarWebElement(By locator) {
        try {

            //return driver.findElement(locator);
            return espera.until(ExpectedConditions.presenceOfElementLocated(locator));
            //obtener el elemento web a retornar

        } catch (Exception ex) {
            System.out.println("Ocurrio un error buscando el elemento web...");
            System.out.println(ex.getStackTrace());
            return null;
        }
    }
    //buscar elementos web esperando 20 segundos
    public List <WebElement> esperarWebElements(By locator) {
        try {

            //return driver.findElement(locator);
            return espera.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
            //obtener el elemento web a retornar
        } catch (Exception ex) {
            System.out.println("Ocurrio un error buscando el elemento web...");
            System.out.println(ex.getStackTrace());
            return null;
        }
    }
    //Click
    public void click(By locator){
        driver.findElement(locator).click();
    }
    //SendKeys
    public void agregarTexto(By locator,String texto) {
        esperarWebElement(locator).sendKeys(texto);
    }
        //getText()
        public String obtenerTexto (By locator) {
            return esperarWebElement(locator).getText();
        }
            //cerrar browser
            public void cerrarBrowser () {
                driver.close();
            }

            //maximizar browser
            public void maximizarbrowser () {
                driver.manage().window().maximize();
            }
        //cargar url
        public void cargarUrl(String url){
            driver.get(url);
    }
        //Manipular dropdown element
    public void seleccionarDDLPorTextoVisible(By locator, String textoVisible){
        Select ddl = new Select(esperarWebElement(locator));
        ddl.selectByVisibleText(textoVisible);
    }
    //metodo generico para buscar elemento web
    public boolean validarElementoWeb(By locator){
        return esperarWebElement(locator).isDisplayed();
    }

    public String obtenerTituloPagina() {
        return driver.getTitle();

    }
}
