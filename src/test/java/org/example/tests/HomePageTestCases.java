package org.example.tests;

import org.example.PropertyReader;
import org.example.pomCRA.CRARegistrationPage;
import org.example.pomCRA.EnvironmentPage;
import org.example.pomCRA.HomePageCRA;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.example.pomCRA.HomePageCRA.*;
import static org.example.utils.UtilMethods.waitUntilElementIsDisplayed;

public class HomePageTestCases {

    private static WebDriver driver;
    public static WebDriverWait webDriverWait;
    private HomePageCRA homePageCRA;
    private final String unregisterButton = "//a[@id='unregisterButton']";
    private final String currencyListRecords = "//div[@id='currenciesList']/div/button";


    private final String oldDate = "01.01.1993";
    private final String futureDate = "01.01.2024";

    private final String stringDate = "Date: ";
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private final String fromDate = "01.10.2018";
    private final String toDate = "01.11.2018";
    private final String registrationForm = "//h3[text() = 'Registration form']";
    private final LocalDate localDate = LocalDate.parse(fromDate, formatter);

    private final LocalDate localDateTo = LocalDate.parse(toDate, formatter);
    private final LocalDate localDateFrom = LocalDate.parse(fromDate, formatter);

    @BeforeMethod
    public void beforeImplementation() {
        File chromeDriver = new File("drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
        driver.get(PropertyReader.getProperty("bnm.url.registration"));
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 20);
        EnvironmentPage environmentPage = new EnvironmentPage(driver);
        environmentPage.enterEnvironmentPage();
        CRARegistrationPage craRegistrationPage = new CRARegistrationPage(driver);
        homePageCRA = new HomePageCRA(driver);
        waitUntilElementIsDisplayed(By.xpath("//h3[contains(@class,'panel-title text-center')]"));
        craRegistrationPage.sendingNameAndPassword("John1234", "John1234");
        craRegistrationPage.clickLogInButton();
    }

    @Test(description = "If currency nominal is not 1, it should be set to 1 and currency value should be recalculated accordingly. HOMRTS-001.1")
    public void currencyNominal() {
        double min = 0.05;
        double max = 0.07;

        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharCodes("HUF");
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.xpath(currencyListRecords));
        homePageCRA.getAllDates();
        homePageCRA.getAllValues();
        List<Double> listDouble =
                valuesList.stream()
                        .map(s -> s.substring(8))
                        .map(Double::parseDouble)
                        .toList();

        Assert.assertTrue(dateList.stream()
                .map(s -> LocalDate.parse(fromDate, formatter))
                .allMatch(localDate1 -> (localDate1.isBefore(localDateTo) || localDate1.equals(localDateTo)) && localDate1.isAfter(localDateFrom)
                        || localDate1.equals(localDateFrom)));

