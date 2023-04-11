package org.example.ui.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.PropertyReader;
import org.example.pomCRA.CRARegistrationPage;
import org.example.ui.core.UITestBase;
import org.example.ui.pom.EnvironmentLoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import static org.example.utils.UtilMethods.waitUntilElementIsDisplayed;

public class RegistrationStepDefinitions extends UITestBase {

    private final CRARegistrationPage craRegistrationPage = new CRARegistrationPage(getDriver());

    @Given("CRA Environment Login Page is opened")
    public void cra_environment_login_page_is_opened() {
        chromeDriver.get(PropertyReader.getProperty("bnm.url.registration"));

    }

    @When("Login with valid environment credentials")
    public void login_with_valid_environment_credentials() {
        ChromeDriver driver = getDriver();
        EnvironmentLoginPage environmentLoginPage = new EnvironmentLoginPage(driver);
        environmentLoginPage.enterEnvironmentName("qatraining");
        environmentLoginPage.enterPassword("0Gx5");
        environmentLoginPage.login();
    }

    @Then("User login page should be displayed")
    public void user_login_page_should_be_displayed() {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        Assert.assertTrue(chromeDriver.findElement(By.xpath(registrationTitleString)).isDisplayed());
    }

    @When("Entering valid username {string} and valid password {string}")
    public void entering_a_valid_username(String username, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.sendingNameAndPassword(username, password);
    }

    @When("Clicking on Register")
    public void clicking_on_register() {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerButtonClick();
    }

    @Then("HomePage should be displayed")
    public void home_page_should_be_displayed() {
        waitUntilElementIsDisplayed(By.xpath("//a[@id='unregisterButton']"));
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//a[@id='unregisterButton']")).isDisplayed());
//        waitUntilElementIsDisplayed(By.xpath("//a[@id='unregisterButton']"));
//        craRegistrationPage.unregisterUser();
    }

//    @When("Entering invalid <username> {string} and valid <password> {string}")
//    public void enteringInvalidUsernameAndValidPassword(String arg0, String arg1) {
//        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
//        craRegistrationPage.registerCRARegistrationPage(arg0, arg1);
//        Assert.assertTrue(chromeDriver.findElement(By.xpath("//a[@id='unregisterButton']")).isDisplayed());
//    }


    @When("Entering invalid {string} and valid {string}")
    public void enteringInvalidAndValid(String arg0, String arg1) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(arg0, arg1);
        waitUntilElementIsDisplayed(By.xpath(registrationFailedTextString));
        Assert.assertTrue(chromeDriver.findElement(By.xpath(registrationFailedTextString)).isDisplayed());
//        Assert.assertTrue(chromeDriver.findElement(By.xpath("//a[@id='unregisterButton']")).isDisplayed());
    }

    @Then("“Registration failed! Check your username or password.” message is displayed.")
    public void registrationFailedCheckYourUsernameOrPasswordMessageIsDisplayed() {
        waitUntilElementIsDisplayed(By.xpath(registrationFailedTextString));
        Assert.assertTrue(chromeDriver.findElement(By.xpath(registrationFailedTextString)).isDisplayed());
    }

    @After
    public void quit() {
        chromeDriver.quit();
    }


    @When("Entering valid {string} and invalid {string}")
    public void enteringValidAndInvalid(String arg0, String arg1) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(arg0, arg1);
        waitUntilElementIsDisplayed(By.xpath(registrationFailedTextString));
        Assert.assertTrue(chromeDriver.findElement(By.xpath(registrationFailedTextString)).isDisplayed());
    }

    @And("Clicking on Logout")
    public void clickingOnLogout() {
        craRegistrationPage.clickLogoutButton();
    }

    @And("Clicking on Unregister")
    public void clickingOnUnregister() {
        craRegistrationPage.unregisterUser();
    }

    @When("Clicking on Limited Version Button")
    public void clickingOnLimitedVersionButton() {
        waitUntilElementIsDisplayed(By.xpath("//button[text()='Limited version']"));
        chromeDriver.findElement(By.xpath("//button[text()='Limited version']")).click();
    }

    @Then("The CRA Limited Version should be displayed")
    public void theCRALimitedVersionShouldBeDisplayed() {
        waitUntilElementIsDisplayed(By.xpath("//b[text() = 'Information']"));
        Assert.assertTrue(chromeDriver.findElement(By.xpath("//b[text() = 'Information']")).isDisplayed());
    }

    @When("Clicking on SingUp Button")
    public void clickingOnSingUpButton() {
        waitUntilElementIsDisplayed(By.id("signUpButton"));
        chromeDriver.findElement(By.id("signUpButton")).click();
    }

    @Then("The Registration form should be opened.")
    public void theRegistrationFormShouldBeOpened() {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        Assert.assertTrue(chromeDriver.findElement(By.xpath(registrationTitleString)).isDisplayed());
    }
}
