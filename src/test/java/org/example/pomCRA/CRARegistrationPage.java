package org.example.pomCRA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
// allure generate     fullpath to surefire
public class CRARegistrationPage {
    WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='User name']")
    private WebElement usernameTextBox;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordTextBox;

    @FindBy(xpath = "//button[text()='Register']")
    private WebElement registerButton;

    @FindBy(xpath = "//a[@id='unregisterButton']")
    private WebElement unregisterButton;

    @FindBy(xpath = "//button[text()='Limited version']")
    private WebElement limitedVersionButton;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right']")
    private WebElement singUpButton;

    @FindBy(xpath = "//a[@id='logoutButton']")
    private WebElement logoutButton;

    @FindBy(id = "login")
    private WebElement logInButton;

    public static final String registrationTitleString = "//h3[contains(@class,'panel-title text-center')]";

    public CRARegistrationPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    public void registerCRARegistrationPage(String name, String password) {
        usernameTextBox.sendKeys(name);
        passwordTextBox.sendKeys(password);
        registerButton.click();
    }

    public void unregisterUser() {
        unregisterButton.click();
    }

    public void exitRegistrationPage() {
        driver.quit();
    }

    public void sendingNameAndPassword(String name, String password) {
        usernameTextBox.sendKeys(name);
        passwordTextBox.sendKeys(password);
    }

    public void clickLimitedVersionPage() {
        limitedVersionButton.click();
    }

    public void clickSingUp() {
        singUpButton.click();
    }

    public void clickLogoutButton() {
        logoutButton.click();
    }

    public void registerButtonClick() {
        registerButton.click();
    }

    public void clickLogInButton() {
        logInButton.click();
    }
}


