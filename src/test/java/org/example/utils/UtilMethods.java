package org.example.utils;

import org.example.tests.RegistrationTestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.example.tests.LoginTestCases.webDriverWait;

public class UtilMethods {

    public static void waitUntilElementIsDisplayed(By byElement) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(byElement));
    }
}
