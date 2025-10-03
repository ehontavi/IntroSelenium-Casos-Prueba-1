package EjemploClase1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumClase1 {
    public static void main(String[] args) throws InterruptedException {
        //CONFIGURAR DRIVER GOOGLE CHROME
        WebDriverManager.chromedriver().setup();
        //Creacion de objeto WebDriver para manipular el browser
        WebDriver driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/");
        System.out.println("Titulo Pagina web: " + driver.getTitle());

        //maximizar el sitio manage()
        driver.manage().window().maximize();

        Thread.sleep(5000); //espera si o si 5 segundos - FIJO

        By locatorLinkRegister = By.linkText("Register");

        //WebElement (instancia del boton en el codigo)
        WebElement btnRegister = driver.findElement(locatorLinkRegister);

        btnRegister.click();

        By locatorGeneroslocatorGeneros = By.xpath("//input[@type='radio']");

        List<WebElement> generos = driver.findElements(locatorGeneroslocatorGeneros);

        generos.get(0).click();
        Thread.sleep(2000);


        driver.findElement(By.id("FirstName")).sendKeys("Pobre domingo");

        //lastname
        driver.findElement(By.name("LastName")).sendKeys("Saavedra");

        //email
        driver.findElement(By.xpath("//input[@data-val-required='Email is required.']")).sendKeys("domingo.saavedra@algo.com");

        //password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123456");

        //click en register
        driver.findElement(By.id("register-button")).click();

        String RegistroOK = driver.findElement(By.xpath("//div[@class='result' and normalize-space(text())='Your registration completed']")).getText();



        if(RegistroOK.equalsIgnoreCase("Your registration completed")){
            System.out.println("Prueba OK");
        }else{
            System.out.println("Mensaje distinto al esperado. Favor revisar");
            System.out.println("Resultado Obtenido: "+ RegistroOK);
            System.out.println("Resultado Esperado: Your registration completed");
        }

    }
}














