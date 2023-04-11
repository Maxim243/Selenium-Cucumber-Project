package org.example.pomCRA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.ui.core.UITestBase.webDriverWait;
import static org.example.utils.UtilMethods.waitUntilElementIsDisplayed;

public class EnvironmentPage {

    WebDriver driver;

    @FindBy(xpath = "//input[@id='environmentname']")
    private WebElement environmentName;

    @FindBy(xpath = "//input[@id='environmentpassword']")
    private WebElement environmentPassword;

    @FindBy(id = "envlogin")
    private WebElement environmentLoginButton;

    @FindBy(xpath = "//h3[@class='panel-title text-center']")
    public static WebElement registrationTitle;

    public EnvironmentPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    public void enterEnvironmentPage() {
        environmentName.sendKeys("qatraining");
        environmentPassword.sendKeys("0Gx5");
        environmentLoginButton.click();
    }

}
