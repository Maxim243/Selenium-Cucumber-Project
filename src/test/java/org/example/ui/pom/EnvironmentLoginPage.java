package org.example.ui.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnvironmentLoginPage {
    @FindBy(xpath = "//input[@id='environmentname']")
    private WebElement environmentNameTexBox;

    @FindBy(xpath = "//input[@id='environmentpassword']")
    private WebElement environmentPassword;

    @FindBy(id = "envlogin")
    private WebElement environmentLoginButton;

    @FindBy(xpath = "//h3[@class='panel-title text-center']")
    public static WebElement registrationPanel;

    public EnvironmentLoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

//    public void enterEnvironmentPage() {
//        environmentNameTexBox.sendKeys("qatraining");
//        environmentPassword.sendKeys("0Gx5");
//        environmentLoginButton.click();
//    }

    public void enterEnvironmentName(String environmentName){
        environmentNameTexBox.sendKeys(environmentName);
    }

    public void enterPassword(String environmentPasswordArg){
        environmentPassword.sendKeys(environmentPasswordArg);
    }

    public void login(){
        environmentLoginButton.click();
    }
}
