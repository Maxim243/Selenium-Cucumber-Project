package org.example.utils;

import org.example.tests.RegistrationTestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.tests.HomePageTestCases.webDriverWait;

//import static org.example.tests.LoginTestCases.webDriverWait;

//import static org.example.tests.RegistrationTestCases.webDriverWait;

//import static org.example.ui.core.UITestBase.webDriverWait;

public class UtilMethods {

    public static void waitUntilElementIsDisplayed(By byElement) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
    }
}
