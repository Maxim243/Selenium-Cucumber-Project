package org.example.tests;
import org.example.PropertyReader;
import org.example.pomCRA.CRARegistrationPage;
import org.example.pomCRA.EnvironmentPage;
import org.example.ui.core.UITestBase;
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


public class RegistrationTestCases {

    private static WebDriver driver;
    public static WebDriverWait webDriverWait;
    private CRARegistrationPage craRegistrationPage;
    private static final String registrationTitleString = "//h3[contains(@class,'panel-title text-center')]";
    private static final String registrationFailedTextString = "//strong[text()='Registration failed!']";

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
        Reporter.log("Application started");
    }


    @DataProvider(name = "validUsernameAndPassword")
    public Object[][] dataProviderMethodValidUsernameAndPassword() {
        return new Object[][]{{"John527", "Mike123"}};
    }

    @DataProvider(name = "invalidUsernameAndValidPassword")
    public Object[][] dataProviderMethodInvalidUsername() {
        String password = "Maxim123";
        return new Object[][]{
                {"Va1", password},
                {"Maxim", password},
                {"MAXIM", password},
                {"maxim146", password},
                {"", password}
        };
    }

    @DataProvider(name = "invalidPasswordAndValidUsername")
    public Object[][] dataProviderMethodInvalidPassword() {
        String username = "Vlad111";
        return new Object[][]{
                {username, "Maxi1"},
                {username, "Maximlo"},
                {username, "MAXIM12"},
                {username, "maxim12"},
                {username, ""},
        };
    }

    @Test(dataProvider = "validUsernameAndPassword", priority = 1)
    public void enterValidUserNameAndPassword(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(name, password);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("unregisterButton")));
        craRegistrationPage.unregisterUser();
    }

    @Test(dataProvider = "invalidUsernameAndValidPassword", priority = 2)
    public void enterInvalidUsernameAndValidPassword(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(name, password);
        waitUntilElementIsDisplayed(By.xpath(registrationFailedTextString));
        Assert.assertTrue(driver.findElement(By.xpath(registrationFailedTextString)).isDisplayed());
    }

    @Test(dataProvider = "invalidPasswordAndValidUsername", priority = 3)
    public void enterInvalidPasswordAndValidUsername(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(name, password);
        waitUntilElementIsDisplayed(By.xpath(registrationFailedTextString));
        Assert.assertTrue(driver.findElement(By.xpath(registrationFailedTextString)).isDisplayed());
    }


    @Test(priority = 4, dataProvider = "validUsernameAndPassword")
    public void enterExistingUsername(String name, String password) {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(name, password);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logoutButton")));
        craRegistrationPage.clickLogoutButton();
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.registerCRARegistrationPage(name, password);
        waitUntilElementIsDisplayed(By.xpath(registrationFailedTextString));
        Assert.assertTrue(driver.findElement(By.xpath(registrationFailedTextString)).isDisplayed());
    }

    @Test(priority = 5)
    public void verifyLinkOfLimitedVersion() {
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        craRegistrationPage.clickLimitedVersionPage();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='nav navbar-nav navbar-right']")));
        craRegistrationPage.clickSingUp();
        waitUntilElementIsDisplayed(By.xpath(registrationTitleString));
        Assert.assertTrue(driver.findElement(By.xpath(registrationTitleString)).isDisplayed());
    }

    @AfterMethod
    public void driverQuit() {
        craRegistrationPage.exitRegistrationPage();
    }
}

