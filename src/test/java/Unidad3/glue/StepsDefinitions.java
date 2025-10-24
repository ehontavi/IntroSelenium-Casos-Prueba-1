package Unidad3.glue;

import Unidad3.glue.pages.HomePage;
import Unidad3.glue.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepsDefinitions {
    HomePage homePage;
    LoginPage loginPage;

    @Given("el usuario se encuentra en el home")
    public void el_usuario_se_encuentra_en_el_home() {

        homePage = new HomePage();
        homePage.cargarUrl("https://demowebshop.tricentis.com/");

    }

    @When("selecciona link log in")
    public void selecciona_link_log_in() {
        homePage.irALogin();
        loginPage = new LoginPage();
    }

    @Then("se visualiza modal de inicio de sesion")
    public void se_visualiza_modal_de_inicio_de_sesion() {
        Assertions.assertEquals("Returning Customer",loginPage.obtenerTituloModalLogin());
    }

    @Then("caja de texto para el ingreso del mail")
    public void caja_de_texto_para_el_ingreso_del_mail() {
        Assertions.assertTrue(loginPage.obtenerTxtMail());
    }

    @Then("caja de texto para el ingreso del password")
    public void caja_de_texto_para_el_ingreso_del_password() {
        Assertions.assertTrue(loginPage.obtenerTxtPass());
    }

    @Then("boton iniciar sesion")
    public void boton_iniciar_sesion() {
        Assertions.assertTrue(loginPage.obtenerBtnLogin());
    }

    @When("se ingresa mail {string}")
    public void se_ingresa_mail(String docString) {
        loginPage.ingresarEmail(docString);
    }

    @When("se ingresa password {string}")
    public void se_ingresa_password(String docString) {
        loginPage.ingresarPassword(docString);
    }
    @When("presiona boton Log in")
    public void presiona_boton_log_in() {
        loginPage.login();
    }
    @Then("se visualiza el home del sitio")
    public void se_visualiza_el_home_del_sitio() {
        Assertions.assertEquals("Demo Web Shop",homePage.obtenerTituloPagina());
    }
    @Then("se visualiza el usuario logeado {string}")
    public void se_visualiza_el_usuario_logeado(String docString) {
        Assertions.assertEquals(docString,homePage.obtenerUsuarioLogeado());

    }
    @Then("se visualiza mensaje de error {string}")
    public void se_visualiza_mensaje_de_error(String docString) {
        Assertions.assertEquals(docString,loginPage.getLoginError());
    }
}


