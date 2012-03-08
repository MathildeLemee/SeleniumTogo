package fr.javafreelance.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.fest.assertions.Assertions.assertThat;

@Test(threadPoolSize = 3, invocationCount = 10)
public class CheckWebSiteTestSeleniumTestNGGrid {


    public void globalTest() throws MalformedURLException, InterruptedException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setBrowserName("internet explorer");
        WebDriver webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), desiredCapabilities);

       //Thread.sleep(5000);
        try {
            webDriver.get("http://192.168.56.1:9000");

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
            assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 1 to 10 of 574");

            webDriver.findElement(By.cssSelector("#pagination li.next a")).click();

            assertThat(webDriver.findElement(By.cssSelector("#pagination li.current")).getText()).isEqualTo("Displaying 11 to 20 of 574");

            webDriver.findElement(By.cssSelector("#searchbox")).sendKeys("Apple");
            webDriver.findElement(By.cssSelector("#searchbox")).submit();

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("13 computers found");
            webDriver.findElement(By.linkText("Apple II Plus")).click();
            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("Edit computer");

             Thread.sleep(3000);
            webDriver.findElement(By.id("discontinued")).sendKeys("xxx");
            webDriver.findElement(By.cssSelector("input.primary")).click();
/*


            assertThat(webDriver.findElements(By.cssSelector("div.error")).size()).isEqualTo(1);
            assertThat(webDriver.findElement(By.cssSelector("div.error label")).getText()).isEqualTo("Discontinued date");

            webDriver.findElement(By.id("discontinued")).clear();
            webDriver.findElement(By.id("discontinued")).sendKeys("");
            webDriver.findElement(By.cssSelector("input.primary")).click();

            assertThat(webDriver.findElement(By.cssSelector("section h1")).getText()).isEqualTo("574 computers found");
            assertThat(webDriver.findElement(By.className("alert-message")).getText()).isEqualTo("Done! Computer Apple II Plus has been updated");
*/

        } finally {
            //   webDriver.close();
            webDriver.quit();

        }
    }

}
