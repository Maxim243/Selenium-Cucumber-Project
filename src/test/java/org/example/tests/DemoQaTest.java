package org.example.tests;

import org.example.PropertyReader;
import org.example.pomCRA.CRARegistrationPage;
import org.example.pomCRA.EnvironmentPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.File;
import java.util.Iterator;
import java.util.Set;

public class DemoQaTest {

    static WebDriver driver;
    static WebDriverWait waiter;
    @FindBy(xpath = "//div[@class = 'col-12 mt-4 col-md-6']")
    private static WebElement frameDiv;

    public static void main(String[] args) throws InterruptedException {
        File chromeDriver = new File("drivers/chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
        driver = new ChromeDriver();
        waiter = new WebDriverWait(driver, 10);
        driver.get(PropertyReader.getProperty("bnm.url.registration"));
        driver.manage().window().maximize();

//        System.out.println(driver.findElements(By.tagName("iframe")).size());
//
//        System.out.println(driver.switchTo().frame(1).findElement(By.xpath("//h1[text() = 'This is a sample page']")).getText());
//        Thread.sleep(500);
//
//        System.out.println(driver.switchTo().defaultContent());
//
//
//        System.out.println(driver.switchTo().frame(2).findElement(By.id("sampleHeading")).getText());


//        driver.findElement(By.xpath("//button[@id='tabButton']")).click();
//        Thread.sleep(500);
//        driver.findElement(By.id("windowButton")).click();
//        Thread.sleep(500);
//        String parent = driver.getWindowHandle();
//        Set<String> windowsId = driver.getWindowHandles();
//        for (String s : windowsId) {
//            System.out.println(s);
//        }
//        driver.navigate().back();
//
//        for (String childWindow : windowsId) {
//            if (!parent.equals(childWindow)) {
//                Thread.sleep(1000);
//                System.out.println(driver.switchTo().window(childWindow).findElement(By.id("sampleHeading")).getText());
//            }

//        System.out.println(parent);
//        System.out.println(windowsId);
//        driver.switchTo().window(pa)


//        }
//        driver.findElement(By.id("alertButton")).click();
//        waiter.until(ExpectedConditions.alertIsPresent());
//        System.out.println(driver.switchTo().alert().getText());
//        driver.switchTo().alert().accept();
//
//        driver.findElement(By.id("timerAlertButton")).click();
//        waiter.until(ExpectedConditions.alertIsPresent());
//        System.out.println(driver.switchTo().alert().getText());
//        driver.switchTo().alert().accept();
////        waiter.until(ExpectedConditions.alertIsPresent());
//
//        driver.findElement(By.id("confirmButton")).click();
//        waiter.until(ExpectedConditions.alertIsPresent());
//        System.out.println(driver.switchTo().alert().getText());
//        driver.switchTo().alert().accept();
//
//        driver.findElement(By.id("promtButton")).click();
//        waiter.until(ExpectedConditions.alertIsPresent());
//        System.out.println(driver.switchTo().alert().getText());
//        driver.switchTo().alert().sendKeys("Alert field");
//        driver.switchTo().alert().accept();

//        System.out.println(driver.switchTo().frame(1).switchTo().frame(0).findElement(By.xpath("//p[text()='Child Iframe']")).getText());
//        Thread.sleep(500);
//        System.out.println(driver.switchTo().parentFrame().findElement(By.xpath("//body[text()='Parent frame']")).getText());
//        System.out.println(driver.getPageSource());
//        driver.close();

        driver.findElement(By.id("showSmallModal")).click();
//        waiter.until(ExpectedConditions.alertIsPresent());
        System.out.println(driver.switchTo().frame("SmallModal").getTitle());
    }
}
