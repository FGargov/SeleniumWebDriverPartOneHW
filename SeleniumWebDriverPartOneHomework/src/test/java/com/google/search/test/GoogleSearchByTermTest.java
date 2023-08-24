package com.google.search.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.time.Duration;
public class GoogleSearchByTermTest {
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));//Това означава, че всеки път, когато извикате driver.findElement(), WebDriver ще изчака до 4 секунди, преди да се откаже да намери елемента и да репортува грешка.

        driver.get("https://google.com");

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
        String searchTerm = "Telerik Academy Alpha";
        //Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//textarea[@type='search']"));
        searchField.sendKeys(searchTerm);

        //Click search button
        WebElement searchButton = driver.findElement(By.xpath("(//input[@type='submit' and @name='btnK'])[2]"));
        searchButton.click();

        //Assert result found
        WebElement firstResult = driver.findElement(By.xpath("//a/h3[1]"));
        Assertions.assertTrue(firstResult.getText().contains(searchTerm), "Search result not found.");
    }
}
