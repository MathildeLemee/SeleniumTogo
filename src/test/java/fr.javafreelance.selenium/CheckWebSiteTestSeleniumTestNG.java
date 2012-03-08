package fr.javafreelance.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

public class CheckWebSiteTestSeleniumTestNG {

    @Test(threadPoolSize = 3,invocationCount = 5)
    public void globalTest() throws MalformedURLException, InterruptedException {

        DesiredCapabilities desired =  new DesiredCapabilities();
        desired.setBrowserName("firefox");


        WebDriver webDriver = new RemoteWebDriver(new URL("http://192.168.56.1:4444/wd/register"),desired);

        try {
            webDriver.get("http://localhost:9000");

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
            assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 1 to 10 of 574");

            webDriver.findElement(By.cssSelector("#pagination li.next a")).click();

            assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 11 to 20 of 574");

            webDriver.findElement(By.cssSelector("#searchbox")).sendKeys("Apple");
            webDriver.findElement(By.cssSelector("#searchbox")).submit();

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("13 computers found");
            webDriver.findElement(By.linkText("Apple II Plus")).click();
            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("Edit computer");

            webDriver.findElement(By.id("discontinued")).sendKeys("xxx");
            webDriver.findElement(By.cssSelector("input.primary")).click();

        } finally {
            //   webDriver.close();
            webDriver.quit();

        }
    }

}
