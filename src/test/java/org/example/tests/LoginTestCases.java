package org.example.tests;

import org.example.PropertyReader;
import org.example.pomCRA.CRARegistrationPage;
import org.example.pomCRA.EnvironmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.io.File;

import static org.example.utils.UtilMethods.waitUntilElementIsDisplayed;

public class LoginTestCases {

    private static WebDriver driver;
    public static WebDriverWait webDriverWait;

    private CRARegistrationPage craRegistrationPage;

    private static final String registrationTitleString = "//h3[contains(@class,'panel-title text-center')]";

    @BeforeMethod
    public void beforeImplementation() {
        File chromeDriver = new File("drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
        Reporter.log("Browser Opened");
        driver.get(PropertyReader.getProperty("bnm.url.registration"));
        driver.manage().window().maximize();
        Reporter.log("Browser Maximized");
        webDriverWait = new WebDriverWait(driver, 10);
        EnvironmentPage environmentPage = new EnvironmentPage(driver);
        environmentPage.enterEnvironmentPage();
        craRegistrationPage = new CRARegistrationPage(driver);
    }

    @DataProvider(name = "existingUser")
    public Object[][] dataProviderMethodValidUsernameAndPassword() {
        return new Object[][]{{"John1234", "John1234"}};
    }

    @DataProvider(name = "newLoginAndPassword")
    public Object[][] dataProviderMethodNewUsernameAndPassword() {
        return new Object[][]{{"Maxim123", "Maxim156"}};
    }

    @DataProvider(name = "existingUsernameAndWrongPassword")
    public Object[][] dataProviderMethodExistingsernameAndWrongPassword() {
        return new Object[][]{{"John1234", "Maxim156"}};
    }

    @Test(dataProvider = "existingUser")
    public void loginAnExistingUser(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.sendingNameAndPassword(name, password);
        craRegistrationPage.clickLogInButton();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unregisterButton")));
        Assert.assertTrue(driver.findElement(By.id("unregisterButton")).isDisplayed());
    }

    @Test(dataProvider = "newLoginAndPassword")
    public void loginWithNewCredentials(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.sendingNameAndPassword(name, password);
        craRegistrationPage.clickLogInButton();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text() ='Login failed!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//strong[text() ='Login failed!']")).isDisplayed());
    }

    @Test(dataProvider = "existingUsernameAndWrongPassword")
    public void loginWithAnExistingUsernameAndWrongPassword(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.sendingNameAndPassword(name, password);
        craRegistrationPage.clickLogInButton();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//strong[text() ='Login failed!']")));
        Assert.assertTrue(driver.findElement(By.xpath("//strong[text() ='Login failed!']")).isDisplayed());
    }

    @AfterMethod
    public void exit() {
        driver.quit();
    }

}
