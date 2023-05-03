package org.example.pom;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Main {
    public static RegistrationPage registrationPage;
//
//    public static void main(String[] args) throws InterruptedException {
//        File chromeDriver = new File("drivers/chromedriver.exe");
//        System.setProperty("webdriver.chrome.driver", chromeDriver.getAbsolutePath());
//        WebDriver driver = new ChromeDriver();
//        driver.get(PropertyReader.getProperty("bnm.url.registration"));
//
//        driver.manage().window().maximize();
//
//        registrationPage = new RegistrationPage(driver);
//        registrationPage.enterUsername("Maxim");
//        Thread.sleep(1000);
//        registrationPage.enterUserLastname("Korsakov");
//        Thread.sleep(1000);
//        registrationPage.enterEmail("maks.kors@gmail.com");
//        Thread.sleep(1000);
//        registrationPage.clickGender();
//        Thread.sleep(1000);
//        registrationPage.enterMobile("7360967372");
//        Thread.sleep(1000);
//        registrationPage.enterDateOfBirth("April", "2001");
//        Thread.sleep(1000);
//        registrationPage.enterSubject("Maths");
//        Thread.sleep(1000);
//        registrationPage.clickHobbiesButton();
//        Thread.sleep(1000);
//        registrationPage.uploadPicture("C:\\myPicture.jpg");
//        Thread.sleep(1000);
//        registrationPage.enterAddress("Florilor 2");
//        Thread.sleep(1000);
//        registrationPage.selectState();
//        registrationPage.selectCity();
//        Thread.sleep(500);
//        registrationPage.submitForm();
//        Thread.sleep(500);
//        driver.quit();
//
//        Actions actions = new Actions(driver);

    //        String day = "";
//        WebElement month = driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']"));
//        Select monthSelector = new Select(month);
//        monthSelector.selectByVisibleText(monthValue);
//        WebElement dayEl = driver.findElement(By.xpath("//div[text()='" + day +"']"));
//    }
    public static void main(String[] args) {
        String fromDate = "13.04.2023";
        String toDate = "01.01.2018";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate localDate = LocalDate.parse(toDate, formatter1);
//        String ss = String.valueOf(localDate.minusDays(30));
//        System.out.println(ss);
        List<String> list = List.of("11.01.20", "12.02.2020");
        LocalDate localDateTo = LocalDate.parse(toDate, formatter1);
        LocalDate localDateFrom = LocalDate.parse(fromDate, formatter1);
        String str = "";
        str = str.concat("new");
//        System.out.println(list.stream()
//                .map(s -> LocalDate.parse(fromDate, formatter1))
//                .allMatch(localDate1 -> localDate1.isBefore(localDateTo) && localDate1.isAfter(localDateFrom)));
//        System.out.println(localDate);
//        formatter1.format(localDate);
//
//        WebDriver driver1 = WebDriverManager.firefoxdriver().create();
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        System.out.println("hello");
        WebDriver webDriver = WebDriverManager.operadriver().create();
//        WebDriver driver = new OperaDriver();
        webDriver.get("https://www.youtube.com");
        System.out.println(webDriver.getTitle());
    }
}