        Assert.assertTrue(listDouble.get(0) > min);
        Assert.assertTrue(listDouble.get(listDouble.size() - 1) < max);
    }


    //test failed (bug) - getting an error status code 500
    @Test(description = "Verify if user specified the 'From' date older than 01.01.1994 error message is displayed. HOMRTS-002(1)")
    public void oldFromDateCurrency() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(oldDate);
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.id("onGetCurrencyRatesFailAlert"));
        Assert.assertEquals(homePageCRA.verifyDateOldError(oldDate), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }

    //test failed (bug) - getting an incorrect date
    @Test(description = "Verify if 'From date' is set by default. HOMRTS-002(2)")
    public void futureToDateCurrency() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterToDateCurrencyRates(futureDate);
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.id("onGetCurrencyRatesFailAlert"));
        Assert.assertEquals(homePageCRA.verifyDateFutureError(futureDate), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }

    //test failed (bug) - getting an error 500 status code
    @Test(description = "Verify if user specified the 'To' date older than 01.01.1994 error message is displayed. HOMRTS-002(3)")
    public void oldToDateCurrency() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterToDateCurrencyRates(oldDate);
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.id("onGetCurrencyRatesFailAlert"));
        Assert.assertEquals(homePageCRA.verifyDateOldError(oldDate), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }


    @Test(description = "Verify if user specified the 'From' date  greater than current date error message is displayed. HOMRTS-002(4)")
    public void futureFromDateCurrency() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(futureDate);
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.id("onGetCurrencyRatesFailAlert"));
        Assert.assertEquals(homePageCRA.verifyDateFutureError(futureDate), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }

    @Test(description = "Verify if 'From date' is set by default. HOMRTS-002(5)")
    public void defaultFromDateCurrency() {
        String toDate = "01.01.2018";
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.xpath(currencyListRecords));
        homePageCRA.getAllDates();

        LocalDate localDate = LocalDate.parse(toDate, formatter);
        String dateMinus30Days = formatter.format(localDate.minusMonths(1));
        Assert.assertTrue(dateList.get(0).equals(stringDate + dateMinus30Days) && dateList.get(dateList.size() - 1).equals(stringDate + toDate));
    }


    //    requirement - Currency rates are displayed(only displayed)
    @Test(description = "Verify if user specified valid date currency rates are displayed. HOMRTS-002(6)")
    public void validFromDateCurrency() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.id("currenciesList"));
        Assert.assertTrue(driver.findElement(By.id("currenciesList")).isDisplayed());
    }

    //test failed (bug) - getting an error 500 status code
    @Test(description = "Verify if 'To' date is set by default. HOMRTS-002(7)")
    public void defaultToDate() {
        LocalDate localDate = LocalDate.now();
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();

        Assert.assertEquals(stringDate + formatter.format(localDate), dateList.get(dateList.size() - 1));
    }

    //bug, we should get 60 dates = we get 59 dates
    @Test(description = "Verify if only 60 dates are displayed. HOMRTS-002(8)")
    public void verify60Dates() {
        String fromDate1 = "01.08.2018";

        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        homePageCRA.clearFromDateCharCode();
        homePageCRA.clearToDateCharCode();
        Assert.assertTrue(dateList.get(0).equals(stringDate + fromDate) && dateList.get(dateList.size() - 1).equals(stringDate + toDate));

        homePageCRA.enterFromDateCurrencyRates(fromDate1);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.clickGetCurrencyButton();
//        waitUntilElementIsDisplayed(By.xpath(currencyListRecords));
        homePageCRA.getAllDates();
        LocalDate localDate = LocalDate.parse(fromDate1, formatter);
        String dateString60Days = formatter.format(localDate.plusMonths(2));
        Assert.assertEquals(stringDate + fromDate1, dateList.get(0));
        Assert.assertEquals(dateList.get(dateList.size() - 1), (stringDate + dateString60Days));
    }

    @Test(description = "Verify if user can get Currency rates when char codes are not specified. HOMRTS-003(1)")
    public void verifyGetCurrencyRatesNotSpecifiedCharCode() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        String dateString60Days = formatter.format(localDate.plusMonths(1));

        Assert.assertEquals(dateList.get(0), (stringDate + fromDate));
        Assert.assertEquals(dateList.get(dateList.size() - 1), stringDate + dateString60Days);
    }

    //test failed (bug) - if we specify 2 Char Codes, records with the first char code specified - are displayed
    @Test(description = "Verify if user can get Currency rates for valid char codes. HOMRTS-003(2)")
    public void verifyGetCurrencyRatesWithSpecifiedCharCode() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterCharCodes("EUR");
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.id("currenciesList"));
        homePageCRA.getAllChars();
        homePageCRA.getAllDates();
        boolean euroBoolean = charCodesList.stream()
                .allMatch(s -> s.contains("EUR"));
        Assert.assertTrue(euroBoolean);
        Assert.assertEquals(dateList.get(0), stringDate + fromDate);
        Assert.assertEquals(dateList.get(dateList.size() - 1), stringDate + toDate);


        homePageCRA.enterCharCodes(", USD");
        homePageCRA.clickGetCurrencyButton();
        waitUntilElementIsDisplayed(By.xpath(currencyListRecords));
        homePageCRA.getAllDates();

        //verifying if record does not contain any other char codes
        boolean euroAndUsdBoolean = charCodesList.stream()
                .allMatch(s -> s.contains("USD") || s.contains("EUR"));

        Assert.assertTrue(charCodesList.contains("USD") && charCodesList.contains("EUR"));
        Assert.assertTrue(euroAndUsdBoolean);
        Assert.assertEquals(dateList.get(0), stringDate + fromDate);
        Assert.assertEquals(dateList.get(dateList.size() - 1), stringDate + toDate);
    }

    @Test(description = "Verify if user can not get Currency rates for invalid char codes HOMRTS-003(3)")
    public void invalidCharCodeSpecified() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharCodes("EURO");
        homePageCRA.clickGetCurrencyButton();
        List<WebElement> listOfWebElements1 = driver.findElements(By.xpath(currencyListRecords));
        Assert.assertTrue(listOfWebElements1.isEmpty());

        homePageCRA.clearCharCodesField();
        homePageCRA.enterCharCodes("EUR CAD");
        homePageCRA.clickGetCurrencyButton();
        List<WebElement> listOfWebElements2 = driver.findElements(By.xpath(currencyListRecords));
        Assert.assertTrue(listOfWebElements2.isEmpty());
    }

    @Test(description = "Verify if user can use both of cases HOMRTS-003.1")
    public void charCodeCase() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharCodes("EUR");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllChars();
        Assert.assertTrue(charCodesList.stream()
                .allMatch(s -> s.contains("EUR")));

        homePageCRA.clearCharCodesField();
        homePageCRA.enterCharCodes("eur, cad");
        homePageCRA.getAllChars();
        boolean euroAndCADBoolean = charCodesList.stream()
                .allMatch(s -> s.contains("EUR") || s.contains("CAD"));

        Assert.assertTrue(charCodesList.contains("EUR") && charCodesList.contains("CAD"));
        Assert.assertTrue(euroAndCADBoolean);

        homePageCRA.clearCharCodesField();
        homePageCRA.enterCharCodes("EuR, caD");
        homePageCRA.getAllChars();
        boolean euroAndcaDBoolean = charCodesList.stream()
                .allMatch(s -> s.contains("EUR") || s.contains("CAD"));

        Assert.assertTrue(charCodesList.contains("EUR") && charCodesList.contains("CAD"));
        Assert.assertTrue(euroAndcaDBoolean);
    }

    @Test(description = "Verify if user can get Currency rates when currency names are not specified HOMRTS-004(1)")
    public void getCharCodesWhenCurrencyNameIsNotSpecified() {
        String datePlus30Days = formatter.format(localDate.plusMonths(1));
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterCharCodes("Eur");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        Assert.assertEquals(dateList.get(0), stringDate + fromDate);
        Assert.assertEquals(dateList.get(dateList.size() - 1), stringDate + toDate);
        Assert.assertEquals(stringDate + datePlus30Days, dateList.get(dateList.size() - 1));
    }

    // bug, CRA does not include today's date
    @Test(description = "Verify if user can get Currency rates for valid currency names HOMRTS-004(2)")
    public void getCharCodesWithCurrencyNameSpecified() {
        LocalDate localDate = LocalDate.now();
        String dateNow = formatter.format(localDate);
        String dateMinus30Days = formatter.format(localDate.minusMonths(1));
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharNameField("Euro");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllChars();
        Assert.assertTrue(charCodesList.stream()
                .allMatch(s -> s.contains("EUR")));

        homePageCRA.clearCharNameField();
        homePageCRA.enterCharNameField("Euro, Dollar");
        homePageCRA.clickGetCurrencyButton();
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(currencyListRecords)));
        homePageCRA.getAllDates();
        Assert.assertEquals(dateList.get(0), stringDate + dateMinus30Days);
        Assert.assertEquals(dateList.get(dateList.size() - 1), stringDate + dateNow);
        boolean euroAndDollarBoolean = charCodesList.stream()
                .allMatch(s -> s.contains("EUR") || s.contains("USD"));
        Assert.assertTrue(charCodesList.contains("EUR") && charCodesList.contains("USD") && euroAndDollarBoolean);
    }

    @Test(description = "Verify if user can get Curency rates for invalid currency names HOMRTS-004(3)")
    public void invalidCurrencyNames() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharNameField("Eur");
        homePageCRA.clickGetCurrencyButton();
        List<WebElement> listOfWebElements1 = driver.findElements(By.xpath(currencyListRecords));
        Assert.assertTrue(listOfWebElements1.isEmpty());

        homePageCRA.clearCharNameField();
        homePageCRA.enterCharNameField("Euro US Dollar");
        homePageCRA.clickGetCurrencyButton();
        List<WebElement> listOfWebElements2 = driver.findElements(By.xpath(currencyListRecords));
        Assert.assertTrue(listOfWebElements2.isEmpty());
    }

