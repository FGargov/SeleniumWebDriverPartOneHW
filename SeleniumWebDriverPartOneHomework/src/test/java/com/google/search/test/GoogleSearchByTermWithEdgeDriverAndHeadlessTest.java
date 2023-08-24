package com.google.search.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleSearchByTermWithEdgeDriverAndHeadlessTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    String searchTerm = "Telerik Academy Alpha";
    String expectedResult = "IT Career Start in 6 Months - Telerik Academy Alpha";
    String actualResult = "";

    @BeforeAll
    public static void classSetup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        driver = new EdgeDriver(options);

        wait = new WebDriverWait(driver, Duration.ofSeconds(4));

        driver.get("https://google.com");

        //Agree to consent
        WebElement acceptButton = driver.findElement(By.xpath("//button[@id='L2AGLb']"));
        acceptButton.click();
    }

    @AfterAll
    public static void classTearDown(){
        driver.close();
    }

    @BeforeEach
    public  void testSetup() {
        driver.get("https://google.com");
    }

    @Test
    public void resultFound_when_searchTermProvided_telerikAcademy() {
        //Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys(searchTerm);

        //Click search button
        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[2]"));
        wait.until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();

        //Assert result found
        WebElement firstResult = driver.findElement(By.xpath("//a/h3[1]"));
        actualResult = firstResult.getText();
        Assertions.assertEquals(expectedResult, actualResult, "Search result not found.");
    }
}
