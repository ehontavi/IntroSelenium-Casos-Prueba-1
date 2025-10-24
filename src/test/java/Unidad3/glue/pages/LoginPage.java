package Unidad3.glue.pages;

import Unidad3.glue.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass {

    By locatorEmail = By.xpath("//input[@id='Email']");
    By locatorPass = By.xpath("//input[@id='Password']");
    By locatorBtnLogin = By.xpath("//input[@class='button-1 login-button']");
    By locatorModalLogin =By.xpath("//div[@class='returning-wrapper']/div[@class='title']");

    By locatorErrorLogin = By.xpath("//div[@class='validation-summary-errors']");



    public LoginPage() {
        super();
    }

    public void ingresarUsuario(String mail, String pass) {
        agregarTexto(locatorEmail, mail);
        agregarTexto(locatorPass, pass);
        click(locatorBtnLogin);


    }
    public String obtenerTituloModalLogin(){
        return obtenerTexto(locatorModalLogin);
    }

    public boolean obtenerTxtMail(){
        return validarElementoWeb(locatorEmail);
    }

    public boolean obtenerTxtPass() {
        return validarElementoWeb(locatorPass);
    }
    public boolean obtenerBtnLogin() {
        return validarElementoWeb(locatorEmail);
    }
    public String getEmail() {
        return obtenerTexto(locatorEmail);
    }

    public String getPassword() {
        return obtenerTexto(locatorPass);
    }

    public void ingresarEmail(String email){
        agregarTexto(locatorEmail,email);
    }
    public void ingresarPassword(String password) {
        agregarTexto(locatorPass,password);
    }
    public void login() {
        click(locatorBtnLogin);
    }

    public void login(String user,String pass){
        agregarTexto(locatorEmail,user);
        agregarTexto(locatorPass,pass);
        click(locatorBtnLogin);
    }
    public String getLoginError() {
        return obtenerTexto(locatorErrorLogin);
    }
}