//    @Test(description = "Verify if user gets result in selected language HOMRTS-004 (4)")
//    public void getCurrencyInSelectedLanguage() {
//        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
//        homePageCRA.clickGetCurrencyButton();
//        homePageCRA.getAllChars();
//
//    }


    // no data is returned
    @Test(description = "Verify if user can use both of cases HOMRTS-004.1")
    public void getCurrencyWithUpperAndLowerCase() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharNameField("EURO");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllChars();
        Assert.assertTrue(charCodesList.stream()
                .allMatch(s -> s.contains("EUR")));

        homePageCRA.clearCharNameField();
        homePageCRA.enterCharNameField("euro, us dollar");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllChars();
        Assert.assertTrue(charCodesList.stream()
                .allMatch(s -> s.contains("EUR") || s.contains("USD")));
        Assert.assertTrue(charCodesList.contains("EUR") && charCodesList.contains("USD"));

        homePageCRA.clearCharNameField();
        homePageCRA.enterCharNameField("EuRo, Us DollAR");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllChars();
        Assert.assertTrue(charCodesList.stream()
                .allMatch(s -> s.contains("EUR") || s.contains("USD")));
        Assert.assertTrue(charCodesList.contains("EUR") && charCodesList.contains("USD"));
    }

    @Test(description = "Verify if user can filter currency values by specifiying currency values range. HOMRTS-005(1)")
    public void getCurrencyWithEmptyValueField() {
        String dateString30Days = formatter.format(localDate.plusMonths(1));
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharCodes("EUR");
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        homePageCRA.getAllChars();
        Assert.assertEquals(dateList.get(0), stringDate + fromDate);
        Assert.assertEquals(stringDate + dateString30Days, dateList.get(dateList.size() - 1));
    }

    @Test(description = "Verify if user can filter currency values by specifiying currency values range. HOMRTS-005(2)")
    public void getCurrencyValuesBySpecifiedFromInput() {
        String dateString30Days = formatter.format(localDate.plusMonths(1));
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterFromValueInput("19.2");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        homePageCRA.getAllChars();
        Assert.assertEquals(dateList.get(0), stringDate + fromDate);
        Assert.assertEquals(stringDate + dateString30Days, dateList.get(dateList.size() - 1));
    }

    @Test(description = "Verify if user can filter currency values by specifiying currency values range. HOMRTS-005(3)")
    public void getCurrencyValuesNegative() {
        String invalidValueFrom1 = "-19.2";
        String invalidValueFrom2 = "*#%^";
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterFromValueInput(invalidValueFrom1);
        homePageCRA.clickGetCurrencyButton();
        Assert.assertEquals(homePageCRA.verifyCurrencyValue(invalidValueFrom1), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
        homePageCRA.enterToValueInput("abc");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.clearFromValueField();
        homePageCRA.enterFromValueInput(invalidValueFrom2);
        Assert.assertEquals(homePageCRA.verifyCurrencyValue(invalidValueFrom2), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }


    @Test(description = "Verify if user can filter currency values by specifiying currency values range. HOMRTS-005(4)")
    public void getCurrencyByValueRange() {
        double min = 19.2;
        double max = 19.5;
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterCharCodes("EUR");
        homePageCRA.enterFromValueInput(String.valueOf(min));
        homePageCRA.enterToValueInput(String.valueOf(max));
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllValues();
        homePageCRA.getAllDates();
        List<Double> listDouble =
                valuesList.stream()
                        .map(s -> s.substring(8))
                        .map(Double::parseDouble)
                        .toList();
        Assert.assertTrue(listDouble.get(0) > min);
        Assert.assertTrue(listDouble.get(listDouble.size() - 1) < max);
        // should verify if our data is in range fromDate and ToDate

        Assert.assertTrue(dateList.stream()
                .map(s -> LocalDate.parse(fromDate, formatter))
                .allMatch(localDate1 -> (localDate1.isBefore(localDateTo) || localDate1.equals(localDateTo)) && localDate1.isAfter(localDateFrom)
                        || localDate1.equals(localDateFrom)));

        homePageCRA.clearToValueField();
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        homePageCRA.getAllValues();
        Assert.assertTrue(listDouble.get(0) > min);
        Assert.assertTrue(dateList.stream()
                .map(s -> LocalDate.parse(fromDate, formatter))
                .allMatch(localDate1 -> (localDate1.isBefore(localDateTo) || localDate1.equals(localDateTo)) && localDate1.isAfter(localDateFrom)
                        || localDate1.equals(localDateFrom)));
    }

    @Test(description = "Verify if user can filter currency values by specifiying currency values range. HOMRTS-005(5)")
    public void getCurrentValueToNegative() {
        double min = 19.2;
        double max = 19.5;
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterFromValueInput(String.valueOf(min));
        homePageCRA.enterToValueInput(String.valueOf(max));
        homePageCRA.enterCharCodes("EUR");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllDates();
        homePageCRA.getAllValues();
        List<Double> listDouble =
                valuesList.stream()
                        .map(s -> s.substring(8))
                        .map(Double::parseDouble)
                        .toList();
        Assert.assertTrue(listDouble.get(0) > min);
        Assert.assertTrue(listDouble.get(listDouble.size() - 1) < max);

        Assert.assertTrue(dateList.stream()
                .map(s -> LocalDate.parse(fromDate, formatter))
                .allMatch(localDate1 -> (localDate1.isBefore(localDateTo) || localDate1.equals(localDateTo)) && localDate1.isAfter(localDateFrom)
                        || localDate1.equals(localDateFrom)));

        homePageCRA.clearToValueField();
        homePageCRA.enterToValueInput("abc");

        Assert.assertEquals(homePageCRA.verifyCurrencyValue("abc"), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());

        homePageCRA.clearToValueField();
        homePageCRA.enterToValueInput("a#$^");
        Assert.assertEquals(homePageCRA.verifyCurrencyValue("a#$^"), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }

    @Test(description = "Verify if user can specify currency names in any available language.  HOMRTS-006(1)")
    public void verifyLanguage() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharNameField("Euro");
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllChars();
    }

    @Test(description = "Verify if  user specified char code and currency names which does not correspond to each other. HOMRTS-007(1)")

    public void getCurrencyMismatchCurrencyNameAndCode() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterCharNameField("Euro");
        homePageCRA.enterCharCodes("USD");
        homePageCRA.clickGetCurrencyButton();
        List<WebElement> listOfWebElements2 = driver.findElements(By.xpath(currencyListRecords));
        Assert.assertTrue(listOfWebElements2.isEmpty());
    }

    @Test(description = "Verify if user gets error message if only English is entered in 'Language field. HOMRTS-008(1)")
    public void getErrorMessageWhenOnlyLanguageIsSpecified() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterLanguage("ru");
        homePageCRA.clickGetCurrencyButton();
        Assert.assertEquals(homePageCRA.verifyLanguage(), driver.findElement(By.id("onGetCurrencyRatesFailAlert")).getText());
    }

    @Test(description = "Verify if user introduces valid data he gets currency rates. HOMRTS-001(1)")
    public void getCurrencyRates() {
        String dateString30Days = formatter.format(localDate.plusMonths(1));
        double min = 19.1;
        double max = 19.5;
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.enterFromDateCurrencyRates(fromDate);
        homePageCRA.enterToDateCurrencyRates(toDate);
        homePageCRA.enterCharCodes("Eur");
        homePageCRA.enterLanguage("En");
        homePageCRA.enterFromValueInput(String.valueOf(min));
        homePageCRA.enterToValueInput(String.valueOf(max));
        homePageCRA.clickGetCurrencyButton();
        homePageCRA.getAllValues();
        homePageCRA.getAllDates();
        homePageCRA.getAllChars();
        homePageCRA.getAllValues();
        List<Double> listDouble =
                valuesList.stream()
                        .map(s -> s.substring(8))
                        .map(Double::parseDouble)
                        .toList();
        Assert.assertTrue(listDouble.get(0) > min);
        Assert.assertTrue(listDouble.get(listDouble.size() - 1) < max);

        Assert.assertTrue(dateList.stream()
                .map(s -> LocalDate.parse(fromDate, formatter))
                .allMatch(localDate1 -> (localDate1.isBefore(localDateTo) || localDate1.equals(localDateTo)) && localDate1.isAfter(localDateFrom)
                        || localDate1.equals(localDateFrom)));
        Assert.assertTrue(charCodesList.stream()
                .allMatch(s -> s.contains("EUR")));
    }

    @Test(description = "Verify that User can log out from the system and log in with the same user credentials. HOMGEN-001(1)")
    public void logInAndLogOutWithTheSameCredentials() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.clickLogout();
        waitUntilElementIsDisplayed(By.xpath(registrationForm));
        homePageCRA.enterNameAndPassword("John1234", "John1234");
        homePageCRA.clickLogIn();
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        Assert.assertTrue(driver.findElement(By.xpath(unregisterButton)).isDisplayed());
    }

    @Test(description = "Verify that User is able to unregister a User and can not log into the system with the same user credentials. HOMGEN-002")
    public void unregister() {
        waitUntilElementIsDisplayed(By.xpath(unregisterButton));
        homePageCRA.clickUnregister();
        waitUntilElementIsDisplayed(By.xpath(registrationForm));
        Assert.assertTrue(driver.findElement(By.xpath(registrationForm)).isDisplayed());
    }

    @AfterMethod
    public void quit() {
        driver.close();
    }
}
