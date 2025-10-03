package ejemploSeleniumJunit;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.security.PrivateKey;
import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCaseDemoWeb {
    private static int numberOfCase = 0;
    private WebDriver driver;


    //ANTES DE TODO
    @BeforeAll
    public static void beforeSuite() {

        //CONFIGURAR DRIVER GOOGLE CHROME
        WebDriverManager.chromedriver().setup();
        //precondiciona nivel de suite
        System.out.println("EJECUCION SUITE DE PRUEBAS CURSO SECURITY");
        System.out.println("-----------------------------------------");
        System.out.println("PREPARANDO DATA DE PRUEBAS, CONEXIONES, ACCESOS Y BLA BLA BLA");


    }

    //ANTES DE CADA TEST
    @BeforeEach
    public void beforeTests() throws InterruptedException {
        numberOfCase++;
        System.out.println("EJECUTANDO TEST" + numberOfCase);

        //PRECONDICIONES A NIVEL DE PASOS PREVIOS EN CADA TEST

        //Creacion de objeto WebDriver para manipular el browser
        driver = new ChromeDriver();
        driver.get("https://demowebshop.tricentis.com/"); //CONFIGURACION
        System.out.println("Titulo Pagina web: " + driver.getTitle());//VERIFICACION PRECONDICION

        //maximizar el sitio manage()
        driver.manage().window().maximize(); //CONFIGURACIONES

        Thread.sleep(5000); //espera si o si 5 segundos - FIJO

        //Manejar con hasta 30 segundos el tiempo de espera para manipular un elemento web
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Tiempo de carga de las paginas
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        //Tiempo de carga de los elementos asincronos (javaScript)
        driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);

    }

    @AfterAll
    public static void afterSuite() {
        System.out.println("SE FINALIZA SUITE DE PRUEBAS.....IMPRIMIENDO REPORTE......");


    }

    @AfterEach
    public void postTests() {
        System.out.println("Pos condiciones test nro: " + numberOfCase);
        driver.close();
    }


    @Order(1)
    @Test
    public void CP001_CREATE_ACCOUNT() throws InterruptedException {

        By locatorLinkRegister = By.linkText("Register");

        //WebElement (instancia del boton en el codigo)
        WebElement btnRegister = driver.findElement(locatorLinkRegister);

        btnRegister.click();

        By locatorGeneroslocatorGeneros = By.xpath("//input[@type='radio']");

        List<WebElement> generos = driver.findElements(locatorGeneroslocatorGeneros);

        generos.get(0).click();
        Thread.sleep(2000);


        driver.findElement(By.id("FirstName")).sendKeys("Esteban");

        //lastname
        driver.findElement(By.name("LastName")).sendKeys("Hontavilla");

        //email
        driver.findElement(By.xpath("//input[@data-val-required='Email is required.']")).sendKeys("ehontavil790@algo.com");

        //password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123456");

        //click en register
        driver.findElement(By.id("register-button")).click();

        String RegistroOK = driver.findElement(By.xpath("//div[@class='result' and normalize-space(text())='Your registration completed']")).getText();


        Assertions.assertEquals("Your registration completed", driver.findElement(By.xpath("//div[@class='result' and normalize-space(text())='Your registration completed']")).getText());


    }

    @Order(2)
    @Test
    public void CP002_ERROR_CREATE_ACCOUNT() throws InterruptedException {

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


        // Browser.elementWEB( L         O         C         A        L      I      Z      A        D        O         R )).accion();
        String RegistroOK = driver.findElement(By.xpath("//li[text()='The specified email already exists']")).getText();

        Assertions.assertEquals("The specified email already exists", driver.findElement(By.xpath("//li[text()='The specified email already exists']")).getText());


        //if (RegistroOK.equalsIgnoreCase("The specified email already exists")) {
        //   System.out.println("Prueba OK");
        //} else {
        //   System.out.println("Mensaje distinto al esperado. Favor revisar");
        //   System.out.println("Resultado Obtenido: " + RegistroOK);
        //   System.out.println("Resultado Esperado: The specified email already exists");


    }


    @Order(3)
    @Test
    public void CP003_CAMPOSVACIOS_FIRSTNAME() throws InterruptedException {

        By locatorLinkRegister = By.linkText("Register");

        //WebElement (instancia del boton en el codigo)
        WebElement btnRegister = driver.findElement(locatorLinkRegister);

        btnRegister.click();

        By locatorGeneroslocatorGeneros = By.xpath("//input[@type='radio']");

        List<WebElement> generos = driver.findElements(locatorGeneroslocatorGeneros);

        generos.get(0).click();
        Thread.sleep(2000);


        driver.findElement(By.id("FirstName")).sendKeys("");

        //lastname
        driver.findElement(By.name("LastName")).sendKeys("Hontavilla");

        //email
        driver.findElement(By.xpath("//input[@data-val-required='Email is required.']")).sendKeys("ehontavil800@algo.com");

        //password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123456");

        //click en register
        driver.findElement(By.id("register-button")).click();

        String RegistroOK = driver.findElement(By.xpath("//span[text()='First name is required.']")).getText();

        Assertions.assertEquals("First name is required.", driver.findElement(By.xpath("//span[text()='First name is required.']")).getText());


    }

    @Order(4)
    @Test
    public void CP003_CAMPOSVACIOS_LASTNAME() throws InterruptedException {

        By locatorLinkRegister = By.linkText("Register");

        //WebElement (instancia del boton en el codigo)
        WebElement btnRegister = driver.findElement(locatorLinkRegister);

        btnRegister.click();

        By locatorGeneroslocatorGeneros = By.xpath("//input[@type='radio']");

        List<WebElement> generos = driver.findElements(locatorGeneroslocatorGeneros);

        generos.get(0).click();
        Thread.sleep(2000);


        driver.findElement(By.id("FirstName")).sendKeys("Esteban");

        //lastname
        driver.findElement(By.name("LastName")).sendKeys("");

        //email
        driver.findElement(By.xpath("//input[@data-val-required='Email is required.']")).sendKeys("ehontavi800@algo.com");

        //password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123456");

        //click en register
        driver.findElement(By.id("register-button")).click();

        String RegistroOK = driver.findElement(By.xpath("//span[text()='Last name is required.']")).getText();

        Assertions.assertEquals("Last name is required.", driver.findElement(By.xpath("//span[text()='Last name is required.']")).getText());


    }

    @Order(5)
    @Test
    public void CP005_CAMPOSVACIOS_CONFIRMPASSWORD() throws InterruptedException {

        By locatorLinkRegister = By.linkText("Register");

        //WebElement (instancia del boton en el codigo)
        WebElement btnRegister = driver.findElement(locatorLinkRegister);

        btnRegister.click();

        By locatorGeneroslocatorGeneros = By.xpath("//input[@type='radio']");

        List<WebElement> generos = driver.findElements(locatorGeneroslocatorGeneros);

        generos.get(1).click();
        Thread.sleep(2000);


        driver.findElement(By.id("FirstName")).sendKeys("SCARLET");

        //lastname
        driver.findElement(By.name("LastName")).sendKeys("OVERKILL");

        //email
        driver.findElement(By.xpath("//input[@data-val-required='Email is required.']")).sendKeys("soverkill@algo.com");

        //password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("");

        //click en register
        driver.findElement(By.id("register-button")).click();

        String RegistroOK = driver.findElement(By.xpath("//span[text()='Password is required.']")).getText();

        Assertions.assertEquals("Password is required.", driver.findElement(By.xpath("//span[text()='Password is required.']")).getText());


    }

    @Order(6)
    @Test
    public void CP006_COMMUNITYPOOL_WITH_OUT_REGISTER() throws InterruptedException {

        By locatorPoll = By.xpath("//input[@name='pollanswers-1']");

        List<WebElement> poll = driver.findElements(locatorPoll);

        poll.get(0).click();
        Thread.sleep(2000);

        By BtnVote = By.xpath("//input[@id='vote-poll-1']");
        WebElement Vote = driver.findElement(BtnVote);

        Vote.click();
        Thread.sleep(1000);

        //String ErrorVote = driver.findElement(By.xpath("//div[text()='Only registered users can vote.']")).getText();

        Assertions.assertEquals("Only registered users can vote.", driver.findElement(By.id("block-poll-vote-error-1")).getText());
    }

    @Order(7)
    @Test
    public void CP007_COMMUNITYPOOL_WITH_REGISTER() throws InterruptedException {

        By locatorPoll = By.xpath("//input[@name='pollanswers-1']");

        List<WebElement> poll = driver.findElements(locatorPoll);

        poll.get(0).click();
        Thread.sleep(2000);

        By BtnVote = By.xpath("//input[@id='vote-poll-1']");
        WebElement Vote = driver.findElement(BtnVote);

        Vote.click();
        Thread.sleep(1000);

        //String ErrorVote = driver.findElement(By.xpath("//div[text()='Only registered users can vote.']")).getText();

        Assertions.assertEquals("Only registered users can vote.", driver.findElement(By.id("block-poll-vote-error-1")).getText());

        By locatorLinkRegister = By.linkText("Register");

        //WebElement (instancia del boton en el codigo)
        WebElement btnRegister = driver.findElement(locatorLinkRegister);

        btnRegister.click();

        By locatorGeneroslocatorGeneros = By.xpath("//input[@type='radio']");

        List<WebElement> generos = driver.findElements(locatorGeneroslocatorGeneros);

        generos.get(0).click();
        Thread.sleep(2000);


        driver.findElement(By.id("FirstName")).sendKeys("Esteban");

        //lastname
        driver.findElement(By.name("LastName")).sendKeys("Hontavilla");

        //email
        driver.findElement(By.xpath("//input[@data-val-required='Email is required.']")).sendKeys("ehontavil700@algo.com");

        //password
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        //Confirm password
        driver.findElement(By.name("ConfirmPassword")).sendKeys("123456");

        //click en register
        driver.findElement(By.id("register-button")).click();

        String RegistroOK = driver.findElement(By.xpath("//div[@class='result' and normalize-space(text())='Your registration completed']")).getText();


        Assertions.assertEquals("Your registration completed", driver.findElement(By.xpath("//div[@class='result' and normalize-space(text())='Your registration completed']")).getText());

        By BtnContinue = By.xpath("//input[@class='button-1 register-continue-button']");
        WebElement Continue = driver.findElement(BtnContinue);

        Continue.click();
        Thread.sleep(4000);

        By locatorPollHome = By.xpath("//input[@name='pollanswers-1']");

        List<WebElement> pollHome = driver.findElements(locatorPollHome);

        pollHome.get(0).click();
        Thread.sleep(2000);

        By BtnVoteHome = By.xpath("//input[@id='vote-poll-1']");
        WebElement VoteHome = driver.findElement(BtnVoteHome);

        VoteHome.click();
        Thread.sleep(4000);

        By LabelVote = By.xpath("//span[@class='poll-total-votes']");
        WebElement LVote = driver.findElement(LabelVote);

        Assertions.assertTrue(LVote.isDisplayed());

    }

    @Order(8)
    @Test
    public void CP008_COMMUNITYPOOL_WITH_LOGIN() throws InterruptedException {

        By locatorPoll = By.xpath("//input[@name='pollanswers-1']");

        List<WebElement> poll = driver.findElements(locatorPoll);

        poll.get(0).click();
        Thread.sleep(2000);

        By BtnVote = By.xpath("//input[@id='vote-poll-1']");
        WebElement Vote = driver.findElement(BtnVote);

        Vote.click();
        Thread.sleep(1000);

        Assertions.assertEquals("Only registered users can vote.", driver.findElement(By.id("block-poll-vote-error-1")).getText());

        By locatorLinkLogin = By.linkText("Log in");

        WebElement btnLogin = driver.findElement(locatorLinkLogin);

        btnLogin.click();

        driver.findElement(By.id("Email")).sendKeys("ehontavil600@gmail.com");

        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        By BtnLogin = By.xpath("//input[@value=\"Log in\"]");
        WebElement LOGIN = driver.findElement(BtnLogin);

        LOGIN.click();
        Thread.sleep(4000);

        By locatorPollHome = By.xpath("//input[@name='pollanswers-1']");

        List<WebElement> pollHome = driver.findElements(locatorPollHome);

        pollHome.get(0).click();
        Thread.sleep(2000);

        By BtnVoteHome = By.xpath("//input[@id='vote-poll-1']");
        WebElement VoteHome = driver.findElement(BtnVoteHome);

        VoteHome.click();
        Thread.sleep(4000);

        By LabelVote = By.xpath("//span[@class='poll-total-votes']");
        WebElement LVote = driver.findElement(LabelVote);

        Assertions.assertTrue(LVote.isDisplayed());

        }

    @Order(9)
    @Test
    public void CP008_LOGIN_ERROR() throws InterruptedException {
        By locatorLinkLogin = By.linkText("Log in");

        WebElement btnLogin = driver.findElement(locatorLinkLogin);

        btnLogin.click();

        driver.findElement(By.id("Email")).sendKeys("ehontavil15@gmail.com");

        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("123456");

        By BtnLogin = By.xpath("//input[@value=\"Log in\"]");
        WebElement LOGIN = driver.findElement(BtnLogin);

        LOGIN.click();
        Thread.sleep(4000);

        Assertions.assertEquals("Login was unsuccessful. Please correct the errors and try again.", driver.findElement(By.xpath("//span[contains(text(),'Login was unsuccessful. Please correct the errors and try again.')]")).getText());
    }
}











