package Unidad2.pages;

import Unidad2.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BaseClass {

    //Centralizar las estrategias de busqueda (By)
    By locatorGeneros = By.xpath("//input[@type='radio']");
    By locatorName = By.id("FirstName");
    By locatorLastName = By.name("LastName");
    By locatorEmail = By.xpath("//input[@data-val-required='Email is required.']");

    By locatorPassword = By.xpath("//input[@id='Password']");
    By locatorConfirmPassword = By.name("ConfirmPassword");
    By locatorBtnRegister = By.id("register-button");
    By locatorSussesfullMessage = By.xpath("//div[@class='result' and normalize-space(text())='Your registration completed']");

    By locatorErrorMessageMailUsed = By.xpath("//li[text()='The specified email already exists']");
    By locatorFirstNameEmpty = By.xpath("//span[@for='FirstName']");
    By locatorLastNameEmpty = By.xpath("//span[@for='LastName']");
    By locatorConfirmPasswordEmpty = By.xpath("//span[@for='ConfirmPassword']");
    By locatorLogin = By.xpath("//div[@class='header-logo']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void crearCuenta(int genero, String nombre, String apellido, String mail, String pass, String confirmPass) {
        esperarWebElements(locatorGeneros).get(genero); //0-1ro (Male) 1=2do (Female)
        agregarTexto(locatorName, nombre);
        agregarTexto(locatorLastName, apellido);
        agregarTexto(locatorEmail, mail);
        agregarTexto(locatorPassword, pass);
        agregarTexto(locatorConfirmPassword, confirmPass);
        click(locatorBtnRegister);
    }

    public String getMensajeCtaOK() {
        return obtenerTexto(locatorSussesfullMessage);
    }

    public String getErrorMensajeCta_MailUsed() {
        return obtenerTexto(locatorErrorMessageMailUsed);
    }

    public String getErrorFirstNameEmpty() {
        return obtenerTexto(locatorFirstNameEmpty);
    }

    public String getErrorLastNameEmpty() {
        return obtenerTexto(locatorLastNameEmpty);
    }

    public String getErrorConfirmPasswordEmpty() { return obtenerTexto(locatorConfirmPasswordEmpty); }

    public void irALogin(){
        click(locatorLogin);
    }
}

