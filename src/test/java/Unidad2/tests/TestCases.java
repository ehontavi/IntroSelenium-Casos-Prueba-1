package Unidad2.tests;

import Unidad2.pages.HomePage;
import Unidad2.pages.LoginPage;
import Unidad2.pages.RegisterPage;
import Unidad2.utils.DataDriven;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestCases {
        //Atributos
    WebDriver driver;
    HomePage home;
    RegisterPage register;
    LoginPage login;


    ArrayList<String> datodePrueba;


        //ANTES DE TODO
        @BeforeAll
        public static void beforeSuite() {
            WebDriverManager.chromedriver().setup(); //Setup driver para usar Chrome
        }

        //ANTES DE CADA TEST
        @BeforeEach
        public void beforeTests() throws InterruptedException {
            datodePrueba = new ArrayList<>();
            driver = new ChromeDriver(); //Se instancia el driver de Chrome
            home = new HomePage(driver);
            register = new RegisterPage(home.getDriver());
            login = new LoginPage(home.getDriver());

            home.cargarUrl("https://demowebshop.tricentis.com/");
            home.maximizarbrowser();
        }
        @AfterAll
        public static void afterSuite() {

        }

        @AfterEach
        public void postTests() {
            home.cerrarBrowser();

        }


        @Order(1)
        @Test
        public void CP001_CREATE_ACCOUNT() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP001_CREATE_ACCOUNT");
            home.irARegister();
            register.crearCuenta(Integer.parseInt(datodePrueba.get(1)), datodePrueba.get(2), datodePrueba.get(3), datodePrueba.get(4),datodePrueba.get(5),datodePrueba.get(6));
            Assertions.assertEquals(datodePrueba.get(7),register.getMensajeCtaOK());

        }

        @Order(2)
        @Test
        public void CP002_ERROR_CREATE_ACCOUNT() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP002_ERROR_CREATE_ACCOUNT");
            home.irARegister();
            register.crearCuenta(Integer.parseInt(datodePrueba.get(1)), datodePrueba.get(2), datodePrueba.get(3), datodePrueba.get(4),datodePrueba.get(5),datodePrueba.get(6));
            Assertions.assertEquals(datodePrueba.get(7),register.getErrorMensajeCta_MailUsed());
        }


        @Order(3)
        @Test
        public void CP003_CAMPOSVACIOS_FIRSTNAME() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP003_CAMPOSVACIOS_FIRSTNAME");
            home.irARegister();
            register.crearCuenta(Integer.parseInt(datodePrueba.get(1)), datodePrueba.get(2), datodePrueba.get(3), datodePrueba.get(4),datodePrueba.get(5),datodePrueba.get(6));
            Assertions.assertEquals(datodePrueba.get(7),register.getErrorFirstNameEmpty());
        }

        @Order(4)
        @Test
        public void CP004_CAMPOSVACIOS_LASTNAME() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP004_CAMPOSVACIOS_LASTNAME");
            home.irARegister();
            register.crearCuenta(Integer.parseInt(datodePrueba.get(1)), datodePrueba.get(2), datodePrueba.get(3), datodePrueba.get(4),datodePrueba.get(5),datodePrueba.get(6));
            Assertions.assertEquals(datodePrueba.get(7),register.getErrorLastNameEmpty());
        }

        @Order(5)
        @Test
        public void CP005_CAMPOSVACIOS_CONFIRMPASSWORD() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP005_CAMPOSVACIOS_CONFIRMPASSWORD");
            home.irARegister();
            register.crearCuenta(Integer.parseInt(datodePrueba.get(1)), datodePrueba.get(2), datodePrueba.get(3), datodePrueba.get(4),datodePrueba.get(5),datodePrueba.get(6));
            Assertions.assertEquals(datodePrueba.get(7),register.getErrorConfirmPasswordEmpty());
        }

        @Order(6)
        @Test
        public void CP006_COMMUNITYPOOL_WITH_OUT_REGISTER() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP006_COMMUNITYPOOL_WITH_OUT_REGISTER");
            home.VotePollExcelent();
            home.VotePollVote();
            Thread.sleep(2000);
            Assertions.assertEquals(datodePrueba.get(1),home.getErrorVoteWithoutRegistration());
        }

        @Order(7)
        @Test
        public void CP007_COMMUNITYPOOL_WITH_REGISTER() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP007_COMMUNITYPOOL_WITH_REGISTER");
            home.VotePollExcelent();
            home.VotePollVote();
            Thread.sleep(2000);
            Assertions.assertEquals(datodePrueba.get(1),home.getErrorVoteWithoutRegistration());
            home.irARegister();
            register.crearCuenta(Integer.parseInt(datodePrueba.get(2)), datodePrueba.get(3), datodePrueba.get(4), datodePrueba.get(5),datodePrueba.get(6),datodePrueba.get(7));
            Assertions.assertEquals(datodePrueba.get(8),register.getMensajeCtaOK());
            register.irALogin();
            home.VotePollExcelent();
            home.VotePollVote();
            Thread.sleep(2000);
            Assertions.assertTrue(home.getVoteOK());

        }

        @Order(8)
        @Test
        public void CP008_COMMUNITYPOOL_WITH_LOGIN() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP008_COMMUNITYPOOL_WITH_LOGIN");
            home.VotePollExcelent();
            home.VotePollVote();
            Thread.sleep(2000);
            Assertions.assertEquals(datodePrueba.get(1),home.getErrorVoteWithoutRegistration());
            home.irALogin();
            login.ingresarUsuario(datodePrueba.get(2),datodePrueba.get(3));
            home.VotePollExcelent();
            home.VotePollVote();
            Thread.sleep(2000);
            Assertions.assertTrue(home.getVoteOK());

        }

        @Order(9)
        @Test
        public void CP009_LOGIN_ERROR() throws InterruptedException {
            datodePrueba = DataDriven.getData("CP009_LOGIN_ERROR");
            home.irALogin();
            login.ingresarUsuario(datodePrueba.get(1),datodePrueba.get(2));
            Assertions.assertEquals(datodePrueba.get(3),home.getErrorLogin());



        }
}
