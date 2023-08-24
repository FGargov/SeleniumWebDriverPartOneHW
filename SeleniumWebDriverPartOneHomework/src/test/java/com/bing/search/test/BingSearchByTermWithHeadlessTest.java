package com.bing.search.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class BingSearchByTermWithHeadlessTest {
    private static WebDriver driver;

    @BeforeAll
    public static void classSetup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless");
        driver = new EdgeDriver(options);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));

        driver.get("https://bing.com");

        //Agree to consent
        WebElement acceptButton = driver.findElement(By.xpath("//*/button[@id='bnp_btn_accept']"));
        acceptButton.click();
    }

    @AfterAll
    public static void classTearDown(){
        driver.close();
    }

    @BeforeEach
    public  void testSetup() {
        driver.get("https://bing.com");
    }

    @Test
    public  void resultFound_when_searchTerm_telerikAcademy() {
        String searchTerm = "Telerik Academy Alpha";

        //Type text in search field
        WebElement searchField = driver.findElement(By.xpath("//form/input[@type='search'][@class='sb_form_q']"));
        searchField.sendKeys(searchTerm);

        //Click search button
        WebElement searchButton = driver.findElement(By.xpath("//*[@id='search_icon']"));
        searchButton.click();

        //Assert result found
        WebElement firstResult = driver.findElement(By.xpath("//*/ol[@id='b_results']/li[2]/div[@class='b_algo_group']/div[@class='tpcn']/a[@class='tilk']"));
        Assertions.assertEquals("IT Career Start in 6 Months - Telerik Academy Alpha", firstResult.getText(), "Search result not found.");
    }
}