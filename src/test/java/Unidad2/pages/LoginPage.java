package Unidad2.pages;

import Unidad2.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass {

    By locatorEmail = By.xpath("//input[@id='Email']");
    By locatorPass = By.xpath("//input[@id='Password']");
    By locatorBtnLogin = By.xpath("//input[@class='button-1 login-button']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void ingresarUsuario(String mail, String pass) {
        agregarTexto(locatorEmail, mail);
        agregarTexto(locatorPass, pass);
        click(locatorBtnLogin);


    }






    public String getEmail() {
        return obtenerTexto(locatorEmail);
    }

    public String getPassword() {
        return obtenerTexto(locatorPass);
    }



}
