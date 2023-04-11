package org.example.ui.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UITestBase {
    protected ChromeDriver chromeDriver = null;
    public static WebDriverWait webDriverWait;
//
//    public static WebDriver driver;
    public final String registrationTitleString = "//h3[contains(@class,'panel-title text-center')]";
    public final String registrationFailedTextString = "//strong[text()='Registration failed!']";
    private void initDriver() {
        if (chromeDriver == null || chromeDriver.getSessionId() == null) {
            System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
            chromeDriver = new ChromeDriver();
            webDriverWait = new WebDriverWait(chromeDriver, 10);
        } else {
            chromeDriver.close();
            chromeDriver.quit();
            chromeDriver = null;
        }
    }

    protected ChromeDriver getDriver() {
        if (chromeDriver == null) {
            initDriver();
        }
        return chromeDriver;
    }

//    protected void closeDriver() {
//        if (chromeDriver != null) {
//
//        }
//    }

}
