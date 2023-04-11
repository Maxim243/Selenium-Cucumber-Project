package org.example.pom;

import org.example.PropertyReader;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class RegistrationPage {
    public WebDriver driver;


    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement usernameTextBox;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement userLastnameTextBox;

    @FindBy(xpath = "//input[@placeholder='name@example.com']")
    private WebElement emailTextBox;

    @FindBy(xpath = "//label[text()='Male']")
    private WebElement genderTextBox;

    @FindBy(xpath = "//input[@placeholder='Mobile Number']")
    private WebElement mobileTextBox;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthTextBox;

    @FindBy(xpath = "//input[@id='subjectsInput']")
    private WebElement subjectTextBox;

    @FindBy(xpath = "//label[text()='Sports']")
    private WebElement hobbiesButton;

    @FindBy(xpath = "//label[@for='uploadPicture']")
    private WebElement pictureButton;

    @FindBy(xpath = "//input[@id='uploadPicture']")
    private WebElement choosePicture;

    @FindBy(xpath = "//textarea[@placeholder='Current Address']")
    private WebElement addressTextBox;

    @FindBy(xpath = "//div[text()='Select State']")
    private WebElement selectStateButton;

    @FindBy(xpath = "//div[text()='Haryana']")
    private WebElement stateHaryana;

    @FindBy(xpath = "//div[text()='Select City']")
    private WebElement selectCityButton;

    @FindBy(xpath = "//div[text()='Karnal']")
    private WebElement cityKarnal;

    @FindBy(xpath = "//button[@id ='submit']")
    private WebElement submitFormButton;

    RegistrationPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.driver = webDriver;
    }

    public void submitForm() {
        submitFormButton.click();
    }

    public void selectCity() throws InterruptedException {
        selectCityButton.click();
        Thread.sleep(200);
        cityKarnal.click();
    }

    public void selectState() throws InterruptedException {
        selectStateButton.click();
        Thread.sleep(200);
        stateHaryana.click();
    }

    public void enterAddress(String address) {
        addressTextBox.clear();
        addressTextBox.sendKeys(address);
    }

    public void uploadPicture(String imagePath) {
//        pictureButton.click();
        choosePicture.sendKeys(imagePath);
    }

    public void enterSubject(String subject) {
        subjectTextBox.clear();
        subjectTextBox.sendKeys(subject);
        subjectTextBox.sendKeys(Keys.ENTER);
    }

    public void clickHobbiesButton() {
        hobbiesButton.click();
    }

    public void enterUsername(String username) {
        usernameTextBox.clear();
        usernameTextBox.sendKeys(username);
    }

    public void enterUserLastname(String userLastname) {
        userLastnameTextBox.clear();
        userLastnameTextBox.sendKeys(userLastname);
    }

    public void enterEmail(String email) {
        emailTextBox.clear();
        emailTextBox.sendKeys(email);
    }

    public void enterMobile(String mobile) {
        mobileTextBox.clear();
        mobileTextBox.sendKeys(mobile);
    }

    public void clickGender() {
        genderTextBox.click();
    }

    public void enterDateOfBirth(String month, String year) throws InterruptedException {
        dateOfBirthTextBox.click();
        WebElement monthElement = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
        WebElement yearElement = driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']"));
        Select monthSelector = new Select(monthElement);
        monthSelector.selectByVisibleText(month);
        Thread.sleep(500);
        Select yearSelector = new Select(yearElement);
        yearSelector.selectByValue(year);
        Thread.sleep(500);
        WebElement dayElement = driver.findElement(By.xpath("//div[text()='12' and contains(@aria-label, 'April')]"));
        dayElement.click();
//        Thread.sleep(500);
//        year.click();
//        Thread.sleep(500);
//        day.click();
    }
}

