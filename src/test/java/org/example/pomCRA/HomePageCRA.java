package org.example.pomCRA;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

import static org.example.tests.HomePageTestCases.webDriverWait;

public class HomePageCRA {

    public static List<String> dateList;
    public static List<String> charCodesList;
    public static List<String> valuesList;

    public static List<String> currenciesList;

    private WebDriver driver;

    @FindBy(xpath = "//input[@placeholder='User name']")
    private WebElement usernameTextBox;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordTextBox;
    @FindBy(id = "charCodesSearchParam")
    private WebElement charCodes;
    @FindBy(id = "fromDateSearchParam")
    private WebElement fromDateInput;

    @FindBy(id = "unregisterButton")
    private WebElement unregisterButton;
    @FindBy(id = "toDateSearchParam")
    private WebElement toDateInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(id = "getCurrencyRatesButton")
    private WebElement getCurrencyRatesButton;

    @FindBy(id = "currencyNamesSearchParam")
    private WebElement charNameInput;

    @FindBy(id = "currencyValueFromSearchParam")
    private WebElement valueFromInput;

    @FindBy(id = "currencyValueToSearchParam")
    private WebElement valueToInput;

    @FindBy(id = "languageSearchParam")
    private WebElement languageInput;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    public HomePageCRA(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    public void enterFromDateCurrencyRates(String date) {
        fromDateInput.sendKeys(date);
    }

    public void enterToDateCurrencyRates(String date) {
        toDateInput.sendKeys(date);
    }

    public void clickGetCurrencyButton() {
        getCurrencyRatesButton.click();
    }

    public void enterCharCodes(String charCode) {
        charCodes.sendKeys(charCode);
    }

    public void clearFromDateCharCode() {
        fromDateInput.clear();
    }

    public void clearToDateCharCode() {
        toDateInput.clear();
    }

    public void getAllDates() {
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(driver.findElement(By.id("currencyRatesProgressbar")))));
        dateList = webDriverWait.until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(By.xpath("//div[@id='currenciesList']/div/button")))
                .stream()
                .map(WebElement::getText)
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .filter(s -> s.contains("Date"))
                .toList();
    }

    public void getAllChars() {
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(driver.findElement(By.id("currencyRatesProgressbar")))));
        charCodesList = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='currenciesList']/div/button")))
                .stream()
                .map(WebElement::getText)
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .filter(s -> s.contains("Name"))
                .toList();

    }

    public void getAllValues() {
        webDriverWait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(driver.findElement(By.id("currencyRatesProgressbar")))));
        valuesList = webDriverWait.until(ExpectedConditions.
                        presenceOfAllElementsLocatedBy(By.xpath("//div[@id='currenciesList']/div/button")))
                .stream()
                .map(WebElement::getText)
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .filter(s -> s.contains("Value"))
                .toList();
    }

    public String verifyDateFutureError(String date) {
        return "400 Bad Request. Reason: '" + date + "' future date is not valid";
    }

    public String verifyDateOldError(String date) {
        return "400 Bad Request. Reason: '" + date + "' old date is not valid";
    }

    public String verifyCurrencyValue(String value) {
        return "400 Bad Request. Reason: '" + value + "' is not a valid double";
    }

    public String verifyLanguage() {
        return "400 Bad Request. Reason: Body is null";
    }

    public void clearCharCodesField() {
        charCodes.clear();
    }

    public void enterCharNameField(String name) {
        charNameInput.sendKeys(name);
    }

    public void clearCharNameField() {
        charNameInput.clear();
    }

    public void enterFromValueInput(String name) {
        valueFromInput.sendKeys(name);
    }

    public void enterToValueInput(String name) {
        valueToInput.sendKeys(name);
    }

    public void clearFromValueField() {
        valueFromInput.clear();
    }

    public void clearToValueField() {
        valueToInput.clear();
    }

    public void enterLanguage(String name) {
        languageInput.sendKeys(name);
    }

    public void clickLogout() {
        logoutButton.click();
    }

    public void enterNameAndPassword(String name, String password) {
        usernameTextBox.sendKeys(name);
        passwordTextBox.sendKeys(password);
    }

    public void clickLogIn() {
        loginButton.click();
    }

    public void clickUnregister() {
        unregisterButton.click();
    }
}

