package Unidad1.ejemploSelenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {

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

        By locatorLinkLogin = By.linkText("Log in");
        WebElement btnLogin = driver.findElement(locatorLinkLogin);

        btnLogin.click();

        driver.findElement(By.id("Email")).sendKeys("domingo.saavedra@algo.com");

        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        driver.findElement(By.xpath("//input[@value='Log in']")).click();






    }
}