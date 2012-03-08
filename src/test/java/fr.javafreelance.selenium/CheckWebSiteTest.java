package fr.javafreelance.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.fest.assertions.Assertions.assertThat;

public class CheckWebSiteTest {

    WebDriver webDriver;


    @Test
    public void globalTest() throws Exception {
        System.setProperty("webdriver.chrome.driver","/usr/local/Cellar/chromedriver/16.0.902.0/bin/chromedriver") ;

        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:9000");

        assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
        assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 1 to 10 of 574");

        webDriver.findElement(By.cssSelector("#pagination li.next a")).click();
        assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 11 to 20 of 574");

        webDriver.findElement(By.id("searchbox")).sendKeys("Apple");
        webDriver.findElement(By.id("searchsubmit")).click();

        assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("13 computers found");

        webDriver.findElement(By.linkText("Apple II Plus")).click();
        assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("Edit computer");


        webDriver.findElement(By.id("discontinued")).sendKeys("xxx");
        webDriver.findElement(By.cssSelector("input.primary")).click();


        assertThat(webDriver.findElements(By.cssSelector("div.error")).size()).isEqualTo(1);
        assertThat(webDriver.findElement(By.cssSelector("div.error label")).getText()).isEqualTo("Discontinued date");

        webDriver.findElement(By.id("discontinued")).clear();
        webDriver.findElement(By.id("discontinued")).sendKeys("");
        webDriver.findElement(By.cssSelector("input.primary")).click();

        assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
        assertThat(webDriver.findElement(By.className("alert-message")).getText()).isEqualTo("Done! Computer Apple II Plus has been updated");
        webDriver.quit();
       }
//   System.setProperty("webdriver.chrome.driver","/usr/local/Cellar/chromedriver/16.0.902.0/bin/chromedriver") ;


}
