package Unidad3.glue.pages;

import Unidad3.glue.utils.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BaseClass {
    //Centralizar las estrategias de busqueda
    By locatorRegister = By.linkText("Register");
    By locatorBtnVote = By.xpath("//input[@id='vote-poll-1']");
    By locatorPollExcelent = By.xpath("//input[@id='pollanswers-1']");

    By locatorErrorVoteWithoutRegistration = By.xpath("//div[@id='block-poll-vote-error-1']");
    By locatorVoteOK = By.xpath("//span[@class='poll-total-votes']");

    By locatorLoginOk = By.xpath("//div[@class='header-links']");

    By locatorlogin = By.linkText("Log in");
    By locatorErrorLogin = By.xpath("//div[@class='validation-summary-errors']");



    public HomePage() {
        super();
    }

    //Acciones
    public void irARegister() {
        click(locatorRegister);
    }

    public void VotePollVote() {
        click(locatorBtnVote);
    }

    public void VotePollExcelent() {
        click(locatorPollExcelent);

    }

    public String getErrorVoteWithoutRegistration() {
        return obtenerTexto(locatorErrorVoteWithoutRegistration);
    }

    public boolean getVoteOK() {
        return validarElementoWeb(locatorVoteOK);
    }

    public boolean getLoginOK() {
        return validarElementoWeb(locatorLoginOk);
    }
    public void irALogin() {
            click(locatorlogin);
    }
    public String getErrorLogin() {
        return obtenerTexto(locatorErrorLogin);
    }
}
